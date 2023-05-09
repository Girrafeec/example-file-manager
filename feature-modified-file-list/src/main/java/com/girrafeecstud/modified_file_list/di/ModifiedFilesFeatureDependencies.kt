package com.girrafeecstud.modified_file_list.di

import android.content.Context
import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.file_list_api.di.annotation.LocalFilesDataSourceQualifer

interface ModifiedFilesFeatureDependencies {

    fun getContext(): Context

    fun getFilesDataSource(): IFilesDataSource

}
