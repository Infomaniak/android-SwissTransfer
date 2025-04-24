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

val sentryAuthToken = envProperties?.getProperty("sentryAuthToken").takeUnless { it.isNullOrBlank() }
    ?: error("The `sentryAuthToken` property in `env.properties` must be specified (see `env.example.properties`).")

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
        versionCode = 1_01_001_01
        versionName = "1.1.1"

        setProperty("archivesBaseName", "swisstransfer-$versionName ($versionCode)")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        resValue("string", "preprod_host", preprodHost)
        resValue("string", "prod_host", prodHost)

        buildConfigField("String", "GITHUB_REPO_URL", "\"https://github.com/Infomaniak/android-SwissTransfer\"")
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
        create("preprod") {
            dimension = "env"
            applicationIdSuffix = ".preprod"
            buildConfigField("String", "BASE_URL", "\"https://$preprodHost\"")
        }
        create("prod") {
            dimension = "env"
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
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

sentry {
    org = "sentry"
    projectName = "swisstransfer-android"
    authToken = sentryAuthToken
    url = "https://sentry-mobile.infomaniak.com"
    includeDependenciesReport = false
    includeNativeSources = true
    includeSourceContext = true
    uploadNativeSymbols = true
}

dependencies {

    implementation(project(":Core"))
    implementation(project(":Core:AppIntegrity"))
    implementation(project(":Core:Compose:Basics"))
    implementation(project(":Core:FileTypes"))
    implementation(project(":Core:InAppStore"))
    implementation(project(":Core:Matomo"))
    implementation(project(":Core:Network"))
    implementation(project(":Core:Notifications"))
    implementation(project(":Core:Onboarding"))
    implementation(project(":Core:Sentry"))
    implementation(project(":Core:Thumbnails"))

    implementation(kotlin("reflect"))

    implementation(core.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(core.compose.bom))
    implementation(libs.compose.foundation) // TODO: To be removed when compose 1.8.0 is stable
    implementation(core.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(core.compose.material3)
    implementation(libs.compose.material3.adaptative.navigation)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.accompanist.permissions)

    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)

    coreLibraryDesugaring(libs.desugar.jdk)

    implementation(libs.swisstransfer.core)
    implementation(libs.swisstransfer.network)

    implementation(libs.coil.compose)
    implementation(libs.coil.video)
    implementation(libs.qrose)

    // Compose preview tools
    implementation(core.compose.ui.tooling.preview)
    debugImplementation(core.compose.ui.tooling)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.androidx.work)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.androidx.compiler)
    kapt(libs.room.processing) //TODO[workaround]: Remove when https://github.com/google/dagger/issues/4693 is fixed.
    implementation(libs.hilt.navigation.compose)

    // Others
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.lottie)
    implementation(libs.workmanager)
    implementation(core.kotlinx.serialization.json)
    implementation(core.splitties.toast)

    // Test
    testImplementation(core.junit)
    androidTestImplementation(core.androidx.junit)
}
