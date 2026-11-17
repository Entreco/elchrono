# M2 evidence baseline review and M3 handoff

| Field | Value |
|---|---|
| Document ID | M2-DOC-007 |
| Revision | 0.8 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | M2 milestone, product requirements, design/usability, accessibility, test/traceability, and software-safety scopes: requested for PR #33; reviewer appointments and competence/scope confirmation pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#26](https://github.com/Entreco/elchrono/issues/26) |
| Review PR | Prior evidence baseline: [PR #32](https://github.com/Entreco/elchrono/pull/32); current review: [PR #33](https://github.com/Entreco/elchrono/pull/33) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](document-control.md#lifecycle-and-approval).

## Review outcome

**NOT APPROVED — the M2 gate remains open pending review of this exact
candidate and disposition of its blockers.** Requirements, designs, usability
records, a measurable test protocol, and traceability now include D-01 through
D-06. The Clock clipping finding is closed only for two checked prototype
layouts (500 × 1000 px and 1440 × 1200 px); supported layouts remain undefined,
and no application-level verification exists. Design approval, exact-revision
human/specialist review, competent disposition of the linked risk baseline,
and acceptance of the documented caregiver-evidence limitation are not yet
recorded for this candidate. Product protocol cases remain unexecuted. No M2
completion, release approval, or authorization to proceed past the M2
dependency is asserted.

The closure of issues [#22](https://github.com/Entreco/elchrono/issues/22)
through [#25](https://github.com/Entreco/elchrono/issues/25) records work
items' dispositions; it does not establish approval of their records or
satisfy the M2 milestone gate. Issue #24 permits a documented participant
limitation, but its closure does not provide direct caregiver evidence or
approve that limitation for M2 completion.

## Scope and evidence baseline

This is an integration check of the records and GitHub evidence listed below.
It is not a specialist usability, accessibility, product-design, or
software-safety review. The M2 documents revised here are candidates in PR #33;
the exact reviewed content is the PR head identified in GitHub when a human
reviews it. A PR merge or a document's branch-derived lifecycle is not
evidence of human acceptance or specialist review.

| Record | Exact source revision and evidence inspected |
|---|---|
| [M2-DOC-001 — Product requirements](../product/requirements.md) | Revision 0.5, candidate in [PR #33](https://github.com/Entreco/elchrono/pull/33); nine proposed requirements and D-01–D-06, including no separate manual timer controls |
| [M2-DOC-004 — Accessible screen flows and theme prototypes](../design/accessible-screen-flows-and-theme-prototypes.md) | Revision 1.6, candidate in [PR #33](https://github.com/Entreco/elchrono/pull/33); Main/Settings concepts reflect D-01–D-06 using the existing prototype designs; visualizations and appearance options remain proposals pending review |
| [M2-DOC-005 — Usability review plan](../usability/usability-plan.md) | Revision 0.6, candidate in [PR #33](https://github.com/Entreco/elchrono/pull/33); future tasks align to the current requirements/design; earlier sessions were not governed by it |
| [M2-DOC-006 — Usability findings and evidence status](../usability/usability-findings.md) | Revision 0.6, candidate in [PR #33](https://github.com/Entreco/elchrono/pull/33); preserves retrospective evidence, caregiver gap, and limited F01 layout checks |
| [M2-DOC-002 — Product test protocol](../testing/test-protocol.md) | Revision 0.5, candidate in [PR #33](https://github.com/Entreco/elchrono/pull/33); measurable procedures for nine active requirements; product tests remain unexecuted |
| [M2-DOC-003 — Requirements traceability matrix](../safety/requirements-traceability-matrix.md) | Revision 2.3, candidate in [PR #33](https://github.com/Entreco/elchrono/pull/33); current source revisions and D-01–D-06 linked; product verification pending |
| [M1-DOC-004 — Risk assessment](../safety/risk-assessment.md) | Revision 0.5, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32); owner D-01 disposition recorded, risk acceptability still undetermined pending competent review |
| [M1-DOC-009 — Risk traceability matrix](../safety/risk-traceability-matrix.md) | Revision 0.7, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32); v1 scope reflected, risk review and verification pending |

The source PRs are merged. PR #32 merged at
`5ae94aeca79c5331deb6b75e9db9adc3eef78775` from reviewed head
`e03f0b910e4379b3be089e06fa4889ef5bd37f85`; its description records human
acceptance of the #26 evidence/handoff deliverable at that head. That
acceptance expressly did not approve M2, the linked source documents, or
specialist dispositions. PR #32 has no formal GitHub review submissions.
Reviewers must now record actual scopes, findings/dispositions, limitations,
and acceptance of the final PR #33 head; agents must leave the human
acceptance box unchecked.

The candidate inputs also depend on [M1-DOC-002, intended use revision 0.9](../product/intended-use.md)
and [M1-DOC-004, risk assessment revision 0.5](../safety/risk-assessment.md).
Their exact-revision approval and required risk review remain pending. The
M2 traceability matrix marks their contents and risk links as proposed.

## M2 completion-gate evidence

The gate below follows the [M2 milestone definition](https://github.com/Entreco/elchrono/milestone/2).
“Evidence exists” means a candidate record or finding is present; it does not
mean the item is approved, verified, or accepted for milestone completion.

| Gate item | Evidence and review check | Outcome |
|---|---|---|
| Stable requirements and measurable acceptance criteria | M2-DOC-001 revision 0.5 contains nine active REQ-NNN statements. M2-DOC-002 revision 0.5 has nine corresponding planned test cases with measurable criteria, and M2-DOC-003 revision 2.3 links requirements, risks, design/usability evidence, and tests. D-06 resolves the manual pause/resume/reset choice. Product protocol cases have not run because no timer implementation is in scope here. | **Candidate records are ready for review; product behavior is not verified. The M2 approver must confirm whether this documentation gate requires product execution or whether planned protocol coverage is sufficient at this stage.** |
| Usability plan and findings, or documented participant-review limitation | M2-DOC-005 revision 0.6 and M2-DOC-006 revision 0.6 preserve two 2026-11-16 retrospective intended-user sessions and their limitations. The plan postdates those sessions. There was no caregiver session and no replacement recruitment attempt is claimed; the owner elected not to arrange one for that formative round. The sessions do not establish caregiver workflow usability, representative preferences among the existing prototypes, or validation. | **Limitations are documented, but direct caregiver evidence and validated preference among the existing prototypes are absent. M2 acceptance of these deviations is pending; issue #24 closure alone does not satisfy this gate.** |
| Design review and approval | M2-DOC-004 revision 1.6 updates Main/Settings concepts to D-01–D-06 using the existing prototypes. This review adds no design/theme; future theme work is deferred to later versions. Theme concepts, default selection, and appearance options remain proposals, and no preference is asserted. | **Not approved; exact-revision design, accessibility, usability, and human acceptance are pending.** |
| Initial requirements-to-risk-to-test traceability | M2-DOC-003 revision 2.3 maps nine active requirements to six proposed risks and nine planned test cases, includes reverse coverage, and links findings, limitations, and decisions. It does not claim product-test execution, competent risk acceptance, control effectiveness, or approval. | **Candidate traceability exists; source review and exact-revision acceptance remain pending.** |
| Required completion and Settings behavior | REQ-002 specifies Settings-open pause and valid-save start from the full saved duration, with no separate manual pause/resume/reset controls. REQ-010 specifies immediate reset with no completed state; music is deferred to v2 and stops at zero only if later introduced. | **Product decisions recorded; implementation and deterministic verification remain pending.** |
| Usability and interface findings | M2-DOC-006-F01 records Clock remaining-time clipping, corroborated by the Session B screenshot. The owner reports resolution; Chrome 157.0.8103.79 layout checks at 500 × 1000 px and 1440 × 1200 px place all six Clock labels within the visualization stage with no horizontal page overflow. Supported layout configurations and application-level verification remain pending. | **Specific prototype finding closed for checked layouts; complete product criterion remains unverified.** |
| v1 caregiver-protection risk disposition | The product owner explicitly accepts unprotected Settings for v1. M1-DOC-004 revision 0.5 records the decision and bounded rationale; it does not record a competent acceptability determination. | **Product decision recorded; competent risk review and residual-risk disposition remain required.** |

The usability findings also retain G01–G05: retrospective session evidence and
no pre-session protocol, no contemporaneous notes, no direct caregiver
feedback, unobserved workflows/states, and no reported assistive-technology
evaluation. The limited formative sufficiency rationale permits continued
design iteration only; it waives no M2 gate or risk-control verification.

## Product decisions and v1 justification

The product owner supplied the dispositions below. They define
the current v1 proposal and are recorded in M2-DOC-001 revision 0.5 and
M2-DOC-004 revision 1.6. No reviewer acceptance is claimed.

| Decision | Current evidence and disposition |
|---|---|
| D-01 — Caregiver protection mechanism | No caregiver gate in v1; patients may change duration/theme. Product owner accepts this exposure for now. Justification: v1 is limited to ordinary waiting and Settings contain duration/theme only. Unintended changes can still cause confusion or alter a wait; no control is credited. M1-DOC-004 retains RISK-004 acceptability as undetermined pending competent review. |
| D-02 — Volume controls | No in-app volume controls in v1. Justification: no v1 audio capability exists to control; adding an unused control would add configuration without v1 playback. This does not decide system volume or promise controls in v2. |
| D-03 — Music and start behavior | Music integration is deferred to v2; no music start behavior or completion sound is in v1. Justification: without v1 playback there is no start behavior to select or misrepresent. Provider, data flow, playback, volume, failure, and sound behavior require separate review before v2. |
| D-04 — Timer start and Settings lifecycle | Opening Settings while running pauses the timer; saving valid settings starts a new countdown from the full saved duration. Justification: explicit save is the start action and does not silently resume timing against changed configuration. This behavior is unimplemented and unverified. |
| D-05 — Interruption recovery | No recovery behavior is provided or promised in v1; retain an interrupted warning and do not imply timing continuity. Justification: the v1 product makes no recovery or reliable-progress claim. RISK-002 remains unassessed; this decision is not residual-risk acceptance. |
| D-06 — Manual pause, resume, and reset controls | No separate manual pause, resume, or reset controls are included in v1. Settings-open is the selected pause behavior; saving valid Settings is the only selected start/restart action and starts from the full saved duration. This resolves the M2 product choice without claiming that the behavior is implemented, verified, or usable. |

These are product-scope justifications, not evidence that risks are eliminated,
controls are effective, or residual risks are acceptable. The non-medical
ordinary-waiting boundary limits intended use but does not replace competent
risk review or usability/accessibility review.

## M3 handoff and dependencies

The [M3 milestone](https://github.com/Entreco/elchrono/milestone/3) depends on
M2. This record identifies the outstanding handoff; it does not clear that
dependency, approve architecture, or authorize implementation of unresolved
behavior.

| Action before relying on M2 as an approved input | Owner / source | Evidence needed |
|---|---|---|
| Review the exact M2 candidate and prerequisite M1 source revisions; appoint/confirm competent review scopes, record findings/dispositions and limitations, and tie acceptance to the final PR #33 head. | @Entreco; M2-DOC-001 through M2-DOC-007, M1-DOC-002 revision 0.9, M1-DOC-004 revision 0.5, and M1-DOC-009 revision 0.7; [document controls](document-control.md) | Human acceptance and specialist scope/competence confirmation are pending. The prior PR #32 acceptance covers only the #26 evidence/handoff deliverable, not these revisions or M2 closure. |
| Decide whether the documented caregiver/usability limitation is acceptable for M2 or whether further safeguarded evidence is required. | @Entreco; M2-DOC-005/006, G01–G05, milestone #2 | No direct caregiver session or assistive-technology evaluation is reported; the owner elected not to attempt a replacement caregiver session for that formative round. Do not claim representative validation. |
| Retain and review the limited Clock clipping evidence; define supported layouts and verify the actual application when available. | @Entreco; M2-DOC-006-F01, M2-DOC-003, TP-REQ-003 and TP-REQ-006 | The merged PR #32 probe checks only the prototype at 500 × 1000 px and 1440 × 1200 px. No further demo-CSS work is proposed; supported layouts and application-level verification remain open. |
| Confirm the M2 definition-of-done interpretation for unexecuted product test cases. | @Entreco; M2-DOC-002 revision 0.5, M2-DOC-003 revision 2.3, milestone #2 | Protocol coverage and measurable criteria are documented; no product tests can be claimed before implementation. If M2 requires execution results, this item blocks closure and needs a separately authorized implementation/test scope. |
| Implement the recorded D-01 through D-06 decisions only after required requirements/design/risk review; review the explicit D-01 exposure and reassess M1 source gaps before relying on them. | @Entreco; M2-DOC-001, M2-DOC-004, M1-DOC-002, M1-DOC-004 | Review the product decisions and resulting risks; no control or residual-risk acceptance is inferred from this record. |
| For M3, complete its own architecture decision record; platform-support matrix and provider-feasibility findings; reproducible development setup; documentation templates; dependency/SOUP inventory and assessment; and working automated checks. Document data flows and avoid identifiable patient data unless an approved requirement makes it necessary. | @Entreco; [M3 milestone](https://github.com/Entreco/elchrono/milestone/3) | M3-specific reviewed and verified evidence, with limitations and traceability. M3 work does not substitute for M2 closure. |

The M2 outcome remains blocked until its completion gate is reviewed against
the final evidence and all blocking gaps are resolved or formally dispositioned
under the applicable project controls. Any continuation into M3 must preserve
the non-medical intended-use boundary and must not silently decide unresolved
product behavior.

## Review limitations

This integration check compares the linked records, their stated revisions,
the M2 milestone gate, and available PR descriptions/review metadata. The
limited F01 prototype layout check is described above; it does not reproduce
participant sessions, execute product protocol cases, verify risk controls,
establish reviewer competence, or decide legal qualification. No scoped
competent-reviewer acceptance of the current M2 source revisions was supplied,
and this agent integration check does not substitute for specialist review.
The PR review evidence remains incomplete as described above.
Do not treat this record's existence, issue #26 closure, or the document's
branch-derived lifecycle as M2 approval.

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
