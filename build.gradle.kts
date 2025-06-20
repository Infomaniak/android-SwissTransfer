buildscript {
    extra.apply {
        set("appCompileSdk", 35) // Ensure any extra configChanges are added into Activities' manifests.
        set("appMinSdk", 27)
        set("legacyMinSdk", 27) // Duplicated from `Core/Legacy/build.gradle` : `legacyMinSdk = 27`
        set("javaVersion", JavaVersion.VERSION_17)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(core.plugins.kotlin.android) version libs.versions.kotlin apply false
    alias(core.plugins.compose.compiler) version libs.versions.kotlin apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false
    kotlin("plugin.serialization") version libs.versions.kotlin apply false
}
