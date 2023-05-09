/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.di

import com.girrafeecstud.modified_file_list.engine.IModifiedFilesEngine

interface ModifiedFilesFeatureApi {

    fun getModifiedFilesEngine(): IModifiedFilesEngine

}