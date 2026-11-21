# Dependency and third-party component inventory

Settings appearance review: [PR #76](https://github.com/Entreco/elchrono/pull/76).

Desktop keyboard changes: [PR #73](https://github.com/Entreco/elchrono/pull/73).

| Field | Value |
|---|---|
| Document ID | M3-DOC-005 |
| Revision | 1.0 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Architecture/dependency, licensing, and security reviewers as applicable; appointments and competence confirmation pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#38](https://github.com/Entreco/elchrono/issues/38) |
| Review PR | [PR #52](https://github.com/Entreco/elchrono/pull/52); required specialist reviews and human acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This is the initial inventory of external dependencies directly declared by
El Chrono's module manifests, with a proportionate preliminary assessment of
their roles, provenance, published licence metadata, maintenance evidence, and
open actions. It also records the build-tool-managed Kotlin runtime used by
the current modules. It is a voluntary engineering control, not a claim that
IEC 62304 applies or is followed, not a safety classification, and not a
completed legal, security, or licence review.

The inventory is limited to the twelve modules configured in `project.yaml` at
the 2026-11-19 source snapshot and their direct production/test declarations.
It does not attempt to inventory every transitive dependency, operating-system component, compiler
plug-in, SDK, browser engine, or provider component. A resolved dependency
graph is used to confirm direct versions and platform variants; the graph
includes many transitive dependencies that are outside this record's direct
inventory scope. Re-run the discovery and review when module manifests,
versions, targets, toolchain defaults, or distribution choices change.

At the inspected source snapshot, the app was a starter screen, not an
implemented timer. No dependency
assessment here establishes application behavior, support, or release
readiness. No dependency update is made by this record.

## Evidence basis

Repository configuration and resolution were inspected on 2026-11-18:

| Evidence | Observed result and limit |
|---|---|
| [`project.yaml`](../../project.yaml) and the ten `module.yaml` files | Establish configured module paths and direct declaration scopes, including the JVM test-only declarations in `core/localization/module.yaml`. |
| [`libs.versions.toml`](../../libs.versions.toml) | Establishes catalog versions for AndroidX Activity, AndroidX Lifecycle, Compose Multiplatform Adaptive, Kotlin Coroutines, and Logback. |
| `./kotlin show modules` | Resolved the ten configured modules. |
| `./kotlin show dependencies --all-modules --include-tests` | Resolved module/platform dependency graphs; confirms versions and the macOS ARM64 selection of `currentOs` on the inspected host. The generated report is not retained as a controlled artifact. |
| `./kotlin show settings -m android -m ios` | Reports Kotlin 2.4.20, Compose 1.12.1, Android `versionCode` 1 and `versionName` `unspecified` defaults. These defaults are not release approval. |
| Published POMs and upstream release pages linked below | Provide publisher-declared license, source, version, or release-history evidence. A published POM or release does not establish a support SLA, suitability, or legal approval. |

Declared internal module dependencies are first-party project modules, not
third-party components; they are not repeated in the external component table.
Toolchain-generated Kotlin test libraries and the JUnit test engine are
distinguished from dependencies explicitly declared in `module.yaml`. Their
versions may change with the Kotlin Toolchain and test configuration.

## Direct external dependencies

The module names below are the unique leaf selectors reported by the Kotlin
Toolchain. The `module.yaml` files remain the source of truth for module
ownership and dependency scope. `main` means declared for production
compilation; `jvmTest` means declared only for the JVM tests.

| Declaration / resolved component | Direct module scope | Version and provenance | Published licence metadata | Preliminary SOUP assessment and open action |
|---|---|---|---|---|
| `$compose.runtime`, `org.jetbrains.compose.runtime:runtime` | `common`, `design`, `music`, `settings`, `timer` (`main`); also toolchain-added in Compose-enabled source sets | 1.12.1; resolved by the Kotlin Toolchain's Compose defaults | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/runtime/runtime/1.12.1/runtime-1.12.1.pom) | Core Compose composition/runtime dependency. Changes can affect all rendered feature surfaces; require targeted cross-platform build and UI verification after updates. JetBrains' release feed displayed 1.13.0-rc01 on the review date, a prerelease; select and review a supported release before product release rather than upgrading automatically. |
| `$compose.foundation`, `org.jetbrains.compose.foundation:foundation` | `common`, `design` (`main`) | 1.12.1; Compose Toolchain | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/foundation/foundation/1.12.1/foundation-1.12.1.pom) and [release history](https://github.com/JetBrains/compose-multiplatform/releases) | Shared layout/input foundation. `core/design` uses it for the reusable numeric keypad. Cross-platform UI impact is material; validate interaction and accessibility after changes. No platform support or release approval follows from its presence. |
| `$compose.material3`, `org.jetbrains.compose.material3:material3` | `common`, `design` (`main`) | 1.12.0-alpha03; Compose Toolchain | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/material3/material3/1.12.0-alpha03/material3-1.12.0-alpha03.pom) | Direct dependency is explicitly an alpha release. Review API/stability and the intended stable-version policy before relying on it in a shipped UI; no change is made here. |
| `$libs.compose.navigation3.ui`, `org.jetbrains.androidx.navigation3:navigation3-ui` | `common` (`main`) | 1.2.0; pinned in `libs.versions.toml` | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/androidx/navigation3/navigation3-ui/1.2.0/navigation3-ui-1.2.0.pom) | Shared navigation host and predictive-back integration. Verify Back from About and Settings, cancellation, and completed gestures without transition replay on Android. |
| `$libs.compose.cloudy`, `com.github.skydoves:cloudy` | `common` (`main`) | 1.0.0-alpha01; pinned in `libs.versions.toml`, [upstream release](https://github.com/skydoves/Cloudy/tree/1.0.0-alpha01) | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/com/github/skydoves/cloudy/1.0.0-alpha01/cloudy-1.0.0-alpha01.pom) | Prerelease used for Settings, About and the remaining-time backdrop/glass layers, separate from sharp text and controls. The remaining-time layer captures the visualization and invalidates on snapshot or settings changes. Upstream documents RuntimeShader on Android 33+, tint/edge fallback through Android 32, and Skia on iOS, desktop and Wasm; actual rendering, performance and readability require target testing. Backdrop errors are surfaced with a tinted fallback; the lens API does not expose shader failures. No sensor-driven light is enabled. Licence, security, and release suitability reviews remain pending. |
| `$compose.ui`, `org.jetbrains.compose.ui:ui` | `browser`, `common`, `design`, `ios`, `music`, `settings`, `timer` (`main`) | 1.12.1; Compose Toolchain | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/ui/ui/1.12.1/ui-1.12.1.pom) and [release history](https://github.com/JetBrains/compose-multiplatform/releases) | Rendering/input dependency across configured app targets. Android uses the experimental `mediaQuery` keyboard capability API, which requires enabling `ComposeUiFlags.isMediaQueryIntegrationEnabled`; iOS/Wasm and desktop use explicit input policies, as the pinned desktop sources do not provide the required media scope. Validate rendering, semantics, keyboard/touch, and platform-specific behavior on updates. TV/watch support is not established by the configured modules. |
| `$compose.components.resources`, `org.jetbrains.compose.components:components-resources` | `common`, `design`, `localization` (`main`); also added to Compose-enabled source sets | 1.12.1; Compose Toolchain | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/components/components-resources/1.12.1/components-resources-1.12.1.pom) and [release history](https://github.com/JetBrains/compose-multiplatform/releases) | Design and localization resource loading is shared app infrastructure. Check resource generation/rendering on configured platforms after updates; externalized resource service behavior is not used or assessed here. |
| `$compose.preview`, `org.jetbrains.compose.ui:ui-tooling-preview` | `common`, `desktop`, `android` (`main`) | 1.12.1; Compose Toolchain | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/ui/ui-tooling-preview/1.12.1/ui-tooling-preview-1.12.1.pom) and [release history](https://github.com/JetBrains/compose-multiplatform/releases) | Preview-oriented API is declared in production source sets. Confirm whether this is intentionally needed in production artifacts and whether its declaration should be narrowed; do not change scope without build evidence. |
| `$compose.uiTooling`, `org.jetbrains.compose.ui:ui-tooling` | `common` (`android` source set), `android` (`main`) | 1.12.1; Compose Toolchain | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/ui/ui-tooling/1.12.1/ui-tooling-1.12.1.pom) and [release history](https://github.com/JetBrains/compose-multiplatform/releases) | Android tooling dependency is declared for the Android app and common Android source set. Confirm release packaging excludes development-only tooling where appropriate. |
| `$compose.desktop.currentOs`, `org.jetbrains.compose.desktop:desktop-jvm-macos-arm64` | `desktop` (`main`); `common`, `localization`, `settings` (`jvmTest`, runtime-only) | 1.12.1; resolves to macOS ARM64 on the inspected host; other hosts resolve different native variants | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/desktop/desktop-jvm-macos-arm64/1.12.1/desktop-jvm-macos-arm64-1.12.1.pom) | Platform-specific desktop runtime and test-resource support. This report only confirms the current host variant; packaging and runtime evidence are needed for each supported desktop OS/architecture. Transitive Skiko/native artifacts are outside this direct-dependency assessment. |
| `$libs.compose.ui.test`, `org.jetbrains.compose.ui:ui-test` | `common`, `settings` (`jvmTest`) | 1.12.1; pinned in `libs.versions.toml` to match Compose UI | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/ui/ui-test/1.12.1/ui-test-1.12.1.pom) | Test-only Compose interaction harness; exercise Settings and overlay semantics and simulated key events without production exposure. Verify CI host compatibility and reassess alongside Compose upgrades; simulated events do not prove physical-device behavior. |
| `$compose.hotReload.runtimeApi`, `org.jetbrains.compose.hot-reload:hot-reload-runtime-api` | `common` (`main`) | 1.2.0; Compose Toolchain | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/compose/hot-reload/hot-reload-runtime-api/1.2.0/hot-reload-runtime-api-1.2.0.pom) | A hot-reload runtime API is declared in a production source set. The upstream release feed displayed 1.3.0-rc01 on the review date. Determine whether this is required at runtime, whether it belongs in production packaging, and which stable version is supported before release. |
| `$libs.androidx.lifecycle.viewmodel.compose`, `org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose`; `$libs.androidx.lifecycle.runtime.compose`, `org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose` | `common` (`main`) | 2.11.0; versions pinned in `libs.versions.toml`, resolved across configured common targets | Apache-2.0; [ViewModel POM](https://repo.maven.apache.org/maven2/org/jetbrains/androidx/lifecycle/lifecycle-viewmodel-compose/2.11.0/lifecycle-viewmodel-compose-2.11.0.pom) and [Runtime POM](https://repo.maven.apache.org/maven2/org/jetbrains/androidx/lifecycle/lifecycle-runtime-compose/2.11.0/lifecycle-runtime-compose-2.11.0.pom) | Common app state is hosted by ViewModel and observed through Compose lifecycle APIs. The ViewModel pauses/marks a running timer interrupted on `ON_STOP`; platform event delivery and runtime recovery remain separate verification obligations. |
| `$libs.kotlinx.coroutines.core`, `org.jetbrains.kotlinx:kotlinx-coroutines-core` | `common` (`main`) | 1.11.0; pinned in `libs.versions.toml` | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core/1.11.0/kotlinx-coroutines-core-1.11.0.pom) and [upstream releases](https://github.com/Kotlin/kotlinx.coroutines/releases) | Direct API dependency for app state flows and timer refresh scheduling; keep aligned with the declared coroutine test and desktop dispatcher versions. |
| `$libs.androidx.activity.compose`, `androidx.activity:activity-compose` | `android` (`main`) | 1.13.0; pinned in `libs.versions.toml` and resolved from Google Maven | Apache-2.0; [published POM](https://dl.google.com/dl/android/maven2/androidx/activity/activity-compose/1.13.0/activity-compose-1.13.0.pom) and [official release notes](https://developer.android.com/jetpack/androidx/releases/activity) | Android launcher/activity integration. Platform-critical but narrowly scoped; review release notes and test Android lifecycle/launch behavior when updated. |
| `$libs.kotlinx.coroutines.swing`, `org.jetbrains.kotlinx:kotlinx-coroutines-swing` | `desktop` (`main`); `common` (`jvmTest`, runtime-only) | 1.11.0; pinned in `libs.versions.toml` | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-swing/1.11.0/kotlinx-coroutines-swing-1.11.0.pom) and [upstream releases](https://github.com/Kotlin/kotlinx.coroutines/releases) | Desktop Swing coroutine dispatcher integration; the common JVM UI navigation test also requires a main dispatcher. Verify CI host compatibility and reassess alongside coroutine updates. |
| `$libs.kotlinx.coroutines.test`, `org.jetbrains.kotlinx:kotlinx-coroutines-test` | `localization` (`jvmTest`) | 1.11.0; pinned in `libs.versions.toml` | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-test/1.11.0/kotlinx-coroutines-test-1.11.0.pom) and [upstream releases](https://github.com/Kotlin/kotlinx.coroutines/releases) | Test-only deterministic coroutine support; no production runtime exposure from this declaration. Keep aligned with the production coroutine family and existing tests. |
| `$libs.logback.classic`, `ch.qos.logback:logback-classic` | `desktop` (`main`) | 1.6.2; pinned in `libs.versions.toml` | EPL-2.0 or LGPL-2.1-only; [published parent POM](https://repo.maven.apache.org/maven2/ch/qos/logback/logback-parent/1.6.2/logback-parent-1.6.2.pom) | Desktop logging backend. No direct app logging calls were found in the repository search. Upstream released 1.6.7 on 2026-11-14; review the patch gap, compatibility, and licence obligations before distribution. Do not log patient data or identifiable information. |

Compose and Compose Multiplatform artifact license statements above are the
publisher's Apache-2.0 metadata. The published dependency families include
transitive third-party components with their own licences; this direct
inventory does not establish a complete distributed-artifact licence
manifest. Generate and review a platform-specific transitive licence manifest
before distribution.

## Bundled icon assets

The four `material_*.xml` vector resources in `core/design/composeResources/drawable`
adapt Google's Material Icons Settings, Close, Arrow Back, and Check 24px SVGs
at [source revision 10ecc14790612ca8fd164e3f4ffe9c16fc50d5b7](https://github.com/google/material-design-icons/tree/10ecc14790612ca8fd164e3f4ffe9c16fc50d5b7/src).
The upstream [license](https://github.com/google/material-design-icons/blob/10ecc14790612ca8fd164e3f4ffe9c16fc50d5b7/LICENSE)
is Apache-2.0; the repository's root `LICENSE` contains that license. The SVG
paths were converted to Compose vector XML for shared platform resources. This
adds no runtime dependency. Asset licensing and distribution notices remain
subject to the release licence review.

## Bundled font asset

The Rounded option uses the unmodified Nunito variable font from the
[Google Fonts repository at revision `604936664fd62c14271209b51f98e7f495dd1a3e`](https://github.com/google/fonts/tree/604936664fd62c14271209b51f98e7f495dd1a3e/ofl/nunito).
Its upstream metadata credits Vernon Adams, Cyreal, and Jacques Le Bailly and
states copyright 2014 The Nunito Project Authors. The font is licensed under
the SIL Open Font License 1.1; the complete upstream copyright and licence
notice is bundled at `core/design/composeResources/files/nunito_ofl.txt`.
The bundled font's SHA-256 is
`bb55a5ca5c2042335b3991af27c4d0705d0ef41cac6164ac737fd8f2a1e85207`.
Its declared subsets include Latin, Cyrillic and Vietnamese; font fallback and
rendering across supported platforms and languages need verification. Confirm
that each distribution includes the required notice and complete the release
licence review; this entry is not a completed legal assessment.

## Toolchain-managed runtime and test dependencies

These are resolved direct dependencies of generated source sets, but are not
explicit module-manifest declarations. They are listed separately to
distinguish Kotlin Toolchain defaults from project-selected catalog entries.

| Component | Scope and resolved version | Published licence and source | Preliminary assessment and open action |
|---|---|---|---|
| Kotlin standard library, `org.jetbrains.kotlin:kotlin-stdlib` | Toolchain-provided production runtime; 2.4.20 | Apache-2.0; [published POM](https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/kotlin-stdlib/2.4.20/kotlin-stdlib-2.4.20.pom), source repository `JetBrains/kotlin` | Core language runtime. Version is coupled to the toolchain rather than `libs.versions.toml`; review release notes, compatibility, and the platform-resolved artifacts when changing the toolchain. |
| Kotlin test libraries, including `kotlin-test` and the JUnit 5 adapter | Generated test source sets; 2.4.20 | Apache-2.0; [Kotlin Test POM](https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/kotlin-test/2.4.20/kotlin-test-2.4.20.pom) and [JUnit 5 adapter POM](https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/kotlin-test-junit5/2.4.20/kotlin-test-junit5-2.4.20.pom), source repository `JetBrains/kotlin` | Test-only assertions/adapters. Not a production runtime declaration; keep test execution and generated variants aligned with the toolchain. |
| JUnit test engine | Toolchain default `junit-5`; JUnit Platform version 6.1.3 in effective settings | JUnit Platform transitive licence not assessed in this direct inventory | Test-only infrastructure; include its exact artifacts/licences in test-tooling records if test outputs are distributed. It is not evidence that production dependencies or platforms are supported. |

The Kotlin Toolchain version observed in the existing platform-feasibility
record is 0.13.0; toolchain defaults can change separately from
`libs.versions.toml`. Transitive production dependencies remain outside this
direct inventory; verify and record their exact licences in the
platform-specific distributed-artifact review.

The dependency graph is host-sensitive: `currentOs` resolved to the macOS
ARM64 desktop artifact for this assessment. This does not inventory or verify
the artifacts selected on Windows, Linux, Intel macOS, or any unconfigured
platform.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish the initial direct dependency inventory and preliminary assessments. |
| 0.2 | Add the direct adaptive-layout dependency, update design/UI dependency scopes, and record the scoped experimental keyboard MediaQuery integration and platform limitations. |

## Assessment method and follow-up

This preliminary screening considers component role, declaration scope,
platform reach, upstream publication/release evidence, explicit preview
status, and whether direct use is observable in repository source. It does
not assign IEC 62304 terminology as an applicable compliance obligation,
calculate quantitative product risk, determine legal acceptability, or
approve a dependency. No known security advisory or vulnerability scan was
performed.

Before a release, the product owner and assigned reviewers should:

1. Re-resolve dependencies for every configured release target and record
   exact coordinates, versions, and any host/platform substitutions.
2. Verify each direct and packaged transitive licence against the distributed
   binaries, assess notices/attribution obligations, and retain the actual
   licence evidence.
3. Review upstream maintenance, supported versions, advisories, compatibility,
   and update/retirement options for the exact versions selected.
4. Resolve the Material3 alpha, production-source-set preview/tooling and
   hot-reload declarations, and the unused-looking desktop logging/coroutine
   declarations before treating them as intentional release dependencies.
5. Test dependency changes against approved requirements, risks, platforms,
   privacy boundaries, and release evidence. Keep unresolved anomalies,
   unsupported targets, and reviewer gaps explicit.

The dependency assessment does not approve provider integrations, add data
flows, or make the timer dependent on internet access. El Chrono remains
within its defined non-medical visual waiting-time purpose. If actual intended
use, functions, claims, markets, or applicable obligations change, escalate
and reassess before relying on this inventory.

## Sources

- Repository declarations: [`project.yaml`](../../project.yaml), the module
  manifests under `apps/`, `core/`, and `features/`, and
  [`libs.versions.toml`](../../libs.versions.toml).
- Kotlin Toolchain command help and dependency report, commands and scope
  recorded in Evidence basis.
- [Compose Multiplatform releases](https://github.com/JetBrains/compose-multiplatform/releases)
  and artifact POMs linked in the inventory.
- [Compose Hot Reload releases](https://github.com/JetBrains/compose-hot-reload/releases)
  and its artifact POM linked above.
- [AndroidX Activity release notes](https://developer.android.com/jetpack/androidx/releases/activity)
  and Google Maven POM linked above.
- [Kotlin Coroutines releases](https://github.com/Kotlin/kotlinx.coroutines/releases)
  and artifact POMs linked above.
- [Logback releases](https://github.com/qos-ch/logback/releases) and its
  published parent POM linked above.
- [M1-DOC-006 — applicability matrix](../governance/applicability-matrix.md)
  and [M1-DOC-001 — document controls](../governance/document-control.md).

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish the initial direct dependency/SOUP inventory and preserve evidence limits, preliminary assessments, and open review actions for issue #38. |
| 0.2 | Add the stable common Compose Material 3 Adaptive and coroutine-core dependencies; record lifecycle/ViewModel use in the app shell and retain runtime-verification limitations. |
| 0.3 | Add the Cloudy liquid-glass surface-effect dependency for Settings and record its alpha status and platform-specific fallback/support limits. |
| 0.4 | Add the test-only Compose UI harness and desktop native runtime for Settings interaction tests; retain physical-device and CI verification limits. |
| 0.5 | Document the isolated Settings glass layer and shader-failure fallback; retain platform verification limitations. |
| 0.6 | Remove Cloudy from the direct dependency inventory after replacing it with Compose's native blur; retain platform and accessibility verification gaps. |
| 0.7 | Restore the prerelease Cloudy dependency for Settings and About glass; retain native Timer blur and platform and dependency review gaps. |
| 0.8 | Record provenance and licence for the bundled Material icon vector resources; retain distribution review gaps. |
| 0.9 | Extend the existing Cloudy assessment to the remaining-time glass backdrop and preserve renderer and dependency review gaps. |
| 1.0 | Record the bundled Nunito font, provenance and licence notice; retain platform and distribution review gaps. |
