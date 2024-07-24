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
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

/**
 * Sealed class representing the navigation arguments for the main navigation flow.
 */
@Serializable
sealed class MainNavigation : NavigationDestination() {
    @Serializable
    data object SentDestination : MainNavigation()
    @Serializable
    data object ReceivedDestination : MainNavigation()
    @Serializable
    data class TransferDetailsDestination(val transferId: Int) : MainNavigation()

    @Serializable
    data object SettingsDestination : MainNavigation()

    companion object {
        fun fromRoute(backStackEntry: NavBackStackEntry?, startDestination: MainNavigation): MainNavigation {
            return fromRoute(MainNavigation::class, backStackEntry, startDestination)
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
    data object TransferTypeDestination : NewTransferNavigation()
    @Serializable
    data object TransferOptionsDestination : NewTransferNavigation()
    @Serializable
    data object ValidateUserEmailDestination : NewTransferNavigation()

    @Serializable
    data object UploadProgressDestination : NewTransferNavigation()
    @Serializable
    data object UploadSuccessDestination : NewTransferNavigation()

    companion object {
        fun fromRoute(backStackEntry: NavBackStackEntry?, startDestination: NewTransferNavigation): NewTransferNavigation {
            return fromRoute(NewTransferNavigation::class, backStackEntry, startDestination)
        }
    }
}

/**
 * Sealed class representing navigation arguments with a title resource.
 */
@Serializable
sealed class NavigationDestination {
    companion object {
        fun <T : NavigationDestination> fromRoute(
            kClass: KClass<T>,
            backStackEntry: NavBackStackEntry?,
            startDestination: T,
        ): T {
            if (backStackEntry == null) return startDestination
            val route = backStackEntry.destination.route ?: ""
            val args = backStackEntry.arguments
            val subclass = kClass.sealedSubclasses.firstOrNull {
                route.contains(it.qualifiedName.toString())
            }
            return subclass?.let { createInstance(it, args) } ?: startDestination
        }

        private fun <T : NavigationDestination> createInstance(kClass: KClass<T>, bundle: Bundle?): T? {
            val primaryConstructor = kClass.constructors.firstOrNull()
            return primaryConstructor?.let {
                val args = it.parameters.associateWith { parameter ->
                    bundle?.get(parameter.name)
                }
                it.callBy(args)
            } ?: kClass.objectInstance
        }
    }
}
