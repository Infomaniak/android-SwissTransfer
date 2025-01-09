plugins {
    alias(libs.plugins.android.library)
    alias(core2.plugins.kotlin.android)
}

val sharedCompileSdk: Int by rootProject.extra
val sharedMinSdk: Int by rootProject.extra
val sharedJavaVersion: JavaVersion by rootProject.extra

android {
    namespace = "com.infomaniak.core2.matomo"
    compileSdk = sharedCompileSdk

    defaultConfig {
        minSdk = sharedMinSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "MATOMO_URL", "\"https://analytics.infomaniak.com/matomo.php\"")
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
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = sharedJavaVersion.toString()
    }
}

dependencies {
    api(core2.matomo)
    implementation(core2.splitties.appctx)
}
