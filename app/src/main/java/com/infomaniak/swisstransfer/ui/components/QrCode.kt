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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.qrInfomaniak.QrInfomaniak
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import io.github.alexzhirkevich.qrose.options.QrBrush
import io.github.alexzhirkevich.qrose.options.QrLogoPadding
import io.github.alexzhirkevich.qrose.options.solid
import io.github.alexzhirkevich.qrose.rememberQrCodePainter

@Composable
fun QrCode(transferUrl: String) {

    val centralIcon = rememberVectorPainter(AppIcons.QrInfomaniak.image())
    val darkPixelsColor = QrBrush.solid(SwissTransferTheme.colors.qrCodeDarkPixels)
    val lightPixelsColor = QrBrush.solid(SwissTransferTheme.colors.qrCodeLightPixels)
    val painter = rememberQrCodePainter(transferUrl) {
        colors {
            dark = darkPixelsColor
            light = lightPixelsColor
        }
        logo {
            painter = centralIcon
            size = 0.25f // 25%: Icon size in fraction relative to the QR Code size.
            padding = QrLogoPadding.Natural(size = 0.13f) // 13%: Padding size in fraction relative to the Icon size.
        }
    }

    Image(
        modifier = Modifier.size(160.dp),
        painter = painter,
        contentDescription = null,
    )
}

@PreviewLightAndDark
@Composable
private fun QrCodePreview() {
    SwissTransferTheme {
        Surface {
            QrCode(transferUrl = "https://www.swisstransfer.com/d/b311318d-0790-4618-83d5-3ae0e407727c")
        }
    }
}
