/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.di

import com.girrafeecstud.modified_files_impl.di.annotation.ModifiedFilesServiceScope
import com.girrafeecstud.modified_files_impl.service.ModifiedFilesService
import dagger.Subcomponent

@ModifiedFilesServiceScope
@Subcomponent
interface ModifiedFilesServiceComponent {

    fun inject(service: ModifiedFilesService)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ModifiedFilesServiceComponent

    }

}