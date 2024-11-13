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
package com.infomaniak.swisstransfer.ui.screen.main.received.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.ArrowRightCurved
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun ReceivedEmptyFab(isMessageVisible: () -> Boolean) {
    ConstraintLayout {
        val (text, icon, fab) = createRefs()

        if (isMessageVisible()) {
            Text(
                text = stringResource(R.string.firstTransferDescription),
                style = SwissTransferTheme.typography.bodyRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                modifier = Modifier
                    .padding(top = Margin.Huge)
                    .constrainAs(text) {
                        bottom.linkTo(icon.top, Margin.Mini)
                        end.linkTo(icon.end, Margin.Mini)
                    },
            )

            Icon(
                modifier = Modifier
                    .constrainAs(icon) {
                        top.linkTo(fab.top)
                        bottom.linkTo(fab.bottom, Margin.Mini)
                        end.linkTo(fab.start, Margin.Medium)
                    },
                imageVector = AppIllus.ArrowRightCurved,
                contentDescription = null,
            )
        }

        NewTransferFab(
            modifier = Modifier.constrainAs(fab) {},
            newTransferFabType = NewTransferFabType.BOTTOM_BAR,
        )
    }
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
                ReceivedEmptyFab(isMessageVisible = { true })
            }
        }
    }
}
