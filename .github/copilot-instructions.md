# Copilot Coding Agent Onboarding — android-SwissTransfer

> **Read `AGENTS.md` first** for architecture and conventions. This file covers build, CI, and validation.

## Overview
SwissTransfer for Android — secure file transfer up to 50 GB. Pure Kotlin + Jetpack Compose, Hilt DI, KMP business/networking logic via the `multiplatform-SwissTransfer` library. Two flavors: `prod` and `preprod`.

## One-Time Environment Setup
```bash
git submodule update --init --recursive   # Core submodule — required for Gradle settings plugin
cp env.example.properties env.properties
echo "sentryAuthToken=DummyToken" > env.properties   # required even for debug builds
```
Missing `env.properties` → Gradle config phase fails.

## Build & Test (CI: `.github/workflows/android.yml`)
```bash
./gradlew clean
./gradlew assembleDebug          # or assembleProdDebug / assemblePreprodDebug
./gradlew lint
./gradlew testProdDebugUnitTest --stacktrace
```
CI runs all four steps in order on every non-draft PR.

## Project Layout
```
app/
├── src/
│   ├── main/java/com/infomaniak/swisstransfer/
│   │   ├── di/            # Hilt modules
│   │   ├── ui/            # Compose screens (upload, download, settings, transfers list)
│   │   └── workers/       # WorkManager (upload chunking)
│   ├── prod/              # Prod-flavor sources (API endpoints, app ID)
│   └── preprod/           # Preprod-flavor overrides
Core/                       # Git submodule — Infomaniak Core
gradle/libs.versions.toml
settings.gradle.kts         # includes Core/build-logic
```

## Key Rules
- All networking/business logic lives in `multiplatform-SwissTransfer` (KMP) — Android layer is UI + DI only.
- `prod` is the default/release flavor; `preprod` points to staging servers.
- No Firebase or Google services — app is distributed on both Google Play and F-Droid.
- All user-visible strings in `res/values/strings.xml`.
- When adding/removing a runtime dependency, update `LICENSES.md` at the repo root.
