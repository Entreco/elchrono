# Product requirements

Settings appearance review: [PR #76](https://github.com/Entreco/elchrono/pull/76).

Desktop keyboard changes: [PR #73](https://github.com/Entreco/elchrono/pull/73).

| Field | Value |
|---|---|
| Document ID | M2-DOC-001 |
| Revision | 1.4 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Product requirements and accessibility: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#22](https://github.com/Entreco/elchrono/issues/22) |
| Review PR | Prior baseline: [PR #32](https://github.com/Entreco/elchrono/pull/32); prior review: [PR #41](https://github.com/Entreco/elchrono/pull/41); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); issue #67: [PR #68](https://github.com/Entreco/elchrono/pull/68); issue #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); status-indicator follow-up: [draft PR #74](https://github.com/Entreco/elchrono/pull/74); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This is the candidate requirements register for El Chrono's proposed
non-medical visual support for ordinary waiting. It records the product
requirements that guide design and implementation. Acceptance criteria and
verification procedures belong in the test protocol; requirement, risk,
design, usability, and test relationships belong in the traceability matrix.
Issues #47, #65, and #67 provide the browser shell, Settings, and About
implementation candidates against this proposed requirements baseline. Issue
#66 completes the Timer presentation. Product-level verification, usability,
accessibility, offline behavior, and platform support are not established by
builds or domain tests.

The [intended-use record, M1-DOC-002 revision 1.0](intended-use.md) is a
candidate whose review/approval gaps remain open. This register does not
approve that source record, resolve its gaps, expand the intended use, or claim
medical-device qualification, legal conformance, or certification.
Requirement-to-source and requirement-to-risk links are maintained in the
requirements traceability matrix. Resolve prerequisite review and
applicability gates before relying on this register for affected work or
closing issue #22.

## Requirement identification

Requirement IDs use the form `REQ-NNN`. Earlier revisions used the
`ELC-REQ-NNN` prefix; this revision drops `ELC` while retaining the numeric
suffixes. IDs are stable across revisions and retired IDs are not reused.
REQ-007 (previously ELC-REQ-007) was retired before approval. The requirements
in this revision incorporate the product decisions recorded below; their
exact-revision review and acceptance are recorded in the current review PR.

| ID | Requirement |
|---|---|
| REQ-001 | A caregiver shall be able to configure every whole-minute waiting duration from 00:00 through 24:00 using two numeric fields, Hours and Minutes, populated by sequential digits. The first digit enters the minute ones place, the second the minute tens place, and each subsequent digit shifts the existing digits left, moving them into the hour field. Mobile targets shall accept system-keyboard input when a keyboard is available and provide an on-screen numeric keypad otherwise. Desktop shall accept physical-keyboard duration entry without the app's on-screen keypad. Browser and TV targets shall use the on-screen numeric keypad and shall not expose keyboard-based duration entry. |
| REQ-002 | Valid settings changes shall be saved automatically with no explicit Save action. Opening Settings while a countdown is running shall pause it. Closing Settings shall start a fresh countdown at the configured duration; 00:00 shall remain an explicitly idle, non-counting, non-animating state and shall not be represented as a completed countdown. In v1, no separate manual pause, resume, or reset controls shall be provided. |
| REQ-003 | A patient shall be able to distinguish the timer's running, paused, and interrupted states and understand its remaining-time presentation. State and progress meaning shall not rely on color alone; interruption shall communicate that progress may be stale. Each available visualization shall specify whether it shows numeric remaining time, visual progress, or both; no visualization shall show a clock face or time of day. |
| REQ-004 | The product shall provide Main, Settings, and About screens. Main shall contain the selected timer visualization; Settings shall provide controls for duration and theme and appear as a supporting side pane alongside Main when the available window supports multiple panes, with a reachable single-pane destination on compact windows; About shall contain the company logo, actual version/build details, support information, privacy policy, relevant licences, intended use, and limitations. Unavailable owner-controlled content shall be recorded as unresolved, not fabricated or treated as satisfying this requirement. Music integration and in-app volume controls are excluded from v1. |
| REQ-005 | In v1, Settings shall not require a caregiver-protection mechanism. |
| REQ-006 | A caregiver shall be able to select one of the four v1 themes available in Settings: Clock, Battery, Fan, and Ring. |
| REQ-008 | Timer operation and remaining-time presentation shall not depend on internet connectivity. Music integration and associated playback-failure handling are deferred to v2 and are not v1 capabilities. |
| REQ-009 | Product screens and user-facing claims shall describe ordinary waiting-time support only; they shall not present El Chrono as a clock, deadline guarantee, medical function, or safety-critical timer. |
| REQ-010 | When the countdown reaches zero, the timer shall reset immediately to the configured duration and shall not display a completed timer state. If optional music is introduced in v2, playback shall stop at zero. |
| REQ-011 | Android TV and Apple TV are primary v1 product targets. Their user-facing flows shall be operable through the target platform's remote/controller focus model, with a visible focus indicator, predictable navigation, readable content at ordinary viewing distance, and no interaction that relies on colour alone. Platform support is not claimed until the target is configured and the approved platform verification is passed. |
| REQ-012 | Settings shall allow caregivers to configure background, foreground, and text colors, font, and an integer detail level from 0 through 12 without contrast restrictions or warnings. The selected background shall cover the full app-owned area across Main, Settings, About, app bars, and navigation gutters. Settings shall reflect applicable colors and font immediately; its Detail slider shall hide track dots at zero while retaining its discrete values and accessible label and value. Ring shall additionally offer thickness from thin to solid fill, with its control shown only for Ring. Shape changes and temporary control hiding shall preserve all selected values. |
| REQ-013 | At detail 0, extra segment boundaries, Clock duration labels, and all on-screen timer text, including state labels and interruption warnings, shall be hidden; no running or paused indicator shall appear, while the interrupted and idle cues remain visible. At detail 1 through 12, show non-interactive play/pause state indicators at the bottom right for running/paused respectively. Never show textual running/paused status. Accessible descriptions shall identify running, paused, and interrupted states and explain that interrupted progress may be stale. At detail 1 through 12, Clock shall show that number of evenly spaced duration labels around its continuous disk, with no segment boundaries; other shapes shall show that number of equal visual segments, including one continuous segment at level 1. Clock labels shall show whole seconds for configured durations below ten minutes and compact minutes otherwise, with a unit label. Remaining-time text is shown at detail 1 through 12. No treatment shall add clock-face markings or time-of-day information. |
| REQ-014 | Valid Settings changes, including duration and Ring thickness, shall preview immediately in the Timer while Settings is open and shall persist after app close and reopen. Duration preview shall show the full selected duration without advancing or mutating the paused countdown; invalid drafts shall retain the last valid preview and display the input error. Stored invalid or stale data shall fall back safely to defaults with a surfaced error; legacy settings shall retain their selections when thickness is introduced. Storage failures shall be surfaced rather than presented as successful saves. Persistence shall not imply recovery of a previously running countdown. |
| REQ-015 | Settings shall remain reachable on compact layouts and use a supporting pane beside the Timer where space permits. Its panel shall use a subtle Liquid Glass treatment over the selected global background regardless of Timer shape or detail, with a translucent fallback where the effect is unavailable and readable controls. Controls shall have labels and applicable browser, keyboard, touch, and assistive-technology semantics without clipping. |

Changing detail shall add or remove decorations without changing any base
shape's center or bounds at otherwise identical inputs (REQ-003/REQ-013).
Battery shall retain its shallow horizontal silhouette, visible outline,
inset fill, and external terminal; Ring shall retain its outer radius across
thickness changes (REQ-003/REQ-006/REQ-012). Visible state indicators
shall occupy the Timer pane's bottom-right corner and remain non-interactive;
this does not introduce manual timer controls or change D-06.
At detail 0, running and paused have no distinct visible state indicator.
This candidate does not establish that REQ-003 is met for sighted users at
that level. Product, accessibility/usability, and risk review must resolve
this gap; accessible descriptions alone are not equivalent for all users.

## Product decisions and scope

The product-owner decisions below define the v1 scope. D-01 through D-06 were
provided on 2026-11-17. On 2026-11-19, the product owner approved the four
demo visualizations for v1, designated Android TV and Apple TV as primary
product targets with Wear OS and watchOS outside v1, and specified adaptive
Settings presentation and target-specific duration input methods. These
decisions do not establish representative-user preference, platform support,
or verification. Reassess affected risks and requirements before v2 or any
change to intended use.

| ID | Decision | Current disposition |
|---|---|---|
| D-01 | Caregiver protection mechanism | Product owner accepts the risk of unprotected Settings for v1; Settings may be accessed by patients. No control or risk reduction is claimed. RISK-004 disposition is documented in the risk assessment. |
| D-02 | Volume controls and limits | No in-app volume controls are included in v1. Reassess if audio is introduced in a later version; this decision does not promise volume controls in v2. |
| D-03 | Music start behavior and completion sounds | Music integration is deferred to v2; no music start behavior or in-app playback is needed in v1. No completion sound is included in v1; v2 audio behavior requires separate review and requirements. |
| D-04 | Timer start, Settings opening, and saving | Saving valid Settings starts a countdown from the full saved duration. Opening Settings while running pauses the countdown; saving starts a new countdown from the full saved duration rather than resuming the prior remaining time. |
| D-05 | Interruption detection and recovery | When a configured app receives `ON_STOP` while the timer is running, it pauses and presents an interrupted state; it shall not resume automatically on `ON_START`. Saving valid Settings is required to start a new countdown from the full saved duration. No timer state recovery across process termination or device restart is provided or promised. Platform lifecycle delivery and the resulting UX remain subject to verification and risk review. |
| D-06 | Manual pause, resume, and reset controls | No separate manual pause, resume, or reset controls are included in v1. Opening Settings while running is the selected pause behavior; saving valid Settings is the only selected start/restart action and starts from the full saved duration. |
| D-07 | Primary platforms and v1 exclusions | Android TV and Apple TV are the primary product targets. Wear OS and watchOS are outside v1 scope. The browser remains the M5 implementation/verification target; its completion does not establish TV support. TV support requires separate target configuration, packaging, remote/focus, viewing-distance, lifecycle, accessibility, and usability verification. |
| D-08 | V1 visualization set | The v1 theme choices are Clock, Battery, Fan, and Ring, as shown in the approved demo. Clock is the initial selection, matching the demo; this default is not evidence of user preference. The visual mapping and use of each theme still require application verification and appropriate usability/accessibility review. |
| D-09 | Adaptive Settings and duration input methods | Show Settings in a supporting side pane when the available window supports multiple panes and as a reachable single-pane destination on compact windows. Mobile may use an available system keyboard, with the on-screen keypad as fallback. Desktop uses keyboard entry without the app keypad; browser and TV use the on-screen keypad without keyboard-based duration entry. This does not establish support for unconfigured TV targets. |
| D-10 | Text-free Timer at detail 0 | Issue #66 carries the owner-requested behavior that detail 0 hides every on-screen timer text, including interruption warnings, while retaining distinct non-text state cues and accessible state/stale-progress descriptions. The revised interrupted-warning design and its risk/usability implications require human review; no equivalent accessibility or effectiveness is presumed. |
| D-11 | Issue #66 appearance refinements | The owner requested global edge-to-edge background, translucent Settings, bottom-right icon, immediate duration preview, demo Battery geometry, stable detail overlays, and Ring thickness. The implementation plan uses translucency without required blur, preserves the non-interactive cue, and proposes normalized thickness with the existing nominal default. Plan approval permits implementation; final visual/accessibility/usability/risk and exact-head acceptance remain pending. |

Requirement REQ-007 was retired before approval at the product owner's
direction; it must not be reused. No WCAG conformance baseline is selected by
this register. This does not remove applicable accessibility laws or the
project's general accessibility and usability expectations.

The test protocol, not this register, defines acceptance criteria and planned
verification. The requirements traceability matrix maintained under
[issue #25](https://github.com/Entreco/elchrono/issues/25) is the authoritative
record linking requirements to intended use, risks, design/usability evidence,
and test cases/results. No implementation, verification result,
representative-user finding, or requirement approval is claimed by this
candidate.

Legal accessibility applicability remains unresolved in
[M1-DOC-006](../governance/applicability-matrix.md). This register selects no
accessibility standard or conformance baseline. Accessibility and usability
review under the project's general requirements remains necessary before
approval and implementation.

Other evidence gaps include exact-revision approval of the intended-use record,
the required review of the risk assessment, appointment of competent
requirements/accessibility reviewers, and the review/verification needed for
these product decisions. In particular, the product owner's D-01 disposition
does not replace qualified review or formally determine RISK-004
acceptability. Keep these gaps visible; issue closure or document lifecycle
does not establish that they were resolved.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish the initial proposed requirements and explicit open decisions for issue #22; define the ELC-REQ-NNN identifier scheme and record initial accessibility criteria as a voluntary project baseline without claiming legal applicability or conformance. |
| 0.2 | Make duration entry whole-minute and digit-based; clarify caregiver start, patient visibility, Settings access, and theme selection; propose immediate timer reset and music stop at completion; retire ELC-REQ-007 and remove the WCAG baseline at the product owner's direction. |
| 0.3 | Use REQ-NNN identifiers and keep the requirements register to requirement statements; assign acceptance criteria to the test protocol and requirement-risk-test links to the traceability matrix. |
| 0.4 | Record the v1 product decisions for unprotected Settings, no volume controls, v2 music deferral, save-to-start/settings-open pause behavior, and no interruption recovery; retain competent review and verification gaps. |
| 0.5 | Resolve the v1 manual pause/resume/reset decision: no separate controls; Settings-open pauses and valid Save starts from the full saved duration. |
| 0.6 | Add the owner-approved Clock, Battery, Fan, and Ring v1 theme set, define Clock as the demo-matching initial selection, designate Android TV and Apple TV as primary targets and Wear/watch as outside v1, and retain separate verification/support gates. |
| 0.7 | Clarify the existing in-process interruption behavior for common lifecycle stop/start events: pause and mark interrupted on stop, do not automatically resume, and retain no process-termination or restart recovery; keep platform verification and risk review pending. |
| 0.8 | Add the owner-directed supporting-pane Settings layout and target-specific duration-input methods; retain sequential digit semantics and keep layout, keyboard, accessibility, and TV verification pending. |
| 0.9 | Add zero-duration, automatic-save, complete personalization, detail 0-12, persistence, and compact/adaptive Settings requirements for issue #65. |
| 1.0 | Clarify idle zero-duration and Timer-state requirements; record issue #66's text-free detail-0 state cues and stale-progress semantics, with review and verification pending. |
| 1.1 | Align Clock detail levels with the demo's outer duration labels rather than segments; require a continuous Clock fill and specify duration units while retaining equal segment divisions for Battery, Fan, and Ring. |
| 1.2 | Extend issue #66 appearance scope to global background, translucent Settings, immediate duration preview, stable detail geometry, shallow Battery, and Ring thickness; preserve manual-control and review boundaries. |
| 1.3 | Replace desktop keypad-only duration entry with physical-keyboard entry; retain mobile, browser, and TV input policies and pending verification. |
| 1.4 | Specify live Settings appearance, detail-zero slider dots, and an always-on subtle glass treatment with platform fallback; retain accessibility and platform review gaps. |
