package com.girrafeecstud.modified_file_list.di

import android.content.Context
import com.girrafeecstud.modified_file_list.data.FileHashCalculator
import com.girrafeecstud.modified_file_list.data.FilesHashDataSource
import com.girrafeecstud.modified_file_list.data.IFilesHashDataSource
import com.girrafeecstud.modified_file_list.data.ModifiedFilesRepository
import com.girrafeecstud.modified_file_list.domain.IModifiedFilesRepository
import com.girrafeecstud.modified_file_list.domain.ModifiedFilesInteractor
import com.girrafeecstud.modified_file_list.engine.IModifiedFilesEngine
import com.girrafeecstud.modified_file_list.engine.ModifiedFilesEngine
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [ModifiedFilesFeatureModule.ModifiedFilesFeatureBindModule::class]
)
class ModifiedFilesFeatureModule {

    @ModifiedFilesFeature
    @Provides
    fun provideFileHashCalculator() =
        FileHashCalculator()

    @ModifiedFilesFeature
    @Provides
    fun provideModifiedFilesInteractor(repository: IModifiedFilesRepository) =
        ModifiedFilesInteractor(repository = repository)

    @Module
    interface ModifiedFilesFeatureBindModule {

        @ModifiedFilesFeature
        @Binds
        fun bindsIFilesHashDataSource(impl: FilesHashDataSource): IFilesHashDataSource

        @ModifiedFilesFeature
        @Binds
        fun bindModifiedFilesRepository(impl: ModifiedFilesRepository): IModifiedFilesRepository

        @ModifiedFilesFeature
        @Binds
        fun bindModifiedFilesEngine(impl: ModifiedFilesEngine): IModifiedFilesEngine
    }

}
