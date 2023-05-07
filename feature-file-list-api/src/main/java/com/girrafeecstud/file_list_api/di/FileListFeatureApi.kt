/* Created by Girrafeec */

package com.girrafeecstud.file_list_api.di

import com.girrafeecstud.file_list_api.data.IFileListDataSource
import com.girrafeecstud.file_list_api.domain.IFileListInteractor
import com.girrafeecstud.file_list_api.domain.IFileListRepository

interface FileListFeatureApi {

    fun getFileListDataSource(): IFileListDataSource

    fun getFileListRepository(): IFileListRepository

    fun getFileListInteractor(): IFileListInteractor

}