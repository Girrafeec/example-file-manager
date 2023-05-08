/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.di.module

import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.file_list_api.di.annotation.LocalFilesDataSourceQualifer
import com.girrafeecstud.file_list_api.domain.IFilesInteractor
import com.girrafeecstud.file_list_api.domain.IFilesRepository
import com.girrafeecstud.file_list_impl.data.FilesRepository
import com.girrafeecstud.file_list_impl.data.LocalFilesDataSource
import com.girrafeecstud.file_list_impl.di.FileListComponent
import com.girrafeecstud.file_list_impl.di.annotation.FileListFeature
import com.girrafeecstud.file_list_impl.domain.FilesInteractor
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module(
    includes = [FileListFeatureModule.FileListFeatureBindModule::class],
    subcomponents = [FileListComponent::class]
)
class FileListFeatureModule {

    @Module
    interface FileListFeatureBindModule {

        @FileListFeature
        @Binds
        fun bindLocalFilesDataSource(impl: LocalFilesDataSource): IFilesDataSource

        @FileListFeature
        @Binds
        fun bindFilesRepository(impl: FilesRepository): IFilesRepository

        @FileListFeature
        @Binds
        fun bindFilesInteractor(impl: FilesInteractor): IFilesInteractor

    }

}