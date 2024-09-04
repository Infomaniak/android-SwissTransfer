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

val AppIcons.SpeechBubble: ImageVector
    get() {

        if (_speechBubble != null) return _speechBubble!!

        _speechBubble = Builder(
            name = "SpeechBubble",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(21.75f, 18.75f)
                    horizontalLineToRelative(-10.5f)
                    lineToRelative(-6.0f, 4.5f)
                    verticalLineToRelative(-4.5f)
                    horizontalLineToRelative(-3.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, -1.5f)
                    verticalLineToRelative(-15.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, -1.5f)
                    horizontalLineToRelative(19.5f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, 1.5f)
                    verticalLineToRelative(15.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, 1.5f)
                    moveTo(5.25f, 7.5f)
                    horizontalLineToRelative(13.5f)
                    moveTo(5.25f, 12.0f)
                    horizontalLineToRelative(10.5f)
                }
            }
        }.build()

        return _speechBubble!!
    }

private var _speechBubble: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.SpeechBubble,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize),
        )
    }
}
