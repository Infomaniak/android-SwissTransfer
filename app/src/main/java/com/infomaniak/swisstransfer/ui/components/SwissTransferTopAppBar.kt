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
package com.infomaniak.swisstransfer.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownBar
import com.infomaniak.swisstransfer.ui.images.icons.ArrowLeft
import com.infomaniak.swisstransfer.ui.images.icons.Cross
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun SwissTransferTopAppBar(
    @StringRes titleRes: Int,
    navigationMenu: TopAppBarButton? = null,
    vararg actionMenus: TopAppBarButton,
) {
    SwissTransferTopAppBar(title = stringResource(titleRes), navigationMenu, *actionMenus)
}

@Composable
fun SwissTransferTopAppBar(
    title: String? = null,
    @StringRes titleRes: Int,
    navigationMenu: TopAppBarButton? = null,
    vararg actionMenus: TopAppBarButton,
) {
    if (title != null) {
        SwissTransferTopAppBar(title = title, navigationMenu = navigationMenu, actionMenus = actionMenus)
    } else {
        SwissTransferTopAppBar(titleRes = titleRes, navigationMenu = navigationMenu, actionMenus = actionMenus)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SwissTransferTopAppBar(
    title: String,
    navigationMenu: TopAppBarButton? = null,
    vararg actionMenus: TopAppBarButton,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SwissTransferTheme.materialColors.tertiary,
            titleContentColor = SwissTransferTheme.colors.toolbarTextColor,
            actionIconContentColor = SwissTransferTheme.colors.toolbarIconColor,
            navigationIconContentColor = SwissTransferTheme.colors.toolbarIconColor,
        ),
        title = { Text(text = title, style = SwissTransferTheme.typography.h2) },
        navigationIcon = { navigationMenu?.let { MenuButton(navigationMenu) } },
        actions = { actionMenus.forEach { actionMenu -> MenuButton(actionMenu) } },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SwissTransferTopAppBar(
    title: String,
    navigationMenu: TopAppBarButton? = null,
    vararg actionMenus: TopAppBarButton,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SwissTransferTheme.materialColors.tertiary,
            titleContentColor = SwissTransferTheme.colors.toolbarTextColor,
            actionIconContentColor = SwissTransferTheme.colors.toolbarIconColor,
            navigationIconContentColor = SwissTransferTheme.colors.toolbarIconColor,
        ),
        title = { Text(text = title, style = SwissTransferTheme.typography.h2) },
        navigationIcon = { navigationMenu?.let { MenuButton(navigationMenu) } },
        actions = { actionMenus.forEach { actionMenu -> MenuButton(actionMenu) } },
    )
}

@Composable
private fun MenuButton(navigationMenu: TopAppBarButton) {
    IconButton(onClick = navigationMenu.onClick) {
        Icon(imageVector = navigationMenu.icon, contentDescription = stringResource(navigationMenu.contentDescription))
    }
}

@Immutable
data class TopAppBarButton(
    val icon: ImageVector,
    @StringRes val contentDescription: Int,
    val onClick: () -> Unit,
) {
    companion object {
        val backButton: (onClick: () -> Unit) -> TopAppBarButton = {
            TopAppBarButton(AppIcons.ArrowLeft, R.string.contentDescriptionButtonBack, it)
        }
        val closeButton: (onClick: () -> Unit) -> TopAppBarButton = {
            TopAppBarButton(AppIcons.Cross, R.string.contentDescriptionButtonClose, it)
        }
        val downloadButton: (onClick: () -> Unit) -> TopAppBarButton = {
            TopAppBarButton(AppIcons.ArrowDownBar, R.string.buttonDownload, it)
        }
    }
}

@PreviewLightAndDark
@Composable
private fun SwissTransferTopAppBarPreview() {
    SwissTransferTheme {
        SwissTransferTopAppBar(
            titleRes = R.string.appName,
            navigationMenu = TopAppBarButton.backButton {},
            TopAppBarButton(AppIcons.Add, R.string.appName) {},
            TopAppBarButton.closeButton {},
            TopAppBarButton.downloadButton {},
        )
    }
}
