/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2026 Infomaniak Network SA
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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.images.icons.Checkmark
import com.infomaniak.swisstransfer.ui.images.icons.Person
import com.infomaniak.swisstransfer.ui.images.icons.Settings
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

private val BOTTOM_SHEET_ITEM_HEIGHT = 56.dp

/**
 * A reusable bottom sheet row item built on top of [SharpRippleButton].
 *
 * It exposes two optional slots — [leading] and [trailing] — so it can adapt to any kind of bottom sheet entry:
 * a simple action, a selectable option, an account picker, etc.
 *
 * When [isSelected] is `true` and no [trailing] slot is provided, a default checkmark icon is displayed automatically.
 */
@Composable
fun BottomSheetItem(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    leading: (@Composable RowScope.() -> Unit)? = null,
    trailing: (@Composable RowScope.() -> Unit)? = null,
) {
    SharpRippleButton(
        modifier = modifier
            .height(BOTTOM_SHEET_ITEM_HEIGHT)
            .fillMaxWidth(),
        onClick = onClick,
        isSelected = { isSelected },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Margin.Large),
            horizontalArrangement = Arrangement.spacedBy(Margin.Medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leading?.invoke(this)
            content()
            if (trailing != null) {
                trailing(this)
            } else if (isSelected) {
                Icon(
                    modifier = Modifier.size(Dimens.SmallIconSize),
                    imageVector = AppIcons.Checkmark,
                    contentDescription = null,
                    tint = SwissTransferTheme.materialColors.primary,
                )
            }
        }
    }
}

@PreviewLightAndDark
@Composable
private fun BottomSheetItemPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                BottomSheetItem(
                    onClick = {},
                    leading = {
                        Icon(
                            modifier = Modifier.size(Dimens.IconSize),
                            imageVector = AppIcons.Add,
                            contentDescription = null,
                            tint = SwissTransferTheme.materialColors.primary,
                        )
                    },
                    content = {
                        Text(
                            text = stringResource(R.string.appName),
                            style = SwissTransferTheme.typography.bodyRegular,
                        )
                    },
                )

                BottomSheetItem(
                    onClick = {},
                    leading = {
                        Icon(
                            modifier = Modifier.size(Dimens.IconSize),
                            imageVector = AppIcons.Settings,
                            contentDescription = null,
                            tint = SwissTransferTheme.materialColors.primary,
                        )
                    },
                    content = {
                        Text(
                            text = stringResource(R.string.appName),
                            style = SwissTransferTheme.typography.bodyRegular,
                            modifier = Modifier.weight(1.0f),
                        )
                    },
                    trailing = {
                        Text(
                            text = stringResource(R.string.advancedSettingsTitle),
                            style = SwissTransferTheme.typography.bodySmallRegular,
                            color = SwissTransferTheme.colors.secondaryTextColor,
                        )
                    }
                )

                BottomSheetItem(
                    onClick = {},
                    isSelected = true,
                    leading = {
                        Icon(
                            modifier = Modifier.size(Dimens.IconSize),
                            imageVector = AppIcons.Person,
                            contentDescription = null,
                            tint = SwissTransferTheme.materialColors.primary,
                        )
                    },
                    content = {
                        Text(
                            text = stringResource(R.string.appName),
                            style = SwissTransferTheme.typography.bodyRegular,
                            modifier = Modifier.weight(1.0f),
                        )
                    },
                )
            }
        }
    }
}
