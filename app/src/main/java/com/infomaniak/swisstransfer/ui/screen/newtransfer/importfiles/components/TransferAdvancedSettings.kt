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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.*
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun TransferAdvancedSetting(onClick: () -> Unit) {

}

enum class TransferAdvancedSetting(val buttonIcon: ImageVector, @StringRes val buttonText: Int) {
    VALIDITY_DURATION(buttonIcon = AppIcons.Clock, buttonText = R.string.settingsOptionValidityPeriod),
    DOWNLOAD_NUMBER_LIMIT(buttonIcon = AppIcons.ArrowDownFile, buttonText = R.string.settingsOptionDownloadLimit),
    PASSWORD(buttonIcon = AppIcons.Password, buttonText = R.string.settingsOptionPassword),
    LANGUAGE(buttonIcon = AppIcons.SpeechBubble, buttonText = R.string.settingsOptionEmailLanguage),
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun TransferTypeButtonsPreview() {
    SwissTransferTheme {
        Surface {
            TransferAdvancedSetting(onClick = {})
        }
    }
}
