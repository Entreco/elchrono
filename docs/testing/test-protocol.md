# Product test protocol

| Field | Value |
|---|---|
| Document ID | M2-DOC-002 |
| Revision | 1.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Test and product requirements review: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#25](https://github.com/Entreco/elchrono/issues/25) |
| Review PR | Prior baselines: [PR #27](https://github.com/Entreco/elchrono/pull/27), [PR #31](https://github.com/Entreco/elchrono/pull/31), [PR #32](https://github.com/Entreco/elchrono/pull/32), and [PR #41](https://github.com/Entreco/elchrono/pull/41); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); #67 candidate: [draft PR #68](https://github.com/Entreco/elchrono/pull/68); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This protocol contains planned test procedures and acceptance criteria for
[M2-DOC-001, revision 0.9, Product requirements](../product/requirements.md).
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
| TP-REQ-001 | [REQ-001](../product/requirements.md#requirement-identification) | Enter representative digit sequences through the mobile system-keyboard route and the on-screen keypad route. Confirm desktop/browser use the keypad without keyboard-based entry. Test duration boundaries, invalid values, and all whole-minute durations with a controllable input/test harness. | Both available input routes preserve the sequential mapping: `2`, `20`, `205`, and `2400` display as `00:02`, `00:20`, `02:05`, and `24:00`. Every duration from `00:01` through `24:00` is representable. `00:00`, minute values above `59`, and values above `24:00` cannot be configured as valid durations. On mobile, keypad visibility follows keyboard availability; desktop, browser, and TV use the keypad and do not expose keyboard-based duration entry. |
| TP-REQ-002 | [REQ-002](../product/requirements.md#requirement-identification) | Inspect Main and Settings for manual timer controls. Save valid settings and advance the injected time source. While running, open Settings and record remaining time; keep Settings open while advancing time, then save valid settings. | No separate manual pause, resume, or reset controls are available in v1. Saving starts the countdown from the full saved duration. Opening Settings pauses it (remaining time does not change while Settings is open). Saving again starts a new countdown from the full saved duration, not the previously remaining time. |
| TP-REQ-003 | [REQ-003](../product/requirements.md#requirement-identification) | Review Clock, Battery, Fan, and Ring and exercise running, paused, and interrupted presentations. In the app-state test harness, trigger repeated lifecycle stop/start events; on each configured runtime target, trigger stop/background and return. Observe the interruption warning, timer status, and valid-save behavior. Inspect each supported layout configuration in scope. | All four themes map consistently to remaining time and present numeric remaining time or visual progress. Remaining-time information is fully visible and not clipped or obscured. No clock face or time of day is presented, state meaning does not rely on colour alone, and numeric duration is not presented as time of day. Lifecycle stop pauses a running timer and shows interrupted; start does not automatically resume it, and valid Save starts from the full saved duration. Process-termination/restart recovery is not claimed. |
| TP-REQ-004 | [REQ-004](../product/requirements.md#requirement-identification) | Inspect Main, Settings, and About against their required v1 content. At expanded widths confirm Settings and About use the supporting pane beside Timer; at compact widths confirm each is a reachable single-pane destination. Resize across the breakpoint while About is open. Verify the official logo's scaling and accessible name, actual version/build metadata, approved support/privacy/licence information and link destinations, intended-use/limitations copy, navigation back to Settings and Timer, keyboard operation, and screen-reader labels. | Main includes the selected timer visualization. Settings uses the supporting pane when space permits and remains available on compact windows; duration and theme controls are present, with no v1 music or volume controls. About contains all REQ-004 content from authoritative sources; the logo preserves its aspect ratio and is accessible, links work, navigation is correct, and content is readable and not clipped or obscured. A missing source or required item is explicitly recorded as a gap and makes this test blocked, not passed. |
| TP-REQ-005 | [REQ-005](../product/requirements.md#requirement-identification) | Open Settings without a gate as a patient and change/save duration or theme. | Settings is not protected by a caregiver gate in v1 and permits the access specified by the product decision. The test verifies this behavior; it does not establish that the resulting risk is acceptable or controlled. |
| TP-REQ-006 | [REQ-006](../product/requirements.md#requirement-identification) | Select Clock, Battery, Fan, and Ring in Settings and inspect each timer presentation. | Exactly the four owner-approved v1 themes are available, each can be selected and becomes the selected timer theme, and each preserves the shared remaining-time mapping. Do not assert user preference without usability evidence. |
| TP-REQ-008 | [REQ-008](../product/requirements.md#requirement-identification) | For v1, run the timer with network unavailable. Defer audio-failure and playback checks until a v2 music integration is approved and implemented. | Timer operation and remaining-time presentation do not depend on network connectivity. No v1 music integration or playback-failure behavior is claimed. The v2 audio criteria require review before that scope begins. |
| TP-REQ-009 | [REQ-009](../product/requirements.md#requirement-identification) | Review application screens, About content, and external product claims against intended-use exclusions. | No content presents the app as a clock, guarantees a deadline, makes medical claims, or presents it for safety-critical timing. Conflicts in intended purpose, functionality, claims, or markets are escalated before affected work continues. |
| TP-REQ-010 | [REQ-010](../product/requirements.md#requirement-identification) | With a controllable time source and no v1 audio, advance the countdown to zero. | The timer immediately resets to the configured duration, displays no completed state, handles completion once, and does not become negative. Stopping optional music is deferred to v2 and is not a v1 test result. |
| TP-REQ-011 | [REQ-011](../product/requirements.md#requirement-identification) | On each configured target, operate Main, Settings, and About using its supported remote/controller focus model; verify visible focus, predictable navigation, readability at ordinary viewing distance, and non-colour-only cues. Attempt target packaging and lifecycle checks. | Android TV and Apple TV controls are operable without touch, focus is visible and predictable, and content remains readable and complete at the agreed viewing distance. Record target configuration and verification before claiming support; an unconfigured target or unavailable device makes the test blocked, not passed. |
| TP-REQ-012 | [REQ-012](../product/requirements.md#requirement-identification) | Enumerate detail levels 0-12 and select colors/fonts across shape changes and temporary hidden controls. | Every detail level and color/font choice is reachable, shape changes do not erase selections, and no contrast restriction or warning is introduced. |
| TP-REQ-013 | [REQ-013](../product/requirements.md#requirement-identification) | For each shape and detail level, inspect detail 0 and every value 1-12. | Detail 0 hides extra segments and on-screen timer text but retains the visualization; values 1-12 show that count and remaining-time text; representations do not use a clock face or time of day. |
| TP-REQ-014 | [REQ-014](../product/requirements.md#requirement-identification) | Exercise preview, auto-save, app reopen, invalid/stale storage, storage read failure, save failure, and zero duration. | Valid changes preview and persist without Save; reopen restores settings but not a running countdown; invalid/stale values use defaults; storage failures are visibly reported. |
| TP-REQ-015 | [REQ-015](../product/requirements.md#requirement-identification) | Resize Settings across compact and expanded layouts and inspect labels, focus, semantics, and clipping. | Settings remains reachable, uses a side pane where supported, and required controls remain visible and labeled at each browser configuration checked. |

## Execution record

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
#66 remain open; their proposed Settings/Timer behavior is not verified by this
candidate. Human design, usability, accessibility, and product review are
pending.

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
