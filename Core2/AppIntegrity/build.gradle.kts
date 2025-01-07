plugins {
    alias(libs.plugins.android.library)
    alias(core2.plugins.kotlin.android)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

val sharedCompileSdk: Int by rootProject.extra
val sharedMinSdk: Int by rootProject.extra
val sharedJavaVersion: JavaVersion by rootProject.extra

android {
    namespace = "com.infomaniak.core2.appintegrity"
    compileSdk = sharedCompileSdk

    defaultConfig {
        minSdk = sharedMinSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "APP_INTEGRITY_BASE_URL", "\"https://api.infomaniak.com\"")
        }

        debug {
            buildConfigField("String", "APP_INTEGRITY_BASE_URL", "\"https://api.preprod.dev.infomaniak.ch\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = sharedJavaVersion
        targetCompatibility = sharedJavaVersion
    }

    kotlinOptions {
        jvmTarget = sharedJavaVersion.toString()
    }
}

dependencies {

    implementation(project(":Core2:Sentry"))

    implementation(core2.integrity)
    implementation(core2.ktor.client.core)
    implementation(core2.ktor.client.content.negociation)
    implementation(core2.ktor.client.json)
    implementation(core2.ktor.client.encoding)
    implementation(core2.ktor.client.okhttp)
    implementation(core2.kotlinx.serialization.json)
    testImplementation(core2.junit)
    testImplementation(core2.ktor.client.mock)
    androidTestImplementation(core2.androidx.junit)
}
