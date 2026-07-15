/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.ui.compose.basics.Dimens
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ArrowCircle
import com.infomaniak.swisstransfer.ui.images.icons.Organization
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun OrganizationSwitcher(
    organizationName: String,
    onClick: (() -> Unit),
    modifier: Modifier = Modifier,
    disabled: Boolean = false,
    organizationIcon: ImageVector = AppIcons.Organization,
    switchIcon: ImageVector = AppIcons.ArrowCircle,
    contentPadding: PaddingValues = PaddingValues(Margin.Mini)
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = !disabled,
        colors = ButtonDefaults.textButtonColors(disabledContentColor = SwissTransferTheme.colors.primaryTextColor)
    ) {
        Row(
            modifier = Modifier.padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
        ) {
            Icon(
                imageVector = organizationIcon,
                contentDescription = null,
                modifier = Modifier.size(Dimens.smallIconSize),
            )
            Text(
                text = organizationName,
                style = SwissTransferTheme.typography.bodyRegular,
            )
            if (!disabled) {
                Icon(
                    imageVector = switchIcon,
                    contentDescription = stringResource(R.string.settingsSwitchOrganization),
                    modifier = Modifier.size(Dimens.smallIconSize),
                )
            }
        }
    }
}

@PreviewLightAndDark
@Composable
private fun OrganizationSwitcherPreview() {
    SwissTransferTheme {
        Surface {
            OrganizationSwitcher(
                organizationName = "Infomaniak Group",
                onClick = { },
                modifier = Modifier.padding(Margin.Medium),
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun OrganizationSwitcherSingleOrganizationPreview() {
    SwissTransferTheme {
        Surface {
            OrganizationSwitcher(
                organizationName = "Infomaniak Group",
                onClick = { },
                modifier = Modifier.padding(Margin.Medium),
                disabled = true,
            )
        }
    }
}
