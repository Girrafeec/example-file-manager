/* Created by Girrafeec */

package com.girrafeecstud.modified_files_api.engine

import kotlinx.coroutines.flow.Flow

interface IModifiedFilesEngine {

    fun startEngine()

    fun getEngineState(): Flow<ModifiedFilesEngineState>

}