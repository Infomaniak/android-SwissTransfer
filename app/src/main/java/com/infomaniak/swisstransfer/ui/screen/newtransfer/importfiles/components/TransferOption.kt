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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.ui.components.SharpRippleButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightThick
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun TransferOption(
    transferOptionType: TransferOptionType,
    selectedSetting: () -> SettingOption?,
    onClick: () -> Unit,
) {
    SharpRippleButton(onClick = onClick, contentPadding = PaddingValues(horizontal = Margin.Large, vertical = Margin.Medium)) {
        Icon(
            modifier = Modifier.size(Dimens.SmallIconSize),
            imageVector = transferOptionType.buttonIcon,
            tint = SwissTransferTheme.materialColors.primary,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(Margin.Small))
        Text(
            text = stringResource(transferOptionType.buttonText),
            color = SwissTransferTheme.materialColors.primary,
            style = SwissTransferTheme.typography.bodySmallMedium,
        )
        Spacer(modifier = Modifier.weight(1.0f))
        SettingValue(selectedSetting)
        Spacer(modifier = Modifier.width(Margin.Small))
        Icon(
            modifier = Modifier.size(Dimens.SmallIconSize),
            imageVector = AppIcons.ChevronRightThick,
            tint = SwissTransferTheme.colors.iconColor,
            contentDescription = null,
        )
    }
}

@Composable
private fun SettingValue(selectedSetting: () -> SettingOption?) {
    Text(
        text = selectedSetting()?.title?.invoke() ?: "",
        style = SwissTransferTheme.typography.bodySmallRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )
}

@PreviewLightAndDark
@Composable
private fun TransferTypeButtonsPreview() {
    SwissTransferTheme {
        Surface {
            TransferOption(
                transferOptionType = TransferOptionType.VALIDITY_DURATION,
                selectedSetting = { ValidityPeriodOption.THIRTY },
                onClick = {},
            )
        }
    }
}
