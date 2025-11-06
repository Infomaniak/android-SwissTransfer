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
package com.infomaniak.swisstransfer.di

import com.infomaniak.multiplatform_swisstransfer.SwissTransferInjection
import com.infomaniak.multiplatform_swisstransfer.common.utils.ApiEnvironment
import com.infomaniak.swisstransfer.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SwissTransferInjectionModule {

    @Provides
    @Singleton
    fun providesSwissTransferInjection(@UserAgent userAgent: String): SwissTransferInjection {
        return SwissTransferInjection(
            environment = if (BuildConfig.FLAVOR == "preprod") ApiEnvironment.Preprod else ApiEnvironment.Prod,
            userAgent = userAgent,
            crashReport = crashReport
        )
    }

    @Provides
    @Singleton
    fun providesTransferManager(swissTransferInjection: SwissTransferInjection) = swissTransferInjection.transferManager

    @Provides
    @Singleton
    fun providesFileManager(swissTransferInjection: SwissTransferInjection) = swissTransferInjection.fileManager

    @Provides
    @Singleton
    fun providesAppSettingsManager(swissTransferInjection: SwissTransferInjection) = swissTransferInjection.appSettingsManager

    @Provides
    @Singleton
    fun providesAccountManager(swissTransferInjection: SwissTransferInjection) = swissTransferInjection.accountManager

    @Provides
    @Singleton
    fun providesUploadManager(swissTransferInjection: SwissTransferInjection) = swissTransferInjection.inMemoryUploadManager

    @Provides
    @Singleton
    fun providesSharedApiUrlCreator(swissTransferInjection: SwissTransferInjection) = swissTransferInjection.sharedApiUrlCreator
}
