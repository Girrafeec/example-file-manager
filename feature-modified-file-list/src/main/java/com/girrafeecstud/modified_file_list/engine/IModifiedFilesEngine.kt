/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.engine

import kotlinx.coroutines.flow.Flow

interface IModifiedFilesEngine {

    fun startEngine()

    fun getEngineState(): Flow<ModifiedFilesEngineState>

}