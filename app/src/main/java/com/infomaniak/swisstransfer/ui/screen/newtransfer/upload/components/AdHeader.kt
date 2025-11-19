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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.core.onboarding.components.HighlightedText
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadProgressAdType
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun ColumnScope.AdHeader(adScreenType: UploadProgressAdType) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1.0f)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(adScreenType.illustration))
        var isHighlighted by rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(Unit) { isHighlighted = true }

        Spacer(Modifier.height(Margin.Giant))

        HighlightedText(
            template = stringResource(R.string.uploadProgressTitleTemplate),
            argument = stringResource(R.string.uploadProgressTitleArgument),
            style = SwissTransferTheme.typography.bodyMedium,
            highlightedColor = SwissTransferTheme.colors.highlightedColor,
            isHighlighted = { isHighlighted },
        )

        Spacer(Modifier.height(Margin.Huge))

        Text(
            text = adScreenType.description(),
            textAlign = TextAlign.Center,
            style = SwissTransferTheme.typography.specificLight20,
            modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
        )

        WeightOneSpacer(minHeight = Margin.Medium)

        LottieAnimation(
            composition,
            isPlaying = true,
            iterations = LottieConstants.IterateForever,
        )

        WeightOneSpacer(minHeight = Margin.Medium)
    }
}

@PreviewAllWindows
@Composable
private fun AdHeaderPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                AdHeader(UploadProgressAdType.ENERGY)
            }
        }
    }
}
