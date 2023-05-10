/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.modified_files_api.domain.IModifiedFilesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ModifiedFilesInteractor @Inject constructor(
    private val repository: IModifiedFilesRepository
) : IModifiedFilesInteractor {

    override fun getModifiedFiles(path: String): Flow<BusinessResult<List<FileInfo>>> =
        repository.getModifiedFiles(path = path).flowOn(Dispatchers.IO)
}