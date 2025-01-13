package com.infomaniak.swisstransfer.ui.screen.newtransfer

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedFilesManager @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {
    private val _sharedUris = MutableSharedFlow<List<Uri>?>(1)
    val sharedUris: SharedFlow<List<Uri>?> = _sharedUris.asSharedFlow()

    suspend fun initializeSharedFilesUris(uris: List<Uri>?) {
        _sharedUris.emit(uris)
    }
}
