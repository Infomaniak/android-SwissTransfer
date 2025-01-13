package com.infomaniak.core2.filetypes.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.infomaniak.core2.filetypes.FileTypeIcons

internal val FileTypeIcons.Folder: ImageVector
    get() {
        if (_folder != null) {
            return _folder!!
        }
        _folder = Builder(
            name = "Folder",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF9F9F9F)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(0.659f, 2.159f)
                curveTo(1.081f, 1.737f, 1.653f, 1.5f, 2.25f, 1.5f)
                horizontalLineTo(7.5f)
                curveTo(7.849f, 1.5f, 8.194f, 1.581f, 8.506f, 1.738f)
                curveTo(8.819f, 1.894f, 9.09f, 2.121f, 9.3f, 2.4f)
                lineTo(10.875f, 4.5f)
                horizontalLineTo(21.75f)
                curveTo(22.347f, 4.5f, 22.919f, 4.737f, 23.341f, 5.159f)
                curveTo(23.763f, 5.581f, 24.0f, 6.153f, 24.0f, 6.75f)
                verticalLineTo(20.25f)
                curveTo(24.0f, 20.847f, 23.763f, 21.419f, 23.341f, 21.841f)
                curveTo(22.919f, 22.263f, 22.347f, 22.5f, 21.75f, 22.5f)
                horizontalLineTo(2.25f)
                curveTo(1.653f, 22.5f, 1.081f, 22.263f, 0.659f, 21.841f)
                curveTo(0.237f, 21.419f, 0.0f, 20.847f, 0.0f, 20.25f)
                verticalLineTo(3.75f)
                curveTo(0.0f, 3.153f, 0.237f, 2.581f, 0.659f, 2.159f)
                close()
                moveTo(2.25f, 3.0f)
                curveTo(2.051f, 3.0f, 1.86f, 3.079f, 1.72f, 3.22f)
                curveTo(1.579f, 3.36f, 1.5f, 3.551f, 1.5f, 3.75f)
                verticalLineTo(20.25f)
                curveTo(1.5f, 20.449f, 1.579f, 20.64f, 1.72f, 20.78f)
                curveTo(1.86f, 20.921f, 2.051f, 21.0f, 2.25f, 21.0f)
                horizontalLineTo(21.75f)
                curveTo(21.949f, 21.0f, 22.14f, 20.921f, 22.28f, 20.78f)
                curveTo(22.421f, 20.64f, 22.5f, 20.449f, 22.5f, 20.25f)
                verticalLineTo(6.75f)
                curveTo(22.5f, 6.551f, 22.421f, 6.36f, 22.28f, 6.22f)
                curveTo(22.14f, 6.079f, 21.949f, 6.0f, 21.75f, 6.0f)
                horizontalLineTo(10.5f)
                curveTo(10.264f, 6.0f, 10.042f, 5.889f, 9.9f, 5.7f)
                lineTo(8.1f, 3.3f)
                curveTo(8.03f, 3.207f, 7.94f, 3.131f, 7.835f, 3.079f)
                curveTo(7.731f, 3.027f, 7.616f, 3.0f, 7.5f, 3.0f)
                horizontalLineTo(2.25f)
                close()
            }
        }
            .build()
        return _folder!!
    }

private var _folder: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = FileTypeIcons.Folder, contentDescription = null)
    }
}
