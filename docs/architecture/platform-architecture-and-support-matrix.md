# Platform architecture decision and support matrix

| Field | Value |
|---|---|
| Document ID | M3-DOC-007 |
| Revision | 0.2 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Cross-platform architecture; browser/desktop; Android/iOS; TV input; provider, privacy, licensing/policy, lifecycle/audio, and accessibility/input scopes: @Entreco, designated by the product owner on 2026-11-19; final exact-head acceptance pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#39](https://github.com/Entreco/elchrono/issues/39) |
| Review PR | Prior baseline: [PR #54](https://github.com/Entreco/elchrono/pull/54); issue #47 update: [PR #59](https://github.com/Entreco/elchrono/pull/59); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Context and scope

This record uses the merged feasibility findings for browser/desktop (#33),
TV/watch (#34), Android/iOS (#35), and audio providers/background execution
(#36) to decide which cross-platform architecture to continue evaluating and
to define the evidence-backed platform status. It is an architecture and
feasibility decision, not a release approval, product validation, or
platform-support commitment.

The current app shell is being implemented for the browser under issue #47.
El Chrono remains non-medical visual
waiting-time support; this record does not introduce diagnosis, treatment,
medical monitoring, safety-critical timing, or medical claims. It does not
decide timer recovery, pause/reset behavior, caregiver protection, volume
limits, completion behavior, companion-app behavior, or any behavior beyond
the approved M2 requirements and product decisions. The M2 design baseline is
approved by the product owner for issue #47; product verification remains open.

## Evidence basis

The following source revisions are pinned to their merged pull-request heads.
They provide inputs to this decision; the source records retain their own
review and evidence limitations.

| Source | Evidence used |
|---|---|
| [M3-DOC-001, revision 0.2](https://github.com/Entreco/elchrono/blob/345c0acf413cf55bd521d49fe0123e388ad4bc9c/docs/architecture/platform-feasibility-findings.md), [PR #42](https://github.com/Entreco/elchrono/pull/42) | Browser/desktop on macOS 26.6.2 arm64 and Chrome 157.0.8103.79; Kotlin Toolchain 0.13.0 and JVM 25.0.4. Browser launch succeeded on port 8081 after the default port was occupied; desktop rendered on macOS and executable-JAR packaging succeeded. Other hosts, browser baselines, offline behavior, keyboard/accessibility, and independent package launch were not verified. |
| [M3-DOC-002, revision 0.2](https://github.com/Entreco/elchrono/blob/54d7c7f7711f0b5b7ae939eb2dc24ea25bb4705c/docs/architecture/tv-watch-feasibility-findings.md), [PR #49](https://github.com/Entreco/elchrono/pull/49) | TV/watch targets are not configured. Apple TV/watch simulator runtimes/devices and usable Android TV/Wear OS targets were unavailable. Remote/focus, watch interaction, lifecycle, provider playback, packaging, and usability were not demonstrated. |
| [M3-DOC-003, revision 0.1](https://github.com/Entreco/elchrono/blob/54d7c7f7711f0b5b7ae939eb2dc24ea25bb4705c/docs/architecture/audio-provider-feasibility-findings.md), [PR #49](https://github.com/Entreco/elchrono/pull/49) | Official provider constraints were assessed on 2026-11-18, without SDK installation, credentials, provider accounts, runtime playback, or network testing. YouTube's reviewed IFrame API is a visible player, not an audio-only stream, and does not permit hidden/minimized-window background playback. Spotify routes have subscription, authorization, policy, privacy, platform, and app-availability constraints; no provider is selected or approved. |
| [M3-DOC-004, revision 0.3](https://github.com/Entreco/elchrono/blob/4766634e45ffa74203761ea42f8ba6430ec056c3/docs/architecture/android-ios-platform-feasibility-findings.md), [PR #50](https://github.com/Entreco/elchrono/pull/50) | Android build/AAB packaging succeeded on macOS 26.6.2, but no usable Android launch target was available. iOS built and rendered the starter shell on an iPhone 18 Pro simulator with iOS 27.2; no physical-device, accessibility, lifecycle, provider, offline, or release-signing behavior was verified. |

The four findings PRs are merged, but their records identify required review
scopes and/or human acceptance as pending. Merge and issue closure are not
evidence that those reviews occurred. Required reviewers of this candidate
must assess both this synthesis and any relevant source gaps; no missing
review is represented as complete here.

## Decision

Continue the existing Kotlin Multiplatform and Compose Multiplatform
architecture as the shared implementation foundation for the currently
configured browser, desktop, Android, and iOS launchers. Keep platform-specific
packaging, lifecycle, audio, system-volume, and provider integration behind
the existing launcher/platform boundaries; keep timer-domain behavior
independent of rendering and music integrations. Do not replace the toolchain
or framework without a separately reviewed architecture decision.

This is a decision to retain the current engineering approach and implement
the browser MVP against the owner-approved M2 design baseline. Android TV and
Apple TV are primary project targets, but have no configured targets or
support commitment. Wear OS and watchOS are outside v1. This record does not
declare any platform supported, select minimum OS/browser versions, or
establish feature parity. Configured-target evidence remains limited to the
demonstrations listed below.

### Alternatives and trade-offs

| Option | Benefits | Costs, risks, and evidence | Disposition |
|---|---|---|---|
| Continue Kotlin Multiplatform/Compose with existing launchers | Preserves the configured project, shared shell, module ownership, and current launcher proofs without a parallel build. Platform APIs can remain isolated. | Current results cover a starter shell only; Android launch and physical-device behavior are missing; minimum versions, offline operation, accessibility/input, lifecycle recovery, and provider behavior are not established. | Selected as the existing engineering foundation, subject to approved requirements and platform-specific verification. |
| Replace the framework or build separate native applications now | Could enable platform-native implementation choices and APIs. | No comparative prototype or evidence demonstrates a material benefit; replacement would exceed the feasibility evidence and require an approved architecture decision, migration, and maintenance assessment. | Not selected. Revisit only with evidence and a separate reviewed decision. |
| Configure primary TV targets now or infer support from shared/mobile code | Could advance the project's primary TV direction. | No configured target or suitable test environment is evidenced; packaging, input, lifecycle, provider support, and usability are unverified. Shared code/mobile builds do not prove TV support. | Defer target configuration until its own reviewed scope and test environment are available. Android TV and Apple TV remain primary future targets, not supported platforms. Wear OS and watchOS are outside v1. |

## Platform support matrix

Status terms describe repository configuration and observed evidence, not
release readiness:

- **Configured**: a repository launcher/module exists.
- **Demonstrated**: the specific evidence and environment stated in the row
  were observed; this does not mean the product is validated.
- **Candidate**: retained for feasibility study without a configured target or
  support commitment.
- **Unsupported**: an applicable authoritative constraint rules out the
  particular integration or behavior named; it must not be generalized to the
  whole platform.
- **Unverified**: required evidence is absent or not tested. Do not infer
  support or lack of support from this status.

| Target | Status and observed environment | Input, lifecycle, and packaging evidence | Audio/provider constraints | Support baseline and decision |
|---|---|---|---|---|
| Browser (Kotlin/Wasm) | Configured; demonstrated in Chrome 157.0.8103.79 on macOS 26.6.2 build 25G83 arm64, with Kotlin Toolchain 0.13.0 and JVM 25.0.4. Browser rendering and pointer activation were observed using port 8081; the default port was occupied. The #47 browser target builds in this PR; no interactive browser run is claimed. | Keyboard, screen reader, touch, deployment packaging, offline startup, other browsers/OSs, and interruption behavior remain unverified. No minimum browser version is established. | No provider integration or runtime test. YouTube requires a visible standard player (minimum 200 × 200 viewport) and does not permit hidden/minimized-window background playback. Spotify Web Playback requires OAuth with the `streaming` scope and Premium eligibility; app-specific background behavior is unverified. | No browser support baseline proposed. Minimum browser/OS versions and offline behavior require testing and review. |
| Android | Configured; build and unsigned AAB packaging demonstrated on macOS 26.6.2 build 25G83 arm64 with Kotlin Toolchain 0.13.0, Android command-line tools 20.0, ADB 37.0.0-14910828, and emulator 37.1.11.0. The inspected SDK had platforms android-35, android-36, android-36.1, android-37.0, android-37.1 and build-tools 34.0.0, 36.0.0, 36.1.0, 37.0.0; no usable emulator/device was available for installation or launch. | Touch, accessibility, lifecycle/recovery, actual package installation, signing, and runtime behavior are unverified. No minimum Android version is established. | No playback or volume integration. Android audio focus, background media-service requirements, and shared system-volume constraints need platform-specific design and verification. YouTube WebView use would still require a visible player and would not permit background audio-only playback; Spotify App Remote depends on authorization and the Spotify app. Neither is integrated. | Configured only; no Android support baseline proposed. |
| iOS | Configured; simulator build, launch, and initial shell rendering demonstrated on iPhone 18 Pro / iOS 27.2 build 24B8117 simulator under Xcode 27.2 build 27B52 on macOS 26.6.2 build 25G83 arm64. Kotlin Toolchain 0.13.0 and JVM 25.0.4 were used. | Simulator evidence only. Physical-device signing/install, interaction/accessibility, lifecycle/recovery, interruption, and release packaging are unverified. No minimum iOS version is established. | No playback or volume integration. Audio-session interruptions and the limits of background execution require verification. YouTube's visible-player/no-background-audio constraints still apply in a WebView. The reviewed Spotify App Remote path requires the Spotify app and physical-device verification; it was not tested. | Configured and simulator-demonstrated only; no iOS support baseline proposed. |
| Desktop (JVM) | Configured; starter shell rendered on macOS 26.6.2 build 25G83 arm64 using Kotlin Toolchain 0.13.0 and JVM 25.0.4 (`25.0.4+-16339643-b508.27`). Executable-JAR packaging completed. | Only macOS was exercised; the JAR was not independently launched. Windows/Linux, installer/signing, keyboard/accessibility, offline startup, and lifecycle behavior are unverified. Desktop OS scope and minimum OS/JVM versions are undecided. | No embedded browser/provider integration or playback test. YouTube embedding would require a suitable visible player; Spotify Web Playback browser evidence does not establish support in the JVM launcher. | macOS demonstration only. Do not claim desktop support until desktop OS scope, minimum versions, packaging, and verification are reviewed. |
| Android TV | Primary future target; unconfigured and unverified. Read-only inspection was on macOS 26.6.2 build 25G83 arm64 with Android command-line tools 20.0, ADB 37.0.0-14910828, and emulator 37.1.11.0; no usable Android TV emulator/device was available and installed TV packages could not be confirmed. | D-pad/focus order, viewing-distance readability, accessibility, packaging, lifecycle, and runtime behavior require a configured target and review. | TV output routes/formats can change and unsupported output can fail. No El Chrono provider SDK or TV integration was established. Spotify Connect routing is not evidence of an El Chrono TV SDK. | Primary product target; no target or support commitment until configured and verified. |
| Apple TV | Primary future target; unconfigured and unverified. Xcode 27.2 build 27B52 on macOS 26.6.2 listed tvOS 27.2 SDK, but no tvOS simulator runtime/device or repository target was available. | Remote/focus navigation, accessibility, packaging, lifecycle, and runtime behavior are unverified. SDK presence does not establish an app build or launch. | No tvOS provider path, playback, or audio-routing behavior was established. | Primary product target; no target or support commitment until configured and verified. |
| Wear OS | Outside v1; unconfigured and unverified. Read-only inspection was on macOS 26.6.2 build 25G83 arm64 with Android command-line tools 20.0, ADB 37.0.0-14910828, and emulator 37.1.11.0; no usable Wear OS emulator/device was available and installed packages could not be confirmed. | Small-display interaction, ambient refresh, state recovery, long-running activity, battery use, packaging, and usability are unverified. | No watch output route, provider SDK, or standalone/companion provider path was established. | Outside v1; no target or support commitment. |
| watchOS | Outside v1; unconfigured and unverified. Xcode 27.2 build 27B52 on macOS 26.6.2 listed watchOS 27.2 SDK, but no watchOS simulator runtime/device or repository target was available. | Small-display input, Always On behavior, lifecycle/recovery, packaging, and usability are unverified. | No provider integration or playback test. No companion, audio, or background policy is selected. | Outside v1; no target or support commitment. |

No whole platform is classified as unsupported by this evidence. The
unsupported behavior specifically evidenced here is YouTube audio-only
extraction or hidden/minimized-window background playback through the reviewed
IFrame Player API. All other unverified provider/platform combinations remain
unverified, not implicitly supported or rejected.

## Cross-cutting boundaries and open decisions

- **Product and intended use:** Preserve the non-medical intended use and
  current no-clock presentation. This decision does not authorize diagnosis,
  treatment, monitoring, safety-critical timing, a watch face, or an alarm
  framing.
- **Timer reliability and lifecycle:** The browser MVP records a hidden-page
  or page-hide event and pauses when that event is detected after return; it
  warns that progress may be out of date and does not automatically resume.
  This behavior has not been product-tested. Other platforms have no lifecycle
  implementation here. Cross-restart persistence is not provided.
- **Audio and provider boundary:** Music remains optional. Timer operation must
  work without internet or music, and audio/provider failures must be explicit
  without interrupting the timer. Do not select a provider or claim a
  supported-platform integration from these findings. Resolve Spotify audience,
  subscription, commercial-use, policy, privacy, and licensing questions before
  considering it for implementation.
- **Accessibility and input:** Browser keyboard/screen reader, mobile touch and
  assistive technology, desktop keyboard, TV remote/focus, and watch interaction
  require their own verification. A successful render or build is not
  accessibility evidence.
- **Data and privacy:** No provider account, token flow, patient account,
  identifiable patient data, telemetry, or new network behavior is approved by
  this record. Assess data flows, consent, token storage/retention, and
  disconnection before a future provider decision.
- **Support scope:** Set minimum browser and mobile OS versions and desktop OS
  scope only after platform/package, input/accessibility, lifecycle, offline,
  provider, and usability evidence is reviewed for the proposed distribution.
- **M2 dependency:** The product owner approved the M2 design baseline for
  issue #47. This does not verify requirements, risks/controls, platform
  support, or unresolved product behavior, and does not replace exact-head
  acceptance of the current PR.

## Limitations and follow-up

| Gap | Owner | Required evidence or decision | Follow-up |
|---|---|---|---|
| Required specialist review and human acceptance for the source feasibility records remain pending as documented. | @Entreco | Confirm applicable reviewer scopes and findings disposition against the exact source revisions; do not infer acceptance from merge/closure. | Review this PR and the source records. |
| Browser default-port launch, additional browser/host coverage, offline startup, keyboard/accessibility, and independent desktop package launch are incomplete. | @Entreco; platform reviewers | Re-run in a suitable environment and record exact versions, commands, results, and limitations before proposing a browser/desktop baseline. | Future platform verification issue; not claimed complete here. |
| Android runtime, physical-device behavior, iOS physical-device behavior, signing, lifecycle, offline operation, and accessibility remain unverified. | @Entreco; Android/iOS reviewers | Obtain authorized test targets through the approved workflow and verify packaging, input, lifecycle, audio, and recovery without changing product behavior implicitly. | Reassess before platform support/release decisions. |
| TV/watch targets, packaging, remote/watch input, lifecycle, provider feasibility, and usability are unverified. | @Entreco; TV/watch reviewers | Configure and review separate targets and test environments only after the feasibility and UX gates are approved. | Keep TV/watch feasibility-only; no implementation commitment in this record. |
| Minimum OS/browser versions, desktop OS scope, and product/provider eligibility decisions are open. | @Entreco; applicable platform/privacy/licensing reviewers | Decide only from current official requirements, approved product scope, and recorded tests/reviews. Recheck provider and platform policies before implementation. | Revisit this record when evidence or intended scope changes. |

## Requirements, risks, and dependencies

| Relationship | Source | Impact and verification |
|---|---|---|
| Architecture constraint | [Module layout](module-layout.md) | Retain the launcher → common shell → feature/core dependency direction; keep platform APIs outside core. Reassess if a separately reviewed architecture decision changes it. |
| Intended-use boundary | [Intended use](../product/intended-use.md) | No medical claims or safety-critical medical-timing purpose. Reassess if functionality, claims, or markets change. |
| Requirements and risks | M2 requirements, risk, usability, and verification records | Their gate remains open. Do not invent requirement/risk IDs or treat this record as approval/verification; map this decision to stable IDs after the applicable M2 records are approved. |
| Provider feasibility | M3-DOC-003, revision 0.1 | Feasibility input only; provider choice, eligibility, licensing, data-flow review, and integration are unresolved. |
| Platform feasibility | M3-DOC-001, M3-DOC-002, and M3-DOC-004 | Source findings and limitations are summarized above; this synthesis does not replace platform-specific verification. |
| Dependency/SOUP | M3-DOC-005 | Use the indexed dependency inventory for future provider/platform implementation; its preliminary assessments and open reviews are not approval to add dependencies. |

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Initial evidence-based architecture decision and platform support matrix for issue #39; source review gaps and support-baseline limitations remain explicit. |
| 0.2 | Record Android TV and Apple TV as primary future targets, Wear OS/watchOS outside v1, the owner-approved KMP/Compose implementation basis, and browser MVP evidence without claiming platform support. |
