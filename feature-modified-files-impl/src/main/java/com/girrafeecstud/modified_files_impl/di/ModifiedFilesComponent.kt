/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.di

import com.girrafeecstud.modified_files_impl.di.annotation.ModifiedFilesScope
import com.girrafeecstud.modified_files_impl.di.module.ModifiedFilesModule
import com.girrafeecstud.modified_files_impl.ui.ModifiedFilesFragment
import dagger.Subcomponent

@ModifiedFilesScope
@Subcomponent(
    modules = [ModifiedFilesModule::class]
)
interface ModifiedFilesComponent {

    fun inject(fragment: ModifiedFilesFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ModifiedFilesComponent

    }

}