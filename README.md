# El Chrono

A visual remaining-time timer for people with cognitive or intellectual
disabilities. Caregivers configure duration, an available theme, and optional
music; patients can adjust volume. The current application is a starter UI,
not an implemented timer.

## Product positioning

El Chrono 🦒 is intended as a **non-medical-device app** for visual waiting-time
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

## Developer setup and CI

The checked-in `kotlin` and `kotlin.bat` wrappers pin Kotlin Toolchain 0.13.0
and verify its distribution checksum. The first invocation downloads the
toolchain and its runtime, so network access is required; a global Kotlin or
Gradle installation is not required. Project configuration is kept in
`project.yaml`, each module's `module.yaml`, and `libs.versions.toml`.
Examples below use `./kotlin` on macOS/Linux; use `kotlin.bat` on Windows.

Keep machine-specific paths and credentials outside tracked files. Set
platform-specific SDK paths in the local shell or CI environment (for example,
`ANDROID_HOME` or `ANDROID_SDK_ROOT` for Android). The wrapper's optional
`KOTLIN_CLI_*` overrides are also environment configuration, not project
configuration. Build outputs and Kotlin caches are ignored. Do not commit
signing keys, tokens, patient data, local environment files, or `.idea` project
models; project policy permits only intentionally shared
`.idea/codeStyles/` and `.idea/inspectionProfiles/` files.
The CI workflow needs no repository secrets and requests only `contents: read`.

`.github/workflows/ci.yml` runs for pull requests and pushes to `main` on
`ubuntu-24.04` with Java 17. It discovers all declared modules, builds the JVM
targets and browser Wasm target in separate invocations, and runs the existing
common/localization JVM tests and checks. The exact CI commands are:

```sh
./kotlin show modules
./kotlin build -p jvm
./kotlin build -m browser
./kotlin test -p jvm -m common -m localization
./kotlin check -m common -m localization
```

Observed on the current macOS worktree: a first-use wrapper bootstrap with an
empty `KOTLIN_CLI_BOOTSTRAP_CACHE_DIR` downloaded the pinned toolchain/runtime
and listed all ten declared modules. Both build commands, the JVM test command,
and the check command completed successfully; the test command passed all four
tests.

Combining the JVM and Wasm builds in one `./kotlin build -p jvm -p wasmJs`
invocation produced a Kotlin Toolchain `FileAlreadyExistsException` while
extracting the shared Wasm Skiko runtime; running the platform builds
separately succeeded. The GitHub-hosted [CI run for PR #51](https://github.com/Entreco/elchrono/actions/runs/44389945776)
passed all workflow steps. `./kotlin build -m android` also
completed successfully on this macOS host with its installed Android SDK; no
Android device launch was attempted.

CI does not provision an Android SDK or run Android builds, emulator/device
launches, or iOS builds. Android builds can be run on a suitably provisioned
host with `./kotlin build -m android`; iOS compilation and simulator work
require macOS and Xcode. Physical-device installation may additionally require
signing. CI cannot establish support for other desktop operating systems,
Android TV, Apple TV, Wear OS, or watchOS; TV and watch remain unconfigured
targets.


See [AGENTS.md](./AGENTS.md) for project goals, platform targets, accessibility
requirements, voluntary documentation controls, and development instructions.

## Documentation

Product documents live under `docs/`; a human acceptance checkbox in the PR provides review
evidence without duplicate Markdown evidence files or separate signatures.
Lifecycle status applies to every document in the viewed branch: **Draft** on a
feature branch without an open PR targeting `main`, **In review** with an open
PR, and **Approved and effective** on `main` after merge. Do not store Status
or Approval fields in documents or make metadata commits for these transitions.
Changes enter `main` through PRs. Author self-review is permitted: before merge,
record the accepting human and reviewed commit, then check acceptance. This is
a manual review gate, not GitHub-enforced approval. Start with the
[document controls and M1 evidence index](docs/governance/document-control.md).
Documentation lifecycle status does not prove specialist review, verification,
milestone completion, or certification. Missing review evidence remains explicit
and must be resolved for the relevant work.
