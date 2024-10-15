package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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

val AppIcons.Chain: ImageVector
    get() {
        if (_chain != null) return _chain!!

        _chain = Builder(
            name = "Chain",
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
                    moveTo(9.365f, 19.127f)
                    lineToRelative(-0.932f, 0.932f)
                    arcToRelative(4.5f, 4.5f, 0.0f, false, true, -6.364f, -6.364f)
                    lineTo(6.842f, 8.92f)
                    arcToRelative(4.5f, 4.5f, 0.0f, false, true, 6.826f, 5.825f)
                }
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(14.82f, 6.194f)
                    lineToRelative(0.75f, -0.75f)
                    arcToRelative(4.5f, 4.5f, 0.0f, true, true, 6.363f, 6.364f)
                    lineTo(17.16f, 16.58f)
                    arcToRelative(4.5f, 4.5f, 0.0f, false, true, -6.824f, -5.826f)
                }
            }
        }.build()
        return _chain!!
    }

private var _chain: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Chain,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
