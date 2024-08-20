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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.icons.app.ChevronRightSmall
import com.infomaniak.swisstransfer.ui.icons.app.Folder
import com.infomaniak.swisstransfer.ui.icons.app.OpenOutside
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

private val horizontalMargin = Margin.Medium
private val verticalMargin = 12.dp

@Composable
fun SettingTitle(@StringRes titleRes: Int) {
    Text(
        modifier = Modifier.padding(horizontal = horizontalMargin, vertical = verticalMargin),
        text = stringResource(id = titleRes),
        style = SwissTransferTheme.typography.bodySmallRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )
}

@Composable
fun SettingItem(
    @StringRes titleRes: Int,
    icon: ImageVector? = null,
    description: String? = null,
    endIcon: EndIconType? = null,
    onClick: (() -> Unit)?,
) {
    val modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 56.dp)

    onClick?.let {
        SharpRippleButton(
            modifier = modifier,
            onClick = it,
        ) {
            SettingItemContent(icon, titleRes, description, endIcon)
        }
    } ?: run {
        Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
            SettingItemContent(icon, titleRes, description, endIcon)
        }
    }
}

@Composable
private fun SettingItemContent(
    icon: ImageVector?,
    titleRes: Int,
    description: String?,
    endIcon: EndIconType?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalMargin, vertical = verticalMargin),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(imageVector = it, contentDescription = null, tint = SwissTransferTheme.materialColors.primary)
            Spacer(modifier = Modifier.width(Margin.Medium))
        }

        Column(modifier = Modifier.weight(1f)) {
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

@Composable
fun SettingDivider() {
    HorizontalDivider(modifier = Modifier.padding(horizontal = horizontalMargin, vertical = verticalMargin))
}

@Preview
@Composable
private fun SettingItemPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                SettingTitle(R.string.appName)
                SettingItem(R.string.appName, AppIcons.Add, "Clair", EndIconType.CHEVRON) {}
                SettingItem(R.string.appName, AppIcons.Folder, endIcon = EndIconType.OPEN_OUTSIDE) {}
                SettingItem(R.string.appName, description = "1.1.2") {}
                SettingItem(R.string.appName) {}
                SettingDivider()
                SettingTitle(R.string.appName)
                SettingItem(R.string.appName, endIcon = EndIconType.OPEN_OUTSIDE) {}
                SettingItem(R.string.appName, endIcon = EndIconType.OPEN_OUTSIDE) {}
                SettingItem(R.string.appName, description = "0.0.1", onClick = null)
            }
        }
    }
}

enum class EndIconType(val icon: ImageVector) {
    CHEVRON(AppIcons.ChevronRightSmall),
    OPEN_OUTSIDE(AppIcons.OpenOutside),
}
