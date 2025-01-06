import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(core2.plugins.kotlin.android)
    alias(core2.plugins.compose.compiler)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.sentry)
    kotlin("plugin.parcelize")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

val sharedCompileSdk: Int by rootProject.extra
val sharedMinSdk: Int by rootProject.extra
val sharedJavaVersion: JavaVersion by rootProject.extra

val envProperties = rootProject.file("env.properties").takeIf { it.exists() }?.let { file ->
    Properties().also { it.load(file.reader()) }
}

val sentryAuthToken = envProperties?.getProperty("sentryAuthToken").takeUnless { it.isNullOrBlank() }
    ?: error("The `sentryAuthToken` property in `env.properties` must be specified (see `env.example.properties`).")

android {
    namespace = "com.infomaniak.swisstransfer"
    compileSdk = sharedCompileSdk

    defaultConfig {
        applicationId = "com.infomaniak.swisstransfer"
        minSdk = sharedMinSdk
        targetSdk = sharedCompileSdk
        versionCode = 1_00_000_03
        versionName = "1.0.0-Alpha5"

        setProperty("archivesBaseName", "swisstransfer-$versionName ($versionCode)")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val preprodHost = "swisstransfer.preprod.dev.infomaniak.ch"
        val prodHost = "www.swisstransfer.com"
        resValue("string", "preprod_host", preprodHost)
        resValue("string", "prod_host", prodHost)
        buildConfigField("String", "PREPROD_URL", "\"https://$preprodHost\"")
        buildConfigField("String", "PROD_URL", "\"https://$prodHost\"")

        buildConfigField("String", "GITHUB_REPO_URL", "\"https://github.com/Infomaniak/android-SwissTransfer\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = sharedJavaVersion
        targetCompatibility = sharedJavaVersion
    }
    kotlinOptions {
        jvmTarget = sharedJavaVersion.toString()
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
    uploadNativeSymbols = true
    includeNativeSources = true
    includeSourceContext = true
}

dependencies {

    implementation(project(":Core2"))
    implementation(project(":Core2:AppIntegrity"))
    implementation(project(":Core2:Compose:Core"))
    implementation(project(":Core2:Matomo"))
    implementation(project(":Core2:Network"))
    implementation(project(":Core2:Onboarding"))
    implementation(project(":Core2:Sentry"))
    implementation(project(":FileTypes"))

    implementation(kotlin("reflect"))

    implementation(core2.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(core2.compose.bom))
    implementation(libs.compose.foundation) // TODO: To be removed when compose 1.8.0 is stable
    implementation(core2.compose.ui.android)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.material3)
    implementation(libs.compose.material3.adaptative.navigation)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)

    coreLibraryDesugaring(libs.desugar.jdk)

    implementation(libs.swisstransfer.core)
    implementation(libs.swisstransfer.network)

    implementation(libs.coil.compose)
    implementation(libs.qrose)

    // Compose preview tools
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(core2.compose.ui.tooling)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.androidx.work)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.androidx.compiler)
    implementation(libs.hilt.navigation.compose)

    // Others
    implementation(core2.kotlinx.serialization.json)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.recaptcha)
    implementation(libs.workmanager)

    // Test
    testImplementation(core2.junit)
    androidTestImplementation(core2.androidx.junit)
}
