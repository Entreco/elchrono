# El Chrono - Project instructions

## Purpose

El Chrono is a visual remaining-time timer for people with cognitive or
intellectual disabilities. It helps patients understand how much waiting time
remains without showing a clock face or the time of day. Caregivers configure
the experience; patients have simple, accessible controls.

- Repository: https://github.com/Entreco/elchrono
- Project: https://github.com/users/Entreco/projects/4
- Milestones: https://github.com/Entreco/elchrono/milestones

## Non-medical product boundary

El Chrono is intended as a **non-medical-device app** for visual waiting-time
support. Do not make medical claims in the application, documentation, marketing,
store listings, or support material. Do not introduce diagnosis, treatment,
medical-condition monitoring, or safety-critical medical timing as intended uses.

Medical-device-style documentation is adopted voluntarily for rigor, not to
position the app as a medical device or claim certification. Target-market
qualification review must check the actual intended use, functionality, and
claims against this boundary; a disclaimer alone does not determine legal
qualification. If the assessment conflicts with the non-medical scope, escalate
and resolve the conflict before continuing affected work. Do not silently expand
the product into a medical device.

## Product requirements

- Caregivers select any whole-minute duration from 1 minute to 24 hours using
  Hours and Minutes fields: sequential digits fill MM first and then shift left
  into HH (for example, `205` displays `02:05`).
- Caregivers select from the available timer themes. Include quiet experiences
  and explore geometric experiences such as triangles; final designs require
  usability review rather than assumptions about patient preferences.
- Caregivers optionally connect YouTube or Spotify for background music through
  supported provider integrations.
- Patients can change volume without gaining access to caregiver settings.
- The timer must work without music or internet connectivity.
- Audio failures must not interrupt the timer or silently appear successful.
- Keep visual remaining-time semantics consistent across themes and platforms.

Minimum screens:

1. **Main:** themed countdown, accessible volume control, and distinguishable
   running, paused, and interrupted states; no clock presentation. On
   completion, reset immediately to the configured duration and stop music;
   do not display a completed state.
2. **Settings:** duration, theme, optional music, and caregiver timer controls.
3. **About:** company logo, version/build details, support information, privacy
   policy, relevant licences, intended use, and limitations.

Caregiver protection, volume limits, completion sounds, pause/reset behaviour,
and interruption recovery require documented requirements and risk/UX review.
Do not introduce these behavioural decisions implicitly during implementation.

## Platform targets

| Target | Platforms | Current scaffold |
|---|---|---|
| Web | Browser | `apps/browser`, Kotlin/Wasm |
| Mobile | Android, iOS | `apps/mobile/android`, `apps/mobile/ios` |
| Desktop | Desktop operating systems agreed in the support matrix | `apps/desktop`, JVM |
| TV | Android TV, Apple TV | Reserved at `apps/television`; no target configured |
| Watch | Wear OS, watchOS | Reserved at `apps/watch`; no target configured |

Do not claim TV/watch support merely because Android/iOS code compiles.
Validate platform tooling, packaging, input, lifecycle, and provider support.
Document minimum supported OS/browser versions and desktop OS scope. Watches
may require a companion app; do not assume standalone audio or feature parity.

## Existing implementation and conventions

The project uses Kotlin Multiplatform and Compose Multiplatform, built with the
Kotlin Toolchain wrappers `kotlin` and `kotlin.bat`. The current UI is a starter
screen, not an implemented timer.

- `project.yaml` declares modules; each module uses `module.yaml`.
- `libs.versions.toml` is the dependency version catalog.
- `apps/common` composes shared feature UI and owns the application shell.
- `features/{timer,settings,music}` are CMP library scaffolds, not implemented
  product features. Feature UI and feature-owned logic belong here.
- `core/design` owns the shared theme/components and design resources.
- `core/localization` owns string resources and translations; use generated
  Compose resource accessors rather than hard-coded visible text.
