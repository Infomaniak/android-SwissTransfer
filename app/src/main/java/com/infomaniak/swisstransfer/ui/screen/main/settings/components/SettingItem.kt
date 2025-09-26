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
package com.infomaniak.swisstransfer.ui.screen.main.settings.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SharpRippleButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.images.icons.ArrowOpenOutside
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightThick
import com.infomaniak.swisstransfer.ui.images.icons.Folder
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

private val ITEM_MIN_HEIGHT = 56.dp

@Composable
fun SettingItem(
    @StringRes titleRes: Int,
    isSelected: () -> Boolean,
    icon: ImageVector? = null,
    description: String? = null,
    endIcon: EndIconType? = null,
    shouldTintIcon: Boolean = true,
    onClick: (() -> Unit)?,
) {
    val modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = ITEM_MIN_HEIGHT)

    onClick?.let {
        SharpRippleButton(
            modifier = modifier,
            isSelected = isSelected,
            onClick = it,
        ) {
            SettingItemContent(icon, titleRes, description, endIcon, shouldTintIcon)
        }
    } ?: run {
        Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
            SettingItemContent(icon, titleRes, description, endIcon, shouldTintIcon)
        }
    }
}

@Composable
private fun SettingItemContent(
    icon: ImageVector?,
    titleRes: Int,
    description: String?,
    endIcon: EndIconType?,
    shouldTintIcon: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.SettingHorizontalMargin, vertical = Dimens.SettingVerticalMargin),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            if (shouldTintIcon) {
                Icon(imageVector = it, contentDescription = null, tint = SwissTransferTheme.materialColors.primary)
            } else {
                Image(imageVector = it, contentDescription = null)
            }
            Spacer(Modifier.width(Margin.Medium))
        }

        Column(modifier = Modifier.weight(1.0f)) {
            Text(text = stringResource(id = titleRes), style = SwissTransferTheme.typography.bodyRegular)

            description?.let {
                Text(
                    text = it,
                    style = SwissTransferTheme.typography.bodySmallRegular,
                    color = SwissTransferTheme.colors.tertiaryTextColor,
                )
            }
        }

        endIcon?.let {
            Icon(imageVector = it.icon, contentDescription = null, tint = SwissTransferTheme.colors.iconColor)
        }
    }
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Column(modifier = Modifier.selectableGroup()) {
                SettingTitle(R.string.appName)
                SettingItem(R.string.appName, isSelected = { true }, AppIcons.Add, "Clair", EndIconType.CHEVRON) {}
                SettingItem(R.string.appName, isSelected = { false }, AppIcons.Folder, endIcon = EndIconType.OPEN_OUTSIDE) {}
                SettingItem(R.string.appName, isSelected = { false }, description = "1.1.2") {}
                SettingItem(R.string.appName, isSelected = { false }) {}
                SettingDivider()
                SettingTitle(R.string.appName)
                SettingItem(R.string.appName, isSelected = { false }, endIcon = EndIconType.OPEN_OUTSIDE) {}
                SettingItem(R.string.appName, isSelected = { false }, endIcon = EndIconType.OPEN_OUTSIDE) {}
                SettingItem(R.string.appName, isSelected = { false }, description = "0.0.1", onClick = null)
            }
        }
    }
}

enum class EndIconType(val icon: ImageVector) {
    CHEVRON(AppIcons.ChevronRightThick),
    OPEN_OUTSIDE(AppIcons.ArrowOpenOutside),
}
