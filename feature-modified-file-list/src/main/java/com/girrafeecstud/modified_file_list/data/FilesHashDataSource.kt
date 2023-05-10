package com.girrafeecstud.modified_file_list.data

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_base.domain.base.EmptyResult
import com.girrafeecstud.modified_file_list.data.database.FileEntity
import com.girrafeecstud.modified_file_list.data.database.FilesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FilesHashDataSource @Inject constructor(
    private val dao: FilesDao
) : IFilesHashDataSource {

    override fun getFilesAndHashes(): Flow<BusinessResult<Map<String, String>>> =
        flow {
            val files = dao.getAllFiles()
            val map = files.associate { file ->
                file.path to file.hash
            }
            emit(BusinessResult.Success(data = map))
        }.flowOn(Dispatchers.IO)

    override fun updateFilesHashes(filesAndHashes: Map<String, String>): Flow<BusinessResult<EmptyResult>> =
        flow {
            filesAndHashes.forEach { (path, hash) ->
                val file = FileEntity(path = path, hash = hash)
                dao.insert(file = file)
                emit(BusinessResult.Success(data = EmptyResult))
            }
        }.flowOn(Dispatchers.IO)
}
