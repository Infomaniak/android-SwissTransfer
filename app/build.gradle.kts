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
import java.util.Properties

/**
 * Don't change the order in this `plugins` block, it will mess things up.
 */
plugins {
    alias(core.plugins.android.application)
    alias(core.plugins.dagger.hilt)
    alias(core.plugins.compose.compiler)
    alias(core.plugins.kapt)
    alias(core.plugins.kotlin.android)
    alias(core.plugins.sentry.plugin)
    kotlin("plugin.parcelize")
    kotlin("plugin.serialization") version core.versions.kotlin
}

val appCompileSdk: Int by rootProject.extra
val appMinSdk: Int by rootProject.extra
val javaVersion: JavaVersion by rootProject.extra

android {
    namespace = "com.infomaniak.swisstransfer"
    compileSdk = appCompileSdk

    // Those urls are duplicated with the ones we have in KMP so don't forget to change them also in KMP
    val preprodHost = "swisstransfer.preprod.dev.infomaniak.ch"
    val prodHost = "www.swisstransfer.com"

    defaultConfig {
        applicationId = "com.infomaniak.swisstransfer"
        minSdk = appMinSdk
        targetSdk = appCompileSdk
        versionCode = 1_04_000_02
        versionName = "1.4.0"

        setProperty("archivesBaseName", "swisstransfer-$versionName ($versionCode)")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        resValue("string", "preprod_host", preprodHost)
        resValue("string", "prod_host", prodHost)

        buildConfigField("String", "GITHUB_REPO_URL", "\"https://github.com/Infomaniak/android-SwissTransfer\"")

        missingDimensionStrategy(dimension = "distribution", requestedValue = "standard")
    }

    val debugSigningConfig = signingConfigs.getByName("debug") {
        storeFile = rootProject.file("debug.keystore")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            applicationIdSuffix = ".debug"
            signingConfig = debugSigningConfig
        }
    }
    androidComponents.onVariants { variant ->
        if (variant.buildType == "release") return@onVariants
        variant.outputs.forEach { output ->
            // Pin the versionCode to 1 for all non-release variants,
            // to avoid the issue where we have to reinstall the app
            // after checking out an older branch because
            // the versionCode is lower than the one installed.
            output.versionCode = 1
        }
    }

    productFlavors {
        // Remember to update the command to build the app in android.yml to build the new flavor when you add a new flavor here
        create("preprod") {
            applicationIdSuffix = ".preprod"
            buildConfigField("String", "BASE_URL", "\"https://$preprodHost\"")
        }
        create("prod") {
            buildConfigField("String", "BASE_URL", "\"https://$prodHost\"")
            isDefault = true
        }
    }

    buildFeatures {
        flavorDimensions += "env"
        buildConfig = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

val isRelease = gradle.startParameter.taskNames.any { it.contains("release", ignoreCase = true) }

val envProperties = rootProject.file("env.properties")
    .takeIf { it.exists() }
    ?.let { file -> Properties().also { it.load(file.reader()) } }

val sentryAuthToken = envProperties?.getProperty("sentryAuthToken")
    .takeUnless { it.isNullOrBlank() }
    ?: if (isRelease) error("The `sentryAuthToken` property in `env.properties` must be specified (see `env.example.properties`).") else ""

configurations.configureEach {
    // The Matomo SDK logs network issues to Timber, and the Sentry plugin detects the Timber dependency,
    // and adds its integration, which generates noise.
    // Since we're not using Timber for anything else, it's safe to completely disabled it,
    // as specified in Sentry's documentation: https://docs.sentry.io/platforms/android/integrations/timber/#disable
    exclude(group = "io.sentry", module = "sentry-android-timber")
}

sentry {
    autoInstallation.sentryVersion.set(core.versions.sentry)
    org = "sentry"
    projectName = "swisstransfer-android"
    authToken = sentryAuthToken
    url = "https://sentry-mobile.infomaniak.com"
    includeDependenciesReport = false
    includeSourceContext = isRelease

    // Enables or disables the automatic upload of mapping files during a build.
    // If you disable this, you'll need to manually upload the mapping files with sentry-cli when you do a release.
    // Default is enabled.
    autoUploadProguardMapping = true

    // Disables or enables the automatic configuration of Native Symbols for Sentry.
    // This executes sentry-cli automatically so you don't need to do it manually.
    // Default is disabled.
    uploadNativeSymbols = isRelease

    // Does or doesn't include the source code of native code for Sentry.
    // This executes sentry-cli with the --include-sources param. automatically so you don't need to do it manually.
    // Default is disabled.
    includeNativeSources = isRelease
}

dependencies {

    implementation(core.infomaniak.core.appintegrity)
    implementation(core.infomaniak.core.common)
    implementation(core.infomaniak.core.filetypes)
    implementation(core.infomaniak.core.inappreview)
    implementation(core.infomaniak.core.inappupdate)
    implementation(core.infomaniak.core.matomo)
    implementation(core.infomaniak.core.network)
    implementation(core.infomaniak.core.notifications)
    implementation(core.infomaniak.core.onboarding)
    implementation(core.infomaniak.core.sentry)
    implementation(core.infomaniak.core.thumbnails)
    implementation(core.infomaniak.core.ui.compose.basicbutton)
    implementation(core.infomaniak.core.ui.compose.basics)
    implementation(core.infomaniak.core.ui.compose.bottomstickybuttonscaffolds)
    implementation(core.infomaniak.core.ui.compose.margin)
    implementation(core.infomaniak.core.ui.compose.preview)
    implementation(core.infomaniak.core.ui.compose.theme)

    implementation(kotlin("reflect"))

    implementation(core.androidx.core.ktx)
    implementation(core.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(core.activity.compose)
    implementation(platform(core.compose.bom))
    implementation(core.compose.foundation) // TODO: To be removed when compose 1.8.0 is stable
    implementation(core.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(core.compose.material3)
    implementation(libs.compose.material3.adaptative.navigation)
    implementation(core.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.accompanist.permissions)

    implementation(core.androidx.adaptive)
    implementation(core.androidx.adaptive.layout)
    implementation(core.androidx.adaptive.navigation)

    coreLibraryDesugaring(libs.desugar.jdk)

    implementation(libs.swisstransfer.core)
    implementation(libs.swisstransfer.network)

    implementation(core.coil.compose)
    implementation(core.coil.video)
    implementation(libs.qrose)

    // Compose preview tools
    implementation(core.compose.ui.tooling.preview)
    debugImplementation(core.compose.ui.tooling)

    // Hilt
    implementation(core.hilt.android)
    implementation(core.hilt.work)
    kapt(core.hilt.compiler)
    kapt(core.hilt.androidx.compiler)
    kapt(libs.room.processing) // TODO[workaround]: Remove when https://github.com/google/dagger/issues/4693 is fixed.
    implementation(core.hilt.navigation.compose)

    // Others
    implementation(libs.androidx.core.splashscreen)
    implementation(core.androidx.datastore.preferences)
    implementation(core.lottie.compose)
    implementation(core.androidx.work.runtime.ktx)
    implementation(core.kotlinx.serialization.json)
    implementation(core.splitties.toast)

    // Test
    testImplementation(core.junit)
    androidTestImplementation(core.androidx.junit)
}
