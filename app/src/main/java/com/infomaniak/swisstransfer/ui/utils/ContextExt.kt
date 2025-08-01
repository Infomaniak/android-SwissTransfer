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
package com.infomaniak.swisstransfer.ui.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.infomaniak.core.filetypes.FileType
import com.infomaniak.swisstransfer.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import splitties.toast.UnreliableToastApi
import splitties.toast.toast
import kotlin.reflect.KClass

fun <T : Activity> Context.launchActivity(kClass: KClass<T>, options: Bundle? = null) {
    startActivity(Intent(this, kClass.java), options)
}

fun Context.openUrl(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun Context.openAppNotificationSettings() {
    Intent().apply {
        action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
    }.also(::startActivity)
}

/**
 * This method opens a chooser to let the user open an email app as if he clicked on it on his home screen, with an ACTION_MAIN.
 *
 * Create a chooser with two parts:
 * 1) The mainActionIntent which lists email apps with a single intent
 * 2) A list of additional intents `extraEmailAppIntents` added using [Intent.EXTRA_INITIAL_INTENTS]
 *
 * When creating a chooser after Android 10 a maximum of 3 intents can be provided to the chooser. Relying on 1) lets us display
 * more than 3 apps but not many email apps support it. To counter this, we add two more intents from the list of all available
 * email apps using [Intent.EXTRA_INITIAL_INTENTS].
 */
fun Context.openMailApp() {
    // Returns the list of unique package names from the list of ResolveInfo that support the input Intent
    fun queryIntentSupportedPackageNames(intent: Intent): Set<String> = buildSet {
        packageManager
            .queryIntentActivities(intent, 0)
            .forEach { add(it.activityInfo.packageName) }
    }

    val mainActionIntent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_EMAIL).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    val mainPackageNames = queryIntentSupportedPackageNames(mainActionIntent)

    val extraEmailAppIntents = queryIntentSupportedPackageNames(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:")))
        .filter { it !in mainPackageNames }
        .mapNotNull { packageName ->
            packageManager.getLaunchIntentForPackage(packageName)
        }

    val chooserIntent = Intent.createChooser(mainActionIntent, getString(R.string.buttonOpenMailApp)).apply {
        putExtra(Intent.EXTRA_INITIAL_INTENTS, extraEmailAppIntents.toTypedArray())
    }
    safeStartActivity(chooserIntent)
}

fun Context.copyText(text: String, showSnackbar: (String) -> Unit) {
    val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboardManager.setPrimaryClip(ClipData.newPlainText(text, text))

    if (SDK_INT < 33) showSnackbar(getString(R.string.snackbarLinkCopiedToClipboard))
}

fun Context.shareText(text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    safeStartActivity(Intent.createChooser(intent, null))
}

suspend fun Context.openFile(uri: Uri) {
    val intent = Intent(Intent.ACTION_VIEW).also {
        val type = Dispatchers.IO { contentResolver.getType(uri) } ?: uri.lastPathSegment?.let { name ->
            FileType.guessMimeTypeFromFileName(name)
        }
        it.setDataAndType(uri, type)
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        @OptIn(UnreliableToastApi::class)
        toast(msgResId = R.string.startActivityCantHandleAction)
        // TODO: Offer the share action as a fallback.
    }
}

fun Context.safeStartActivity(intent: Intent) {
    runCatching {
        startActivity(intent)
    }.onFailure {
        showToast(R.string.startActivityCantHandleAction)
    }
}

fun Context.showToast(title: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, title, duration).show()
}
