package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.LockedTextField: ImageVector
    get() {
        if (_lockedTextField != null) return _lockedTextField!!

        _lockedTextField = Builder(
            name = "LockedTextField",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f,
        ).apply {
            path(fill = SolidColor(Color(0xFF3CB572)), pathFillType = NonZero) {
                moveTo(8.564f, 8.963f)
                horizontalLineTo(1.527f)
                curveToRelative(-0.398f, 0.0f, -0.797f, -0.133f, -1.062f, -0.465f)
                curveTo(0.199f, 8.232f, 0.0f, 7.834f, 0.0f, 7.436f)
                verticalLineTo(1.46f)
                curveToRelative(0.0f, -0.398f, 0.133f, -0.796f, 0.465f, -1.062f)
                reflectiveCurveTo(1.129f, 0.0f, 1.527f, 0.0f)
                horizontalLineToRelative(11.95f)
                curveToRelative(0.399f, 0.0f, 0.797f, 0.133f, 1.062f, 0.465f)
                curveToRelative(0.266f, 0.332f, 0.465f, 0.664f, 0.465f, 1.062f)
                verticalLineTo(5.51f)
                curveToRelative(0.0f, 0.266f, -0.199f, 0.531f, -0.531f, 0.531f)
                reflectiveCurveToRelative(-0.465f, -0.265f, -0.465f, -0.597f)
                verticalLineTo(1.461f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.132f, -0.332f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.332f, -0.133f)
                horizontalLineTo(1.594f)
                curveToRelative(-0.2f, 0.0f, -0.266f, 0.066f, -0.399f, 0.133f)
                curveToRelative(-0.066f, 0.132f, -0.133f, 0.265f, -0.133f, 0.332f)
                verticalLineToRelative(5.975f)
                curveToRelative(0.0f, 0.132f, 0.067f, 0.265f, 0.133f, 0.332f)
                curveToRelative(0.133f, 0.132f, 0.2f, 0.199f, 0.332f, 0.199f)
                horizontalLineToRelative(6.97f)
                curveToRelative(0.266f, 0.0f, 0.532f, 0.199f, 0.532f, 0.53f)
                curveToRelative(0.0f, 0.266f, -0.2f, 0.466f, -0.465f, 0.466f)
            }
            path(fill = SolidColor(Color(0xFF3CB572)), pathFillType = NonZero) {
                moveTo(3.519f, 5.245f)
                curveToRelative(-0.067f, 0.0f, -0.2f, 0.0f, -0.266f, -0.067f)
                curveTo(3.12f, 5.112f, 2.988f, 5.046f, 2.921f, 4.913f)
                curveTo(2.855f, 4.78f, 2.788f, 4.647f, 2.788f, 4.448f)
                curveToRelative(0.0f, -0.133f, 0.0f, -0.199f, 0.067f, -0.265f)
                curveToRelative(0.066f, -0.067f, 0.066f, -0.133f, 0.199f, -0.2f)
                curveTo(3.12f, 3.917f, 3.187f, 3.851f, 3.32f, 3.851f)
                curveToRelative(0.265f, -0.067f, 0.464f, -0.067f, 0.73f, 0.066f)
                curveToRelative(0.133f, 0.066f, 0.199f, 0.2f, 0.265f, 0.332f)
                verticalLineToRelative(0.398f)
                quadToRelative(0.0f, 0.2f, -0.199f, 0.399f)
                lineTo(3.718f, 5.245f)
                close()
                moveToRelative(3.983f, 0.0f)
                curveToRelative(-0.066f, 0.0f, -0.2f, 0.0f, -0.265f, -0.067f)
                curveToRelative(-0.133f, -0.066f, -0.266f, -0.132f, -0.332f, -0.265f)
                curveTo(6.838f, 4.78f, 6.772f, 4.647f, 6.772f, 4.515f)
                curveToRelative(0.0f, -0.133f, 0.0f, -0.2f, 0.066f, -0.266f)
                curveToRelative(0.067f, -0.066f, 0.067f, -0.2f, 0.133f, -0.266f)
                reflectiveCurveToRelative(0.133f, -0.132f, 0.266f, -0.132f)
                curveToRelative(0.265f, -0.067f, 0.53f, -0.067f, 0.73f, 0.066f)
                curveToRelative(0.133f, 0.066f, 0.199f, 0.2f, 0.265f, 0.332f)
                curveToRelative(0.067f, 0.133f, 0.067f, 0.266f, 0.067f, 0.465f)
                quadToRelative(0.0f, 0.198f, -0.2f, 0.398f)
                quadToRelative(-0.198f, 0.2f, -0.398f, 0.2f)
                curveTo(7.635f, 5.245f, 7.568f, 5.245f, 7.502f, 5.245f)
                moveToRelative(3.984f, 0.0f)
                curveToRelative(-0.067f, 0.0f, -0.2f, 0.0f, -0.266f, -0.067f)
                curveToRelative(-0.133f, -0.066f, -0.266f, -0.132f, -0.332f, -0.265f)
                reflectiveCurveToRelative(-0.133f, -0.266f, -0.133f, -0.465f)
                curveToRelative(0.0f, -0.066f, 0.0f, -0.199f, 0.067f, -0.265f)
                curveToRelative(0.066f, -0.067f, 0.066f, -0.2f, 0.132f, -0.266f)
                curveToRelative(0.067f, -0.066f, 0.133f, -0.133f, 0.266f, -0.133f)
                curveToRelative(0.265f, -0.066f, 0.531f, -0.066f, 0.73f, 0.067f)
                curveToRelative(0.133f, 0.066f, 0.2f, 0.199f, 0.266f, 0.332f)
                curveToRelative(0.066f, 0.132f, 0.066f, 0.265f, 0.066f, 0.398f)
                reflectiveCurveToRelative(-0.133f, 0.265f, -0.199f, 0.398f)
                quadToRelative(-0.2f, 0.2f, -0.398f, 0.2f)
                curveToRelative(-0.067f, 0.066f, -0.133f, 0.066f, -0.2f, 0.066f)
            }
            path(fill = SolidColor(Color(0xFF3CB572)), pathFillType = EvenOdd) {
                moveTo(15.137f, 9.826f)
                arcToRelative(0.143f, 0.143f, 0.0f, false, true, -0.133f, -0.133f)
                verticalLineTo(9.029f)
                curveToRelative(0.0f, -1.261f, -1.062f, -2.324f, -2.323f, -2.324f)
                curveToRelative(-1.262f, 0.0f, -2.324f, 1.063f, -2.324f, 2.324f)
                verticalLineToRelative(0.664f)
                arcToRelative(0.143f, 0.143f, 0.0f, false, true, -0.133f, 0.133f)
                arcToRelative(1.005f, 1.005f, 0.0f, false, false, -0.863f, 0.996f)
                verticalLineToRelative(3.651f)
                curveToRelative(0.0f, 0.531f, 0.465f, 0.996f, 0.996f, 0.996f)
                horizontalLineToRelative(4.647f)
                curveToRelative(0.531f, 0.0f, 0.996f, -0.465f, 0.996f, -0.996f)
                verticalLineToRelative(-3.651f)
                curveToRelative(0.0f, -0.531f, -0.332f, -0.93f, -0.863f, -0.996f)
                moveTo(12.68f, 13.61f)
                curveToRelative(-0.399f, 0.0f, -0.664f, -0.266f, -0.664f, -0.664f)
                reflectiveCurveToRelative(0.265f, -0.664f, 0.664f, -0.664f)
                curveToRelative(0.398f, 0.0f, 0.663f, 0.266f, 0.663f, 0.664f)
                reflectiveCurveToRelative(-0.265f, 0.664f, -0.663f, 0.664f)
                moveToRelative(0.796f, -3.85f)
                arcToRelative(0.143f, 0.143f, 0.0f, false, false, 0.133f, -0.133f)
                verticalLineTo(8.963f)
                curveToRelative(0.0f, -0.531f, -0.465f, -0.996f, -0.996f, -0.996f)
                reflectiveCurveToRelative(-0.996f, 0.465f, -0.996f, 0.996f)
                verticalLineToRelative(0.664f)
                curveToRelative(0.0f, 0.066f, 0.067f, 0.132f, 0.133f, 0.132f)
                close()
            }
        }.build()
        return _lockedTextField!!
    }

private var _lockedTextField: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.LockedTextField, contentDescription = null, modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
