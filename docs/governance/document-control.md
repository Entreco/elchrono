# Document controls and review responsibilities

| Field | Value |
|---|---|
| Document ID | M1-DOC-001 |
| Revision | 2.2 |
| Product owner | @Entreco |
| Document owner and approver | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Review evidence | [PR #9](https://github.com/Entreco/elchrono/pull/9); [PR #10](https://github.com/Entreco/elchrono/pull/10); [PR #15](https://github.com/Entreco/elchrono/pull/15); [PR #16](https://github.com/Entreco/elchrono/pull/16); [PR #17](https://github.com/Entreco/elchrono/pull/17); [PR #18](https://github.com/Entreco/elchrono/pull/18); [PR #19](https://github.com/Entreco/elchrono/pull/19); [PR #20](https://github.com/Entreco/elchrono/pull/20) |
| Work item | [Issue #1](https://github.com/Entreco/elchrono/issues/1) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](#lifecycle-and-approval).

## Purpose and scope

Keep El Chrono's foundation records identifiable, reviewable, and traceable from
draft through approval and subsequent change. Product documents belong under
the repository's `docs/` folder. The PR acceptance checklist is the review and approval
evidence: do not duplicate them in separate Markdown evidence files or require
an additional signature. Keep substantive decisions, limitations, and revision
history in the document; keep review checks, discussions, and dispositions in
the PR.

These are voluntary project controls, not a certification or a claim that every
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
engineering context, not an M1 safety or regulatory assessment. It follows the
same documentation lifecycle. The current application is a starter UI, not an
implemented timer.

## Responsibilities

| Role | Designation | Responsibility and competency |
|---|---|---|
| Product owner | @Entreco | Accountable for product boundaries, target markets, resourcing, and assignment of competent reviewers |
| Document owner | @Entreco for this record; explicitly assign for each subsequent record | Maintains revisions, evidence links, findings, and affected-record assessment |
| Author | Copilot for this record; identify the actual author on each record | Prepares evidence and identifies uncertainty; cannot invent findings resolution, qualifications, or approval |
| Document approver | @Entreco | Accepts the exact document revision after required reviews and findings resolution; this designation does not establish specialist competence |
| Regulatory reviewer | @Entreco for M1-DOC-003 (qualified reviewer, as confirmed by the reviewer); unassigned for other assessments | Assesses medical-device qualification and applicable obligations within the appointed markets |
| Software-safety reviewer | @Entreco for M1-DOC-005; competence confirmed by reviewer; conclusion recorded in revision 0.2 and exact-head acceptance evidenced by [PR #16](https://github.com/Entreco/elchrono/pull/16), with the record reconciliation in [PR #17](https://github.com/Entreco/elchrono/pull/17) | Reviews non-medical software scope and IEC 62304 applicability against the identified source revisions |
| Applicability-matrix approver | @Entreco accepted M1-DOC-006 revision 0.1 as product owner at the exact PR #18 head; see the acceptance evidence recorded in that matrix and reconciled by PR #19 | Approves the matrix's documented decisions and follow-up gates; this does not establish specialist competence or resolve an expressly open legal applicability question |
| Milestone approver | Unassigned; required before #8 can close | Checks the complete M1 evidence baseline and authorizes its handoff |

The product owner must record each appointment, the review scope, and confirmation
of relevant competence in this table or the applicable record. For M1-DOC-003,
@Entreco confirms qualification for the EU and US assessment; the review
conclusion and scope are recorded in that document. For M1-DOC-005, @Entreco
confirms competence as software-safety reviewer; the scope and conclusion are
recorded in revision 0.2 and the human acceptance of its exact PR #16 head is
recorded in revision 0.3. Do not collect CVs, private contact details, or
personal evidence in the repository.

For M1-DOC-006 revision 0.1, @Entreco's human acceptance is tied to PR #18 head
`fce218225968af2e1f5b2c766186b1a1eb0c9835`, merged as
`4e695cbdce992cedf7de2751448115aa74f2d3ab`; GitHub reports the merge commit
signature as verified. This is acceptance of the matrix as a controlled
applicability record and its staged gates. It does not extend @Entreco's
documented reviewer qualifications beyond their recorded scope or imply that
the future CRA, EAA, privacy, and market-specific determinations are complete.

A person may hold multiple roles if competent and permitted by the applicability
matrix; do not impose an unsupported independence requirement. Record overlapping
roles and how review challenges are handled. If independent review is required,
appoint another reviewer. AI assistance is not a qualified human review.

For this record, regulatory and software-safety reviewers must check the proposed
review arrangement and document controls within their respective scopes before
merge. M1-DOC-005 records the software-safety applicability conclusion against
the merged source baseline; its follow-up PR remains subject to human review
and acceptance. For subsequent records, identify required reviewers using
their subject matter and the applicability matrix. Owner approval alone
cannot substitute for required specialist review.

## Record format and identifiers

Every controlled record starts with: document ID, revision, owner,
author, required reviewers, approver, related issue, and review PR links.
Link this lifecycle rule rather than storing a Status or Approval field.
Include scope, source references, decisions and rationale,
assumptions, evidence gaps, findings, limitations, and revision history.

- Reserve document IDs in the foundation index below before creating records.
  IDs stay unchanged through renames and supersession and are never reused.
- Use numeric document revisions: this record is `0.6`; subsequent substantive
  changes increment the revision. A candidate may be numbered `1.0`, but a
  version number never implies approval. Branch/PR transitions alone do not
  require revision bumps or metadata commits.
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

Status is a property of the **viewed branch and its PR context**, not text
maintained inside a document. Apply the following rule to **all documents under
`docs/`**, including unchanged documents and the module layout record:

| Viewed context | Derived lifecycle status |
|---|---|
| Feature branch without an open PR targeting `main` | Draft |
| Feature branch with an open PR targeting `main` | In review |
| `main` after a PR merge | Approved and effective |

Opening a PR, including a draft PR, moves its branch's documents to In review.
This does not mean the candidate is ready to merge or reviewers are appointed.
Closing without merging returns the branch to Draft; reopening restores
In review. New commits on an open PR remain In review, but invalidate acceptance
of the previous head and require review of the new full head SHA.

Merging establishes the entire `docs/` tree on `main` as the approved and
effective documentation baseline. Unchanged documents inherit that baseline;
this does not claim they were individually re-reviewed in every PR. A retained
feature branch is not the effective baseline merely because its PR was merged:
use `main` or a commit-pinned merged baseline. Historical/detached snapshots
require recorded branch and merge provenance; a revision label, copied file, or
commit SHA without that provenance does not establish lifecycle status. Local
unsubmitted edits are not covered by acceptance of a PR head.

Approval here describes the **repository documentation lifecycle**. It does not
establish specialist competence, completed verification, regulatory
qualification, certification, milestone completion, or release authorization.
Missing evidence and open findings remain explicit even on `main`; historical
merges with incomplete acceptance evidence are process discrepancies, not proof
that review occurred. The pre-merge requirements below still apply.

Supersession or withdrawal is an explicit restriction on use of a baseline,
not a fourth branch-derived state. Retain the reason, replacement/withdrawal
reference, and historical review evidence; do not rely on withdrawn content
merely because it is present on `main`.

1. Open or reference an issue containing scope, acceptance criteria, dependencies,
   and expected verification. Assign owner and required reviewers.
2. Prepare the document under `docs/` on a feature branch. Link approved prerequisite
   revisions, or visibly mark unavailable prerequisites as blockers.
3. Submit a reviewable candidate through a PR and link it from the document.
   Identify document IDs/revisions, required reviewer scopes, acceptance checks,
   verification results, and limitations in the PR description and review.
4. Required reviewers must be appointed and given the exact candidate
   commit/path/revision, criteria, and evidence before their review.
   Reviewers check content and acceptance criteria, record findings, and confirm
   dispositions in PR threads against the final candidate. Changed content
   requires fresh approval; acceptance of an older candidate is not reused.
5. The document approver reviews the final diff, records their handle and full
   head SHA in the PR description, and checks **Human review completed and final
   revision accepted for merge**. Self-review by the PR author is permitted.
   This is our voluntary acceptance record, not GitHub's formal Approve action
   or independent review. Agents must leave this checkbox unchecked.
6. Before merge, update the foundation index with changed document revisions
   and review PR links. Merge only after required reviews, verification, and
   findings resolution. The merge makes all documents on `main` Approved and
   effective; do not make a follow-up commit to change lifecycle labels.
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
new candidate revision on a feature branch. Its lifecycle follows the branch/PR
rule above. Record the reason and impact on intended use, markets,
requirements, risks, controls, classification, applicability, verification,
releases, and dependent records. Notify affected owners and require re-review
where assumptions or conclusions change. Do not reuse acceptance of an older
candidate for changed content.

If new information undermines a baseline's safety or regulatory conclusion,
record its withdrawal/supersession and block affected work pending assessment;
do not continue relying on it merely because a replacement is unfinished.
First determine whether the software-classification scheme is applicable to
the product within its intended use and qualified scope. If it is not
applicable, record the rationale and reassess when the scope changes. If it is
applicable and Class A cannot be justified, escalate to the product owner and
qualified reviewers and revise the classification and affected process before
proceeding.

Maintain links from source document ID/section/revision to risk IDs, proposed
controls, future requirement IDs, verification evidence, and related issues.
Before M2, source sections and explicit "not yet established" entries are valid
placeholders, not completed requirements-to-test traceability. Planned controls
and future tests must remain distinguishable from implemented/verified controls.

Unknown required appointments, missing prerequisite approval, unresolved
qualification/classification, or findings affecting required safety/regulatory
conclusions block merge and issue closure. A non-blocking limitation needs a
justification, an owner, and linked follow-up; it cannot waive a completion gate.

## Foundation evidence index

Paths shown in code formatting are reserved deliverables; link the record when
it exists. Only populate review evidence after real review. Lifecycle follows
the viewed branch for every existing entry; do not duplicate it in this index.
No controlled requirements register exists at this revision. M1-DOC-005
revision 0.3 records the conclusion that IEC 62304 software safety
classification is not applicable to El Chrono's defined non-medical software,
and the human acceptance of the exact PR #16 head
`dc01e2ebc25f1458ef0a9a901a28128cdf7464ea`, merged as
`2a8791c9a1cf76a96768e3033a88af3f51f81732`. M1-DOC-004 proposes initial
product risks and controls, which remain subject to review.
M1-DOC-003 records the qualified EU/US regulatory qualification conclusion.

| ID | Record / planned path | Issue | Current evidence |
|---|---|---|---|
| M1-DOC-001 | [Document controls](document-control.md) | [#1](https://github.com/Entreco/elchrono/issues/1) | Owner @Entreco; revision 2.1 candidate; [PR #19](https://github.com/Entreco/elchrono/pull/19) reconciles M1-DOC-006 review evidence; issue #1 closed and [PR #9](https://github.com/Entreco/elchrono/pull/9) merged; [PR #10](https://github.com/Entreco/elchrono/pull/10) contains earlier changes; historical acceptance identity/SHA and other specialist review evidence remain unresolved |
| M1-DOC-002 | [Intended use](../product/intended-use.md) | [#2](https://github.com/Entreco/elchrono/issues/2) | Owner @Entreco; revision 0.7 candidate; EU and United States selected for assessment; exact-revision approval evidence unresolved; see [PR #10](https://github.com/Entreco/elchrono/pull/10), [PR #13](https://github.com/Entreco/elchrono/pull/13), and [PR #16](https://github.com/Entreco/elchrono/pull/16) |
| M1-DOC-003 | [Qualification and classification](../regulatory/qualification-and-classification.md) | [#3](https://github.com/Entreco/elchrono/issues/3) | Owner @Entreco; revision 0.6 candidate; [PR #13](https://github.com/Entreco/elchrono/pull/13) and [PR #16](https://github.com/Entreco/elchrono/pull/16); qualified reviewer concludes non-device in EU/US; human acceptance pending; replaces closed [PR #12](https://github.com/Entreco/elchrono/pull/12) after branch rename |
| M1-DOC-004 | [Risk assessment](../safety/risk-assessment.md) | [#4](https://github.com/Entreco/elchrono/issues/4) | Owner @Entreco; revision 0.2 candidate; [PR #14](https://github.com/Entreco/elchrono/pull/14) and [PR #16](https://github.com/Entreco/elchrono/pull/16); six proposed risks and controls; no approved requirement IDs or verified controls; separate risk review walkthrough remains pending |
| M1-DOC-005 | [Software safety classification](../safety/software-safety-classification.md) | [#5](https://github.com/Entreco/elchrono/issues/5) | Owner @Entreco; revision 0.3 records the conclusion and acceptance evidence; @Entreco accepted PR #16's exact head `dc01e2ebc25f1458ef0a9a901a28128cdf7464ea`, merged as `2a8791c9a1cf76a96768e3033a88af3f51f81732`; record reconciliation in [PR #17](https://github.com/Entreco/elchrono/pull/17); IEC 62304 classification not applicable |
| M1-DOC-006 | [Standards and documentation applicability matrix](applicability-matrix.md) | [#6](https://github.com/Entreco/elchrono/issues/6) | Owner @Entreco; revision 0.1 accepted in [PR #18](https://github.com/Entreco/elchrono/pull/18) at head `fce218225968af2e1f5b2c766186b1a1eb0c9835`; merged as signature-verified commit `4e695cbdce992cedf7de2751448115aa74f2d3ab`; revision 0.2 reconciles evidence in [PR #19](https://github.com/Entreco/elchrono/pull/19); open legal applicability actions remain gated before affected work |
| M1-DOC-007 | [Software development plan, revision 0.1](software-development-plan.md) | [#7](https://github.com/Entreco/elchrono/issues/7) | Owner @Entreco; revision 0.1 candidate in [PR #20](https://github.com/Entreco/elchrono/pull/20); exact-revision approval for M1-DOC-002 and M1-DOC-003 remains unresolved; M1-DOC-004/M1-DOC-009 review and risk walkthrough remain pending; do not close #7 until all evidence gates are met |
| M1-DOC-008 | `docs/governance/m1-review.md` | [#8](https://github.com/Entreco/elchrono/issues/8) | Not created |
| M1-DOC-009 | [Risk traceability matrix](../safety/risk-traceability-matrix.md) | [#4](https://github.com/Entreco/elchrono/issues/4) | Owner @Entreco; revision 0.1 candidate; [PR #14](https://github.com/Entreco/elchrono/pull/14); planned verification and test evidence pending; software-safety reviewer unassigned |

For each created record, replace its reserved path with a working link and record
its owner, revision, review PR, and commit-pinned merged baseline when available.
Document owners keep the index consistent with the records.

## Initial process walkthrough

This is an author walkthrough of this record, not a human review.
Revision 0.4 was submitted in [PR #9](https://github.com/Entreco/elchrono/pull/9)
and merged in [commit e4a1465](https://github.com/Entreco/elchrono/commit/e4a14656c1768e1bd29906fc7e29d03697b9a33c).
Under this lifecycle rule, that merged `docs/` baseline is Approved and
effective. PR #9 has a checked acceptance box, but still lists the accepting
human and reviewed full head SHA as pending. Retain this as an unresolved
process discrepancy; merge and issue closure do not prove the required human
or specialist reviews happened. Subsequent changes are linked to
[PR #10](https://github.com/Entreco/elchrono/pull/10).

| Step / criterion from #1 | Observed result | Remaining evidence |
|---|---|---|
| Identify accountable owner and approver | @Entreco designated by the user for both roles; @Entreco confirms qualification for the M1-DOC-003 regulatory review scope and competence for the M1-DOC-005 software-safety applicability review; exact-head acceptance for M1-DOC-005 is recorded in [PR #16](https://github.com/Entreco/elchrono/pull/16) | Other specialist appointments remain subject to their own scopes and evidence |
| Identify candidate and status | M1-DOC-001, revision 0.4, merged PR #9; its main baseline is Approved and effective, but acceptance identity and reviewed SHA are pending | Verify the required human/specialist review evidence without fabricating it |
| Exercise lifecycle | Feature branch without open PR: Draft; open PR #10 branch: In review; merged main baseline: Approved and effective | Lifecycle context does not establish reviewer appointments or completed reviews |
| Trace records and planned risks/requirements | Index reserves eight documents and distinguishes missing records; risk and requirement IDs are not presented as established | Populate actual records and cross-links when created |
| Exercise approval/change handling | Procedure binds acceptance to exact content and requires impact assessment and re-review after changes | Human acceptance of the final PR head and required specialist checks |

### Open findings

| Finding | Disposition | Resolution owner / evidence needed |
|---|---|---|
| M1-DOC-001-F01: Required regulatory and software-safety reviewers are unassigned | Partially resolved: @Entreco is assigned and confirms qualification for M1-DOC-003 EU/US qualification review and competence for M1-DOC-005; the non-applicability conclusion and exact-head acceptance are recorded in M1-DOC-005 revision 0.3 and [PR #16](https://github.com/Entreco/elchrono/pull/16) | Other specialist appointments remain subject to scope |
| M1-DOC-001-F02: Merged PR #9 has a checked acceptance box but does not identify an accepting human or reviewed full head SHA | Historical process discrepancy; unresolved | @Entreco verifies candidate identity and actual acceptance evidence; record any missing evidence honestly and obtain required review of the current candidate |
| M1-DOC-001-F03: Applicability matrix and milestone approver are not established | Later dependency; cannot waive later gates | @Entreco coordinates #6 and appointment before #8; re-review these controls if the matrix changes them |
| M1-DOC-001-F04: Required main protections have not been verified | Blocking; open | @Entreco configures protections and links effective-settings verification in PR #9 |

The M1-DOC-005 review links its assessed source revisions, findings disposition,
human acceptance, and the PR checklist in [PR #16](https://github.com/Entreco/elchrono/pull/16).
This evidence is limited to issue #5 and does not close other review scopes.
Issue #1 is closed in GitHub and its merged main baseline has the derived lifecycle
status, but the unresolved review evidence above still requires reconciliation.
Do not infer milestone completion or specialist assessment from issue closure,
merge, or the Approved and effective lifecycle status.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Initial document controls, assigned product ownership/approver, reserved M1 evidence index, and blocked walkthrough |
| 0.2 | Link the dedicated draft review record, historical baseline, requested human checks, and remaining evidence |
| 0.3 | Replace duplicate review evidence with human PR approvals; specify protected-main workflow and enforcement verification |
| 0.4 | Permit author self-review using a human acceptance checkbox and reviewed head SHA; distinguish the manual gate from GitHub protection |
| 0.5 | Add the M1-DOC-002 evidence-index entry and distinguish issue #1 closure/merge from its pending approval evidence |
| 0.6 | Derive lifecycle from branch/PR context for all docs; remove stored lifecycle labels and reconcile historical merge status with unresolved review evidence |
| 0.7 | Record the candidate EU/US qualification assessment and updated intended-use record in the foundation evidence index |
| 0.8 | Link the replacement review PR after the original PR closed during branch rename |
| 0.9 | Record the non-device qualification conclusion and update the intended-use evidence index |
| 1.0 | Record @Entreco's M1-DOC-003 regulatory reviewer assignment and update the evidence index to show qualified review disposition and pending PR acceptance |
| 1.1 | Update the evidence index for the qualified review conclusion and new intended-use and qualification revisions |
| 1.2 | Update the evidence index for the revisioned intended-use and qualification records |
| 1.3 | Add M1-DOC-004 and the M1-DOC-009 risk traceability matrix to the foundation evidence index; distinguish proposed controls from verified controls |
| 1.4 | Add the M1-DOC-005 software safety classification candidate and preserve its unresolved reviewer and prerequisite-approval gates |
| 1.5 | Link the M1-DOC-005 review PR and update the evidence index for this document-control revision |
| 1.6 | Record @Entreco's confirmed software-safety reviewer competence and the post-merge timing of the M1-DOC-005 applicability decision |
| 1.7 | Record the M1-DOC-005 non-applicability conclusion and pending follow-up PR acceptance |
| 1.8 | Update the evidence index for revised intended-use, qualification, risk, and classification-applicability records |
| 1.9 | Record M1-DOC-005's exact-head human acceptance and merged evidence in the reviewer assignment, findings, and foundation index; link the record-reconciliation PR |
| 2.0 | Add the M1-DOC-006 applicability-matrix candidate and its pending review scopes to the foundation evidence index |
| 2.1 | Record @Entreco's exact-head acceptance and GitHub-verified merge signature for M1-DOC-006 revision 0.1; distinguish matrix approval from future specialist applicability determinations |
| 2.2 | Add M1-DOC-007 revision 0.1 to the foundation evidence index and preserve its prerequisite approval and reviewer gaps |
