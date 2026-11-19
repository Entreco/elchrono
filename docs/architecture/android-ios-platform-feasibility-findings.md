# Android and iOS platform feasibility findings

| Field | Value |
|---|---|
| Document ID | M3-DOC-004 |
| Revision | 0.3 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Android/iOS platform feasibility, platform tooling, lifecycle/background execution, audio/system-volume, and accessibility/input scopes; appointments and competence confirmation pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#35](https://github.com/Entreco/elchrono/issues/35) |
| Review PR | [PR #50](https://github.com/Entreco/elchrono/pull/50); required reviews and human acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This record evaluates the configured Android and iOS launchers as technical
proofs for the shared Compose application shell. It builds on the starter-demo
evidence in the [module layout decision](module-layout.md) and follows the
[M2 evidence handoff](../governance/m2-evidence-handoff.md). At the source
snapshot reviewed for this feasibility record, the M2 gate remained open; this
evaluation did not clear that historical dependency.

At the source snapshot used for these technical proofs, the app was a starter
shell, not a timer. Successful compilation,
packaging, or a simulator launch is not product validation, a supported-
platform commitment, or evidence that timer behavior survives interruptions.
No timer recovery, pause/reset, volume limit, completion behavior, or other
unresolved product behavior is selected here.

El Chrono remains non-medical visual waiting-time support. This record makes no
medical claims and does not position the app for diagnosis, treatment,
monitoring, or safety-critical medical timing.

## Evidence environment

All checks were run on the available macOS host. No SDK packages were
installed, licenses accepted, or physical devices altered.

| Component | Observed version or state |
|---|---|
| Host | macOS 26.6.2, build 25G83, arm64 |
| Kotlin Toolchain | 0.13.0 (`abc7a5f`, 2026-10-01) |
| JVM | OpenJDK 25.0.4 (`25.0.4+-16339643-b508.27`), arm64 |
| Android command-line tools | `sdkmanager` 20.0; `adb` 37.0.0-14910828; emulator 37.1.11.0 |
| Android SDK contents | Platform directories `android-35`, `android-36`, `android-36.1`, `android-37.0`, `android-37.1`; build-tools `34.0.0`, `36.0.0`, `36.1.0`, `37.0.0` |
| Android test targets | No connected device. The only AVD, `Foldable`, could not load because its Google APIs arm64-v8a system image is missing. |
| Xcode | 27.2 (build 27B52) |
| iOS simulator target | iPhone 18 Pro (`iPhone19,2`), iOS 27.2 runtime (build 24B8117); simulator only |
| Physical test devices | None listed by `adb devices -l` or `xcrun devicectl list devices` |

`./kotlin show modules` discovered the configured `android` (`android/app`)
and `ios` (`ios/app`) launchers. `sdkmanager --list_installed` emitted a shell
warning and loading progress but no package entries in the captured output;
the Android SDK directory listing above records the observed local platform
and build-tools versions instead.

## Execution evidence

Build, packaging, and launch commands were run from the repository root in the
issue-specific worktree. Host/SDK inventory was read-only on the same host.

| Check | Command | Observed outcome | Limitation |
|---|---|---|---|
| Module discovery | `./kotlin show modules` | Android and iOS launchers were discovered. | Discovery is not platform-support evidence. |
| Android build | `./kotlin build -m android` | Successful. | Compilation only; no Android runtime target was available. |
| Android package | `./kotlin package -m android --format aab` | Successful; generated `build/tasks/_android_bundleAndroid/gradle-project-release.aab`. | No installation or launch was possible without an emulator/device. |
| Android signing | `jarsigner -verify -verbose -certs build/tasks/_android_bundleAndroid/gradle-project-release.aab` | Reported `jar is unsigned`. | No release signing configuration or credentials were used or assessed. |
| Android target inventory | `adb devices -l`; `avdmanager list avd`; `emulator -list-avds` | No connected devices; `Foldable` was listed but could not load because its system image is missing. | Android installation, launch, rendered resources, physical-device behavior, and signing were not verified. No system image was installed. |
| iOS simulator build | `./kotlin build -m ios -p iosSimulatorArm64` | Successful. | This is an iOS simulator target, not a physical-device build or release archive. |
| iOS simulator launch | `./kotlin run -m ios -p iosSimulatorArm64 --device-id 6E4C017A-0A9D-40FE-A81C-9A73B712DE74` | Successful; the app started as `nl.entreco.elchrono.ElChrono` on the iPhone 18 Pro simulator. `xcrun simctl get_app_container` confirmed an installed `.app` bundle. | Simulator launch does not establish physical-device installation, distribution, or product behavior. |
| iOS simulator signing | `codesign -dv --verbose=2 "$(xcrun simctl get_app_container 6E4C017A-0A9D-40FE-A81C-9A73B712DE74 nl.entreco.elchrono.ElChrono app)"` | The simulator app bundle had an ad-hoc signature and no TeamIdentifier. | No physical-device provisioning, signing, IPA/archive packaging, or distribution was tested. |
| iOS rendering | From the local session files directory: `xcrun simctl io 6E4C017A-0A9D-40FE-A81C-9A73B712DE74 screenshot issue35-ios-simulator.png` | The initial screen rendered the themed Compose shell and the localized `Click me!` resource string. The screenshot is retained as a local session artifact, not committed. | The conditional image/greeting state, interaction, accessibility, and screen-reader behavior were not exercised. |

The iOS screenshot also shows the operating system's status-bar time. That is
system chrome rather than Compose content; whether it should remain visible
alongside the product's no-clock presentation requires product/UX review. No
launcher behavior was changed here.

## Packaging, installation, and signing

The Android release AAB packaging task completed, but the produced bundle is
unsigned and was not installed or launched. No authorized Android emulator or
device was available. This is packaging evidence only.

The iOS simulator app was installed and launched by the wrapper. Its local
simulator bundle was ad-hoc signed without a team identifier. No physical iOS
device was available, so device provisioning, physical installation, release
signing, archive/IPA creation, and distribution remain unverified.

## Lifecycle and background execution

The Android launcher in `apps/mobile/android/src/MainActivity.kt` enables
edge-to-edge rendering and calls `setContent { App() }` from `onCreate`; it
does not implement timer persistence or background lifecycle handling. The
current shared shell uses Compose `remember` state only for its demonstration
button. Android Activity lifecycle callbacks and process recreation therefore
remain relevant to any future timer state.

Android provides lifecycle-aware UI handling and several background-work
mechanisms. Deferrable work and ongoing user-visible media playback have
different platform options and constraints; Android's Media3 guidance uses a
service for background media playback, while audio focus must be requested and
loss/change events handled. Android uses separate audio streams; guidance
recommends routing hardware volume keys to the relevant stream and discourages
programmatic changes to shared stream volume because they affect other apps.
These are future implementation considerations, not a selection of whether
the timer or optional music continues in the background. No audio focus, media
service, timer, or volume integration exists in the current launcher.

The iOS SwiftUI `WindowGroup` hosts the Compose `ViewController`; the bridge
does not implement scene-state persistence or timer recovery. UIKit can move a
scene to the background and suspend or disconnect it, and iOS background-task
APIs provide bounded/system-managed work rather than a general promise of
continuous execution. The current `Info.plist` declares no background modes.
No interruption, suspension, termination, relaunch, or recovery behavior was
tested; no timer recovery policy is chosen.

## Audio, system volume, and offline constraints

No playback, audio-session/audio-focus handling, or in-app/system-volume
control is implemented on either platform. Android documentation requires
media apps to manage audio focus and respond when focus is lost; background
media playback also requires platform-specific service configuration. Apple
documents audio-session interruption notifications and provides `MPVolumeView`
for user control of system output volume. Apple's documentation explicitly
notes that volume adjustment and route selection through `MPVolumeView` cannot
be tested in Simulator. None of these behaviors was exercised in this
evaluation, and provider playback is out of scope.

The inspected Android manifest has no `INTERNET` permission, and a source
search found no network client or audio integration in the launcher, shared
shell, features, or core modules. Compose resources are bundled with the app.
This is source/package inspection, not an offline test: the iOS simulator was
not disconnected, and Android did not launch. Offline startup and operation
are therefore unverified. Any future optional provider integration must not
become a dependency for the timer's offline operation.

## Feasibility summary and limitations

The configured Android and iOS targets compile on this host. Android AAB
packaging succeeds, but Android launch/rendering could not be evaluated because
no usable emulator or connected device was available. The iOS starter shell
installed and rendered on one iPhone simulator; physical-device signing and
runtime behavior remain unverified.

These results do not establish a minimum supported OS, platform support,
product validation, release readiness, or parity. Accessibility/input,
backgrounding/recovery, offline operation, audio focus, system-volume behavior,
and provider integrations remain unverified. TV and watch support are not
established by these mobile results.

## Sources

Official platform guidance reviewed on 2026-11-18. Platform policies can
change; recheck them before implementation or a support decision.

| Source | Relevance |
|---|---|
| [Android Activity lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle) | Activity transitions, process lifecycle, and state handling. |
| [Android background tasks](https://developer.android.com/develop/background-work/background-tasks) | Background work categories and execution constraints. |
| [Android audio focus](https://developer.android.com/media/optimize/audio-focus) | Requesting and responding to focus changes across Android versions. |
| [Android audio output](https://developer.android.com/media/platform/output) | Hardware volume controls, audio streams, and limits of app-driven system-volume changes. |
| [Android background media playback](https://developer.android.com/media/media3/session/background-playback) | Media service lifecycle and foreground-service requirements for playback. |
| [Apple: Managing your app's life cycle](https://developer.apple.com/documentation/uikit/managing-your-app-s-life-cycle) | Scene/app state transitions, suspension, and disconnection. |
| [Apple: Background Tasks](https://developer.apple.com/documentation/backgroundtasks) | System-managed background processing tasks. |
| [Apple: Extending your app's background execution time](https://developer.apple.com/documentation/uikit/extending-your-app-s-background-execution-time) | Bounded background execution. |
| [Apple: Handling audio interruptions](https://developer.apple.com/documentation/avfaudio/handling-audio-interruptions) | Audio-session interruption notifications and playback response. |
| [Apple: MPVolumeView](https://developer.apple.com/documentation/mediaplayer/mpvolumeview) | System volume/route UI and the simulator limitation. |

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record issue #35 Android/iOS build, packaging, simulator launch, shell rendering, lifecycle, audio, system-volume, offline, and signing evidence with limitations. |
| 0.2 | Link the findings candidate to PR #50; required review scopes and human acceptance remain pending. |
| 0.3 | Assign the next available document ID, M3-DOC-004, because the concurrent issue #36 provider-feasibility record uses M3-DOC-003; retain PR #50 and pending review. |
