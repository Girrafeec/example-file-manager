/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.presentation

import com.girrafeecstud.core_ui.presentation.BaseComponentViewModel
import com.girrafeecstud.modified_files_impl.di.ModifiedFilesComponent
import com.girrafeecstud.modified_files_impl.di.ModifiedFilesFeatureComponent

class ModifiedFilesComponentViewModel : BaseComponentViewModel() {

    private var _modifiedFilesComponent: ModifiedFilesComponent? = null

    val modifiedFilesComponent: ModifiedFilesComponent
        get() {
            if (_modifiedFilesComponent == null)
                initComponent()
            return  _modifiedFilesComponent!!
        }

    init {
        initComponent()
    }

    override fun initComponent() {
        _modifiedFilesComponent = ModifiedFilesFeatureComponent.modifiedFilesFeatureComponent.fileListComponent().build()
    }

    override fun destroyComponent() {
        _modifiedFilesComponent = null
    }

    override fun onCleared() {
        destroyComponent()
    }
}