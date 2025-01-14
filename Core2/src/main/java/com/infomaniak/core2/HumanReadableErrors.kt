/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.core2

import com.infomaniak.core2.DownloadStatus.Failed.LocalIssue
import com.infomaniak.core2.DownloadStatus.Failed.RemoteIssue

fun DownloadStatus.Failed.Reason.snackbarMsgResId(): Int = when (this) {
    is RemoteIssue -> when (this) {
        RemoteIssue.HttpDataError -> R.string.errorDownload //TODO: Provide a more precise error message.
        is RemoteIssue.HttpError -> R.string.errorDownload //TODO: Provide a more precise error message.
        RemoteIssue.TooManyRedirects -> R.string.errorDownload //TODO: Provide a more precise error message.
        RemoteIssue.UnhandledHttpCode -> R.string.errorDownload //TODO: Provide a more precise error message.
    }
    is LocalIssue -> when (this) {
        LocalIssue.InsufficientSpace -> R.string.errorDownloadInsufficientSpace
        LocalIssue.StorageDeviceNotFound -> R.string.errorDownload //TODO: Provide a more precise error message.
        LocalIssue.FileError -> R.string.errorDownload //TODO: Provide a more precise error message.
        LocalIssue.FileAlreadyExists -> R.string.errorDownload //TODO: Provide a more precise error message.
    }
    DownloadStatus.Failed.Reason.CannotResume -> {
        R.string.errorDownload //TODO: Provide a more precise error message.
    }
    DownloadStatus.Failed.Reason.UnknownError -> {
        R.string.errorDownload //TODO: Provide a more precise error message.
    }
}
