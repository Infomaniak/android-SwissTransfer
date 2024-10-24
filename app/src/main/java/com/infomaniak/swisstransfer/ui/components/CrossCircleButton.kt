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

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.CrossThick
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BoxScope.CrossCircleButton(onClick: (() -> Unit)?, size: Dp = 48.dp) {
    val buttonPadding = ((size - 24.dp) / 2f).coerceAtLeast(0.dp)

    CompositionLocalProvider(LocalRippleConfiguration provides RippleConfiguration(color = Color.White)) {
        Button(
            modifier = Modifier
                .size(size)
                .padding(buttonPadding)
                .align(Alignment.TopEnd),
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = SwissTransferTheme.colors.fileItemRemoveButtonBackground),
            onClick = onClick ?: {},
        ) {
            Icon(
                modifier = Modifier.size(Margin.Mini),
                imageVector = AppImages.AppIcons.CrossThick,
                contentDescription = stringResource(R.string.contentDescriptionButtonRemove),
                tint = Color.White,
            )
        }
    }
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun CrossCircleButtonPreview() {
    SwissTransferTheme {
        Surface {
            Box {
                CrossCircleButton({})
            }
        }
    }
}
