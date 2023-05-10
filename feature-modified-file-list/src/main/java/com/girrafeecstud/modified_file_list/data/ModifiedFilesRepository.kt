/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.data

import android.os.Environment
import com.girrafeecstud.core_base.base.ExceptionType
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.file_list_api.di.annotation.LocalFilesDataSourceQualifer
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import com.girrafeecstud.modified_file_list.domain.IModifiedFilesRepository
import kotlinx.coroutines.flow.*
import java.io.FileNotFoundException
import javax.inject.Inject

class ModifiedFilesRepository @Inject constructor(
    private val filesHashDataSource: IFilesHashDataSource,
    @LocalFilesDataSourceQualifer private val filesDataSource: IFilesDataSource,
    private val hashCalculator: FileHashCalculator
) : IModifiedFilesRepository {

    override fun getModifiedFiles(path: String): Flow<BusinessResult<List<FileInfo>>> =
        combine(
            //TODO change to constant value from utils
            filesDataSource.getAllFilesList(path = path),
            filesHashDataSource.getFilesAndHashes()
        ) { filesResult, filesHashesResult ->
            Pair(filesResult, filesHashesResult)
        }.flatMapLatest { (filesResult, filesHashesResult) ->
            when (filesResult) {
                is BusinessResult.Success -> {
                    when (filesHashesResult) {
                        is BusinessResult.Success -> {

                            val files = filesResult.data!!
                            val oldHashes = filesHashesResult.data!!
                            val newHashes = mutableMapOf<String, String>()
                            var modifiedFiles: ArrayList<FileInfo> = ArrayList()

                            // Compare new hashes with ahshes from db
                            try {
                                hashCalculator.calculateFilesHashes(files)
                                    .collect { filesAndHashes ->
                                        filesAndHashes.forEach { (fileInfo, hash) ->
                                            val path = fileInfo.path
                                            newHashes[path] = hash
                                            if (oldHashes.containsKey(path) && oldHashes[path] != hash) {
                                                modifiedFiles.add(fileInfo)
                                            }
                                        }
                                    }
                                // Update hashes in db
                                filesHashDataSource.updateFilesHashes(newHashes).collect()

//                                if (modifiedFiles?.isEmpty() == true)
//                                    modifiedFiles = null
                                flowOf(BusinessResult.Success(data = modifiedFiles))
                            } catch (e: FileNotFoundException) {
                                flowOf(BusinessResult.Exception(exceptionType = ExceptionType.FILE_OR_DIR_NOT_EXIST))
                            }
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