# Browser and desktop platform feasibility findings

| Field | Value |
|---|---|
| Document ID | M3-DOC-001 |
| Revision | 0.2 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Platform feasibility and browser/desktop scopes; appointments and competence confirmation pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#33](https://github.com/Entreco/elchrono/issues/33) |
| Review PR | [PR #42](https://github.com/Entreco/elchrono/pull/42) (draft; required review scopes pending) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This record evaluates the configured browser and desktop launchers as technical
proofs for the shared application shell on the currently available macOS host.
It builds on the starter-demo evidence in the
[module layout decision](module-layout.md). A successful demo launch is not
product validation, a supported-platform commitment, or approval of the M2
gate. The app remains a starter UI, not an implemented timer.

The evaluation does not change product behavior or intended use. El Chrono
remains non-medical visual waiting-time support; this record makes no medical
claims and does not position the app for diagnosis, treatment, monitoring, or
safety-critical timing.

## Tested environment

| Component | Observed version |
|---|---|
| Host | macOS 26.6.2, build 25G83, arm64 |
| Browser | Google Chrome 157.0.8103.79 |
| Kotlin Toolchain | 0.13.0 (`abc7a5f`, 2026-10-01) |
| JVM | OpenJDK 25.0.4 (`25.0.4+-16339643-b508.27`), arm64 |
| Modules | `browser` (`wasm-js/app`) and `desktop` (`jvm/app`) |

No additional host, OS, browser, physical device, or browser version was tested.
These observations do not establish minimum browser or desktop OS versions.

## Execution evidence

Commands were run from the repository root.

| Check | Command | Observed outcome | Limitation |
|---|---|---|---|
| Module discovery | `./kotlin show modules` | Both `browser` and `desktop` were discovered with the expected product types. | Discovery is not platform support evidence. |
| Target builds | `./kotlin build -m browser -m desktop` | Successful. | Build success alone does not establish launch or packaging support. |
| Browser launch | `./kotlin run -m browser` | Did not start: the toolchain reported `java.net.BindException: Address already in use` while binding its default port. A Java process already held `127.0.0.1:8080`; it was left untouched. | The canonical browser launch command needs a rerun in an environment where its default port is free. |
| Browser launch with free port | `./kotlin run -m browser --port 8081` | Successful; the toolchain reported `Responding at http://127.0.0.1:8081`, and Chrome loaded the ElChrono page. | This workaround does not replace verification of the default command. |
| Desktop launch | `./kotlin run -m desktop` | Successful; the toolchain reported application startup, and an ElChrono window rendered on macOS. | Only this macOS host was exercised. |
| Desktop package | `./kotlin package -m desktop --format executable-jar` | Successful; `build/tasks/_desktop_executableJarJvm/desktop-jvm-executable.jar` was generated. | The packaged JAR was not independently installed or launched; this is not OS installer or signing evidence. |

The desktop launch emitted a JDK 25 restricted-native-access warning from
Skiko's `System.load` call. No desktop runtime exception was observed. The
warning is an environment/runtime compatibility consideration, not evidence of
a failed launch.

## Rendering and input observations

Both launchers rendered the shared starter shell, including its themed
background and localized `Click me!` button. In Chrome, pointer activation
revealed the Compose Multiplatform image and the localized greeting for
`Web with Kotlin/Wasm`; this confirms those shared/design/localization resources
rendered in the browser run. The desktop window showed the initial shell; its
conditional image/greeting state was not verified there.

Only the browser pointer interaction was exercised. Desktop activation beyond
initial rendering, keyboard navigation, screen-reader semantics, touch, TV
remote/D-pad, and watch input were not tested. No product workflow or timer
behavior was exercised.

## Packaging, offline startup, and runtime constraints

- The desktop executable JAR packaging task completed, but its artifact was not
  run independently and no native installer, signing, or packaging on another
  desktop OS was evaluated.
- The Kotlin Toolchain package help lists JAR, executable-JAR, Android AAB, and
  Maven Central bundle formats; no browser-specific web deployment package
  format is listed. The browser was run through the local development server,
  not deployed to a static web host or installed as a PWA.
- A cold browser load requires its static Wasm/JS resources to be served. No
  service worker or offline cache was identified in the inspected launcher.
  No network-disconnection/offline-startup test was performed, so offline
  availability is not claimed for either launcher.
- No external network request references were found in the inspected launcher,
  shared shell, design, or localization sources. This source inspection is not
  a network-isolation test or an assessment of all transitive dependencies.
- No runtime behavior outside macOS 26.6.2/arm64 and Chrome 157.0.8103.79 was
  evaluated. Minimum browser and desktop OS versions remain undecided.

## Findings and follow-up

The configured browser and desktop launchers are viable technical proofs of
the shared starter shell on this host. The successful browser run required
port 8081 because port 8080 was already occupied; rerun the canonical command
when that port is available before claiming an unqualified reproducible
browser launch. Verify the desktop conditional resource state and perform
offline, keyboard/accessibility, and additional-host checks only when their
scope and environments are available.

The M2 evidence handoff
([M2-DOC-007](../governance/m2-evidence-handoff.md)) keeps the M2 gate open.
This M3 platform evaluation does not clear that dependency or authorize product
implementation. No platform support baseline is selected by these findings.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record issue #33 browser and desktop prototype evidence, tested host/tool versions, and remaining platform-feasibility limitations. |
| 0.2 | Link draft review PR #42; required reviewer scopes and human acceptance remain pending. |
