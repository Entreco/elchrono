# Product test protocol

Settings appearance review: [PR #76](https://github.com/Entreco/elchrono/pull/76).

Desktop keyboard changes: [PR #73](https://github.com/Entreco/elchrono/pull/73).

| Field | Value |
|---|---|
| Document ID | M2-DOC-002 |
| Revision | 1.7 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Test and product requirements review: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#25](https://github.com/Entreco/elchrono/issues/25) |
| Review PR | Prior baselines: [PR #27](https://github.com/Entreco/elchrono/pull/27), [PR #31](https://github.com/Entreco/elchrono/pull/31), [PR #32](https://github.com/Entreco/elchrono/pull/32), and [PR #41](https://github.com/Entreco/elchrono/pull/41); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); #67 candidate: [draft PR #68](https://github.com/Entreco/elchrono/pull/68); #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); Settings foreground: [PR #72](https://github.com/Entreco/elchrono/pull/72); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This protocol contains planned test procedures and acceptance criteria for
[M2-DOC-001, revision 1.4, Product requirements](../product/requirements.md).
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
| TP-REQ-001 | [REQ-001](../product/requirements.md#requirement-identification) | Enter representative digit sequences through the mobile system-keyboard route, desktop physical keyboard in each field, and on-screen keypad route. Test desktop focus, keyboard navigation, deletion, selection/replacement, non-digit input, and keypad absence at compact and expanded sizes. Confirm browser and TV use the keypad without keyboard-based entry. Test duration boundaries, invalid values, and all whole-minute durations with a controllable input/test harness. | All input routes preserve the sequential mapping: `2`, `20`, `205`, and `2400` display as `00:02`, `00:20`, `02:05`, and `24:00`. Every duration from `00:01` through `24:00` is representable. Minute values above `59` and values above `24:00` cannot be configured as valid durations. On mobile, keypad visibility follows keyboard availability; desktop fields accept physical keyboard input without the app keypad; browser and TV use the keypad and do not expose keyboard-based duration entry. The existing conflict over whether `00:00` is valid remains unresolved; do not mark this test fully passed until the owner reconciles REQ-001, REQ-002, and the 1-minute minimum in project instructions. |
| TP-REQ-002 | [REQ-002](../product/requirements.md#requirement-identification) | Inspect Main and Settings for manual timer controls. Save valid settings and advance the injected time source. While running, open Settings and record remaining time; keep Settings open while advancing time, then save valid settings. | No separate manual pause, resume, or reset controls are available in v1. Saving starts the countdown from the full saved duration. Opening Settings pauses it (remaining time does not change while Settings is open). Saving again starts a new countdown from the full saved duration, not the previously remaining time. |
| TP-REQ-003 | [REQ-003](../product/requirements.md#requirement-identification) | For Clock, Battery, Fan, and Ring, review running, paused, interrupted, and idle states at detail 0 and 1-12. Use a controllable time source for progress boundaries. In the browser, trigger hidden/return transitions and Settings navigation; inspect representative compact and expanded layouts and screen-reader output. | All four themes use the same continuous remaining-time fraction and remain fully visible without clipping. Clock's leading edge moves clockwise from 12 toward the right as time elapses; detail N shows N evenly spaced duration labels and no internal boundaries (for example, a 60-minute duration at detail 2 shows 0 at the top and 30 at the bottom). Durations below ten minutes use whole-second labels; longer durations use compact minute values and a unit label. Fan's complete semicircle is centered and visible at compact and expanded sizes. No clock face/time-of-day markings are added; states are distinct without relying on color. At detail 0, no Timer text is visible; interrupted and idle cues and accessible descriptions identify state and stale progress. Running and paused have no visible state indicator at detail 0: the candidate cannot currently establish the required visual distinction and this criterion remains unresolved. At detail 1-12, play/pause icons identify running/paused without text; interruption warning and remaining time remain visible. Hidden-page interruption does not resume automatically; valid Settings changes start a fresh countdown. Process-termination/restart recovery is not claimed. Human design, accessibility, usability, and risk review remain required. |
| TP-REQ-004 | [REQ-004](../product/requirements.md#requirement-identification) | Inspect Main, Settings, and About against their required v1 content. At expanded widths confirm Settings and About use the supporting pane beside Timer; at compact widths confirm each is a reachable single-pane destination. Resize across the breakpoint while About is open. Verify the official logo's scaling and accessible name, actual version/build metadata, approved support/privacy/licence information and link destinations, intended-use/limitations copy, navigation back to Settings and Timer, keyboard operation, and screen-reader labels. | Main includes the selected timer visualization. Settings uses the supporting pane when space permits and remains available on compact windows; duration and theme controls are present, with no v1 music or volume controls. About contains all REQ-004 content from authoritative sources; the logo preserves its aspect ratio and is accessible, links work, navigation is correct, and content is readable and not clipped or obscured. A missing source or required item is explicitly recorded as a gap and makes this test blocked, not passed. |
| TP-REQ-005 | [REQ-005](../product/requirements.md#requirement-identification) | Open Settings without a gate as a patient and change/save duration or theme. | Settings is not protected by a caregiver gate in v1 and permits the access specified by the product decision. The test verifies this behavior; it does not establish that the resulting risk is acceptable or controlled. |
| TP-REQ-006 | [REQ-006](../product/requirements.md#requirement-identification) | Select Clock, Battery, Fan, and Ring in Settings and inspect each timer presentation. | Exactly the four owner-approved v1 themes are available, each can be selected and becomes the selected timer theme, and each preserves the shared remaining-time mapping. Do not assert user preference without usability evidence. |
| TP-REQ-008 | [REQ-008](../product/requirements.md#requirement-identification) | For v1, run the timer with network unavailable. Defer audio-failure and playback checks until a v2 music integration is approved and implemented. | Timer operation and remaining-time presentation do not depend on network connectivity. No v1 music integration or playback-failure behavior is claimed. The v2 audio criteria require review before that scope begins. |
| TP-REQ-009 | [REQ-009](../product/requirements.md#requirement-identification) | Review application screens, About content, and external product claims against intended-use exclusions. | No content presents the app as a clock, guarantees a deadline, makes medical claims, or presents it for safety-critical timing. Conflicts in intended purpose, functionality, claims, or markets are escalated before affected work continues. |
| TP-REQ-010 | [REQ-010](../product/requirements.md#requirement-identification) | With a controllable time source and no v1 audio, advance the countdown to zero. | The timer immediately resets to the configured duration, displays no completed state, handles completion once, and does not become negative. Stopping optional music is deferred to v2 and is not a v1 test result. |
| TP-REQ-011 | [REQ-011](../product/requirements.md#requirement-identification) | On each configured target, operate Main, Settings, and About using its supported remote/controller focus model; verify visible focus, predictable navigation, readability at ordinary viewing distance, and non-colour-only cues. Attempt target packaging and lifecycle checks. | Android TV and Apple TV controls are operable without touch, focus is visible and predictable, and content remains readable and complete at the agreed viewing distance. Record target configuration and verification before claiming support; an unconfigured target or unavailable device makes the test blocked, not passed. |
| TP-REQ-012 | [REQ-012](../product/requirements.md#requirement-identification) | Enumerate detail levels 0-12 in both directions and select colors/fonts across shape changes and temporary hidden controls. Inspect Settings text, input font, accents and Detail track at zero and nonzero; exercise pointer, keyboard and assistive-technology value changes. For each foreground preset inspect keypad buttons and focused fields, including a change while a duration draft is invalid. Inspect matching and low-contrast foreground/background combinations. | Every detail level and color/font choice is reachable, shape changes do not erase selections, and no contrast restriction or warning is introduced. Applicable Settings colors and font update immediately. At zero the Detail track has no ticks or end dot but retains the thumb, label, value and discrete range; above zero its marks return. Keypad text has at least 4.5:1 contrast against its button and independent keyboard focus remains available. Record visibility limitations for low-contrast combinations and obtain human accessibility and risk review; do not treat unrestricted colors as proof of contrast. |
| TP-REQ-013 | [REQ-013](../product/requirements.md#requirement-identification) | For every shape, enumerate detail 0-12 and verify shape-specific marker count/geometry, text visibility, and accessible state semantics. Include detail 1 explicitly and inspect the zero-duration state. | Detail 0 has no extra boundaries, Clock duration labels, or on-screen Timer text; it retains the base visualization and state cue and exposes accessible descriptions including stale progress. At Clock detail N from 1-12, N evenly spaced duration labels surround a continuous disk with no internal boundaries; for 60 minutes at detail 2, labels are 0 at the top and 30 at the bottom. Short durations use whole seconds; longer durations use compact minutes and one unit label. Battery, Fan, and Ring use N-1 equal boundaries to form N segments; detail 1 is one continuous segment. The Fan semicircle is centered and not clipped at representative compact and expanded sizes. Remaining-time text is shown at levels 1-12. 00:00 is idle/non-counting/non-animating and has no progress/completion semantics. |
| TP-REQ-014 | [REQ-014](../product/requirements.md#requirement-identification) | Exercise preview, auto-save, app reopen, invalid/stale storage, storage read failure, save failure, and zero duration. | Valid changes preview and persist without Save; reopen restores settings but not a running countdown; invalid/stale values use defaults; storage failures are visibly reported. |
| TP-REQ-015 | [REQ-015](../product/requirements.md#requirement-identification) | Resize Settings across compact and expanded layouts and inspect labels, focus, semantics, and clipping. Check every shape and detail zero for a subtle glass surface, readable controls, and a translucent fallback on platforms without the shader. | Settings remains reachable, uses a side pane where supported, and required controls remain visible and labeled at each browser configuration checked. The glass remains independent of shape and detail and does not distort controls; fallback behavior is recorded for each renderer. |

## Execution record

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
  Clock labels, seconds/minutes units, full fill, and remaining text must update
  before closing. The paused engine must not advance/reset; invalid drafts
  must retain the last valid preview and show an error.
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
