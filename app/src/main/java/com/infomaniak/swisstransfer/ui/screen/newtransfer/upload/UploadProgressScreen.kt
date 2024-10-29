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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.ThemedImage
import com.infomaniak.swisstransfer.ui.images.illus.uploadAd.MetallicSafe
import com.infomaniak.swisstransfer.ui.images.illus.uploadAd.MountainGondola
import com.infomaniak.swisstransfer.ui.images.illus.uploadAd.SwissWithFlag
import com.infomaniak.swisstransfer.ui.images.illus.uploadCancelBottomSheet.RedCrossPaperPlanes
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

    val adScreenType = rememberSaveable { UploadProgressAdType.entries.random() }

    UploadProgressScreen(
        uploadedSizeInBytes = { uploadedSizeInBytes },
        totalSizeInBytes = { totalSizeInBytes },
        adScreenType = adScreenType,
        onCancel = {
            // TODO
        }
    )
}

@Composable
private fun UploadProgressScreen(
    uploadedSizeInBytes: () -> Long,
    totalSizeInBytes: () -> Long,
    adScreenType: UploadProgressAdType,
    onCancel: () -> Unit,
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(R.string.buttonCancel, modifier = it, onClick = { showBottomSheet = true })
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
                Text(stringResource(R.string.uploadSuccessTitle), style = SwissTransferTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(Margin.Huge))
                Text(
                    adScreenType.description(),
                    modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
                    style = SwissTransferTheme.typography.specificLight18,
                    textAlign = TextAlign.Center,
                )

                WeightOneSpacer(minHeight = Margin.Medium)

                Image(imageVector = adScreenType.illustration.image(), contentDescription = null)

                WeightOneSpacer(minHeight = Margin.Medium)
            }

            Spacer(modifier = Modifier.height(Margin.Medium))
            Text(stringResource(R.string.uploadSuccessTransferInProgress))
            Progress(uploadedSizeInBytes, totalSizeInBytes)
            Spacer(modifier = Modifier.height(Margin.Huge)) // TODO
        }

        if (showBottomSheet) CancelUploadBottomSheet(onCancel = onCancel, closeButtonSheet = { showBottomSheet = false })
    }
}

@Composable
private fun CancelUploadBottomSheet(onCancel: () -> Unit, closeButtonSheet: () -> Unit) {
    SwissTransferBottomSheet(
        titleRes = R.string.appName,
        imageVector = AppIllus.RedCrossPaperPlanes.image(),
        topButton = {
            LargeButton(
                titleRes = R.string.appName,
                modifier = it,
                style = ButtonType.ERROR,
                onClick = onCancel,
            )
        },
        bottomButton = {
            LargeButton(
                titleRes = R.string.appName,
                modifier = it,
                style = ButtonType.TERTIARY,
                onClick = closeButtonSheet,
            )
        },
        onDismissRequest = closeButtonSheet,
    )
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
    INDEPENDENCE(
        R.string.uploadSuccessDescriptionTemplateIndependence,
        R.string.uploadSuccessDescriptionArgumentIndependence,
        AppIllus.SwissWithFlag
    ),
    ENERGY(
        R.string.uploadSuccessDescriptionTemplateEnergy,
        R.string.uploadSuccessDescriptionArgumentEnergy,
        AppIllus.MountainGondola
    ),
    CONFIDENTIALITY(
        R.string.uploadSuccessDescriptionTemplateConfidentiality,
        R.string.uploadSuccessDescriptionArgumentConfidentiality,
        AppIllus.MetallicSafe
    );

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
        UploadProgressScreen(
            { 44321654 },
            { 76321894 },
            UploadProgressAdType.INDEPENDENCE,
            {}
        )
    }
}
