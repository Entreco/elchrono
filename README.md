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
| `apps/common` | Shared app scaffold, feature routing and Settings visibility |
| `apps/desktop` | Desktop JVM launcher |
| `apps/browser` | Kotlin/Wasm browser launcher |
| `apps/mobile/android` | Android launcher, manifest and native resources |
| `apps/mobile/ios` | iOS launcher, Swift bridge, Xcode project and assets |
| `apps/television/androidtv` | Candidate Android TV launcher and banner |
| `apps/television` (Apple TV), `apps/watch` | Reserved for future targets; not launchable |
| `core/counter` | Shared timer duration and monotonic countdown logic; no Compose |
| `core/design` | Shared theme, design types, reusable components and drawable resources |
| `core/localization` | Shared translation-ready string resources |
| `features/timer`, `features/settings`, `features/about` | Shared Compose UI features |
| `features/music` | Empty v2 placeholder; not included in the app |
| `tools/design-catalog` | Development-only desktop catalog for shared design components |

Dependencies flow from platform launchers to `apps/common`, from the shell to
features/core, and from features to core. Features do not depend on one another.
Core must not depend on apps/features or platform SDKs. Timer settings are
currently held in memory; no cross-restart persistence is specified.
See [the module layout decision](docs/architecture/module-layout.md).

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
./kotlin test -p jvm -m common -m counter -m design -m settings -m localization

./kotlin run -m desktop
./kotlin run -m browser
./kotlin run -m android --device-id <adb-serial>
./kotlin run -m androidtv --device-id <adb-serial>
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

If the default port is already in use, choose another available port, for example:

```sh
./kotlin run -m browser --port 8081 --no-open-browser
```

The server reports the URL it is listening on. Do not stop an existing process
unless you have identified it and intend to close that specific server.

### Design catalog

Run the standalone desktop catalog from the repository root:

```sh
./kotlin run -m design-catalog
```

The catalog displays interactive examples of the public `core/design`
composables. Resize its window or select a preview width to inspect compact
and wide layouts; the controls use sample state and do not change app settings.
The catalog depends on the design module, but the released app modules do not
depend on the catalog. It is not part of an app package. Because it is a
registered JVM module, the all-JVM CI build also compiles it.

The toolchain enables Compose Hot Reload for the desktop catalog by default.
To request it explicitly, use
`./kotlin run -m design-catalog --compose-hot-reload`; this uses JetBrains
Runtime. Use `./kotlin run -m design-catalog --no-compose-hot-reload` to run
without it. A desktop window and suitable graphical environment are required.

Examples are maintained explicitly in `tools/design-catalog/src/DesignCatalog.kt`,
not discovered automatically. When adding or changing a public design
composable, update its example and exercise relevant states and widths. The
catalog is a development aid, not evidence of device, accessibility, or
usability verification.

`ElChronoTheme` supplies shared Material colors, typography, and shapes to the
app and catalog. The dark palette approximates the issue #61 screenshot; the
font is a system sans-serif approximation because the source font is unknown.
The sampled colors and visual appearance still require design and accessibility
review across targets.

Desktop launch is verified on the current macOS host; other desktop operating
systems require their own packaging/verification. The Android TV candidate
launches on a TV emulator, but launcher artwork, remote interaction,
accessibility, lifecycle and usability are not verified. Apple TV, Wear OS,
and watchOS remain planned, not configured targets.

The supplied artwork in `docs/design/icons` is used by the browser, mobile,
desktop window and Android TV candidate. Native desktop application packaging
and icons, browser install icons, Apple TV, and visual icon checks remain
unverified. Foldable behavior requires testing on suitable devices.

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
targets and browser Wasm target in separate invocations, and runs common,
counter, design, settings, and localization JVM tests and checks. The exact CI
commands are:

```sh
./kotlin show modules
./kotlin build -p jvm
./kotlin build -m browser
./kotlin test -p jvm -m common -m counter -m design -m settings -m localization
./kotlin check -m common -m counter -m design -m settings -m localization
```

CI restores Kotlin Toolchain bootstrap and dependency downloads before module
discovery and saves them after both builds succeed, before tests and checks.
The workflow explicitly sets `KOTLIN_SHARED_CACHE_DIR` and
`KOTLIN_CLI_BOOTSTRAP_CACHE_DIR` under the runner's temporary directory. It caches
only `.m2.cache`, `download.cache`, and `cli`; workspace build outputs, extracted
caches, telemetry, and temporary download/lock files are excluded.
Cache keys separate runner OS/architecture and wrapper versions, prefer matching
dependency configuration, and allow reuse from the same wrapper version after
dependency changes. Each run attempt saves a new immutable entry so additional
downloads can be retained. GitHub cache scope and eviction still apply.

An HTTP `429 Too Many Requests` from Maven Central is a dependency-download
failure, not a failing application test or proof of an individual artifact-size
limit. [Sonatype's consumption-limit guidance](https://central.sonatype.org/faq/429-error/)
explains that aggregate traffic, including
[shared hosted-CI egress](https://central.sonatype.org/faq/429-shared-egress/),
can trigger throttling. Caching reduces repeat downloads but does not guarantee
that a cold cache or shared runner avoids limits. Allow a block to expire rather
than repeatedly rerunning builds; do not disable checksum verification or switch
between `repo1.maven.org` and `repo.maven.apache.org` as a bypass (both serve
Maven Central). Persistent failures require provider/Sonatype investigation;
a managed caching proxy is a separate infrastructure option.

Hosted cold-cache population and subsequent warm-cache restoration for this
configuration remain unverified until the workflow runs. The earlier
[main run's truncated Skiko Wasm failure](https://github.com/Entreco/elchrono/actions/runs/44599279733)
is a separate unresolved issue; download caching is not evidence that it is fixed.

On the issue #47 candidate, module discovery listed 12 modules. The JVM and
browser Wasm builds, selected JVM tests, and checks completed successfully on
the current macOS worktree. The JVM test selection includes the counter and
settings regression tests; this is not browser UI or platform-runtime
verification.

Combining the JVM and Wasm builds in one `./kotlin build -p jvm -p wasmJs`
invocation produced a Kotlin Toolchain `FileAlreadyExistsException` while
extracting the shared Wasm Skiko runtime; running the platform builds
separately succeeded. The GitHub-hosted workflow has not yet run for this
branch, so its result remains pending. `./kotlin build -m android` also
completed successfully on this macOS host with its installed Android SDK; no
Android device launch was attempted.

CI does not provision an Android SDK or run Android builds, emulator/device
launches, or iOS builds. Android builds can be run on a suitably provisioned
host with `./kotlin build -m android`; iOS compilation and simulator work
require macOS and Xcode. Physical-device installation may additionally require
signing. CI cannot establish support for other desktop operating systems,
Android TV, Apple TV, Wear OS, or watchOS; only the Android TV candidate is
configured.

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
