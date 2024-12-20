buildscript {
    extra.apply {
        set("sharedCompileSdk", 35)
        set("sharedMinSdk", 24)
        set("sharedJavaVersion", JavaVersion.VERSION_17)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.android.library) apply false
}
