package com.infomaniak.swisstransfer.ui.images.icons

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
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.Eye: ImageVector
    get() {
        if (_eye != null) return _eye!!

        _eye = Builder(
            name = "Eye",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 25.0f,
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(12.002f, 5.878f)
                curveTo(7.97f, 5.81f, 3.802f, 8.627f, 1.18f, 11.512f)
                arcToRelative(1.663f, 1.663f, 0.0f, false, false, 0.0f, 2.226f)
                curveTo(3.745f, 16.562f, 7.9f, 19.444f, 12.0f, 19.375f)
                curveToRelative(4.1f, 0.069f, 8.259f, -2.813f, 10.825f, -5.637f)
                arcToRelative(1.663f, 1.663f, 0.0f, false, false, 0.0f, -2.226f)
                curveTo(20.2f, 8.627f, 16.032f, 5.81f, 12.002f, 5.878f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(15.752f, 12.627f)
                arcToRelative(3.75f, 3.75f, 0.0f, true, true, -7.5f, -0.002f)
                arcToRelative(3.75f, 3.75f, 0.0f, false, true, 7.5f, 0.002f)
            }
        }.build()
        return _eye!!
    }

private var _eye: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Eye, contentDescription = null, modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
