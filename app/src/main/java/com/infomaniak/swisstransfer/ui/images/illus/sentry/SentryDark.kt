/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024 Infomaniak Network SA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.infomaniak.swisstransfer.ui.images.illus.sentry

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
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.SentryDark: ImageVector
    get() {

        if (_sentryDark != null) return _sentryDark!!

        _sentryDark = Builder(
            name = "SentryDark",
            defaultWidth = 163.0.dp,
            defaultHeight = 38.0.dp,
            viewportWidth = 163.0f,
            viewportHeight = 38.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(23.93f, 2.56f)
                curveTo(23.6f, 1.99f, 23.12f, 1.51f, 22.55f, 1.18f)
                curveTo(21.98f, 0.85f, 21.34f, 0.68f, 20.69f, 0.68f)
                curveTo(20.04f, 0.68f, 19.39f, 0.85f, 18.83f, 1.18f)
                curveTo(18.26f, 1.51f, 17.78f, 1.99f, 17.45f, 2.56f)
                lineTo(12.11f, 11.95f)
                curveTo(16.19f, 14.04f, 19.66f, 17.19f, 22.18f, 21.08f)
                curveTo(24.71f, 24.97f, 26.2f, 29.48f, 26.5f, 34.15f)
                horizontalLineTo(22.76f)
                curveTo(22.45f, 30.15f, 21.14f, 26.3f, 18.94f, 22.98f)
                curveTo(16.74f, 19.66f, 13.73f, 16.99f, 10.22f, 15.23f)
                lineTo(5.28f, 24.0f)
                curveTo(7.26f, 24.91f, 8.98f, 26.31f, 10.29f, 28.08f)
                curveTo(11.59f, 29.85f, 12.45f, 31.93f, 12.77f, 34.13f)
                horizontalLineTo(4.16f)
                curveTo(4.06f, 34.12f, 3.96f, 34.09f, 3.88f, 34.03f)
                curveTo(3.79f, 33.98f, 3.72f, 33.9f, 3.67f, 33.81f)
                curveTo(3.62f, 33.72f, 3.59f, 33.61f, 3.59f, 33.51f)
                curveTo(3.59f, 33.4f, 3.61f, 33.3f, 3.66f, 33.21f)
                lineTo(6.05f, 29.04f)
                curveTo(5.24f, 28.35f, 4.32f, 27.82f, 3.32f, 27.46f)
                lineTo(0.96f, 31.62f)
                curveTo(0.72f, 32.06f, 0.56f, 32.54f, 0.49f, 33.03f)
                curveTo(0.43f, 33.53f, 0.46f, 34.03f, 0.59f, 34.52f)
                curveTo(0.72f, 35.0f, 0.94f, 35.45f, 1.24f, 35.85f)
                curveTo(1.53f, 36.24f, 1.91f, 36.57f, 2.33f, 36.82f)
                curveTo(2.89f, 37.15f, 3.52f, 37.32f, 4.16f, 37.32f)
                horizontalLineTo(15.95f)
                curveTo(16.16f, 34.55f, 15.68f, 31.76f, 14.55f, 29.24f)
                curveTo(13.41f, 26.71f, 11.66f, 24.53f, 9.46f, 22.91f)
                lineTo(11.33f, 19.57f)
                curveTo(14.11f, 21.53f, 16.34f, 24.2f, 17.8f, 27.31f)
                curveTo(19.26f, 30.42f, 19.91f, 33.87f, 19.68f, 37.32f)
                horizontalLineTo(29.66f)
                curveTo(29.9f, 32.1f, 28.79f, 26.9f, 26.46f, 22.25f)
                curveTo(24.12f, 17.61f, 20.64f, 13.67f, 16.36f, 10.84f)
                lineTo(20.15f, 4.18f)
                curveTo(20.23f, 4.03f, 20.37f, 3.93f, 20.53f, 3.88f)
                curveTo(20.69f, 3.84f, 20.85f, 3.87f, 21.0f, 3.95f)
                curveTo(21.43f, 4.19f, 37.45f, 32.91f, 37.75f, 33.24f)
                curveTo(37.8f, 33.34f, 37.83f, 33.45f, 37.83f, 33.56f)
                curveTo(37.82f, 33.67f, 37.79f, 33.78f, 37.74f, 33.87f)
                curveTo(37.68f, 33.97f, 37.6f, 34.05f, 37.51f, 34.1f)
                curveTo(37.41f, 34.16f, 37.31f, 34.18f, 37.2f, 34.18f)
                horizontalLineTo(33.34f)
                curveTo(33.39f, 35.24f, 33.39f, 36.3f, 33.34f, 37.35f)
                horizontalLineTo(37.21f)
                curveTo(37.71f, 37.36f, 38.19f, 37.26f, 38.65f, 37.07f)
                curveTo(39.1f, 36.88f, 39.52f, 36.59f, 39.87f, 36.24f)
                curveTo(40.21f, 35.88f, 40.49f, 35.46f, 40.68f, 34.99f)
                curveTo(40.87f, 34.52f, 40.96f, 34.02f, 40.96f, 33.52f)
                curveTo(40.96f, 32.85f, 40.79f, 32.19f, 40.46f, 31.62f)
                lineTo(23.93f, 2.56f)
                close()
                moveTo(101.22f, 24.23f)
                lineTo(89.25f, 8.36f)
                horizontalLineTo(86.27f)
                verticalLineTo(29.63f)
                horizontalLineTo(89.29f)
                verticalLineTo(13.33f)
                lineTo(101.6f, 29.63f)
                horizontalLineTo(104.24f)
                verticalLineTo(8.36f)
                horizontalLineTo(101.22f)
                verticalLineTo(24.23f)
                close()
                moveTo(71.08f, 20.28f)
                horizontalLineTo(81.81f)
                verticalLineTo(17.52f)
                horizontalLineTo(71.07f)
                verticalLineTo(11.11f)
                horizontalLineTo(83.18f)
                verticalLineTo(8.35f)
                horizontalLineTo(67.99f)
                verticalLineTo(29.63f)
                horizontalLineTo(83.33f)
                verticalLineTo(26.87f)
                horizontalLineTo(71.07f)
                lineTo(71.08f, 20.28f)
                close()
                moveTo(58.46f, 17.58f)
                curveTo(54.29f, 16.55f, 53.12f, 15.74f, 53.12f, 13.75f)
                curveTo(53.12f, 11.97f, 54.65f, 10.76f, 56.94f, 10.76f)
                curveTo(59.02f, 10.83f, 61.03f, 11.57f, 62.67f, 12.89f)
                lineTo(64.29f, 10.53f)
                curveTo(62.22f, 8.86f, 59.64f, 7.97f, 57.0f, 8.03f)
                curveTo(52.89f, 8.03f, 50.03f, 10.53f, 50.03f, 14.09f)
                curveTo(50.03f, 17.92f, 52.46f, 19.24f, 56.89f, 20.35f)
                curveTo(60.83f, 21.28f, 62.04f, 22.15f, 62.04f, 24.09f)
                curveTo(62.04f, 26.03f, 60.42f, 27.23f, 57.91f, 27.23f)
                curveTo(55.42f, 27.22f, 53.02f, 26.25f, 51.18f, 24.51f)
                lineTo(49.36f, 26.75f)
                curveTo(51.71f, 28.83f, 54.71f, 29.97f, 57.81f, 29.96f)
                curveTo(62.25f, 29.96f, 65.11f, 27.5f, 65.11f, 23.71f)
                curveTo(65.08f, 20.49f, 63.23f, 18.77f, 58.46f, 17.58f)
                close()
                moveTo(159.09f, 8.36f)
                lineTo(152.86f, 18.35f)
                lineTo(146.66f, 8.36f)
                horizontalLineTo(143.05f)
                lineTo(151.23f, 21.22f)
                verticalLineTo(29.64f)
                horizontalLineTo(154.34f)
                verticalLineTo(21.12f)
                lineTo(162.58f, 8.36f)
                horizontalLineTo(159.09f)
                close()
                moveTo(106.69f, 11.24f)
                horizontalLineTo(113.48f)
                verticalLineTo(29.64f)
                horizontalLineTo(116.59f)
                verticalLineTo(11.24f)
                horizontalLineTo(123.38f)
                verticalLineTo(8.36f)
                horizontalLineTo(106.7f)
                lineTo(106.69f, 11.24f)
                close()
                moveTo(137.78f, 21.33f)
                curveTo(140.91f, 20.44f, 142.64f, 18.19f, 142.64f, 14.98f)
                curveTo(142.64f, 10.89f, 139.73f, 8.32f, 135.04f, 8.32f)
                horizontalLineTo(125.83f)
                verticalLineTo(29.63f)
                horizontalLineTo(128.91f)
                verticalLineTo(21.98f)
                horizontalLineTo(134.14f)
                lineTo(139.39f, 29.64f)
                horizontalLineTo(142.99f)
                lineTo(137.32f, 21.47f)
                lineTo(137.78f, 21.33f)
                close()
                moveTo(128.9f, 19.25f)
                verticalLineTo(11.17f)
                horizontalLineTo(134.71f)
                curveTo(137.75f, 11.17f, 139.48f, 12.65f, 139.48f, 15.2f)
                curveTo(139.48f, 17.76f, 137.62f, 19.25f, 134.75f, 19.25f)
                horizontalLineTo(128.9f)
                close()
            }
        }.build()

        return _sentryDark!!
    }

private var _sentryDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.SentryDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
