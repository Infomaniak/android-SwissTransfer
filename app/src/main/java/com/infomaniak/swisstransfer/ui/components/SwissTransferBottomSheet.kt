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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infomaniak.core.ui.compose.bottomstickybuttonscaffolds.DoubleStackedButtonScaffold
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.ArrowDownRightCurved
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwissTransferBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onDismissRequest: () -> Unit,
    imageVector: ImageVector? = null,
    title: String? = null,
    description: String? = null,
    topButton: @Composable ((Modifier) -> Unit)? = null,
    bottomButton: @Composable ((Modifier) -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    ModalBottomSheet(onDismissRequest, modifier, sheetState) {
        BottomSheetContent(imageVector, title, description, content, topButton, bottomButton)
    }
}

@Composable
private fun BottomSheetContent(
    imageVector: ImageVector?,
    title: String?,
    description: String?,
    content: @Composable (() -> Unit)?,
    topButton: @Composable ((Modifier) -> Unit)? = null,
    bottomButton: @Composable ((Modifier) -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val paddedModifier = Modifier.padding(horizontal = Margin.Medium)

        imageVector?.let {
            Image(modifier = paddedModifier, imageVector = imageVector, contentDescription = null)
            Spacer(Modifier.height(Margin.Large))
        }

        title?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.bodyMedium,
                color = SwissTransferTheme.colors.primaryTextColor,
                modifier = paddedModifier,
            )
            Spacer(Modifier.height(Margin.Large))
        }

        description?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.bodyRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                modifier = paddedModifier,
            )
            Spacer(Modifier.height(Margin.Large))
        }

        content?.let {
            it()
            Spacer(Modifier.height(Margin.Large))
        }

        DoubleStackedButtonScaffold(
            modifier = Modifier.padding(bottom = Dimens.ButtonComboVerticalPadding),
            topButton = topButton,
            bottomButton = bottomButton,
        )
    }
}

@PreviewLightAndDark
@Composable
private fun BottomSheetDefaultsPreview() {
    SwissTransferTheme {
        Surface {
            BottomSheetContent(
                imageVector = AppIllus.ArrowDownRightCurved,
                title = stringResource(R.string.appName),
                description = stringResource(R.string.sentEmptyTitle),
                content = {
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(Color.Gray),
                    )
                },
                topButton = {
                    LargeButton(
                        modifier = it,
                        title = "Top button",
                        style = ButtonType.Destructive,
                        onClick = {},
                    )
                },
                bottomButton = {
                    LargeButton(
                        modifier = it,
                        title = "Bottom button",
                        style = ButtonType.Tertiary,
                        onClick = {},
                    )
                },
            )
        }
    }
}
