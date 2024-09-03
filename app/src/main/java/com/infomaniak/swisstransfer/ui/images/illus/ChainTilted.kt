package com.infomaniak.swisstransfer.ui.images.illus

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
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIllus.ChainTilted: ImageVector
    get() {

        if (_chainTilted != null) return _chainTilted!!

        _chainTilted = Builder(
            name = "ChainTilted",
            defaultWidth = 120.0.dp,
            defaultHeight = 80.0.dp,
            viewportWidth = 120.0f,
            viewportHeight = 80.0f,
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF3CB572)),
                strokeLineWidth = 3.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(47.17f, 75.82f)
                lineToRelative(-4.56f, 4.56f)
                arcToRelative(22.03f, 22.03f, 0.0f, true, true, -31.16f, -31.15f)
                lineToRelative(23.37f, -23.37f)
                arcToRelative(22.03f, 22.03f, 0.0f, false, true, 33.41f, 28.52f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF3CB572)),
                strokeLineWidth = 3.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(73.88f, 12.5f)
                lineToRelative(3.67f, -3.67f)
                arcToRelative(22.03f, 22.03f, 0.0f, false, true, 31.16f, 31.16f)
                lineTo(85.34f, 63.36f)
                arcToRelative(22.03f, 22.03f, 0.0f, false, true, -33.41f, -28.52f)
            }
        }.build()

        return _chainTilted!!
    }

private var _chainTilted: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.ChainTilted,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
