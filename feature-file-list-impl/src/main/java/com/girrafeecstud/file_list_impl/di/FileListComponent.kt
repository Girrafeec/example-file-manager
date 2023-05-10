/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.di

import com.girrafeecstud.file_list_impl.di.annotation.FileListScope
import com.girrafeecstud.file_list_impl.di.module.FileListModule
import com.girrafeecstud.file_list_impl.ui.FileListFragment
import dagger.Subcomponent

@FileListScope
@Subcomponent(
    modules = [FileListModule::class]
)
interface FileListComponent {

    fun inject(fragment: FileListFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): FileListComponent

    }

}