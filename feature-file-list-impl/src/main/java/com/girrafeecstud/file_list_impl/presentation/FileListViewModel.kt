/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_base.base.sortByName
import com.girrafeecstud.core_base.base.sortByNumberDown
import com.girrafeecstud.core_base.base.sortByNumberUp
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.file_list_api.domain.FileSortType
import com.girrafeecstud.file_list_impl.domain.FilesInteractor
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FileListViewModel @Inject constructor(
    private val filesInteractor: FilesInteractor
)
    : BaseViewModel<FileListUiState>() {

    override var _state: MutableStateFlow<FileListUiState> = MutableStateFlow(FileListUiState())
    override val state: StateFlow<FileListUiState> = _state.asStateFlow()

    var fileSortType = FileSortType.BY_NAME

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

    fun sortFiles(sortType: FileSortType) {
        this.fileSortType = sortType
        val sortedFiles = when (sortType) {
            FileSortType.BY_NAME ->
                 _state.value.files?.sortByName { it.name }
            FileSortType.BY_SIZE_UP ->
                _state.value.files?.sortByNumberUp { it.size }
            FileSortType.BY_SIZE_DOWN ->
                _state.value.files?.sortByNumberDown { it.size }
            FileSortType.BY_EXTENSION ->
                _state.value.files?.sortByName { it.fileType.extension }
        }
        _state.update { it.copy(files = sortedFiles) }
    }
}