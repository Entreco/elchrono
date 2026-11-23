# Product requirements

Settings appearance review: [PR #76](https://github.com/Entreco/elchrono/pull/76).

Desktop keyboard changes: [PR #73](https://github.com/Entreco/elchrono/pull/73).

| Field | Value |
|---|---|
| Document ID | M2-DOC-001 |
| Revision | 1.9 |
| Current change review | [PR #78](https://github.com/Entreco/elchrono/pull/78); human acceptance and required specialist review pending |
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

The [intended-use record, M1-DOC-002 revision 1.1](intended-use.md) is a
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
| REQ-002 | Valid Settings changes shall be saved automatically without Save. Opening Settings pauses a running countdown and retains its remaining time; closing with the close icon or Back resumes only if it was running on entry and no interruption revoked that intent. An actual valid duration change immediately resets the engine, paused, to the new full duration while Settings is open; closing resumes that new duration only if the prior running intent remains valid and the new duration is positive. Appearance-only edits, unchanged values and invalid drafts shall not reset it. A manually paused timer stays paused; an unchanged finished timer stays finished at zero; a duration change after finish prepares a paused timer requiring Resume. Initial positive configuration shall offer Resume without auto-start. 00:00 always presents idle and non-counting with no enabled timer action, even when interruption is retained internally; its warning is not shown at zero. After positive duration reconfiguration, the warning returns and explicit Restart is required to clear interruption and begin the full duration. |
| REQ-003 | A patient shall be able to distinguish running, paused, finished, idle, and interrupted states and understand remaining time without relying on color alone. Interrupted progress shall communicate that it may be stale. Visualizations shall specify numeric remaining time, visual progress, or both, and shall not show a clock face or time of day. State/action comprehension, including text-free detail 0, requires accessibility and usability review. |
| REQ-004 | The product shall provide Main, Settings, and About screens. Main shall contain the selected timer visualization; Settings shall provide controls for duration and theme and appear over the Timer on compact and expanded windows; About shall contain the company logo, actual version/build details, support information, privacy policy, relevant licences, intended use, and limitations. Unavailable owner-controlled content shall be recorded as unresolved, not fabricated or treated as satisfying this requirement. Music integration and in-app volume controls are excluded from v1. |
| REQ-005 | In v1, Settings shall not require a caregiver-protection mechanism. |
| REQ-006 | A caregiver shall be able to select one of the four v1 themes available in Settings: Clock, Battery, Fan, and Ring. |
| REQ-008 | Timer operation and remaining-time presentation shall not depend on internet connectivity. Music integration and associated playback-failure handling are deferred to v2 and are not v1 capabilities. |
| REQ-009 | Product screens and user-facing claims shall describe ordinary waiting-time support only; they shall not present El Chrono as a clock, deadline guarantee, medical function, or safety-critical timer. |
| REQ-010 | A positive countdown reaching zero shall stop at zero in a distinct finished state, never automatically restart, and report completion exactly once without negative remaining time. Restart shall begin a fresh countdown at the configured full duration. |
| REQ-011 | Android TV and Apple TV are primary v1 product targets. Their user-facing flows shall be operable through the target platform's remote/controller focus model, with a visible focus indicator, predictable navigation, readable content at ordinary viewing distance, and no interaction that relies on colour alone. Platform support is not claimed until the target is configured and the approved platform verification is passed. |
| REQ-012 | Settings shall allow caregivers to configure background, foreground, and text colors, font, and an integer detail level from 0 through 12 without contrast restrictions or warnings. The selected background shall cover the full app-owned area across Main, Settings, About, app bars, and navigation gutters. Settings shall reflect applicable colors and font immediately; its Detail slider shall hide track dots at zero while retaining its discrete values and accessible label and value. Ring shall additionally offer thickness from thin to solid fill, with its control shown only for Ring. Shape changes and temporary control hiding shall preserve all selected values. |
| REQ-013 | At detail 0, hide extra segment boundaries, Clock duration labels, and all visible Timer text, including interruption warnings; retain interrupted and idle cues. The bottom-right timer action shall be available at every detail level: Pause with a pause icon when running, Resume with a play icon when paused, and Restart when finished or interrupted. At detail 0 actions are icon-only with localized accessible names; at detail 1-12 Restart is visibly labeled. Accessible descriptions shall identify all states and stale interrupted progress. At detail 1-12, Clock shows that number of evenly spaced duration labels around its continuous disk without segment boundaries; other shapes show that number of equal visual segments, including one continuous segment at level 1. Clock labels show whole seconds for configured durations below ten minutes and compact minutes otherwise, with a unit label. Remaining-time text is shown at detail 1-12. No clock-face markings or time-of-day information shall be added. |
| REQ-014 | Valid Settings changes, including duration and Ring thickness, shall immediately update the Timer presentation behind Settings and persist after app close and reopen. The preview uses the full selected duration without advancing the engine; it need not be sharp or unobstructed. An actual valid duration change resets the engine paused as specified by REQ-002; appearance-only changes, unchanged durations and invalid drafts do not. Invalid drafts retain the last valid preview and show an input error. Invalid or stale storage falls back safely to defaults with a surfaced error; legacy selections survive thickness migration. Storage failures are surfaced, not presented as successful saves. Persistence does not imply recovery of a previously running countdown. |
| REQ-015 | The Timer shall remain a stationary full-window layer behind the app bar, Settings and About. Settings and About shall slide in from and out to the left, with About over Settings. Panels shall fill compact windows and be at most 440 dp wide in expanded windows. Apply native Timer blur where supported and a liquid-glass treatment to the panels without blurring controls; where native blur is unavailable the Timer remains unblurred. Controls shall have labels and applicable browser, keyboard, touch, and assistive-technology semantics without clipping. |

For REQ-002/003/010/014/015, orientation changes, window resizing, and Android
configuration recreation shall preserve the retained timer session, current
screen, valid settings, duration draft, and scroll position. Running time
continues against the same monotonic time source through missed UI refreshes;
recreation alone shall not pause, reset, mark interrupted, or require Restart.
A countdown expiring during recreation shall finish at zero once. This does
not add background execution or process-death/device-restart countdown recovery.

Changing detail shall add or remove decorations without changing any base
shape's center or bounds at otherwise identical inputs (REQ-003/REQ-013).
Battery shall retain its shallow horizontal silhouette, visible outline,
inset fill, and external terminal; Ring shall retain its outer radius across
thickness changes (REQ-003/REQ-006/REQ-012). Action icons identify the action,
not a decorative state indicator. Their distinctness and the interruption cue
at detail 0 remain unverified and require product, accessibility, usability,
and risk review; accessible descriptions alone are not equivalent for all users.

## Product decisions and scope

The product-owner decisions below define the v1 scope. D-01 through D-06 were
originally provided on 2026-11-17; the current D-04 through D-06 supersede
their earlier timer behavior and still require the reviews identified below.
On 2026-11-19, the product owner approved the four
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
| D-04 | Timer start, Settings opening, and saving | Settings pauses a running countdown and close/Back resumes its remaining time only when its prior running intent remains valid. A valid changed duration immediately resets the paused engine to its new full duration; closing resumes it only for that retained running intent. Appearance-only, invalid and unchanged edits do not reset. Initial positive configuration, manual pause and a duration change after finish require explicit Resume. |
| D-05 | Interruption detection and recovery | Genuine background `ON_STOP` while running, or while Settings holds running intent, marks interruption and revokes automatic resume; `ON_START` does not recover. Android configuration-change stops are excluded: rotation/recreation preserves the session and retained running intent. Resize alone never interrupts. Settings alone, including duration changes, does not clear genuine interruption. At zero show idle without the warning or an action while retaining interruption internally; positive reconfiguration restores the warning and requires explicit Restart to clear it and start the full configured duration. No process-termination or device-restart countdown recovery is promised. Lifecycle delivery and UX require verification and risk review. |
| D-06 | Manual pause, resume, and restart controls | Main offers bottom-right Pause while running, Resume while paused, and Restart when finished or interrupted; Restart begins the full configured duration. Actions are available at all detail levels with localized accessible names, icon-only at detail 0. No enabled action is offered at zero configuration. |
| D-07 | Primary platforms and v1 exclusions | Android TV and Apple TV are the primary product targets. Wear OS and watchOS are outside v1 scope. The browser remains the M5 implementation/verification target; its completion does not establish TV support. TV support requires separate target configuration, packaging, remote/focus, viewing-distance, lifecycle, accessibility, and usability verification. |
| D-08 | V1 visualization set | The v1 theme choices are Clock, Battery, Fan, and Ring, as shown in the approved demo. Clock is the initial selection, matching the demo; this default is not evidence of user preference. The visual mapping and use of each theme still require application verification and appropriate usability/accessibility review. |
| D-09 | Settings layout and duration input methods | Show Settings over the Timer at compact and expanded widths. Mobile may use an available system keyboard, with the on-screen keypad as fallback. Desktop uses keyboard entry without the app keypad; browser and TV use the on-screen keypad without keyboard-based duration entry. This does not establish support for unconfigured TV targets. |
| D-10 | Text-free Timer at detail 0 | All visible Timer text remains hidden at detail 0, including interruption warnings; action icons, interrupted/idle cues and accessible state/stale-progress descriptions remain. The changed visual distinction and warning need human accessibility, usability and risk review. |
| D-11 | Issue #66 appearance refinements | The owner requested global edge-to-edge background, bottom-right icon, demo Battery geometry, stable detail overlays, and Ring thickness. The owner selected a stationary full-window Timer with native blur and left-sliding liquid-glass Settings and About panels, and accepted that a sharp preview is not required while Settings is open. Product, accessibility, usability, risk and exact-head review of this change remain pending. |

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
| 1.5 | Replace the Settings side pane and sharp-preview requirement with a native-blurred Timer backdrop and translucent overlay; retain unblurred fallback and review gaps. |
| 1.6 | Specify stationary full-window Timer and left-sliding glass Settings and About layers; retain platform and review gaps. |
| 1.7 | Supersede timer, Settings and interruption decisions with one-shot completion and explicit controls; retain review and verification gaps. |
| 1.8 | Specify zero-duration idle priority and retained interruption with warning restored after positive reconfiguration; retain review gaps. |
| 1.9 | Distinguish configuration recreation from genuine background interruption; require retained session and UI continuity without expanding restart recovery. |
