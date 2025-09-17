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
package com.infomaniak.swisstransfer.di

import com.infomaniak.multiplatform_swisstransfer.common.interfaces.BreadcrumbType
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.CrashReportInterface
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.CrashReportLevel
import io.sentry.Breadcrumb
import io.sentry.Sentry
import io.sentry.SentryEvent
import io.sentry.SentryLevel
import io.sentry.protocol.Message

val CrashReportLevel.sentryLevel: SentryLevel
    get() = when (this) {
        CrashReportLevel.DEBUG -> SentryLevel.DEBUG
        CrashReportLevel.INFO -> SentryLevel.INFO
        CrashReportLevel.WARNING -> SentryLevel.WARNING
        CrashReportLevel.ERROR -> SentryLevel.ERROR
        CrashReportLevel.FATAL -> SentryLevel.FATAL
    }

val crashReport = object : CrashReportInterface {
    override fun addBreadcrumb(
        message: String,
        category: String,
        level: CrashReportLevel,
        type: BreadcrumbType,
        data: Map<String, String>?
    ) {
        val breadcrumb = Breadcrumb()
        breadcrumb.message = message
        breadcrumb.category = category
        breadcrumb.level = level.sentryLevel
        breadcrumb.type = type.value
        data?.forEach { (key, value) -> breadcrumb.setData(key, value) }
        Sentry.addBreadcrumb(breadcrumb)
    }

    override fun capture(message: String, error: Throwable, data: Map<String, String>?) {
        val sentryEvent = SentryEvent(error).apply {
            data?.forEach { (key, value) -> setExtra(key, value) }
            this.message = Message().apply { this.message = message }
        }
        Sentry.captureEvent(sentryEvent)
    }

    override fun capture(message: String, data: Map<String, String>?, level: CrashReportLevel?) {
        Sentry.captureMessage(message, level?.sentryLevel ?: SentryLevel.INFO) { scope ->
            data?.forEach { (key, value) -> scope.setExtra(key, value) }
        }
    }
}
