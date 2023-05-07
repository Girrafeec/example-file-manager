/* Created by Girrafeec */

package com.girrafeecstud.file_list_api.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow


interface IFileListInteractor {

    // TODO change path parameter?
    fun getFileList(path: String): Flow<BusinessResult<List<FileInfo>>>

}