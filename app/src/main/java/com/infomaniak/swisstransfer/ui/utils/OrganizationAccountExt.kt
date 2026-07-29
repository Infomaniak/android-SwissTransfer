/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil3.SingletonImageLoader
import com.infomaniak.core.avatar.LocalAvatarColors
import com.infomaniak.core.avatar.getBackgroundColorResBasedOnId
import com.infomaniak.core.avatar.models.AvatarColors
import com.infomaniak.core.avatar.models.AvatarType
import com.infomaniak.core.avatar.models.AvatarUrlData
import com.infomaniak.core.ksuite.myksuite.ui.components.MyKSuiteTier
import com.infomaniak.multiplatform_swisstransfer.database.models.OrganizationAccount

private const val MAX_INITIALS_COUNT = 2
private const val MY_KSUITE_TYPE = "my_ksuite"
private const val MY_KSUITE_PLUS_PACK = "my_ksuite_plus"

val OrganizationAccount.myKSuiteTier: MyKSuiteTier?
    get() = when {
        type != MY_KSUITE_TYPE -> null
        pack == MY_KSUITE_PLUS_PACK -> MyKSuiteTier.Plus
        else -> MyKSuiteTier.Free
    }

@Composable
fun OrganizationAccount.avatarType(): AvatarType.WithInitials {
    val context = LocalContext.current
    val avatarColors = LocalAvatarColors.current

    return AvatarType.getUrlOrInitials(
        avatarUrlData = logoUrl?.takeIf { it.isNotBlank() }?.let { AvatarUrlData(it, SingletonImageLoader.get(context)) },
        initials = name.computeInitials(),
        colors = AvatarColors(
            containerColor = getBackgroundColorResBasedOnId(id.toInt(), avatarColors.containerColors),
            contentColor = avatarColors.contentColor,
        ),
    )
}

private fun String.computeInitials(): String = split(' ')
    .filter { it.isNotBlank() }
    .take(MAX_INITIALS_COUNT)
    .joinToString(separator = "") { it.first().uppercase() }
