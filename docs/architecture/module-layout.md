# Module layout

Desktop keyboard changes: [PR #73](https://github.com/Entreco/elchrono/pull/73).

Lifecycle status follows the [branch/PR rule](../governance/document-control.md#lifecycle-and-approval)
for all documents under `docs/`; no status is stored in this record.

## Decision

Organize the existing Kotlin Toolchain project into app launchers, reusable
platform-agnostic core libraries, and shared Compose feature libraries.
This is an engineering structure decision, not medical-device classification,
regulatory approval, or evidence that product features are implemented or
verified.

Platform launchers in `apps/desktop`, `apps/browser`, and `apps/mobile/{android,ios}`
depend on the shared `apps/common` application shell. The shell owns the
Material 3 app scaffold and adaptive pane layout, screen routing,
Settings visibility and lifecycle coordination through `AppViewModel`, and
composition of feature UI; it does not own timer-domain behavior or
feature-screen UI. `core/counter` owns shared, Compose-free duration and
countdown logic.
`features/timer` owns the countdown and visualization UI;
`core/design` owns reusable design components, including a numeric keypad that
is not used by Settings;
`features/settings` owns independent Hours/Minutes field-entry behavior,
duration/theme selection UI, and its tests;
`features/about` owns About UI; and `features/music` remains an empty v2
placeholder.

Features may depend on core libraries, but not on one another. Core modules
must not depend on features or apps. `core/design` owns shared design types,
components, and theme assets; `core/localization` owns all user-visible
strings. Platform lifecycle and keyboard-capability integration stay outside
core. Settings uses independently editable platform text fields on all
configured targets and does not display the app keypad or gate field editing
on keyboard-availability detection. Actual keyboard/IME behavior, accessibility,
and remote operation require target-specific verification; this does not
establish support for unconfigured TV targets.

The issue #47 implementation of this boundary is recorded in
[PR #59](https://github.com/Entreco/elchrono/pull/59).

No `core/prefs` module is added: cross-restart persistence has not been
specified or approved. Current settings live only in app memory; add local
storage only against an approved persistence requirement.

The shared libraries target JVM, Android, iOS ARM64, iOS simulator ARM64, and
Wasm/JS. These compilation targets do not establish TV/watch support.
`apps/television` and `apps/watch` reserve future launcher locations only.

## Ownership

- Design owns `ElChronoTheme`, shared `TimerTheme`, spacing tokens in
  `LocalDimens`, and reusable fade and directional slide transitions in
  `ElChronoMotion`. The shell chooses transitions for its navigation scenes;
  design does not depend on navigation or app state.
  `LocalDimens` supplies a relative spacing scale from `spaceXXS` to
  `spaceXXXL`, with `spaceM` as the default. The baseline values are 2, 4,
  6, 8, 12, 16, 20, and 24 dp, respectively; names describe relative sizes,
  not fixed dimensions. A composition subtree can override the `Dimens` value
  without changing the enclosing theme. No form-factor-specific values are
  selected by default; future profiles require layout and accessibility review.
- Localization owns default-locale string resources. Generated accessors are
  public and use a distinct namespace from design resources.
- Common owns the adaptive app scaffold, feature routing, the `AppViewModel`
  that coordinates `TimerEngine`, settings and lifecycle state, and lifecycle
  event integration. `AppUiState.isTicking` derives whether updates are needed
  from running and interruption state; the lifecycle still gates the UI update
  loop. Common does not contain timer-domain logic or feature-screen UI.
- Counter owns `TimerDuration`, `MonotonicClock`, and `TimerEngine`, with
  deterministic tests in the same module.
- Design owns the reusable `NumericKeypad`; Settings does not use it.
- Timer (`TimerFeature`), settings (`SettingsPanel` and `SettingsScreen`), and
  about own their respective Compose screens; field-entry behavior and tests
  belong to settings.
- Music remains an empty v2 module with no app dependency or production
  behavior.

Keep package identities, Android application ID, desktop main class, and iOS
Swift/Kotlin bridge stable. The iOS Xcode wrapper path must point from the
nested module back to the root `kotlin` wrapper in both build configurations.

## Trade-offs

A shared application shell avoids duplicating composition across launchers or
making core depend on feature UI. Small explicit feature modules establish
ownership without a new framework or a catch-all utilities library.

The existing wrappers and YAML build model are retained; the shared adaptive
layout module is added at the existing adaptive version. The counter module
remains Compose-free. The common shell uses the Compose Multiplatform
Navigation 3 `NavDisplay` with Material Adaptive scene strategies. The app
state owns the Timer → Settings → About back stack. The Material Adaptive
directive selects the split scene when the window allows at least two
horizontal partitions. In that scene Settings occupies the left slot and
Timer the remaining space; About replaces Settings in the same slot.
Navigation 3 slides between single-pane destinations and fades between
Timer-only and split scenes on wider windows. Navigation 3 and
its adaptive scene own the Android predictive-back gesture and transition;
the app does not track gesture progress or offsets.

## Verification

Build/check/test commands and launch prerequisites are in the root README.
Verification must cover module discovery and dependency direction, preserved
platform tests, localized-string formatting, all configured app builds, and
actual desktop/browser/Android/iOS simulator launches with resources rendered.
Record observed outcomes separately from unsupported targets or signing limits.
Counter behavior and independent field-entry logic have focused JVM regression
tests. Settings also has desktop Compose interaction tests for field editing,
focus selection, empty input, and Backspace. Physical keyboard/IME behavior on
each platform, screen-reader and remote operation, feature layout/accessibility,
and interactive browser behavior remain unverified by those tests.

Historical scaffold verification outcomes (not evidence for the #47 UI):

| Check | Observed outcome |
|---|---|
| Module discovery | All ten modules resolved with unique leaf names |
| Shared dependency graph | Common resolves design/localization and the three feature libraries; no core-to-feature dependency |
| `./kotlin build` | Successful for configured modules/platforms, including iOS device and simulator compilation |
| `./kotlin check` | Successful, including preserved platform tests and two JVM string-resource regression tests |
| Desktop, macOS | On-screen app window observed; user confirmed theme, button interaction, logo and greeting. Automated inspection/capture was blocked by macOS permissions |
| Browser, Chrome | HTTP response, rendered theme/logo/strings, button interaction, and absence of page runtime errors verified |
| Android emulator | App installed/launched; accessibility hierarchy and screenshot verified button, theme, logo and formatted greeting after interaction |
| iOS simulator | App installed/launched; screenshot verified initial theme/button and bundle inspection verified design/localization resources |
| Direct Xcode simulator build | Successful using the relocated project and corrected root-wrapper path |

Screenshots, build logs, and browser automation evidence were retained as local
session artifacts, not committed app assets. Desktop confirmation is manual
evidence, not an automated UI test. iOS hidden-content interaction was not
automated. Unsigned iOS device compilation does not establish physical-device
installation; TV/watch and other desktop hosts remain unverified future targets.
