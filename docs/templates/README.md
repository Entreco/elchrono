# Reusable controlled-record templates

These templates provide starting structures for future project records. They
are not completed evidence, approvals, or product requirements. Replace every
bracketed prompt with evidence or an explicit limitation before submitting a
record for review.

Before using a template:

- reserve a unique document ID in the
  [foundation evidence index](../governance/document-control.md#foundation-evidence-index);
- include the required record identity fields (ID, revision, owner, author,
  required reviewers, approver, related issue, and review PR);
- link the document-control lifecycle rule rather than adding a `Status` or
  `Approval` outcome field; an approver identity is still required;
- distinguish observed evidence from assumptions, missing evidence, and open
  actions, and do not claim a review or verification that did not occur;
- keep human acceptance in the PR description; do not create duplicate
  signatures or acceptance evidence in a record.

Choose the template matching the record's purpose:

| Record purpose | Template |
|---|---|
| Stable requirement statements | [Requirements register](requirements-template.md) |
| Product risk scenarios and controls | [Risk assessment](risk-assessment-template.md) |
| Architecture or design decisions | [Architecture/design](architecture-design-template.md) |
| Verification evidence and traceability | [Verification/traceability](verification-traceability-template.md) |
| Change and configuration assessment | [Change/configuration](change-configuration-template.md) |
| A platform-specific release decision | [Release record](release-record-template.md) |
| Explicit gaps and restrictions | [Limitations record](limitations-template.md) |

Keep measurable acceptance criteria in the test protocol and requirement,
risk, design, and test relationships in the traceability matrix where those
controlled records exist. A template does not replace required source records,
specialist review, findings disposition, or a release decision.
