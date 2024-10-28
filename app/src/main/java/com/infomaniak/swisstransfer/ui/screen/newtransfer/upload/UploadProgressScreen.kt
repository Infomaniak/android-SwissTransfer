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

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.ThemedImage
import com.infomaniak.swisstransfer.ui.images.illus.matomo.Matomo
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferViewModel
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow
import java.util.Locale

@Composable
fun UploadProgressScreen(newTransferViewModel: NewTransferViewModel = hiltViewModel<NewTransferViewModel>()) {
    val uploadedSizeInBytes by newTransferViewModel.uploadedSizeInBytes.collectAsState()
    val totalSizeInBytes by newTransferViewModel.totalSizeInBytes.collectAsState()

    UploadProgressScreen({ uploadedSizeInBytes }, { totalSizeInBytes })
}

@Composable
private fun UploadProgressScreen(uploadedSizeInBytes: () -> Long, totalSizeInBytes: () -> Long) {
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

                Image(imageVector = AppIllus.Matomo.image(), contentDescription = null)

                WeightOneSpacer(minHeight = Margin.Medium)
            }

            Spacer(modifier = Modifier.height(Margin.Medium))
            Text("Transfert en cours...")
            Progress(uploadedSizeInBytes, totalSizeInBytes)
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
fun Progress(uploadedSizeInBytes: () -> Long, totalSizeInBytes: () -> Long) {
    Row {
        Percentage(uploadedSizeInBytes, totalSizeInBytes)
        Text(" - ")
        UploadedSize(uploadedSizeInBytes)

        TotalSize(totalSizeInBytes)
    }
}

@Composable
private fun Percentage(uploadedSizeInBytes: () -> Long, totalSizeInBytes: () -> Long) {
    val percentageNoDecimals by remember {
        derivedStateOf {
            val percentage = (uploadedSizeInBytes().toFloat() / totalSizeInBytes())
            String.format(Locale.getDefault(), "%.0f", percentage * 100)
        }
    }

    Text("$percentageNoDecimals%")
}

@Composable
private fun UploadedSize(uploadedSizeInBytes: () -> Long) {
    val context = LocalContext.current
    val humanReadableSize by remember {
        derivedStateOf { HumanReadableSizeUtils.getHumanReadableSize(context, uploadedSizeInBytes()) }
    }

    Text(humanReadableSize)
}

@Composable
private fun TotalSize(totalSizeInBytes: () -> Long) {
    val context = LocalContext.current
    val humanReadableTotalSize by remember {
        derivedStateOf { HumanReadableSizeUtils.getHumanReadableSize(context, totalSizeInBytes()) }
    }

    Text(" / $humanReadableTotalSize")
}


enum class UploadProgressAdType(
    @StringRes private val descriptionTemplate: Int,
    @StringRes private val descriptionAccentuatedPart: Int,
    val illustration: ThemedImage,
) {
    INDEPENDENCE(R.string.appName, R.string.appName, AppIllus.Matomo),
    ENERGY(R.string.appName, R.string.appName, AppIllus.Matomo),
    CONFIDENTIALITY(R.string.appName, R.string.appName, AppIllus.Matomo);

    @Composable
    fun description(): AnnotatedString {
        val template = stringResource(descriptionTemplate)
        val templateArgument = stringResource(descriptionAccentuatedPart)

        val description = String.format(template, templateArgument)

        val startIndex = description.indexOf(templateArgument)
        val endIndex = startIndex + templateArgument.length

        return buildAnnotatedString {
            append(description)
            addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), start = startIndex, end = endIndex)
        }
    }
}


@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun UploadProgressScreenPreview() {
    SwissTransferTheme {
        UploadProgressScreen({ 44321654 }, { 76321894 })
    }
}
