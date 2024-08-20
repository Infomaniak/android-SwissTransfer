package com.infomaniak.swisstransfer.ui.icons.illu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
                stroke = SolidColor(Color(0xFF3cb572)),
                strokeLineWidth = 2.77778f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(15.55f, 56.31f)
                lineToRelative(14.43f, -8.34f)
                lineToRelative(-8.33f, -14.43f)
                lineToRelative(-14.44f, 8.33f)
                lineToRelative(8.34f, 14.44f)
                close()
                moveToRelative(79.68f, 21.34f)
                lineToRelative(14.43f, -8.33f)
                lineToRelative(-8.33f, -14.43f)
                lineToRelative(-14.43f, 8.33f)
                lineToRelative(8.33f, 14.43f)
                close()
                moveToRelative(-71.35f, -6.91f)
                lineToRelative(28.87f, -16.67f)
                lineToRelative(-4.17f, -7.22f)
                moveToRelative(14.43f, -8.33f)
                lineToRelative(-12.5f, -21.65f)
                lineToRelative(21.65f, -12.5f)
                lineToRelative(12.5f, 21.65f)
                lineToRelative(-7.21f, 4.17f)
                moveToRelative(-37.2f, 2.23f)
                lineToRelative(-4.17f, -7.22f)
                moveToRelative(37.5f, 64.95f)
                lineTo(61.08f, 68.5f)
                lineToRelative(7.22f, -4.16f)
                moveToRelative(10.27f, -15.55f)
                lineTo(93.0f, 40.46f)
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
