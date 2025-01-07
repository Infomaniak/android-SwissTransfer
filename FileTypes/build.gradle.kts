plugins {
    alias(libs.plugins.android.library)
    alias(core2.plugins.kotlin.android)
    alias(core2.plugins.compose.compiler)
}

val sharedCompileSdk: Int by rootProject.extra
val sharedMinSdk: Int by rootProject.extra
val sharedJavaVersion: JavaVersion by rootProject.extra

android {
    namespace = "com.infomaniak.library.filetypes"
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
        }
    }
    compileOptions {
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
}

dependencies {
    implementation(core2.androidx.core.ktx)
    implementation(platform(core2.compose.bom))
    implementation(core2.compose.foundation.android)
    implementation(core2.compose.ui)
    implementation(core2.compose.ui.tooling.preview)

    testImplementation(core2.junit)
    androidTestImplementation(core2.androidx.junit)
    debugImplementation(core2.compose.ui.tooling)
}
