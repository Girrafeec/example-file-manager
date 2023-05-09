/* Created by Girrafeec */

package com.girrafeecstud.file_manager.presentation

import com.girrafeecstud.core_ui.presentation.BaseComponentViewModel
import com.girrafeecstud.file_manager.di.FileManagerComponent
import com.girrafeecstud.file_manager.di.FileManagerDashboardComponent

class FileManagerDashboardComponentViewModel : BaseComponentViewModel() {

    private var _fileManagerDashboardComponent: FileManagerDashboardComponent? = null

    val fileManagerDashboardComponent: FileManagerDashboardComponent
        get() {
            if (_fileManagerDashboardComponent == null)
                initComponent()
            return _fileManagerDashboardComponent!!
        }

    init {
        initComponent()
    }

    override fun onCleared() {
        destroyComponent()
    }

    override fun initComponent() {
        _fileManagerDashboardComponent = FileManagerComponent.fileManagerComponent.fileManagerDashboardComponent().build()
    }

    override fun destroyComponent() {
        _fileManagerDashboardComponent = null
    }
}