/* Created by Girrafeec */

package com.girrafeecstud.file_manager.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.file_manager.di.annotation.FileManagerFeature
import com.girrafeecstud.file_manager.di.module.FileManagerFeatureModule
import com.girrafeecstud.file_manager.ui.FileManagerFlowFragment
import com.girrafeecstud.modified_files_api.di.ModifiedFilesFeatureApi
import dagger.Component

@FileManagerFeature
@Component(
    modules = [FileManagerFeatureModule::class],
    dependencies = [FileManagerFeatureDependencies::class]
)
interface FileManagerFeatureComponent {

    fun inject(fragment: FileManagerFlowFragment)

    fun fileManagerDashboardComponent(): FileManagerDashboardComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: FileManagerFeatureDependencies): Builder

        fun build(): FileManagerFeatureComponent

    }

    companion object {

        private var _fileManagerFeatureComponent: FileManagerFeatureComponent? = null

        val fileManagerFeatureComponent get() = _fileManagerFeatureComponent!!

        fun init(dependencies: FileManagerFeatureDependencies) {
            if (_fileManagerFeatureComponent == null)
                _fileManagerFeatureComponent = DaggerFileManagerFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _fileManagerFeatureComponent = null
        }

    }

    @FileManagerFeature
    @Component(
        dependencies = [
            CoreComponentsApi::class,
            ModifiedFilesFeatureApi::class
        ]
    )
    interface FileManagerFeatureDependenciesComponent : FileManagerFeatureDependencies

}