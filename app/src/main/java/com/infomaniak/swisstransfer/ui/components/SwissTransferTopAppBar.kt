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
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.*
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun SwissTransferTopAppBar(
    @StringRes titleRes: Int,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    SwissTransferTopAppBar(title = stringResource(titleRes), navigationIcon, actions)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SwissTransferTopAppBar(
    title: String = "",
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SwissTransferTheme.materialColors.tertiary,
            titleContentColor = SwissTransferTheme.colors.toolbarTextColor,
            actionIconContentColor = SwissTransferTheme.colors.toolbarIconColor,
            navigationIconContentColor = SwissTransferTheme.colors.toolbarIconColor,
        ),
        title = { Text(text = title, style = SwissTransferTheme.typography.h2) },
        navigationIcon = navigationIcon,
        actions = actions,
    )
}

@Composable
fun TopAppBarButton(
    icon: ImageVector,
    @StringRes contentDescResId: Int,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    IconButton(onClick = onClick, enabled = enabled) {
        Icon(imageVector = icon, contentDescription = stringResource(contentDescResId))
    }
}

object TopAppBarButtons {
    @Composable
    fun Back(onClick: () -> Unit) = TopAppBarButton(
        icon = AppIcons.ArrowLeft,
        contentDescResId = R.string.contentDescriptionButtonBack,
        onClick = onClick,
    )

    @Composable
    fun Close(onClick: () -> Unit) = TopAppBarButton(
        icon = AppIcons.Cross,
        contentDescResId = R.string.contentDescriptionButtonClose,
        onClick = onClick,
    )

    @Composable
    fun QrCode(onClick: () -> Unit) = TopAppBarButton(
        icon = AppIcons.QrCode,
        contentDescResId = R.string.transferTypeQrCode,
        onClick = onClick,
    )

    @Composable
    fun Download(
        onClick: () -> Unit,
        enabled: Boolean = true,
    ) = TopAppBarButton(
        icon = AppIcons.ArrowDownBar,
        enabled = enabled,
        contentDescResId = R.string.buttonDownload,
        onClick = onClick,
    )
}

@PreviewLightAndDark
@Composable
private fun SwissTransferTopAppBarPreview() {
    SwissTransferTheme {
        SwissTransferTopAppBar(
            title = stringResource(R.string.appName),
            navigationIcon = { TopAppBarButtons.Back {} },
            actions = {
                TopAppBarButton(AppIcons.Add, R.string.appName, onClick = {})
                TopAppBarButtons.Close {}
                TopAppBarButtons.QrCode {}
                TopAppBarButtons.Download(onClick = {})
            }
        )
    }
}
