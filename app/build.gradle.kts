plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.sentry)
    kotlin("plugin.parcelize")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

val sharedMinSdk: Int by rootProject.extra
val sharedTargetSdk: Int by rootProject.extra
val javaVersion: JavaVersion by rootProject.extra

android {
    namespace = "com.infomaniak.swisstransfer"
    compileSdk = sharedTargetSdk

    defaultConfig {
        applicationId = "com.infomaniak.swisstransfer"
        minSdk = sharedMinSdk
        targetSdk = sharedTargetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "RECAPTCHA_API_SITE_KEY", "\"6LfaxOgpAAAAAI3Sj4rtB2oAFjkRJILiGEt-LUsc\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
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
    // Enables or disables the automatic upload of mapping files during a build.
    // If you disable this, you'll need to manually upload the mapping files with sentry-cli when you do a release.
    // Default is enabled.
    autoUploadProguardMapping = true

    // Does or doesn't include the source code of native code for Sentry.
    // This executes sentry-cli with the --include-sources param. automatically so you don't need to do it manually.
    // Default is disabled.
    includeNativeSources = true
}

dependencies {
    implementation(project(":Core2"))
    implementation(project(":Core2:Sentry"))
    implementation(project(":Core2:Matomo"))
    implementation(project(":FileTypes"))
    implementation(kotlin("reflect"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.foundation) //TODO: To be removed when compose 1.8.0 is stable
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.material3)
    implementation(libs.compose.material3.adaptative.navigation)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)

    implementation(libs.swisstransfer.core)
    implementation(libs.swisstransfer.network)

    implementation(libs.coil.compose)
    implementation(libs.qrose)

    // Compose preview tools
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.androidx.work)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.androidx.compiler)
    implementation(libs.hilt.navigation.compose)

    // Others
    implementation(libs.kotlinx.serialization)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.recaptcha)
    implementation(libs.workmanager)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
