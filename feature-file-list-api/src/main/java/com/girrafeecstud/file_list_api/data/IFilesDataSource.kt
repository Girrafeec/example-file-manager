/* Created by Girrafeec */

package com.girrafeecstud.file_list_api.data

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import kotlinx.coroutines.flow.Flow

interface IFilesDataSource {

    fun getFilesList(path: String): Flow<BusinessResult<List<FileInfo>>>

}