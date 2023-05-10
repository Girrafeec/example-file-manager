/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.engine

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import com.girrafeecstud.modified_files_impl.service.ModifiedFilesService
import com.girrafeecstud.modified_files_api.engine.IModifiedFilesEngine
import com.girrafeecstud.modified_files_api.engine.ModifiedFilesEngineState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ModifiedFilesEngine @Inject constructor(
    private val context: Context
) : IModifiedFilesEngine {

    override fun startEngine() {
        if (!isModifiedFilesServiceRunning()) {
            val intent = Intent(context, ModifiedFilesService::class.java)
            context.startService(intent)
        }
    }

    override fun getEngineState(): Flow<ModifiedFilesEngineState> =
        ModifiedFilesService.state

    private fun isModifiedFilesServiceRunning(): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == ModifiedFilesService::class.java.name
        }
    }

}