- Each module's `src` contains common Kotlin code; `src@<platform>` contains
  platform-specific implementations; `test` and `test@<platform>` contain tests.
- `composeResources` belongs to its owning module. Design and localization have
  distinct generated resource packages with exposed cross-module accessors.
- Application modules contain their platform entry points and configuration.
- Use the existing `nl.entreco.elchrono` package hierarchy and Compose resources.
- Platform launchers depend on the common shell; the shell depends on
  features/core; features depend on core. Core never depends on features/apps,
  other features do not depend on one another, and platform SDK code stays
  outside core.
- Register nested paths in `project.yaml` and use root-relative module dependency
  paths such as `//apps/common`. CLI selectors use unique leaf module names.

See `docs/architecture/module-layout.md` for the restructure rationale.

Reuse this structure. Do not introduce a parallel Gradle build or replace the
toolchain/framework without an approved architecture decision.
Keep timer logic independent of rendering and music integrations. Share domain
behaviour where feasible; isolate platform lifecycle, persistence, playback,
and system-volume APIs behind narrow, testable boundaries.

## Accessibility and timer reliability

- Prefer predictable layouts, readable contrast, large controls, and low sensory
  load. Never communicate remaining time through colour alone.
- Avoid flashing, sudden loud audio, and unnecessary motion. Validate theme
  choices with caregivers and representative patients under appropriate
  consent and safeguarding arrangements.
- Provide accessible labels/semantics and verify applicable screen-reader,
  keyboard, touch, TV remote/D-pad, and watch interactions.
- Derive elapsed time from an appropriate monotonic time source during active
  execution; do not treat animation frames or callback counts as elapsed time.
- Define and verify recovery across backgrounding, sleep, process termination,
  restart, and wall-clock changes. Monotonic timestamps alone do not provide
  persistence across device restarts.
- Test every allowed duration, including 1 minute and 24 hours, invalid
  configuration, pause/resume, completion boundaries, and interruption paths.
- Inject controllable time sources for deterministic tests rather than waiting
  real minutes/hours. Prevent negative remaining time and duplicate completion.
- Surface errors explicitly without exposing patient data or credentials.

## Medical-device documentation and safety

The M1 software-safety applicability assessment in
[M1-DOC-005](docs/safety/software-safety-classification.md) concludes that El
Chrono, within its defined non-medical intended purpose and functions, is not
medical-device software. IEC 62304:2006 with Amendment 1:2015 software safety
classification is therefore not applicable to that scope; no Class A, B, or C
is assigned. This is distinct from regulatory device classification and does
not claim legal qualification, conformity, or certification. Voluntary
project controls may draw on lifecycle practices, but must not be described
as IEC 62304 applicability or conformity.

M1 must establish the non-medical intended use and no-medical-claims boundary,
review medical-device qualification and target-market obligations, and justify
the standards' applicability with qualified review. Assess the intended
purpose, actual functions, claims, and markets; absence of medical claims alone
does not determine qualification. Reassess if any of these change. If a
changed scope makes medical-device software or otherwise changes standards
applicability, escalate and assess obligations before affected work continues.
Do not assign a software safety class by default. Never claim certification or
invent clinical evidence.

Product documents belong under `docs/`. The PR human acceptance checklist is review and
approval evidence; do not create duplicate Markdown evidence files or require
separate signatures. Link the PR from the document and record review scopes,
checks, findings resolution, and limitations in the PR. Keep pending reviews
explicit; never fabricate qualified human review or approval.

Lifecycle status is derived from the viewed branch and PR context for every
document under `docs/`, including unchanged documents: **Draft** on a feature
branch without an open PR targeting `main`, **In review** with an open PR
(including draft PRs), and **Approved and effective** on `main` after merge.
Do not store Status/Approval fields or duplicate current lifecycle labels in
indexes or revision histories; branch/PR transitions need no metadata commits.
This status is not proof of specialist review, verification, milestone
completion, or release approval. Keep evidence gaps and use restrictions
explicit even on `main`; required pre-merge review gates still apply.

