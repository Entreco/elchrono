# Requirements traceability matrix

Settings appearance review: [PR #76](https://github.com/Entreco/elchrono/pull/76).

Desktop keyboard changes: [PR #73](https://github.com/Entreco/elchrono/pull/73).

| Field | Value |
|---|---|
| Document ID | M2-DOC-003 |
| Revision | 4.6 |
| Current change review | [PR #78](https://github.com/Entreco/elchrono/pull/78); human acceptance and required specialist review pending |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Requirements and software-safety review: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#25](https://github.com/Entreco/elchrono/issues/25); provider-feasibility trace update in [#36](https://github.com/Entreco/elchrono/issues/36); platform architecture trace update in [#39](https://github.com/Entreco/elchrono/issues/39) |
| Review PR | Prior baselines: revision 1.4 in [PR #29](https://github.com/Entreco/elchrono/pull/29), revision 1.7 in [PR #30](https://github.com/Entreco/elchrono/pull/30), revision 1.8 in [PR #31](https://github.com/Entreco/elchrono/pull/31), revision 2.2 in [PR #32](https://github.com/Entreco/elchrono/pull/32), revision 2.3 in [PR #41](https://github.com/Entreco/elchrono/pull/41), revision 2.4 in [PR #49](https://github.com/Entreco/elchrono/pull/49), and revision 2.5 in [PR #54](https://github.com/Entreco/elchrono/pull/54); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); #67 candidate: [draft PR #68](https://github.com/Entreco/elchrono/pull/68); #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); Settings foreground: [PR #72](https://github.com/Entreco/elchrono/pull/72); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and use

This matrix is the authoritative cross-reference between the intended-use
source, product requirements, risks, design/usability evidence, and test
protocol. Requirement statements live in
[M2-DOC-001, revision 1.8](../product/requirements.md); acceptance criteria and
procedures live in [M2-DOC-002, revision 2.5](../testing/test-protocol.md).
Do not duplicate those statements or criteria here.

The mappings below are initial and proposed. Design links are included when
records exist; usability findings and verification status remain pending until
actual evidence is recorded.

| Source record | Revision | Relationship |
|---|---|---|
| [M1-DOC-002, intended use](../product/intended-use.md) | 1.1 candidate | Product purpose, roles, use, and limitations |
| [M2-DOC-001, product requirements](../product/requirements.md) | 1.8 candidate | Stationary Timer and sliding glass overlays; product review pending |
| [M1-DOC-004, risk assessment](risk-assessment.md) | 1.11 candidate | RISK-001 through RISK-006 traced below; review pending; no control effectiveness or risk reduction claimed |
| [M1-DOC-009, risk traceability matrix](risk-traceability-matrix.md) | 1.10 candidate | Bounded JVM evidence and planned verification; review pending |
| [M2-DOC-002, product test protocol](../testing/test-protocol.md) | 2.5 candidate | Bounded one-shot evidence and approved zero idle priority; full protocol acceptance pending |
| [M2-DOC-004, accessible screen flows and theme prototypes](../design/accessible-screen-flows-and-theme-prototypes.md) | 3.2 candidate | Settings appearance, remaining-time glass and stationary Timer; exact-head review pending |
| [M2-DOC-005, usability review plan](../usability/usability-plan.md) | 1.2 candidate | Future refinement review scope; no participant session authorized |
| [M2-DOC-006, usability findings and evidence status](../usability/usability-findings.md) | 1.2 candidate | Retrospective sessions and extended G06 redesign gap |
| [M3-DOC-007, platform architecture decision and support matrix](../architecture/platform-architecture-and-support-matrix.md) | 0.3 candidate | Retains the configured Kotlin Multiplatform/Compose architecture; records TV priorities, Wear/watch exclusion, evidence limits, and no support commitment |

## Traceability

| Requirement | Intended-use source | Applicable risks | Design/usability evidence | Test protocol | Verification evidence |
|---|---|---|---|---|---|
| [REQ-001](../product/requirements.md#requirement-identification) | [Duration and supported waiting use](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | RISK-001 | [Settings duration entry](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); planned [U-03](../usability/usability-plan.md#tasks-and-neutral-prompts); session scope [M2-DOC-006](../usability/usability-findings.md#session-scope-and-evidence-provenance), gap G04 | [TP-REQ-001](../testing/test-protocol.md#test-cases) | JVM duration-entry, policy, and desktop Compose interaction tests cover sequential text edits and Backspace. Actual physical-keyboard entry, focus, layout, accessibility, and the zero-duration conflict remain unverified. The Wasm settings test runner previously failed during Skiko WebAssembly instantiation as recorded in M2-DOC-002. |
| [REQ-002](../product/requirements.md#requirement-identification) | [Users and roles](../product/intended-use.md#users-population-and-roles) | RISK-001, RISK-002, RISK-004, RISK-005 | [Settings lifecycle and manual actions](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); usability gaps G03/G04/G06 | [TP-REQ-002](../testing/test-protocol.md#test-cases) | Operator-reported passing JVM tests cover retained time, actual duration reset, manual pause, finished/initial state, About and lifecycle-stop intent. Zero idle priority and warning restoration require full platform/human review; see [execution record](../testing/test-protocol.md#one-shot-timer-and-manual-controls-candidate). |
| [REQ-003](../product/requirements.md#requirement-identification) | [Remaining-time use](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | RISK-001, RISK-002, RISK-005, RISK-006 | [Main states](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states); gaps G04-G06 | [TP-REQ-003](../testing/test-protocol.md#test-cases) | Reported JVM state mapping and Compose interaction tests cover several action/state semantics. Zero after interruption renders idle without a warning; full visual matrix, screen-reader, device and human review remain pending. |
| [REQ-004](../product/requirements.md#requirement-identification) | [Users and roles](../product/intended-use.md#users-population-and-roles); screen/content inventory is M2 product scope in [#22](https://github.com/Entreco/elchrono/issues/22) | RISK-004, RISK-005, RISK-006 | [Main, Settings, and About designs](../design/accessible-screen-flows-and-theme-prototypes.md#screen-flows); Settings overlay; owner-supplied logo; unavailable content remains a blocker; no v1 audio/volume controls | [TP-REQ-004](../testing/test-protocol.md#test-cases) | Prior About and browser checks do not verify the revised overlay. Required version/build, support, privacy, and licence sources remain unavailable, so TP-REQ-004 is blocked. Browser rendering, links, responsive no-clipping, keyboard, screen-reader, and integrated behavior remain unverified. See the #67 candidate execution record in M2-DOC-002. |
| [REQ-005](../product/requirements.md#requirement-identification) | [Users and roles](../product/intended-use.md#users-population-and-roles) | RISK-004 | [Unprotected Settings v1 decision](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states); prior sessions did not test access paths (G03/G04) | [TP-REQ-005](../testing/test-protocol.md#test-cases) | Pending; test does not establish risk acceptability |
| [REQ-006](../product/requirements.md#requirement-identification) | No direct theme-selection statement in intended use; this product-level choice is scoped by [#22](https://github.com/Entreco/elchrono/issues/22) | RISK-004, RISK-005 | [Four owner-approved timer visualizations](../design/accessible-screen-flows-and-theme-prototypes.md#timer-visualizations); session findings [F01-F04](../usability/usability-findings.md#participant-feedback-and-observations); gaps G03/G04 | [TP-REQ-006](../testing/test-protocol.md#test-cases) | Pending; user preference and application/platform behavior are not inferred |
| [REQ-008](../product/requirements.md#requirement-identification) | [Offline use](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio); future audio scope deferred | RISK-003 | Music and volume controls excluded from v1; future audio behavior requires separate review; [M3-DOC-003](../architecture/audio-provider-feasibility-findings.md) records feasibility constraints only | [TP-REQ-008](../testing/test-protocol.md#test-cases) | Network-independence test pending; v2 audio tests deferred |
| [REQ-009](../product/requirements.md#requirement-identification) | [Exclusions and claims boundary](../product/intended-use.md#exclusions-and-claims-boundary) | RISK-006 | [About screen content](../design/accessible-screen-flows-and-theme-prototypes.md#about-screen); session gap G04 | [TP-REQ-009](../testing/test-protocol.md#test-cases) | Pending |
| [REQ-010](../product/requirements.md#requirement-identification) | [Limitations](../product/intended-use.md#foreseeable-use-and-limitations) | RISK-001, RISK-005 | One-shot finished at zero and explicit Restart; usability gaps G04/G06 | [TP-REQ-010](../testing/test-protocol.md#test-cases) | Operator-reported passing JVM tests exercise all 1,440 durations, one completion event, no loop and full Restart, plus bounded Compose Restart interaction. Product, device and human review remain pending. |
| [REQ-011](../product/requirements.md#requirement-identification) | [Roles and ordinary use](../product/intended-use.md#users-population-and-roles); TV viewing/input boundaries | RISK-005, RISK-006 | [TV priority and v1 boundary](../design/accessible-screen-flows-and-theme-prototypes.md#design-principles); TV interaction and platform support remain unverified | [TP-REQ-011](../testing/test-protocol.md#test-cases) | Pending; Android TV has a candidate launcher; Apple TV is unconfigured. No TV support claim is made |
| REQ-012 | [Users and roles](../product/intended-use.md#users-population-and-roles) | RISK-004, RISK-005 | Settings personalization, 16-color palettes, distinct font choices, dot-free Ring track and dot-free detail-zero track; unrestricted-color visibility and human product/accessibility/risk review pending | TP-REQ-012 | Earlier JVM slider and browser observations do not establish acceptance of the expanded palette, new fonts or Ring slider. Platform font rendering, compact layout, keyboard/screen-reader behavior, and human review remain pending. |
| REQ-013 | [Remaining-time use](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | RISK-001, RISK-002, RISK-005 | [Detail mapping and actions](../design/accessible-screen-flows-and-theme-prototypes.md#m5-timer-scope-and-detail-mapping); G06 | TP-REQ-013 | Reported JVM Compose tests exercise actions at details 0–12, Tab/Enter, expanded overlay isolation and one compact large-font Restart; full visual matrix, screen readers and human review remain pending. |
| REQ-014 | [Users and roles](../product/intended-use.md#users-population-and-roles) | RISK-001, RISK-002, RISK-004, RISK-005 | Full-duration preview, paused engine reset only for actual duration change and retained interruption | TP-REQ-014 | Reported JVM ViewModel tests cover changed duration, preview, appearance-only preservation and storage failure. Zero idle priority and warning restoration, complete persistence/platform and human verification require review. |
| REQ-015 | [Users and roles](../product/intended-use.md#users-population-and-roles) | RISK-005 | Stationary Timer, sliding glass panels and native blur; no-clipping, fallback and assistive-tech evidence pending | TP-REQ-015 | Platform rendering, back gestures, focus and human review remain pending |

## Reverse coverage

The following earlier test observations are historical and do not verify the
new Settings-intent, changed-duration reset, explicit actions, one-shot
completion or interruption policy. The bounded current JVM results are in the
[execution record](../testing/test-protocol.md#one-shot-timer-and-manual-controls-candidate);
full verification and human review remain pending.


Issue #66 refinement evidence supplements REQ-012/013/014/015 above.
`AppColorSchemeTest` checks global-background identity and core text contrast;
`TimerPresentationTest` checks the all-detail Clock label envelope, bounded
shallow Battery geometry, and Ring centerline/inner/outer radius invariants.
An earlier `AppViewModelTest` checked duration projection without changing the
then-paused/interrupted engine; the current changed-duration reset supersedes
that behavior. `SettingsPersistenceTest` checks all thickness
values, legacy selections, and explicit malformed-data errors. These are
implementation-level checks, not human visual/accessibility/usability/risk
acceptance. See the refinement execution record in M2-DOC-002 for the exact
commands, browser evidence, and limits.

For REQ-003, REQ-012 and REQ-013, the remaining-time glass candidate is linked
to RISK-001/RISK-005 and TP-REQ-003/TP-REQ-013. The
[bounded remaining-time execution record](../testing/test-protocol.md#remaining-time-glass-candidate)
reports JVM layout and backdrop checks and two default-Clock browser views;
contrast, theme coverage on devices, error/fallback behavior and human review
remain open. This does not verify the redesigned detail-zero actions or resolve the human-review gap.

The requirement rows above are the authoritative mapping. This reverse view
checks that every source risk and planned protocol case links back to a
requirement; no unlinked risk or test currently needs a no-direct-mapping
rationale.

| Risk | Linked requirements |
|---|---|
| RISK-001 | REQ-001, REQ-002, REQ-003, REQ-010, REQ-013, REQ-014 |
| RISK-002 | REQ-002, REQ-003, REQ-013, REQ-014 |
| RISK-003 | REQ-008; REQ-010 if audio is added in v2 |
| RISK-004 | REQ-002, REQ-004, REQ-005, REQ-006, REQ-012, REQ-014 |
| RISK-005 | REQ-002, REQ-003, REQ-004, REQ-006, REQ-010, REQ-011, REQ-012, REQ-013, REQ-014, REQ-015 |
| RISK-006 | REQ-003, REQ-004, REQ-009, REQ-011 |

| Planned test | Linked requirement |
|---|---|
| TP-REQ-001 | REQ-001 |
| TP-REQ-002 | REQ-002 |
| TP-REQ-003 | REQ-003 |
| TP-REQ-004 | REQ-004 |
| TP-REQ-005 | REQ-005 |
| TP-REQ-006 | REQ-006 |
| TP-REQ-008 | REQ-008 |
| TP-REQ-009 | REQ-009 |
| TP-REQ-010 | REQ-010 |
| TP-REQ-011 | REQ-011 |
| TP-REQ-012 | REQ-012 |
| TP-REQ-013 | REQ-013 |
| TP-REQ-014 | REQ-014 |
| TP-REQ-015 | REQ-015 |

REQ-007 was retired before approval and is not an active requirement; it has
no planned test. No test result is implied by these planned links.

## Timer-domain verification record

Historical record only: these results cover the superseded save-to-start and
automatic-reset contract, not the current planned requirements.


The timer domain implements and tests only the behavior attributable to
TP-REQ-001, TP-REQ-002, and TP-REQ-010. The verification used the uncommitted
working tree on `feat/45-v1-timer-engine`; it does not establish application,
presentation, accessibility, usability, or platform-lifecycle behavior.

| Verification | Result |
|---|---|
| `./kotlin test -m timer` | Passed: 12 tests on JVM, Wasm/JS, and iOS Simulator ARM64. Android test compilation succeeded; Android runtime tests were not run. |
| `./kotlin build -m timer` | Passed: module compiled for JVM, Android, iOS ARM64, iOS Simulator ARM64, and Wasm/JS. |
| Duration range | Passed: enumerated every allowed whole-minute duration (00:01–24:00); rejected zero, out-of-range values, and fractional-minute inputs. |
| Lifecycle and completion boundaries | Passed at domain level: Settings-open pause, save-to-full-duration, exact-zero reset, no negative remainder, and no duplicate completion reporting. |

The bounded browser observation recorded under issue #66 verifies only idle
and running appearance plus Settings navigation, not the revised transitions.
Reported current JVM tests cover explicit interruption Restart and
Settings-held intent; actual lifecycle delivery and full user-facing behavior
remain unverified.
Android tests were compiled but not executed; no device/runtime verification
is claimed. These results do not resolve usability evidence gaps or establish
risk-control effectiveness.

## Findings, limitations, and decision follow-up

The following links keep usability evidence, open decisions, and planned
verification connected. Tests named here remain unrun; linked test cases do not
resolve usability evidence gaps.

| Finding or limitation | Requirements | Risks | Design/usability evidence | Planned test links | Follow-up |
|---|---|---|---|---|---|
| F01, Clock remaining-time text clipping | REQ-003, REQ-006 | RISK-001, RISK-005 | [Clock visualization](../design/accessible-screen-flows-and-theme-prototypes.md#timer-visualizations); [F01](../usability/usability-findings.md#participant-feedback-and-observations) and retained Session B screenshots; owner-reported resolution and Chrome prototype layout check at 500 × 1000 px and 1440 × 1200 px | TP-REQ-003, TP-REQ-006 | Close the specific prototype finding only for those checked layouts. The browser UI now exists, but supported layouts are undefined and product no-clipping verification remains pending. |
| F02–F04, qualitative feedback without representative preference evidence | REQ-003, REQ-006 | RISK-005 | [F02–F04](../usability/usability-findings.md#participant-feedback-and-observations); design preference and visibility remain open | TP-REQ-003, TP-REQ-006 | Preserve feedback as qualitative only; do not infer a preferred theme or generalize. Keep remaining-time content fully visible whenever shown. |
| G01–G02, retrospective protocol and evidence-provenance limitations | REQ-001–REQ-006 excluding retired REQ-007, and REQ-008–REQ-010 | RISK-001–RISK-005 | [G01/G02](../usability/usability-findings.md#evidence-gaps-and-limitations); M2-DOC-005 revision 0.6 postdates the sessions | No direct protocol test: these concern usability-study conduct and evidence provenance, not product behavior. The product tests linked to the affected requirements do not close this gap. | Retain retrospective status and missing contemporaneous evidence; obtain appropriate review before relying on these findings. Do not add unobserved details. |
| G03-G06, usability evidence gaps | G03: REQ-001–REQ-006 excluding retired REQ-007, and REQ-008–REQ-010; G04: REQ-001–REQ-005, REQ-008–REQ-010; G05: REQ-003–REQ-006; G06: REQ-002, REQ-003, REQ-010, REQ-013, REQ-014 | G03/G04: RISK-001–RISK-005; G05: RISK-005; G06: RISK-001, RISK-002, RISK-004, RISK-005 | [G03-G06](../usability/usability-findings.md#evidence-gaps-and-limitations); applicable flows in [M2-DOC-004 revision 3.1](../design/accessible-screen-flows-and-theme-prototypes.md) | G03: TP-REQ-001, TP-REQ-002, TP-REQ-003, TP-REQ-004, TP-REQ-005, TP-REQ-006, TP-REQ-008, TP-REQ-009, TP-REQ-010; G04: TP-REQ-001, TP-REQ-002, TP-REQ-003, TP-REQ-004, TP-REQ-005, TP-REQ-008, TP-REQ-009, TP-REQ-010; G05: TP-REQ-003, TP-REQ-004, TP-REQ-005, TP-REQ-006; G06: TP-REQ-002, TP-REQ-003, TP-REQ-010, TP-REQ-013, TP-REQ-014 | Keep the missing evidence explicit. Assess caregiver participation, unobserved tasks, the revised text-free states and visualization details, and supported assistive-technology/layout conditions before claiming those areas are validated. |
| D-01, no caregiver-protection mechanism in v1 | REQ-005 | RISK-004 | [Unprotected Settings](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states); product-owner disposition in [requirements](../product/requirements.md#product-decisions-and-scope) | TP-REQ-005 | Product decision recorded; competent risk review of the accepted exposure remains required. Do not credit a protection control. |
| D-02, no v1 in-app volume controls | REQ-004 | RISK-003, RISK-004 | [v1 audio/volume exclusion](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); product decision in requirements | TP-REQ-004 | Verify no v1 volume control is presented; reconsider requirements if audio scope changes. |
| D-03, music deferred to v2 | REQ-004, REQ-008, REQ-010 | RISK-003 | [v1 audio exclusion](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); [M3-DOC-003 provider feasibility](../architecture/audio-provider-feasibility-findings.md) informs future review but does not change the v1 decision | TP-REQ-008, TP-REQ-010 | Audio tests deferred, not passed or waived; define and review v2 behavior before integration. |
| D-04, Settings pause and retained intent | REQ-002, REQ-014 | RISK-001, RISK-002, RISK-004 | [Settings lifecycle](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen) | TP-REQ-002, TP-REQ-014 | Reported JVM ViewModel tests cover retained time and changed-duration reset; complete verification and human review pending. |
| D-05, explicit interruption restart | REQ-002, REQ-003, REQ-013 | RISK-002, RISK-005 | [Interrupted state](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states) | TP-REQ-002, TP-REQ-003, TP-REQ-013 | Reported JVM tests cover explicit Restart and Settings-held intent. Zero idle priority and warning restoration require human risk review; platform delivery remains open. |
| D-06, manual actions | REQ-002, REQ-010, REQ-013 | RISK-001, RISK-004, RISK-005 | [Main actions](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states) | TP-REQ-002, TP-REQ-010, TP-REQ-013 | Reported JVM tests cover the actions and bounded keyboard/overlay scenarios; device, screen-reader and human usability review pending. |

## Evidence and limitations

The intended-use record and M1 risk assessment have unresolved review gates.
The RISK-001 through RISK-006 mappings are proposed links, not evidence of
implemented controls. The product owner has explicitly accepted the unprotected
Settings exposure for v1, but RISK-004 acceptability remains undetermined
pending competent review. M2-DOC-006 records facilitator-reported
feedback from two sessions and Session B screenshots, alongside retrospective
protocol and other evidence gaps. The sessions do not constitute validation
across the intended user population; no test results, requirement approval,
or risk-control effectiveness are claimed. Issue #24 was closed at the owner's
request after PR #30; closure recorded a limited formative disposition and did
not resolve the caregiver-participation limitation, F01, or the other evidence
gaps.

The [formative sufficiency rationale](../usability/usability-findings.md#caregiver-availability-and-formative-sufficiency)
records the owner's decision to skip a replacement caregiver session for this
round and continue M2 design iteration. Gap G03 retains the missing direct
caregiver perspective, particularly for REQ-001, REQ-002, REQ-004, REQ-005,
REQ-006, and REQ-008. The reported delegation to Participant B is not caregiver
workflow evidence. This disposition neither completes M2 nor verifies the
linked RISK-001 through RISK-005 controls.

When verification is performed, record the exact requirement, test protocol
revision, implementation/build revision, platform, result, evidence link,
review scope, and limitations. Update all affected trace links when a
requirement, risk, design, test, or use assumption changes. Do not duplicate
test procedures or acceptance criteria in this matrix.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish initial requirement-to-intended-use/risk/design/usability/test/evidence links; preserve all review and verification gaps. |
| 0.2 | Link the candidate M2-DOC-004 screen-flow and theme-prototype record to applicable requirement rows; keep design review, usability, and verification evidence pending. |
| 0.3 | Update design traceability for the proposed remaining-disk default, alternate timer visualizations, and conditional appearance options; keep all review and verification evidence pending. |
| 0.4 | Link the local interactive two-hour visualization preview to the design evidence for REQ-006; leave usability and verification evidence pending. |
| 0.5 | Align traceability to M2-DOC-004 revision 0.4 and its updated prototype-only appearance previews; keep usability and verification evidence pending. |
| 0.6 | Align traceability to M2-DOC-004 revision 0.5 and its prototype-only background and number-visibility options; keep usability and verification evidence pending. |
| 0.7 | Align traceability to M2-DOC-004 revision 0.6 and its prototype-only duration and personalization-panel controls; keep usability and verification evidence pending. |
| 0.8 | Align traceability to M2-DOC-004 revision 0.7 and its prototype-only background, digital-time placement, and digital-shape refinements; keep usability and verification evidence pending. |
| 0.9 | Align traceability to M2-DOC-004 revision 0.8 and its prototype-only three-color visualization controls; keep usability and verification evidence pending. |
| 1.0 | Align traceability to M2-DOC-004 revision 0.9 and its prototype-only disk-label placement and digital shape refinements; keep usability and verification evidence pending. |
| 1.1 | Align traceability to M2-DOC-004 revision 1.0 and its larger disk labels and fixed-radius angular fan; keep usability and verification evidence pending. |
| 1.2 | Align traceability to M2-DOC-004 revision 1.1 and its unified prototype settings and shape selector; keep usability and verification evidence pending. |
| 1.3 | Align traceability to M2-DOC-004 revision 1.2 and independent remaining/progress controls; keep usability and verification evidence pending. |
| 1.4 | Align traceability to M2-DOC-004 revision 1.3 and the Off/2–12-section details slider; keep usability and verification evidence pending. |
| 1.5 | Add M2-DOC-005 usability protocol and M2-DOC-006 documentary findings/gaps; trace proposed tasks and explicit non-participant evidence gaps without claiming user review. |
| 1.6 | Link facilitator-reported feedback from two sessions, the Clock clipping finding and screenshots, and remaining protocol/usability evidence gaps; do not generalize findings. |
| 1.7 | Link the caregiver availability explanation and limited formative sufficiency rationale; retain direct-caregiver evidence gaps and distinguish continued M2 work from milestone completion. |
| 1.8 | Align with M2-DOC-002 revision 0.2; add reverse risk/test coverage, link findings, usability limitations, and unresolved behavior decisions to planned tests and follow-up, and correct the M2-DOC-006 issue-closure reference. |
| 1.9 | Align with v1 product decisions and updated requirements, design, test protocol, and risk records; trace the clipping correction while retaining pending browser verification and competent risk review. |
| 2.0 | Trace deterministic AppViewModel lifecycle-state tests for the revised interruption behavior; keep Compose event delivery, platform runtime, and risk review pending. |
| 2.0 | Update current usability/design source revisions and trace D-01–D-05 dispositions alongside the clipping correction; retain pending implementation, verification, usability, and competent risk review. |
| 2.1 | Record limited prototype-layout evidence for F01 without treating it as application verification or a complete pass of the no-clipping criterion. |
| 2.2 | Update the current usability-plan revision link after aligning its design source to M2-DOC-004 revision 1.5. |
| 2.3 | Align source revisions with the M2 review candidate and trace D-06's omission of separate manual pause, resume, and reset controls to REQ-002 and TP-REQ-002. |
| 2.4 | Link M3-DOC-003 feasibility findings to REQ-008 and D-03 without changing v1 audio deferral or claiming provider verification. |
| 2.5 | Add M3-DOC-007 as a cross-platform architecture trace input while retaining the M2 gate, all requirement/risk verification gaps, and unresolved support baselines. |
| 2.6 | Record timer-domain verification for the implemented duration, Settings lifecycle, and completion behavior; retain application/UI, Android runtime, accessibility, usability, and lifecycle limitations. |
| 2.7 | Align source revisions with the current M1/M2/M3 candidates; add REQ-011/TP-REQ-011 links and the four-theme, browser interruption, and TV-target verification scope. |
| 2.8 | Align usability source references to revision 0.7 and record the implemented browser UI and focused duration-entry tests without claiming product verification. |
| 2.9 | Align risk/protocol source revisions and record the counter/settings-domain test scope after the timer logic moved to core/counter; retain UI and product-verification limitations. |
| 3.0 | Trace adaptive supporting-pane Settings and target-specific keyboard/keypad entry criteria; record focused input-policy tests while retaining rendered layout/input and no-clipping verification gaps. |
| 3.1 | Align the test-protocol revision and record the successful JVM tests/builds plus unresolved Skiko Wasm test-runner failure; retain product-level verification gaps. |
| 3.2 | Trace the Android TV launcher candidate while retaining the TV interaction, usability, and support-verification gaps. |
| 3.3 | Align source revisions with the About candidate; trace the supplied logo and explicit required-content gaps, retaining TP-REQ-004 as blocked and browser/accessibility/integration evidence pending. |
| 3.4 | Align the M1/M2 source revisions with issue #66; trace text-free detail-0 states, all-shape segmentation, clockwise Clock mapping, and pending browser/human review evidence. |
| 3.5 | Record bounded browser render observations for REQ-003/013 while retaining the unresolved accessibility, complete visual-matrix, and human-review gaps. |
| 3.6 | Align requirements, protocol, risks, and usability sources with the demo-matching Clock labels and centered Fan geometry; keep rendered behavior and human review explicitly pending. |
| 3.7 | Align refinement source revisions and trace preview, color, Battery/Ring geometry, and thickness migration tests; distinguish bounded browser observations from human acceptance. |
| 3.8 | Trace desktop keyboard-entry criteria and focused JVM interaction coverage; retain physical-device, accessibility, and zero-duration reconciliation gaps. |
| 3.9 | Trace live Settings appearance, glass and detail-zero slider checks; retain platform and human review limitations. |
| 4.0 | Trace Settings overlay, obscured preview and native blur fallback; retain product, risk, accessibility and platform review gaps. |
| 4.1 | Trace stationary Timer and sliding glass panel criteria; retain platform and human review gaps. |
| 4.2 | Trace remaining-time glass, its bounded checks and readability risks; retain fallback, device and human review gaps. |
| 4.3 | Trace the expanded palette, font choices and tick-free Ring slider to risk review and verification; retain platform and human review gaps. |
| 4.4 | Trace revised requirements, risks, planned tests and usability gaps; distinguish historical results from current verification. |
| 4.5 | Add bounded operator-reported one-shot timer and action evidence while retaining the zero-warning mismatch and review gaps. |
| 4.6 | Align traceability with approved zero idle priority and retained interruption; retain human and platform review gaps. |
