plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

android {
    namespace = "com.infomaniak.appintegrity"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // implementation(libs.androidx.core.ktx)
    implementation(core2.integrity)
    implementation(core2.ktor.client.core)
    implementation(core2.ktor.client.content.negociation)
    implementation(core2.ktor.client.json)
    implementation(core2.ktor.client.encoding)
    implementation(core2.ktor.client.okhttp)
    api(core2.kotlinx.serialization.json)
    // testImplementation(core2.junit)
    // androidTestImplementation(core2.androidx.junit)
}
