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
./gradlew assembleDebug
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
│   │   ├── ui/            # Compose screens
│   │   └── workers/       # WorkManager (upload chunking)
│   ├── prod/              # Prod-flavor sources
│   └── preprod/           # Preprod-flavor overrides
Core/                       # Git submodule — Infomaniak Core
gradle/libs.versions.toml
```

## PR Review Instructions

- Ensure strings are localized via `strings.xml` resources.
- Ensure UI is written in Jetpack Compose using Material3 components — this is a pure Compose app, do not introduce XML layouts.
- All networking and business logic lives in the `multiplatform-SwissTransfer` KMP library — the Android layer is UI + DI only. Avoid duplicating logic in the Android layer.
- `prod` is the default/release flavor; `preprod` points to staging servers — ensure flavor-specific code stays in the correct source sets.
- When adding/removing a runtime dependency, update `LICENSES.md` at the repo root.
