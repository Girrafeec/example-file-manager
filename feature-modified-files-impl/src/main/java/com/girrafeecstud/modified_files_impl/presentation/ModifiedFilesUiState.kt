package com.girrafeecstud.modified_files_impl.presentation

import com.girrafeecstud.file_list_api.domain.FileInfo

data class ModifiedFilesUiState(
    val isLoading: Boolean = false,
    val files: List<FileInfo>? = null
)
