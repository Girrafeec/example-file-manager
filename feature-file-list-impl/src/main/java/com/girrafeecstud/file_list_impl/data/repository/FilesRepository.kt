/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.data.repository

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.IFilesRepository
import com.girrafeecstud.file_list_impl.data.LocalFilesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FilesRepository @Inject constructor(
    private val dataSource: LocalFilesDataSource
) : IFilesRepository {

    override fun getFilesList(path: String): Flow<BusinessResult<List<FileInfo>>> {
        return dataSource.getFilesAndDirsList(path = path).flowOn(Dispatchers.IO)
    }
}