All changes must enter `main` through a PR. For solo maintenance, use zero required
formal GitHub approvals and permit author self-review. Before merging, a human
must review the final diff, record their handle and full reviewed head SHA in the
PR description, and check the acceptance box. Agents must not check it. After
new commits, clear acceptance and repeat review. This is a manual gate, not
technically enforced human approval; required specialist scopes still apply.
Require resolution of review conversations and protected-main enforcement with
no bypasses, force pushes, or branch deletion. Verify effective GitHub protections
before claiming enforcement. See [document controls](docs/governance/document-control.md).

Maintain controlled, versioned records as applicable:

- Intended use, standards-applicability rationale (and classification rationale
  where applicable), development plan, responsibilities,
  and standards/documentation applicability.
- Requirements with stable IDs; keep measurable acceptance criteria in the
  test protocol and requirement-risk-test relationships in the traceability
  matrix.
- Risks, controls, usability findings, and verification of those controls.
- Architecture/design decisions and third-party dependency/SOUP assessments.
- Verification plans, results, and requirements-to-risk-to-test traceability.
- Configuration/change records, unresolved anomalies, release approvals,
  instructions, limitations, and maintenance/incident assessment procedures.

These project controls are not a claim that every listed document is explicitly
required by IEC 62304 or legally required for this non-medical app. Distinguish
voluntarily adopted controls from applicable legal obligations in the approved
applicability matrix.
Update affected records with each change. Separate observed evidence from
assumptions; record missing evidence and review status honestly.

## Privacy and music integrations

Avoid patient accounts and identifiable patient data unless approved requirements
make them necessary. Do not add telemetry by default.
Document provider data flows, consent where applicable, token storage,
retention, and disconnection behaviour. Never commit credentials, signing keys,
tokens, patient data, or identifiable logs/screenshots.

Use official provider APIs/SDKs and supported authentication/playback mechanisms.
Verify licensing, subscriptions, autoplay, embedding, and platform limitations.
Do not scrape providers, bypass playback restrictions, or promise unsupported
system-volume control. Display meaningful errors and preserve timer operation.

## Build, checks, and tests

Run commands from the repository root. Use the existing wrapper rather than
assuming globally installed Kotlin or Gradle:

```sh
./kotlin build -m common
./kotlin test -m common -m localization
./kotlin check -m common -m localization
./kotlin build -m android
./kotlin run -m android --device-id <adb-serial>
./kotlin run -m desktop
./kotlin run -m ios -p iosSimulatorArm64 --device-id <simulator-uuid>
./kotlin run -m browser
```

Use `./kotlin --help` and subcommand help to discover supported options;
`./kotlin show --help` exposes discovery commands. Scope validation to affected
modules/platforms first, then broaden when shared changes warrant it.
Use `./kotlin build`, `./kotlin test`, and `./kotlin check` for whole-project
validation when needed. Report missing SDKs, unavailable targets, absent tests,
or failed checks as limitations, never as passing verification.
Documentation-only changes need no compilation unless documentation tooling
requires it; check links, command accuracy, and the diff.

Pull requests and pushes to `main` run `.github/workflows/ci.yml` on
`ubuntu-24.04` with Java 17. It discovers modules, builds JVM and browser Wasm
targets in separate invocations, and runs common/localization JVM tests and
checks:

```sh
./kotlin show modules
./kotlin build -p jvm
./kotlin build -m browser
./kotlin test -p jvm -m common -m localization
./kotlin check -m common -m localization
```

This workflow does not provision an Android SDK or validate Android builds,
emulator/device launches, or iOS builds. See the root README for the local
workflow and platform prerequisites. Keep configuration in the repository's
YAML module model and version catalog; supply machine-specific paths through
the local shell or CI environment. Never commit credentials, signing keys,
tokens, patient data, local environment files, or machine-local `.idea` files.
Only shared `.idea/codeStyles/` and `.idea/inspectionProfiles/` files may be
tracked.

