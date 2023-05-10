package com.girrafeecstud.file_list_api.domain

import org.threeten.bp.LocalDateTime

data class FileInfo(
    val path: String,
    val creationDate: LocalDateTime,
    val name: String,
    val size: Long,
    val fileType: FileType,
    val isChanged: Boolean = false
)
