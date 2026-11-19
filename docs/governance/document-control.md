# Document controls and review responsibilities

| Field | Value |
|---|---|
| Document ID | M1-DOC-001 |
| Revision | 2.52 |
| Product owner | @Entreco |
| Document owner and approver | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Review evidence | [PR #9](https://github.com/Entreco/elchrono/pull/9); [PR #10](https://github.com/Entreco/elchrono/pull/10); [PR #15](https://github.com/Entreco/elchrono/pull/15); [PR #16](https://github.com/Entreco/elchrono/pull/16); [PR #17](https://github.com/Entreco/elchrono/pull/17); [PR #18](https://github.com/Entreco/elchrono/pull/18); [PR #19](https://github.com/Entreco/elchrono/pull/19); [PR #20](https://github.com/Entreco/elchrono/pull/20); [PR #21](https://github.com/Entreco/elchrono/pull/21) (merged; M1 evidence review); [PR #27](https://github.com/Entreco/elchrono/pull/27); [PR #28](https://github.com/Entreco/elchrono/pull/28); [PR #29](https://github.com/Entreco/elchrono/pull/29); [PR #30](https://github.com/Entreco/elchrono/pull/30); [PR #31](https://github.com/Entreco/elchrono/pull/31); [PR #32](https://github.com/Entreco/elchrono/pull/32); [PR #41](https://github.com/Entreco/elchrono/pull/41); [PR #42](https://github.com/Entreco/elchrono/pull/42); [PR #43](https://github.com/Entreco/elchrono/pull/43); [PR #49](https://github.com/Entreco/elchrono/pull/49); [PR #50](https://github.com/Entreco/elchrono/pull/50); [PR #52](https://github.com/Entreco/elchrono/pull/52); [PR #54](https://github.com/Entreco/elchrono/pull/54); [PR #56](https://github.com/Entreco/elchrono/pull/56); [PR #57](https://github.com/Entreco/elchrono/pull/57); issue #47: [PR #59](https://github.com/Entreco/elchrono/pull/59). Prior acceptance evidence and limitations retain their recorded scope; exact final-head acceptance for this change remains pending. |
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

The [module layout record](../architecture/module-layout.md) is engineering
context, not an M1 safety or regulatory assessment. It follows the same
documentation lifecycle. The issue #47 candidate implements a browser MVP
using that module structure; its product verification and platform support
remain limited as stated in the linked review PR.

## Responsibilities

| Role | Designation | Responsibility and competency |
|---|---|---|
| Product owner | @Entreco | Accountable for product boundaries, target markets, resourcing, and assignment of competent reviewers |
| Document owner | @Entreco for this record; explicitly assign for each subsequent record | Maintains revisions, evidence links, findings, and affected-record assessment |
| Author | Copilot for this record; identify the actual author on each record | Prepares evidence and identifies uncertainty; cannot invent findings resolution, qualifications, or approval |
| Document approver | @Entreco | Accepts the exact document revision after required reviews and findings resolution; this designation does not establish specialist competence |
| Regulatory reviewer | @Entreco for M1-DOC-003 (EU/US qualification scope); for the issue #47 M1/M2/M3 review, @Entreco is designated by the product owner across applicable review disciplines and self-attests competence; final exact-head acceptance pending | Assesses medical-device qualification and applicable obligations within the appointed markets; open legal/market determinations remain open |
| Software-safety reviewer | @Entreco for M1-DOC-005; competence confirmed by reviewer; conclusion recorded in revision 0.2 and exact-head acceptance evidenced by [PR #16](https://github.com/Entreco/elchrono/pull/16), with the record reconciliation in [PR #17](https://github.com/Entreco/elchrono/pull/17); designated for the issue #47 review scopes | Reviews non-medical software scope and IEC 62304 applicability against the identified source revisions; does not imply risk-control verification or risk acceptance |
| Applicability-matrix approver | @Entreco accepted M1-DOC-006 revision 0.1 as product owner at the exact PR #18 head; see the acceptance evidence recorded in that matrix and reconciled by PR #19 | Approves the matrix's documented decisions and follow-up gates; this does not establish specialist competence or resolve an expressly open legal applicability question |
| Milestone approver | @Entreco, designated by the product owner for the current M1 review; exact final-head acceptance pending | Checks the M1 evidence baseline and its explicit limitations; this designation does not close issue #8 or resolve open market/applicability follow-ups |

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
and acceptance. For subsequent records, identify required reviewers using their subject matter
and the applicability matrix. For the issue #47 M1/M2/M3 review, @Entreco has
designated themself as the human reviewer across applicable disciplines and
confirmed that appointment; this self-attested designation is not an
independent credential audit. Open legal, usability, accessibility, platform,
and verification evidence remains explicit and is not converted into a
completed determination by reviewer appointment.

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
M2-DOC-001 is the candidate requirements register; its requirement IDs
remain proposed pending review. Acceptance criteria belong in M2-DOC-002,
and requirement/source/risk/design/usability/test/evidence relationships
belong in M2-DOC-003. REQ-007 was retired before approval and must not be
reused. M1-DOC-005
revision 0.3 records the conclusion that IEC 62304 software safety
classification is not applicable to El Chrono's defined non-medical software,
and the human acceptance of the exact PR #16 head
`dc01e2ebc25f1458ef0a9a901a28128cdf7464ea`, merged as
`2a8791c9a1cf76a96768e3033a88af3f51f81732`. M1-DOC-004 revision 0.5 records the product owner's D-01 decision to ship v1
without a caregiver gate; its risk acceptability and controls remain subject
to competent review.
M1-DOC-003 records the qualified EU/US regulatory qualification conclusion.

| ID | Record / planned path | Issue | Current evidence |
|---|---|---|---|
| M1-DOC-001 | [Document controls](document-control.md) | [#1](https://github.com/Entreco/elchrono/issues/1) | Owner @Entreco; revision 2.52 updates current review assignments and evidence index; historical PR #9 acceptance metadata remains a process discrepancy |
| M1-DOC-002 | [Intended use](../product/intended-use.md) | [#2](https://github.com/Entreco/elchrono/issues/2) | Owner @Entreco; revision 1.0 candidate defines ordinary-waiting use, 1-minute to 24-hour duration, and the non-medical boundary; owner review disposition is recorded in M1-DOC-008; final exact-head acceptance pending |
| M1-DOC-003 | [Qualification and classification](../regulatory/qualification-and-classification.md) | [#3](https://github.com/Entreco/elchrono/issues/3) | Owner @Entreco; revision 0.7 assesses intended-use revision 1.0 and records the qualified reviewer's confirmation that the defined purpose remains non-medical; market-specific legal/privacy/accessibility follow-ups remain open; final exact-head acceptance pending |
| M1-DOC-004 | [Risk assessment](../safety/risk-assessment.md) | [#4](https://github.com/Entreco/elchrono/issues/4) | Owner @Entreco; revision 0.7 records the bounded v1 unprotected-Settings disposition, scenario walkthrough, and focused domain-test evidence; controls are not claimed effective and no post-control score is claimed; owner review disposition is recorded in M1-DOC-008; exact-head acceptance pending |
| M1-DOC-005 | [Software safety classification](../safety/software-safety-classification.md) | [#5](https://github.com/Entreco/elchrono/issues/5) | Owner @Entreco; revision 0.3 records the conclusion and acceptance evidence; @Entreco accepted PR #16's exact head `dc01e2ebc25f1458ef0a9a901a28128cdf7464ea`, merged as `2a8791c9a1cf76a96768e3033a88af3f51f81732`; record reconciliation in [PR #17](https://github.com/Entreco/elchrono/pull/17); IEC 62304 classification not applicable |
| M1-DOC-006 | [Standards and documentation applicability matrix](applicability-matrix.md) | [#6](https://github.com/Entreco/elchrono/issues/6) | Owner @Entreco; revision 1.1 aligns current issue #47 source revisions and reviewer appointments; legal, privacy, accessibility, cybersecurity, and market-specific determinations remain open; exact-head acceptance pending |
| M1-DOC-007 | [Software development plan](software-development-plan.md) | [#7](https://github.com/Entreco/elchrono/issues/7) | Owner @Entreco; revision 1.4 candidate reconciles the owner-approved M1/M2/M3 review scope, adaptive supporting-pane/input implementation, and current test/build evidence for #47; final exact-head acceptance and product verification remain pending |
| M1-DOC-008 | [M1 evidence baseline review and M2 handoff](m1-review.md) | [#8](https://github.com/Entreco/elchrono/issues/8) | Owner @Entreco; revision 0.4 records owner approval of the M1 review disposition for issue #47 and appointment as milestone approver; source gaps and exact current-head acceptance remain explicit |
| M1-DOC-009 | [Risk traceability matrix](../safety/risk-traceability-matrix.md) | [#4](https://github.com/Entreco/elchrono/issues/4) | Owner @Entreco; revision 1.1 traces current M1/M2 sources, browser interruption and TV test scope, core duration entry, and Settings input-policy test scope; no control effectiveness, residual-risk acceptance, or product verification is claimed; final exact-head acceptance pending |
| M2-DOC-001 | [Product requirements](../product/requirements.md) | [#22](https://github.com/Entreco/elchrono/issues/22) | Owner @Entreco; revision 0.8 contains ten active requirements, D-01–D-09, four owner-approved themes, adaptive Settings, and target-specific duration input methods; product execution and exact-head acceptance remain pending |
| M2-DOC-002 | [Product test protocol](../testing/test-protocol.md) | [#25](https://github.com/Entreco/elchrono/issues/25) | Owner @Entreco; revision 1.0 defines measurable procedures for ten active requirements, records passing JVM tests/target builds and an unresolved Skiko Wasm test-runner failure; rendered product tests, accessibility evaluation, and exact-head acceptance remain pending |
| M2-DOC-003 | [Requirements traceability matrix](../safety/requirements-traceability-matrix.md) | [#25](https://github.com/Entreco/elchrono/issues/25) | Owner @Entreco; revision 3.1 traces current requirements, risks, designs, usability evidence, planned tests, and test/build evidence including the Wasm test-runner limitation; rendered UI tests do not establish product verification or platform support |
| M2-DOC-004 | [Accessible screen flows and theme prototypes](../design/accessible-screen-flows-and-theme-prototypes.md) | [#23](https://github.com/Entreco/elchrono/issues/23) | Owner @Entreco; revision 1.8 records Main/Settings/About, owner-approved Clock/Battery/Fan/Ring themes, adaptive Settings, and target-specific duration input; rendered behavior, accessibility, and TV support remain unverified |
| M2-DOC-005 | [Usability review plan](../usability/usability-plan.md) | [#24](https://github.com/Entreco/elchrono/issues/24) | Owner @Entreco; revision 0.7 updates future-task scope; retrospective sessions were not governed by it, and caregiver/assistive-technology evidence remains absent |
| M2-DOC-006 | [Usability findings and evidence status](../usability/usability-findings.md) | [#24](https://github.com/Entreco/elchrono/issues/24) | Owner @Entreco; revision 0.7 records retrospective evidence and limitations; no validation or representative preference is claimed |
| M2-DOC-007 | [M2 evidence baseline review and M3 handoff](m2-evidence-handoff.md) | [#26](https://github.com/Entreco/elchrono/issues/26) | Owner @Entreco; revision 1.3 records approval of the M2 product/design baseline for #47, including D-09 adaptive Settings and target input methods; rendered UI/product verification and exact current-head acceptance remain pending |
| M3-DOC-001 | [Browser and desktop platform feasibility findings](../architecture/platform-feasibility-findings.md) | [#33](https://github.com/Entreco/elchrono/issues/33) | Owner @Entreco; revision 0.2 candidate in merged [PR #42](https://github.com/Entreco/elchrono/pull/42); records macOS/Chrome browser and desktop prototype results; default browser port 8080 was occupied, additional hosts and offline startup were not tested; required review scopes and human acceptance pending; no support baseline or M2 approval claimed |
| M3-DOC-002 | [TV and watch platform feasibility findings](../architecture/tv-watch-feasibility-findings.md) | [#34](https://github.com/Entreco/elchrono/issues/34) | Owner @Entreco; revision 0.2 candidate in merged [PR #49](https://github.com/Entreco/elchrono/pull/49), initially recorded in [PR #43](https://github.com/Entreco/elchrono/pull/43); records read-only toolchain evidence and official TV/watch constraints; Apple SDKs present without TV/watch simulator runtimes, Android target packages could not be confirmed and no usable AVD/device was available; required reviews and human acceptance pending; no platform support or M3 approval claimed |
| M3-DOC-003 | [Audio-provider and background-execution feasibility findings](../architecture/audio-provider-feasibility-findings.md) | [#36](https://github.com/Entreco/elchrono/issues/36) | Owner @Entreco; revision 0.1 in [PR #49](https://github.com/Entreco/elchrono/pull/49); records official YouTube/Spotify and platform constraints, privacy/data-flow implications, and unverified target combinations; provider selection, qualified policy/privacy review, implementation, and platform support remain unresolved |
| M3-DOC-004 | [Android and iOS platform feasibility findings](../architecture/android-ios-platform-feasibility-findings.md) | [#35](https://github.com/Entreco/elchrono/issues/35) | Owner @Entreco; revision 0.3 candidate in [PR #50](https://github.com/Entreco/elchrono/pull/50); records successful Android/iOS builds and unsigned Android AAB packaging, no Android launch target, iPhone 18 Pro iOS simulator launch/rendering, and lifecycle/audio/offline/signing limits; required reviews and human acceptance pending; no platform support or M2 approval claimed |
| M3-DOC-005 | [Dependency and third-party component inventory](../architecture/dependency-inventory.md) | [#38](https://github.com/Entreco/elchrono/issues/38) | Owner @Entreco; revision 0.2 candidate records direct production/test declarations, including adaptive-layout, resolved versions, publisher licence metadata, preliminary SOUP assessments, and open maintenance/licence/source-use actions; transitive licences and competent review remain outstanding |
| M3-DOC-006 | [Build, version, and release-artifact conventions](../architecture/build-release-versioning.md) | [#38](https://github.com/Entreco/elchrono/issues/38) | Owner @Entreco; revision 0.1 candidate in [PR #52](https://github.com/Entreco/elchrono/pull/52) proposes identifiers and artifact evidence for configured targets; Android/iOS scaffold values are not release baselines, browser/iOS packaging gaps remain, and product-owner/platform review is pending |
| M3-DOC-007 | [Platform architecture decision and support matrix](../architecture/platform-architecture-and-support-matrix.md) | [#39](https://github.com/Entreco/elchrono/issues/39) | Owner @Entreco; revision 0.2 records Android TV/Apple TV as primary future targets, Wear OS/watchOS outside v1, and no platform-support commitment; current platform feasibility and minimum-version limitations remain |
| M3-DOC-008 | [M3 evidence gate review and M4 handoff](m3-evidence-handoff.md) | [#40](https://github.com/Entreco/elchrono/issues/40) | Owner @Entreco; revision 0.6 aligns the current M2 review reference and records #47 core/feature ownership, adaptive supporting-pane composition, keyboard-capability adapters, dependency updates, and the Wasm test-runner limitation; M4, rendered UI, product verification, and platform-support gaps remain; exact-head acceptance pending |

For each created record, replace its reserved path with a working link and record
its owner, revision, review PR, and commit-pinned merged baseline when available.
Document owners keep the index consistent with the records.

The [reusable controlled-record templates](../templates/README.md) support
future requirements, risk, architecture/design, verification/traceability,
change/configuration, release, and limitations records. They are starting
structures, not evidence or approval records; reserve IDs for records in this
index and keep lifecycle status and human PR acceptance in their established
locations.

The issue #24 updates to M2-DOC-005/006 revision 0.3 and M2-DOC-003 revision
1.7 are in merged [PR #30](https://github.com/Entreco/elchrono/pull/30).
Revisions 0.5/1.6/0.6/0.6/0.5/2.3/0.8 of M2-DOC-001/004/005/006/002/003/007
in PR #41 add D-06, align the linked source revisions, and prepare the M2 gate
review. PR #32 remains the evidence baseline; its human acceptance is limited
to the #26 handoff deliverable. M2 approval, required specialist review, and
exact-head acceptance of the PR #41 candidate remain pending.

The issue #25 updates to M2-DOC-002 revision 0.2 and M2-DOC-003 revision 1.8
are in merged [PR #31](https://github.com/Entreco/elchrono/pull/31). No
protocol test execution, requirement approval, or risk-control verification
is claimed.

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
| 2.3 | Reconcile M1-DOC-007's merged PR and incomplete acceptance evidence; add the M1-DOC-008 review, PR #21 link, and blocked M1 gate outcome |
| 2.4 | Reserve M2-DOC-001 in the evidence index and link its candidate requirements register |
| 2.5 | Align the indexed intended-use, risk-traceability, software-development, and M2 requirements candidates with the updated whole-minute duration scope |
| 2.6 | Update the M2 requirements and risk/applicability records for caregiver start and Settings access, patient remaining-time visibility, abstract theme selection, completion reset/music stop, and retirement of the WCAG baseline requirement |
| 2.7 | Separate REQ-NNN statements, test-protocol acceptance criteria, and comprehensive requirements traceability; index the M2 test protocol and traceability matrix |
| 2.8 | Reconcile current intended-use, risk, applicability, and development-plan revisions with the requirements/test/traceability split |
| 2.9 | Reconcile the M1 handoff with current M2 issues and candidate requirements, test protocol, and traceability records |
| 2.10 | Reserve M2-DOC-004 for issue #23 and index the candidate accessible screen flows and theme prototypes |
| 2.11 | Update M2-DOC-004 to propose a default remaining-time disk, alternate visualizations, and conditional per-visualization font/color settings |
| 2.12 | Add the local interactive visualization demo and update the M2-DOC-004 evidence index |
| 2.13 | Record prototype-only refinements to disk direction, digital background color, and numeric-ring thickness |
| 2.14 | Index prototype-only background-color, number-visibility, and draining digital-fill feedback options |
| 2.15 | Record demo-duration selection, panel visibility control, and hairline-to-solid ring thickness |
| 2.16 | Index disk background refinements, digital time placement/shapes, and background color presets |
| 2.17 | Index separate background, foreground, and text colors for each visualization |
| 2.18 | Index outside disk numerals and radial-circle and folding-fan digital shapes |
| 2.19 | Index 36 px disk numerals and fixed-radius angular fan closure |
| 2.20 | Index unified prototype shape selection and shared settings with retained conditional options |
| 2.21 | Index independent remaining/progress controls, shape markers, and fixed-edge fan sweep |
| 2.22 | Index Show details section slider and configured-duration subdivision labels |
| 2.23 | Reserve M2-DOC-005 and M2-DOC-006 for issue #24; index the usability protocol and evidence-gap record and update M2 traceability for the prototype revision 1.3 baseline |
| 2.24 | Update M2-DOC-005 to clarify it postdates the sessions; index M2-DOC-006 revision 0.2 and M2-DOC-003 revision 1.6 with session feedback, screenshots, and evidence limitations |
| 2.25 | Index the caregiver-participation explanation and bounded formative sufficiency rationale in M2-DOC-005/006 revision 0.3 and M2-DOC-003 revision 1.7; retain milestone and review gates |
| 2.26 | Index M2-DOC-002 revision 0.2 and M2-DOC-003 revision 1.8 with explicit no-clipping criteria, reverse coverage, and linked evidence/follow-up gaps; retain pending review and verification status |
| 2.27 | Link M2-DOC-002 and M2-DOC-003 revision reviews to PR #31 and update the evidence index; human acceptance and required reviews remain pending |
| 2.28 | Record M2-DOC-007's blocked M2 evidence review and M3 handoff; reconcile the index with merged M2 PRs #27–#31 and retain exact-head acceptance and review gaps |
| 2.29 | Link M2-DOC-007 revision 0.2 and its review PR #32; keep required reviews and human acceptance pending |
| 2.30 | Update the M2 evidence index for PR #32 revisions 0.3–1.9, record D-01 in the risk assessment and v1 risk matrix, and keep the gate, reviews, clipping verification, and test execution explicitly pending |
| 2.31 | Reconcile the index and linked evidence baseline to the final M2/risk source revisions in PR #32; keep review, clipping verification, risk assessment, and test execution pending |
| 2.32 | Index M1-DOC-006 revision 0.7 and M1-DOC-007 revision 0.6, and preserve the M2 gate's outstanding review and verification evidence |
| 2.33 | Record the owner-reported Clock clipping resolution and limited prototype layout checks in M2-DOC-002/003/006/007; keep supported-layout coverage and product-test execution pending |
| 2.34 | Align the evidence index with updated usability-plan and requirements-traceability revisions; retain the blocked M2 gate and review requirements |
| 2.35 | Clarify that the limited prototype layout probe and agent integration review do not substitute for complete product verification or specialist acceptance |
| 2.36 | Reconcile merged PR #32 acceptance scope and index the PR #41 M2 gate-review candidate, including the manual timer-controls decision and retained limitations |
| 2.37 | Reserve and index M3-DOC-001 with issue #33 browser/desktop findings and retain the host, default-port, offline, and review limitations |
| 2.38 | Link M3-DOC-001 revision 0.2 and draft PR #42; retain pending review, human acceptance, and platform evidence gaps |
| 2.39 | Reserve and index M3-DOC-002 with issue #34 TV/watch feasibility findings; retain unavailable test targets, pending reviews, and feasibility-only status |
| 2.40 | Link M3-DOC-002 revision 0.1 and PR #43; retain pending review, human acceptance, and platform evidence gaps |
| 2.41 | Reserve M3-DOC-003 for issue #36 and link provider-feasibility findings while preserving pending privacy/policy reviews and unsupported-target limitations |
| 2.42 | Link M3-DOC-003 and related #36 record updates to PR #49; retain pending specialist review, human acceptance, and unsupported-target limitations |
| 2.43 | Assign the #35 Android/iOS findings the next available ID, M3-DOC-004, and link revision 0.3 to PR #50 after M3-DOC-003 was allocated to issue #36 |
| 2.44 | Reserve M3-DOC-005/006 for issue #38 dependency/SOUP and build/release-versioning records in PR #52; link reusable templates and retain open review, licensing, packaging, and M2-gate limitations |
| 2.45 | Reserve M3-DOC-007 for issue #39's architecture decision and platform support matrix in PR #54; link the merged #33–#36 feasibility evidence, trace the architecture input in M2-DOC-003 revision 2.5, and retain support-baseline and review limitations |
| 2.46 | Reserve M3-DOC-008 for issue #40's M3 evidence gate review and M4 handoff in PR #55; record actual M3 review gaps, the unresolved M2 dependency, and the not-approved gate outcome |
| 2.46 | Link M1-DOC-007 revision 0.9 and this index update to issue #44/PR #56; preserve the candidate status, unresolved M2/product/reviewer gate, and explicit block on affected M4 implementation |
| 2.47 | Index M1-DOC-007 revision 1.0 for issue #46; reconcile the M5 roadmap scope to candidate v1 records, preserve the unassigned review gate and absent browser-support baseline, and block affected implementation |
| 2.48 | Reconcile the M1/M2/M3 evidence index with the owner-designated review scope and current #47 records; clear the absent-reviewer blocker while retaining exact-head, verification, legal, usability, and platform-support limitations |
| 2.49 | Reconcile the application and feature/core ownership model with the #47 module-boundary implementation; retain product-verification and platform-support limitations |
| 2.50 | Update the controlled evidence index for the core/counter refactor, focused domain-test results, revised test/traceability/applicability records, and current M2/M3 review evidence; retain human acceptance and product-verification gaps |
| 2.51 | Reconcile the index and current M1/M2/M3 handoffs with D-09, the supporting-pane/keypad implementation, adaptive-layout dependency, and remaining rendered UI/input/platform verification gaps |
| 2.52 | Update the evidence index with current test-protocol/traceability revisions, passing JVM tests and target builds, and the unresolved Skiko Wasm test-runner failure |
| 2.51 | Reconcile the index and current M1/M2/M3 handoffs with D-09, the supporting-pane/keypad implementation, adaptive-layout dependency, and remaining rendered UI/input/platform verification gaps |
