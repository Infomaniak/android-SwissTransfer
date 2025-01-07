plugins {
    alias(libs.plugins.android.library)
    alias(core2.plugins.kotlin.android)
    alias(core2.plugins.compose.compiler)
}

val sharedCompileSdk: Int by rootProject.extra
val sharedMinSdk: Int by rootProject.extra
val sharedJavaVersion: JavaVersion by rootProject.extra

android {
    namespace = "com.infomaniak.core2.onboarding"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    kotlinOptions {
        jvmTarget = sharedJavaVersion.toString()
    }
}

dependencies {
    implementation(platform(core2.compose.bom))
    implementation(core2.androidx.core.ktx)
    implementation(core2.compose.foundation)
    implementation(core2.compose.material3)
    implementation(core2.compose.runtime)
    implementation(core2.compose.ui.tooling.preview)

    debugImplementation(core2.compose.ui.tooling)
}
