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
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.ThemedImage
import com.infomaniak.swisstransfer.ui.images.illus.uploadAd.MetallicSafe
import com.infomaniak.swisstransfer.ui.images.illus.uploadAd.MountainGondola
import com.infomaniak.swisstransfer.ui.images.illus.uploadAd.SwissWithFlag

enum class UploadProgressAdType(
    @StringRes private val descriptionTemplate: Int,
    @StringRes private val descriptionAccentuatedPart: Int,
    val illustration: ThemedImage,
) {
    INDEPENDENCE(
        R.string.uploadProgressDescriptionTemplateIndependence,
        R.string.uploadProgressDescriptionArgumentIndependence,
        AppImages.AppIllus.SwissWithFlag,
    ),
    ENERGY(
        R.string.uploadProgressDescriptionTemplateEnergy,
        R.string.uploadProgressDescriptionArgumentEnergy,
        AppImages.AppIllus.MountainGondola,
    ),
    CONFIDENTIALITY(
        R.string.uploadProgressDescriptionTemplateConfidentiality,
        R.string.uploadProgressDescriptionArgumentConfidentiality,
        AppImages.AppIllus.MetallicSafe,
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
