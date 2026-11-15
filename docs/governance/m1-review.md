# M1 evidence baseline review and M2 handoff

| Field | Value |
|---|---|
| Document ID | M1-DOC-008 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Designated M1 milestone approver (unassigned); competent reviewer for risk evidence (unassigned) |
| Approver | Designated M1 milestone approver (unassigned) |
| Related issue | [#8](https://github.com/Entreco/elchrono/issues/8) |
| Review PR | This draft PR for #8; link added when GitHub assigns its number |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](document-control.md#lifecycle-and-approval).

## Review outcome

**BLOCKED — the M1 evidence baseline does not currently satisfy its completion
gate.** This evidence review found missing exact-revision acceptance for the
intended-use and qualification records, pending competent risk review and
walkthrough evidence, and missing review appointments including the milestone
approver. The designated milestone approver has not issued a decision because
the role is unassigned. This record does not approve M1, close issue #8, or
authorize a milestone or project-status change.

The review is an integration check of repository and GitHub evidence, not a
qualified specialist review. Closed issues, merged PRs, and the repository
lifecycle of records do not by themselves prove human acceptance, reviewer
competence, or completion of a milestone gate.

## Scope and evidence baseline

Issue #8 requires an evidence map for the M1 milestone completion gate, checks
of links, approvals, responsibilities, consistency, source revisions, and risk
coverage, a pass/block decision, and owned M2 follow-ups. Its source snapshot
is `cbbff2c56e71f33652a8ba250400d9ebd06fe10c` (`main` when this review began).
The M1 gate is defined in the
[M1 milestone](https://github.com/Entreco/elchrono/milestone/1).

The following links identify the exact document revisions inspected at that
snapshot; they do not imply that a candidate was approved:

| Record | Exact source revision at baseline |
|---|---|
| M1-DOC-001 — document controls | [Revision 2.2](https://github.com/Entreco/elchrono/blob/cbbff2c56e71f33652a8ba250400d9ebd06fe10c/docs/governance/document-control.md) |
| M1-DOC-002 — intended use | [Revision 0.7](https://github.com/Entreco/elchrono/blob/cbbff2c56e71f33652a8ba250400d9ebd06fe10c/docs/product/intended-use.md) |
| M1-DOC-003 — qualification and classification | [Revision 0.6](https://github.com/Entreco/elchrono/blob/cbbff2c56e71f33652a8ba250400d9ebd06fe10c/docs/regulatory/qualification-and-classification.md) |
| M1-DOC-004 — risk assessment | [Revision 0.2](https://github.com/Entreco/elchrono/blob/cbbff2c56e71f33652a8ba250400d9ebd06fe10c/docs/safety/risk-assessment.md) |
| M1-DOC-005 — software safety classification | [Revision 0.3](https://github.com/Entreco/elchrono/blob/cbbff2c56e71f33652a8ba250400d9ebd06fe10c/docs/safety/software-safety-classification.md) |
| M1-DOC-006 — applicability matrix | [Revision 0.2](https://github.com/Entreco/elchrono/blob/cbbff2c56e71f33652a8ba250400d9ebd06fe10c/docs/governance/applicability-matrix.md) |
| M1-DOC-007 — software development plan | [Revision 0.1](https://github.com/Entreco/elchrono/blob/cbbff2c56e71f33652a8ba250400d9ebd06fe10c/docs/governance/software-development-plan.md) |
| M1-DOC-009 — risk traceability matrix | [Revision 0.1](https://github.com/Entreco/elchrono/blob/cbbff2c56e71f33652a8ba250400d9ebd06fe10c/docs/safety/risk-traceability-matrix.md) |

## M1 completion-gate evidence map

The “current evidence” column distinguishes candidate records from evidenced
acceptance. “Blocked” means the completion-gate item cannot be accepted from the
available evidence; it does not negate conclusions that are explicitly
recorded within their approved scope.

| Exact M1 completion-gate item | Record and review evidence | Gate check |
|---|---|---|
| Approved intended-use statement | M1-DOC-002 revision 0.7 is the current candidate. [Issue #2](https://github.com/Entreco/elchrono/issues/2) is closed; PRs [#10](https://github.com/Entreco/elchrono/pull/10), [#13](https://github.com/Entreco/elchrono/pull/13), and [#16](https://github.com/Entreco/elchrono/pull/16) are linked in the record. The exact-revision human acceptance is not evidenced. | **Blocked.** Do not treat issue closure or revision 0.6's use in later assessments as approval of revision 0.7. |
| Medical-device qualification and regulatory classification assessment | M1-DOC-003 revision 0.6 records a qualified EU/US non-device conclusion, distinguishes device classification from software classification, and links PRs #13 and #16. Its human acceptance remains pending; it assesses intended-use revision 0.6, while revision 0.7 is the current candidate. | **Blocked.** Confirm the exact intended-use scope and record human acceptance of the assessment revision. No device class or certification is claimed. |
| Justified IEC 62304 software safety classification | M1-DOC-005 revision 0.3 records non-applicability for the defined non-medical software scope, with no Class A, B, or C assigned. @Entreco accepted PR #16 head `dc01e2ebc25f1458ef0a9a901a28128cdf7464ea`, merged as `2a8791c9a1cf76a96768e3033a88af3f51f81732`; PR #17 reconciles the record. | **Evidence present for the stated scope.** Reassess if purpose, functionality, claims, or markets change. This is distinct from regulatory device classification and is not conformity or certification. |
| Initial risk assessment | M1-DOC-004 revision 0.2 and M1-DOC-009 revision 0.1 identify proposed risks, controls, and planned verification; PRs [#14](https://github.com/Entreco/elchrono/pull/14) and #16 are linked. The required risk-review walkthrough remains pending and the software-safety reviewer for this scope is unassigned. No controls are verified or residual risks accepted. | **Blocked.** Review the scenarios and controls, complete the required incorrect-timing, sound, and settings-access walkthrough, and reconcile the assessed source revisions. |
| Software development plan | M1-DOC-007 revision 0.1 was submitted in PR [#20](https://github.com/Entreco/elchrono/pull/20), head `8c5ef65d5ee3a924a563b3d04f3a85046ec35023`, and merged as `cbbff2c56e71f33652a8ba250400d9ebd06fe10c`. Its PR leaves the accepting human and reviewed SHA pending, despite a checked acceptance box; required lifecycle/risk reviewers and prerequisite evidence remain unresolved. Issue #7 is closed. | **Blocked.** The issue state and merge do not resolve the plan's documented approval and review gaps. Reassess it after its prerequisite records and reviewers are reconciled. |
| Documentation and applicability matrix | M1-DOC-006 revision 0.1 was accepted at PR #18 head `fce218225968af2e1f5b2c766186b1a1eb0c9835`, merged as `4e695cbdce992cedf7de2751448115aa74f2d3ab`. Revision 0.2 records that evidence in PR [#19](https://github.com/Entreco/elchrono/pull/19), whose accepting-human and reviewed-SHA fields remain pending. The matrix retains open legal, privacy, accessibility, cybersecurity, and market follow-ups. | **Partially evidenced; current revision acceptance pending.** Matrix acceptance does not resolve its separately scoped follow-up determinations. |
| Review responsibilities | M1-DOC-001 revision 2.2 identifies the product owner and some record-specific reviewer assignments. The risk reviewer, software-development/lifecycle reviewer, and M1 milestone approver remain unassigned. PR #9 has a checked acceptance box but no accepting identity or reviewed full SHA recorded; PRs #19 and #20 also have incomplete acceptance metadata. | **Blocked.** Appoint competent reviewers for each required scope and the milestone approver; reconcile acceptance evidence without inferring it from a checkbox. |

## Checks and cross-document findings

The following review checks were performed against the evidence snapshot:

- Compared every item in the M1 milestone completion gate with its controlled
  record, revision, related issue, and cited review PR.
- Traced the exact-head acceptance evidence for the software-safety decision
  and the accepted applicability-matrix revision; did not extend those
  approvals to other records or later revisions.
- Checked the document-control index, risk assessment, risk traceability
  matrix, applicability matrix, and development plan for stated source
  revisions, reviewer assignments, risk coverage, and open approval evidence.
- Confirmed issues #1–#7 are closed, but their closed states do not override
  approval gaps described in the records and PR descriptions. In particular,
  issue #7 is closed although PR #20 and M1-DOC-007 identify unresolved
  prerequisites and review assignments.
- Confirmed the M2 milestone exists but has no issues at this snapshot; no
  M2 follow-up issue IDs are available to link.

| Finding | Disposition and evidence needed | Owner |
|---|---|---|
| M1-DOC-008-F01 — Intended-use and qualification records lack exact-revision acceptance | **Open; blocking.** Record the human acceptance and reviewed full SHA for M1-DOC-002 revision 0.7 and M1-DOC-003 revision 0.6 after confirming the latter's assessed intended-use scope. | @Entreco; appoint/confirm required reviewers |
| M1-DOC-008-F02 — Initial risks lack competent review and walkthrough evidence | **Open; blocking.** Assign the required competent reviewer; complete and record the walkthrough for incorrect timing, sound, and settings access; disposition the proposed controls and source-version differences. | @Entreco; competent reviewer unassigned |
| M1-DOC-008-F03 — Development-plan evidence conflicts with issue #7 closure | **Open; blocking.** Reconcile M1-DOC-007's prerequisite approvals, lifecycle/risk reviewer assignments, and exact-head human acceptance; update the issue's completion evidence as authorized. | @Entreco |
| M1-DOC-008-F04 — M1 milestone approver is unassigned | **Open; blocking.** Appoint the designated approver with scope to decide the complete baseline and record their decision against the final evidence revision. | @Entreco as product owner |
| M1-DOC-008-F05 — Acceptance metadata is incomplete or inconsistent | **Open; blocking where used as approval evidence.** The checked boxes in PRs #9, #19, and #20 do not identify the accepting human and final reviewed SHA. Obtain real acceptance for the applicable exact revisions; do not reconstruct missing history. | @Entreco and each applicable record owner |
| M1-DOC-008-F06 — Risk records cite older source revisions | **Open; review required.** M1-DOC-004 revision 0.2 and M1-DOC-009 revision 0.1 refer to intended-use revision 0.6 and earlier risk/qualification inputs. The risk reviewer must confirm or update traceability against the current candidates before approving the risk baseline. | @Entreco; competent risk reviewer unassigned |
| M1-DOC-008-F07 — M2 follow-ups have no linked tracking issues | **Open; handoff limitation.** The actions below identify an owner and source records, but the M2 milestone has no issues at this snapshot. Create and link owned M2 issues before treating action tracking as complete. | @Entreco |

No human or specialist review of this integration record, finding disposition,
or milestone approval is represented as complete. Required review discussions
and dispositions must be recorded in the PR against its final head.

## M2 handoff actions and limitations

The M2 milestone is [Requirements and accessible UX](https://github.com/Entreco/elchrono/milestone/2);
it has no linked issues at the reviewed snapshot. The following actions are
assigned to @Entreco as product owner for issue planning. These links lead to
the source records and requirements, not to nonexistent M2 issues. Create and
link tracked M2 work before relying on the handoff; do not invent requirement
IDs or treat planned actions as approved behavior.

| M2 action | Owner and linked source | Limitation / gate |
|---|---|---|
| Define measurable requirements and validate accessible UX, themes, and state semantics with caregivers and representative users under appropriate consent and safeguards. | @Entreco; [project accessibility and timer reliability requirements](../../AGENTS.md#accessibility-and-timer-reliability), [intended-use roles](../product/intended-use.md#users-population-and-roles), and M1-DOC-009 RISK-005. | No controlled requirements register or representative usability evidence exists yet. Do not infer patient preferences or claim verification. |
| Decide caregiver protection, patient volume access/limits, completion sounds, and pause/reset behavior before implementation. | @Entreco; [intended-use limitations](../product/intended-use.md#foreseeable-use-and-limitations), M1-DOC-004 RISK-003/RISK-004 and controls, and M1-DOC-009. | These behaviors remain undecided; this handoff authorizes no defaults or implementation. |
| Specify and verify timer behavior and interruption recovery for backgrounding, sleep, process termination, restart, and wall-clock changes. | @Entreco; M1-DOC-004 RISK-001/RISK-002 and M1-DOC-009; [timer reliability requirements](../../AGENTS.md#accessibility-and-timer-reliability). | Recovery decisions and verified controls are absent. A monotonic timestamp alone does not provide persistence across device restarts. |
| Establish stable requirement IDs and link accepted requirements to risks, controls, verification, usability findings, and limitations. | @Entreco; M1-DOC-007 requirements/traceability plan and the M2 milestone. | No approved requirement IDs, completed risk verification, or owned M2 issue links exist at this snapshot. |

These are planned follow-ups, not approved requirements or findings of
acceptable residual risk. The non-medical intended-use boundary remains in
force; nothing here adds medical claims, diagnosis, treatment, monitoring,
safety-critical timing, or a certification claim.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record the M1 evidence map, review checks, open findings, blocked gate outcome, and planned M2 handoff actions |
