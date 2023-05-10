package com.girrafeecstud.modified_files_impl.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
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

}
