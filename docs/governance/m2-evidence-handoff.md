# M2 evidence baseline review and M3 handoff

| Field | Value |
|---|---|
| Document ID | M2-DOC-007 |
| Revision | 1.3 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | M1/M2/M3 product, requirements, design/usability, accessibility, test/traceability, software-safety, and platform-architecture scopes: @Entreco, designated by the product owner on 2026-11-19; review of this final PR head remains pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#26](https://github.com/Entreco/elchrono/issues/26) |
| Review PR | Prior evidence baseline: [PR #32](https://github.com/Entreco/elchrono/pull/32); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](document-control.md#lifecycle-and-approval).

## Review outcome

**Owner-approved M1/M2/M3 scope; final PR-head acceptance pending.** On
2026-11-19, @Entreco confirmed they are the human reviewer for all applicable
disciplines and approved the M1, M2, and M3 reviews. The product owner
specifically approved the demo's Clock, Battery, Fan, and Ring visualizations,
designated Android TV and Apple TV as primary product targets, and excluded
Wear OS and watchOS from v1. The owner accepts the documented usability and
participant-evidence limitations for this design-baseline decision. The
retrospective sessions, missing caregiver/assistive-technology evidence,
unverified controls, undefined support baselines, and unconfigured TV targets
remain evidence gaps; this disposition does not convert them into validation,
verification, or platform support.

The earlier blocker that reviewer appointments were absent is resolved by the
owner's designation. M2 requirements and design are authorized as the reviewed
input for issue #47, subject to the human's final review and acceptance of the
current PR head. Product verification remains required before product claims
or release; planned tests are not reported as executed. No regulatory
qualification, residual-risk acceptance, or release approval is implied.

The closure of issues [#22](https://github.com/Entreco/elchrono/issues/22)
through [#25](https://github.com/Entreco/elchrono/issues/25) records work
items' dispositions; it does not establish approval of their records or
satisfy the M2 milestone gate. Issue #24 permits a documented participant
limitation, but its closure does not provide direct caregiver evidence or
approve that limitation for M2 completion.

## Scope and evidence baseline

This record integrates the documents and evidence below with the product
owner's review disposition. The owner's review is a self-attested human
designation; no external credential or independent competence audit is
asserted. Exact-head acceptance must identify the full reviewed SHA in the
current PR. A PR merge or document lifecycle is not a substitute for that
acceptance.

| Record | Exact source revision and evidence inspected |
|---|---|
| [M2-DOC-001 — Product requirements](../product/requirements.md) | Revision 0.8 candidate; ten active requirements, D-01–D-09, four owner-approved themes, adaptive Settings, and target-specific input methods |
| [M2-DOC-004 — Accessible screen flows and theme prototypes](../design/accessible-screen-flows-and-theme-prototypes.md) | Revision 1.8 candidate; Main/Settings/About flow, four owner-approved themes, supporting-pane Settings, keyboard/keypad policy, and TV limitations |
| [M2-DOC-005 — Usability review plan](../usability/usability-plan.md) | Revision 0.7; future tasks align to the current requirements/design; earlier sessions were not governed by it |
| [M2-DOC-006 — Usability findings and evidence status](../usability/usability-findings.md) | Revision 0.7; preserves retrospective evidence, caregiver gap, and limited F01 layout checks |
| [M2-DOC-002 — Product test protocol](../testing/test-protocol.md) | Revision 1.0 candidate; measurable procedures for ten active requirements, passing focused JVM tests/builds, and the unresolved Skiko Wasm test-runner failure; rendered UI/product-level tests remain unexecuted |
| [M2-DOC-003 — Requirements traceability matrix](../safety/requirements-traceability-matrix.md) | Revision 3.1 candidate; current source revisions, test/build evidence, Wasm test-runner limitation, and adaptive-layout/input verification gaps recorded |
| [M1-DOC-004 — Risk assessment](../safety/risk-assessment.md) | Revision 0.7 candidate; bounded D-01 disposition and scenario walkthrough recorded; domain tests do not establish control effectiveness or a post-control score |
| [M1-DOC-009 — Risk traceability matrix](../safety/risk-traceability-matrix.md) | Revision 1.1 candidate; current M1 sources, focused input-policy/domain-test evidence, and browser/TV verification scope linked |
| [M3-DOC-007 — Platform architecture and support matrix](../architecture/platform-architecture-and-support-matrix.md) | Revision 0.2 candidate; KMP/Compose retained, TV primary, Wear/watch excluded from v1; no support commitment |

The source PRs are merged. PR #32 merged at
`5ae94aeca79c5331deb6b75e9db9adc3eef78775` from reviewed head
`e03f0b910e4379b3be089e06fa4889ef5bd37f85`; its description records human
acceptance of the #26 evidence/handoff deliverable at that head. PR #32 did
not approve M2 source documents or specialist dispositions. PR #41 records
human acceptance of its M2 document package at its reviewed head; it does not
cover the source revisions added in this review or the M3 platform changes.
The owner's current designation and decision are recorded above. The human
must still accept the final head of this PR; agents must leave its acceptance
box unchecked.

The current candidate inputs are [M1-DOC-002, intended use revision
1.0](../product/intended-use.md) and [M1-DOC-004, risk assessment revision
0.7](../safety/risk-assessment.md). These updated revisions and their exact-head
review are part of the current PR; the traceability matrix records their
relationships and remaining verification limitations.

## M2 completion-gate evidence

The gate below follows the [M2 milestone definition](https://github.com/Entreco/elchrono/milestone/2).
“Evidence exists” means a candidate record or finding is present; it does not
mean the item is approved, verified, or accepted for milestone completion.

| Gate item | Evidence and review check | Outcome |
|---|---|---|
| Stable requirements and measurable acceptance criteria | M2-DOC-001 revision 0.8 contains ten active REQ-NNN statements. M2-DOC-002 revision 1.0 has ten corresponding planned test cases, including supporting-pane and target-specific input criteria; M2-DOC-003 revision 3.1 links requirements, risks, design/usability evidence, tests, and remaining UI/test-runner gaps. Product tests remain necessary before product verification or release claims. | **Owner-approved requirements baseline for issue #47; focused JVM tests and builds do not establish product verification. Exact PR-head acceptance remains pending.** |
| Usability plan and findings, or documented participant-review limitation | M2-DOC-005/006 revision 0.7 preserve two 2026-11-16 retrospective intended-user sessions and their limitations. There was no caregiver session, no assistive-technology evaluation, and no representative theme preference evidence. The owner accepts these limitations for the current design-baseline decision. | **Accepted as documented limitations for this M2 design baseline; the sessions remain retrospective and do not establish caregiver workflow usability, representative preference, or validation. Do not claim those outcomes.** |
| Design review and approval | M2-DOC-004 revision 1.8 specifies Main/Settings/About, the four demo visualizations approved by the product owner, adaptive supporting-pane Settings, and target-specific duration input. Android TV and Apple TV are primary targets; TV-specific interaction and support remain unverified. | **Product/design scope approved by the owner; application verification and TV-specific review remain required. Final exact-head acceptance is pending.** |
| Initial requirements-to-risk-to-test traceability | M2-DOC-003 revision 3.1 maps ten active requirements to six risks and ten planned test cases, includes reverse coverage, and links findings, limitations, and decisions. It records passing focused JVM tests/builds and the unresolved Wasm test-runner failure without claiming product verification, control effectiveness, residual-risk acceptance, or platform support. | **Traceability baseline is owner-approved for issue #47; rendered UI/product-level execution and risk-control results remain unverified.** |
| Required completion and Settings behavior | REQ-002 specifies Settings-open pause and valid-save start from the full saved duration, with no separate manual pause/resume/reset controls. REQ-010 specifies immediate reset with no completed state; music is deferred to v2 and stops at zero only if later introduced. Counter-domain tests cover pause/save and completion behavior; UI integration remains unverified. | **Product decisions recorded; focused domain tests pass, while product/UI verification remains pending.** |
| Usability and interface findings | M2-DOC-006-F01 records Clock remaining-time clipping, corroborated by the Session B screenshot. The owner-reported prototype correction was checked at 500 × 1000 px and 1440 × 1200 px. The browser MVP and adaptive Settings/input implementation now exist; their rendered layouts and interactions have not yet been product-tested. | **Specific prototype finding closed only for checked layouts; browser UI no-clipping, pane resizing, and keyboard/keypad behavior remain unverified.** |
| v1 caregiver-protection risk disposition | The product owner explicitly accepts unprotected Settings for v1. M1-DOC-004 revision 0.7 records the bounded decision; no control, risk reduction, or post-control score is claimed. | **Owner-approved product disposition; exact-head review remains pending. Do not treat this as evidence of a protective control.** |

The usability findings retain G01–G05: retrospective session evidence and no
pre-session protocol, no contemporaneous notes, no direct caregiver feedback,
unobserved workflows/states, and no assistive-technology evaluation. The owner
accepts these limitations for the current design-baseline decision only. This
does not validate the product or waive product verification, future usability
work, or risk-control verification.

## Product decisions and v1 justification

The product owner supplied the dispositions below. They define
the current v1 scope and are recorded in M2-DOC-001 revision 0.8 and
M2-DOC-004 revision 1.8.

| Decision | Current evidence and disposition |
|---|---|
| D-01 — Caregiver protection mechanism | No caregiver gate in v1; patients may change duration/theme. Product owner accepts this bounded exposure for v1. Unintended changes can still cause confusion or alter a wait; no control or risk reduction is credited. |
| D-02 — Volume controls | No in-app volume controls in v1. Justification: no v1 audio capability exists to control; adding an unused control would add configuration without v1 playback. This does not decide system volume or promise controls in v2. |
| D-03 — Music and start behavior | Music integration is deferred to v2; no music start behavior or completion sound is in v1. Justification: without v1 playback there is no start behavior to select or misrepresent. Provider, data flow, playback, volume, failure, and sound behavior require separate review before v2. |
| D-04 — Timer start and Settings lifecycle | Opening Settings while running pauses the timer; saving valid settings starts a new countdown from the full saved duration. Justification: explicit save is the start action and does not silently resume timing against changed configuration. Counter-domain tests cover pause/save; browser UI integration remains unverified. |
| D-05 — Interruption recovery | No automatic recovery is provided or promised in v1. The browser MVP records a hidden-page/page-hide event, pauses on the next timer update after return, and retains an interrupted warning until valid Settings are saved; Save starts a fresh countdown from the full saved duration. Progress may be out of date. RISK-002 remains open; this is not residual-risk acceptance. |
| D-06 — Manual pause, resume, and reset controls | No separate manual pause, resume, or reset controls are included in v1. Settings-open is the selected pause behavior; saving valid Settings is the only selected start/restart action and starts from the full saved duration. The candidate implements this behavior in the counter domain; focused tests pass, while browser UI verification remains pending. |
| D-07 — Primary platforms and v1 exclusions | Android TV and Apple TV are primary product targets. Wear OS and watchOS are outside v1. The browser remains the M5 implementation target; no TV support is claimed. |
| D-08 — V1 visualization set | Clock, Battery, Fan, and Ring are approved v1 choices as presented in the demo; Clock is the initial selection, not a user-preference claim. |
| D-09 — Adaptive Settings and duration input methods | Settings uses a supporting pane when space permits and a reachable single-pane destination on compact windows. Mobile uses an available system keyboard with the shared keypad as fallback; desktop/browser and future TV flows use keypad-only entry. TV support is not established. Implementation and policy tests do not verify rendered layout, runtime keyboard selection, accessibility, or clipping. |

These are product-scope justifications, not evidence that risks are eliminated,
controls are effective, or residual risks are acceptable. The non-medical
ordinary-waiting boundary limits intended use but does not replace competent
risk review or usability/accessibility review.

The owner approves the M2 review gate for issue #47 as a product/design
baseline, with the limitations below retained. This is not a declaration that
the M2 milestone is complete or that usability, accessibility, risk-control,
product-test, or release criteria have been verified.

## M3 handoff and dependencies

The [M3 milestone](https://github.com/Entreco/elchrono/milestone/3) depends on
M2. The owner approves the existing KMP/Compose architecture for continued
implementation and designates Android TV and Apple TV as primary future
targets. This does not configure TV targets or claim TV support. Final exact-
head acceptance and product verification remain outstanding.

| Action before relying on M2 as an approved input | Owner / source | Evidence needed |
|---|---|---|
| Review and accept the exact current PR head, record review scope/findings and the full SHA, and keep the acceptance box unchecked until the human does so. | @Entreco; current M1/M2/M3 candidates; [document controls](document-control.md) | Product owner has designated self as reviewer and approved the scope decisions; final full-head acceptance is pending. |
| Retain caregiver/usability and participant-evidence limitations. | @Entreco; M2-DOC-005/006, G01–G05 | Owner accepts the documented limitations for the M2 design baseline; no caregiver session, assistive-technology evaluation, or representative validation is claimed. |
| Verify the browser implementation against supported layouts and the exact protocol. | @Entreco; M2-DOC-006-F01, M2-DOC-002 revision 1.0, M2-DOC-003 revision 3.1 | The PR #32 probe checks only the prototype at 500 × 1000 px and 1440 × 1200 px. The browser UI exists; layout, pane resizing, input, and interaction tests remain pending. |
| Keep unexecuted product-test cases visible and complete them before product verification/release claims. | @Entreco; M2-DOC-002 revision 1.0, M2-DOC-003 revision 3.1 | Protocol criteria are documented; passing compilation/JVM tests and the unresolved Wasm test-runner failure do not establish browser usability, accessibility, offline operation, or TV support. |
| Implement D-01 through D-09 without adding unreviewed behaviors. | @Entreco; M2-DOC-001 revision 0.8, M2-DOC-004 revision 1.8, M1-DOC-002 revision 1.0, M1-DOC-004 revision 0.7 | The owner has approved the listed product decisions; no control effectiveness, residual-risk score, or user-preference finding is inferred. |
| For M3, complete its own architecture decision record; platform-support matrix and provider-feasibility findings; reproducible development setup; documentation templates; dependency/SOUP inventory and assessment; and working automated checks. Document data flows and avoid identifiable patient data unless an approved requirement makes it necessary. | @Entreco; [M3 milestone](https://github.com/Entreco/elchrono/milestone/3) | M3-specific reviewed and verified evidence, with limitations and traceability. M3 work does not substitute for M2 closure. |

No reviewer-appointment or theme-selection blocker remains for issue #47.
Final PR-head human acceptance and product verification are still pending.
Any continuation into M3 must preserve the non-medical intended-use boundary,
the stated platform-support limitations, and the explicit v1 product decisions.

## Review limitations

This record preserves the source evidence, the owner's declared review role
and decisions, and the remaining limitations. The limited F01 prototype check
does not reproduce participant sessions, execute product protocol cases,
verify risk controls, or decide legal qualification. The owner designation is
self-attested; this record makes no independent credential claim. Final
human acceptance must cover the complete PR head and be recorded under
document controls. Do not treat this record's existence, issue closure, or a
branch-derived lifecycle as evidence that the final PR head has been accepted.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record the M2 evidence baseline, blocked completion-gate outcome, open product decisions, unresolved usability/review/verification evidence, and dependent M3 actions for issue #26. |
| 0.2 | Link the evidence record to its review PR; the required review, findings disposition, and human acceptance remain pending. |
| 0.3 | Record the product-owner v1 decisions and scope justifications; align linked requirements/design/usability/test/traceability/risk revisions; document the prototype clipping correction with browser verification pending; keep the M2 gate blocked because required review acceptance was not supplied. |
| 0.4 | Reconcile the design and requirements-traceability records to the current risk source and usability revisions; retain the blocked outcome, missing reviewer acceptance, and pending clipping verification. |
| 0.5 | Record the owner's clipping-resolution report and limited prototype layout results; close only the specific finding for checked layouts while retaining undefined supported-layout and product verification gaps. |
| 0.6 | Reconcile the M2 usability-plan and traceability revisions with the current design source and retain all open gate conditions. |
| 0.7 | Clarify that the F01 layout probe was limited prototype evidence and that agent integration review does not substitute for competent specialist acceptance. |
| 0.8 | Reconcile merged PR #32 and its limited acceptance scope; add D-06, align the M2 source revisions using existing prototypes, and prepare a review package while keeping the gate open and blockers explicit. |
| 0.9 | Record the owner's M1/M2/M3 reviewer designation and approved design/platform scope; accept documented usability limitations for the design baseline while retaining empirical, verification, platform-support, and final-PR-head gaps. |
| 1.0 | Record owner approval of the M2 review gate and four-theme design baseline for issue #47; update current traceability references and retain usability, accessibility, risk-control, product-verification, and exact-head acceptance limitations. |
| 1.1 | Align risk, test-protocol, traceability, and development-plan references with the core/counter refactor; record focused domain-test evidence while retaining product-level verification gaps. |
| 1.2 | Update the evidence baseline for D-09 and the adaptive supporting-pane/keypad implementation; keep rendered behavior, accessibility, TV support, and exact-head acceptance pending. |
| 1.3 | Record the test-protocol/traceability revisions, passing JVM tests and target builds, and unresolved Skiko Wasm test-runner failure; retain product-verification and human-acceptance gaps. |
