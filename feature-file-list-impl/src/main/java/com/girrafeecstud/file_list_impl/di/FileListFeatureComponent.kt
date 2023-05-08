/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.file_list_api.di.FileListFeatureApi
import com.girrafeecstud.file_list_impl.di.annotation.FileListFeature
import com.girrafeecstud.file_list_impl.di.module.FileListFeatureModule
import com.girrafeecstud.file_list_impl.ui.FileListFlowFragment
import dagger.Component

@FileListFeature
@Component(
    dependencies = [FileListFeatureDependencies::class],
    modules = [FileListFeatureModule::class]
)
interface FileListFeatureComponent : FileListFeatureApi {

    fun inject(fragment: FileListFlowFragment)

    fun fileListComponent(): FileListComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: FileListFeatureDependencies): FileListFeatureComponent.Builder

        fun build(): FileListFeatureComponent

    }

    companion object {

        private var _fileListFeatureComponent: FileListFeatureComponent? = null

        val fileListFeatureComponent: FileListFeatureComponent
            get() = _fileListFeatureComponent!!

        fun init(dependencies: FileListFeatureDependencies) {
            if (_fileListFeatureComponent == null)
                _fileListFeatureComponent = DaggerFileListFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _fileListFeatureComponent = null
        }

    }

    @FileListFeature
    @Component(
        dependencies = [
            CoreComponentsApi::class
        ]
    )
    interface FileListFeatureDependenciesComponent : FileListFeatureDependencies

}