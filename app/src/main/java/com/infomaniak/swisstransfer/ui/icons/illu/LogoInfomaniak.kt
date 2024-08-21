package com.infomaniak.swisstransfer.ui.icons.illu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons

val AppIcons.Illu.LogoInfomaniak: ImageVector
    get() {
        if (_logoInfomaniak != null) {
            return _logoInfomaniak!!
        }
        _logoInfomaniak = Builder(
            name = "LogoInfomaniak",
            defaultWidth = 128.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 128.0f,
            viewportHeight = 16.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.26f)
                horizontalLineToRelative(4.34f)
                verticalLineToRelative(2.92f)
                lineTo(0.0f, 3.19f)
                close()
                moveToRelative(0.0f, 4.27f)
                horizontalLineToRelative(4.34f)
                verticalLineToRelative(11.22f)
                horizontalLineTo(0.0f)
                close()
                moveToRelative(6.84f, 0.0f)
                horizontalLineToRelative(4.05f)
                verticalLineToRelative(1.83f)
                quadToRelative(0.91f, -1.12f, 1.84f, -1.6f)
                reflectiveQuadToRelative(2.26f, -0.48f)
                quadToRelative(1.8f, 0.0f, 2.82f, 1.06f)
                quadToRelative(1.02f, 1.06f, 1.02f, 3.28f)
                verticalLineToRelative(7.13f)
                horizontalLineToRelative(-4.37f)
                verticalLineTo(9.58f)
                quadToRelative(0.0f, -1.06f, -0.4f, -1.5f)
                quadToRelative(-0.39f, -0.44f, -1.11f, -0.44f)
                quadToRelative(-0.79f, 0.0f, -1.28f, 0.59f)
                reflectiveQuadToRelative(-0.49f, 2.12f)
                verticalLineToRelative(5.39f)
                lineToRelative(-4.34f, 0.01f)
                close()
                moveToRelative(19.13f, 0.0f)
                horizontalLineToRelative(2.07f)
                verticalLineToRelative(3.15f)
                horizontalLineToRelative(-2.07f)
                verticalLineToRelative(8.07f)
                horizontalLineToRelative(-4.36f)
                verticalLineTo(7.68f)
                horizontalLineToRelative(-1.62f)
                verticalLineTo(4.53f)
                horizontalLineToRelative(1.62f)
                verticalLineTo(4.02f)
                quadToRelative(0.0f, -0.69f, 0.15f, -1.51f)
                quadToRelative(0.15f, -0.82f, 0.56f, -1.35f)
                quadToRelative(0.41f, -0.52f, 1.15f, -0.85f)
                quadTo(24.22f, 0.0f, 25.65f, 0.0f)
                lineToRelative(0.38f, 0.01f)
                quadToRelative(1.1f, 0.04f, 2.94f, 0.26f)
                lineToRelative(-0.48f, 2.6f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, -1.26f, -0.13f)
                quadToRelative(-0.59f, 0.0f, -0.84f, 0.2f)
                quadToRelative(-0.26f, 0.19f, -0.36f, 0.62f)
                quadToRelative(-0.05f, 0.23f, -0.05f, 0.98f)
                moveToRelative(2.48f, 5.64f)
                quadToRelative(0.0f, -2.57f, 1.75f, -4.23f)
                quadToRelative(1.75f, -1.66f, 4.73f, -1.66f)
                quadToRelative(3.4f, 0.0f, 5.14f, 1.95f)
                quadToRelative(1.4f, 1.57f, 1.4f, 3.88f)
                quadToRelative(0.0f, 2.59f, -1.74f, 4.24f)
                reflectiveQuadTo(34.94f, 16.0f)
                quadToRelative(-2.73f, 0.0f, -4.42f, -1.37f)
                quadToRelative(-2.07f, -1.7f, -2.07f, -4.46f)
                moveToRelative(4.36f, -0.01f)
                quadToRelative(0.0f, 1.5f, 0.61f, 2.22f)
                reflectiveQuadToRelative(1.54f, 0.72f)
                quadToRelative(0.94f, 0.0f, 1.54f, -0.71f)
                reflectiveQuadToRelative(0.6f, -2.27f)
                quadToRelative(0.0f, -1.46f, -0.61f, -2.17f)
                reflectiveQuadToRelative(-1.51f, -0.71f)
                quadToRelative(-0.95f, 0.0f, -1.57f, 0.72f)
                quadToRelative(-0.62f, 0.72f, -0.62f, 2.2f)
                moveTo(43.3f, 4.53f)
                horizontalLineToRelative(4.06f)
                verticalLineToRelative(1.64f)
                quadToRelative(0.88f, -1.02f, 1.77f, -1.46f)
                quadToRelative(0.89f, -0.43f, 2.15f, -0.43f)
                quadToRelative(1.36f, 0.0f, 2.15f, 0.47f)
                quadToRelative(0.79f, 0.48f, 1.29f, 1.42f)
                quadToRelative(1.02f, -1.1f, 1.87f, -1.5f)
                quadToRelative(0.84f, -0.4f, 2.08f, -0.4f)
                quadToRelative(1.83f, 0.0f, 2.85f, 1.07f)
                reflectiveQuadToRelative(1.02f, 3.35f)
                verticalLineToRelative(7.04f)
                horizontalLineToRelative(-4.36f)
                lineToRelative(0.01f, -6.37f)
                quadToRelative(0.0f, -0.76f, -0.3f, -1.13f)
                quadToRelative(-0.44f, -0.58f, -1.09f, -0.58f)
                quadToRelative(-0.77f, 0.0f, -1.24f, 0.55f)
                reflectiveQuadToRelative(-0.47f, 1.76f)
                verticalLineToRelative(5.79f)
                horizontalLineToRelative(-4.36f)
                lineToRelative(0.01f, -6.18f)
                quadToRelative(0.0f, -0.74f, -0.09f, -1.0f)
                arcToRelative(1.37f, 1.37f, 0.0f, false, false, -0.48f, -0.68f)
                arcToRelative(1.3f, 1.3f, 0.0f, false, false, -0.8f, -0.26f)
                quadToRelative(-0.75f, 0.0f, -1.23f, 0.56f)
                reflectiveQuadToRelative(-0.48f, 1.84f)
                verticalLineToRelative(5.72f)
                horizontalLineTo(43.3f)
                close()
                moveToRelative(25.51f, 3.63f)
                lineToRelative(-4.15f, -0.43f)
                quadToRelative(0.23f, -1.08f, 0.68f, -1.7f)
                quadToRelative(0.44f, -0.62f, 1.27f, -1.07f)
                quadToRelative(0.6f, -0.33f, 1.64f, -0.51f)
                quadToRelative(1.05f, -0.18f, 2.26f, -0.18f)
                quadToRelative(1.95f, 0.0f, 3.14f, 0.22f)
                quadToRelative(1.18f, 0.22f, 1.98f, 0.9f)
                quadToRelative(0.56f, 0.47f, 0.88f, 1.35f)
                reflectiveQuadToRelative(0.32f, 1.66f)
                verticalLineToRelative(4.95f)
                quadToRelative(0.0f, 0.79f, 0.1f, 1.24f)
                quadToRelative(0.1f, 0.45f, 0.44f, 1.15f)
                horizontalLineToRelative(-4.08f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, true, -0.32f, -0.66f)
                quadToRelative(-0.08f, -0.23f, -0.15f, -0.71f)
                quadToRelative(-0.85f, 0.81f, -1.7f, 1.16f)
                quadTo(69.97f, 16.0f, 68.44f, 16.0f)
                quadToRelative(-2.03f, 0.0f, -3.08f, -0.93f)
                quadToRelative(-1.05f, -0.93f, -1.05f, -2.29f)
                quadToRelative(0.0f, -1.28f, 0.76f, -2.1f)
                quadToRelative(0.7f, -0.76f, 2.51f, -1.16f)
                lineToRelative(2.3f, -0.47f)
                quadToRelative(0.79f, -0.17f, 1.15f, -0.27f)
                quadToRelative(0.73f, -0.19f, 1.54f, -0.51f)
                quadToRelative(0.0f, -0.79f, -0.33f, -1.11f)
                quadToRelative(-0.33f, -0.32f, -1.16f, -0.32f)
                quadToRelative(-1.07f, 0.0f, -1.6f, 0.34f)
                quadToRelative(-0.42f, 0.26f, -0.67f, 0.99f)
                moveToRelative(3.77f, 2.26f)
                quadToRelative(-0.67f, 0.24f, -1.39f, 0.43f)
                lineToRelative(-0.48f, 0.13f)
                quadToRelative(-1.32f, 0.35f, -1.68f, 0.69f)
                quadToRelative(-0.36f, 0.35f, -0.36f, 0.79f)
                quadToRelative(0.0f, 0.51f, 0.36f, 0.83f)
                quadToRelative(0.36f, 0.32f, 1.05f, 0.32f)
                quadToRelative(0.73f, 0.0f, 1.35f, -0.35f)
                quadToRelative(0.62f, -0.35f, 0.89f, -0.85f)
                quadToRelative(0.26f, -0.5f, 0.26f, -1.3f)
                close()
                moveToRelative(6.57f, -5.9f)
                horizontalLineToRelative(4.05f)
                verticalLineToRelative(1.83f)
                quadToRelative(0.91f, -1.12f, 1.84f, -1.6f)
                reflectiveQuadToRelative(2.26f, -0.48f)
                quadToRelative(1.8f, 0.0f, 2.82f, 1.06f)
                quadToRelative(1.02f, 1.06f, 1.02f, 3.28f)
                verticalLineToRelative(7.13f)
                horizontalLineToRelative(-4.37f)
                verticalLineTo(9.58f)
                quadToRelative(0.0f, -1.06f, -0.4f, -1.5f)
                quadToRelative(-0.39f, -0.44f, -1.11f, -0.44f)
                quadToRelative(-0.79f, 0.0f, -1.28f, 0.59f)
                reflectiveQuadToRelative(-0.49f, 2.12f)
                verticalLineToRelative(5.39f)
                horizontalLineToRelative(-4.34f)
                close()
                moveTo(93.6f, 0.26f)
                horizontalLineToRelative(4.34f)
                verticalLineToRelative(2.92f)
                horizontalLineTo(93.6f)
                close()
                moveToRelative(0.0f, 4.27f)
                horizontalLineToRelative(4.34f)
                verticalLineToRelative(11.22f)
                horizontalLineTo(93.6f)
                close()
                moveToRelative(10.8f, 3.63f)
                lineToRelative(-4.16f, -0.43f)
                quadToRelative(0.23f, -1.08f, 0.68f, -1.7f)
                quadToRelative(0.44f, -0.62f, 1.28f, -1.07f)
                quadToRelative(0.6f, -0.33f, 1.64f, -0.51f)
                quadToRelative(1.05f, -0.18f, 2.26f, -0.18f)
                quadToRelative(1.96f, 0.0f, 3.14f, 0.22f)
                quadToRelative(1.18f, 0.22f, 1.98f, 0.9f)
                quadToRelative(0.56f, 0.47f, 0.88f, 1.35f)
                arcToRelative(4.8f, 4.8f, 0.0f, false, true, 0.32f, 1.66f)
                verticalLineToRelative(4.95f)
                quadToRelative(0.0f, 0.79f, 0.1f, 1.24f)
                quadToRelative(0.1f, 0.45f, 0.44f, 1.15f)
                horizontalLineToRelative(-4.08f)
                lineToRelative(-0.14f, -0.26f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, true, -0.18f, -0.4f)
                arcToRelative(5.0f, 5.0f, 0.0f, false, true, -0.15f, -0.71f)
                quadToRelative(-0.85f, 0.81f, -1.7f, 1.16f)
                quadToRelative(-1.15f, 0.47f, -2.68f, 0.47f)
                quadToRelative(-2.03f, 0.0f, -3.08f, -0.93f)
                quadToRelative(-1.05f, -0.93f, -1.05f, -2.29f)
                quadToRelative(0.0f, -1.28f, 0.76f, -2.1f)
                quadToRelative(0.7f, -0.76f, 2.51f, -1.16f)
                lineToRelative(2.3f, -0.47f)
                quadToRelative(0.79f, -0.17f, 1.15f, -0.27f)
                quadToRelative(0.73f, -0.19f, 1.54f, -0.51f)
                quadToRelative(0.0f, -0.79f, -0.33f, -1.11f)
                quadToRelative(-0.33f, -0.32f, -1.16f, -0.32f)
                quadToRelative(-1.07f, 0.0f, -1.6f, 0.34f)
                quadToRelative(-0.42f, 0.26f, -0.67f, 0.99f)
                moveToRelative(3.77f, 2.26f)
                quadToRelative(-0.67f, 0.24f, -1.39f, 0.43f)
                lineToRelative(-0.48f, 0.13f)
                quadToRelative(-1.32f, 0.35f, -1.67f, 0.69f)
                quadToRelative(-0.36f, 0.35f, -0.36f, 0.79f)
                quadToRelative(0.0f, 0.51f, 0.36f, 0.83f)
                quadToRelative(0.36f, 0.32f, 1.05f, 0.32f)
                quadToRelative(0.73f, 0.0f, 1.35f, -0.35f)
                quadToRelative(0.62f, -0.35f, 0.89f, -0.85f)
                quadToRelative(0.26f, -0.5f, 0.26f, -1.3f)
                close()
                moveToRelative(6.56f, -10.17f)
                horizontalLineToRelative(4.44f)
                verticalLineToRelative(7.98f)
                lineToRelative(3.24f, -3.72f)
                horizontalLineToRelative(5.35f)
                lineToRelative(-4.07f, 3.93f)
                lineToRelative(4.31f, 7.3f)
                horizontalLineToRelative(-4.9f)
                lineToRelative(-2.3f, -4.5f)
                lineToRelative(-1.63f, 1.59f)
                verticalLineToRelative(2.91f)
                horizontalLineToRelative(-4.44f)
                close()
            }
        }.build()
        return _logoInfomaniak!!
    }

private var _logoInfomaniak: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Illu.LogoInfomaniak,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
