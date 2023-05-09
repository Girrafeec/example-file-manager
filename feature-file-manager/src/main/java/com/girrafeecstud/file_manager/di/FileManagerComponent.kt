/* Created by Girrafeec */

package com.girrafeecstud.file_manager.di

import com.girrafeecstud.file_manager.di.annotation.FileManagerFeature
import com.girrafeecstud.file_manager.di.module.FileManagerFeatureModule
import com.girrafeecstud.file_manager.ui.FileManagerFlowFragment
import dagger.Component

@FileManagerFeature
@Component(
    modules = [FileManagerFeatureModule::class]
)
interface FileManagerComponent {

    fun inject(fragment: FileManagerFlowFragment)

    fun fileManagerDashboardComponent(): FileManagerDashboardComponent.Builder

    @Component.Builder
    interface Builder {

        fun build(): FileManagerComponent

    }

    companion object {

        private var _fileManagerComponent: FileManagerComponent? = null

        val fileManagerComponent: FileManagerComponent
            get() {
                if (_fileManagerComponent == null) {
                    init()
                }
                return _fileManagerComponent!!
            }

        fun init() {
            if (_fileManagerComponent == null)
                _fileManagerComponent = DaggerFileManagerComponent
                    .builder()
                    .build()
        }

        fun reset() {
            _fileManagerComponent = null
        }

    }

}