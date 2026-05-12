# android-SwissTransfer

## Project Snapshot

Android app for SwissTransfer (secure file transfer service by Infomaniak). Single `:app` module built with Kotlin + Jetpack
Compose, Hilt DI, WorkManager, and an Infomaniak Core composite build plus a SwissTransfer KMP library for all shared
business logic and networking.

**Languages**: Kotlin (100%), Jetpack Compose UI

**Key architecture**: Multi-activity entry points with a Compose-first UI, MVVM + Hilt DI, KMP for cross-platform business logic

## Setup & Development

> **Important**: The `Core` git submodule must be present. Missing it causes a Gradle settings failure at plugin resolution.

```bash
# One-time: initialize submodules
git submodule update --init --recursive

# Copy the env template and fill in your Sentry token (only required for release builds)
cp env.example.properties env.properties

# Build debug app (default flavor: prod)
./gradlew assembleProdDebug

# Build preprod flavor
./gradlew assemblePreprodDebug

# Run lint
./gradlew lint

# Run unit tests (same as CI)
./gradlew testProdDebugUnitTest --stacktrace
```

## Build Flavors

| Flavor    | App ID suffix | API hosts                                                                                    | Default |
|-----------|---------------|----------------------------------------------------------------------------------------------|---------|
| `prod`    | _(none)_      | `www.swisstransfer.com` / `swisstransfer.infomaniak.com`                                     | ✅       |
| `preprod` | `.preprod`    | `swisstransfer-legacy.preprod.dev.infomaniak.ch` / `swisstransfer.preprod.dev.infomaniak.ch` |         |

> The host URLs in `app/build.gradle.kts` are intentionally duplicated from the KMP library. Keep them in sync when changing
> API hosts.

## Architecture Overview

### Module structure

```
android-SwissTransfer/
├── Core/                  # Git submodule – shared Infomaniak library (see Core/AGENTS.md)
│   └── build-logic/       # Gradle convention plugins (included via pluginManagement)
└── app/                   # Single Android app module
    └── src/main/java/com/infomaniak/swisstransfer/
        ├── di/            # Hilt modules and qualifier annotations
        ├── services/      # Foreground/background services (upload, download, cross-app login)
        ├── ui/            # Activities, Compose screens, themes, navigation
        │   ├── components/  # Reusable Compose components
        │   ├── navigation/  # Typed NavDestination sealed classes
        │   ├── screen/      # Feature screens (main, newtransfer, onboarding)
        │   ├── theme/       # Material3 theme, typography, colours
        │   └── utils/       # Extension functions and helpers
        ├── upload/        # Upload pipeline (V1/V2 uploaders, session management)
        └── workers/       # WorkManager workers
```

### Composite build & dependencies

- `Core/build-logic` is included via `pluginManagement { includeBuild(…) }` in `settings.gradle.kts`.
- Two version catalogs: `core` (from `Core/gradle/core.versions.toml`) and `libs` (project-local).
- KMP transfer stack (`com.infomaniak.multiplatform_swisstransfer:*`) provides all business logic: transfers, accounts,
  file management, and networking.

### DI & app bootstrap

- `MainApplication` (`@HiltAndroidApp`) initialises Core networking, Sentry, and WorkManager.
- All KMP managers (`TransferManager`, `AccountManager`, `FileManager`, …) are provided through
  `SwissTransferInjectionModule` → `SwissTransferInjection`. **Do not instantiate KMP managers directly.**
- Coroutine dispatchers are injected via `@IoDispatcher` / `@DefaultDispatcher` qualifiers defined in
  `CoroutinesDispatchersModule`.

### Navigation

- Uses Jetpack Navigation Compose typed destinations (`composable<T>()`, `toRoute<T>()`, `navDeepLink<T>()`).
- Two nav graphs:
    - `MainNavHost` — bottom-navigation tabs (Sent, Received, My Account)
    - `NewTransferNavHost` — new transfer wizard (pick files → upload → success)
- Both legacy (`/d/{uuid}`) and v2 (`/dl/{uuid}`) deep-link URL patterns are registered per destination.

### UI layer

- Compose-first UI, with `MainActivity` hosting most app screens plus dedicated `LaunchActivity`, `OnboardingActivity`, and `NewTransferActivity` entry points.
- Adaptive list/detail layout via `TwoPaneScaffold` (`ListDetailPaneScaffold` from Material3 adaptive).
- `LocalWindowAdaptiveInfo` CompositionLocal propagates window size info to nested composables.
- Matomo analytics via `MatomoSwissTransfer` object (site ID 24).

### Upload pipeline

