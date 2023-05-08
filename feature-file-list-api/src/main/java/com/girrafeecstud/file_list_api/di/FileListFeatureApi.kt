/* Created by Girrafeec */

package com.girrafeecstud.file_list_api.di

import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.file_list_api.di.annotation.LocalFilesDataSourceQualifer
import com.girrafeecstud.file_list_api.domain.IFilesInteractor
import com.girrafeecstud.file_list_api.domain.IFilesRepository
import javax.inject.Named

interface FileListFeatureApi {

    fun getFilesDataSource(): IFilesDataSource

    fun getFilesRepository(): IFilesRepository

    fun getFilesInteractor(): IFilesInteractor

}