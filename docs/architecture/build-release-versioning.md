# Build, version, and release-artifact conventions

| Field | Value |
|---|---|
| Document ID | M3-DOC-006 |
| Revision | 0.3 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Architecture/build, platform release, and configuration-control scopes; appointments and competence confirmation pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#38](https://github.com/Entreco/elchrono/issues/38) |
| Review PR | [PR #52](https://github.com/Entreco/elchrono/pull/52); issue #93 update: [draft PR #97](https://github.com/Entreco/elchrono/pull/97); required specialist reviews and human acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This record proposes initial identifiers for an application version, platform
build, and release artifact. The conventions support traceable builds and
platform-specific release review; they do not establish that any platform is
supported, that an artifact is reproducible, or that a release is approved.
Release approval remains a human decision for each shipped platform.

These are proposed conventions for review before first distribution. The
current project is a starter UI, has no approved release baseline, and does not
have a fully configured packaging process for every platform. Do not treat
scaffold defaults as released versions or change platform packaging solely
because this record proposes a convention.

## Observed configuration

The baseline below was observed on 2026-11-18. Android settings were rechecked
for issue #93 on 2026-11-26:

| Surface | Current source/tool output | Evidence limit |
|---|---|---|
| Android | Effective Kotlin Toolchain settings report application ID/namespace `nl.entreco.elchrono`, label `El Chrono`, `minSdk: 24`, `compileSdk: 37`, `targetSdk: 37`, build tools `37.0.0`, `versionCode: 1`, and `versionName: unspecified`; signing is disabled. `apps/mobile/android/src/AndroidManifest.xml` declares no permissions. | SDK and version values are defaults, not approved product/release decisions. The source manifest was inspected; the merged package manifest and actual runtime permissions were not verified. The built release-variant AAB is unsigned and is not a release candidate. |
| iOS | Xcode project sets `MARKETING_VERSION = 1.0` and `CURRENT_PROJECT_VERSION = 1`; `Info.plist` maps them to `CFBundleShortVersionString` and `CFBundleVersion`. | Scaffold values only; not a release baseline. |
| Desktop | Kotlin Toolchain supports JAR and executable-JAR packaging; current desktop proof was tested on macOS only. | No application product-version field or cross-OS package baseline was established. |
| Browser | Kotlin Toolchain package help lists `jar`, `executable-jar`, `aab`, and `maven-central-bundle`; it exposes no browser deployment package format. | Browser development-server output is not a verified distributable or offline package. |
| iOS packaging | Version fields exist in Xcode project settings; the Kotlin Toolchain package formats do not list an iOS archive/IPA. | A signed release archive/export workflow is not established by this record. |
| Shared/toolchain | `./kotlin show settings -m android -m ios` reports Kotlin 2.4.20 and Compose 1.12.1. | Toolchain/library versions are not the application version and are not release approval. |

`maven-central-bundle` is a library-publishing format, not a customer-facing
application artifact. A successful compile or package task alone does not
establish platform support, installability, signing, or distribution
readiness.

## Proposed identifiers

Subject to product-owner and platform review before first release:

1. **Product version:** use `MAJOR.MINOR.PATCH` (for example,
   `1.2.3`) for the user-visible application version. Do not use a scaffold
   default as the initial released version; choose the first value when the
   release baseline is reviewed.
2. **Platform build number:** use a positive monotonically increasing integer
   for each platform's application identity. Never reuse a build number for
   that identity, including after a failed or withdrawn candidate. Map Android
   to `versionCode` and iOS to `CURRENT_PROJECT_VERSION`; map the product
   version to Android `versionName` and iOS `MARKETING_VERSION`. Confirm
   store-specific limits and conventions before publishing.
3. **Cross-platform identity:** keep one product version across platforms
   released as the same application release, but retain platform-specific
   build numbers and decisions. A platform released later requires its own
   exact build and approval record; do not imply simultaneous release.
4. **Source identity:** build from a reviewed commit on the authorized release
   path. Record the full commit SHA, repository state, toolchain and relevant
   SDK versions, platform/architecture, build variant, and package format for
   every candidate artifact. Do not identify a build only by a branch name or
   version string.
5. **Artifact identity:** where a distributable is produced, name it using
   `<product>-<version>-<build>-<platform>-<target>-<format>-<short-sha>`
   followed by its actual extension. Record a SHA-256 digest and artifact
   location in the release record. The digest identifies the bytes; it does
   not prove reproducibility or safety.
6. **Release tag:** if a Git release tag is used, propose `v<MAJOR>.<MINOR>.<PATCH>`
   and create it only after the release decision for the associated scope.
   No tag or release is created by this issue.

These conventions do not determine app-store availability, support duration,
minimum OS/browser versions, or a supported-platform matrix. Those require
separate platform evidence and product-owner approval.

## Per-platform release evidence

For each platform actually proposed for distribution, maintain one release
record using the [release record template](../templates/release-record-template.md).
The record should identify:

- product version, platform build number, exact platform and architecture,
  application/package identity, build variant, artifact format, and source
  commit;
- toolchain, SDK, signing and packaging environment needed to interpret the
  build, with signing credentials excluded from the record;
- artifact path or store/distribution reference and SHA-256 digest;
- exact verification commands/results, applicable requirements/risks, test
  environment, and unresolved anomalies;
- relevant support-matrix scope, accessibility/input and lifecycle evidence,
  provider/audio and offline evidence where applicable, privacy/licence/user
  information, and limitations;
- reviewer scopes, findings/disposition, and the human's per-platform release
  decision recorded in the PR/release process.

The PR's human acceptance evidence remains in the PR description and must not
be copied into a separate document or represented as a template field.
Required specialist review, exact-head human acceptance, issue acceptance, and
platform release approval are distinct gates.

## Open packaging and release limitations

- Android package settings currently rely on `versionCode: 1` and
  `versionName: unspecified` defaults. A reviewed configuration change is
  required before distribution.
- Issue #93's Android build and AAB packaging tasks passed on 2026-11-26, but
  the resulting AAB is unsigned and no physical-device installation or launch
  was possible; see the linked test-protocol execution record.
- iOS scaffold values are `1.0` and `1`; signing/archive/export, store
  delivery, and release verification are not established here.
- Desktop executable-JAR packaging was observed in the macOS feasibility work,
  but independent install/launch and non-macOS packaging remain unverified.
- The browser toolchain package interface has no listed web deployment format.
  Static hosting, version metadata, cache invalidation, and offline behavior
  remain unresolved.
- No versioned release artifact has been approved. Do not claim TV/watch
  support or infer it from Android/iOS builds.
- This record does not establish reproducible builds, software bill of
  materials generation, signing policy, release automation, or a retention
  period. Assign and verify those controls before they are needed for a
  distribution decision.

Keep unsupported, unavailable, and unverified targets explicit. Reassess
applicability and release obligations for actual functions, claims, markets,
distribution channels, data flows, and providers before affected work or
distribution proceeds. This record preserves El Chrono's non-medical
visual-waiting purpose and makes no medical claim, IEC 62304 applicability or
conformity claim, or certification claim.

## Sources

- [`apps/mobile/android/module.yaml`](../../apps/mobile/android/module.yaml),
  [`apps/mobile/ios/module.xcodeproj/project.pbxproj`](../../apps/mobile/ios/module.xcodeproj/project.pbxproj),
  and [`apps/mobile/ios/src/Info.plist`](../../apps/mobile/ios/src/Info.plist).
- `./kotlin show settings -m android -m ios` and `./kotlin package --help`,
  inspected on 2026-11-18; `./kotlin show settings -m android`, the Android
  build, and AAB packaging rechecked on 2026-11-26. See the issue #93
  [execution record](../testing/test-protocol.md#issue-93-android-mobile-release-readiness-evidence).
- [M3-DOC-001 — browser and desktop feasibility findings](platform-feasibility-findings.md)
  and [M3-DOC-002 — TV and watch feasibility findings](tv-watch-feasibility-findings.md).
- [M1-DOC-001 — document controls](../governance/document-control.md),
  [M1-DOC-006 — applicability matrix](../governance/applicability-matrix.md),
  and [M1-DOC-007 — software development plan](../governance/software-development-plan.md).

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Propose build/version/artifact identification conventions for issue #38; document current scaffold values and unresolved platform packaging/release evidence. |
| 0.2 | Recheck Android package identity, effective default SDK/version settings, permissions declaration, and unsigned AAB status for issue #93 without approving release values or support. |
| 0.3 | Link the issue #93 Android configuration evidence to draft PR #97; required review and release decisions remain pending. |
