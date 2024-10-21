pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        // mavenLocal() // TODO Do not put this in production
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    versionCatalogs {
        create("core2") { from(files("Core2/gradle/core2.versions.toml")) }
        create("filetypes") { from(files("FileTypes/gradle/filetypes.versions.toml")) }
    }
}

rootProject.name = "android-SwissTransfer"
include(":app")
include(":Core2")
include(":Core2:Sentry")
include(":FileTypes")
