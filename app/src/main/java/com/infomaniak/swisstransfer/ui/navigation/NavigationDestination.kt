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
package com.infomaniak.swisstransfer.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.SettingsDestination.getDeeplinkDirection
import com.infomaniak.swisstransfer.ui.screen.main.DeeplinkViewModel.Companion.SENT_DEEPLINK_SUFFIX
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi
import kotlinx.serialization.Serializable

/**
 * Sealed class representing the navigation arguments for the main navigation flow.
 */
@Serializable
sealed class MainNavigation : NavigationDestination() {
    var enableTransition = true

    protected inline fun <reified T : MainNavigation> NavGraphBuilder.getDeeplinkDirection(
        noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit),
        transferUuidName: String,
        suffix: String = "",
    ) {
        val basePath = "${BuildConfig.BASE_URL}/d/{$transferUuidName}$suffix"
        val deeplinks = listOf(navDeepLink<T>(basePath))

        composable<T>(deepLinks = deeplinks, content = content)
    }

    // If it has to be renamed, don't forget to rename `*DestinationName` in the companion object too.
    @Serializable
    data class SentDestination(val transferUuid: String? = null) : MainNavigation() {

        companion object {
            fun NavGraphBuilder.sentDestination(content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)) {
                getDeeplinkDirection<SentDestination>(content, SentDestination::transferUuid.name, SENT_DEEPLINK_SUFFIX)
            }
        }
    }

    // If it has to be renamed, don't forget to rename `*DestinationName` in the companion object too.
    @Serializable
    data class ReceivedDestination(val transferUuid: String? = null) : MainNavigation() {

        companion object {
            fun NavGraphBuilder.receivedDestination(content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)) {
                getDeeplinkDirection<ReceivedDestination>(content, ReceivedDestination::transferUuid.name)
            }
        }
    }

    @Serializable
    data object SettingsDestination : MainNavigation()

    companion object {
        private val TAG = MainNavigation::class.java.simpleName
        val startDestination = SentDestination()

        // Because of the minification, we can't directly use the classes names here. It won't work in production environment.
        // So, if these classes have to be renamed, they need to be renamed here too.
        // Don't worry if you forget, there's a unit test about that.
        private const val sentDestinationName = "SentDestination"
        private const val receivedDestinationName = "ReceivedDestination"
        private const val settingsDestinationName = "SettingsDestination"
        val destinationsNames = listOf(
            sentDestinationName,
            receivedDestinationName,
            settingsDestinationName,
        )

        fun NavBackStackEntry.toMainDestination(): MainNavigation? {
            return runCatching {
                val destinationRoute = destination.route ?: error("Destination route cannot be empty")
                when (destinationsNames.firstOrNull { destinationRoute.contains(it) }) {
                    sentDestinationName -> this.toRoute<SentDestination>()
                    receivedDestinationName -> this.toRoute<ReceivedDestination>()
                    settingsDestinationName -> this.toRoute<SettingsDestination>()
                    else -> error("Destination $destinationRoute is not handled")
                }
            }.getOrElse { exception ->
                SentryLog.e(TAG, "toMainDestination: Failure", exception)
                null
            }
        }
    }
}

/**
 * Sealed class representing the navigation arguments for the new transfer flow.
 */
@Serializable
sealed class NewTransferNavigation : NavigationDestination() {

    @Serializable
    data object ImportFilesDestination : NewTransferNavigation()

    @Serializable
    data class ValidateUserEmailDestination(val userEmail: String) : NewTransferNavigation()

    @Serializable
    data class UploadProgressDestination(
        val transferType: TransferTypeUi,
        val totalSize: Long,
    ) : NewTransferNavigation()

    @Serializable
    data class UploadSuccessDestination(
        val transferType: TransferTypeUi,
        val transferUuid: String,
        val transferUrl: String,
    ) : NewTransferNavigation()

    @Serializable
    data class UploadErrorDestination(
        val transferType: TransferTypeUi,
        val totalSize: Long,
    ) : NewTransferNavigation()

    @Serializable
    data object NewTransferFilesDetailsDestination : NewTransferNavigation()

    companion object {
        val startDestination = ImportFilesDestination
    }
}

/**
 * Sealed class representing navigation arguments with a title resource.
 */
@Serializable
sealed class NavigationDestination
