/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.data

import com.girrafeecstud.file_list_api.domain.FileInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.FileNotFoundException
import java.security.MessageDigest

class FileHashCalculator {

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 8192
    }

    fun calculateFilesHashes(files: List<FileInfo>): Flow<Pair<FileInfo, String>> = flow {
        val md = MessageDigest.getInstance("SHA-256")
        for (fileInfo in files) {
            val file = File(fileInfo.path)
            if (!file.exists())
                throw FileNotFoundException()
            val inputStream = file.inputStream()
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                md.update(buffer, 0, bytesRead)
            }
            inputStream.close()
            val hashBytes = md.digest()
            val hash = hashBytes.toHex()
            emit(Pair(fileInfo, hash))
        }
    }.flowOn(Dispatchers.IO)

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }

}