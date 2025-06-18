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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SharpRippleButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.images.icons.Checkmark
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun SingleSelectOptions(items: List<SettingOption>, selectedItem: () -> Int, setSelectedItem: (Int) -> Unit) {
    Column(Modifier.selectableGroup()) {
        items.forEachIndexed { index, item ->
            if (index > 0) HorizontalDivider(Modifier.padding(horizontal = Margin.Medium))
            SettingOptionItem(item, isSelected = selectedItem() == index) { setSelectedItem(index) }
        }
    }
}

@Composable
private fun SettingOptionItem(item: SettingOption, isSelected: Boolean, onClick: () -> Unit) {
    SharpRippleButton(
        modifier = Modifier.selectable(selected = isSelected, onClick = onClick),
        onClick = onClick,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Margin.Medium, vertical = Margin.Medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            item.icon?.let {
                Image(imageVector = it, contentDescription = null)
                Spacer(Modifier.width(Margin.Medium))
            } ?: run {
                // Make sure the items with no icons have the same height as the ones with icons
                Spacer(Modifier.height(Margin.Large))
            }

            Text(
                text = item.title(),
                style = SwissTransferTheme.typography.bodyRegular,
                modifier = Modifier.weight(1.0f),
            )

            if (isSelected) Spacer(Modifier.width(Margin.Medium))
            AnimatedVisibility(
                visible = isSelected,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                Icon(
                    imageVector = AppIcons.Checkmark,
                    contentDescription = null,
                    tint = SwissTransferTheme.materialColors.primary,
                )
            }
        }
    }
}

interface SettingOption {
    val title: @Composable () -> String
    val imageVector: ImageVector?
    val imageVectorResId: Int?

    val icon: ImageVector?
        @Composable get() = imageVector ?: imageVectorResId?.let { ImageVector.vectorResource(it) }
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Column {
                val iconItem = object : SettingOption {
                    override val title: @Composable () -> String = { stringResource(R.string.appName) }
                    override val imageVector: ImageVector = AppIcons.Add
                    override val imageVectorResId = null
                }
                val textItem = object : SettingOption {
                    override val title: @Composable () -> String = { stringResource(R.string.appName) }
                    override val imageVector = null
                    override val imageVectorResId = null
                }
                SettingOptionItem(item = iconItem, isSelected = true) {}
                SettingOptionItem(item = iconItem, isSelected = false) {}
                SettingOptionItem(item = textItem, isSelected = false) {}
            }
        }
    }
}
