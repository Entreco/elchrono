# Requirements traceability matrix

| Field | Value |
|---|---|
| Document ID | M2-DOC-003 |
| Revision | 2.2 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Requirements and software-safety reviewers: unassigned |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#25](https://github.com/Entreco/elchrono/issues/25) |
| Review PR | Prior baselines: revision 1.4 in [PR #29](https://github.com/Entreco/elchrono/pull/29), revision 1.7 in [PR #30](https://github.com/Entreco/elchrono/pull/30), revision 1.8 in [PR #31](https://github.com/Entreco/elchrono/pull/31); revision 2.2 in [PR #32](https://github.com/Entreco/elchrono/pull/32) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and use

This matrix is the authoritative cross-reference between the intended-use
source, product requirements, risks, design/usability evidence, and test
protocol. Requirement statements live in
[M2-DOC-001, revision 0.4](../product/requirements.md); acceptance criteria and
procedures live in [M2-DOC-002, revision 0.3](../testing/test-protocol.md).
Do not duplicate those statements or criteria here.

The mappings below are initial and proposed. Design links are included when
records exist; usability findings and verification status remain pending until
actual evidence is recorded.

| Source record | Revision | Relationship |
|---|---|---|
| [M1-DOC-002, intended use](../product/intended-use.md) | 0.9 candidate | Product purpose, roles, use, and limitations |
| [M2-DOC-001, product requirements](../product/requirements.md) | 0.4 candidate | Nine proposed requirements incorporating the v1 product decisions |
| [M1-DOC-004, risk assessment](risk-assessment.md) | 0.5 candidate | RISK-001 through RISK-006 traced below; D-01 product-owner disposition is not competent risk acceptance |
| [M1-DOC-009, risk traceability matrix](risk-traceability-matrix.md) | 0.6 candidate | Risk-to-source and planned verification; review pending |
| [M2-DOC-002, product test protocol](../testing/test-protocol.md) | 0.3 candidate | Test procedures and acceptance criteria traced below; audio tests deferred to v2 |
| [M2-DOC-004, accessible screen flows and theme prototypes](../design/accessible-screen-flows-and-theme-prototypes.md) | 1.4 candidate | Main/Settings/About and theme proposals aligned with v1 decisions; design review pending |
| [M2-DOC-005, usability review plan](../usability/usability-plan.md) | 0.3 candidate | Proposed participant approach and tasks; created after the two sessions and did not govern them |
| [M2-DOC-006, usability findings and evidence status](../usability/usability-findings.md) | 0.3 candidate | Two retrospective sessions and retained screenshots; Clock clipping and caregiver/usability limitations retained |

## Traceability

| Requirement | Intended-use source | Applicable risks | Design/usability evidence | Test protocol | Verification evidence |
|---|---|---|---|---|---|
| [REQ-001](../product/requirements.md#requirement-identification) | [Duration and supported waiting use](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | RISK-001 | [Settings duration entry](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); planned [U-03](../usability/usability-plan.md#tasks-and-neutral-prompts); session scope [M2-DOC-006](../usability/usability-findings.md#session-scope-and-evidence-provenance), gap G04 | [TP-REQ-001](../testing/test-protocol.md#test-cases) | Pending |
| [REQ-002](../product/requirements.md#requirement-identification) | [Users and roles](../product/intended-use.md#users-population-and-roles) | RISK-001 | [Save-to-start and Settings-open pause](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); session gap G04 | [TP-REQ-002](../testing/test-protocol.md#test-cases) | Pending |
| [REQ-003](../product/requirements.md#requirement-identification) | [Visual remaining-time use and limitations](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | RISK-001, RISK-002, RISK-005, RISK-006 | [Main states and visualization mapping](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states); session findings [F01/F04](../usability/usability-findings.md#participant-feedback-and-observations); gaps G04/G05 | [TP-REQ-003](../testing/test-protocol.md#test-cases) | Pending |
| [REQ-004](../product/requirements.md#requirement-identification) | [Users and roles](../product/intended-use.md#users-population-and-roles); screen/content inventory is M2 product scope in [#22](https://github.com/Entreco/elchrono/issues/22) | RISK-004, RISK-005, RISK-006 | [Main, Settings, and About designs](../design/accessible-screen-flows-and-theme-prototypes.md#screen-flows); no v1 audio/volume controls | [TP-REQ-004](../testing/test-protocol.md#test-cases) | Pending |
| [REQ-005](../product/requirements.md#requirement-identification) | [Users and roles](../product/intended-use.md#users-population-and-roles) | RISK-004 | [Unprotected Settings v1 decision](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states); prior sessions did not test access paths (G03/G04) | [TP-REQ-005](../testing/test-protocol.md#test-cases) | Pending; test does not establish risk acceptability |
| [REQ-006](../product/requirements.md#requirement-identification) | No direct theme-selection statement in intended use; this product-level choice is scoped by [#22](https://github.com/Entreco/elchrono/issues/22) | RISK-004, RISK-005 | [Timer visualizations and conditional appearance options](../design/accessible-screen-flows-and-theme-prototypes.md#timer-visualizations); session findings [F01-F04](../usability/usability-findings.md#participant-feedback-and-observations); gap G03 | [TP-REQ-006](../testing/test-protocol.md#test-cases) | Pending |
| [REQ-008](../product/requirements.md#requirement-identification) | [Offline use](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio); future audio scope deferred | RISK-003 | Music and volume controls excluded from v1; future audio behavior requires separate review | [TP-REQ-008](../testing/test-protocol.md#test-cases) | Network-independence test pending; v2 audio tests deferred |
| [REQ-009](../product/requirements.md#requirement-identification) | [Exclusions and claims boundary](../product/intended-use.md#exclusions-and-claims-boundary) | RISK-006 | [About screen content](../design/accessible-screen-flows-and-theme-prototypes.md#about-screen); session gap G04 | [TP-REQ-009](../testing/test-protocol.md#test-cases) | Pending |
| [REQ-010](../product/requirements.md#requirement-identification) | [Ordinary waiting use and limitations](../product/intended-use.md#foreseeable-use-and-limitations) | RISK-001, RISK-005; RISK-003 only if audio is added in v2 | Immediate reset without a completed state; no v1 audio | [TP-REQ-010](../testing/test-protocol.md#test-cases) | Pending; music-stop criterion deferred to v2 |

## Reverse coverage

The requirement rows above are the authoritative mapping. This reverse view
checks that every source risk and planned protocol case links back to a
requirement; no unlinked risk or test currently needs a no-direct-mapping
rationale.

| Risk | Linked requirements |
|---|---|
| RISK-001 | REQ-001, REQ-002, REQ-003, REQ-010 |
| RISK-002 | REQ-003 |
| RISK-003 | REQ-008; REQ-010 if audio is added in v2 |
| RISK-004 | REQ-004, REQ-005, REQ-006 |
| RISK-005 | REQ-003, REQ-004, REQ-006, REQ-010 |
| RISK-006 | REQ-003, REQ-004, REQ-009 |

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

REQ-007 was retired before approval and is not an active requirement; it has
no planned test. No test result is implied by these planned links.

## Findings, limitations, and decision follow-up

The following links keep usability evidence, open decisions, and planned
verification connected. Tests named here remain unrun; linked test cases do not
resolve usability evidence gaps.

| Finding or limitation | Requirements | Risks | Design/usability evidence | Planned test links | Follow-up |
|---|---|---|---|---|---|
| F01, Clock remaining-time text clipping | REQ-003, REQ-006 | RISK-001, RISK-005 | [Clock visualization](../design/accessible-screen-flows-and-theme-prototypes.md#timer-visualizations); [F01](../usability/usability-findings.md#participant-feedback-and-observations) and retained Session B screenshots; owner-reported resolution and Chrome prototype layout check at 500 × 1000 px and 1440 × 1200 px | TP-REQ-003, TP-REQ-006 | Close the specific prototype finding for those checked layouts. Supported layouts are undefined and the application is not implemented; complete no-clipping verification remains pending. |
| F02–F04, qualitative feedback without representative preference evidence | REQ-003, REQ-006 | RISK-005 | [F02–F04](../usability/usability-findings.md#participant-feedback-and-observations); design preference and visibility remain open | TP-REQ-003, TP-REQ-006 | Preserve feedback as qualitative only; do not infer a preferred theme or generalize. Keep remaining-time content fully visible whenever shown. |
| G01–G02, retrospective protocol and evidence-provenance limitations | REQ-001–REQ-006 excluding retired REQ-007, and REQ-008–REQ-010 | RISK-001–RISK-005 | [G01/G02](../usability/usability-findings.md#evidence-gaps-and-limitations); M2-DOC-005 revision 0.5 postdates the sessions | No direct protocol test: these concern usability-study conduct and evidence provenance, not product behavior. The product tests linked to the affected requirements do not close this gap. | Retain retrospective status and missing contemporaneous evidence; obtain appropriate review before relying on these findings. Do not add unobserved details. |
| G03, no direct caregiver session; G04, unobserved workflows/states; G05, no assistive-technology evaluation | G03: REQ-001–REQ-006 excluding retired REQ-007, and REQ-008–REQ-010; G04: REQ-001–REQ-005, REQ-008–REQ-010; G05: REQ-003–REQ-006 | G03/G04: RISK-001–RISK-005; G05: RISK-005 | [G03–G05](../usability/usability-findings.md#evidence-gaps-and-limitations); applicable flows in [M2-DOC-004 revision 1.5](../design/accessible-screen-flows-and-theme-prototypes.md) | G03: TP-REQ-001, TP-REQ-002, TP-REQ-003, TP-REQ-004, TP-REQ-005, TP-REQ-006, TP-REQ-008, TP-REQ-009, TP-REQ-010; G04: TP-REQ-001, TP-REQ-002, TP-REQ-003, TP-REQ-004, TP-REQ-005, TP-REQ-008, TP-REQ-009, TP-REQ-010; G05: TP-REQ-003, TP-REQ-004, TP-REQ-005, TP-REQ-006 | Keep the missing evidence explicit. Assess caregiver participation, unobserved tasks, and supported assistive-technology/layout conditions before claiming those areas are validated. |
| D-01, no caregiver-protection mechanism in v1 | REQ-005 | RISK-004 | [Unprotected Settings](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states); product-owner disposition in [requirements](../product/requirements.md#product-decisions-and-scope) | TP-REQ-005 | Product decision recorded; competent risk review of the accepted exposure remains required. Do not credit a protection control. |
| D-02, no v1 in-app volume controls | REQ-004 | RISK-003, RISK-004 | [v1 audio/volume exclusion](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); product decision in requirements | TP-REQ-004 | Verify no v1 volume control is presented; reconsider requirements if audio scope changes. |
| D-03, music deferred to v2 | REQ-004, REQ-008, REQ-010 | RISK-003 | [v1 audio exclusion](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); product decision in requirements | TP-REQ-008, TP-REQ-010 | Audio tests deferred, not passed or waived; define and review v2 behavior before integration. |
| D-04, Settings-open pause and save-to-start | REQ-002 | RISK-001 | [Settings lifecycle](../design/accessible-screen-flows-and-theme-prototypes.md#settings-screen); product decision in requirements | TP-REQ-002 | Behavior selected; implementation and deterministic verification pending. |
| D-05, no interruption recovery in v1 | REQ-003 | RISK-002 | [Interrupted state](../design/accessible-screen-flows-and-theme-prototypes.md#main-screen-and-states); product decision in requirements | TP-REQ-003 | No recovery is promised; retain warning and limitation. Risk review remains open and no continuity claim may be made. |

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
| 2.0 | Update current usability/design source revisions and trace D-01–D-05 dispositions alongside the clipping correction; retain pending implementation, verification, usability, and competent risk review. |
| 2.1 | Record limited prototype-layout evidence for F01 without treating it as application verification or a complete pass of the no-clipping criterion. |
| 2.2 | Update the current usability-plan revision link after aligning its design source to M2-DOC-004 revision 1.5. |
