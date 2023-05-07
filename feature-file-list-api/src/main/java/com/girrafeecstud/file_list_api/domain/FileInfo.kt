package com.girrafeecstud.file_list_api.domain

import java.time.LocalDateTime

data class FileInfo(
    val path: String,
    val creationDate: LocalDateTime,
    val name: String,
    val size: Long,
    val fileType: FileType,
    val isChanged: Boolean = false
)
