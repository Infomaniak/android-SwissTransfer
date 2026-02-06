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
package com.infomaniak.swisstransfer.ui.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.core.avatar.components.Avatar
import com.infomaniak.core.avatar.models.AvatarType
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownCircle
import com.infomaniak.swisstransfer.ui.images.icons.ArrowUpCircle
import com.infomaniak.swisstransfer.ui.images.icons.Person
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.MyAccountDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.ReceivedDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.SentDestination
import com.infomaniak.core.common.R as RCore

/**
 * Enum class representing the different destinations in the app's [BottomAppBar] or [NavigationRail].
 *
 * @property label The string label for the destination.
 * @property icon The icon to be displayed for the destination.
 */
enum class NavigationItem(
    val label: @Composable () -> String,
    val icon: @Composable (User?, contentDescription: String?) -> Unit,
    val destination: MainNavigation,
) {
    Sent(
        label = { stringResource(R.string.sentTitle) },
        icon = { _, contentDescription -> Icon(AppIcons.ArrowUpCircle, contentDescription) },
        destination = SentDestination()
    ),
    Received(
        label = { stringResource(R.string.receivedTitle) },
        icon = { _, contentDescription -> Icon(AppIcons.ArrowDownCircle, contentDescription) },
        destination = ReceivedDestination()
    ),
    MyAccount(
        label = { pluralStringResource(RCore.plurals.myAccount, 1) },
        icon = { user, contentDescription ->
            Crossfade(user) { user ->
                if (user == null) {
                    Icon(AppIcons.Person, contentDescription)
                } else {
                    Avatar(AvatarType.fromUser(user), modifier = Modifier.size(24.dp))
                }
            }
        },
        destination = MyAccountDestination
    ),
}
