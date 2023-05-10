package com.girrafeecstud.modified_files_impl.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import kotlinx.coroutines.flow.Flow

interface IModifiedFilesRepository {
    fun getModifiedFiles(path: String): Flow<BusinessResult<List<FileInfo>>>
}
