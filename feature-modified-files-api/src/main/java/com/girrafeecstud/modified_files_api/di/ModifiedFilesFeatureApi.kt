/* Created by Girrafeec */

package com.girrafeecstud.modified_files_api.di

import com.girrafeecstud.modified_files_api.engine.IModifiedFilesEngine

interface ModifiedFilesFeatureApi {

    fun getModifiedFilesEngine(): IModifiedFilesEngine

}