package com.infomaniak.swisstransfer.ui.icons.app

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
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.OpenOutside: ImageVector
    get() {
        if (_openOutside != null) {
            return _openOutside!!
        }
        _openOutside = Builder(
            name = "OpenOutside",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 2.0f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(15.0f, 5.2f)
                    verticalLineTo(1.0f)
                    horizontalLineToRelative(-4.2f)
                    moveTo(15.0f, 1.0f)
                    lineToRelative(-9.333f, 9.333f)
                    moveTo(7.533f, 3.8f)
                    horizontalLineToRelative(-5.6f)
                    arcTo(0.933f, 0.933f, 0.0f, false, false, 1.0f, 4.733f)
                    verticalLineToRelative(9.334f)
                    arcTo(0.933f, 0.933f, 0.0f, false, false, 1.933f, 15.0f)
                    horizontalLineToRelative(9.334f)
                    arcToRelative(0.933f, 0.933f, 0.0f, false, false, 0.933f, -0.933f)
                    verticalLineToRelative(-5.6f)
                }
            }
        }.build()
        return _openOutside!!
    }

private var _openOutside: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.OpenOutside,
            contentDescription = null,
        modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
