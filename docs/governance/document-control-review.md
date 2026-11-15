# Document-control review record

| Field | Value |
|---|---|
| Record ID | M1-DOC-001-REVIEW-001 |
| Revision | 0.1 |
| Status | Draft; review preparation only |
| Document under review | [M1-DOC-001](document-control.md), revision 0.2 |
| Document owner and approver | @Entreco |
| Draft author | Copilot |
| Requested human reviewer | @Entreco; product-owner and document-control scope only |
| Required specialist reviewers | Regulatory and software-safety reviewers unassigned |
| Work item | [#1](https://github.com/Entreco/elchrono/issues/1) |
| Branch | `docs/m1-document-control-review` |
| PR | Pending creation |
| Candidate commit and pinned document link | Pending publication of the final review candidate |
| Human review evidence | Pending |
| Approval decision and date | Pending; no approval claimed |

## Candidate identification

The existing revision 0.1 is available at
[commit 4b6daf60bc3f801d0669420bf1dd73e0be1517aa](https://github.com/Entreco/elchrono/blob/4b6daf60bc3f801d0669420bf1dd73e0be1517aa/docs/governance/document-control.md).
This is historical context, **not the revision 0.2 review candidate**.

When the PR is created, record its URL and the full candidate commit SHA with a
commit-pinned link to `docs/governance/document-control.md`. Give reviewers that
exact revision and the checks below. Any content change requires an updated
candidate reference and review of the changed content. Neither branch name nor
PR approval of an older commit identifies acceptance of a later revision.

The document remains Draft until all required reviewers are appointed. @Entreco
can review the product-owner and document-control scope through the PR without
implicitly claiming specialist qualifications or completing all review gates.

## Review checklist

Record each actual result and its evidence; these are requested checks, not
completed human verification.

| Check | Requested review | Result / evidence |
|---|---|---|
| Ownership and competencies | Confirm owner/approver designation; appoint required reviewers and record competence/scope without private personal evidence | Pending |
| Non-medical boundary | Confirm visual waiting-time support, no medical claims, and voluntary documentation rigor; check consistency with README and AGENTS | Pending |
| Record identification | Check stable IDs, revision/status fields, candidate commit references, and retained history | Pending |
| Lifecycle | Walk a candidate through Draft, In review, Approved, and Superseded; confirm explicit human decisions and required-review gates | Pending |
| Change handling | Walk a changed approved record through impact assessment and re-review; confirm stale acceptance is not reused | Pending |
| Traceability | Check all eight index entries against M1 issues; confirm missing risks/requirements and planned controls are not presented as approved or verified | Pending |
| Findings and limitations | Confirm blocking findings cannot be waived as limitations; check owners and later dependencies | Pending |
| End-to-end evidence | Record actual PR review, findings dispositions, exact accepted candidate, and explicit approval in this record before #1 closes | Pending |

## Findings and disposition

Preserve the finding IDs from the document. Only the actual reviewer may confirm
resolution within their scope; a proposed fix is not a closed finding.

| Finding | Current disposition | Owner / required resolution |
|---|---|---|
| M1-DOC-001-F01 | Blocking; open | @Entreco appoints competent regulatory and software-safety reviewers; reviewers confirm scope and review the candidate |
| M1-DOC-001-F02 | Blocking; open; this record supplies the missing review-record structure only | @Entreco coordinates PR/candidate publication, real human review, findings resolution, and explicit approval |
| M1-DOC-001-F03 | Later dependency; open | @Entreco coordinates applicability review in #6 and milestone-approver appointment before #8; re-review controls if applicability changes |

Add subsequent findings with the next unused `M1-DOC-001-Fnn` identifier,
reviewer, scope, blocking rationale, resolution evidence, and disposition.

## Approval record to complete after review

| Required evidence | Current value |
|---|---|
| Document ID, path, revision, full approved candidate SHA and pinned link | Pending final candidate |
| Human reviewers, scopes, competency confirmations, and review links | Pending |
| Acceptance-criteria verification results | Pending |
| Findings resolution and reviewer dispositions | Pending |
| Non-blocking limitations, rationale, owners, and follow-up links | Pending; later dependencies listed above |
| Approver's explicit decision, date, and human decision link | Pending |
| Foundation evidence-index update | Draft review-record link added; approved baseline pending |

Store the completed evidence under `docs/`. An approval entry must point to an
earlier immutable candidate commit, not attempt to contain its own final SHA.
Merging a PR alone is not controlled approval. Do not close #1 until the required
reviews and its acceptance criteria are genuinely complete.

## Revision history

| Revision | Change | Approval |
|---|---|---|
| 0.1 | Prepare the first document-control review record and unresolved-evidence checklist | Pending |
