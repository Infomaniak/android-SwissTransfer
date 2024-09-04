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
package com.infomaniak.swisstransfer.ui.screen.main.sent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.illus.ArrowDownRightCurved
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun SentEmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val maxWidth = Dimens.DescriptionWidth
        Text(
            modifier = Modifier.widthIn(max = maxWidth),
            text = stringResource(id = R.string.sentEmptyTitle),
            style = SwissTransferTheme.typography.specificMedium32,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(Margin.Medium))
        Text(
            modifier = Modifier.widthIn(max = maxWidth),
            text = stringResource(id = R.string.firstTransferDescription),
            style = SwissTransferTheme.typography.bodyRegular,
        )
        Spacer(modifier = Modifier.height(Margin.Medium))
        ConstraintLayout {
            val (icon, fab) = createRefs()

            Icon(
                modifier = Modifier
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        end.linkTo(fab.start, Margin.Small)
                    },
                imageVector = AppImages.AppIllus.ArrowDownRightCurved,
                contentDescription = null,
            )
            NewTransferFab(
                modifier = Modifier
                    .constrainAs(fab) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, Margin.Large)
                    },
                newTransferFabType = NewTransferFabType.EMPTY_STATE,
            )
        }
    }
}

@PreviewMobile
@PreviewTablet
@Composable
private fun SentEmptyScreenPreview() {
    SwissTransferTheme {
        Surface {
            SentEmptyScreen()
        }
    }
}
