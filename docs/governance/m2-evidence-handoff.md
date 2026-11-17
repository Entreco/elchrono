# M2 evidence baseline review and M3 handoff

| Field | Value |
|---|---|
| Document ID | M2-DOC-007 |
| Revision | 0.6 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | M2 milestone, product requirements, design/usability, accessibility, test/traceability, and software-safety reviewers: unassigned |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#26](https://github.com/Entreco/elchrono/issues/26) |
| Review PR | [PR #32](https://github.com/Entreco/elchrono/pull/32) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](document-control.md#lifecycle-and-approval).

## Review outcome

**BLOCKED — the M2 evidence baseline does not currently satisfy its completion
gate.** Candidate requirements, designs, usability records, test protocol, and
traceability exist. The product owner has recorded D-01 through D-05. The
specific Clock clipping finding is resolved for the two checked prototype
layouts (500 × 1000 px and 1440 × 1200 px); supported layout configurations
remain undefined, so the complete no-clipping criterion and all product
protocol execution remain pending. Required human/specialist review and
exact-revision acceptance are missing, as are design approval and adequate
caregiver-usability evidence. No M2 completion, release approval, or
authorization to proceed past the M2 dependency is asserted.

The closure of issues [#22](https://github.com/Entreco/elchrono/issues/22)
through [#25](https://github.com/Entreco/elchrono/issues/25) records work
items' dispositions; it does not establish approval of their records, resolve
the evidence gaps, or satisfy the M2 milestone gate. In particular, issue #24
was closed at the owner's request with the caregiver evidence limitation
retained.

## Scope and evidence baseline

This is an integration check of the records and GitHub evidence listed below.
It is not a specialist usability, accessibility, product-design, or
software-safety review. The M2 documents revised here are candidates in PR #32;
the exact reviewed content is the PR head identified in GitHub when a human
reviews it. A PR merge or a document's branch-derived lifecycle is not
evidence of human acceptance or specialist review.

| Record | Exact source revision and evidence inspected |
|---|---|
| [M2-DOC-001 — Product requirements](../product/requirements.md) | Revision 0.4, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32); exact current PR head is the review baseline |
| [M2-DOC-004 — Accessible screen flows and theme prototypes](../design/accessible-screen-flows-and-theme-prototypes.md) | Revision 1.5, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32); includes revised Main/Settings concepts and a CSS correction for the clipped Clock label; exact current PR head is the review baseline |
| [M2-DOC-005 — Usability review plan](../usability/usability-plan.md) | Revision 0.5, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32) to align future tasks with current v1 decisions and the current design source; earlier sessions were not governed by it |
| [M2-DOC-006 — Usability findings and evidence status](../usability/usability-findings.md) | Revision 0.5, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32) to record owner-reported resolution and limited Chrome layout checks for F01, without altering retrospective evidence |
| [M2-DOC-002 — Product test protocol](../testing/test-protocol.md) | Revision 0.4, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32); records the limited prototype check separately from unexecuted product-test procedures |
| [M2-DOC-003 — Requirements traceability matrix](../safety/requirements-traceability-matrix.md) | Revision 2.2, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32); source revisions, v1 decisions, and limited F01 evidence linked; product verification pending |
| [M1-DOC-004 — Risk assessment](../safety/risk-assessment.md) | Revision 0.5, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32); owner D-01 disposition recorded, risk acceptability still undetermined pending competent review |
| [M1-DOC-009 — Risk traceability matrix](../safety/risk-traceability-matrix.md) | Revision 0.7, candidate; updated in [PR #32](https://github.com/Entreco/elchrono/pull/32); v1 scope reflected, risk review and verification pending |

The prior source PRs are merged, but their descriptions leave the accepting-human
identity and reviewed full head SHA pending despite checked acceptance boxes.
No formal PR review submissions or PR discussion comments were present in the
review evidence inspected for PRs #27–#31. Do not infer acceptance or specialist
review from a checked box, merge, or issue closure. Record the actual reviewer
scope, findings and dispositions, limitations, and final reviewed head SHA in
this record's review PR when those reviews occur; agents must leave the human
acceptance box unchecked. PR #32 remains open and does not have human acceptance.

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
| Stable requirements and measurable acceptance criteria | M2-DOC-001 revision 0.4 contains nine active REQ-NNN statements. M2-DOC-002 revision 0.4 has nine corresponding planned test cases with measurable criteria, and M2-DOC-003 revision 2.2 links requirements, risks, design/usability evidence, and tests. The records remain candidates; required review/acceptance is not evidenced, and no product protocol tests have run. | **Partial; not approved or verified.** |
| Usability plan and findings, or documented participant-review limitation | M2-DOC-005 revision 0.5 and M2-DOC-006 revision 0.5 describe two 2026-11-16 intended-user sessions and the evidence limitations. The plan postdates those sessions. There was no caregiver session; the owner elected not to arrange a replacement for this formative round. The sessions do not establish caregiver workflow usability, representative preferences, or validation. | **Documented formative evidence and limitations; the caregiver/review gate remains unmet.** |
| Design review and approval | M2-DOC-004 revision 1.5 updates Main/Settings concepts to the selected v1 behavior. Required product-design, accessibility, and usability reviews are unassigned; this PR does not claim reviewer acceptance. | **Not approved; required review and exact-head human acceptance are missing.** |
| Initial requirements-to-risk-to-test traceability | M2-DOC-003 revision 2.2 maps nine active requirements to six proposed risks and nine planned test cases, includes reverse coverage, and links findings, limitations, and decisions. It does not claim product-test execution, competent risk acceptance, control effectiveness, or approval. | **Initial candidate traceability exists; review and verification remain pending.** |
| Required completion and Settings behavior | REQ-002 specifies Settings-open pause and save-to-start from the full saved duration. REQ-010 specifies immediate reset with no completed state; music is deferred to v2. These decisions are reflected in the protocol, but no execution evidence exists. | **Product decisions recorded; review/approval and verification are absent.** |
| Usability and interface findings | M2-DOC-006-F01 records Clock remaining-time clipping, corroborated by the Session B screenshot. The owner reports resolution; Chrome 157.0.8103.79 layout checks at 500 × 1000 px and 1440 × 1200 px place all six Clock labels within the visualization stage with no horizontal page overflow. Supported layout configurations and application-level verification remain pending. | **Specific prototype finding closed for checked layouts; complete product criterion remains unverified.** |
| v1 caregiver-protection risk disposition | The product owner explicitly accepts unprotected Settings for v1. M1-DOC-004 revision 0.5 records the decision and bounded rationale; it does not record a competent acceptability determination. | **Product decision recorded; competent risk review and residual-risk disposition remain required.** |

The usability findings also retain G01–G05: retrospective session evidence and
no pre-session protocol, no contemporaneous notes, no direct caregiver
feedback, unobserved workflows/states, and no reported assistive-technology
evaluation. The limited formative sufficiency rationale permits continued
design iteration only; it waives no M2 gate or risk-control verification.

## Product decisions and v1 justification

The product owner supplied the dispositions below. They define
the current v1 proposal and are recorded in M2-DOC-001 revision 0.4 and
M2-DOC-004 revision 1.5. No reviewer acceptance is claimed.

| Decision | Current evidence and disposition |
|---|---|
| D-01 — Caregiver protection mechanism | No caregiver gate in v1; patients may change duration/theme. Product owner accepts this exposure for now. Justification: v1 is limited to ordinary waiting and Settings contain duration/theme only. Unintended changes can still cause confusion or alter a wait; no control is credited. M1-DOC-004 retains RISK-004 acceptability as undetermined pending competent review. |
| D-02 — Volume controls | No in-app volume controls in v1. Justification: no v1 audio capability exists to control; adding an unused control would add configuration without v1 playback. This does not decide system volume or promise controls in v2. |
| D-03 — Music and start behavior | Music integration is deferred to v2; no music start behavior or completion sound is in v1. Justification: without v1 playback there is no start behavior to select or misrepresent. Provider, data flow, playback, volume, failure, and sound behavior require separate review before v2. |
| D-04 — Timer start and Settings lifecycle | Opening Settings while running pauses the timer; saving valid settings starts a new countdown from the full saved duration. Justification: explicit save is the start action and does not silently resume timing against changed configuration. This behavior is unimplemented and unverified. |
| D-05 — Interruption recovery | No recovery behavior is provided or promised in v1; retain an interrupted warning and do not imply timing continuity. Justification: the v1 product makes no recovery or reliable-progress claim. RISK-002 remains unassessed; this decision is not residual-risk acceptance. |

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
| Appoint competent reviewers for requirements, design/usability, accessibility, test/traceability, software safety, and the M2 gate; review exact document revisions, resolve findings, and record actual scopes, dispositions, limitations, and final reviewed full SHA. | @Entreco; M2-DOC-001 through M2-DOC-007; [document controls](document-control.md) | Completed human and required specialist review evidence; acceptance must match the final PR head. This PR does not claim delegated reviewer acceptance. |
| Verify the Clock clipping correction at defined supported layouts. | @Entreco; M2-DOC-006-F01, M2-DOC-003, TP-REQ-003 and TP-REQ-006 | CSS correction is included in this PR; browser/viewport evidence is still required. Do not mark unavailable layout checks passed. |
| Assess and obtain appropriate caregiver/usability, consent/safeguarding, privacy, accessibility/input-method, and unobserved-workflow evidence. Keep the existing participant limitation and retrospective process gaps explicit until resolved. | @Entreco; M2-DOC-005/006, G01–G05 | Reviewed evidence or a justified, explicitly retained limitation; no claim of representative validation without supporting evidence. |
| Implement the recorded D-01 through D-05 decisions only after required requirements/design/risk review; review the explicit D-01 exposure and reassess M1 source gaps before relying on them. | @Entreco; M2-DOC-001, M2-DOC-004, M1-DOC-002, M1-DOC-004 | Review the product decisions and resulting risks; no control or residual-risk acceptance is inferred from this record. |
| For M3, complete its own architecture decision record; platform-support matrix and provider-feasibility findings; reproducible development setup; documentation templates; dependency/SOUP inventory and assessment; and working automated checks. Document data flows and avoid identifiable patient data unless an approved requirement makes it necessary. | @Entreco; [M3 milestone](https://github.com/Entreco/elchrono/milestone/3) | M3-specific reviewed and verified evidence, with limitations and traceability. M3 work does not substitute for M2 closure. |

The M2 outcome remains blocked until its completion gate is reviewed against
the final evidence and all blocking gaps are resolved or formally dispositioned
under the applicable project controls. Any continuation into M3 must preserve
the non-medical intended-use boundary and must not silently decide unresolved
product behavior.

## Review limitations

This integration check compares the linked records, their stated revisions,
the M2 milestone gate, and available PR descriptions/review metadata. It does
not reproduce participant sessions, test the prototype, execute protocol
cases, verify risk controls, establish reviewer competence, or decide legal
qualification. The owner directed that no delegated reviewer acceptance be
recorded; consequently, this change does not supply or imply required reviewer
acceptance. The PR review evidence remains incomplete as described above.
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
