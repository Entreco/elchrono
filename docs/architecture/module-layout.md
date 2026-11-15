# Module layout

## Decision

Organize the existing Kotlin Toolchain project into app launchers, reusable
platform-agnostic core libraries, and shared Compose feature libraries.
This is an engineering structure decision, not medical-device classification,
regulatory approval, or a claim that product features are implemented.

Platform launchers in `apps/desktop`, `apps/browser`, and `apps/mobile/{android,ios}`
depend on the shared `apps/common` application shell. The shell composes
`features/{timer,settings,music}` and core libraries. Features can use
`core/design` and `core/localization`; core must not depend on features or apps.
Platform lifecycle/integration code stays outside core.

The shared libraries target JVM, Android, iOS ARM64, iOS simulator ARM64, and
Wasm/JS. These compilation targets do not establish TV/watch support.
`apps/television` and `apps/watch` reserve future launcher locations only.

## Ownership

- Design owns `ElChronoTheme` and reusable design assets. The initial theme
  delegates to MaterialTheme to preserve the starter appearance.
- Localization owns default-locale string resources. Generated accessors are
  public and use a distinct namespace from design resources.
- Common owns the starter `App` and `expect`/`actual` platform-name demonstration.
  The obsolete hard-coded `Greeting` helper is replaced by formatted string
  resources; its formatting regression coverage lives in localization tests.
- Features are configured library scaffolds with no invented product screens
  or domain/provider behaviour.

Keep package identities, Android application ID, desktop main class, and iOS
Swift/Kotlin bridge stable. The iOS Xcode wrapper path must point from the
nested module back to the root `kotlin` wrapper in both build configurations.

## Trade-offs

A shared application shell avoids duplicating composition across launchers or
making core depend on feature UI. Small explicit feature modules establish
ownership without a new framework or a catch-all utilities library.

The existing wrappers, YAML build model, and dependency versions are retained.
The only added library is test-scoped `kotlinx-coroutines-test`, aligned to the
existing coroutine version, for reading and formatting suspend-based Compose
string resources in JVM regression tests. Those tests also use the existing
Compose desktop runtime alias at runtime because resource environment detection
loads the host's Skiko native library. Neither adds production data flow.
No additional translations or runtime privacy behaviour are introduced.

## Verification

Build/check/test commands and launch prerequisites are in the root README.
Verification must cover module discovery and dependency direction, preserved
platform tests, localized-string formatting, all configured app builds, and
actual desktop/browser/Android/iOS simulator launches with resources rendered.
Record observed outcomes separately from unsupported targets or signing limits.
The feature modules have no product-behaviour tests because they contain no
product implementation.

Restructure verification outcomes:

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
