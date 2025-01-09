plugins {
    alias(libs.plugins.android.library)
    alias(core2.plugins.kotlin.android)
}

val sharedCompileSdk: Int by rootProject.extra
val sharedMinSdk: Int by rootProject.extra
val sharedJavaVersion: JavaVersion by rootProject.extra

android {
    namespace = "com.infomaniak.core2.inappstore"
    compileSdk = sharedCompileSdk

    defaultConfig {
        minSdk = sharedMinSdk

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
    implementation(libs.play.review)
    implementation(libs.play.review.ktx)
    implementation(project(":Core2"))
}
