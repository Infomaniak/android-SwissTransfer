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
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.EyeSmall: ImageVector
    get() {
        if (_eyeSmall != null) return _eyeSmall!!

        _eyeSmall = Builder(
            name = "EyeSmall",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f,
        ).apply {
            path(fill = SolidColor(Color(0xFF666666)), pathFillType = NonZero) {
                moveTo(9.147f, 5.56f)
                arcTo(3.0f, 3.0f, 0.0f, false, false, 8.0f, 5.333f)
                lineToRelative(3.0f, 3.001f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, false, -1.852f, -2.772f)
            }
            path(fill = SolidColor(Color(0xFF666666)), pathFillType = EvenOdd) {
                moveTo(6.332f, 5.838f)
                arcTo(3.0f, 3.0f, 0.0f, false, true, 8.0f, 5.332f)
                lineToRelative(3.0f, 3.001f)
                arcToRelative(3.0f, 3.0f, 0.0f, true, true, -4.667f, -2.495f)
                moveTo(8.0f, 6.332f)
                arcToRelative(2.0f, 2.0f, 0.0f, true, true, 0.001f, 4.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, true, -0.001f, -4.0f)
            }
            path(fill = SolidColor(Color(0xFF666666))) {
                moveTo(15.585f, 7.253f)
                curveToRelative(-1.787f, -1.964f, -4.69f, -3.964f, -7.586f, -3.92f)
                curveToRelative(-2.896f, -0.044f, -5.8f, 1.956f, -7.584f, 3.92f)
                lineTo(0.413f, 7.255f)
                arcToRelative(1.61f, 1.61f, 0.0f, false, false, 0.0f, 2.153f)
                lineTo(0.415f, 9.41f)
                curveToRelative(1.744f, 1.921f, 4.64f, 3.967f, 7.584f, 3.922f)
                curveToRelative(2.944f, 0.045f, 5.84f, -2.0f, 7.586f, -3.922f)
                lineToRelative(0.001f, -0.002f)
                arcToRelative(1.61f, 1.61f, 0.0f, false, false, 0.0f, -2.153f)
                close()
                moveTo(1.0f, 8.332f)
                curveToRelative(0.0f, -0.15f, 0.055f, -0.295f, 0.156f, -0.407f)
                curveToRelative(1.708f, -1.88f, 4.36f, -3.633f, 6.834f, -3.591f)
                horizontalLineToRelative(0.017f)
                curveToRelative(2.476f, -0.042f, 5.127f, 1.711f, 6.837f, 3.591f)
                arcToRelative(0.61f, 0.61f, 0.0f, false, true, 0.0f, 0.813f)
                curveToRelative(-1.674f, 1.842f, -4.317f, 3.636f, -6.837f, 3.594f)
                horizontalLineTo(7.991f)
                curveToRelative(-2.52f, 0.042f, -5.163f, -1.752f, -6.835f, -3.593f)
                arcTo(0.6f, 0.6f, 0.0f, false, true, 1.0f, 8.332f)
            }
        }.build()
        return _eyeSmall!!
    }

private var _eyeSmall: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.EyeSmall,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
