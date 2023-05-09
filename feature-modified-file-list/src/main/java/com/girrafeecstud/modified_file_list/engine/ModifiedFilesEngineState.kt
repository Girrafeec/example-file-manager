package com.girrafeecstud.modified_file_list.engine

import com.girrafeecstud.file_list_api.domain.FileInfo

data class ModifiedFilesEngineState(
    val isLoading: Boolean = false,
    val modifiedFiles: List<FileInfo>? = null
)
