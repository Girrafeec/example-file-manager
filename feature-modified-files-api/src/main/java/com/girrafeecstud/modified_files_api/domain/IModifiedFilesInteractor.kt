/* Created by Girrafeec */

package com.girrafeecstud.modified_files_api.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import kotlinx.coroutines.flow.Flow

interface IModifiedFilesInteractor {
    fun getModifiedFiles(path: String): Flow<BusinessResult<List<FileInfo>>>
}