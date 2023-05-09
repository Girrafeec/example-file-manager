/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.data

import android.os.Environment
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.file_list_api.di.annotation.LocalFilesDataSourceQualifer
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import com.girrafeecstud.modified_file_list.domain.IModifiedFilesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ModifiedFilesRepository @Inject constructor(
    @LocalFilesDataSourceQualifer private val filesDataSource: IFilesDataSource,
    private val hashCalculator: FileHashCalculator
) : IModifiedFilesRepository {

    override fun getModifiedFiles(): Flow<BusinessResult<List<FileInfo>>> =
        //TODO change to constant value from utils
        filesDataSource.getAllFilesList(path = Environment.getExternalStorageDirectory().absolutePath)
            .flatMapLatest {
                
            }

}