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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.DoorRectangleArrowRight: ImageVector
    get() {
        if (_doorRectangleArrowRight != null) {
            return _doorRectangleArrowRight!!
        }
        _doorRectangleArrowRight = Builder(
            name = "DoorRectangleArrowRight",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(7.5f, 12.004f)
                    horizontalLineTo(23.25f)
                }
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(19.5f, 15.754f)
                    lineTo(23.25f, 12.004f)
                    lineTo(19.5f, 8.254f)
                }
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(15.75f, 16.5f)
                    verticalLineTo(21.0f)
                    curveTo(15.767f, 21.38f, 15.633f, 21.75f, 15.378f, 22.032f)
                    curveTo(15.122f, 22.313f, 14.766f, 22.481f, 14.386f, 22.5f)
                    horizontalLineTo(2.113f)
                    curveTo(1.734f, 22.481f, 1.377f, 22.312f, 1.122f, 22.031f)
                    curveTo(0.867f, 21.75f, 0.733f, 21.379f, 0.75f, 21.0f)
                    verticalLineTo(3.0f)
                    curveTo(0.733f, 2.621f, 0.866f, 2.25f, 1.122f, 1.969f)
                    curveTo(1.377f, 1.687f, 1.734f, 1.519f, 2.113f, 1.5f)
                    horizontalLineTo(14.386f)
                    curveTo(14.766f, 1.519f, 15.122f, 1.687f, 15.378f, 1.968f)
                    curveTo(15.633f, 2.25f, 15.767f, 2.62f, 15.75f, 3.0f)
                    verticalLineTo(7.5f)
                }
            }
        }.build()
        return _doorRectangleArrowRight!!
    }

private var _doorRectangleArrowRight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.DoorRectangleArrowRight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
