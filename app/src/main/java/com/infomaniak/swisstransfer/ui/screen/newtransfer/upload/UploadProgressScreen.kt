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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.illus.matomo.Matomo
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun UploadProgressScreen() {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(R.string.appName, modifier = it, onClick = {})
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(Margin.Giant))
                Text("Ce qui nous rend différent ?", style = SwissTransferTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(Margin.Huge))
                Text(
                    "Nous développons l’indépendance technologique en Europe. Sans compromis sur l’écologie, la vie privée et l'humain.",
                    modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
                    style = SwissTransferTheme.typography.specificLight18,
                    textAlign = TextAlign.Center,
                )

                WeightOneSpacer(minHeight = Margin.Medium)

                Image(imageVector = AppImages.AppIllus.Matomo.image(), contentDescription = null)

                WeightOneSpacer(minHeight = Margin.Medium)
            }

            Spacer(modifier = Modifier.height(Margin.Medium))
            Text("Transfert en cours...")
            Progress()
            Spacer(modifier = Modifier.height(Margin.Huge)) // TODO
        }
    }
}

@Composable
private fun ColumnScope.WeightOneSpacer(minHeight: Dp) {
    Spacer(modifier = Modifier.height(minHeight))
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun Progress() {
    Row {
        Percentage()
        Text(" - ")
        UploadedSize()
        Text(" / 6.7 Mo")
    }
}

@Composable
private fun Percentage() {
    Text("30%")
}

@Composable
private fun UploadedSize() {
    Text("2 Mo")
}


@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun UploadProgressScreenPreview() {
    SwissTransferTheme {
        UploadProgressScreen()
    }
}
