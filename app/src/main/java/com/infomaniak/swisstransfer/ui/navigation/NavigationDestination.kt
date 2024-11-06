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

import android.os.Bundle
import androidx.navigation.NavBackStackEntry
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferType
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * Sealed class representing the navigation arguments for the main navigation flow.
 */
@Serializable
sealed class MainNavigation : NavigationDestination() {
    var enableTransition = true

    @Serializable
    data object SentDestination : MainNavigation()
    @Serializable
    data object ReceivedDestination : MainNavigation()
    @Serializable
    data class TransferDetailsDestination(val transferId: Int) : MainNavigation()

    @Serializable
    data object SettingsDestination : MainNavigation()

    companion object {
        val startDestination = SentDestination
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
    data object TransferTypeDestination : NewTransferNavigation()
    @Serializable
    data object TransferOptionsDestination : NewTransferNavigation()
    @Serializable
    data object ValidateUserEmailDestination : NewTransferNavigation()

    @Serializable
    data class UploadProgressDestination(val totalSize: Long) : NewTransferNavigation()
    @Serializable
    data class UploadSuccessDestination(val transferType: TransferType, val transferLink: String) : NewTransferNavigation()

    companion object {
        val startDestination = ImportFilesDestination
    }
}

/**
 * Sealed class representing navigation arguments with a title resource.
 */
@Serializable
sealed class NavigationDestination {

    companion object {

        inline fun <reified T : NavigationDestination> NavBackStackEntry.toDestination(): T? {
            return toDestination(T::class, backStackEntry = this)
        }

        fun <T : NavigationDestination> toDestination(kClass: KClass<T>, backStackEntry: NavBackStackEntry?): T? {

            fun kClassFromRoute(route: String) = kClass.sealedSubclasses.firstOrNull {
                route.contains(it.qualifiedName.toString())
            }

            if (backStackEntry == null) return null

            val route = backStackEntry.destination.route ?: ""
            val args = backStackEntry.arguments
            val subclass = kClassFromRoute(route) ?: return null

            return createInstance(subclass, args)
        }

        private fun <T : NavigationDestination> createInstance(kClass: KClass<T>, bundle: Bundle?): T? {
            return kClass.primaryConstructor?.let {
                val args = it.parameters.associateWith { parameter -> bundle?.get(parameter.name) }
                it.callBy(args)
            } ?: kClass.objectInstance
        }
    }
}
