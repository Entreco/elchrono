# Document controls and review responsibilities

| Field | Value |
|---|---|
| Document ID | M1-DOC-001 |
| Revision | 0.1 |
| Status | Draft |
| Product owner | @Entreco |
| Document owner and approver | @Entreco |
| Draft author | Copilot; human accountability remains with the owner |
| Review record | [Initial process walkthrough](#initial-process-walkthrough) |
| Work item | [Issue #1](https://github.com/Entreco/elchrono/issues/1) |
| Approval | Pending; no approved baseline exists |

## Purpose and scope

Keep El Chrono's foundation records identifiable, reviewable, and traceable from
draft through approval and subsequent change. All product documents and review
records belong under the repository's `docs/` folder. GitHub issues and PRs
coordinate work and provide supporting evidence; they do not replace the
controlled records.

These are proposed project controls, not a certification or a claim that every
record is required by IEC 62304. The approved applicability matrix will determine
normative obligations and justified tailoring. Until it exists, do not omit a
milestone deliverable or claim an exemption.

El Chrono's product direction is non-medical: visual waiting-time support with
no medical claims or intended diagnosis, treatment, medical-condition monitoring,
or safety-critical medical timing. Medical-device-style documentation is
voluntary. Qualification review checks this boundary against actual functionality,
claims, and target-market rules; it is not a plan to obtain medical-device status.
Escalate any conflicting assessment to the product owner before affected work
continues. Separate voluntary process controls from applicable legal obligations.

The existing [module layout record](../architecture/module-layout.md) is
engineering context, not an approved M1 safety or regulatory record. The current
application is a starter UI, not an implemented timer.

## Responsibilities

| Role | Designation | Responsibility and competency |
|---|---|---|
| Product owner | @Entreco | Accountable for product boundaries, target markets, resourcing, and assignment of competent reviewers |
| Document owner | @Entreco for this record; explicitly assign for each subsequent record | Maintains revisions, evidence links, findings, and affected-record assessment |
| Author | Copilot for this draft; identify the actual author on each record | Prepares evidence and identifies uncertainty; cannot invent findings resolution, qualifications, or approval |
| Document approver | @Entreco | Accepts the exact document revision after required reviews and findings resolution; this designation does not establish specialist competence |
| Regulatory reviewer | Unassigned; blocking | Must be competent to assess qualification, classification, and obligations in the selected markets |
| Software-safety reviewer | Unassigned; blocking | Must be competent in the applicable IEC 62304 classification approach, software hazards, risk assumptions, and controls |
| Milestone approver | Unassigned; required before #8 can close | Checks the complete M1 evidence baseline and authorizes its handoff |

The product owner must record each appointment, the review scope, and confirmation
of relevant competence in this table or the applicable record. Use a concise
professional competency statement confirmed by the appointed reviewer; do not
collect CVs, private contact details, or personal evidence in the repository.

A person may hold multiple roles if competent and permitted by the applicability
matrix; do not impose an unsupported independence requirement. Record overlapping
roles and how review challenges are handled. If independent review is required,
appoint another reviewer. AI assistance is not a qualified human review.

For this record, regulatory and software-safety reviewers must check the proposed
review arrangement, classification escalation, and document controls within their
respective scopes before approval. For subsequent records, identify required
reviewers using their subject matter and the applicability matrix. Owner approval
alone cannot substitute for required specialist review.

## Record format and identifiers

Every controlled record starts with: document ID, revision, status, owner,
author, required reviewers, approver, related issue, review-record link, and
approval status/link. Include scope, source references, decisions and rationale,
assumptions, evidence gaps, findings, limitations, and revision history.

- Reserve document IDs in the foundation index below before creating records.
  IDs stay unchanged through renames and supersession and are never reused.
- Use numeric document revisions: this draft is `0.1`; subsequent reviewable
  drafts increment the minor revision. The first approved candidate may be
  `1.0`, with later candidates incrementing the revision. A version number never
  implies approval.
- Record stable risk IDs as `RISK-001`, `RISK-002`, etc. in the initial risk
  assessment. Allocate them in that record and retain retired entries.
- M2 establishes the requirement ID scheme and controlled requirements register.
  Do not manufacture requirement IDs or mark proposed risks/controls verified.
- Give review findings stable IDs within each document, such as
  `M1-DOC-001-F01`. Record the finding, reviewer, blocking/non-blocking rationale,
  resolution evidence, and reviewer disposition.

Links to supporting issues may evolve; approvals must identify an immutable
Git commit SHA and document path in addition to the document ID and revision.
Use commit-pinned links for approved source records. Never identify the reviewed
content only as `main`, "latest", a mutable PR head, or a document revision label.

## Lifecycle and approval

| State | Meaning and entry/exit evidence |
|---|---|
| Draft | Proposed or changed content; missing decisions are explicit. Not an approved development input |
| In review | Required reviewers are appointed and given an exact candidate commit/path/revision with criteria and evidence. Any content change requires review of the new candidate |
| Approved | Required human reviews and verification are complete, blocking findings resolved, limitations owned, and approver acceptance recorded for the exact candidate |
| Superseded | An approved replacement or explicit withdrawal identifies why the previous baseline must no longer be used for new work; history and approval evidence remain accessible |

1. Open or reference an issue containing scope, acceptance criteria, dependencies,
   and expected verification. Assign owner and required reviewers.
2. Prepare the draft and evidence under `docs/`. Link approved prerequisite
   revisions, or visibly mark unavailable prerequisites as blockers.
3. Submit a reviewable candidate through a PR. Record its commit SHA, path,
   revision, reviewer assignments, and checks in a review record.
4. Reviewers check content and acceptance criteria, record findings, and confirm
   dispositions against the final candidate. Changed content invalidates
   acceptance of the old candidate for the new revision.
5. The approver records explicit acceptance only after required reviews finish.
   Merging a PR, checking a box, or an automated check does not itself constitute
   controlled approval.
6. Store the review/approval record under `docs/` and update the index. That
   record identifies the approved content's earlier immutable commit; it does
   not try to embed its own final commit SHA. A later metadata-only commit may
   link the approval, provided it does not change the approved content.
7. Close the issue only when its criteria and completion evidence are satisfied.
   Do not close the milestone or change project status implicitly.

An approval record identifies document ID/revision/path, full candidate commit
SHA and pinned link, reviewers and scopes, verification results, findings and
dispositions, limitations and follow-up owners, approver, decision, and date.
Link actual human GitHub review/decision evidence. Preserve approvals in Git
history; do not rewrite an old approval to cover changed content.

## Changes, blockers, and traceability

When approved content changes, retain the previous approved commit and create a
new draft revision. Record the reason and impact on intended use, markets,
requirements, risks, controls, classification, applicability, verification,
releases, and dependent records. Notify affected owners and require re-review
where assumptions or conclusions change. Do not carry forward approval silently.

If new information undermines a baseline's safety or regulatory conclusion,
record its withdrawal/supersession and block affected work pending assessment;
do not continue relying on it merely because a replacement is unfinished.
If Class A cannot be justified, escalate to the product owner and qualified
reviewers and revise the classification and affected process before proceeding.

Maintain links from source document ID/section/revision to risk IDs, proposed
controls, future requirement IDs, verification evidence, and related issues.
Before M2, source sections and explicit "not yet established" entries are valid
placeholders, not completed requirements-to-test traceability. Planned controls
and future tests must remain distinguishable from implemented/verified controls.

Unknown required appointments, missing prerequisite approval, unresolved
qualification/classification, or findings affecting required safety/regulatory
conclusions block approval and issue closure. A non-blocking limitation needs a
justification, an owner, and linked follow-up; it cannot waive a completion gate.

## Foundation evidence index

Paths in code formatting are reserved deliverables, not links to existing files.
Only populate approval evidence after real review. No requirement/risk register
or approved M1 record exists at this revision.

| ID | Record / planned path | Issue | Current evidence |
|---|---|---|---|
| M1-DOC-001 | [Document controls](document-control.md) | [#1](https://github.com/Entreco/elchrono/issues/1) | Draft 0.1; walkthrough below; human review and approval pending |
| M1-DOC-002 | `docs/product/intended-use.md` | [#2](https://github.com/Entreco/elchrono/issues/2) | Not created |
| M1-DOC-003 | `docs/regulatory/qualification-and-classification.md` | [#3](https://github.com/Entreco/elchrono/issues/3) | Not created |
| M1-DOC-004 | `docs/safety/initial-risk-assessment.md` | [#4](https://github.com/Entreco/elchrono/issues/4) | Not created |
| M1-DOC-005 | `docs/safety/software-safety-classification.md` | [#5](https://github.com/Entreco/elchrono/issues/5) | Not created |
| M1-DOC-006 | `docs/governance/applicability-matrix.md` | [#6](https://github.com/Entreco/elchrono/issues/6) | Not created |
| M1-DOC-007 | `docs/governance/software-development-plan.md` | [#7](https://github.com/Entreco/elchrono/issues/7) | Not created |
| M1-DOC-008 | `docs/governance/m1-review.md` | [#8](https://github.com/Entreco/elchrono/issues/8) | Not created |

For each created record, replace its reserved path with a working link and record
its owner, revision/status, review record, and approved commit when available.
Document owners keep the index consistent with the records.

## Initial process walkthrough

This is a draft-author walkthrough of this record, not a human review or approval.
The candidate is the working-tree draft 0.1; no immutable candidate commit or PR
has been published. This walkthrough cannot satisfy #1's completed-review gate.

| Step / criterion from #1 | Observed result | Remaining evidence |
|---|---|---|
| Identify accountable owner and approver | @Entreco designated by the user for both roles | Confirm specialist appointments and competence |
| Identify candidate and status | M1-DOC-001, draft 0.1, repository path, no approval claimed | Commit-pinned candidate and PR |
| Exercise lifecycle | This draft stops before In review because required reviewers are unassigned | Real reviewer checks and findings/dispositions |
| Trace records and planned risks/requirements | Index reserves eight documents and distinguishes missing records; risk and requirement IDs are not presented as established | Populate actual records and cross-links when created |
| Exercise approval/change handling | Procedure binds acceptance to exact content and requires impact assessment and re-review after changes | Human review of this procedure and explicit approval |

### Open findings

| Finding | Disposition | Resolution owner / evidence needed |
|---|---|---|
| M1-DOC-001-F01: Required regulatory and software-safety reviewers are unassigned | Blocking; open | @Entreco appoints reviewers; reviewers confirm competence and scope |
| M1-DOC-001-F02: No immutable candidate, human review, or approval record | Blocking; open | @Entreco coordinates candidate publication, human review, findings resolution, and approval |
| M1-DOC-001-F03: Applicability matrix and milestone approver are not established | Later dependency; cannot waive later gates | @Entreco coordinates #6 and appointment before #8; re-review these controls if the matrix changes them |

The first completed worked example will link this record's actual candidate,
verification, human findings resolution, and approval under `docs/`. Until then,
#1 remains open and this document remains a proposal.

## Revision history

| Revision | Change | Approval |
|---|---|---|
| 0.1 | Initial document controls, assigned product ownership/approver, reserved M1 evidence index, and blocked walkthrough | Pending |
