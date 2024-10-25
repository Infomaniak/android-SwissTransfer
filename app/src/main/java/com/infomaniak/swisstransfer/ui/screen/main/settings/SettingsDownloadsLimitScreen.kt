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
package com.infomaniak.swisstransfer.ui.screen.main.settings

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.infomaniak.multiplatform_swisstransfer.common.models.DownloadLimit
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.OptionScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferAdvancedOptionsEnum
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun SettingsDownloadsLimitScreen(
    downloadLimit: DownloadLimit,
    navigateBack: (() -> Unit)?,
    onDownloadLimitChange: (DownloadLimit) -> Unit,
) {
    OptionScaffold(
        topAppBarTitleRes = R.string.settingsOptionDownloadLimit,
        optionTitleRes = R.string.settingsDownloadsLimitTitle,
        enumEntries = DownloadLimitOption.entries,
        selectedSettingOptionPosition = downloadLimit.ordinal,
        setSelectedSettingOptionPosition = { position -> onDownloadLimitChange(DownloadLimit.entries[position]) },
        navigateBack = navigateBack,
    )
}

enum class DownloadLimitOption(
    override val title: @Composable () -> String,
    override val imageVector: ImageVector? = null,
    override val imageVectorResId: Int? = null,
) : SettingOption, TransferAdvancedOptionsEnum {
    TWO_HUNDRED_FIFTY({ DownloadLimit.TWO_HUNDRED_FIFTY.value }),
    ONE_HUNDRED({ DownloadLimit.ONE_HUNDRED.value }),
    TWENTY({ DownloadLimit.TWENTY.value }),
    ONE({ DownloadLimit.ONE.value });

    companion object {
        fun DownloadLimit.toAdvancedOption() = when (this) {
            DownloadLimit.TWO_HUNDRED_FIFTY -> TWO_HUNDRED_FIFTY
            DownloadLimit.ONE_HUNDRED -> ONE_HUNDRED
            DownloadLimit.TWENTY -> TWENTY
            DownloadLimit.ONE -> ONE
        }
    }
}

@PreviewAllWindows
@Composable
private fun SettingsThemeScreenPreview() {
    SwissTransferTheme {
        Surface {
            SettingsDownloadsLimitScreen(
                downloadLimit = DownloadLimit.TWO_HUNDRED_FIFTY,
                navigateBack = {},
                onDownloadLimitChange = {},
            )
        }
    }
}
