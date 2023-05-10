/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.data

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow

interface IFilesHashDataSource {

    fun getFilesAndHashes(): Flow<BusinessResult<Map<String, String>>>

    fun updateFilesHashes(filesAndHashes: Map<String, String>): Flow<BusinessResult<EmptyResult>>

}