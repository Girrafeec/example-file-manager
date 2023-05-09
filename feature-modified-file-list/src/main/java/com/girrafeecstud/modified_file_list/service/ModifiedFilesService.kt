/* Created by Girrafeec */

package com.girrafeecstud.modified_file_list.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.girrafeecstud.modified_file_list.di.ModifiedFilesFeatureComponent
import com.girrafeecstud.modified_file_list.engine.ModifiedFilesEngineState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ModifiedFilesService : Service() {

    companion object {
        private var _state = MutableStateFlow<ModifiedFilesEngineState>(
            ModifiedFilesEngineState()
        )

        val state = _state.asStateFlow()
    }

    private val binder = FileChangeDetectionServiceBinder()

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        ModifiedFilesFeatureComponent.modifiedFilesFeatureComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }



    inner class FileChangeDetectionServiceBinder() : Binder() {
        fun getService() = this@ModifiedFilesService
    }
}