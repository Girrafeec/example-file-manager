/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.IFilesInteractor
import com.girrafeecstud.file_list_api.domain.IFilesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FilesInteractor @Inject constructor(
    private val repository: IFilesRepository
) : IFilesInteractor {

    override fun getFilesList(path: String): Flow<BusinessResult<List<FileInfo>>> =
        repository.getFilesList(path = path).flowOn(Dispatchers.IO)
}