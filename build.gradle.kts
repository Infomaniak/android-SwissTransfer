buildscript {
    extra.apply {
        set("appCompileSdk", 35) // Ensure any extra configChanges are added into Activities' manifests.
        set("appMinSdk", 24)
        set("javaVersion", JavaVersion.VERSION_17)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(core.plugins.kotlin.android) apply false
    alias(core.plugins.compose.compiler) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false
}
