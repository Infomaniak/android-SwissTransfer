package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.Shield: ImageVector
    get() {
        if (_shield != null) {
            return _shield!!
        }
        _shield = Builder(
            name = "Shield",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(2.25f, 3.924f)
                    verticalLineToRelative(7.614f)
                    arcTo(11.91f, 11.91f, 0.0f, false, false, 9.882f, 22.65f)
                    lineToRelative(1.041f, 0.4f)
                    arcToRelative(3.0f, 3.0f, 0.0f, false, false, 2.154f, 0.0f)
                    lineToRelative(1.041f, -0.4f)
                    arcToRelative(11.91f, 11.91f, 0.0f, false, false, 7.632f, -11.113f)
                    verticalLineTo(3.924f)
                    arcToRelative(1.49f, 1.49f, 0.0f, false, false, -0.868f, -1.362f)
                    arcTo(21.7f, 21.7f, 0.0f, false, false, 12.0f, 0.75f)
                    arcToRelative(21.7f, 21.7f, 0.0f, false, false, -8.882f, 1.81f)
                    arcTo(1.49f, 1.49f, 0.0f, false, false, 2.25f, 3.923f)
                    close()
                }
            }
        }.build()
        return _shield!!
    }

private var _shield: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Shield,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
