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

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SharpRippleButton
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.icons.app.Checkmark
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun MutuallyExclusiveOptions(items: List<SettingOption>, selectedItem: Int, setSelectedItem: (Int) -> Unit) {
    Column(Modifier.selectableGroup()) {
        items.forEachIndexed { index, item ->
            if (index > 0) HorizontalDivider(Modifier.padding(horizontal = Margin.Medium))
            SettingOptionItem(item, isSelected = selectedItem == index) { setSelectedItem(index) }
        }
    }
}

@Composable
private fun SettingOptionItem(item: SettingOption, isSelected: Boolean, onClick: () -> Unit) {
    SharpRippleButton(
        modifier = Modifier.selectable(selected = isSelected, onClick = {}), // onClick left empty because handled by button
        onClick = onClick
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Margin.Medium, vertical = Margin.Medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            item.icon?.let {
                Icon(imageVector = it, contentDescription = null)
                Spacer(modifier = Modifier.width(Margin.Medium))
            }

            Text(text = stringResource(id = item.title), Modifier.weight(1f))

            if (isSelected) Spacer(modifier = Modifier.width(Margin.Medium))
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
    @get:StringRes
    val title: Int
    val icon: ImageVector?
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun SettingOptionItemPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                val item = object : SettingOption {
                    override val title: Int = R.string.appName
                    override val icon: ImageVector = AppIcons.Add
                }
                SettingOptionItem(item, true) {}
                SettingOptionItem(item, false) {}
            }
        }
    }
}