- `UploadForegroundService` acts as the upload orchestrator and keeps process priority high while files are staged.
- Two uploader implementations selected at DI time based on `accountManager.shouldUseV1Api`:
    - `TransferUploaderV1` — legacy chunked upload with `InMemoryUploadManager`
    - `TransferUploaderV2` — new API with `UploadV2Manager`
- Chunk size and parallelism are computed by `FileChunkSizeManager`.

### Download pipeline

- V1 transfers and single-file V2 downloads use Android `DownloadManager`.
- V2 whole-transfer and folder downloads use `DownloadWorker` (WorkManager).

## Universal Conventions

- **Code style**: Kotlin official style guide enforced via ktlint (from Core build logic).
- **Logging**: Use `SentryLog` (from `com.infomaniak.core.sentry`) — not `Log` or `println`.
- **Context access outside activities**: Use `appCtx` from splitties (`splitties.init.appCtx`).
- **Intent launches**: Use `Context.safeStartActivity()` / `Context.openFile()` from `ui/utils/ContextExt.kt` — never call
  `startActivity` directly when a failure toast is needed.
- **Background work**: Use WorkManager workers for deferrable work; keep raw coroutines for in-process coordination only.
- **No hardcoded strings**: All user-visible strings go in `strings.xml`. Use `R.string.*`.
- **Never commit**: `env.properties`, API tokens, or signing credentials.

### Commit & PR title convention

Every commit message **and** PR title must match the following regex (enforced by CI via
`.github/workflows/semantic-commit.yml`):

```
^Merge .+|(^(feat|fix|chore|docs|style|refactor|perf|ci|test)(\(.+\))?: [A-Z0-9].+)
```

In plain English:

- Start with one of: `feat`, `fix`, `chore`, `docs`, `style`, `refactor`, `perf`, `ci`, `test`
- Optionally add a scope in parentheses: `feat(upload): …`
- Follow with `: ` and a message whose **first character is uppercase or a digit**

Examples: `feat: Add offline support`, `fix(upload): Retry on network error`, `chore: Bump dependencies`

## Key Files

| File                                                              | Purpose                                                    |
|-------------------------------------------------------------------|------------------------------------------------------------|
| `settings.gradle.kts`                                             | Composite build setup, version catalogs, submodule include |
| `app/build.gradle.kts`                                            | App dependencies, flavors, Sentry config                   |
| `app/src/main/java/…/ui/MainApplication.kt`                       | App entry point, Core init, Sentry config                  |
| `app/src/main/java/…/ui/MainActivity.kt`                          | Primary Compose host activity, deep-link handling          |
| `app/src/main/java/…/ui/LaunchActivity.kt`                        | Splash/routing activity                                    |
| `app/src/main/java/…/ui/navigation/NavigationDestination.kt`      | All typed nav destination sealed classes                   |
| `app/src/main/java/…/ui/screen/main/MainNavHost.kt`               | Main bottom-nav host                                       |
| `app/src/main/java/…/ui/screen/newtransfer/NewTransferNavHost.kt` | New transfer wizard nav host                               |
| `app/src/main/java/…/upload/UploadForegroundService.kt`           | Upload orchestrator & state flows                          |
| `app/src/main/java/…/upload/UploadSessionManager.kt`              | V1/V2 upload session lifecycle                             |
| `app/src/main/java/…/di/SwissTransferInjectionModule.kt`          | KMP manager bindings                                       |

## JIT Index

```bash
# Navigation routes and typed destinations
rg -n "composable<|toRoute<|navDeepLink<" app/src/main/java/com/infomaniak/swisstransfer

# Hilt modules and entry points
rg -n "@HiltAndroidApp|@AndroidEntryPoint|@HiltViewModel|@Module|@InstallIn" app/src/main/java/com/infomaniak/swisstransfer

# Upload flow
rg -n "UploadSession|TransferUploader|UploadForegroundService|shouldUseV1Api" app/src/main/java/com/infomaniak/swisstransfer/upload

# Download flow
rg -n "DownloadManager|DownloadWorker" app/src/main/java/com/infomaniak/swisstransfer

# WorkManager workers and services
rg -n "class.*Worker|class.*Service" app/src/main/java/com/infomaniak/swisstransfer/{services,workers}

# Matomo analytics tracking calls
rg -n "MatomoSwissTransfer\." app/src/main/java/com/infomaniak/swisstransfer

# Unit tests
find app/src/test -name "*.kt"
find app/src/androidTest -name "*.kt"
```

## Common Gotchas

- **Missing `Core` submodule** causes a build failure at settings evaluation (`com.infomaniak.core.composite` plugin
  resolution). Always run `git submodule update --init --recursive` after cloning.
- **`env.properties`** is only strictly required for release builds (Sentry auth token upload). For debug builds an empty or
  missing file is fine.
