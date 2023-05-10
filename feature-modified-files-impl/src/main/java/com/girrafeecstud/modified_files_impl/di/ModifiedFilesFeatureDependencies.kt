package com.girrafeecstud.modified_files_impl.di

import android.content.Context
import com.girrafeecstud.file_list_api.data.IFilesDataSource

interface ModifiedFilesFeatureDependencies {

    fun getContext(): Context

    fun getFilesDataSource(): IFilesDataSource

}
