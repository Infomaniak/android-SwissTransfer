package com.infomaniak.swisstransfer.ui.icons.illu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.infomaniak.swisstransfer.ui.icons.AppIcons

val AppIcons.GermanFlag: ImageVector
    get() {
        if (_germanFlag != null) {
            return _germanFlag!!
        }
        _germanFlag = Builder(
            name = "GermanFlag",
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
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(0.0f, 3.0f)
                    horizontalLineToRelative(24.0f)
                    verticalLineToRelative(6.0f)
                    horizontalLineTo(0.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFDDA25)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(0.0f, 15.667f)
                    horizontalLineToRelative(24.0f)
                    verticalLineToRelative(6.0f)
                    horizontalLineTo(0.0f)
                    close()
                }
            }
        }
            .build()
        return _germanFlag!!
    }

private var _germanFlag: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.GermanFlag, contentDescription = "")
    }
}
