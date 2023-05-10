/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.data

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import com.girrafeecstud.core_base.base.ExceptionType
import com.girrafeecstud.core_base.base.NoReadMemoryPermissionsException
import com.girrafeecstud.core_base.base.sortByName
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import com.girrafeecstud.file_list_api.utils.FileListUtils.hasReadMemoryPermissions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime
import javax.inject.Inject

class LocalFilesDataSource @Inject constructor(
    private val context: Context
) : IFilesDataSource {

    override fun getFilesAndDirsList(path: String): Flow<BusinessResult<List<FileInfo>>> =
        flow {
            val files = mutableListOf<FileInfo>()

            try {
                files.addAll(getFilesAndDirs(dirPath = path))
            } catch (e: FileNotFoundException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.FILE_OR_DIR_NOT_EXIST))
            } catch (e: NoReadMemoryPermissionsException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_READ_MEMORY_PERMISSION))
            }

            val sortedFiles = files.sortByName { it.name }
            emit(BusinessResult.Success(data = sortedFiles))
        }

    override fun getAllFilesList(path: String): Flow<BusinessResult<List<FileInfo>>> =
        flow {
            val files = mutableListOf<FileInfo>()

            Log.i("files", "all files list path = $path")
            try {
                files.addAll(getFiles(dirPath = path))
            } catch (e: FileNotFoundException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.FILE_OR_DIR_NOT_EXIST))
            } catch (e: NoReadMemoryPermissionsException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_READ_MEMORY_PERMISSION))
            }

            Log.i("files", "all files list = $files")
            emit(BusinessResult.Success(data = files))
        }

    private fun getFilesAndDirs(dirPath: String): List<FileInfo> {

        if (!context.hasReadMemoryPermissions())
            throw NoReadMemoryPermissionsException()

        val dir = File(dirPath)

        val files = mutableListOf<FileInfo>()
        if (!dir.exists()) {
            throw FileNotFoundException()
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

    private fun getFiles(dirPath: String): List<FileInfo> {

        Log.i("files", "get files from $dirPath")
        if (!context.hasReadMemoryPermissions())
            throw NoReadMemoryPermissionsException()

        val dir = File(dirPath)

        val files = mutableListOf<FileInfo>()
        if (!dir.exists()) {
            throw FileNotFoundException()
        }
        dir.listFiles()?.forEach { file ->

            val fileCreationTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val fileAttr = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
                val fileTime = fileAttr.creationTime().toMillis()
                fileTime
            } else {
                file.lastModified()
            }

            if (!file.isDirectory) {
                //TODO do smth with version
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
            files.addAll(getFiles(dirPath = file.absolutePath))
        }
        return files
    }
}