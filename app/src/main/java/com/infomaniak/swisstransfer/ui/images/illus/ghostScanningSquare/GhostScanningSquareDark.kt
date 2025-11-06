package com.infomaniak.swisstransfer.ui.images.illus.ghostScanningSquare

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
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

val AppIllus.GhostScanningSquareDark: ImageVector
    get() {
        if (_ghostScanningSquareDark != null) {
            return _ghostScanningSquareDark!!
        }
        _ghostScanningSquareDark = Builder(
            name = "GhostScanningSquareDark",
            defaultWidth = 160.0.dp,
            defaultHeight = 160.0.dp,
            viewportWidth = 160.0f,
            viewportHeight = 160.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF2B383B)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(80.45f, 0.5f)
                curveTo(103.33f, 0.5f, 122.54f, 17.74f, 125.0f, 40.49f)
                lineTo(137.73f, 158.19f)
                lineTo(124.34f, 139.81f)
                curveTo(116.97f, 129.71f, 101.9f, 129.71f, 94.54f, 139.81f)
                curveTo(87.57f, 149.37f, 73.32f, 149.37f, 66.36f, 139.81f)
                curveTo(59.0f, 129.71f, 43.92f, 129.71f, 36.56f, 139.81f)
                lineTo(23.17f, 158.19f)
                lineTo(35.9f, 40.49f)
                curveTo(38.36f, 17.74f, 57.57f, 0.5f, 80.45f, 0.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(69.21f, 55.73f)
                lineTo(69.21f, 55.73f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 73.71f, 60.23f)
                lineTo(73.71f, 62.02f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 69.21f, 66.52f)
                lineTo(69.21f, 66.52f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 64.72f, 62.02f)
                lineTo(64.72f, 60.23f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 69.21f, 55.73f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(91.69f, 55.73f)
                lineTo(91.69f, 55.73f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 96.18f, 60.23f)
                lineTo(96.18f, 62.02f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 91.69f, 66.52f)
                lineTo(91.69f, 66.52f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 87.19f, 62.02f)
                lineTo(87.19f, 60.23f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 91.69f, 55.73f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(96.1f, 69.71f)
                lineTo(113.66f, 87.27f)
                verticalLineTo(149.12f)
                lineTo(97.75f, 141.26f)
                lineTo(97.53f, 141.15f)
                lineTo(97.31f, 141.26f)
                lineTo(80.9f, 149.37f)
                lineTo(64.49f, 141.26f)
                lineTo(64.27f, 141.15f)
                lineTo(64.05f, 141.26f)
                lineTo(48.14f, 149.12f)
                verticalLineTo(69.71f)
                horizontalLineTo(96.1f)
                close()
            }
            group {
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(80.9f, 107.32f)
                    curveTo(81.1f, 107.32f, 81.26f, 107.16f, 81.26f, 106.97f)
                    curveTo(81.26f, 106.77f, 81.1f, 106.61f, 80.9f, 106.61f)
                    verticalLineTo(105.98f)
                    curveTo(80.35f, 105.98f, 79.91f, 106.42f, 79.91f, 106.97f)
                    curveTo(79.91f, 107.51f, 80.35f, 107.96f, 80.9f, 107.96f)
                    verticalLineTo(107.32f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(80.9f, 105.98f)
                    curveTo(81.45f, 105.98f, 81.89f, 106.42f, 81.89f, 106.97f)
                    curveTo(81.89f, 107.51f, 81.45f, 107.96f, 80.9f, 107.96f)
                    verticalLineTo(107.32f)
                    curveTo(80.7f, 107.32f, 80.54f, 107.16f, 80.54f, 106.97f)
                    curveTo(80.54f, 106.77f, 80.7f, 106.61f, 80.9f, 106.61f)
                    verticalLineTo(105.98f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(76.01f, 106.97f)
                    curveTo(76.01f, 104.27f, 78.2f, 102.08f, 80.9f, 102.08f)
                    curveTo(83.6f, 102.08f, 85.79f, 104.27f, 85.79f, 106.97f)
                    curveTo(85.79f, 109.67f, 83.6f, 111.85f, 80.9f, 111.85f)
                    curveTo(78.2f, 111.85f, 76.01f, 109.67f, 76.01f, 106.97f)
                    close()
                    moveTo(80.9f, 103.43f)
                    curveTo(78.94f, 103.43f, 77.36f, 105.01f, 77.36f, 106.97f)
                    curveTo(77.36f, 108.92f, 78.94f, 110.51f, 80.9f, 110.51f)
                    curveTo(82.85f, 110.51f, 84.44f, 108.92f, 84.44f, 106.97f)
                    curveTo(84.44f, 105.01f, 82.85f, 103.43f, 80.9f, 103.43f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(80.9f, 99.97f)
                    curveTo(81.27f, 99.97f, 81.57f, 100.27f, 81.57f, 100.65f)
                    verticalLineTo(103.81f)
                    curveTo(81.57f, 104.18f, 81.27f, 104.48f, 80.9f, 104.48f)
                    curveTo(80.53f, 104.48f, 80.22f, 104.18f, 80.22f, 103.81f)
                    verticalLineTo(100.65f)
                    curveTo(80.22f, 100.27f, 80.53f, 99.97f, 80.9f, 99.97f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(80.9f, 109.45f)
                    curveTo(81.27f, 109.45f, 81.57f, 109.75f, 81.57f, 110.13f)
                    verticalLineTo(113.29f)
                    curveTo(81.57f, 113.66f, 81.27f, 113.96f, 80.9f, 113.96f)
                    curveTo(80.53f, 113.96f, 80.22f, 113.66f, 80.22f, 113.29f)
                    verticalLineTo(110.13f)
                    curveTo(80.22f, 109.75f, 80.53f, 109.45f, 80.9f, 109.45f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(83.38f, 106.97f)
                    curveTo(83.38f, 106.59f, 83.69f, 106.29f, 84.06f, 106.29f)
                    horizontalLineTo(86.59f)
                    curveTo(86.96f, 106.29f, 87.26f, 106.59f, 87.26f, 106.97f)
                    curveTo(87.26f, 107.34f, 86.96f, 107.64f, 86.59f, 107.64f)
                    horizontalLineTo(84.06f)
                    curveTo(83.69f, 107.64f, 83.38f, 107.34f, 83.38f, 106.97f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(74.54f, 106.97f)
                    curveTo(74.54f, 106.59f, 74.84f, 106.29f, 75.21f, 106.29f)
                    horizontalLineTo(77.74f)
                    curveTo(78.11f, 106.29f, 78.41f, 106.59f, 78.41f, 106.97f)
                    curveTo(78.41f, 107.34f, 78.11f, 107.64f, 77.74f, 107.64f)
                    horizontalLineTo(75.21f)
                    curveTo(74.84f, 107.64f, 74.54f, 107.34f, 74.54f, 106.97f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(78.56f, 100.54f)
                    curveTo(78.72f, 100.88f, 78.57f, 101.28f, 78.23f, 101.43f)
                    curveTo(75.89f, 102.53f, 73.86f, 104.19f, 72.33f, 106.28f)
                    curveTo(72.18f, 106.49f, 72.09f, 106.74f, 72.09f, 106.99f)
                    curveTo(72.09f, 107.25f, 72.18f, 107.5f, 72.33f, 107.71f)
                    curveTo(73.85f, 109.8f, 75.89f, 111.46f, 78.23f, 112.55f)
                    curveTo(78.57f, 112.71f, 78.72f, 113.11f, 78.56f, 113.44f)
                    curveTo(78.4f, 113.78f, 78.0f, 113.93f, 77.67f, 113.77f)
                    curveTo(75.11f, 112.59f, 72.9f, 110.78f, 71.24f, 108.5f)
                    lineTo(71.23f, 108.5f)
                    curveTo(70.92f, 108.06f, 70.75f, 107.53f, 70.75f, 106.99f)
                    curveTo(70.75f, 106.45f, 70.92f, 105.92f, 71.23f, 105.48f)
                    lineTo(71.24f, 105.48f)
                    curveTo(72.9f, 103.21f, 75.11f, 101.4f, 77.67f, 100.21f)
                    curveTo(78.0f, 100.06f, 78.4f, 100.2f, 78.56f, 100.54f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(83.24f, 100.49f)
                    curveTo(83.39f, 100.15f, 83.79f, 100.0f, 84.13f, 100.16f)
                    curveTo(86.69f, 101.35f, 88.9f, 103.16f, 90.56f, 105.43f)
                    lineTo(90.56f, 105.43f)
                    curveTo(90.88f, 105.87f, 91.05f, 106.4f, 91.05f, 106.94f)
                    curveTo(91.05f, 107.48f, 90.88f, 108.01f, 90.56f, 108.45f)
                    lineTo(90.56f, 108.45f)
                    curveTo(88.9f, 110.72f, 86.69f, 112.54f, 84.13f, 113.72f)
                    curveTo(83.79f, 113.88f, 83.39f, 113.73f, 83.24f, 113.39f)
                    curveTo(83.08f, 113.06f, 83.23f, 112.65f, 83.57f, 112.5f)
                    curveTo(85.91f, 111.41f, 87.94f, 109.75f, 89.47f, 107.66f)
                    curveTo(89.62f, 107.45f, 89.7f, 107.2f, 89.7f, 106.94f)
                    curveTo(89.7f, 106.68f, 89.62f, 106.43f, 89.47f, 106.23f)
                    curveTo(87.94f, 104.14f, 85.91f, 102.47f, 83.56f, 101.38f)
                    curveTo(83.23f, 101.23f, 83.08f, 100.83f, 83.24f, 100.49f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF2B383B)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(96.81f, 102.08f)
                    curveTo(97.82f, 98.17f, 101.8f, 95.81f, 105.71f, 96.81f)
                    lineTo(122.2f, 101.04f)
                    lineTo(118.57f, 115.2f)
                    lineTo(102.08f, 110.97f)
                    curveTo(98.17f, 109.97f, 95.81f, 105.99f, 96.81f, 102.08f)
                    close()
                }
                path(
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(122.2f, 101.04f)
                    lineTo(105.71f, 96.81f)
                    curveTo(101.8f, 95.81f, 97.82f, 98.17f, 96.81f, 102.08f)
                    curveTo(95.81f, 105.99f, 98.17f, 109.97f, 102.08f, 110.97f)
                    lineTo(118.57f, 115.2f)
                }
                path(
                    fill = SolidColor(Color(0xFF2B383B)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(64.38f, 102.08f)
                    curveTo(63.38f, 98.17f, 59.4f, 95.81f, 55.49f, 96.81f)
                    lineTo(39.0f, 101.04f)
                    lineTo(42.63f, 115.2f)
                    lineTo(59.12f, 110.97f)
                    curveTo(63.03f, 109.97f, 65.39f, 105.99f, 64.38f, 102.08f)
                    close()
                }
                path(
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(39.0f, 101.04f)
                    lineTo(55.49f, 96.81f)
                    curveTo(59.4f, 95.81f, 63.38f, 98.17f, 64.38f, 102.08f)
                    curveTo(65.39f, 105.99f, 63.03f, 109.97f, 59.12f, 110.97f)
                    lineTo(42.63f, 115.2f)
                }
                path(
                    fill = linearGradient(
                        0.0f to Color(0x00152123),
                        1.0f to Color(0xFF014958),
                        start = Offset(80.0f, 35.0f),
                        end = Offset(80.44f, 145.29f),
                    ),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(0.0f, 35.0f)
                    horizontalLineTo(160.0f)
                    verticalLineTo(125.0f)
                    horizontalLineTo(0.0f)
                    verticalLineTo(35.0f)
                    close()
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF67DD95)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(160.0f, 124.1f)
                    horizontalLineTo(0.0f)
                    verticalLineTo(125.9f)
                    horizontalLineTo(160.0f)
                    verticalLineTo(124.1f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFF9802)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(160.0f, 0.0f)
                    horizontalLineTo(149.21f)
                    verticalLineTo(10.79f)
                    horizontalLineTo(149.21f)
                    verticalLineTo(1.8f)
                    horizontalLineTo(158.2f)
                    verticalLineTo(10.79f)
                    horizontalLineTo(160.0f)
                    verticalLineTo(0.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFF9802)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(-0.0f, 0.0f)
                    lineTo(0.0f, 10.79f)
                    lineTo(1.8f, 10.79f)
                    lineTo(1.8f, 1.8f)
                    lineTo(10.79f, 1.8f)
                    lineTo(10.79f, 0.0f)
                    lineTo(-0.0f, 0.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFF9802)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(0.0f, 160.0f)
                    lineTo(10.79f, 160.0f)
                    lineTo(10.79f, 158.2f)
                    lineTo(1.8f, 158.2f)
                    lineTo(1.8f, 149.21f)
                    lineTo(0.0f, 149.21f)
                    lineTo(0.0f, 160.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFF9802)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(160.0f, 160.0f)
                    lineTo(160.0f, 149.21f)
                    lineTo(149.21f, 149.21f)
                    lineTo(149.21f, 160.0f)
                    lineTo(160.0f, 160.0f)
                    close()
                    moveTo(158.2f, 158.2f)
                    lineTo(158.2f, 149.21f)
                    lineTo(149.21f, 149.21f)
                    lineTo(149.21f, 158.2f)
                    lineTo(158.2f, 158.2f)
                    close()
                }
            }
        }.build()
        return _ghostScanningSquareDark!!
    }

private var _ghostScanningSquareDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.GhostScanningSquareDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
