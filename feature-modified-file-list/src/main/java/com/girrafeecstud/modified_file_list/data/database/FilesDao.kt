/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface FilesDao {
    @Query("SELECT * from files")
    fun getAllFiles(): List<FileEntity>

    @Query("SELECT * FROM files WHERE path = :path")
    fun getFileByPath(path: String): FileEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(file: FileEntity)

    @Delete
    suspend fun delete(file: FileEntity)
}