package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.LockedTextFieldSmall: ImageVector
    get() {
        if (_lockedTextFieldSmall != null) {
            return _lockedTextFieldSmall!!
        }
        _lockedTextFieldSmall = Builder(
            name = "LockedTextFieldSmall",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f,
        ).apply {
            path(fill = SolidColor(Color(0xFF3CB572)), pathFillType = EvenOdd) {
                moveTo(1.21f, 2.892f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.354f, -0.146f)
                horizontalLineToRelative(12.858f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(5.066f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, false, 1.0f, 0.0f)
                verticalLineTo(3.245f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, false, -1.5f, -1.5f)
                horizontalLineTo(1.564f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, false, -1.5f, 1.5f)
                verticalLineToRelative(6.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, false, 1.5f, 1.5f)
                horizontalLineTo(7.75f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.0f, -1.0f)
                horizontalLineTo(1.564f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, -0.5f, -0.5f)
                verticalLineToRelative(-6.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.147f, -0.353f)
                moveToRelative(2.355f, 2.604f)
                arcToRelative(0.75f, 0.75f, 0.0f, true, false, -0.003f, 1.498f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, false, 0.003f, -1.498f)
                moveToRelative(3.713f, 0.056f)
                arcToRelative(0.75f, 0.75f, 0.0f, true, true, 0.572f, 1.386f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, -0.572f, -1.386f)
                moveToRelative(4.287f, -0.056f)
                arcToRelative(0.749f, 0.749f, 0.0f, false, false, -0.288f, 1.443f)
                arcToRelative(0.75f, 0.75f, 0.0f, true, false, 0.288f, -1.443f)
                moveTo(12.0f, 9.043f)
                curveToRelative(-0.351f, 0.0f, -0.679f, 0.127f, -0.913f, 0.34f)
                arcToRelative(1.03f, 1.03f, 0.0f, false, false, -0.353f, 0.762f)
                verticalLineToRelative(0.637f)
                horizontalLineToRelative(2.532f)
                verticalLineToRelative(-0.637f)
                curveToRelative(0.0f, -0.276f, -0.12f, -0.551f, -0.353f, -0.762f)
                arcTo(1.36f, 1.36f, 0.0f, false, false, 12.0f, 9.043f)
                moveToRelative(2.81f, 5.971f)
                horizontalLineToRelative(0.523f)
                close()
                moveToRelative(0.523f, 0.0f)
                verticalLineToRelative(0.024f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.016f, 0.143f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.112f, 0.316f)
                arcToRelative(0.94f, 0.94f, 0.0f, false, true, -0.34f, 0.355f)
                arcTo(1.1f, 1.1f, 0.0f, false, true, 14.3f, 16.0f)
                horizontalLineTo(9.679f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.137f, -0.014f)
                arcToRelative(1.2f, 1.2f, 0.0f, false, true, -0.313f, -0.091f)
                arcToRelative(0.96f, 0.96f, 0.0f, false, true, -0.381f, -0.304f)
                arcToRelative(0.96f, 0.96f, 0.0f, false, true, -0.181f, -0.577f)
                verticalLineToRelative(-3.271f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 0.016f, -0.142f)
                curveToRelative(0.015f, -0.081f, 0.046f, -0.196f, 0.112f, -0.317f)
                arcToRelative(0.94f, 0.94f, 0.0f, false, true, 0.339f, -0.354f)
                curveToRelative(0.161f, -0.098f, 0.35f, -0.146f, 0.555f, -0.148f)
                verticalLineToRelative(-0.637f)
                curveToRelative(0.0f, -0.586f, 0.257f, -1.137f, 0.695f, -1.534f)
                arcTo(2.4f, 2.4f, 0.0f, false, true, 12.0f, 8.0f)
                curveToRelative(0.598f, 0.0f, 1.18f, 0.215f, 1.616f, 0.61f)
                curveToRelative(0.438f, 0.398f, 0.695f, 0.949f, 0.695f, 1.535f)
                verticalLineToRelative(0.637f)
                horizontalLineToRelative(0.01f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 0.137f, 0.013f)
                curveToRelative(0.079f, 0.012f, 0.192f, 0.037f, 0.313f, 0.092f)
                arcToRelative(0.96f, 0.96f, 0.0f, false, true, 0.381f, 0.304f)
                arcToRelative(0.96f, 0.96f, 0.0f, false, true, 0.181f, 0.577f)
                close()
                moveTo(12.0f, 12.43f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(1.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, true, -1.0f, 0.0f)
                verticalLineToRelative(-1.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.5f, -0.5f)
            }
        }.build()
        return _lockedTextFieldSmall!!
    }

private var _lockedTextFieldSmall: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.LockedTextFieldSmall,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
