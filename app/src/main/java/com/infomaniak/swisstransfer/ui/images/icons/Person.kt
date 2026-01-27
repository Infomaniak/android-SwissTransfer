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

val AppIcons.Person: ImageVector
    get() {
        if (_person != null) {
            return _person!!
        }
        _person = Builder(
            name = "Person",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(6.0f, 6.75f)
                    curveTo(6.0f, 8.341f, 6.632f, 9.867f, 7.757f, 10.993f)
                    curveTo(8.883f, 12.118f, 10.409f, 12.75f, 12.0f, 12.75f)
                    curveTo(13.591f, 12.75f, 15.117f, 12.118f, 16.243f, 10.993f)
                    curveTo(17.368f, 9.867f, 18.0f, 8.341f, 18.0f, 6.75f)
                    curveTo(18.0f, 5.159f, 17.368f, 3.633f, 16.243f, 2.507f)
                    curveTo(15.117f, 1.382f, 13.591f, 0.75f, 12.0f, 0.75f)
                    curveTo(10.409f, 0.75f, 8.883f, 1.382f, 7.757f, 2.507f)
                    curveTo(6.632f, 3.633f, 6.0f, 5.159f, 6.0f, 6.75f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(2.25f, 23.25f)
                    curveTo(2.25f, 20.73f, 3.277f, 18.313f, 5.106f, 16.53f)
                    curveTo(6.934f, 14.748f, 9.414f, 13.747f, 12.0f, 13.747f)
                    curveTo(14.586f, 13.747f, 17.066f, 14.748f, 18.894f, 16.53f)
                    curveTo(20.723f, 18.313f, 21.75f, 20.73f, 21.75f, 23.25f)
                }
            }
        }.build()
        return _person!!
    }

private var _person: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Person,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
