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
package com.infomaniak.swisstransfer.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

object TextUtils {
    @Composable
    fun assembleWithBoldArgument(template: String, argument1: String, argument2: String? = null): AnnotatedString {
        val description = try {
            if (argument2 != null) {
                String.format(template, argument1, argument2)
            } else {
                String.format(template, argument1)
            }
        } catch (e: java.util.IllegalFormatException) {
            template
        }

        return buildAnnotatedString {
            append(description)

            val startIndex1 = description.indexOf(argument1)
            if (startIndex1 != -1) {
                addStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold, color = SwissTransferTheme.colors.primaryTextColor),
                    start = startIndex1,
                    end = startIndex1 + argument1.length
                )
            }

            if (argument2 != null) {
                val startIndex2 = description.indexOf(argument2)
                if (startIndex2 != -1) {
                    addStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold, color = SwissTransferTheme.colors.primaryTextColor),
                        start = startIndex2,
                        end = startIndex2 + argument2.length
                    )
                }
            }
        }
    }
}
