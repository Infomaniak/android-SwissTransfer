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

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import com.infomaniak.core.avatar.components.Avatar
import com.infomaniak.core.ui.compose.basics.bottomsheet.dismissGracefully
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.multiplatform_swisstransfer.database.models.OrganizationAccount
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.avatarType
import com.infomaniak.swisstransfer.ui.utils.myKSuiteTier
import java.util.Locale
import com.infomaniak.core.ui.compose.basics.Dimens as CoreDimens

/**
 * A bottom sheet that lists the organizations the current user belongs to, and lets him switch between them.
 * This is adapted from kDrive's drive switcher bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationSwitcherBottomSheet(
    onOrganizationClicked: (OrganizationAccount) -> Unit,
    closeBottomSheet: () -> Unit,
    organizations: List<OrganizationAccount>,
    selectedOrganizationId: Long?,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    SwissTransferBottomSheet(
        onDismissRequest = closeBottomSheet,
        modifier = modifier,
        sheetState = sheetState,
        title = stringResource(R.string.settingsOptionOrganization),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup(),
        ) {
            organizations.forEachIndexed { index, organization ->
                if (index > 0) HorizontalDivider(modifier = Modifier.padding(horizontal = Margin.Large))
                OrganizationItem(
                    organization = organization,
                    isSelected = organization.id == selectedOrganizationId,
                    onClick = {
                        onOrganizationClicked(organization)
                        sheetState.dismissGracefully(scope, onDismissRequest = { closeBottomSheet() })
                    },
                )
            }
        }
    }
}

@Composable
private fun OrganizationItem(
    organization: OrganizationAccount,
    onClick: () -> Unit,
    isSelected: Boolean,
) {
    BottomSheetItem(
        onClick = onClick,
        isSelected = isSelected,
        leading = {
            Avatar(
                avatarType = organization.avatarType(),
                modifier = Modifier
                    .size(CoreDimens.bigAvatarSize)
                    .border(Dimens.BorderWidth, SwissTransferTheme.materialColors.outline, CustomShapes.EXTRA_SMALL),
                shape = CustomShapes.EXTRA_SMALL,
            )
        },
    ) {
        Column(modifier = Modifier.weight(1.0f)) {
            val organizationPack = organization.pack
            val myKSuiteTier = organization.myKSuiteTier

            Text(
                text = organization.name,
                style = SwissTransferTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            when {
                myKSuiteTier != null -> {
                    Image(
                        imageVector = ImageVector.vectorResource(id = myKSuiteTier.iconRes),
                        contentDescription = stringResource(myKSuiteTier.descriptionName),
                    )
                }
                organizationPack.isNotBlank() -> {
                    Text(
                        text = organizationPack.replaceFirstChar { it.titlecase(Locale.getDefault()) },
                        style = SwissTransferTheme.typography.bodyRegular,
                        color = SwissTransferTheme.colors.secondaryTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@PreviewLightAndDark
@Composable
private fun OrganizationItemPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                OrganizationItem(
                    organization = previewOrganizationAccount(
                        id = 1L,
                        name = "Westworld",
                        type = "ksuite",
                        pack = "Entreprise",
                    ),
                    isSelected = true,
                    onClick = {},
                )
                OrganizationItem(
                    organization = previewOrganizationAccount(
                        id = 2L,
                        name = "Infomaniak Group",
                        type = "my_ksuite",
                        pack = "Particulier",
                    ),
                    isSelected = false,
                    onClick = {},
                )
                OrganizationItem(
                    organization = previewOrganizationAccount(
                        id = 3L,
                        name = "pedro.perso@ik.me",
                        type = "my_ksuite",
                        pack = "my_ksuite_plus",
                    ),
                    isSelected = false,
                    onClick = {},
                )
                OrganizationItem(
                    organization = previewOrganizationAccount(id = 4L, name = "pedro.perso@ik.me", type = "Particulier"),
                    isSelected = false,
                    onClick = {},
                )
            }
        }
    }
}
