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
    includeBuild("Core/build-logic")
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
            content { includeGroup("com.infomaniak.multiplatform_swisstransfer") }
        }
        maven {
            url = uri("https://jitpack.io")
            content {
                includeModule("com.github.infomaniak", "android-login")
                includeModule("com.github.lottiefiles", "dotlottie-android")
                includeModule("com.github.matomo-org", "matomo-sdk-android")
                includeModule("com.github.AppDevNext.Logcat", "LogcatCoreLib")
            }
        }
        mavenLocal() // Do not put this in production
    }
    versionCatalogs {
        create("core") { from(files("Core/gradle/core.versions.toml")) }
    }
}

plugins {
    id("com.infomaniak.core.composite")
}

rootProject.name = "android-SwissTransfer"
include(":app")
