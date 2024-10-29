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

import android.app.Application
import com.infomaniak.multiplatform_swisstransfer.SwissTransferInjection
import com.infomaniak.swisstransfer.ui.MainActivity
import androidx.work.WorkManager
import com.infomaniak.swisstransfer.ui.MainApplication
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun providesMainApplication(application: Application) = application as MainApplication

    @Provides
    @Singleton
    fun providesGlobalCoroutineScope(@DefaultDispatcher defaultDispatcher: CoroutineDispatcher): CoroutineScope {
        return CoroutineScope(defaultDispatcher)
    }

    @Provides
    @Singleton
    fun provideMatomoSwissTransfer(application: MainApplication) = MatomoSwissTransfer(application)

    @Provides
    @Singleton
    fun provideMatomoTracker(matomo: MatomoSwissTransfer) = matomo.tracker

    @Provides
    @Singleton
    fun providesWorkManager(@ApplicationContext context: Context) = WorkManager.getInstance(context)
}
