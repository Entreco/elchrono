# El Chrono

A visual remaining-time timer for people with cognitive or intellectual
disabilities. Caregivers configure duration, one of five themes, and optional
music; patients can adjust volume. The current application is a starter UI,
not an implemented timer.

## Product positioning

El Chrono is intended as a **non-medical-device app** for visual waiting-time
support. We will not make medical claims: it is not intended for diagnosis,
treatment, monitoring a medical condition, or safety-critical medical timing.

We voluntarily maintain documentation with medical-device-style rigor, including
intended use, risks, reviews, verification, and traceability. This does not make
the app a medical device or imply certification. Target-market qualification
review must confirm that the actual intended use, functionality, and published
claims remain consistent with this non-medical scope.

## Structure

This Kotlin Multiplatform / Compose Multiplatform project uses the Kotlin
Toolchain wrappers `kotlin` (macOS/Linux) and `kotlin.bat` (Windows).

| Directory | Responsibility |
|---|---|
| `apps/common` | Shared application composition and starter UI |
| `apps/desktop` | Desktop JVM launcher |
| `apps/browser` | Kotlin/Wasm browser launcher |
| `apps/mobile/android` | Android launcher, manifest and native resources |
| `apps/mobile/ios` | iOS launcher, Swift bridge, Xcode project and assets |
| `apps/television`, `apps/watch` | Reserved for future targets; not launchable |
| `core/design` | Shared theme, design components and drawable resources |
| `core/localization` | Shared translation-ready string resources |
| `features/timer`, `features/settings`, `features/music` | Shared CMP feature library scaffolds; functionality not implemented |

Dependencies flow from platform launchers to `apps/common`, from the shell to
features/core, and from features to core. Core must not depend on apps/features
or platform SDKs. See [the module layout decision](docs/architecture/module-layout.md).

Common Kotlin code belongs in each module's `src`; platform-specific source
sets use `src@<platform>`. Tests use `test` and `test@<platform>`.
Compose resources belong in the owning module's `composeResources`.
Add translations under `core/localization/composeResources/values-<language>`,
keeping default keys and format placeholders consistent.

## Build and launch

From the repository root:

```sh
./kotlin show modules
./kotlin build
./kotlin check
./kotlin test -m common -m localization

./kotlin run -m desktop
./kotlin run -m browser
./kotlin run -m android --device-id <adb-serial>
./kotlin run -m ios -p iosSimulatorArm64 --device-id <simulator-uuid>
```

CLI module names are leaf directory names, not paths. `project.yaml` lists full
relative module paths; module dependencies use project-root paths such as
`//core/design`. Keep module names unique.

Android requires an installed SDK and authorized device/emulator (`adb devices -l`).
iOS requires macOS, Xcode, and an available simulator (`xcrun simctl list devices available`).
An iOS device build requires signing before installation; unsigned compilation
does not establish physical-device launch support.

For browser automation or running without opening a browser:

```sh
./kotlin run -m browser --port 8080 --no-open-browser
```

Desktop launch is verified on the current macOS host; other desktop operating
systems require their own packaging/verification. Android TV, Apple TV, Wear OS,
and watchOS remain planned, not configured targets.

See [AGENTS.md](./AGENTS.md) for project goals, platform targets, accessibility
requirements, voluntary documentation controls, and development instructions.

## Documentation

Product documents and review records live under `docs/`. Start with the
[document controls and M1 evidence index](docs/governance/document-control.md).
These controls are a draft awaiting qualified human review and approval, not
an approved safety baseline or a certification claim.
