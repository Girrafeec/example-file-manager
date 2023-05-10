/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ModifiedFilesInteractor @Inject constructor(
    private val repository: IModifiedFilesRepository
) {

    fun getModifiedFiles(path: String): Flow<BusinessResult<List<FileInfo>>> =
        repository.getModifiedFiles(path = path).flowOn(Dispatchers.IO)
}