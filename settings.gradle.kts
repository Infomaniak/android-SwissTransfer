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
        // mavenLocal() // Do not put this in production
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    versionCatalogs {
        create("core") { from(files("Core/gradle/core.versions.toml")) }
    }
}

rootProject.name = "android-SwissTransfer"
include(
    ":app",
    ":Core",
    ":Core:AppIntegrity",
    ":Core:Compose:BasicButton",
    ":Core:Compose:Basics",
    ":Core:Compose:Margin",
    ":Core:Compose:Preview",
    ":Core:FileTypes",
    ":Core:InAppReview",
    ":Core:InAppUpdate",
    ":Core:Matomo",
    ":Core:Network",
    ":Core:Network:Models",
    ":Core:Notifications",
    ":Core:Onboarding",
    ":Core:Sentry",
    ":Core:Thumbnails",
    ":Core:WebView",
)
