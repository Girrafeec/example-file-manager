/* Created by Girrafeec */

package com.girrafeecstud.example_file_manager.app

import android.app.Application
import com.girrafeecstud.example_file_manager.di.AppComponent

class FileManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponent.init()
    }
}