/* Created by Girrafeec */

package com.girrafeecstud.example_file_manager.di

import com.girrafeecstud.example_file_manager.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

    }

    companion object {

        private var _appComponent: AppComponent? = null

        val appComponent: AppComponent
            get() {
                if (_appComponent == null) {
                    init()
                }
                return _appComponent!!
            }

        fun init() {
            if (_appComponent == null)
                _appComponent = DaggerAppComponent
                    .builder()
                    .build()
        }

        fun reset() {
            _appComponent = null
        }

    }

}