package com.infomaniak.swisstransfer.ui.screen.newtransfer

import android.net.Uri
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewTransferOpenManager @Inject constructor() {
    // private val _sharedUris = MutableSharedFlow<List<Uri>?>(replay = 1)
    // val sharedUris: SharedFlow<List<Uri>?> = _sharedUris.asSharedFlow()
    //
    // suspend fun initializeSharedFilesUris(uris: List<Uri>?) {
    //     _sharedUris.emit(uris)
    // }

    sealed interface Reason {
        data class ExternalShareIncoming(val uris: List<Uri>) : Reason
        data object Other : Reason
    }

    suspend fun readOpenReason(): Reason = reasonChannel.receive()

    fun setOpenReason(reason: Reason) {
        reasonChannel.trySend(reason)
    }

    private val reasonChannel = Channel<Reason>(capacity = 1)
}
