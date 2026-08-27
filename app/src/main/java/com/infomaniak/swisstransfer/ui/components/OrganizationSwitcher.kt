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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core.avatar.components.Avatar
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.multiplatform_swisstransfer.database.models.OrganizationAccount
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightThick
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.avatarType

private val AVATAR_SIZE = 24.dp
private const val CHEVRON_DOWN_ROTATION = 90.0f

@Composable
fun OrganizationSwitcher(
    modifier: Modifier = Modifier,
    organizationSwitcherViewModel: OrganizationSwitcherViewModel = hiltViewModel<OrganizationSwitcherViewModel>(),
) {
    val selectedOrganization by organizationSwitcherViewModel.selectedOrganization.collectAsStateWithLifecycle()
    val organizations by organizationSwitcherViewModel.organizations.collectAsStateWithLifecycle()

    OrganizationSwitcher(
        selectedOrganization = { selectedOrganization },
        organizations = { organizations },
        onSwitchOrganization = { organizationAccountId -> organizationSwitcherViewModel.switchToOrganization(organizationAccountId) },
        modifier = modifier,
    )
}

@Composable
private fun OrganizationSwitcher(
    selectedOrganization: () -> OrganizationAccount?,
    organizations: () -> List<OrganizationAccount>,
    onSwitchOrganization: (organizationAccountId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val organization = selectedOrganization() ?: return
    if (organizations().size <= 1) return

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = modifier
            .offset(x = -Margin.Mini) // Helps to align the org avatar without cutting the touch feedback off.
            .clip(CustomShapes.SMALL)
            .clickable { showBottomSheet = true }
            .padding(Margin.Mini),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
    ) {
        Avatar(
            avatarType = organization.avatarType(),
            modifier = Modifier.size(AVATAR_SIZE),
            shape = CustomShapes.EXTRA_SMALL,
        )
        Text(
            modifier = Modifier.weight(1.0f, fill = false),
            text = organization.name,
            style = SwissTransferTheme.typography.bodyRegular,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Icon(
            modifier = Modifier
                .size(Dimens.SmallIconSize)
                .rotate(CHEVRON_DOWN_ROTATION),
            imageVector = AppIcons.ChevronRightThick,
            contentDescription = null,
            tint = SwissTransferTheme.colors.secondaryTextColor,
        )
    }

    if (showBottomSheet) {
        OrganizationSwitcherBottomSheet(
            onOrganizationClicked = { clickedOrganization -> onSwitchOrganization(clickedOrganization.id) },
            closeBottomSheet = { showBottomSheet = false },
            organizations = organizations(),
            selectedOrganizationId = organization.id,
        )
    }
}

internal fun previewOrganizationAccount(
    id: Long,
    name: String,
    type: String = "",
    pack: String = "",
) = OrganizationAccount(
    id = id,
    userId = 1L,
    name = name,
    logoUrl = null,
    type = type,
    pack = pack,
    isInKSuite = false,
    limits = OrganizationAccount.Limits(transferTotalSize = 0L),
)

@PreviewLightAndDark
@Composable
private fun Preview() {
    val organizations = listOf(
        previewOrganizationAccount(id = 1L, name = "Infomaniak Network SA"),
        previewOrganizationAccount(id = 2L, name = "SwissTransfer"),
    )

    SwissTransferTheme {
        Surface {
            OrganizationSwitcher(
                selectedOrganization = { organizations.first() },
                organizations = { organizations },
                onSwitchOrganization = {},
            )
        }
    }
}
