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
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        // mavenLocal() // Do not put this in production
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    versionCatalogs {
        create("core") { from(files("Core/gradle/core.versions.toml")) }
    }
}

rootProject.name = "android-SwissTransfer"
include(
    ":app",
    ":Core",
    ":Core:AppIntegrity",
    ":Core:AppVersionChecker",
    ":Core:FileTypes",
    ":Core:InAppReview",
    ":Core:InAppUpdate",
    ":Core:Matomo",
    ":Core:Network",
    ":Core:Network:Models",
    ":Core:Notifications",
    ":Core:Onboarding",
    ":Core:Sentry",
    ":Core:Thumbnails",
    ":Core:Ui:View",
    ":Core:Ui:Compose:BasicButton",
    ":Core:Ui:Compose:Basics",
    ":Core:Ui:Compose:BottomStickyButtonScaffolds",
    ":Core:Ui:Compose:Margin",
    ":Core:Ui:Compose:Preview",
    ":Core:Ui:Compose:Theme",
    ":Core:WebView",
)
