# android-SwissTransfer

## Package Identity

Android app for SwissTransfer, built with Kotlin + Jetpack Compose, using Hilt DI, WorkManager, and an Infomaniak Core composite
build (`:Core`) plus SwissTransfer KMP dependencies.

## Setup & Development

> **Important**: The repository expects the `Core` git submodule to be present. If missing, Gradle settings resolution fails.

```bash
# Initialize submodules first
git submodule update --init --recursive

# Build debug app
./gradlew assembleDebug

# Run lint
./gradlew lint

# Run unit tests (same as CI)
./gradlew testProdDebugUnitTest --stacktrace
```

## Architecture Overview

### App structure

- Single Android app module: `:app`
- Main package: `app/src/main/java/com/infomaniak/swisstransfer/`
- Key areas:
    - `ui/`: activities, Compose screens, themes, navigation
    - `di/`: Hilt modules and qualifiers
    - `services/`, `workers/`, `upload/`: background work and upload/download pipelines

### Dependency model

- Core modules are provided through the composite build declared in `settings.gradle.kts` (`includeBuild("Core/build-logic")`).
- App dependencies mix:
    - `com.infomaniak.core:*` (Core modules via catalog `core`)
    - `com.infomaniak.multiplatform_swisstransfer:*` (KMP transfer stack)

### DI & app bootstrap

- App entry point: `MainApplication` annotated with `@HiltAndroidApp`.
- DI modules are in `app/src/main/java/com/infomaniak/swisstransfer/di/`:
    - `ApplicationModule`
    - `CoroutinesDispatchersModule`
    - `SwissTransferInjectionModule`

### Navigation

- Uses Jetpack Navigation Compose typed destinations (`composable<T>()`, `toRoute<T>()`, `navDeepLink<T>()`).
- Main flow definitions are under `ui/navigation/NavigationDestination.kt` and hosts in:
    - `ui/screen/main/MainNavHost.kt`
    - `ui/screen/newtransfer/NewTransferNavHost.kt`

### UI layer

- Compose-first UI.
- Adaptive list/detail patterns use Material3 adaptive APIs via `ui/components/TwoPaneScaffold.kt`.

## Patterns & Conventions

- Kotlin style follows existing code conventions and ktlint checks from the Core build logic.
- Keep navigation destination names in sync when renaming destination classes (string constants are used for route matching).
- Use app helpers for safe intent launches and file opening (`ui/utils/ContextExt.kt`).
- Prefer using existing managers/utilities (`TransferManager`, `AccountUtils`, upload session abstractions) rather than duplicating
  flow logic in Activities.

### Commit & PR title convention

Every commit message **and** PR title must match the following regex (enforced by CI via
`.github/workflows/semantic-commit.yml`):

```
^Merge .+|(^(feat|fix|chore|docs|style|refactor|perf|ci|test)(\(.+\))?: [A-Z0-9].+)
```

In plain English:
- Start with one of: `feat`, `fix`, `chore`, `docs`, `style`, `refactor`, `perf`, `ci`, `test`
- Optionally add a scope in parentheses: `feat(upload): …`
- Follow with `: ` and a message whose first character is **uppercase** or a digit

Examples: `feat: Add offline support`, `fix(upload): Retry on network error`, `chore: Bump dependencies`

## Key Files

- Project settings/composite build: `settings.gradle.kts`
- App build config/dependencies: `app/build.gradle.kts`
- Application bootstrap: `app/src/main/java/com/infomaniak/swisstransfer/ui/MainApplication.kt`
- Main activity and deep-link handling: `app/src/main/java/com/infomaniak/swisstransfer/ui/MainActivity.kt`
- Launch routing activity: `app/src/main/java/com/infomaniak/swisstransfer/ui/LaunchActivity.kt`
- Navigation model: `app/src/main/java/com/infomaniak/swisstransfer/ui/navigation/NavigationDestination.kt`
- Main nav host: `app/src/main/java/com/infomaniak/swisstransfer/ui/screen/main/MainNavHost.kt`
- Upload pipeline: `app/src/main/java/com/infomaniak/swisstransfer/upload/`

## JIT Index

### Locate key implementations

```bash
# Navigation routes and typed destinations
rg -n "composable<|toRoute<|navDeepLink<" app/src/main/java/com/infomaniak/swisstransfer

# Hilt modules and entry points
rg -n "@HiltAndroidApp|@AndroidEntryPoint|@Module|@InstallIn" app/src/main/java/com/infomaniak/swisstransfer

# Upload flow
rg -n "UploadSession|TransferUploader|UploadForegroundService" app/src/main/java/com/infomaniak/swisstransfer/upload

# WorkManager workers/services
rg -n "Worker|WorkManager|Service" app/src/main/java/com/infomaniak/swisstransfer/{services,workers}
```

### Test locations

```bash
find app/src/test -name "*.kt"
find app/src/androidTest -name "*.kt"
```

## Common Gotchas

- Missing `Core` submodule causes build failure at settings evaluation (`com.infomaniak.core.composite` plugin resolution).
- `env.properties` may be required by build scripts for some tasks; keep a local non-secret development value when needed.
- Deep-link routing includes both legacy and v2 URL patterns; preserve current suffix/argument conventions when changing routes.

## Pre-PR Checks

```bash
./gradlew assembleDebug
./gradlew lint
./gradlew testProdDebugUnitTest --stacktrace
```
