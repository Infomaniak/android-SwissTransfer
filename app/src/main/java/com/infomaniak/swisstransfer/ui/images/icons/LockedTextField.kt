package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

val AppIcons.LockedTextField: ImageVector
    get() {
        if (_lockedTextField != null) return _lockedTextField!!

        _lockedTextField = Builder(
            name = "LockedTextField",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 25.0f
        ).apply {
            group {
                path(fill = SolidColor(Color(0xFF9F9F9F)), pathFillType = EvenOdd) {
                    moveTo(16.63f, 14.858f)
                    curveToRelative(0.352f, -0.32f, 0.844f, -0.51f, 1.37f, -0.51f)
                    reflectiveCurveToRelative(1.018f, 0.19f, 1.37f, 0.51f)
                    curveToRelative(0.349f, 0.316f, 0.529f, 0.73f, 0.529f, 1.142f)
                    verticalLineToRelative(0.957f)
                    horizontalLineToRelative(-3.798f)
                    verticalLineTo(16.0f)
                    curveToRelative(0.0f, -0.413f, 0.18f, -0.826f, 0.53f, -1.142f)
                    moveToRelative(5.586f, 8.447f)
                    horizontalLineTo(23.0f)
                    close()
                    moveToRelative(0.784f, 0.003f)
                    verticalLineToRelative(-4.873f)
                    arcToRelative(1.44f, 1.44f, 0.0f, false, false, -0.271f, -0.865f)
                    arcToRelative(1.44f, 1.44f, 0.0f, false, false, -0.572f, -0.456f)
                    arcToRelative(1.8f, 1.8f, 0.0f, false, false, -0.675f, -0.157f)
                    horizontalLineToRelative(-0.015f)
                    verticalLineTo(16.0f)
                    curveToRelative(0.0f, -0.879f, -0.385f, -1.704f, -1.043f, -2.3f)
                    arcTo(3.6f, 3.6f, 0.0f, false, false, 18.0f, 12.782f)
                    curveToRelative(-0.897f, 0.0f, -1.77f, 0.322f, -2.424f, 0.916f)
                    arcTo(3.1f, 3.1f, 0.0f, false, false, 14.533f, 16.0f)
                    verticalLineToRelative(0.957f)
                    curveToRelative(-0.307f, 0.002f, -0.59f, 0.075f, -0.832f, 0.222f)
                    arcToRelative(1.4f, 1.4f, 0.0f, false, false, -0.508f, 0.53f)
                    arcToRelative(1.6f, 1.6f, 0.0f, false, false, -0.192f, 0.69f)
                    verticalLineToRelative(0.02f)
                    lineTo(13.0f, 18.428f)
                    verticalLineToRelative(4.877f)
                    curveToRelative(0.0f, 0.327f, 0.094f, 0.622f, 0.271f, 0.864f)
                    curveToRelative(0.172f, 0.234f, 0.39f, 0.374f, 0.572f, 0.457f)
                    arcToRelative(1.8f, 1.8f, 0.0f, false, false, 0.675f, 0.156f)
                    horizontalLineToRelative(6.932f)
                    curveToRelative(0.314f, 0.0f, 0.602f, -0.072f, 0.849f, -0.221f)
                    curveToRelative(0.245f, -0.149f, 0.406f, -0.346f, 0.508f, -0.532f)
                    arcToRelative(1.6f, 1.6f, 0.0f, false, false, 0.192f, -0.688f)
                    verticalLineToRelative(-0.02f)
                    lineTo(23.0f, 23.311f)
                    close()
                    moveToRelative(-4.25f, -3.13f)
                    arcToRelative(0.75f, 0.75f, 0.0f, true, false, -1.5f, 0.0f)
                    verticalLineToRelative(1.5f)
                    arcToRelative(0.75f, 0.75f, 0.0f, false, false, 1.5f, 0.0f)
                    close()
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
                    moveTo(11.605f, 16.151f)
                    horizontalLineTo(2.347f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, -1.5f)
                    verticalLineToRelative(-9.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, -1.5f)
                    horizontalLineToRelative(19.306f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, 1.5f)
                    verticalLineToRelative(7.636f)
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
                    moveTo(5.347f, 9.776f)
                    arcToRelative(0.375f, 0.375f, 0.0f, true, true, 0.0f, 0.75f)
                    arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, -0.75f)
                    moveToRelative(6.0f, 0.0f)
                    arcToRelative(0.375f, 0.375f, 0.0f, true, true, 0.0f, 0.75f)
                    arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, -0.75f)
                    moveToRelative(6.0f, 0.0f)
                    arcToRelative(0.375f, 0.375f, 0.0f, true, true, 0.0f, 0.75f)
                    arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, -0.75f)
                }
            }
        }.build()
        return _lockedTextField!!
    }

private var _lockedTextField: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.LockedTextField,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
