package com.girrafeecstud.file_manager.di

import com.girrafeecstud.modified_files_api.engine.IModifiedFilesEngine

interface FileManagerFeatureDependencies {

    fun getModifiedFilesEngine(): IModifiedFilesEngine

}
