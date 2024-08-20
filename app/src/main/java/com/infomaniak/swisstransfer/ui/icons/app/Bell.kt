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
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.Bell: ImageVector
    get() {
        if (_notificationBell != null) {
            return _notificationBell!!
        }
        _notificationBell = Builder(
            name = "Bell",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(9.0f, 20.0f)
                curveToRelative(0.19f, 0.866f, 0.585f, 1.626f, 1.126f, 2.167f)
                reflectiveCurveTo(11.324f, 23.0f, 12.0f, 23.0f)
                reflectiveCurveToRelative(1.333f, -0.292f, 1.874f, -0.833f)
                curveTo(14.414f, 21.627f, 14.81f, 20.866f, 15.0f, 20.0f)
                moveTo(12.0f, 4.0f)
                verticalLineTo(1.0f)
                moveToRelative(0.0f, 3.0f)
                arcToRelative(7.5f, 7.5f, 0.0f, false, true, 7.5f, 7.5f)
                curveToRelative(0.0f, 7.046f, 1.5f, 8.25f, 1.5f, 8.25f)
                horizontalLineTo(3.0f)
                reflectiveCurveToRelative(1.5f, -1.916f, 1.5f, -8.25f)
                arcTo(7.5f, 7.5f, 0.0f, false, true, 12.0f, 4.0f)
            }
        }.build()
        return _notificationBell!!
    }

private var _notificationBell: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Bell,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
