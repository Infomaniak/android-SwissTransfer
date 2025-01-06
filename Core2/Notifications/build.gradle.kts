plugins {
    alias(libs.plugins.android.library)
    alias(core2.plugins.kotlin.android)
}

val sharedCompileSdk: Int by rootProject.extra
val sharedMinSdk: Int by rootProject.extra
val sharedJavaVersion: JavaVersion by rootProject.extra

android {
    namespace = "com.infomaniak.core2.notifications"
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
}

dependencies {
    implementation(project(":Core2"))
    implementation(core2.androidx.core.ktx)
}
