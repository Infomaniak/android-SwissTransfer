package com.infomaniak.swisstransfer.ui.images.icons

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
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.AddThick: ImageVector
    get() {
        if (_addThick != null) {
            return _addThick!!
        }
        _addThick = Builder(
            name = "AddThick",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
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
                    moveTo(1.0f, 8.0f)
                    horizontalLineToRelative(14.0f)
                    moveTo(8.0f, 1.0f)
                    verticalLineToRelative(14.0f)
                }
            }
        }.build()
        return _addThick!!
    }

private var _addThick: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.AddThick,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
