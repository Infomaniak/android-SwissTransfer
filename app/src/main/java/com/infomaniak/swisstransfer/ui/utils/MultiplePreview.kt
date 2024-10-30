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

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

// Small window (ex: phone)
@Preview(
    name = "(1) Small window portrait light",
    group = "SmallWindow",
)
@Preview(
    name = "(2) Small window portrait dark",
    group = "SmallWindow",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "(3) Small window landscape light",
    group = "SmallWindow",
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Preview(
    name = "(4) Small window landscape dark",
    group = "SmallWindow",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:parent=pixel_5,orientation=landscape",
)
annotation class PreviewSmallWindow

// Large window (ex: tablet)
@Preview(
    name = "(1) Large window portrait light",
    group = "LargeWindow",
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
)
@Preview(
    name = "(2) Large window portrait dark",
    group = "LargeWindow",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
)
@Preview(
    name = "(3) Large window landscape light",
    group = "LargeWindow",
    device = "spec:width=1280dp,height=800dp,dpi=240",
)
@Preview(
    name = "(4) Large window landscape dark",
    group = "LargeWindow",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1280dp,height=800dp,dpi=240",
)
annotation class PreviewLargeWindow

// Small + Large
@PreviewSmallWindow
@PreviewLargeWindow
annotation class PreviewAllWindows
