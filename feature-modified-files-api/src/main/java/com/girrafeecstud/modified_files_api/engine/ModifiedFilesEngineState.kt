package com.girrafeecstud.modified_files_api.engine

import com.girrafeecstud.file_list_api.domain.FileInfo

data class ModifiedFilesEngineState(
    val isLoading: Boolean = false,
    val modifiedFiles: List<FileInfo>? = null
)
