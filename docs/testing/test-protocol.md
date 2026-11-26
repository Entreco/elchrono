# Product test protocol

Settings appearance review: [PR #76](https://github.com/Entreco/elchrono/pull/76).

Desktop keyboard changes: [PR #73](https://github.com/Entreco/elchrono/pull/73).

| Field | Value |
|---|---|
| Document ID | M2-DOC-002 |
| Revision | 3.4 |
| Current change review | Issue #93 Android build/package evidence update; human acceptance and required specialist review pending |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Test and product requirements review: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#25](https://github.com/Entreco/elchrono/issues/25) |
| Review PR | Prior baselines: [PR #27](https://github.com/Entreco/elchrono/pull/27), [PR #31](https://github.com/Entreco/elchrono/pull/31), [PR #32](https://github.com/Entreco/elchrono/pull/32), and [PR #41](https://github.com/Entreco/elchrono/pull/41); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); #67 candidate: [draft PR #68](https://github.com/Entreco/elchrono/pull/68); #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); Settings foreground: [PR #72](https://github.com/Entreco/elchrono/pull/72); issue #82 update: [draft PR #92](https://github.com/Entreco/elchrono/pull/92); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This protocol contains planned test procedures and acceptance criteria for
[M2-DOC-001, revision 2.3, Product requirements](../product/requirements.md).
The exact source revisions and requirement-to-source, risk, design, usability,
and test relationships are identified in the
[requirements traceability matrix](../safety/requirements-traceability-matrix.md).
Requirement statements remain in the requirements register.

These are planned criteria, not completed verification. Run tests only against
reviewed requirements and designs, record the exact build/platform and results,
and report failures, limitations, and unavailable test conditions. Use an
injected controllable time source for timer tests; do not wait real minutes or
hours. No accessibility conformance baseline is selected by this protocol.

## Test cases

| Test ID | Requirement | Procedure | Acceptance criteria |
|---|---|---|---|
| TP-REQ-001 | [REQ-001](../product/requirements.md#requirement-identification) | Exercise each field independently via platform text input when `KeyboardKind` is Physical or Virtual; test focus selection, keyboard navigation, replacement, cursor and deletion, empty input, paste, non-digits, overlength edits, carry, clamping and recreation. When it is None, test on-screen keypad selection of Hours/Minutes, digit entry, Clear/Delete, carry/clamping, focus and remote operation. Test all whole-minute durations and capability changes at compact and expanded sizes. | Every positive duration from 00:01 through 24:00 and idle 00:00 is representable. Empty means zero immediately; field input is limited and clamped as specified, 60 minutes carries an hour, and the total never exceeds 24:00. Single-digit editing and blur padding follow REQ-001. The app keypad is absent for Physical/Virtual and present for None; keypad actions edit only the selected field, remain reachable, and do not expose keyboard-based entry when none is available. Rejected or unchanged edits do not change or reset the setting; actual changes save and reset under REQ-002. Fields and keypad are operable without clipping. Earlier sequential-entry tests do not verify these criteria. Focused JVM implementation tests pass; capability reporting, platform input, accessibility, remote results and usability remain pending until observed. |
| TP-REQ-002 | [REQ-002](../product/requirements.md#requirement-identification) | Use injected time to test running, manually paused, finished, initial positive, zero and interrupted states; open Settings, visit About and close via icon and Back. Exercise actual valid duration changes, unchanged values, appearance-only edits and invalid drafts; advance time while Settings is open. Inject lifecycle stop while Settings retains running intent. | Running pauses without losing time; About return does not resume; closing resumes only retained running intent with remaining time or new full duration after a valid duration change. Changed valid duration immediately resets the engine paused; appearance-only, invalid and unchanged edits do not. Manually paused stays paused; finished stays zero unless duration changes, which prepares paused full duration requiring Resume. Initial positive configuration does not auto-start and offers Resume; zero is idle with no enabled action. Stop during Settings revokes resume and shows interruption. Settings alone never clears interruption. |
| TP-REQ-003 | [REQ-003](../product/requirements.md#requirement-identification) | For all four shapes and details 0-12 inspect running, paused, finished, idle and interrupted states, progress, accessible names, stale warning, compact/expanded layouts, keyboard, touch and screen-reader output. Trigger lifecycle interruption including while Settings holds running intent. | States and progress are distinguishable without color alone; no clock face or time of day is shown. Detail 0 has no visible Timer text but has accessible icon actions and distinct idle/interrupted cues. Higher details show interruption warning and remaining time. No automatic interruption recovery; explicit Restart clears warning and starts full duration. All shape geometry, Clock labels and Fan bounds remain correct and unclipped. Human review of comprehension and platform delivery remains required. |
| TP-REQ-004 | [REQ-004](../product/requirements.md#requirement-identification) | Inspect Main, Settings, and About against their required v1 content. At compact and expanded widths confirm Settings overlays Timer; resize across the breakpoint while About is open. Verify the official logo's scaling and accessible name, actual version/build metadata, approved support/privacy/licence information and link destinations, intended-use/limitations copy, navigation back to Settings and Timer, keyboard operation, and screen-reader labels. | Main includes the selected timer visualization. Settings overlays Timer on compact and expanded windows; duration and theme controls are present, with no v1 music or volume controls. About contains all REQ-004 content from authoritative sources; the logo preserves its aspect ratio and is accessible, links work, navigation is correct, and content is readable and not clipped or obscured. A missing source or required item is explicitly recorded as a gap and makes this test blocked, not passed. |
| TP-REQ-005 | [REQ-005](../product/requirements.md#requirement-identification) | Open Settings without a gate as a patient and change/save duration or theme. | Settings is not protected by a caregiver gate in v1 and permits the access specified by the product decision. The test verifies this behavior; it does not establish that the resulting risk is acceptable or controlled. |
| TP-REQ-006 | [REQ-006](../product/requirements.md#requirement-identification) | Select Clock, Battery, Fan, and Ring in Settings and inspect each timer presentation. | Exactly the four owner-approved v1 themes are available, each can be selected and becomes the selected timer theme, and each preserves the shared remaining-time mapping. Do not assert user preference without usability evidence. |
| TP-REQ-008 | [REQ-008](../product/requirements.md#requirement-identification) | For v1, run the timer with network unavailable. Defer audio-failure and playback checks until a v2 music integration is approved and implemented. | Timer operation and remaining-time presentation do not depend on network connectivity. No v1 music integration or playback-failure behavior is claimed. The v2 audio criteria require review before that scope begins. |
| TP-REQ-009 | [REQ-009](../product/requirements.md#requirement-identification) | Review application screens, About content, and external product claims against intended-use exclusions. | No content presents the app as a clock, guarantees a deadline, makes medical claims, or presents it for safety-critical timing. Conflicts in intended purpose, functionality, claims, or markets are escalated before affected work continues. |
| TP-REQ-010 | [REQ-010](../product/requirements.md#requirement-identification) | With controllable time, test all 1,440 positive durations just before, at and after zero; delay a tick beyond multiple durations, poll repeatedly, pause at boundary and restart. | Zero is clamped, finished is distinct from idle, ticking stops and completion is reported exactly once; no auto-restart or negative remainder occurs. Restart starts the full configured duration. No result is implied by this planned case. |
| TP-REQ-011 | [REQ-011](../product/requirements.md#requirement-identification) | On each configured target, operate Main, Settings, and About using its supported remote/controller focus model, including every Timer action, Settings control, and keypad control; verify visible focus, predictable navigation, readability at ordinary viewing distance, and non-colour-only cues. For transient controls, leave the Timer and Settings actions idle for 10 seconds, then use remote/controller input to reveal them; verify the revealing input does not activate a control and subsequent input can focus and select. Attempt target packaging and lifecycle checks. | Android TV and Apple TV controls are operable without touch, focus is visible and predictable, and content remains readable and complete at the agreed viewing distance. Timer action and Settings controls fade out after 10 seconds and reappear on remote/controller interaction; the reveal-only input does not also activate a control. Record target configuration and verification before claiming support; an unconfigured target or unavailable device makes the test blocked, not passed. |
| TP-REQ-012 | [REQ-012](../product/requirements.md#requirement-identification) | Enumerate detail levels 0-12 in both directions and select colors/fonts across shape changes and temporary hidden controls. Inspect Settings text, input font, accents and Detail track at zero and nonzero; exercise pointer, keyboard and assistive-technology value changes. For each foreground preset inspect focused fields. Inspect matching and low-contrast foreground/background combinations. For each text-color preset inspect the Settings, close and About-back icons and selected swatches, including a swatch matching the text color. | Every detail level and color/font choice is reachable, shape changes do not erase selections, and no contrast restriction or warning is introduced. Applicable Settings colors and font update immediately. App-bar icons and selected swatch checks use the selected text color; the check's backing has at least 4.5:1 contrast against the check for each opaque text-color preset, and the swatch remains recognizable and accessible. At zero the Detail track has no ticks or end dot but retains the thumb, label, value and discrete range; above zero its marks return. Independent keyboard focus remains available. Record visibility limitations for low-contrast combinations and obtain human accessibility and risk review; do not treat unrestricted colors as proof of contrast. |
| TP-REQ-013 | [REQ-013](../product/requirements.md#requirement-identification) | Enumerate all shapes, states and details 0-12; test action names, roles, input activation, focus, clipping, and overlay/exit-animation isolation at compact and expanded sizes. Inspect zero and finished separately. | At detail 0 no Timer text, boundaries or Clock labels appear; action icons have localized accessible names, running shows Pause, paused shows Resume, and finished/interrupted show Restart. At 1-12 Restart is visibly labeled; stale warning and remaining text appear as applicable. Hidden Timer controls cannot be focused or activated under overlays. Clock has N evenly spaced duration labels and no boundaries; other shapes have N equal segments, including one at detail 1. Fan stays centered and unclipped. Idle zero has no enabled action or completion semantics. Human review remains pending. |
| TP-REQ-014 | [REQ-014](../product/requirements.md#requirement-identification) | Test full-duration preview, actual valid duration changes, unchanged values, appearance edits, invalid drafts, persistence, reopen and read/save failures across paused, running-intent, finished and interrupted states. | Preview updates immediately; only an actual valid duration change resets the engine paused to full. Preview does not advance it or clear interruption. Invalid drafts retain last valid preview and show an error; invalid/stale storage defaults safely with surfaced error; failures are not reported as saves. Reopen restores settings, not countdown continuity. Overlay need not expose a sharp preview. |
| TP-REQ-015 | [REQ-015](../product/requirements.md#requirement-identification) | Measure Timer and panel bounds before, during and after both directions, including interruption, reversal and resizing. Inspect Settings and About at compact and expanded widths, and exercise back, predictive-back cancellation, labels, focus, semantics, scrolling, reduced motion and clipping. Check glass and native blur on supported renderers and fallbacks, including Android below API 31 and API 32. | One full-window Timer remains stationary. Both panels enter from and exit to the left; About overlays Settings. Compact panels fill the width and expanded panels do not exceed 440 dp. Controls remain sharp, labeled and reachable; hidden layers do not leak semantics or focus. Record renderer-specific blur, glass and fallbacks rather than inferring them from compilation. |
| TP-REQ-016 | [REQ-016](../product/requirements.md#requirement-identification) | On every configured target, record cold and warm starts, first run, full process restart, and recreation with default, saved dark and saved light backgrounds. Record first visible system-owned and app-owned frames; corrupt or deny storage and observe feedback. Confirm all settings restore and the previous countdown does not resume or start. | App-owned startup surfaces match the validated background as early as supported; configurable system-owned surfaces use the dark default. A static system-to-selected-color transition is allowed and recorded. First run or invalid/unavailable storage uses the default; storage errors are visible. Restart does not recover or auto-start a countdown. Unavailable targets or missing recordings are blocked, not passed. |

### Issue #95 startup candidate evidence

On the local macOS host with Kotlin Toolchain 0.13.0 and Xcode 27.2:

- `./kotlin test -p jvm -m persistence` passed 13 tests, including a new
  Java Preferences instance restoring a complete record, an access-denial
  failure, and backing-store read/write
  failure. `./kotlin test -p iosSimulatorArm64 -m persistence` passed the
  shared cases and two isolated NSUserDefaults cases; linker cache warnings
  were emitted. These are not app process-restart tests.
- `./kotlin test -p iosSimulatorArm64 -m common --include-classes
  'nl.entreco.elchrono.AppViewModelTest'` passed, including a fresh-engine
  test showing that saved settings restore while a previous running countdown
  does not. This exercises an in-memory storage stub, not OS process restart.
- `./kotlin build -m browser -m desktop -m android -m androidtv` and
  `./kotlin build -m ios -p iosSimulatorArm64` succeeded. An iPhone 18 Pro
  iOS 27.0 simulator launch rendered the default timer; a settled screenshot
  sampled `#151517` through the lower edge after the shared Scaffold fix.
  The first launch attempt and one destination lookup failed; later retries
  succeeded. A later launch with an NSUserDefaults command-line argument for
  valid light settings rendered `#F7F7F2` through the screen edges; an invalid
  argument rendered `#151517`. These arguments simulate stored records and do
  not verify durable saves, first frames, or physical-device behavior.
  Desktop launched a window on macOS. A headless Chrome 158 screenshot captured
  the dark browser loading surface, but not the rendered timer or a saved
  background; startup-frame behavior remains unverified.
- `./kotlin check -m persistence -m common` failed because pre-existing common
  JVM tests refer to missing `AppBackgroundEffect` and `onAppBackgrounded`
  APIs. The same references and absent definitions are present on the base
  branch. `./kotlin check -m persistence` completed successfully.
- No Android or Android TV device was connected. Cold and warm launch
  recordings, saved-color reload, failed-storage UI behavior, lifecycle
  recreation, non-recovery of a prior countdown, accessibility and human risk
  review remain open on relevant targets. TP-REQ-014/016 are not passed.

For TP-REQ-003 and TP-REQ-013, inspect the centered remaining-time glass
surface at detail 1-12 across all four themes, one-minute and 24-hour values,
compact and expanded windows, selected fonts/colors, and larger system text.
Confirm the value and panel remain centered and unclipped, the visualization,
markers and state cues remain understandable, and there is no panel at detail
0 or on the idle prompt. Check the changing backdrop, capture-error notice and
tinted fallback on available renderers. Record low-contrast combinations,
device limitations and any overlap as findings; human accessibility, product,
usability and risk review remain required.

For TP-REQ-002/003/014, also change the duration to 00:00 while running
intent is held and while interrupted. Zero must not start or enable an
action, and present idle without an interruption warning while retaining the
interruption internally. Positive reconfiguration restores the warning and
requires explicit Restart to clear interruption and start the full duration. Test repeated Settings
navigation and persistence errors without treating a failed save as success.

For TP-REQ-012, verify that each of the three color pickers exposes all 16
named presets in no more than eight columns, with two rows when 384 dp of
picker width is available and unclipped 48 dp targets with responsive wrapping
at narrower widths. Select and restore each font, including Rounded and Serif,
and compare their actual rendered text with Default on each target. Verify
glyph fallback and screen-reader labels. For Ring, select all integer
thicknesses 1-100 using available input methods; the value remains visible
and no dense ticks or endpoint dots appear. Review the expanded choices and
remaining unrestricted low-contrast combinations with representative users.

### Configuration recreation continuity

For TP-REQ-002/003/010/014/015, distinguish configuration recreation from
genuine backgrounding. Existing references to lifecycle stop in those cases
mean genuine background stop, not an Android configuration-change stop.

1. With an injected clock, start five minutes, advance two minutes, suspend UI
   refreshes, advance thirty seconds, then refresh. Require 2:30 remaining,
   Running, no interruption warning, and no reset or Restart requirement.
   Repeat across the finish boundary: clamp to zero and report completion once.
1. On a selected authorized Android emulator/device, rotate repeatedly between
   portrait and landscape while running; also recreate the Activity without
   killing the process. Verify the retained ViewModel/session identity and
   remaining-time trajectory. Repeat paused, finished, zero, interrupted, and
   immediately before completion. A JVM-only lifecycle simulation is insufficient.
1. Repeat with Settings and About open, after appearance and duration changes.
   Preserve the screen, applicable scroll position, duration draft and selections.
   Closing Settings resumes only retained running intent; recreation alone must
   not revoke it. Check portrait/short landscape, large text, focus, clipping,
   panel animation, and resize across compact/expanded widths.
1. Separately test Home, lock, background stop while Settings holds intent, and
   multi-window transitions. Require the existing genuine-background interruption
   and explicit-Restart policy; resizing alone must not trigger it. Process-death
   and reboot recovery are not added by this change.

## Execution record

### Independent duration-fields candidate

| Command | Result and scope |
|---|---|
| `./kotlin test -p jvm -m settings` | Passed: 27 tests. The Settings module's field-entry tests cover every allowed duration, ASCII/length validation, empty values, clamping, minute carry, the 24-hour boundary, focus selection, independent desktop field editing, blur padding, no app keypad, and saveable input restoration. This does not verify physical keyboard/IME behavior on each platform, screen-reader use, real TV remote operation, usability, or risk-control effectiveness. |
| `./kotlin check -m settings` | Passed for the Settings module, including its JVM checks. |
| `./kotlin build -m browser` | Passed for the browser Wasm app and shared dependencies; no browser interaction or accessibility verification is implied. |
| `./kotlin build -m android` | Passed for the Android target; no device/IME or accessibility verification is implied. |
| `./kotlin build -m ios` | Passed for iOS ARM64 and iOS Simulator ARM64 targets; no runtime keyboard or accessibility verification is implied. |
| `./kotlin build -m desktop` | Passed for the desktop JVM app; interactive field behavior is covered by the Settings Desktop Compose tests above. |
| `./kotlin build -m common` | Blocked at common JVM test compilation by unresolved `AppBackgroundEffect` references in `AppLifecycleTest` and `onAppBackgrounded` in `TimerControlsUiTest`. Production sources compiled, and the affected browser, Android, iOS, desktop, and Settings targets built separately. |

### TV keyboard selection correction

The duration fields select all text synchronously on focus and retain that
selection through selection-only IME startup updates until the first text edit.
Subsequent cursor movement and deletion remain available. The Settings panel no
longer disables focus for its descendants.

| Command | Result and scope |
|---|---|
| `./kotlin test -p jvm -m settings` | Passed: 30 tests, including full-panel focus, pointer activation, IME cursor initialization, replacement, deletion, and cursor movement after the first edit. |
| `./kotlin check -m settings` | Passed. |
| `./kotlin run -m android --device-id emulator-5554` | Built, installed, and launched the Android app on the authorized Television (4K) emulator (`sdk_google_atv64_arm64`). This is the mobile Android launcher exercised on a TV emulator, not validation of a dedicated TV release. |
| `./kotlin test -p jvm -m common --include-classes='*TvControlsUiTest'` | Blocked by the existing common test-compilation references to `AppBackgroundEffect` and `onAppBackgrounded`; the class filter does not bypass compilation of other test sources. |

Using ADB remote-key events on that emulator, the on-screen numeric keyboard
replaced selected Hours text with `1`, then deleted it to an empty field while
remaining visible. Minutes accepted consecutive digits as `11`; Done dismissed
the keyboard intentionally. IME inspection reported selection `0..2` before the
first edit and `mInputShown=true` after digit entry and deletion. This bounded
check does not verify physical remotes, other IMEs/devices, Apple TV,
screen readers, all supported layouts, or representative-user usability.
Human review and broader platform qualification remain pending.

The subsequent remote-only retest found that the shell moved focus on both
directional key-down and key-up. The handler now moves focus only on key-down;
the release cannot skip a second control or move away from a newly focused
duration field. A regression in `TvControlsUiTest` explicitly separates press
and release; execution remains blocked by the common compilation errors above.
After rebuilding with the same Android run command, ADB remote events alone
(no field taps) opened Settings, moved Down once to Hours with `02` selected,
and entered `1` using Center while the keyboard stayed open. Remote navigation
to backspace cleared the field without dismissal; Done then hid the keyboard.
The earlier tap-assisted observations alone were insufficient to cover this
reported remote-only path. Physical-controller and other-IME checks remain
pending.

The Settings/navigation and timer-action focus outlines now use `CircleShape`
instead of the small rounded-rectangle shape, matching round icon buttons and
the capsule-shaped labelled Restart button. The shared Settings back icon uses
the same outline. `./kotlin test -p jvm -m timer -m design` passed, and
`./kotlin run -m android --device-id emulator-5554` built and launched.
Screenshots on that emulator confirmed circular, fully visible focus outlines
around Settings and Play after moving focus with Down. This is a bounded visual
check, not accessibility or cross-platform approval.

### Configuration recreation refactor candidate

Observed in the implementation worktree:

| Command | Result and scope |
|---|---|
| `./kotlin test -p jvm -m settings -m counter -m timer` | Passed after the refactor, including save/restore of the duration buffer without republishing a setting, Backspace/keyboard availability, scroll restoration, selected short-landscape bounds, existing appearance interaction, monotonic boundaries and timer presentation. |
| `./kotlin test -p android -m common` | Passed: 19 tests on the toolchain's local Android JVM backend, including isolated engine ownership and the retained-session/missed-refresh cases. This uses a mockable Android platform JAR, not an emulator or Activity recreation. |
| `./kotlin build -m android -m androidtv` | Passed for both launchers after wiring the live configuration-change check. No launch or rotation is implied. |
| `./kotlin build -m browser` | Passed. |
| `./kotlin build -p jvm -m desktop` | Passed for production desktop and shared JVM dependencies, without selecting the blocked common JVM test module. |
| `./kotlin test -p jvm -m common -m counter -m settings -m timer` | Blocked in common test compilation by six pre-existing `tvInteractionMode` arguments in `TvControlsUiTest` that do not match `AppContent`. The initial Settings Backspace failure was resolved by enabling focus only on editable duration fields. |
| `./kotlin build -p jvm` | Failed on the same pre-existing common-test signature mismatch; production sources compiled. |
| `./kotlin check -m common -m counter -m settings -m timer` | Failed on the same pre-existing common JVM test compilation; partial platform results are not a passing overall check. |

`AppLifecycleTest` adds observer-level regressions for configuration-stop
exclusion, a subsequent genuine stop, and observer disposal. Those JVM tests
remain unexecuted because of the common compilation blocker. No Android device
was connected (`adb devices -l` returned an empty list). The current dependencies
contain neither an Activity recreation runner nor Robolectric; no new runner,
SDK package, emulator, or dependency was installed. The device procedure above,
ViewModel retention under actual recreation, screenshots/no-clipping matrix,
screen-reader checks, and product/risk/UX review remain pending. No new
process-termination/reboot recovery or platform support is claimed.

### One-shot timer and manual controls candidate

For the current implementation worktree, the operator reports that
`./kotlin test -p jvm -m common -m counter -m timer -m settings -m localization`
passed after the zero-state correction. The reported `common` run contains
31 tests, including the five `TimerControlsUiTest` cases. The source and test
scope below was inspected; the supplied external log was not independently
inspected, so the result remains operator-reported evidence rather than an
independently observed execution. No exact reviewed head SHA is recorded here.

The reported passing JVM suite exercises:

- `TimerEngineTest` with an injected clock: all 1,440 positive whole-minute
  durations just before and at zero, later polls, one completion event through
  `completedCycles`, stopped `FINISHED` state and full-duration Restart; also
  zero/unconfigured actions, long pauses, a late update and pause at the
  boundary. This does not verify physical lifecycle event delivery.
- `AppViewModelTest`: initial positive configuration waits for an action;
  Settings pauses and resumes retained time, changed valid duration resets
  immediately while paused, appearance and unchanged duration do not reset,
  manual pause and finish are preserved, changed duration after finish stays
  paused, About return does not resume, and interruption, lifecycle stop while
  Settings holds intent, and storage failure paths are exercised.
- `TimerPresentationTest` maps finished, running, paused, interrupted and idle
  states; zero/unconfigured maps to idle even when the interruption flag is
  retained. `TimerControlsUiTest` exercises Pause/Resume/Restart for detail
  0–12, icon-only Restart at detail 0, explicit interrupted Restart, no action
  at zero after interruption, Tab reachability and Enter activation, expanded
  overlay pointer/semantics isolation through exit animation, and Restart
  bounds at 320 × 640 dp and 2× font scale. After positive reconfiguration,
  the interruption flag is retained until explicit Restart.

The approved zero-duration priority presents idle without an action or warning
while retaining interruption internally. Positive reconfiguration restores the
warning and requires explicit Restart. The earlier criterion requiring the
warning at zero is superseded; human accessibility, usability and risk review
of this state priority remains pending.

The parent agent observed the final combined rerun complete with exit 0:

- `./kotlin check -m common -m counter -m timer -m settings -m localization` — passed.
- `./kotlin build -m common` — passed, with Kotlin/Native cached static-library
  warnings that `.a` files could not be opened.
- `./kotlin build -m browser` — passed.

The external logs were not inspected by this documentation agent. Builds and checks
do not establish iOS runtime/device or TV behavior.
JVM test coverage is bounded: complete visual/state/theme coverage on real
devices, actual screen readers, target lifecycle delivery, TV input, and
representative-user and qualified human product/accessibility/usability/risk
review remain pending. A test pass does not establish approval, control
effectiveness or residual-risk acceptance.

The records below describe earlier implementations and observations, including
legacy save-to-start and automatic reset. They do not verify the superseding
one-shot, manual-control, Settings-intent or interruption requirements above.
Full execution of the revised protocol cases and competent human reviews
remain pending despite the bounded reported JVM evidence above.

### Issue #81 Android TV controls candidate

The candidate retains the shared Material controls and exercises focus with
simulated keys in JVM Compose tests. These tests do not represent the Android TV
renderer or a physical remote. Android TV installation, launch, remote/D-pad
operation, focus visibility, lifecycle, screen-reader behavior, viewing-distance
readability, and packaging remain unverified. TP-REQ-011 remains blocked, not
passed; no Android TV support claim is made.

### Issue #82 TV packaging and support evidence

On 2026-11-25, the local environment was macOS 26.6.2 (arm64), Kotlin Toolchain
0.13.0, Android command-line tools 20.0, ADB 37.0.0-14910828, and Android
emulator 37.1.11.0. `./kotlin show modules` listed the `androidtv` module.
`./kotlin build -m androidtv` completed successfully and generated
`build/tasks/_androidtv_buildAndroidDebug/gradle-project-debug.apk`. The first
build attempt exposed missing Compose focus/key imports and focus-manager
access in Settings duration fields; these were corrected before the successful
rerun.

`./kotlin test -p jvm -m settings` passed all 31 tests, including a new
directional-key test that moves between duration fields only at their outer
edges. This JVM result does not verify Android TV rendering or remote input.
`emulator -list-avds` listed `Television_4K`, but `avdmanager list avd`
reported that the profile could not load because its Android TV arm64-v8a
system image is missing. `adb devices -l` listed no connected devices. The APK
was not installed or launched; Main, Settings, About, transient controls,
remote focus, lifecycle, viewing-distance readability, and accessibility were
not exercised on a TV target.

The Apple TV target remains unconfigured: the current Compose UI dependency
graph has no tvOS variants, as recorded under [issue #80](#issue-80-apple-tv-target-feasibility).
No Apple TV package, launch, or remote-focus behavior was verified. TP-REQ-011
therefore remains blocked, not passed, for TV support evidence. Accessibility,
usability, risk, and other required human reviews remain pending; no TV support
claim is made.

### Issue #80 Apple TV target feasibility

On 2026-11-25, local tooling showed Xcode 27.2 (build 27B52), tvOS and
tvOS Simulator SDK 27.2, tvOS 27.2 simulator runtime, and two Apple TV 4K
(4th generation) simulator devices. This establishes local SDK/runtime
availability only.

`./kotlin show modules` listed no Apple TV/tvOS launcher. The existing Compose
modules declare iOS but not tvOS. `./kotlin build -p tvosSimulatorArm64 -m common`
reported `WARN Nothing to build` and `Build successful`; because no module
declares that target, this is not a tvOS compilation result.

For a bounded dependency probe, `tvosSimulatorArm64` was temporarily added to
`core/design/module.yaml`, then
`./kotlin build -p tvosSimulatorArm64 -m design` failed dependency resolution:
Compose animation 1.12.1, foundation 1.12.1, material3 1.12.0-alpha03, UI
1.12.1, and components-resources 1.12.1 do not publish a tvOS simulator
variant. The temporary target edit was reverted. No tvOS launcher was built,
packaged, or launched; no remote focus or transient-control behavior was
implemented or verified. The tvOS simulator runtime/device is available, but
the current shared Compose UI dependency graph blocks this target. Do not
replace the framework or create a separate native UI without a separately
reviewed architecture decision. TP-REQ-011 and issue #80 remain blocked, not
passed; Apple TV support is not claimed.


### Settings palette, fonts and Ring slider candidate

On the current working tree, `./kotlin test -p jvm -m settings -m design -m persistence`
passed. The focused JVM tests exercise all color presets in all three pickers
and their persistence, 48 dp swatches at 288, 384 and 600 dp picker widths,
font-family selection and rendered differences on this host, and Ring slider
values 1-100 and inactive-track pixels. Earlier in the change,
`./kotlin test -p jvm -m design -m settings -m persistence -m common` passed.
`./kotlin build -m common -m browser -m android -m ios` completed, with
Kotlin/Native cache warnings; compilation does not establish runtime support.

The subsequent `./kotlin check -m common -m design -m settings -m persistence -m localization`
reported Skiko WebAssembly instantiation errors and was stopped while native
test compilation was still running, so the complete check has no passing
result. Browser, mobile, screen-reader, physical-input and representative
human usability and risk review remain pending; JVM rendering is not evidence
of cross-platform font fallback, accessibility or full product acceptance.

### Remaining-time glass candidate

On the current working tree, `./kotlin test -p jvm -m common -m timer`
passed. The focused JVM UI tests cover centered panel/text bounds, text-layout
line bounds, the Settings button, all four themes, one-minute and 24-hour
values, 320 x 640 dp and 1200 x 720 dp, selected fonts, 1x and 2x font scale,
detail 0-12, idle, and a sampled change in the captured Battery backdrop.
They do not establish full product or accessibility verification.
`./kotlin build -m browser` also passed.

Headless Chrome on macOS with ANGLE/SwiftShader rendered the default Clock at
500 x 900 and 1200 x 800 pixels. The rounded surface and value appeared at the
Timer center; the existing Clock center mark remained visible through and
around the translucent surface. Review whether the combination is readable
and understandable. Other themes and settings, backdrop error injection,
platform fallback, screen readers, physical devices, and human product,
accessibility, usability and risk review remain pending.

### Stationary Timer and sliding glass candidate

On the current working tree, `./kotlin test -p jvm -m common` passed after
the overlay change. The JVM UI tests cover Timer bounds and semantic-node
identity, Settings and About slide direction, compact width, resizing while
About is open, a rapid Settings reopen, navigation, hidden semantics, and a
bounded keyboard-focus check. Earlier in this change,
`./kotlin test -p jvm -m common -m settings -m timer`,
`./kotlin build -m desktop -m ios -m android -m browser`, and
`./kotlin check -m common -m settings -m timer -m localization` passed.
`./kotlin build -m common` also completed after the latest shell changes,
with Kotlin/Native cache warnings; compilation does not establish runtime
behavior.

Headless Chrome 158 with ANGLE/SwiftShader rendered the candidate at
1200 x 800 and 500 x 900 pixels. The compact Settings panel filled the
window, scrolled to About, and returned from About with its scroll position
retained. The About backdrop was blurred separately to avoid readable Settings
controls showing through it. These observations precede the final
semantics-only adjustment and are not a protocol pass. Physical input,
screen readers, reduced motion, predictive-back gestures, Android fallback,
iOS/desktop runtime rendering, contrast across selections, and human
product/accessibility/usability/risk review remain pending.

### Earlier native-blur overlay candidate

Before the stationary Timer redesign, on the `feat/66-responsive-timer-screen` working tree,
`./kotlin test -p jvm -m common -m settings` passed the Settings interaction
and compact/expanded overlay semantics tests. A subsequent
`./kotlin test -p jvm -m common` passed with navigation from Settings to About
and back. `./kotlin build -m browser -m android` passed; builds alone do not
establish runtime behavior.

Headless Chrome 158 using ANGLE/SwiftShader rendered the browser candidate at
500 x 1000 and 1440 x 1000 pixels. In those two configurations, the timer
appeared blurred behind sharp Settings controls, scrolling exposed the Detail
control and About link, and About navigation returned to Settings. This is
bounded observational evidence, not a full protocol pass. Android fallback,
other renderers, settings combinations, assistive technologies, physical
input, and human product/accessibility/usability/risk review remain pending.

### Desktop keyboard candidate

- `./kotlin test -p jvm -m settings -m counter -m common` passed on the
  current macOS host. Desktop Compose tests exercised injected text edits in
  both fields, a simulated Backspace, keypad absence, and input-mode
  recomposition; these are not physical-keyboard or full-layout evidence.
- `./kotlin build -m desktop -m browser` and
  `./kotlin build -m android -m androidtv -m ios` passed. A desktop window
  was observed through the window manager after
  `./kotlin run -m desktop --no-compose-hot-reload`; its contents and
  interactions were not inspected.
- Simulated digit key-down/up events did not enter text in the Compose test
  harness. The pinned Compose Desktop text field requires AWT `KEY_TYPED`
  events, which the harness's key-down/up injection does not supply. macOS UI automation was unavailable
  because accessibility access was disabled. Real physical digit input,
  keyboard navigation, selection, clipping, assistive technology, and human
  review therefore remain pending; these observations do not pass TP-REQ-001.

### Issue #66 refinement acceptance extension

Apply these additional criteria to TP-REQ-003/012/013/014/015:

- With identical viewport, duration, font, and state, switching details
  0 -> 1 -> 12 -> 0 must leave every shape's center and outer bounds unchanged.
  Overlay text, labels, and cues must remain reachable without obstructing
  required content; check short landscape windows and enlarged fonts.
- Select every background preset and navigate Timer/Settings/About at compact
  and expanded sizes and across the pane breakpoint. App-owned edges, app bar,
  and gutters must match the selected background; Settings must be visibly
  translucent with readable controls and focus. No horizontal overflow.
- Each visible state cue must remain inside the Timer pane at bottom-right,
  with no click action and preserved state/stale-progress semantics.
- Edit durations 0, 1, 9, 10, 60, 120, and 1440 minutes while Settings is open.
  The underlying presentation snapshot must update before closing, without a
  sharp or unobstructed visual preview. The paused engine must not
  advance/reset; invalid drafts must retain the last valid presentation and
  show an error.
- Battery must retain an outline and terminal at full, partial, and idle fill.
  At fixed width, increasing window height must not exceed the 126-logical-pixel
  preferred body height. Its inset fill/separators must stay inside the body.
- Ring thickness must cover integer values 1-100% of outer radius, default 24%.
  At 1, 24, 50, and 100 test 0%, 25%, 50%, and 100% remaining. Outer bounds
  must stay fixed; the solid endpoint must have no hole or overlapping arc.
  Switching away/back and reopen must retain thickness; seven-field legacy
  storage must retain all prior selections and acquire the default. Invalid
  thickness or storage failures must surface an error, not a successful save.

Record bounded observations separately from a full protocol pass.

Issue #65 focused verification:
- `./kotlin test -p jvm -m common -m counter -m design -m settings -m localization -m persistence` passed in the feature worktree.
- `./kotlin build -m browser` passed.
- `./kotlin run -m browser --port 8082 --no-open-browser` responded HTTP 200.

This evidence verifies compilation and JVM/persistence behavior. It does not
verify rendered browser layout, assistive-technology interaction, all browser
storage failures, supported browser matrices, or human product/accessibility/
risk review.

The focused JVM test command
`./kotlin test -p jvm -m common -m counter -m design -m settings -m localization`
passed, including duration/input-policy tests and app-state tests for
Settings-open pause/save-to-start, lifecycle-stop interruption, repeated stop
events, no automatic resume, and completion. The following target builds also
passed: `./kotlin build -p jvm`, `./kotlin build -m browser`,
`./kotlin build -m android`, and `./kotlin build -p iosSimulatorArm64`.
`./kotlin check -m settings tests` failed in its Kotlin/Wasm settings test
runner: Skiko's WebAssembly module reported a truncated data section during
instantiation. The JVM settings tests pass; the Wasm test failure remains
unresolved and must not be reported as a pass.
These checks do not verify rendered browser layout, animation, accessibility,
offline operation, actual lifecycle event delivery on configured targets, or
TV targets.
The browser server started with
`./kotlin run -m browser --port 8081 --no-open-browser` and returned HTTP 200;
port 8080 was occupied and left untouched. A prototype-only layout check for
F01 was run in Chrome 157.0.8103.79 at 500 × 1000 px and 1440 × 1200 px; see
M2-DOC-006. It does not verify the application or establish complete coverage
because supported layout configurations are undefined. Record actual
product-test evidence in the requirements traceability matrix, including
protocol revision, test/build revision, platform, result, reviewer, evidence
link, and limitations. If the supported layout configurations needed to
evaluate content visibility are not defined or available, record the affected
test as blocked rather than passed. Keep failures and blocked tests visible;
do not report planned criteria as passed. The clipping reported in M2-DOC-006
is resolved in the prototype for the two checked layouts; the complete product
no-clipping criterion remains unverified. Music/audio tests are deferred until
v2, not passed or waived.
The browser server started with
`./kotlin run -m browser --port 8081 --no-open-browser` and returned HTTP 200;
port 8080 was occupied and left untouched. These checks do not verify rendered
browser layout, animation, accessibility, offline operation, actual lifecycle
event delivery on configured targets, or TV targets. A
prototype-only layout check for F01 was run in Chrome 157.0.8103.79 at 500 ×
1000 px and 1440 × 1200 px; see M2-DOC-006. It does not verify the application
or establish complete coverage because supported layout configurations are
undefined. Record actual product-test evidence in the requirements traceability matrix, including protocol
revision, test/build revision, platform, result, reviewer, evidence link, and
limitations. If the supported layout configurations needed to evaluate content
visibility are not defined or available, record the affected test as blocked
rather than passed. Keep failures and blocked tests visible; do not report
planned criteria as passed. The clipping reported in M2-DOC-006 is resolved in the prototype for the two
checked layouts; the complete product no-clipping criterion remains unverified.
The music/audio tests are deferred until
v2, not passed or waived.

### Issue #66 bounded browser visual check

Google Chrome 158.0.8127.37 rendered the local browser app in headless mode
with ANGLE/SwiftShader and DevTools viewport emulation at 320 × 640 and
1440 × 1000 CSS pixels (device scale factor 1). Running Clock and Fan
presentations were observed at both sizes with a two-hour configured duration
and detail 6. Clock displayed six duration labels around its continuous disk,
with no internal boundaries; the Fan semicircle and its visible dividers fit
within the available visualization area. The idle Timer also rendered at both
sizes; those checks reported `innerWidth`/`body.scrollWidth` of 320/320 and
1440/1440, respectively. The
running Clock/Fan screenshots showed no visible clipping; per-shape scroll
metrics were not separately recorded.

This is a bounded visual observation, not a TP-REQ-003 pass. The Chrome
accessibility tree exposed the Timer node as generic with the name “Remaining
waiting time”; visible Clock marker announcements, screen-reader behavior, and
progress-role/value announcement remain unverified. The browser captures used
detail 6 only; they do not establish detail 2's rendered example or cover every
theme/state combination. Paused/interrupted/zero-duration states, reduced
motion, offline operation, and human design/accessibility/usability/risk reviews
remain pending. Temporary screenshots are session-local and are not linked as
controlled review evidence.

### Issue #66 appearance-refinement execution

The implementation worktree on `feat/66-responsive-timer-screen` was checked
with these commands:

- `./kotlin test -p jvm -m common -m counter -m design -m settings -m localization -m timer -m persistence`
  passed: 67 JVM tests, including preview isolation, all-detail Clock label
  envelopes, Battery/Ring geometry, all thickness values, and legacy migration.
- `./kotlin test -p jvm -m design -m settings -m common` passed again after
  the final surface/error-readability edits: 26 JVM tests.
- `./kotlin build -m browser` passed after the final UI changes.
- `./kotlin build -m android` passed after the final UI changes; compilation
  only, not device acceptance, TV support, or browser verification.
- `./kotlin check -m common -m timer -m persistence -m settings -m design -m localization -m counter`
  failed in the Wasm persistence test runner: Skiko WebAssembly instantiation
  reported a code section extending beyond the supplied module. This is the
  previously recorded runner limitation, not a passing check. The available
  check is `tests`; it includes platforms beyond the passing scoped JVM run.
- `git diff --check` passed.

Chrome 158.0.8127.37, headless ANGLE/SwiftShader, device scale factor 1, and
DevTools viewport emulation were used against the built browser app:

- Clock/Battery/Fan/Ring were rendered running at detail 0, 1, and 12 at
  320x640, 640x320, 1440x1000, 839x700, and 840x700. Foreground-pixel bounds
  stayed identical except for Fan's topmost foreground row at detail 12 in
  the two breakpoint-size views (one pixel, where a divider crosses the top).
  The underlying shape geometry is independent of detail.
  Viewport/body widths matched, and sampled app corners used the configured
  background. Visible shapes stayed inside the viewport.
- On the expanded Clock preview, the actual detail slider was changed live
  through 0-12 and back to 0. Foreground bounds remained exactly identical.
- All six background presets were checked on compact and expanded Settings
  and About. App corners matched each selected color and there was no horizontal
  overflow. Settings and About panels used translucent tints; controls and error
  surfaces retain their own design treatments for readability.
- Actual keypad edits changed the two-hour Clock preview to five minutes before
  closing Settings. Its accessibility tree included `0 hr 5 min`, `seconds`,
  and the detail-2 intermediate value `150`.
- Actual Ring selection and slider input produced a `100%` readout and saved
  value; switching to Clock hid the thickness control, switching back retained
  it, and page reload restored it. About navigation was exercised.
- Thin, default, middle, and solid Ring previews were captured. A session-only
  `performance.now` offset exercised partial progress without waiting a minute;
  foreground-pixel ratios near half fill were 0.50, 0.486, 0.485, and 0.486 for
  thickness 1, 24, 50, and 100. This bounded browser check is separate from the
  injected-clock domain tests and does not establish interruption reliability.

Screenshots, scripts, and numeric results remain session-local, not controlled
approval evidence. This is not a full TP-REQ-003/012/013/014/015 pass. Complete
shape/state/detail/progress combinations, enlarged-font and screen-reader
behavior, reduced motion, offline operation, interruption paths, and human
design/accessibility/usability/risk acceptance remain pending. No device launch
or platform support is inferred from the Android build.

### Issue #67 About candidate

The About candidate was checked in the `feat/67-about-screen` worktree:

- `./kotlin build -m common` — passed.
- `./kotlin build -m browser` — passed for the browser Wasm target.
- `./kotlin test -p jvm -m common -m localization` — passed; 15 JVM tests.
- `./kotlin check -m about -m common -m localization` — passed.
- `./kotlin run -m browser --port 8081 --no-open-browser` — server started;
  `curl --silent --show-error --fail --output /dev/null --write-out 'HTTP %{http_code}\n' http://127.0.0.1:8081/`
  returned HTTP 200.

No browser executable or browser-automation runtime was available. The HTTP
response and successful resource compilation do not verify rendered logo
appearance, responsive/no-clipping behavior, keyboard operation, screen-reader
semantics, reduced motion, link destinations, or end-to-end navigation in a
browser. Those checks remain pending. The required version/build source,
support information, privacy policy, and relevant licences are unavailable,
so TP-REQ-004 is blocked and the About screen is not complete. Issues #65 and
#67 have since merged, but their merges do not establish the complete
Settings/About requirements. Issue #66 remains open; its Timer behavior and
product verification are not established by this About candidate. Human
design, usability, accessibility, and product review remain pending.

### Issue #66 Settings appearance candidate

In the `feat/66-settings-live-appearance` worktree, the focused JVM tests for
`settings`, `design`, `common`, `counter`, `localization`, `timer`, and
`persistence` passed, including 0-12 slider semantics, rendered track pixels
at zero and one, live color/font selection, and Timer/persistence regressions.
The browser Wasm, Android and iOS builds passed; no mobile runtime behavior is
inferred from compilation. The multi-platform `./kotlin check -m common -m
counter -m design -m settings -m localization` did not pass: the design Wasm
test runner failed while instantiating Skiko, consistent with the previously
recorded runner limitation.

Headless Chrome 158 with ANGLE/SwiftShader at 500 x 1000 showed the subtle
Settings background and, after scrolling, an undotted Detail track at zero.
An orange foreground, blue text and Monospace selection updated the compact
Settings view and were recorded in browser storage. At 1440 x 1000 the
supporting pane and Timer were observed, but resizing across layouts also
produced a Compose lookahead `NullPointerException`; related expanded browser
exceptions were already noted in the baseline. This is not a full protocol
pass: all shapes, colors, fallback renderers, invalid-draft updates, physical
input, screen readers, no-clipping across sizes and human review remain open.

### Issue #93 Android mobile release-readiness evidence

This execution record captures build and packaging evidence only. It does not
verify timer requirements, establish Android support, or satisfy the physical-
device and release gates in issue #93.

| Field | Observed value |
|---|---|
| Source revision | `8b62299f99f3262fab0b7e9d44c1a624f6bb52ed` (`feat/93-android-release-readiness`; clean at execution) |
| Host and toolchain | macOS 26.7.3 build 25G342, arm64; Kotlin Toolchain 0.13.0 (`abc7a5f`, 2026-10-01); Android command-line tools 20.0, ADB 37.0.0-14910828, emulator 37.1.11.0 |
| Effective Android settings | Application ID and namespace `nl.entreco.elchrono`; user-visible label `El Chrono`; `minSdk 24`, `compileSdk 37`, `targetSdk 37`, build tools 37.0.0, `versionCode 1`, `versionName unspecified`; signing disabled. SDK/version values are toolchain defaults, not approved release decisions. |
| Manifest permissions | No `<uses-permission>` declaration in `apps/mobile/android/src/AndroidManifest.xml`; the merged package manifest was not inspected. |

| Check | Exact command | Result |
|---|---|---|
| Android build | `./kotlin build -m android` | Passed from the feature worktree. |
| Release-format package task | `./kotlin package -m android --format aab` | Passed using the default release variant; produced an unsigned AAB, not a signed release candidate. |
| Package signature | `jarsigner -verify -verbose -certs build/tasks/_android_bundleAndroid/gradle-project-release.aab` | `jar is unsigned.` No release signing configuration or credentials were used. |
| Device inventory | `adb devices -l`; `emulator -list-avds`; `avdmanager list avd` | No connected devices. The only AVD is `Television_4K`, which cannot load because its Android TV system image is missing; it is not an Android phone/tablet test target. |

The local AAB at
`build/tasks/_android_bundleAndroid/gradle-project-release.aab` had SHA-256
`dcd3c22d77c0bf105021383f7c87230363b94969ee2b676769e87ca782c216c2` during
this run. The artifact remains a local build output and is not included in the
repository or PR.

No approved minimum Android version or product version is established. The
unsigned AAB was not installed or launched. Physical-device install/update,
runtime behavior, touch and accessibility, lifecycle/recovery, offline
operation, privacy and licence content, and the per-platform release decision
remain unverified. `sdkmanager --list_installed` did not return a usable package
inventory in this environment; the successful build/package and effective
settings above do not establish device coverage or platform support.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish planned test cases and acceptance criteria separately from requirement statements; no execution evidence claimed. |
| 0.2 | Identify the requirements baseline revision and add explicit no-clipping acceptance criteria for required remaining-time and screen content; retain unrun and blocked result rules. |
| 0.3 | Align procedures and criteria with v1 decisions for open Settings, save-to-start pause behavior, absent volume/music controls, deferred v2 audio, and no v1 interruption recovery; record the clipping code correction without claiming layout verification. |
| 0.4 | Record the limited F01 prototype layout check separately from product test execution; retain blocked status for undefined supported layouts and unverified application behavior. |
| 0.5 | Align TP-REQ-002 with the explicit v1 decision to omit separate manual pause, resume, and reset controls. |
| 0.6 | Add measurable verification for the four approved themes, browser interrupted state, About content gaps, and Android TV/Apple TV input/readability without claiming support or results. |
| 0.7 | Record focused counter and settings-domain test evidence after moving timer logic into core/counter; retain product UI, accessibility, interruption, offline, and platform verification limitations. |
| 0.8 | Add deterministic app-state lifecycle tests and align TP-REQ-003 with pause-on-stop/no-auto-resume behavior; retain runtime lifecycle, layout, and platform verification as pending. |
| 0.9 | Add adaptive supporting-pane Settings and target-specific keyboard/keypad acceptance criteria; record core duration-entry and Settings input-policy test ownership without treating builds as UI verification. |
| 1.0 | Record passing focused JVM tests and JVM/browser/Android/iOS builds; document the unresolved Skiko WebAssembly test-runner failure without treating builds as product verification. |
| 1.1 | Add issue #65 personalization, persistence, zero-duration, detail, and responsive browser verification criteria; retain human and runtime evidence gaps. |
| 1.2 | Align Timer criteria with issue #66's text-free detail-0 states, stale-progress semantics, exact segment counts, clockwise Clock mapping, and idle 00:00 behavior; retain browser and human-review gates. |
| 1.3 | Record the bounded Chrome render check at compact and expanded viewports; retain incomplete accessibility, visual-matrix, and human-review gates. |
| 1.4 | Replace Clock segment criteria with demo-matching duration labels, add compact/expanded Fan centering checks, and retain browser, accessibility, usability, and human-review gates. |
| 1.5 | Add measurable global-background, preview, stable-layout, Battery, and Ring-thickness criteria and scoped refinement evidence without claiming full protocol acceptance. |
| 1.6 | Add desktop keyboard entry criteria and identify the existing zero-duration requirement conflict; retain physical-device and accessibility verification gaps. |
| 1.7 | Add Settings live appearance, detail slider, and glass/fallback verification criteria; retain browser, platform, and human review gaps. |
| 1.8 | Specify overlay, blurred backdrop, unblurred fallback and unobstructed-preview removal checks; retain product and human review gaps. |
| 1.9 | Specify stationary Timer, sliding panel, glass, motion, back, focus, and fallback checks; retain human and platform verification gaps. |
| 2.0 | Add navigation and selected-swatch icon tint, indicator contrast, and semantics checks; retain platform and human review gaps. |
| 2.1 | Add remaining-time glass layout, visibility, fallback and renderer verification scope; retain accessibility and human review gaps. |
| 2.2 | Add palette layout, font rendering and tick-free Ring slider verification criteria; retain platform and human review gaps. |
| 2.3 | Replace planned timer acceptance criteria with one-shot, Settings intent and explicit action/interruption cases; preserve earlier execution history. |
| 2.4 | Record operator-reported focused JVM, check and build results and bounded test scope; identify the zero-duration interruption-warning mismatch and pending human review. |
| 2.5 | Align zero idle and retained-interruption criteria with the approved state priority; record parent-agent observed final checks and builds. |
| 2.6 | Record issue #81's shared-control remote-focus candidate; retain blocked remote, device, usability, and human-review verification. |
| 2.7 | Add configuration-recreation acceptance procedures, observed local refactor results and explicit common-JVM/device verification blockers. |
| 2.8 | Replace duration-entry acceptance criteria with the proposed independent-field behavior; retain historical results and pending platform verification. |
| 2.9 | Record focused JVM verification of the independent-field implementation; retain platform, accessibility, usability, and human-review gaps. |
| 3.0 | Record the TV IME selection correction, full-panel regression coverage, and bounded Android TV-emulator input observations; retain integrated-test and platform-review limitations. |
| 3.1 | Make duration-entry verification conditional on keyboard availability and record the tvOS Compose dependency blocker, installed simulator evidence, and pending Apple TV verification without claiming support. |
| 3.2 | Record issue #82 Android TV APK generation, the missing AVD image and absent connected target, bounded JVM duration-field focus testing, and blocked target/human verification without claiming support. |
| 3.3 | Add startup-background and settings-restoration criteria and bounded local evidence; retain platform and human-review blockers. |
| 3.4 | Record issue #93 Android build and unsigned AAB evidence, effective scaffold settings, and blocked physical-device/release verification without claiming support or readiness. |
