/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024 Infomaniak Network SA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.infomaniak.swisstransfer.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.os.BundleCompat
import androidx.lifecycle.lifecycleScope
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.SharedFilesManager
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewTransferActivity : ComponentActivity() {

    @Inject
    lateinit var sharedFilesManager: SharedFilesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        handleSharedFiles()
        setContent {
            SwissTransferTheme {
                NewTransferScreen(closeActivity = { finish() })
            }
        }
    }

    private fun handleSharedFiles() {
        val uris: List<Uri>? = when (intent?.action) {
            Intent.ACTION_SEND -> getSingleUri()
            Intent.ACTION_SEND_MULTIPLE -> getMultipleUris()
            else -> null
        }

        lifecycleScope.launch {
            sharedFilesManager.initializeSharedFilesUris(uris)
        }
    }

    private fun getSingleUri(): List<Uri> {
        val extras = intent.extras ?: return emptyList()
        val uri = BundleCompat.getParcelable(extras, Intent.EXTRA_STREAM, Uri::class.java)

        return if (uri == null) emptyList() else listOf(uri)
    }

    private fun getMultipleUris(): List<Uri> {
        val extras = intent.extras ?: return emptyList()
        val uris = BundleCompat.getParcelableArrayList(extras, Intent.EXTRA_STREAM, Uri::class.java)

        return uris ?: emptyList()
    }
}
