/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.data

import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class LocalFilesDataSource @Inject constructor(
    private val context: Context
) : IFilesDataSource {

    override fun getFilesList(path: String): Flow<BusinessResult<List<FileInfo>>> =
        flow {
            val files = mutableListOf<FileInfo>()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                files.addAll(getFilesAndDirs(dirPath = path))
            }

            emit(BusinessResult.Success(data = files))
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFilesAndDirs(dirPath: String): List<FileInfo> {

        val dir = File(dirPath)

        val files = mutableListOf<FileInfo>()
        if (!dir.exists()) {
            TODO("throw an error")
        }
        dir.listFiles()?.forEach { file ->

            val fileCreationTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val fileAttr = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
                val fileTime = fileAttr.creationTime().toMillis()
                fileTime
            } else {
                file.lastModified()
            }

            if (file.isDirectory) {

                //TODO do smth with version
                files.add(
                    FileInfo(
                        path = file.absolutePath,
                        creationDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(fileCreationTime), ZoneId.systemDefault()),
                        name = file.name,
                        size = file.length(),
                        fileType = FileType.FOLDER,
                        isChanged = false
                    )
                )
            }
            else {
                files.add(
                    FileInfo(
                        path = file.absolutePath,
                        creationDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(fileCreationTime), ZoneId.systemDefault()),
                        name = file.name,
                        size = file.length(),
                        fileType = FileType.fromExtension(extension = file.extension),
                        isChanged = false
                    )
                )
            }
        }
        return files
    }
}