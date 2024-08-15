package com.infomaniak.swisstransfer.ui.icons.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons

val AppIcons.Add: ImageVector
    get() {
        if (_add != null) {
            return _add!!
        }
        _add = Builder(
            name = "Add",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 2.0f,
                    strokeLineCap = Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(1.5f, 12.0f)
                    horizontalLineToRelative(21.0f)
                    moveTo(12.0f, 1.5f)
                    verticalLineToRelative(21.0f)
                }
            }
        }.build()
        return _add!!
    }

private var _add: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Add,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
