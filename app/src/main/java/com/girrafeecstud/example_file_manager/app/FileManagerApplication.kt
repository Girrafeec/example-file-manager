/* Created by Girrafeec */

package com.girrafeecstud.example_file_manager.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.core_components.di.CoreComponentsComponent
import com.girrafeecstud.core_components.di.ICoreComponentsDependencies
import com.girrafeecstud.example_file_manager.di.AppComponent
import com.girrafeecstud.file_list_impl.di.DaggerFileListFeatureComponent_FileListFeatureDependenciesComponent
import com.girrafeecstud.file_list_impl.di.FileListFeatureComponent

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
    }

    private inner class  CoreComponentsDependencies
        :  ICoreComponentsDependencies {
        override val applicationContext: Context = this@FileManagerApplication
    }
}