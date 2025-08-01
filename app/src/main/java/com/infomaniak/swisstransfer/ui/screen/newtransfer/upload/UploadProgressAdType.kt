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

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.utils.TextUtils

enum class UploadProgressAdType(
    @StringRes private val descriptionTemplate: Int,
    @StringRes private val descriptionAccentuatedPart: Int,
    @RawRes val illustration: Int,
) {
    INDEPENDENCE(
        R.string.uploadProgressDescriptionTemplateIndependence,
        R.string.uploadProgressDescriptionArgumentIndependence,
        R.raw.swiss_with_flag,
    ),
    ENERGY(
        R.string.uploadProgressDescriptionTemplateEnergy,
        R.string.uploadProgressDescriptionArgumentEnergy,
        R.raw.mountain_gondola,
    );

    @Composable
    fun description(): AnnotatedString {
        return TextUtils.assembleWithBoldArgument(stringResource(descriptionTemplate), stringResource(descriptionAccentuatedPart))
    }
}
