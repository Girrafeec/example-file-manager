/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.presentation

import androidx.lifecycle.ViewModel
import com.girrafeecstud.core_ui.presentation.BaseComponentViewModel
import com.girrafeecstud.file_list_impl.di.FileListComponent
import com.girrafeecstud.file_list_impl.di.FileListFeatureComponent

class FileListComponentViewModel : BaseComponentViewModel() {

    private var _filesListComponent: FileListComponent? = null

    val filesListComponent: FileListComponent
        get() {
            if (_filesListComponent == null) {
                initComponent()
            }
            return _filesListComponent!!
        }

    init {
        initComponent()
    }

    override fun onCleared() {
        destroyComponent()
    }

    override fun initComponent() {
        _filesListComponent = FileListFeatureComponent.fileListFeatureComponent
            .fileListComponent()
            .build()
    }

    override fun destroyComponent() {
        _filesListComponent = null
    }

}