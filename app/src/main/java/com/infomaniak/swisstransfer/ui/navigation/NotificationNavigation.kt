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
package com.infomaniak.swisstransfer.ui.navigation

const val NOTIFICATION_NAVIGATION_KEY = "notificationNavigationKey"
const val TRANSFER_UUID_KEY = "transferUuidKey"
const val TRANSFER_TYPE_KEY = "transferTypeKey"
const val TRANSFER_TOTAL_SIZE_KEY = "transferTotalSizeKey"
const val TRANSFER_URL_KEY = "transferUrlKey"

enum class NotificationNavigation { UploadProgress, UploadSuccess, UploadFailure }
