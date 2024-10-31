package com.infomaniak.swisstransfer.ui.images.illus.uploadAd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.MountainGondolaLight: ImageVector
    get() {
        if (_mountainGondolaLight != null) {
            return _mountainGondolaLight!!
        }
        _mountainGondolaLight = Builder(
            name = "MountainGondolaLight",
            defaultWidth = 343.0.dp,
            defaultHeight = 210.0.dp,
            viewportWidth = 343.0f,
            viewportHeight = 210.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(267.07f, 180.3f)
                    lineToRelative(0.17f, 0.09f)
                    lineToRelative(0.09f, 0.09f)
                    lineToRelative(0.41f, 0.43f)
                    lineToRelative(1.47f, 1.55f)
                    curveToRelative(1.22f, 1.28f, 2.85f, 3.0f, 4.5f, 4.74f)
                    arcToRelative(911.0f, 911.0f, 0.0f, false, true, 4.57f, 4.86f)
                    curveToRelative(0.64f, 0.68f, 1.17f, 1.26f, 1.57f, 1.7f)
                    arcToRelative(31.0f, 31.0f, 0.0f, false, true, 0.6f, 0.68f)
                    curveToRelative(0.04f, 0.05f, 0.04f, 0.06f, 0.04f, 0.05f)
                    lineToRelative(0.03f, 0.06f)
                    quadToRelative(0.04f, 0.06f, 0.09f, 0.17f)
                    curveToRelative(0.07f, 0.15f, 0.16f, 0.34f, 0.28f, 0.59f)
                    curveToRelative(0.22f, 0.49f, 0.52f, 1.15f, 0.87f, 1.93f)
                    arcToRelative(981.0f, 981.0f, 0.0f, false, true, 2.47f, 5.56f)
                    arcToRelative(2603.0f, 2603.0f, 0.0f, false, true, 3.16f, 7.2f)
                    lineToRelative(-235.09f, -0.13f)
                    curveToRelative(0.36f, -0.29f, 0.84f, -0.67f, 1.5f, -1.14f)
                    curveToRelative(2.08f, -1.5f, 5.86f, -4.01f, 12.63f, -7.96f)
                    curveToRelative(12.86f, -7.52f, 18.84f, -18.85f, 21.63f, -24.14f)
                    quadToRelative(0.27f, -0.51f, 0.5f, -0.94f)
                    curveToRelative(0.75f, -1.4f, 1.25f, -2.56f, 1.7f, -3.61f)
                    curveToRelative(1.12f, -2.62f, 1.94f, -4.52f, 5.57f, -7.56f)
                    curveToRelative(0.65f, -0.54f, 1.32f, -1.1f, 2.01f, -1.66f)
                    curveToRelative(2.04f, -1.68f, 4.19f, -3.45f, 6.03f, -5.19f)
                    curveToRelative(1.24f, -1.17f, 2.35f, -2.34f, 3.22f, -3.49f)
                    curveToRelative(0.87f, -1.14f, 1.52f, -2.29f, 1.78f, -3.41f)
                    curveToRelative(0.11f, -0.45f, 0.45f, -1.28f, 0.99f, -2.43f)
                    arcToRelative(123.0f, 123.0f, 0.0f, false, true, 2.07f, -4.12f)
                    curveToRelative(1.03f, -1.98f, 2.23f, -4.23f, 3.49f, -6.58f)
                    curveToRelative(0.76f, -1.42f, 1.54f, -2.88f, 2.31f, -4.34f)
                    curveToRelative(2.05f, -3.86f, 4.05f, -7.71f, 5.48f, -10.8f)
                    curveToRelative(0.72f, -1.55f, 1.3f, -2.91f, 1.68f, -4.01f)
                    curveToRelative(0.19f, -0.55f, 0.33f, -1.04f, 0.41f, -1.45f)
                    curveToRelative(0.08f, -0.4f, 0.11f, -0.79f, 0.04f, -1.11f)
                    arcToRelative(15.4f, 15.4f, 0.0f, false, false, -0.76f, -2.25f)
                    lineToRelative(-0.16f, -0.37f)
                    curveToRelative(-0.2f, -0.49f, -0.37f, -0.89f, -0.48f, -1.27f)
                    curveToRelative(-0.13f, -0.46f, -0.16f, -0.83f, -0.06f, -1.18f)
                    reflectiveCurveToRelative(0.35f, -0.75f, 0.9f, -1.22f)
                    curveToRelative(0.54f, -0.46f, 0.99f, -0.66f, 1.39f, -0.77f)
                    curveToRelative(0.29f, -0.08f, 0.55f, -0.1f, 0.83f, -0.13f)
                    curveToRelative(0.14f, -0.01f, 0.28f, -0.03f, 0.44f, -0.05f)
                    curveToRelative(0.47f, -0.06f, 0.96f, -0.18f, 1.48f, -0.55f)
                    curveToRelative(0.51f, -0.37f, 1.0f, -0.95f, 1.51f, -1.87f)
                    curveToRelative(0.89f, -1.6f, 2.13f, -2.44f, 3.19f, -3.16f)
                    lineToRelative(0.1f, -0.07f)
                    curveToRelative(0.52f, -0.35f, 1.04f, -0.71f, 1.39f, -1.15f)
                    curveToRelative(0.38f, -0.48f, 0.53f, -1.05f, 0.35f, -1.74f)
                    curveToRelative(-0.12f, -0.46f, -0.17f, -1.4f, -0.16f, -2.64f)
                    curveToRelative(0.01f, -1.21f, 0.08f, -2.63f, 0.17f, -3.99f)
                    arcToRelative(159.0f, 159.0f, 0.0f, false, true, 0.38f, -4.74f)
                    lineToRelative(0.03f, -0.29f)
                    lineToRelative(8.1f, -11.61f)
                    lineToRelative(8.69f, 1.61f)
                    lineToRelative(1.77f, 1.07f)
                    lineToRelative(33.83f, 44.7f)
                    lineToRelative(0.0f, 0.04f)
                    lineToRelative(0.06f, 0.48f)
                    arcToRelative(456.8f, 456.8f, 0.0f, false, false, 0.98f, 6.9f)
                    curveToRelative(0.29f, 1.91f, 0.6f, 3.84f, 0.87f, 5.34f)
                    curveToRelative(0.14f, 0.75f, 0.27f, 1.39f, 0.39f, 1.88f)
                    curveToRelative(0.06f, 0.24f, 0.12f, 0.45f, 0.17f, 0.62f)
                    quadToRelative(0.04f, 0.13f, 0.09f, 0.23f)
                    curveToRelative(0.03f, 0.06f, 0.08f, 0.16f, 0.16f, 0.24f)
                    curveToRelative(0.11f, 0.11f, 0.38f, 0.33f, 0.73f, 0.6f)
                    curveToRelative(0.36f, 0.28f, 0.84f, 0.64f, 1.39f, 1.05f)
                    arcToRelative(517.0f, 517.0f, 0.0f, false, false, 3.92f, 2.88f)
                    curveToRelative(1.4f, 1.02f, 2.77f, 2.02f, 3.8f, 2.76f)
                    lineToRelative(1.24f, 0.89f)
                    lineToRelative(0.34f, 0.25f)
                    lineToRelative(0.09f, 0.07f)
                    lineToRelative(0.02f, 0.02f)
                    lineToRelative(0.01f, 0.0f)
                    lineToRelative(0.0f, 0.0f)
                    lineToRelative(0.29f, -0.41f)
                    lineToRelative(-0.29f, 0.41f)
                    lineToRelative(0.03f, 0.02f)
                    lineToRelative(0.03f, 0.02f)
                    lineToRelative(18.75f, 9.98f)
                    lineToRelative(0.0f, 0.0f)
                    lineToRelative(0.08f, 0.06f)
                    lineToRelative(0.32f, 0.22f)
                    arcToRelative(92.1f, 92.1f, 0.0f, false, false, 5.26f, 3.28f)
                    curveToRelative(3.31f, 1.9f, 7.6f, 4.09f, 11.45f, 5.13f)
                    curveToRelative(3.73f, 1.01f, 10.92f, 4.3f, 17.23f, 7.38f)
                    arcToRelative(475.0f, 475.0f, 0.0f, false, true, 10.7f, 5.38f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(287.41f, 209.97f)
                    lineToRelative(-0.77f, -1.75f)
                    curveToRelative(-0.65f, -1.48f, -1.51f, -3.45f, -2.39f, -5.45f)
                    arcToRelative(1028.0f, 1028.0f, 0.0f, false, false, -2.47f, -5.56f)
                    curveToRelative(-0.35f, -0.78f, -0.65f, -1.44f, -0.87f, -1.92f)
                    arcToRelative(50.0f, 50.0f, 0.0f, false, false, -0.28f, -0.59f)
                    lineToRelative(-0.05f, -0.1f)
                    lineToRelative(-0.04f, -0.08f)
                    lineToRelative(-0.03f, -0.06f)
                    lineToRelative(-0.02f, -0.04f)
                    lineToRelative(-0.01f, -0.02f)
                    lineToRelative(-0.13f, -0.16f)
                    arcToRelative(32.0f, 32.0f, 0.0f, false, false, -0.47f, -0.52f)
                    curveToRelative(-0.39f, -0.43f, -0.93f, -1.01f, -1.56f, -1.7f)
                    arcToRelative(1002.0f, 1002.0f, 0.0f, false, false, -4.57f, -4.86f)
                    arcToRelative(2620.0f, 2620.0f, 0.0f, false, false, -4.5f, -4.74f)
                    lineToRelative(-1.47f, -1.55f)
                    lineToRelative(-0.41f, -0.43f)
                    lineToRelative(-0.09f, -0.09f)
                    lineToRelative(-0.17f, -0.09f)
                    lineToRelative(-0.69f, -0.36f)
                    arcToRelative(475.43f, 475.43f, 0.0f, false, false, -10.7f, -5.38f)
                    curveToRelative(-6.31f, -3.07f, -13.5f, -6.37f, -17.23f, -7.38f)
                    curveToRelative(-3.85f, -1.04f, -8.14f, -3.22f, -11.45f, -5.13f)
                    arcToRelative(92.0f, 92.0f, 0.0f, false, true, -5.26f, -3.28f)
                    lineToRelative(-0.31f, -0.22f)
                    lineToRelative(-0.08f, -0.06f)
                    lineToRelative(-0.0f, -0.0f)
                    lineToRelative(-18.75f, -9.98f)
                    lineToRelative(-0.03f, -0.02f)
                    lineToRelative(-0.03f, -0.02f)
                    lineToRelative(0.29f, -0.41f)
                    lineToRelative(-0.29f, 0.41f)
                    lineToRelative(-0.0f, -0.0f)
                    lineToRelative(-0.01f, -0.0f)
                    lineToRelative(-0.02f, -0.02f)
                    lineToRelative(-0.09f, -0.07f)
                    lineToRelative(-0.34f, -0.25f)
                    lineToRelative(-1.24f, -0.89f)
                    curveToRelative(-1.03f, -0.74f, -2.4f, -1.74f, -3.8f, -2.76f)
                    arcToRelative(481.0f, 481.0f, 0.0f, false, true, -3.92f, -2.88f)
                    curveToRelative(-0.55f, -0.41f, -1.03f, -0.77f, -1.39f, -1.05f)
                    curveToRelative(-0.34f, -0.27f, -0.62f, -0.49f, -0.73f, -0.6f)
                    arcToRelative(0.9f, 0.9f, 0.0f, false, true, -0.16f, -0.24f)
                    arcToRelative(2.0f, 2.0f, 0.0f, false, true, -0.09f, -0.23f)
                    arcToRelative(8.0f, 8.0f, 0.0f, false, true, -0.17f, -0.62f)
                    arcToRelative(36.0f, 36.0f, 0.0f, false, true, -0.39f, -1.88f)
                    curveToRelative(-0.28f, -1.5f, -0.58f, -3.43f, -0.87f, -5.34f)
                    arcToRelative(485.0f, 485.0f, 0.0f, false, true, -1.05f, -7.38f)
                    lineToRelative(-0.0f, -0.04f)
                    lineToRelative(-33.83f, -44.7f)
                    lineToRelative(-1.77f, -1.07f)
                    lineToRelative(-8.0f, -1.48f)
                    lineToRelative(5.09f, 9.79f)
                    arcToRelative(14.0f, 14.0f, 0.0f, false, true, 1.06f, 0.71f)
                    curveToRelative(0.71f, 0.52f, 1.66f, 1.3f, 2.63f, 2.33f)
                    curveToRelative(1.9f, 2.03f, 3.89f, 5.08f, 4.27f, 9.15f)
                    lineToRelative(5.79f, 3.36f)
                    lineToRelative(0.12f, 0.07f)
                    lineToRelative(0.07f, 0.13f)
                    lineToRelative(2.76f, 5.16f)
                    lineToRelative(0.11f, 0.2f)
                    lineToRelative(-0.08f, 0.21f)
                    lineToRelative(-2.65f, 6.79f)
                    lineToRelative(2.15f, 2.5f)
                    lineToRelative(0.19f, 0.22f)
                    lineToRelative(-0.1f, 0.28f)
                    verticalLineToRelative(0.0f)
                    lineToRelative(-0.0f, 0.0f)
                    lineToRelative(-0.01f, 0.02f)
                    lineToRelative(-0.03f, 0.08f)
                    arcToRelative(6.0f, 6.0f, 0.0f, false, false, -0.09f, 0.34f)
                    curveToRelative(-0.08f, 0.31f, -0.17f, 0.77f, -0.25f, 1.39f)
                    curveToRelative(-0.15f, 1.24f, -0.21f, 3.11f, 0.16f, 5.56f)
                    curveToRelative(0.74f, 4.91f, 3.2f, 12.21f, 10.13f, 21.64f)
                    lineToRelative(0.1f, 0.14f)
                    lineToRelative(-0.0f, 0.17f)
                    lineToRelative(-0.17f, 12.13f)
                    lineToRelative(0.17f, 11.24f)
                    verticalLineToRelative(0.02f)
                    lineToRelative(0.0f, 0.03f)
                    lineToRelative(0.0f, 0.13f)
                    quadToRelative(0.0f, 0.17f, 0.02f, 0.51f)
                    curveToRelative(0.03f, 0.45f, 0.08f, 1.12f, 0.19f, 1.98f)
                    curveToRelative(0.22f, 1.72f, 0.66f, 4.21f, 1.54f, 7.26f)
                    lineToRelative(0.06f, 0.2f)
                    close()
                    moveTo(287.41f, 209.97f)
                    lineTo(178.4f, 209.91f)
                    curveToRelative(-0.51f, -3.72f, -0.52f, -7.31f, -0.53f, -10.24f)
                    curveToRelative(-0.02f, -4.43f, -0.29f, -6.5f, -0.67f, -7.89f)
                    curveToRelative(-0.15f, -0.57f, -0.32f, -1.02f, -0.48f, -1.45f)
                    lineToRelative(-0.1f, -0.27f)
                    arcToRelative(12.0f, 12.0f, 0.0f, false, true, -0.54f, -1.87f)
                    curveToRelative(-0.31f, -1.53f, 0.1f, -3.33f, 0.62f, -4.81f)
                    curveToRelative(0.26f, -0.73f, 0.54f, -1.35f, 0.75f, -1.8f)
                    arcToRelative(17.0f, 17.0f, 0.0f, false, true, 0.33f, -0.65f)
                    lineToRelative(0.02f, -0.03f)
                    lineToRelative(0.0f, -0.01f)
                    lineToRelative(0.0f, -0.0f)
                    moveToRelative(109.6f, 29.08f)
                    lineToRelative(-109.6f, -29.08f)
                    moveToRelative(0.0f, 0.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3CB572)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(234.48f, 188.73f)
                    lineToRelative(-4.63f, -0.37f)
                    lineToRelative(8.29f, -13.17f)
                    lineToRelative(0.44f, -0.7f)
                    lineToRelative(-0.82f, -0.06f)
                    lineToRelative(-4.55f, -0.36f)
                    lineToRelative(11.09f, -14.88f)
                    lineToRelative(0.54f, -0.72f)
                    lineToRelative(-0.89f, -0.08f)
                    lineToRelative(-4.31f, -0.37f)
                    lineToRelative(10.55f, -18.67f)
                    lineToRelative(10.48f, 18.53f)
                    lineToRelative(-4.32f, 0.36f)
                    lineToRelative(-0.9f, 0.08f)
                    lineToRelative(0.54f, 0.72f)
                    lineToRelative(11.09f, 14.88f)
                    lineToRelative(-4.55f, 0.37f)
                    lineToRelative(-0.82f, 0.07f)
                    lineToRelative(0.44f, 0.7f)
                    lineToRelative(8.29f, 13.17f)
                    lineToRelative(-4.63f, 0.36f)
                    lineToRelative(-0.77f, 0.06f)
                    lineToRelative(0.37f, 0.68f)
                    lineToRelative(11.22f, 20.49f)
                    lineToRelative(-8.71f, -0.12f)
                    lineToRelative(-0.74f, -0.01f)
                    lineToRelative(0.02f, 0.06f)
                    lineToRelative(-43.54f, 0.23f)
                    lineToRelative(11.23f, -20.5f)
                    lineToRelative(0.37f, -0.68f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(256.62f, 193.57f)
                    lineToRelative(-3.52f, -0.27f)
                    lineToRelative(6.52f, -10.0f)
                    lineToRelative(0.46f, -0.71f)
                    lineToRelative(-0.84f, -0.06f)
                    lineToRelative(-3.44f, -0.26f)
                    lineToRelative(8.74f, -11.31f)
                    lineToRelative(0.56f, -0.73f)
                    lineToRelative(-0.92f, -0.08f)
                    lineToRelative(-3.28f, -0.27f)
                    lineToRelative(8.26f, -14.11f)
                    lineToRelative(8.2f, 14.0f)
                    lineToRelative(-3.28f, 0.27f)
                    lineToRelative(-0.92f, 0.07f)
                    lineToRelative(0.56f, 0.73f)
                    lineToRelative(8.74f, 11.31f)
                    lineToRelative(-3.44f, 0.27f)
                    lineToRelative(-0.84f, 0.06f)
                    lineToRelative(0.46f, 0.71f)
                    lineToRelative(6.52f, 10.0f)
                    lineToRelative(-3.52f, 0.27f)
                    lineToRelative(-0.79f, 0.06f)
                    lineToRelative(0.39f, 0.69f)
                    lineToRelative(8.88f, 15.64f)
                    lineToRelative(-6.78f, -0.09f)
                    lineToRelative(-0.75f, -0.01f)
                    lineToRelative(0.02f, 0.05f)
                    lineToRelative(-34.49f, 0.18f)
                    lineToRelative(8.88f, -15.65f)
                    lineToRelative(0.39f, -0.68f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFE3F6DC)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(244.18f, 112.94f)
                    horizontalLineToRelative(-2.65f)
                    curveToRelative(-1.28f, -3.26f, -4.38f, -5.56f, -8.01f, -5.56f)
                    curveToRelative(-3.8f, 0.0f, -7.02f, 2.53f, -8.18f, 6.04f)
                    arcToRelative(5.4f, 5.4f, 0.0f, false, false, -2.21f, -0.48f)
                    curveToRelative(-2.69f, 0.0f, -4.93f, 1.97f, -5.43f, 4.59f)
                    horizontalLineToRelative(-2.6f)
                    curveToRelative(-2.05f, 0.0f, -3.71f, 1.71f, -3.71f, 3.82f)
                    reflectiveCurveToRelative(1.66f, 3.82f, 3.71f, 3.82f)
                    horizontalLineToRelative(29.09f)
                    curveToRelative(3.28f, 0.0f, 5.94f, -2.74f, 5.94f, -6.12f)
                    reflectiveCurveToRelative(-2.66f, -6.12f, -5.94f, -6.12f)
                }
                path(
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(144.0f, 77.0f)
                    lineToRelative(-201.0f, 68.0f)
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(23.0f, 125.0f)
                    horizontalLineToRelative(28.0f)
                    verticalLineToRelative(2.0f)
                    horizontalLineTo(23.0f)
                    close()
                    moveTo(23.0f, 143.0f)
                    horizontalLineToRelative(28.0f)
                    verticalLineToRelative(6.0f)
                    horizontalLineTo(23.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFE3F6DC)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(25.5f, 127.5f)
                    horizontalLineToRelative(24.0f)
                    verticalLineToRelative(15.0f)
                    horizontalLineToRelative(-24.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(34.0f, 128.0f)
                    horizontalLineToRelative(3.0f)
                    lineToRelative(-8.0f, 14.0f)
                    horizontalLineToRelative(-3.0f)
                    close()
                    moveTo(38.0f, 128.0f)
                    horizontalLineToRelative(5.5f)
                    lineToRelative(-8.0f, 14.0f)
                    horizontalLineTo(30.0f)
                    close()
                    moveTo(49.0f, 128.0f)
                    verticalLineToRelative(9.63f)
                    lineTo(46.5f, 142.0f)
                    horizontalLineTo(41.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(38.67f, 111.0f)
                    horizontalLineToRelative(-3.33f)
                    lineTo(32.0f, 125.0f)
                    horizontalLineToRelative(10.0f)
                    close()
                    moveTo(41.0f, 124.0f)
                    lineTo(40.0f, 120.0f)
                    horizontalLineToRelative(-6.0f)
                    lineToRelative(-1.0f, 4.0f)
                    close()
                    moveTo(34.5f, 119.0f)
                    lineToRelative(0.5f, -2.0f)
                    horizontalLineToRelative(4.0f)
                    lineToRelative(0.5f, 2.0f)
                    close()
                    moveTo(37.0f, 114.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, true, false, 0.0f, -2.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 0.0f, 2.0f)
                }
            }
        }.build()
        return _mountainGondolaLight!!
    }

private var _mountainGondolaLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MountainGondolaLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
