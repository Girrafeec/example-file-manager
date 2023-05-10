package com.girrafeecstud.modified_file_list.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "files")
data class FileEntity(
    @PrimaryKey
    val path: String,
    val hash: String
)
