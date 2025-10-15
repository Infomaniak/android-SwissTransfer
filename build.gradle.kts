buildscript {
    extra.apply {
        set("appCompileSdk", 36) // Ensure any extra configChanges are added into Activities' manifests.
        set("appTargetSdk", 35)
        set("appMinSdk", 27)
        set("legacyMinSdk", 27) // Duplicated from `Core/Legacy/build.gradle` : `legacyMinSdk = 27`
        set("javaVersion", JavaVersion.VERSION_17)
    }
}

plugins {
    alias(core.plugins.compose.compiler)  apply false
    alias(core.plugins.kapt) apply false
    alias(core.plugins.kotlin.android)  apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    kotlin("plugin.serialization") version core.versions.kotlin apply false
}
