/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.di

import com.girrafeecstud.file_list_api.data.IFileListDataSource
import com.girrafeecstud.file_list_api.domain.IFileListInteractor
import com.girrafeecstud.file_list_api.domain.IFileListRepository
import com.girrafeecstud.file_list_impl.data.FileListRepository
import com.girrafeecstud.file_list_impl.data.LocalFileListDataSource
import com.girrafeecstud.file_list_impl.domain.FileListInteractor
import dagger.Binds
import dagger.Module

@Module(includes = [FileListFeatureModule.FileListFeatureBindModule::class])
class FileListFeatureModule {

    @Module
    interface FileListFeatureBindModule {

        @FileListFeature
        @Binds
        fun bindLocalFileListDataSource(impl: LocalFileListDataSource): IFileListDataSource

        @FileListFeature
        @Binds
        fun bindFileListRepository(impl: FileListRepository): IFileListRepository

        @FileListFeature
        @Binds
        fun bindFileListInteractor(impl: FileListInteractor): IFileListInteractor

    }

}