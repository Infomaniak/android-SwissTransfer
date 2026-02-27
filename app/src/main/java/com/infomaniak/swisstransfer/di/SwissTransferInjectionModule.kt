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

import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.SwissTransferInjection
import com.infomaniak.multiplatform_swisstransfer.common.utils.ApiEnvironment
import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import com.infomaniak.multiplatform_swisstransfer.managers.AppSettingsManager
import com.infomaniak.multiplatform_swisstransfer.managers.FileManager
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
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
    fun provideSwissTransferInjection(@UserAgent userAgent: String): SwissTransferInjection {
        return SwissTransferInjection(
            environment = if (BuildConfig.FLAVOR == "preprod") ApiEnvironment.Preprod else ApiEnvironment.Prod,
            userAgent = userAgent,
            crashReport = crashReport
        )
    }

    @Provides
    @Singleton
    fun provideTransferManager(sti: SwissTransferInjection): TransferManager = sti.transferManager

    @Provides
    @Singleton
    fun provideFileManager(sti: SwissTransferInjection): FileManager = sti.fileManager

    @Provides
    @Singleton
    fun provideAppSettingsManager(sti: SwissTransferInjection): AppSettingsManager = sti.appSettingsManager

    @Provides
    @Singleton
    fun provideAccountManager(sti: SwissTransferInjection): AccountManager = sti.accountManager

    @Provides
    @Singleton
    fun provideUploadManager(sti: SwissTransferInjection): InMemoryUploadManager = sti.inMemoryUploadManager

    @Provides
    @Singleton
    fun provideSharedApiUrlCreator(sti: SwissTransferInjection): SharedApiUrlCreator = sti.sharedApiUrlCreator
}
