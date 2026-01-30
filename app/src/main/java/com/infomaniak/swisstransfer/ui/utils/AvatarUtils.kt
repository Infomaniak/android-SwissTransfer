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

import android.content.Context
import androidx.compose.ui.graphics.Color
import coil3.SingletonImageLoader
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.core.avatar.getBackgroundColorResBasedOnId
import com.infomaniak.core.avatar.models.AvatarColors
import com.infomaniak.core.avatar.models.AvatarType
import com.infomaniak.core.avatar.models.AvatarUrlData
import com.infomaniak.swisstransfer.R

object AvatarUtils {
    fun AvatarType.Companion.fromUser(user: User, context: Context): AvatarType.WithInitials = getUrlOrInitials(
        // TODO: See if we want to customize the image loader
        avatarUrlData = user.avatar?.let { AvatarUrlData(it, SingletonImageLoader.get(context)) },
        initials = user.getInitials(),
        colors = AvatarColors( // TODO: See if it works with custom dark mode local to the app
            containerColor = Color(context.getBackgroundColorResBasedOnId(user.id, R.array.AvatarColors)),
            contentColor = Color(context.getColor(R.color.avatarInitialsColor)),
        ),
    )
}
