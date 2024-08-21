package com.infomaniak.swisstransfer.ui.icons.illu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound


val AppIcons.Illu.QrCodeTilted: ImageVector
    get() {
        if (_qrCodeTilted != null) {
            return _qrCodeTilted!!
        }
        _qrCodeTilted = Builder(
            name = "QrCodeTilted",
            defaultWidth = 120.0.dp,
            defaultHeight = 80.0.dp,
            viewportWidth = 120.0f,
            viewportHeight = 80.0f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF3CB572)),
                strokeLineWidth = 3.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(15.56f, 56.33f)
                lineTo(29.99f, 48.0f)
                lineToRelative(-8.33f, -14.43f)
                lineTo(7.23f, 41.9f)
                close()
                moveToRelative(79.69f, 21.35f)
                lineToRelative(14.43f, -8.33f)
                lineToRelative(-8.33f, -14.43f)
                lineToRelative(-14.43f, 8.33f)
                close()
                moveTo(23.9f, 70.76f)
                lineToRelative(28.86f, -16.67f)
                lineToRelative(-4.17f, -7.22f)
                moveToRelative(14.44f, -8.33f)
                lineToRelative(-12.5f, -21.65f)
                lineToRelative(21.65f, -12.5f)
                lineToRelative(12.5f, 21.65f)
                lineToRelative(-7.22f, 4.17f)
                moveToRelative(-37.2f, 2.23f)
                lineToRelative(-4.17f, -7.22f)
                moveTo(73.6f, 90.18f)
                lineTo(61.1f, 68.53f)
                lineToRelative(7.22f, -4.17f)
                moveToRelative(10.26f, -15.55f)
                lineToRelative(14.43f, -8.33f)
            }
        }.build()
        return _qrCodeTilted!!
    }

private var _qrCodeTilted: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Illu.QrCodeTilted,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
