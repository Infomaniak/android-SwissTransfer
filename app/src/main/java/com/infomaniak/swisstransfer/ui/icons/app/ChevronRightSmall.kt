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

val AppIcons.ChevronRightSmall: ImageVector
    get() {
        if (_chevronRightSmall != null) {
            return _chevronRightSmall!!
        }
        _chevronRightSmall = Builder(
            name = "ChevronRightSmall",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 2.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(4.0f, 1.0f)
                lineToRelative(7.338f, 6.67f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.111f, 0.151f)
                arcToRelative(0.43f, 0.43f, 0.0f, false, true, -0.111f, 0.509f)
                lineTo(4.0f, 15.0f)
            }
        }.build()
        return _chevronRightSmall!!
    }

private var _chevronRightSmall: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.ChevronRightSmall,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
