/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.core.extensions.goToPlayStore
import com.infomaniak.core.inappstore.R
import com.infomaniak.core.inappstore.updaterequired.UpdateRequiredActivity
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.MainActivity
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotDead.MascotDead
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun UpdateRequiredScreen() {
    val context = LocalContext.current

    BottomStickyButtonScaffold(
        topBar = { },
        bottomButton = {
            LargeButton(
                modifier = it,
                style = ButtonType.Primary,
                title = stringResource(R.string.updateInstallButton),
                onClick = {
                    // TODO: Maybe we want to use the BottomSheet provided by Google.
                    if (BuildConfig.DEBUG) {
                        // The appended `.debug` to the packageName in debug mode should be removed if we want to test this.
                        context.goToPlayStore("com.infomaniak.swisstransfer")
                    } else {
                        context.goToPlayStore()
                    }
                },
            )

            Spacer(Modifier.height(Margin.Large))
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            val paddedModifier = Modifier.padding(horizontal = Margin.Medium)

            Image(
                modifier = paddedModifier,
                imageVector = AppIllus.MascotDead.image(),
                contentDescription = null
            )

            Spacer(Modifier.height(Margin.Large))

            Text(
                text = stringResource(R.string.updateAppTitle),
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.bodyMedium,
                color = SwissTransferTheme.colors.primaryTextColor,
                modifier = paddedModifier,
            )

            Spacer(Modifier.height(Margin.Large))

            Text(
                text = stringResource(R.string.updateRequiredDescription),
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.bodyRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                modifier = paddedModifier,
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            UpdateRequiredScreen()
        }
    }
}
