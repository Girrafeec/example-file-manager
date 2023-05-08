package com.girrafeecstud.file_list_impl.presentation

import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.file_list_api.domain.FileInfo

data class FileListUiState(
    val isLoading: Boolean = false,
    val files: List<FileInfo>? = null
) : UiState
