/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.data

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.data.IFileListDataSource
import com.girrafeecstud.file_list_api.domain.FileInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalFileListDataSource @Inject constructor() : IFileListDataSource {

    override fun getFileList(path: String): Flow<BusinessResult<List<FileInfo>>> {
        TODO("Not yet implemented")
    }
}