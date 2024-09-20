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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun SettingTitle(@StringRes titleRes: Int) {
    UnpaddedTitle(
        Modifier.padding(horizontal = Dimens.SettingHorizontalMargin, vertical = Dimens.SettingVerticalMargin),
        titleRes
    )
}

@Composable
fun OptionTitle(@StringRes titleRes: Int) {
    UnpaddedTitle(Modifier.padding(horizontal = Dimens.SettingHorizontalMargin, vertical = Margin.Large), titleRes)
}

@Composable
private fun UnpaddedTitle(modifier: Modifier, titleRes: Int) {
    Text(
        modifier = modifier,
        text = stringResource(id = titleRes),
        style = SwissTransferTheme.typography.bodySmallRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun OptionTitlePreview() {
    SwissTransferTheme {
        Surface {
            Box {
                OptionTitle(titleRes = R.string.appName)
            }
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun SettingTitlePreview() {
    SwissTransferTheme {
        Surface {
            SettingTitle(titleRes = R.string.appName)
        }
    }
}
