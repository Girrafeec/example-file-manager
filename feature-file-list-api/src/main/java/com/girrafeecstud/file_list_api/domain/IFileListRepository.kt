/* Created by Girrafeec */

package com.girrafeecstud.file_list_api.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface IFileListRepository {

    fun getFileList(path: String): Flow<BusinessResult<List<FileInfo>>>

}