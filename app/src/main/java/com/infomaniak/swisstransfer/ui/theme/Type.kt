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
package com.infomaniak.swisstransfer.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val h1 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.SemiBold,
    fontSize = 22.sp,
    lineHeight = 28.sp,
)

private val h2 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
)

private val bodyMedium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 20.sp,
)

private val bodyRegular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 20.sp,
)

private val bodySmallMedium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 20.sp,
)

private val bodySmallRegular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
)

private val labelMedium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 18.sp,
)

private val labelRegular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 18.sp,
)

private val specificMedium22 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 22.sp,
    lineHeight = 28.sp,
)

private val specificMedium32 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 32.sp,
    lineHeight = 42.sp,
)

private val specificLight22 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Light,
    fontSize = 22.sp,
    lineHeight = 28.sp,
)

private val specificLight18 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Light,
    fontSize = 18.sp,
    lineHeight = 24.sp,
)

val Typography = CustomTypography(
    h1 = h1,
    h2 = h2,
    bodyMedium = bodyMedium,
    bodyRegular = bodyRegular,
    bodySmallMedium = bodySmallMedium,
    bodySmallRegular = bodySmallRegular,
    labelMedium = labelMedium,
    labelRegular = labelRegular,
    specificMedium32 = specificMedium32,
    specificMedium22 = specificMedium22,
    specificLight22 = specificLight22,
    specificLight18 = specificLight18,
)

@Immutable
data class CustomTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val bodyMedium: TextStyle,
    val bodyRegular: TextStyle,
    val bodySmallMedium: TextStyle,
    val bodySmallRegular: TextStyle,
    val labelMedium: TextStyle,
    val labelRegular: TextStyle,
    val specificMedium32: TextStyle,
    val specificMedium22: TextStyle,
    val specificLight22: TextStyle,
    val specificLight18: TextStyle,
)
