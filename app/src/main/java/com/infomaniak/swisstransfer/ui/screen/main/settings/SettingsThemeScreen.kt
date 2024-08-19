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

package com.infomaniak.swisstransfer.ui.screen.main.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SharpRippleButton
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.icons.app.Checkmark
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingTitle
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun SettingsThemeScreen() {
    Column {
        SettingTitle(titleRes = R.string.appName)
        MutuallyExclusiveOptions(ThemeOption.entries)
    }
}

enum class ThemeOption(override val title: Int, override val icon: ImageVector) : SettingOption {
    SYSTEM(R.string.appName, AppIcons.Add),
    LIGHT(R.string.appName, AppIcons.Add),
    DARK(R.string.appName, AppIcons.Add),
}

interface SettingOption {
    @get:StringRes
    val title: Int
    val icon: ImageVector?
}

@Composable
fun MutuallyExclusiveOptions(items: List<SettingOption>) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) } // TODO: Use DataStore

    Column(Modifier.selectableGroup()) {
        items.forEachIndexed { index, item ->
            if (index > 0) HorizontalDivider(Modifier.padding(horizontal = Margin.Medium))
            SettingOptionItem(item, isSelected = selectedItem == index) { selectedItem = index }
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

            if (isSelected) {
                Spacer(modifier = Modifier.width(Margin.Medium))
                Icon(
                    imageVector = AppIcons.Checkmark,
                    contentDescription = null,
                    tint = SwissTransferTheme.materialColors.primary,
                )
            }
        }
    }
}


@PreviewMobile
@PreviewTablet
@Composable
private fun SettingsThemeScreenPreview() {
    SwissTransferTheme {
        Surface {
            SettingsThemeScreen()
        }
    }
}
