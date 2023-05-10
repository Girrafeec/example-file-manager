/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.data

import android.util.Log
import com.girrafeecstud.core_base.base.ExceptionType
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.modified_files_impl.domain.IModifiedFilesRepository
import kotlinx.coroutines.flow.*
import java.io.FileNotFoundException
import javax.inject.Inject

class ModifiedFilesRepository @Inject constructor(
    private val filesHashDataSource: IFilesHashDataSource,
    private val filesDataSource: IFilesDataSource,
    private val hashCalculator: FileHashCalculator
) : IModifiedFilesRepository {

    override fun getModifiedFiles(path: String): Flow<BusinessResult<List<FileInfo>>> =
        combine(
            filesDataSource.getAllFilesList(path = path),
            filesHashDataSource.getFilesAndHashes()
        ) { filesResult, filesHashesResult ->
            Pair(filesResult, filesHashesResult)
        }.flatMapLatest { (filesResult, filesHashesResult) ->
            when (filesResult) {
                is BusinessResult.Success -> {
                    when (filesHashesResult) {
                        is BusinessResult.Success -> {

                            Log.i("modified files rep", "filesResult= $filesResult")
                            val files = filesResult.data!!
                            val oldHashes = filesHashesResult.data!!
                            val newHashes = mutableMapOf<String, String>()
                            var modifiedFiles: ArrayList<FileInfo>? = ArrayList()

                            // Compare new hashes with hashes from db
                            hashCalculator.calculateFilesHashes(files)
                                .catch { e ->
                                    if (e is FileNotFoundException)
                                        flowOf(BusinessResult.Exception(exceptionType = ExceptionType.FILE_OR_DIR_NOT_EXIST))
                                }
                                .collect { filesAndHashes ->
                                    Log.i("modified files rep", "after calc $filesAndHashes")
                                    filesAndHashes.forEach { (fileInfo, hash) ->
                                        Log.i("modified files rep", "$fileInfo ; $hash")
                                        val path = fileInfo.path
                                        newHashes[path] = hash
                                        if (oldHashes.containsKey(path) && oldHashes[path] != hash) {
                                            modifiedFiles?.add(fileInfo)
                                        }
                                    }
                                }
                            // Update hashes in db
                            filesHashDataSource.updateFilesHashes(newHashes).collect()

                            if (modifiedFiles?.isEmpty() == true)
                                modifiedFiles = null
                            flowOf(BusinessResult.Success(data = modifiedFiles))
                        }
                        is BusinessResult.Error -> flowOf(BusinessResult.Error(filesHashesResult.businessErrorType))
                        is BusinessResult.Exception -> flowOf(BusinessResult.Exception(filesHashesResult.exceptionType))
                    }
                }
                is BusinessResult.Error -> flowOf(BusinessResult.Error(filesResult.businessErrorType))
                is BusinessResult.Exception -> flowOf(BusinessResult.Exception(filesResult.exceptionType))
            }
        }

}