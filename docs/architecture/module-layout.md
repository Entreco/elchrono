# Module layout

Lifecycle status follows the [branch/PR rule](../governance/document-control.md#lifecycle-and-approval)
for all documents under `docs/`; no status is stored in this record.

## Decision

Organize the existing Kotlin Toolchain project into app launchers, reusable
platform-agnostic core libraries, and shared Compose feature libraries.
This is an engineering structure decision, not medical-device classification,
regulatory approval, or evidence that product features are implemented or
verified.

Platform launchers in `apps/desktop`, `apps/browser`, and `apps/mobile/{android,ios}`
depend on the shared `apps/common` application shell. The shell owns the main
scaffold, screen routing, Settings visibility control, and composition of
feature UI; it does not own timer-domain behavior or feature screens.
`core/counter` owns shared, Compose-free duration and countdown logic.
`features/timer` owns the countdown and visualization UI;
`features/settings` owns duration/theme selection UI; `features/about` owns
About UI; and `features/music` remains an empty v2 placeholder.

Features may depend on core libraries, but not on one another. Core modules
must not depend on features or apps. `core/design` owns shared design types,
components, and theme assets; `core/localization` owns all user-visible
strings. Platform lifecycle/integration code stays outside core.

The issue #47 implementation of this boundary is recorded in
[PR #59](https://github.com/Entreco/elchrono/pull/59).

No `core/prefs` module is added: cross-restart persistence has not been
specified or approved. Current settings live only in app memory; add local
storage only against an approved persistence requirement.

The shared libraries target JVM, Android, iOS ARM64, iOS simulator ARM64, and
Wasm/JS. These compilation targets do not establish TV/watch support.
`apps/television` and `apps/watch` reserve future launcher locations only.

## Ownership

- Design owns `ElChronoTheme`, the shared `TimerTheme` selection type, and
  reusable design assets. The initial theme delegates to MaterialTheme.
- Localization owns default-locale string resources. Generated accessors are
  public and use a distinct namespace from design resources.
- Common owns the app scaffold, feature routing, the Settings visibility
  control, and platform interruption hooks; it does not contain timer business
  logic or timer/settings/about screen UI.
- Counter owns `TimerDuration`, `MonotonicClock`, and `TimerEngine`, with
  deterministic domain tests in the same module.
- Timer, settings, and about own their respective Compose screens; duration
  digit-entry behavior and tests belong to settings.
- Music remains an empty v2 module with no app dependency or production
  behavior.

Keep package identities, Android application ID, desktop main class, and iOS
Swift/Kotlin bridge stable. The iOS Xcode wrapper path must point from the
nested module back to the root `kotlin` wrapper in both build configurations.

## Trade-offs

A shared application shell avoids duplicating composition across launchers or
making core depend on feature UI. Small explicit feature modules establish
ownership without a new framework or a catch-all utilities library.

The existing wrappers, YAML build model, and dependency versions are retained.
The new counter module has no Compose dependency. The about feature is separate
so the app shell remains a thin coordinator and each feature owns its UI.

## Verification

Build/check/test commands and launch prerequisites are in the root README.
Verification must cover module discovery and dependency direction, preserved
platform tests, localized-string formatting, all configured app builds, and
actual desktop/browser/Android/iOS simulator launches with resources rendered.
Record observed outcomes separately from unsupported targets or signing limits.
Counter behavior and settings duration entry have focused JVM regression
tests. Compose feature layout/accessibility and interactive browser behavior
remain unverified by UI-level tests.

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
