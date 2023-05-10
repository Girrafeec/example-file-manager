/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.modified_files_impl.util.ModifiedFileFeatureUtils
import com.girrafeecstud.modified_files_impl.di.ModifiedFilesFeatureComponent
import com.girrafeecstud.modified_files_impl.domain.ModifiedFilesInteractor
import com.girrafeecstud.modified_files_api.engine.ModifiedFilesEngineState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ModifiedFilesService : Service() {

    companion object {
        private var _state = MutableStateFlow(
            ModifiedFilesEngineState()
        )

        val state = _state.asStateFlow()
    }

    private val binder = FileChangeDetectionServiceBinder()

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var modifiedFilesInteractor: ModifiedFilesInteractor

    override fun onCreate() {
        super.onCreate()
        ModifiedFilesFeatureComponent.modifiedFilesFeatureComponent
            .serviceComponent().build().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        getModifiedFileList()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private fun getModifiedFileList() {
        modifiedFilesInteractor.getModifiedFiles(ModifiedFileFeatureUtils.DEFAULT_PATH)
            .onStart {
                _state.update { it.copy(isLoading = true) }
            }
            .onEach { result ->
                _state.update { it.copy(isLoading = false) }
                when (result) {
                    is BusinessResult.Success ->
                        _state.update { it.copy(modifiedFiles = result.data) }
                    is BusinessResult.Error -> {}
                    is BusinessResult.Exception -> {}
                }
                Log.i("modified files", "files ${_state.value.modifiedFiles}")
                stopSelf()
            }
            .launchIn(serviceScope)
    }

    inner class FileChangeDetectionServiceBinder() : Binder() {
        fun getService() = this@ModifiedFilesService
    }
}