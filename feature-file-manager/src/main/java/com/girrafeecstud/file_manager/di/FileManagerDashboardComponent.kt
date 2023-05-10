/* Created by Girrafeec */

package com.girrafeecstud.file_manager.di

import com.girrafeecstud.file_manager.di.annotation.FileManagerDashboardScope
import com.girrafeecstud.file_manager.ui.FileManagerDashboardFragment
import dagger.Subcomponent

@FileManagerDashboardScope
@Subcomponent
interface FileManagerDashboardComponent {

    fun inject(fragment: FileManagerDashboardFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): FileManagerDashboardComponent

    }

}