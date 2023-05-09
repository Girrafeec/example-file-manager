/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.file_list_api.di.FileListFeatureApi
import com.girrafeecstud.modified_file_list.service.ModifiedFilesService
import dagger.Component

@ModifiedFilesFeature
@Component(
    modules = [ModifiedFilesFeatureModule::class],
    dependencies = [ModifiedFilesFeatureDependencies::class]
)
interface ModifiedFilesFeatureComponent : ModifiedFilesFeatureApi {

    fun inject(service: ModifiedFilesService)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ModifiedFilesFeatureDependencies): ModifiedFilesFeatureComponent.Builder

        fun build(): ModifiedFilesFeatureComponent

    }

    companion object {

        private var _modifiedFilesFeatureComponent: ModifiedFilesFeatureComponent? = null

        val modifiedFilesFeatureComponent get() = _modifiedFilesFeatureComponent!!

        fun init(dependencies: ModifiedFilesFeatureDependencies) {
            if (_modifiedFilesFeatureComponent == null)
                _modifiedFilesFeatureComponent = DaggerModifiedFilesFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _modifiedFilesFeatureComponent = null
        }

    }

    @ModifiedFilesFeature
    @Component(
        dependencies = [
            CoreComponentsApi::class,
            FileListFeatureApi::class
        ]
    )
    interface ModifiedFilesFeatureDependenciesComponent : ModifiedFilesFeatureDependencies
}