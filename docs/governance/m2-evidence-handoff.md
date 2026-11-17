# M2 evidence baseline review and M3 handoff

| Field | Value |
|---|---|
| Document ID | M2-DOC-007 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | M2 milestone, product requirements, design/usability, accessibility, and test/traceability reviewers: unassigned |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#26](https://github.com/Entreco/elchrono/issues/26) |
| Review PR | Pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](document-control.md#lifecycle-and-approval).

## Review outcome

**BLOCKED — the M2 evidence baseline does not currently satisfy its completion
gate.** Versioned candidate requirements, designs, usability records, a test
protocol, and an initial traceability matrix exist. However, required review
and exact-revision acceptance evidence is incomplete; the design-approval and
caregiver-usability gaps remain; the Clock clipping finding is unresolved;
and planned tests have not been executed. Product decisions D-01 through D-05
also remain open. No M2 completion, release approval, or authorization to
proceed past the M2 dependency is asserted.

The closure of issues [#22](https://github.com/Entreco/elchrono/issues/22)
through [#25](https://github.com/Entreco/elchrono/issues/25) records work
items' dispositions; it does not establish approval of their records, resolve
the evidence gaps, or satisfy the M2 milestone gate. In particular, issue #24
was closed at the owner's request with the caregiver evidence limitation
retained.

## Scope and evidence baseline

This is an integration check of the records and GitHub evidence listed below.
It is not a specialist usability, accessibility, product-design, or
software-safety review. Source links are pinned to the relevant merged PR head
so the reviewed content can be identified; the PR links provide change and
review context. A PR merge or a document's branch-derived lifecycle is not
evidence of human acceptance or specialist review.

| Record | Exact source revision and evidence inspected |
|---|---|
| [M2-DOC-001 — Product requirements](../product/requirements.md) | Revision 0.3, candidate; [PR #27](https://github.com/Entreco/elchrono/pull/27), head [`1753142fd67b00cac6be4fe50ca44bb03c09ca3b`](https://github.com/Entreco/elchrono/blob/1753142fd67b00cac6be4fe50ca44bb03c09ca3b/docs/product/requirements.md) |
| [M2-DOC-004 — Accessible screen flows and theme prototypes](../design/accessible-screen-flows-and-theme-prototypes.md) | Revision 1.3, candidate; revision 0.2 baseline in [PR #28](https://github.com/Entreco/elchrono/pull/28), head [`bebd683d51d3a9926746f08abf22bf4f2428d2b5`](https://github.com/Entreco/elchrono/blob/bebd683d51d3a9926746f08abf22bf4f2428d2b5/docs/design/accessible-screen-flows-and-theme-prototypes.md); revision 1.3 in [PR #29](https://github.com/Entreco/elchrono/pull/29), head [`841ceb7053131a5562cad9c0a1746cefbdcf4063`](https://github.com/Entreco/elchrono/blob/841ceb7053131a5562cad9c0a1746cefbdcf4063/docs/design/accessible-screen-flows-and-theme-prototypes.md) |
| [M2-DOC-005 — Usability review plan](../usability/usability-plan.md) | Revision 0.3, candidate; [PR #30](https://github.com/Entreco/elchrono/pull/30), head [`6440eae77eef858ab2df359f8a5542ba8145c39c`](https://github.com/Entreco/elchrono/blob/6440eae77eef858ab2df359f8a5542ba8145c39c/docs/usability/usability-plan.md) |
| [M2-DOC-006 — Usability findings and evidence status](../usability/usability-findings.md) | Revision 0.3, candidate; [PR #30](https://github.com/Entreco/elchrono/pull/30), head [`6440eae77eef858ab2df359f8a5542ba8145c39c`](https://github.com/Entreco/elchrono/blob/6440eae77eef858ab2df359f8a5542ba8145c39c/docs/usability/usability-findings.md) |
| [M2-DOC-002 — Product test protocol](../testing/test-protocol.md) | Revision 0.2, candidate; [PR #31](https://github.com/Entreco/elchrono/pull/31), head [`f494b4d7c290270d03ae0677f21458a486baf2ed`](https://github.com/Entreco/elchrono/blob/f494b4d7c290270d03ae0677f21458a486baf2ed/docs/testing/test-protocol.md) |
| [M2-DOC-003 — Requirements traceability matrix](../safety/requirements-traceability-matrix.md) | Revision 1.8, candidate; [PR #31](https://github.com/Entreco/elchrono/pull/31), head [`f494b4d7c290270d03ae0677f21458a486baf2ed`](https://github.com/Entreco/elchrono/blob/f494b4d7c290270d03ae0677f21458a486baf2ed/docs/safety/requirements-traceability-matrix.md) |

The linked PRs are merged, but their descriptions leave the accepting-human
identity and reviewed full head SHA pending despite checked acceptance boxes.
No formal PR review submissions or PR discussion comments were present in the
review evidence inspected for PRs #27–#31. Do not infer acceptance or specialist
review from a checked box, merge, or issue closure. Record the actual reviewer
scope, findings and dispositions, limitations, and final reviewed head SHA in
this record's review PR when those reviews occur; agents must leave the human
acceptance box unchecked.

The candidate inputs also depend on [M1-DOC-002, intended use revision 0.9](../product/intended-use.md)
and [M1-DOC-004, risk assessment revision 0.4](../safety/risk-assessment.md).
Their exact-revision approval and required risk review remain pending. The
M2 traceability matrix marks their contents and risk links as proposed.

## M2 completion-gate evidence

The gate below follows the [M2 milestone definition](https://github.com/Entreco/elchrono/milestone/2).
“Evidence exists” means a candidate record or finding is present; it does not
mean the item is approved, verified, or accepted for milestone completion.

| Gate item | Evidence and review check | Outcome |
|---|---|---|
| Stable requirements and measurable acceptance criteria | M2-DOC-001 revision 0.3 contains nine active REQ-NNN statements. M2-DOC-002 revision 0.2 has nine corresponding planned test cases with measurable criteria, and M2-DOC-003 revision 1.8 links the requirements, risks, design/usability evidence, and tests. The records remain candidates; required review/acceptance is not evidenced, and no protocol tests have run. | **Partial; not approved or verified.** |
| Usability plan and findings, or documented participant-review limitation | M2-DOC-005 revision 0.3 and M2-DOC-006 revision 0.3 describe two 2026-11-16 intended-user sessions and the evidence limitations. The plan postdates those sessions. There was no caregiver session; the owner elected not to arrange a replacement for this formative round. The sessions do not establish caregiver workflow usability, representative preferences, or validation. | **Documented formative evidence and limitations; the caregiver/review gate remains unmet.** |
| Design review and approval | M2-DOC-004 revision 1.3 contains candidate Main, Settings, About, state, and timer-visualization proposals. Required product-design, accessibility, and usability reviewers are unassigned; PR #29 does not record an accepting human or reviewed SHA. | **Not approved; required reviews and exact-revision acceptance are missing.** |
| Initial requirements-to-risk-to-test traceability | M2-DOC-003 revision 1.8 maps nine active requirements to six proposed risks and nine planned test cases, includes reverse coverage, and links findings, limitations, and open decisions. It does not claim test execution, risk acceptance, control effectiveness, or approval. | **Initial candidate traceability exists; review and verification remain pending.** |
| Required completion behavior | REQ-010 specifies immediate reset to the configured duration, stopping optional music, and no completed state. The protocol and design reflect this candidate behavior, but the requirements/design reviews and D-03 audio decision are pending; no execution evidence exists. | **Specified as a proposal; review/approval and verification are absent.** |
| Usability and interface findings | M2-DOC-006-F01 records Clock remaining-time text clipping, corroborated by a retained Session B screenshot. M2-DOC-003 links it to TP-REQ-003 and TP-REQ-006 and calls for correction and visibility checks. No correction, follow-up issue, or verification result is recorded. Supported layout configurations are also undefined, so the protocol requires blocked rather than passed results where they are unavailable. | **Unresolved; the no-clipping criterion is not verified.** |

The usability findings also retain G01–G05: retrospective session evidence and
no pre-session protocol, no contemporaneous notes, no direct caregiver
feedback, unobserved workflows/states, and no reported assistive-technology
evaluation. The limited formative sufficiency rationale permits continued
design iteration only; it waives no M2 gate or risk-control verification.

## Decision status

The following decisions remain unresolved in M2-DOC-001 revision 0.3 and
M2-DOC-004 revision 1.3. No default, protection mechanism, limit, or transition
is approved by this review.

| Decision | Current evidence and disposition |
|---|---|
| D-01 — Caregiver protection mechanism | **Open.** Settings must be caregiver-only, but the mechanism is undecided. The design shows only an abstract gate; no access-control design or verification is approved. |
| D-02 — Patient-accessible and caregiver-configured volume limits | **Open.** A labelled volume control is proposed; its range, limits, and default are not selected. |
| D-03 — Music start behavior and completion sounds | **Open.** Music remains optional. No provider, start behavior, or completion sound is selected. REQ-010's proposed stop-at-zero behavior is not evidence that these other choices are approved. |
| D-04 — Manual pause and reset behavior | **Open.** Paused state is depicted, but manual controls and transitions are not selected. Automatic reset in proposed REQ-010 does not decide manual reset. |
| D-05 — Interruption detection and recovery | **Open.** The interrupted presentation warns that progress may be out of date; detection, persistence, and recovery across backgrounding, sleep, termination, restart, and wall-clock changes remain undecided. |

## M3 handoff and dependencies

The [M3 milestone](https://github.com/Entreco/elchrono/milestone/3) depends on
M2. This record identifies the outstanding handoff; it does not clear that
dependency, approve architecture, or authorize implementation of unresolved
behavior.

| Action before relying on M2 as an approved input | Owner / source | Evidence needed |
|---|---|---|
| Appoint competent reviewers for requirements, design/usability, accessibility, test/traceability, and the M2 gate; review exact document revisions, resolve findings, and record actual scopes, dispositions, limitations, and final reviewed full SHA. | @Entreco; M2-DOC-001 through M2-DOC-007; [document controls](document-control.md) | Completed human and required specialist review evidence; acceptance must match the final PR head. |
| Correct the clipped Clock remaining-time content and establish the supported layout configurations used for the no-clipping checks. | @Entreco; M2-DOC-006-F01, M2-DOC-003, TP-REQ-003 and TP-REQ-006 | Tracked disposition and verification evidence; do not accept clipped content or mark unavailable layout checks passed. |
| Assess and obtain appropriate caregiver/usability, consent/safeguarding, privacy, accessibility/input-method, and unobserved-workflow evidence. Keep the existing participant limitation and retrospective process gaps explicit until resolved. | @Entreco; M2-DOC-005/006, G01–G05 | Reviewed evidence or a justified, explicitly retained limitation; no claim of representative validation without supporting evidence. |
| Resolve D-01 through D-05 through product, risk, and UX review before selecting affected architecture or implementation behavior. Reassess the M1 intended-use and risk-assessment review gaps before relying on their proposed inputs. | @Entreco; M2-DOC-001, M2-DOC-004, M1-DOC-002, M1-DOC-004 | Reviewed decisions and source baselines; no defaults inferred from prototypes or issue closure. |
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
qualification. The PR review evidence remains incomplete as described above.
Do not treat this record's existence, issue #26 closure, or the document's
branch-derived lifecycle as M2 approval.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record the M2 evidence baseline, blocked completion-gate outcome, open product decisions, unresolved usability/review/verification evidence, and dependent M3 actions for issue #26. |
