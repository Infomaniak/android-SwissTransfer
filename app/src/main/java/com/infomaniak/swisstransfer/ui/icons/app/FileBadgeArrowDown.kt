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

val AppIcons.FileBadgeArrowDown: ImageVector
    get() {
        if (_fileBadgeArrowDown != null) {
            return _fileBadgeArrowDown!!
        }
        _fileBadgeArrowDown = Builder(
            name = "FileBadgeArrowDown",
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
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(11.25f, 17.25f)
                    arcToRelative(6.0f, 6.0f, 0.0f, true, false, 12.0f, 0.0f)
                    arcToRelative(6.0f, 6.0f, 0.0f, false, false, -12.0f, 0.0f)
                    moveToRelative(6.0f, -3.0f)
                    verticalLineToRelative(6.0f)
                    moveToRelative(0.0f, 0.0f)
                    lineTo(15.0f, 18.0f)
                    moveToRelative(2.25f, 2.25f)
                    lineTo(19.5f, 18.0f)
                }
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(8.25f, 20.25f)
                    horizontalLineToRelative(-6.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, -1.5f)
                    verticalLineTo(2.25f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, -1.5f)
                    horizontalLineToRelative(10.629f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.06f, 0.439f)
                    lineToRelative(2.872f, 2.872f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 0.439f, 1.06f)
                    verticalLineTo(8.25f)
                }
            }
        }.build()
        return _fileBadgeArrowDown!!
    }

private var _fileBadgeArrowDown: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.FileBadgeArrowDown,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
