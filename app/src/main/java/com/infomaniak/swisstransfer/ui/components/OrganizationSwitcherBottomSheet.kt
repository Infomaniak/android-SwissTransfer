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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.infomaniak.core.avatar.components.Avatar
import com.infomaniak.core.avatar.models.AvatarColors
import com.infomaniak.core.avatar.models.AvatarType
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.multiplatform_swisstransfer.database.models.OrganizationAccount
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Checkmark
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.avatarType
import com.infomaniak.core.ui.compose.basics.Dimens as CoreDimens

private val ORGANIZATION_ITEM_HEIGHT = 56.dp

/**
 * A bottom sheet that lists the organizations the current user belongs to, and lets him switch between them.
 * This is adapted from kDrive's drive switcher bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationSwitcherBottomSheet(
    onDismissRequest: () -> Unit,
    organizations: List<OrganizationAccount>,
    selectedOrganizationId: Long?,
    onOrganizationClicked: (OrganizationAccount) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
) {
    SwissTransferBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        title = stringResource(R.string.settingsSwitchOrganization),
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                organizations.forEach { organization ->
                    OrganizationItem(
                        organizationName = organization.name,
                        organizationAvatar = organization.avatarType(),
                        isSelected = organization.id == selectedOrganizationId,
                        onClick = { onOrganizationClicked(organization) },
                    )
                }
            }
        },
    )
}

@Composable
private fun OrganizationItem(
    organizationName: String,
    organizationAvatar: AvatarType,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    SharpRippleButton(
        modifier = Modifier
            .height(ORGANIZATION_ITEM_HEIGHT)
            .fillMaxWidth(),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Margin.Large),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Margin.Medium)
        ) {
            Avatar(
                avatarType = organizationAvatar,
                modifier = Modifier.size(CoreDimens.bigAvatarSize),
                shape = RoundedCornerShape(2.dp)
            )
            Text(
                text = organizationName,
                style = SwissTransferTheme.typography.bodyRegular,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1.0f),
            )
            if (isSelected) {
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

private fun previewAvatar(initials: String) = AvatarType.WithInitials.Initials(
    initials = initials,
    colors = AvatarColors(containerColor = Color(0xFF3CB572), contentColor = Color.White),
)

@PreviewLightAndDark
@Composable
private fun OrganizationItemPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                OrganizationItem(
                    organizationName = "Westworld",
                    organizationAvatar = previewAvatar("W"),
                    isSelected = true,
                    onClick = {},
                )
                OrganizationItem(
                    organizationName = "Infomaniak Group",
                    organizationAvatar = previewAvatar("IG"),
                    isSelected = false,
                    onClick = {},
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightAndDark
@Composable
private fun OrganizationBottomSheetPreview() {
    SwissTransferTheme {
        OrganizationSwitcherBottomSheet(
            onDismissRequest = {},
            organizations = listOf(
                OrganizationAccount(
                    id = 1,
                    name = "Westworld",
                    logoUrl = null,
                    userId = 1,
                    type = "",
                    pack = "",
                    isInKSuite = true,
                    limits = OrganizationAccount.Limits(-1L)
                ),
                OrganizationAccount(
                    id = 2,
                    name = "Infomaniak Group",
                    logoUrl = null,
                    userId = 2,
                    type = "",
                    pack = "",
                    isInKSuite = true,
                    limits = OrganizationAccount.Limits(-1L)
                )
            ),
            selectedOrganizationId = 1,
            onOrganizationClicked = {}
        )
    }
}
