package com.girrafeecstud.modified_files_impl.di.module

import android.content.Context
import androidx.room.Room
import com.girrafeecstud.modified_files_impl.data.FileHashCalculator
import com.girrafeecstud.modified_files_impl.data.FilesHashDataSource
import com.girrafeecstud.modified_files_impl.data.IFilesHashDataSource
import com.girrafeecstud.modified_files_impl.data.ModifiedFilesRepository
import com.girrafeecstud.modified_files_impl.data.database.ModifiedFilesDatabase
import com.girrafeecstud.modified_files_impl.di.ModifiedFilesComponent
import com.girrafeecstud.modified_files_impl.di.annotation.ModifiedFilesFeature
import com.girrafeecstud.modified_files_impl.domain.IModifiedFilesRepository
import com.girrafeecstud.modified_files_impl.domain.ModifiedFilesInteractor
import com.girrafeecstud.modified_files_api.engine.IModifiedFilesEngine
import com.girrafeecstud.modified_files_impl.engine.ModifiedFilesEngine
import com.girrafeecstud.modified_files_api.domain.IModifiedFilesInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [ModifiedFilesFeatureModule.ModifiedFilesFeatureBindModule::class],
    subcomponents = [ModifiedFilesComponent::class]
)
class ModifiedFilesFeatureModule {

    @ModifiedFilesFeature
    @Provides
    fun providesModifiedFilesDatabase(context: Context): ModifiedFilesDatabase =
        Room.databaseBuilder(
            context,
            ModifiedFilesDatabase::class.java,
            "modified_files",
        ).allowMainThreadQueries().build()

    @ModifiedFilesFeature
    @Provides
    fun provideFileHashCalculator() =
        FileHashCalculator()

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
        fun bindModifiedFilesInteractor(impl: ModifiedFilesInteractor): IModifiedFilesInteractor

        @ModifiedFilesFeature
        @Binds
        fun bindModifiedFilesEngine(impl: ModifiedFilesEngine): IModifiedFilesEngine
    }

}
