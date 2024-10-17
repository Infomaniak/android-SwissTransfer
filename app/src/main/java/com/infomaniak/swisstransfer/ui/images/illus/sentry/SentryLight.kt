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

val AppIllus.SentryLight: ImageVector
    get() {

        if (_sentryLight != null) return _sentryLight!!

        _sentryLight = Builder(
            name = "SentryLight",
            defaultWidth = 163.0.dp,
            defaultHeight = 36.0.dp,
            viewportWidth = 163.0f,
            viewportHeight = 36.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(24.23f, 2.07f)
                curveTo(23.54f, 0.93f, 22.32f, 0.24f, 21.0f, 0.24f)
                curveTo(19.68f, 0.24f, 18.46f, 0.93f, 17.77f, 2.07f)
                lineTo(12.47f, 11.15f)
                curveTo(20.72f, 15.27f, 26.16f, 23.45f, 26.78f, 32.65f)
                horizontalLineTo(23.06f)
                curveTo(22.44f, 24.77f, 17.7f, 17.8f, 10.59f, 14.33f)
                lineTo(5.68f, 22.82f)
                curveTo(9.67f, 24.61f, 12.47f, 28.31f, 13.12f, 32.64f)
                horizontalLineTo(4.57f)
                curveTo(4.36f, 32.62f, 4.18f, 32.5f, 4.07f, 32.32f)
                curveTo(3.97f, 32.14f, 3.97f, 31.92f, 4.07f, 31.74f)
                lineTo(6.44f, 27.71f)
                curveTo(5.63f, 27.04f, 4.72f, 26.52f, 3.73f, 26.18f)
                lineTo(1.38f, 30.21f)
                curveTo(0.89f, 31.05f, 0.75f, 32.06f, 1.01f, 33.01f)
                curveTo(1.27f, 33.96f, 1.89f, 34.76f, 2.74f, 35.24f)
                curveTo(3.3f, 35.55f, 3.93f, 35.72f, 4.57f, 35.72f)
                horizontalLineTo(16.28f)
                curveTo(16.73f, 30.26f, 14.28f, 24.97f, 9.83f, 21.77f)
                lineTo(11.69f, 18.54f)
                curveTo(17.31f, 22.4f, 20.47f, 28.93f, 20.0f, 35.72f)
                horizontalLineTo(29.93f)
                curveTo(30.4f, 25.43f, 25.36f, 15.66f, 16.69f, 10.08f)
                lineTo(20.46f, 3.63f)
                curveTo(20.63f, 3.34f, 21.01f, 3.24f, 21.31f, 3.41f)
                curveTo(21.73f, 3.65f, 37.67f, 31.45f, 37.97f, 31.77f)
                curveTo(38.07f, 31.96f, 38.07f, 32.2f, 37.96f, 32.39f)
                curveTo(37.84f, 32.58f, 37.64f, 32.69f, 37.42f, 32.68f)
                horizontalLineTo(33.58f)
                curveTo(33.63f, 33.71f, 33.63f, 34.73f, 33.58f, 35.76f)
                horizontalLineTo(37.43f)
                curveTo(38.42f, 35.76f, 39.37f, 35.37f, 40.07f, 34.68f)
                curveTo(40.77f, 33.98f, 41.16f, 33.03f, 41.16f, 32.04f)
                curveTo(41.16f, 31.39f, 40.99f, 30.76f, 40.66f, 30.2f)
                lineTo(24.23f, 2.07f)
                close()
                moveTo(101.09f, 23.05f)
                lineTo(89.19f, 7.68f)
                horizontalLineTo(86.22f)
                verticalLineTo(28.28f)
                horizontalLineTo(89.23f)
                verticalLineTo(12.49f)
                lineTo(101.47f, 28.28f)
                horizontalLineTo(104.1f)
                verticalLineTo(7.68f)
                horizontalLineTo(101.09f)
                verticalLineTo(23.05f)
                close()
                moveTo(71.12f, 19.23f)
                horizontalLineTo(81.79f)
                verticalLineTo(16.55f)
                horizontalLineTo(71.11f)
                verticalLineTo(10.35f)
                horizontalLineTo(83.15f)
                verticalLineTo(7.67f)
                horizontalLineTo(68.04f)
                verticalLineTo(28.28f)
                horizontalLineTo(83.3f)
                verticalLineTo(25.6f)
                horizontalLineTo(71.11f)
                lineTo(71.12f, 19.23f)
                close()
                moveTo(58.57f, 16.61f)
                curveTo(54.42f, 15.61f, 53.26f, 14.82f, 53.26f, 12.9f)
                curveTo(53.26f, 11.18f, 54.78f, 10.01f, 57.05f, 10.01f)
                curveTo(59.12f, 10.07f, 61.12f, 10.79f, 62.75f, 12.06f)
                lineTo(64.37f, 9.78f)
                curveTo(62.3f, 8.16f, 59.74f, 7.31f, 57.11f, 7.36f)
                curveTo(53.03f, 7.36f, 50.18f, 9.78f, 50.18f, 13.23f)
                curveTo(50.18f, 16.94f, 52.6f, 18.22f, 57.01f, 19.29f)
                curveTo(60.92f, 20.19f, 62.13f, 21.03f, 62.13f, 22.91f)
                curveTo(62.13f, 24.79f, 60.51f, 25.95f, 58.02f, 25.95f)
                curveTo(55.54f, 25.94f, 53.15f, 25.0f, 51.33f, 23.32f)
                lineTo(49.51f, 25.49f)
                curveTo(51.85f, 27.5f, 54.83f, 28.6f, 57.92f, 28.6f)
                curveTo(62.34f, 28.6f, 65.17f, 26.22f, 65.17f, 22.54f)
                curveTo(65.15f, 19.43f, 63.31f, 17.76f, 58.57f, 16.61f)
                close()
                moveTo(158.65f, 7.68f)
                lineTo(152.45f, 17.35f)
                lineTo(146.29f, 7.68f)
                horizontalLineTo(142.69f)
                lineTo(150.83f, 20.14f)
                verticalLineTo(28.29f)
                horizontalLineTo(153.92f)
                verticalLineTo(20.04f)
                lineTo(162.12f, 7.68f)
                horizontalLineTo(158.65f)
                close()
                moveTo(106.53f, 10.47f)
                horizontalLineTo(113.28f)
                verticalLineTo(28.29f)
                horizontalLineTo(116.38f)
                verticalLineTo(10.47f)
                horizontalLineTo(123.13f)
                verticalLineTo(7.68f)
                horizontalLineTo(106.54f)
                lineTo(106.53f, 10.47f)
                close()
                moveTo(137.45f, 20.24f)
                curveTo(140.56f, 19.38f, 142.29f, 17.2f, 142.29f, 14.09f)
                curveTo(142.29f, 10.13f, 139.39f, 7.64f, 134.72f, 7.64f)
                horizontalLineTo(125.56f)
                verticalLineTo(28.27f)
                horizontalLineTo(128.63f)
                verticalLineTo(20.87f)
                horizontalLineTo(133.83f)
                lineTo(139.05f, 28.29f)
                horizontalLineTo(142.63f)
                lineTo(136.99f, 20.37f)
                lineTo(137.45f, 20.24f)
                close()
                moveTo(128.62f, 18.23f)
                verticalLineTo(10.4f)
                horizontalLineTo(134.4f)
                curveTo(137.42f, 10.4f, 139.14f, 11.83f, 139.14f, 14.31f)
                curveTo(139.14f, 16.78f, 137.3f, 18.23f, 134.43f, 18.23f)
                horizontalLineTo(128.62f)
                close()
            }
        }.build()

        return _sentryLight!!
    }

private var _sentryLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.SentryLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