## Required engineering skills and Android CLI guidance

Relevant competencies are Kotlin/Compose Multiplatform, deterministic timer
testing, accessibility/usability, platform lifecycle management, supported media
integration, and controlled medical-device software documentation.
Use agent skills only when actually available; do not invent skill names or
assume a dedicated Android CLI skill is installed.

For Android work, know Android SDK command-line tools, ADB, emulator management,
manifest/permissions, audio focus, lifecycle, and remote/D-pad navigation for TV.
Use an installed SDK and discover available tools before installing anything.
Typical inspection commands, when those tools are available on PATH:

```sh
sdkmanager --list_installed
avdmanager list avd
emulator -list-avds
adb devices -l
```

When multiple devices are connected, explicitly select an authorized test device:

```sh
adb -s <serial> shell am start -n nl.entreco.elchrono/.MainActivity
adb -s <serial> shell input keyevent KEYCODE_DPAD_CENTER
```

Install/start the app through `./kotlin run -m android --device-id <serial>`.
Discover actual APK paths and available CLI options; never guess build outputs.
Scope diagnostics to the app/test device and redact sensitive data from logs.
Do not wipe emulators, uninstall apps, modify a personal device, accept SDK
licences, or alter SDK packages without approval. Never work around unavailable
Apple TV/watch toolchains by labelling mobile builds as those products.

## Milestones and GitHub workflow

1. **M1:** Intended use and safety foundation.
2. **M2:** Requirements and accessible UX.
3. **M3:** Architecture and engineering foundation.
4. **M4:** Reliable timer engine.
5. **M5:** Browser MVP with selectable themes.
6. **M6:** Music and volume.
7. **M7:** Mobile and desktop releases.
8. **M8:** TV and watch releases.
9. **M9:** Release baseline and maintenance.

Follow the dependencies and completion gates in the repository milestones.
Matching project Backlog cards are drafts, not development issues. When asked
to create issues, assign the repository milestone and add them to the project;
include scope, acceptance criteria, dependencies, relevant requirement/risk IDs,
and documentation evidence. Do not invent deadlines or approval decisions.

Use consistent names for issues, branches, pull requests, and commits:

- Issue titles use `<milestone>: <verb-led outcome>` (for example,
  `M1: Assess medical-device qualification and target-market classification`).
- Branches use `<type>/<issue-number>-<short-kebab-topic>` (for example,
  `docs/3-market-qualification`), matching established names such as
  `docs/1-document-control-update` and `docs/2-intended-use`. Use a concise
  type such as `docs`, `feat`, `fix`, or `chore`; do not spell out `issue-`
  before the number. Choose the final name before publishing; avoid renaming a
  branch after opening its PR.
- Pull request titles use `<type>: <imperative summary> (#<issue-number>)`.
- Commit subjects use `<type>(optional-scope): <imperative summary> (#<issue-number>)`;
  keep the subject concise and tie each task commit to its issue.

An issue is Done only after implementation/documentation review, verification
of acceptance criteria, updated traceability, and recorded limitations.
Apply release approval to each shipped platform, not only the final milestone.

## Change discipline

Read relevant code and requirements before editing. Make focused changes,
preserve unrelated work, and add regression coverage for behaviour changes.
Do not modify generated files, silently suppress failures, or introduce
dependencies without a clear need and dependency assessment.
Do not commit, push, publish, or change GitHub status without authorization.

Treat `.idea` project models, caches, device state, workspace/session history,
and local run configurations as generated or machine-local files. They are
ignored; only intentionally shared `codeStyles` and `inspectionProfiles` are
allowed. Review those profiles for machine-specific paths before committing.
Do not share IDE launch configurations until verified after successful sync;
the root README contains the verified CLI launch commands.