- **Navigation destination class names** must stay in sync with the `*DestinationName` string constants in their companion
  objects — minification strips the class names in production. A unit test in `NavigationDestinationUnitTest` guards this.
- **`plugins` block order** in `app/build.gradle.kts` must not be changed (comment in the file explains why).
- **API URL duplication**: the deep-link host strings in `app/build.gradle.kts` are intentionally copied from the KMP
  library. Change them in both places when updating API hosts.
- **V1 vs V2 upload**: the `UploadSessionStarter` binding is selected at DI time via `accountManager.shouldUseV1Api`. Do not
  hard-code a V1 or V2 path in new code; always go through the injected `UploadSessionStarter` interface.

### Logging & error reporting

- **Always use `SentryLog`** (`.d`, `.i`, `.e`, …) instead of `android.util.Log`. This ensures errors surface in Sentry and
  are filtered consistently (network errors and `CancellationException` are intentionally suppressed — see `MainApplication`).

### Coroutines & flows

- Inject `@IoDispatcher` / `@DefaultDispatcher` rather than hard-coding `Dispatchers.IO` / `Dispatchers.Default` in
  ViewModels or use-cases. The qualifiers are defined in `CoroutinesDispatchersModule`.
- Expose UI state as `StateFlow` (via `.stateIn(viewModelScope, SharingStarted.Lazily, …)`) from ViewModels; use
  `collectLatest` in composables.
- Wrap fallible coroutine calls with `runCatching { … }.cancellable()` so `CancellationException` is re-thrown correctly.

### Dependency injection

- All KMP managers (`TransferManager`, `AccountManager`, `FileManager`, `InMemoryUploadManager`, `UploadV2Manager`,
  `SharedApiUrlCreator`) must come from Hilt injection — never instantiate `SwissTransferInjection` directly outside
  `SwissTransferInjectionModule`.
- Use `dagger.Lazy<T>` to break circular DI initialisation (pattern used in `SwissTransferInjectionModule`).

### Navigation & deep links

- Every `MainNavigation` subclass that has a deep link registers **two** `navDeepLink<T>` entries — one for the legacy URL
  (`/d/{uuid}`) and one for v2 (`/dl/{uuid}`). Preserve both when adding new deep-link destinations.
- The `destinationsNames` list in `MainNavigation.Companion` must be updated whenever a destination class is renamed.
  Forgetting this breaks routing in minified builds (a unit test catches it).

### UI & Compose

- Read window size via the `LocalWindowAdaptiveInfo` CompositionLocal, not by accessing `WindowMetrics` directly.
- Use `TwoPaneScaffold` for any list/detail screen — it automatically handles single-pane vs. two-pane layouts based on
  window size.
- Current user is available via `LocalUser` CompositionLocal (set in `MainActivity`).
- For non-Activity context access, use `appCtx` (splitties) rather than passing `Context` through call chains.

### Analytics

- Screen tracking: `MatomoSwissTransfer.trackScreen(MatomoScreen.*)`.
- Event tracking: use the strongly-typed helpers on `MatomoSwissTransfer` (e.g. `trackNewTransferEvent`, `trackSettings`).
  Do not call the raw `trackEvent(String, String, …)` overload with ad-hoc strings.

### Upload pipeline

- Always route new upload initiations through `UploadForegroundService.Companion` methods (`addFiles`, `commitUploadSession`,
  etc.) — the service manages URI permissions and process priority.
- Do not access `UploadState` directly from the UI; observe `UploadForegroundService.uploadStateFlow`.

### Download pipeline

- Use `DownloadWorker` for V2 whole-transfer and folder downloads. Use Android `DownloadManager` only for V2 single-file
  downloads and all V1 downloads. Check `transfer.isV2()` and `targetFile?.isFolder` before choosing the path.

## Definition of Done

- [ ] Code compiles without errors: `./gradlew assembleProdDebug`
- [ ] Lint passes: `./gradlew lint`
- [ ] Unit tests pass: `./gradlew testProdDebugUnitTest --stacktrace`
- [ ] No hardcoded user-visible strings (use `strings.xml` / `R.string.*`)
- [ ] No hardcoded `Dispatchers.IO` / `.Default` in ViewModels (use injected dispatcher)
- [ ] Logging uses `SentryLog`, not `android.util.Log`
- [ ] New background work uses WorkManager (not raw threads or services)
- [ ] Intent launches use `safeStartActivity` / `openFile` helpers
- [ ] Navigation destination renaming updates the minification-safe name constants and passes `NavigationDestinationUnitTest`

## Learned Preferences

These are unspoken rules discovered through code review and codebase deep-dives. They are not enforced by lint but reflect the
team's expectations.

> Add entries here when a review or PR reveals a preference, convention, or pattern that should be remembered for future work.
