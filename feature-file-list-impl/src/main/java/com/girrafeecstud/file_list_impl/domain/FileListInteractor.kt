/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.IFileListInteractor
import com.girrafeecstud.file_list_api.domain.IFileListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FileListInteractor @Inject constructor(
    private val repository: IFileListRepository
) : IFileListInteractor {

    override fun getFileList(path: String): Flow<BusinessResult<List<FileInfo>>> =
        repository.getFileList(path = path).flowOn(Dispatchers.IO)
}