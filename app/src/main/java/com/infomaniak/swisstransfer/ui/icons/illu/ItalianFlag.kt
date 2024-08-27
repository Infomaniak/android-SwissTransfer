package com.infomaniak.swisstransfer.ui.icons.illu

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
import androidx.core.graphics.translationMatrix
import com.infomaniak.swisstransfer.ui.icons.AppIcons

val AppIcons.ItalianFlag: ImageVector
    get() {
        if (_italianFlag != null) {
            return _italianFlag!!
        }
        _italianFlag = Builder(
            name = "ItalianFlag",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFFE70E0E)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(1.333f, 3.0f)
                    horizontalLineToRelative(21.334f)
                    arcTo(1.333f, 1.333f, 0.0f, false, true, 24.0f, 4.333f)
                    verticalLineToRelative(15.334f)
                    arcTo(1.333f, 1.333f, 0.0f, false, true, 22.667f, 21.0f)
                    horizontalLineTo(1.333f)
                    arcTo(1.333f, 1.333f, 0.0f, false, true, 0.0f, 19.667f)
                    verticalLineTo(4.333f)
                    arcTo(1.333f, 1.333f, 0.0f, false, true, 1.333f, 3.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF128D4E)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(0.0f, 4.333f)
                    curveTo(0.0f, 3.597f, 0.597f, 3.0f, 1.333f, 3.0f)
                    horizontalLineTo(8.0f)
                    verticalLineToRelative(18.0f)
                    horizontalLineTo(1.333f)
                    arcTo(1.333f, 1.333f, 0.0f, false, true, 0.0f, 19.6f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(8.0f, 3.0f)
                    horizontalLineToRelative(8.0f)
                    verticalLineToRelative(18.0f)
                    horizontalLineTo(8.0f)
                    close()
                }
            }
        }
            .build()
        return _italianFlag!!
    }

private var _italianFlag: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(
            imageVector = AppIcons.ItalianFlag,
            contentDescription = "",
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
