package com.infomaniak.swisstransfer.ui.images.illus.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.radialGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.RadialGradientCornerTopLeftLight: ImageVector
    get() {
        if (_radialGradientCornerTopLeftLight != null) {
            return _radialGradientCornerTopLeftLight!!
        }
        _radialGradientCornerTopLeftLight = Builder(
            name = "RadialGradientCornerTopLeftLight",
            defaultWidth = 375.0.dp,
            defaultHeight = 832.0.dp,
            viewportWidth = 375.0f,
            viewportHeight = 832.0f
        ).apply {
            group {
                path(
                    fill = radialGradient(
                        0.45f to Color(0xFFE3F6DC), 1.0f to Color(0x00E3F6DC), center = Offset(-7.08f, 2.41f), radius = 179.03f
                    ),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(0.0f, 2.0f)
                    moveToRelative(-182.0f, 0.0f)
                    arcToRelative(182.0f, 182.0f, 0.0f, true, true, 364.0f, 0.0f)
                    arcToRelative(182.0f, 182.0f, 0.0f, true, true, -364.0f, 0.0f)
                }
            }
        }.build()
        return _radialGradientCornerTopLeftLight!!
    }

private var _radialGradientCornerTopLeftLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.wrapContentHeight()) {
        Image(
            imageVector = AppIllus.RadialGradientCornerTopLeftLight,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.wrapContentSize()
        )
    }
}
