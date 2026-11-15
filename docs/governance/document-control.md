# Document controls and review responsibilities

| Field | Value |
|---|---|
| Document ID | M1-DOC-001 |
| Revision | 0.5 |
| Status | Draft |
| Product owner | @Entreco |
| Document owner and approver | @Entreco |
| Draft author | Copilot; human accountability remains with the owner |
| Review evidence | [PR #9](https://github.com/Entreco/elchrono/pull/9); human approval pending |
| Work item | [Issue #1](https://github.com/Entreco/elchrono/issues/1) |
| Approval | Pending; no approved baseline exists |

## Purpose and scope

Keep El Chrono's foundation records identifiable, reviewable, and traceable from
draft through approval and subsequent change. Product documents belong under
the repository's `docs/` folder. The PR acceptance checklist is the review and approval
evidence: do not duplicate them in separate Markdown evidence files or require
an additional signature. Keep substantive decisions, limitations, and revision
history in the document; keep review checks, discussions, and dispositions in
the PR.

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
author, required reviewers, approver, related issue, review PR link, and
approval status/link. Include scope, source references, decisions and rationale,
assumptions, evidence gaps, findings, limitations, and revision history.

- Reserve document IDs in the foundation index below before creating records.
  IDs stay unchanged through renames and supersession and are never reused.
- Use numeric document revisions: this draft is `0.5`; subsequent reviewable
  drafts increment the minor revision. The first approved candidate may be
  `1.0`, with later candidates incrementing the revision. A version number never
  implies approval.
- Record stable risk IDs as `RISK-001`, `RISK-002`, etc. in the initial risk
  assessment. Allocate them in that record and retain retired entries.
- M2 establishes the requirement ID scheme and controlled requirements register.
  Do not manufacture requirement IDs or mark proposed risks/controls verified.
- Give review findings stable IDs within each document, such as
  `M1-DOC-001-F01`. Record the finding, reviewer, blocking/non-blocking rationale,
  resolution evidence, and reviewer disposition in PR review threads. Document
  unresolved limitations without copying the complete discussion.

Link the PR from the document. The accepting human records their GitHub handle
and the full reviewed head commit SHA beside the acceptance checkbox; use that
SHA and document path to locate the accepted revision. The PR edit history and
merge history provide supporting evidence, not an independent signature.
Use commit-pinned links for approved source records.
Never identify reviewed content only as `main`, "latest", or a revision label.

## Lifecycle and approval

| State | Meaning and entry/exit evidence |
|---|---|
| Draft | Proposed or changed content; missing decisions are explicit. Not an approved development input |
| In review | Required reviewers are appointed and given an exact candidate commit/path/revision with criteria and evidence. Any content change requires review of the new candidate |
| Approved | Required reviews and verification are complete, blocking findings resolved, limitations owned, the human acceptance checkbox covers the final head SHA, and the accepted revision is merged |
| Superseded | An approved replacement or explicit withdrawal identifies why the previous baseline must no longer be used for new work; history and approval evidence remain accessible |

1. Open or reference an issue containing scope, acceptance criteria, dependencies,
   and expected verification. Assign owner and required reviewers.
2. Prepare the document under `docs/` on a feature branch. Link approved prerequisite
   revisions, or visibly mark unavailable prerequisites as blockers.
3. Submit a reviewable candidate through a PR and link it from the document.
   Identify document IDs/revisions, required reviewer scopes, acceptance checks,
   verification results, and limitations in the PR description and review.
4. Reviewers check content and acceptance criteria, record findings, and confirm
   dispositions in PR threads against the final candidate. Changed content
   requires fresh approval; acceptance of an older candidate is not reused.
5. The document approver reviews the final diff, records their handle and full
   head SHA in the PR description, and checks **Human review completed and final
   revision accepted for merge**. Self-review by the PR author is permitted.
   This is our voluntary acceptance record, not GitHub's formal Approve action
   or independent review. Agents must leave this checkbox unchecked.
6. Merge only after required reviews, verification, and findings resolution.
   Update the foundation index with the document revision and review PR. For
   documents retaining a Draft/In review label in the reviewed commit, the PR
   acceptance checklist and merge establish the baseline; the label is not evidence of
   approval. Any later metadata update also goes through a reviewed PR.
7. Close the issue only when its criteria and completion evidence are satisfied.
   Do not close the milestone or change project status implicitly.

The PR checklist, discussion, and merge history supply acceptance and its scope.
Required specialist competence and review scope
must still be confirmed; a general approval does not imply specialist review.
Retain the PR and Git history rather than rewriting approval to cover changes.

## Required protection of main

Enforce these controls through GitHub branch protection or an active ruleset:

- No direct pushes to `main`; all changes enter through a PR.
- Set the required formal GitHub approval count to zero for the solo workflow:
  GitHub does not allow PR authors to approve their own PRs.
- Require resolution of review conversations before merge.
- Do not allow administrator, integration, or other bypasses of these controls;
  block force pushes and deletion of `main`.

Verify the effective settings and permitted/bypass actors before relying on this
workflow. This document specifies the required protections; it does not claim
they have been configured or verified.

Human acceptance is a **manual pre-merge gate**, separate from branch protection:
the merger checks the acceptance box is checked, the accepting handle identifies
a human, and the recorded SHA equals the final PR head. After any new commit,
clear the checkbox and repeat review for the new head. Required specialist
reviews still apply where the document requires them.

GitHub does not natively block merging because a description checkbox is
unchecked, nor prove who performed the review. Do not describe this checklist
as technically enforced human approval. A required automated checklist check
would be separate engineering work and could validate fields, not prove that a
human actually reviewed the content.

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
| M1-DOC-001 | [Document controls](document-control.md) | [#1](https://github.com/Entreco/elchrono/issues/1) | Owner @Entreco; Draft 0.5; issue #1 closed and [PR #9](https://github.com/Entreco/elchrono/pull/9) merged; acceptance and required specialist review evidence pending |
| M1-DOC-002 | [Intended use](../product/intended-use.md) | [#2](https://github.com/Entreco/elchrono/issues/2) | Draft 0.1; target jurisdictions unselected; prerequisite approval evidence unresolved |
| M1-DOC-003 | `docs/regulatory/qualification-and-classification.md` | [#3](https://github.com/Entreco/elchrono/issues/3) | Not created |
| M1-DOC-004 | `docs/safety/initial-risk-assessment.md` | [#4](https://github.com/Entreco/elchrono/issues/4) | Not created |
| M1-DOC-005 | `docs/safety/software-safety-classification.md` | [#5](https://github.com/Entreco/elchrono/issues/5) | Not created |
| M1-DOC-006 | `docs/governance/applicability-matrix.md` | [#6](https://github.com/Entreco/elchrono/issues/6) | Not created |
| M1-DOC-007 | `docs/governance/software-development-plan.md` | [#7](https://github.com/Entreco/elchrono/issues/7) | Not created |
| M1-DOC-008 | `docs/governance/m1-review.md` | [#8](https://github.com/Entreco/elchrono/issues/8) | Not created |

For each created record, replace its reserved path with a working link and record
its owner, revision/status, review PR, and approved commit when available.
Document owners keep the index consistent with the records.

## Initial process walkthrough

This is a draft-author walkthrough of this record, not a human review or approval.
Draft 0.4 was submitted in [PR #9](https://github.com/Entreco/elchrono/pull/9),
which is merged. The PR body still lists the accepting human and reviewed head
SHA as pending. The merge and issue closure do not establish approval of this
record or satisfy the required specialist reviews.

| Step / criterion from #1 | Observed result | Remaining evidence |
|---|---|---|
| Identify accountable owner and approver | @Entreco designated by the user for both roles | Confirm specialist appointments and competence |
| Identify candidate and status | M1-DOC-001, draft 0.4, merged PR #9; acceptance identity and reviewed SHA are pending | Verify the exact candidate and required human/specialist review evidence |
| Exercise lifecycle | This draft stops before In review because required reviewers are unassigned | Real reviewer checks and findings/dispositions |
| Trace records and planned risks/requirements | Index reserves eight documents and distinguishes missing records; risk and requirement IDs are not presented as established | Populate actual records and cross-links when created |
| Exercise approval/change handling | Procedure binds acceptance to exact content and requires impact assessment and re-review after changes | Human review of this procedure and explicit approval |

### Open findings

| Finding | Disposition | Resolution owner / evidence needed |
|---|---|---|
| M1-DOC-001-F01: Required regulatory and software-safety reviewers are unassigned | Blocking; open | @Entreco appoints reviewers; reviewers confirm competence and scope |
| M1-DOC-001-F02: The merged PR does not identify an accepting human or reviewed full head SHA | Blocking; open | @Entreco verifies candidate identity, required reviews, findings resolution, and acceptance evidence for the final SHA |
| M1-DOC-001-F03: Applicability matrix and milestone approver are not established | Later dependency; cannot waive later gates | @Entreco coordinates #6 and appointment before #8; re-review these controls if the matrix changes them |
| M1-DOC-001-F04: Required main protections have not been verified | Blocking; open | @Entreco configures protections and links effective-settings verification in PR #9 |

The first completed worked example will link this record's actual candidate,
verification, human findings resolution, and the PR acceptance checklist. Issue
#1 is closed in GitHub, but this document remains a proposal until its required
review and approval evidence is verified; issue closure and merge alone do not
establish an approved baseline.

## Revision history

| Revision | Change | Approval |
|---|---|---|
| 0.1 | Initial document controls, assigned product ownership/approver, reserved M1 evidence index, and blocked walkthrough | Pending |
| 0.2 | Link the dedicated draft review record, historical baseline, requested human checks, and remaining evidence | Pending |
| 0.3 | Replace duplicate review evidence with human PR approvals; specify protected-main workflow and enforcement verification | Pending |
| 0.4 | Permit author self-review using a human acceptance checkbox and reviewed head SHA; distinguish the manual gate from GitHub protection | Pending |
| 0.5 | Add the M1-DOC-002 evidence-index entry and distinguish issue #1 closure/merge from its pending approval evidence | Pending |
