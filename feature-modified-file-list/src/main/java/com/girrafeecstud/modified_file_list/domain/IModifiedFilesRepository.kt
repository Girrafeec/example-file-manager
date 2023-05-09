package com.girrafeecstud.modified_file_list.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import kotlinx.coroutines.flow.Flow

interface IModifiedFilesRepository {
    fun getModifiedFiles(): Flow<BusinessResult<List<FileInfo>>>
}
