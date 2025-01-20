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
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkManager
import com.infomaniak.core.appintegrity.AppIntegrityManager
import com.infomaniak.core.buildUserAgent
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.MainApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun providesMainApplication(application: Application) = application as MainApplication

    @Provides
    @Singleton
    fun providesWorkManager(@ApplicationContext appContext: Context) = WorkManager.getInstance(appContext)

    @UserAgent
    @Provides
    @Singleton
    fun providesUserAgent(): String {
        return buildUserAgent(
            appId = BuildConfig.APPLICATION_ID,
            appVersionCode = BuildConfig.VERSION_CODE,
            appVersionName = BuildConfig.VERSION_NAME,
        )
    }

    @Provides
    @Singleton
    fun providesAppIntegrityManager(application: Application, @UserAgent userAgent: String): AppIntegrityManager {
        return AppIntegrityManager(application, userAgent)
    }

    @Provides
    @Singleton
    fun providesNotificationManagerCompat(@ApplicationContext appContext: Context): NotificationManagerCompat {
        return NotificationManagerCompat.from(appContext)
    }
}
