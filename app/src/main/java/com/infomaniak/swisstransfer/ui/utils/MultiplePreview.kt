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

// Mobile
@Preview(
    name = "(1) Mobile portrait light",
    group = "Mobile",
)
@Preview(
    name = "(2) Mobile portrait dark",
    group = "Mobile",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "(3) Mobile landscape light",
    group = "Mobile",
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Preview(
    name = "(4) Mobile landscape dark",
    group = "Mobile",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:parent=pixel_5,orientation=landscape",
)
annotation class PreviewMobile

// Tablet
@Preview(
    name = "(1) Tablet portrait light",
    group = "Tablet",
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
)
@Preview(
    name = "(2) Tablet portrait dark",
    group = "Tablet",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
)
@Preview(
    name = "(3) Tablet landscape light",
    group = "Tablet",
    device = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240",
)
@Preview(
    name = "(4) Tablet landscape dark",
    group = "Tablet",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240",
)
annotation class PreviewTablet
