buildscript {
    extra.apply {
        set("sharedTargetSdk", 35)
        set("sharedMinSdk", 24)
        set("javaVersion", JavaVersion.VERSION_17)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
}
