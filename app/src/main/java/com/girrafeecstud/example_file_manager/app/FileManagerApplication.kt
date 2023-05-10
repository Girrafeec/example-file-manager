/* Created by Girrafeec */

package com.girrafeecstud.example_file_manager.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.core_components.di.CoreComponentsComponent
import com.girrafeecstud.core_components.di.ICoreComponentsDependencies
import com.girrafeecstud.example_file_manager.di.AppComponent
import com.girrafeecstud.file_list_impl.di.DaggerFileListFeatureComponent_FileListFeatureDependenciesComponent
import com.girrafeecstud.file_list_impl.di.FileListFeatureComponent
import com.girrafeecstud.file_manager.di.FileManagerFeatureComponent
import com.girrafeecstud.modified_files_impl.di.DaggerModifiedFilesFeatureComponent_ModifiedFilesFeatureDependenciesComponent
import com.girrafeecstud.modified_files_impl.di.ModifiedFilesFeatureComponent
import com.girrafeecstud.file_manager.di.DaggerFileManagerFeatureComponent_FileManagerFeatureDependenciesComponent

class FileManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponent.init()

        CoreComponentsComponent.init(dependencies = CoreComponentsDependencies())

        FileListFeatureComponent.init(dependencies = DaggerFileListFeatureComponent_FileListFeatureDependenciesComponent
            .builder()
            .coreComponentsApi(CoreComponentsComponent.coreComponentsComponent)
            .build()
        )

        ModifiedFilesFeatureComponent.init(dependencies = DaggerModifiedFilesFeatureComponent_ModifiedFilesFeatureDependenciesComponent
            .builder()
            .coreComponentsApi(CoreComponentsComponent.coreComponentsComponent)
            .fileListFeatureApi(FileListFeatureComponent.fileListFeatureComponent)
            .build()
        )

        FileManagerFeatureComponent.init(dependencies = DaggerFileManagerFeatureComponent_FileManagerFeatureDependenciesComponent
            .builder()
            .coreComponentsApi(CoreComponentsComponent.coreComponentsComponent)
            .modifiedFilesFeatureApi(ModifiedFilesFeatureComponent.modifiedFilesFeatureComponent)
            .build()
        )
    }

    private inner class  CoreComponentsDependencies
        :  ICoreComponentsDependencies {
        override val applicationContext: Context = this@FileManagerApplication
    }
}