/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.file_list_impl.domain.FilesInteractor
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FileListViewModel @Inject constructor(
    private val filesInteractor: FilesInteractor
)
    : BaseViewModel<FileListUiState>() {

    override var _state: MutableStateFlow<FileListUiState> = MutableStateFlow(FileListUiState())
    override val state: StateFlow<FileListUiState> = _state.asStateFlow()

    init {

    }

    fun getFileList(path: String) {
        filesInteractor.getFilesList(path = path)
            .onStart {
                _state.update { it.copy(isLoading = true) }
            }
            .onEach { result ->
                _state.update { it.copy(isLoading = false) }
                when (result) {
                    is BusinessResult.Error -> TODO()
                    is BusinessResult.Exception -> TODO()
                    is BusinessResult.Success -> {
                        _state.update { it.copy(files = result.data) }
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}