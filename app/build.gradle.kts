import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(core.plugins.kotlin.android)
    alias(core.plugins.compose.compiler)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.sentry)
    kotlin("plugin.parcelize")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

val appCompileSdk: Int by rootProject.extra
val appMinSdk: Int by rootProject.extra
val javaVersion: JavaVersion by rootProject.extra

val envProperties = rootProject.file("env.properties").takeIf { it.exists() }?.let { file ->
    Properties().also { it.load(file.reader()) }
}

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
        versionCode = 1_03_005_01
        versionName = "1.3.5"

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
    org = "sentry"
    projectName = "swisstransfer-android"
    authToken = sentryAuthToken
    url = "https://sentry-mobile.infomaniak.com"
    includeDependenciesReport = false
    includeNativeSources = isRelease
    includeSourceContext = isRelease
    uploadNativeSymbols = isRelease
}

dependencies {

    implementation(project(":Core"))
    implementation(project(":Core:AppIntegrity"))
    implementation(project(":Core:Compose:BasicButton"))
    implementation(project(":Core:Compose:Basics"))
    implementation(project(":Core:Compose:Margin"))
    implementation(project(":Core:FileTypes"))
    implementation(project(":Core:InAppReview"))
    implementation(project(":Core:InAppUpdate"))
    implementation(project(":Core:Matomo"))
    implementation(project(":Core:Network"))
    implementation(project(":Core:Notifications"))
    implementation(project(":Core:Onboarding"))
    implementation(project(":Core:Sentry"))
    implementation(project(":Core:Thumbnails"))

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
    implementation(libs.hilt.android)
    implementation(libs.hilt.androidx.work)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.androidx.compiler)
    kapt(libs.room.processing) // TODO[workaround]: Remove when https://github.com/google/dagger/issues/4693 is fixed.
    implementation(libs.hilt.navigation.compose)

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
