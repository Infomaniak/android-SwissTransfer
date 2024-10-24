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

val AppIcons.EyeCrossedOutSmall: ImageVector
    get() {
        if (_eyeCrossedOutSmall != null) return _eyeCrossedOutSmall!!

        _eyeCrossedOutSmall = Builder(
            name = "InformationCircleCopy",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f,
        ).apply {
            path(fill = SolidColor(Color(0xFF666666)), pathFillType = NonZero) {
                moveTo(14.715f, 1.154f)
                arcTo(0.5f, 0.5f, 0.0f, false, true, 14.7f, 1.861f)
                lineToRelative(-12.5f, 12.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, -0.692f, -0.722f)
                lineToRelative(12.5f, -12.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.707f, 0.015f)
                moveToRelative(-1.953f, 3.971f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.698f, -0.109f)
                curveToRelative(0.77f, 0.562f, 1.482f, 1.2f, 2.124f, 1.905f)
                lineToRelative(0.002f, 0.002f)
                arcToRelative(1.61f, 1.61f, 0.0f, false, true, 0.0f, 2.154f)
                lineToRelative(-0.001f, 0.001f)
                curveTo(13.839f, 11.0f, 10.941f, 13.047f, 7.997f, 13.0f)
                arcToRelative(7.2f, 7.2f, 0.0f, false, true, -2.17f, -0.322f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.296f, -0.955f)
                arcTo(6.2f, 6.2f, 0.0f, false, false, 7.995f, 12.0f)
                horizontalLineToRelative(0.012f)
                curveToRelative(2.52f, 0.043f, 5.163f, -1.752f, 6.837f, -3.593f)
                arcToRelative(0.61f, 0.61f, 0.0f, false, false, 0.0f, -0.813f)
                arcToRelative(13.2f, 13.2f, 0.0f, false, false, -1.973f, -1.77f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, -0.11f, -0.699f)
                moveTo(9.728f, 3.203f)
                arcTo(6.9f, 6.9f, 0.0f, false, false, 8.0f, 3.0f)
                curveTo(5.104f, 2.955f, 2.2f, 4.957f, 0.415f, 6.92f)
                lineTo(0.413f, 6.922f)
                arcToRelative(1.61f, 1.61f, 0.0f, false, false, 0.0f, 2.153f)
                lineToRelative(0.003f, 0.003f)
                arcToRelative(14.4f, 14.4f, 0.0f, false, false, 1.796f, 1.658f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.613f, -0.79f)
                quadToRelative(-0.901f, -0.7f, -1.67f, -1.541f)
                arcToRelative(0.61f, 0.61f, 0.0f, false, true, 0.0f, -0.813f)
                curveTo(2.866f, 5.713f, 5.516f, 3.96f, 7.992f, 4.0f)
                horizontalLineToRelative(0.014f)
                curveToRelative(0.499f, -0.006f, 0.997f, 0.053f, 1.482f, 0.173f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.241f, -0.97f)
            }
            path(fill = SolidColor(Color(0xFF666666)), pathFillType = NonZero) {
                moveTo(7.999f, 6.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, -2.0f, 2.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, true, -1.0f, 0.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, true, 3.0f, -3.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, true, 0.0f, 1.0f)
                moveToRelative(3.0f, 2.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -1.0f, 0.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, true, -2.0f, 2.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.0f, 1.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, false, 3.0f, -3.0f)
            }
        }.build()
        return _eyeCrossedOutSmall!!
    }

private var _eyeCrossedOutSmall: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.EyeCrossedOutSmall,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
