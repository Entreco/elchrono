# TV and watch platform feasibility findings

| Field | Value |
|---|---|
| Document ID | M3-DOC-002 |
| Revision | 0.2 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | TV/watch platform feasibility, interaction/accessibility, and platform-tooling scopes; appointments and competence confirmation pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#34](https://github.com/Entreco/elchrono/issues/34); provider feasibility follow-up [#36](https://github.com/Entreco/elchrono/issues/36) |
| Review PR | Revision 0.1 in [PR #43](https://github.com/Entreco/elchrono/pull/43); revision 0.2 in [PR #49](https://github.com/Entreco/elchrono/pull/49); required reviews and human acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This record assesses the feasibility and constraints of Android TV, Apple TV,
Wear OS, and watchOS using repository configuration, read-only local toolchain
inspection, and official platform guidance reviewed on 2026-11-18. It does not
configure targets, install SDK packages, accept licenses, launch or alter
devices, or establish product support.

El Chrono remains non-medical visual waiting-time support. The current app is a
starter shell, not a timer. This assessment does not select timer interruption,
background recovery, pause/reset, volume, provider, companion-app, or other
product behavior. The application must not present a clock face or time of day;
this is not a proposal to build a watch face or complication.

## Evidence method and environment

All local checks were read-only and run on macOS 26.6.2 (build 25G83, arm64).
No TV/watch build or launch was attempted because the repository declares no
such targets. The checks were:

- `./kotlin show modules`
- `xcodebuild -version` and `xcodebuild -showsdks`
- `xcrun simctl list runtimes` and `xcrun simctl list devices available`
- `sdkmanager --list_installed`, `avdmanager list avd`,
  `emulator -list-avds`, and `adb devices -l`

The reserved `apps/television` and `apps/watch` directories contain README
files only. Neither directory is registered in `project.yaml` or discovered by
the Kotlin Toolchain. Discovered app modules include Android and iOS, but not
tvOS or watchOS.

| Platform | Local tooling and target evidence | Result and limitation |
|---|---|---|
| Android TV | Android SDK command-line tools are on PATH. `avdmanager list avd` reported only `Foldable`, whose Google APIs arm64-v8a system image is missing; `emulator -list-avds` listed only `Foldable`; `adb devices -l` listed no devices. `sdkmanager --list_installed` emitted a shell warning and loading progress but no installed-package entries in the captured output. | No usable TV emulator or connected test device was available. Installed Android TV packages could not be confirmed from the package-list output. No TV module or launch command is configured. |
| Apple TV | Xcode 27.2 (build 27B52) lists tvOS 27.2 and the tvOS Simulator SDK in `xcodebuild -showsdks`. | `xcrun simctl list runtimes` and `xcrun simctl list devices available` showed iOS runtimes/devices only, with no tvOS runtime or Apple TV simulator. No tvOS module or launch command is configured. |
| Wear OS | Same Android SDK/AVD/device inspection as Android TV. | No usable Wear OS emulator or connected test device was available; installed Wear OS packages could not be confirmed. No Wear OS module or launch command is configured. |
| watchOS | Xcode 27.2 lists watchOS 27.2 and the watchOS Simulator SDK in `xcodebuild -showsdks`. | The available simulator runtime/device lists showed iOS only, with no watchOS runtime or Apple Watch simulator. No watchOS module or launch command is configured. |

SDK availability is not evidence that an app target can be built, packaged,
launched, or supported. No signing configuration, physical TV/watch hardware,
or platform installation was evaluated.

## TV interaction and runtime constraints

### Android TV

Android TV's official getting-started guidance describes a substantially
different interaction model from phones and tablets: viewers may be about ten
feet from the screen and should be able to navigate using a directional pad and
select button. A future TV experience therefore needs a viewing-distance
layout, complete focus order, predictable D-pad behavior, and accessibility
checks; none was exercised here. Android TV's accessibility guidance also
calls out navigation guidance, element labels, TalkBack compatibility, and
text scaling.

Android TV can route audio through TV speakers, HDMI, Bluetooth, and other
outputs. Available formats and routes can change during playback, and an
unsupported format can produce errors or silence. This is a platform playback
constraint, not evidence that a YouTube or Spotify integration is available.
Provider authentication, licensing, playback, and background restrictions
remain for issue [#36](https://github.com/Entreco/elchrono/issues/36).

### Apple TV

Apple's tvOS guidance describes a large display commonly viewed from several
feet away, remote/controller/voice inputs, and a focus system that helps people
understand which item is selected. A future implementation would need legible
content at viewing distance and reliable focus-based navigation. No remote,
focus, accessibility, packaging, or runtime behavior was tested.

The tvOS SDK is installed, but this repository has no Apple TV target and the
local inspection found no tvOS simulator runtime/device. SDK presence alone
does not establish that the current Compose Multiplatform project can create or
run a tvOS application.

## Watch interaction, lifecycle, and companion constraints

### Wear OS

Google recommends that Wear OS apps work independently of a phone when their
core functions can do so. A non-standalone design must account for the phone
app's availability; the timer's offline/core operation must not be made
dependent on a provider, network, or paired phone. Whether any optional
provider connection could use a companion remains unassessed.

Wear OS guidance emphasizes brief, glanceable tasks, large touch targets, and
preserving app state across navigation. Its app-quality checklist requires an
ongoing activity or live update for a long-running operation. Wear OS ambient
behavior is system-managed; updates may be as infrequent as once per minute,
and continuous display/keep-screen-on behavior affects battery life. These
constraints make the accuracy and usefulness of an always-visible remaining
time display an open product and technical question. They must be assessed
against the timer's actual lifecycle, UX, and verification requirements, not
resolved by this feasibility record.

The quality checklist's requirement to show time of day applies to watch
faces, not ordinary apps. A watch-face implementation would conflict with El
Chrono's no-clock presentation boundary and is not included in this assessment.

### watchOS

Apple's watchOS guidance emphasizes small displays, glanceable information,
brief and focused tasks, simple gestures, and Digital Crown navigation. It
also recommends designing the app to function independently. This supports
further study of a focused timer surface, but does not demonstrate that a
24-hour timer can remain visible, preserve state, recover from suspension, or
play optional provider audio on watchOS.

Apple's Always On guidance says the app interface can remain visible while it
is frontmost or running a background session; the system dims the display and
expects low-motion, infrequent updates. WatchKit's `WKExtendedRuntimeSession`
continues an app after interaction stops, but each app can support only one of
the documented session types: self care, mindfulness, physical therapy, or
smart alarm. This assessment does not establish that El Chrono's defined
non-medical timer is eligible for any of those types or that a generic
24-hour background session is available. Do not select a session type or
reframe the product as an alarm without separate product and platform review.

The watchOS and watchOS Simulator SDKs are present in Xcode, but no watchOS
simulator runtime/device or repository target was found. Background execution,
audio routing, companion behavior, packaging, and physical-device lifecycle
remain unverified.

### Cross-platform audio and lifecycle

No YouTube or Spotify integration, provider authentication, or cross-device
handoff was tested. Provider-specific policies and data-flow constraints are
assessed separately in [M3-DOC-003, revision 0.1](audio-provider-feasibility-findings.md).
The platform-specific audio evidence and remaining work are:

| Platform | Evidence and limitation | Follow-up |
|---|---|---|
| Android TV | Output may route through TV speakers, HDMI, or Bluetooth; routes/formats can change and unsupported output can fail. This does not establish YouTube/Spotify support. | M3-DOC-003 records provider constraints but no TV-specific integration or test evidence; retain the unconfigured/unverified status. |
| Apple TV | No Apple TV target, test runtime, or device was available; provider playback, audio routing, and background behavior were not verified. | M3-DOC-003 records no established tvOS provider path; retain the unconfigured/unverified status. |
| Wear OS | Standalone status matters if a provider or phone connection is needed; no watch output route or provider SDK was tested. The timer's core/offline operation cannot depend on a paired phone or provider. | M3-DOC-003 distinguishes Spotify Connect routing from a Wear OS SDK; no direct integration or companion decision is established. |
| watchOS | Independent app design is recommended by Apple, but no watchOS target or test runtime was available; provider playback and connectivity were not verified. | M3-DOC-003 records no established watchOS provider path; retain the unconfigured/unverified status. |

These are evidence gaps, not claims that a provider is supported or
unsupported. M3-DOC-003 is a documentation assessment, not an implementation
or platform test. Audio remains optional and must not be represented as
supported on TV or watch.

The product's maximum 24-hour duration makes lifecycle, display refresh,
power consumption, and recovery important open questions on watches. This
record does not prescribe whether the timer runs in the background, what a
paused/interrupted watch UI looks like, or how the system's ambient display
interacts with the no-clock requirement. Those choices require the applicable
requirements, risk, and UX review before implementation.

## Feasibility summary

| Platform | Assessment | Evidence and next constraint |
|---|---|---|
| Android TV | Candidate; unconfigured and unverified | Android supports TV-specific apps, but no usable local TV target was found. D-pad/focus, viewing-distance readability, accessibility, packaging, lifecycle, and audio routing need a TV prototype and test target. |
| Apple TV | Candidate; unconfigured and unverified | tvOS SDK exists, but no simulator runtime/device or repository target was found. Remote/focus interaction, packaging, accessibility, and runtime behavior need a configured target and test evidence. |
| Wear OS | Candidate; unconfigured and unverified | No usable local watch target was found. Standalone/offline behavior, ambient refresh, long-running activity, state recovery, battery impact, small-screen accessibility, and optional provider limits need further study. |
| watchOS | Candidate; unconfigured and unverified | watchOS SDK exists, but no simulator runtime/device or repository target was found. Independent operation, background/lifecycle behavior, display semantics, accessibility, and audio/provider options need further study. |

These assessments are feasibility-only. They do not establish a supported
platform, minimum OS version, feature parity, release commitment, or M3 gate
completion. Mobile compilation or shared source code must not be used as TV or
watch support evidence.

## Evidence gaps and follow-up

- Obtain authorized TV/watch simulator runtimes or physical test targets through
  the approved development workflow; do not install or modify SDK/device state
  as part of this assessment.
- Configure and review separate TV/watch targets before claiming build,
  packaging, input, lifecycle, accessibility, audio, or offline behavior.
- Coordinate provider and background-playback findings with issue #36.
- Resolve whether the timer's duration, offline requirement, no-clock
  presentation, and recovery expectations can be met on watch displays without
  introducing unreviewed product behavior.
- Keep TV/watch as candidates only until platform-specific toolchain,
  packaging, input, lifecycle, provider, and usability evidence is reviewed.

## Sources

Official guidance was reviewed on 2026-11-18. Platform policies and SDK
availability can change; recheck them when making an implementation or release
decision.

| Source | Relevance |
|---|---|
| [Android TV: Get started](https://developer.android.com/training/tv/get-started) | TV viewing distance, D-pad/select input, and TV navigation. |
| [Android TV accessibility](https://developer.android.com/training/tv/accessibility) | Navigation guidance, labels, TalkBack, and text scaling. |
| [Android TV audio capabilities](https://developer.android.com/training/tv/playback/audio-capabilities) | Multiple audio routes, route changes, supported formats, and playback errors. |
| [Wear OS design principles](https://developer.android.com/design/ui/wear/guides/m2-5/foundations/design-principles) | Critical tasks, wrist ergonomics, and short interactions. |
| [Wear OS standalone apps](https://developer.android.com/training/wearables/apps/standalone-apps) | Standalone versus companion-dependent app behavior. |
| [Wear OS app quality requirements](https://developer.android.com/docs/quality-guidelines/wear-app-quality) | Touch targets, state preservation, and long-running activity requirements. |
| [Wear OS always-on apps](https://developer.android.com/training/wearables/always-on) | Ambient refresh, system state transitions, and battery impact. |
| [Apple Human Interface Guidelines: Designing for tvOS](https://developer.apple.com/design/human-interface-guidelines/designing-for-tvos) | Large-screen viewing, remote inputs, and focus navigation. |
| [Apple Human Interface Guidelines: Designing for watchOS](https://developer.apple.com/design/human-interface-guidelines/designing-for-watchos) | Glanceable interactions, watch input, and independent app design. |
| [Apple Human Interface Guidelines: Always On](https://developer.apple.com/design/human-interface-guidelines/always-on) | watchOS display dimming, frontmost/background-session visibility, and low-motion updates. |
| [Apple WatchKit: WKExtendedRuntimeSession](https://developer.apple.com/documentation/watchkit/wkextendedruntimesession) | Extended runtime behavior and its limited documented session types. |

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record read-only TV/watch tooling evidence, official platform constraints, feasibility status, and unresolved implementation/review gaps for issue #34. |
| 0.2 | Link M3-DOC-003 provider findings and update TV/watch audio follow-up without claiming a provider path, platform support, or testing. |
