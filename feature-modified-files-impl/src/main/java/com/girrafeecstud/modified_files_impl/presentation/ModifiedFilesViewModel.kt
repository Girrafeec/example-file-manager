package com.girrafeecstud.modified_files_impl.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_base.base.sortByName
import com.girrafeecstud.core_base.base.sortByNumberDown
import com.girrafeecstud.core_base.base.sortByNumberUp
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.file_list_api.domain.FileSortType
import com.girrafeecstud.modified_files_api.engine.IModifiedFilesEngine
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ModifiedFilesViewModel @Inject constructor(
    private val engine: IModifiedFilesEngine
) : BaseViewModel<ModifiedFilesUiState>() {

    override var _state: MutableStateFlow<ModifiedFilesUiState> = MutableStateFlow(
        ModifiedFilesUiState()
    )
    override val state: StateFlow<ModifiedFilesUiState> = _state.asStateFlow()

    var fileSortType = FileSortType.BY_NAME

    init {
        engine.getEngineState()
            .onEach { engineState ->
                if (engineState.isLoading)
                    _state.update { it.copy(isLoading = true) }
                else
                    _state.update { it.copy(isLoading = false) }

                _state.update { it.copy(files = engineState.modifiedFiles) }
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
