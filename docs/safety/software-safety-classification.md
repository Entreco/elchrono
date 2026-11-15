# Software safety classification

| Field | Value |
|---|---|
| Document ID | M1-DOC-005 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software-safety reviewer: @Entreco (competence confirmed by reviewer); post-merge assessment pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#5](https://github.com/Entreco/elchrono/issues/5) |
| Review PR | [PR #15](https://github.com/Entreco/elchrono/pull/15) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This candidate records the scope and inputs for the software-safety
classification decision required by issue #5. The decision itself is
deliberately deferred until after this PR is merged, so it can assess the
merged intended-use, qualification, and risk records. The follow-up must be
made through a PR and linked here; this candidate is not the final
classification record.

The project direction is a non-medical visual aid for ordinary waiting-time
support. The qualified EU and US assessment in
[M1-DOC-003, revision 0.5](../regulatory/qualification-and-classification.md)
concludes that the product, as described in
[M1-DOC-002, revision 0.6](../product/intended-use.md), is not a medical
device in those markets. IEC 62304 is used voluntarily and subject to the
applicability review in issue [#6](https://github.com/Entreco/elchrono/issues/6).
Neither that non-device conclusion nor this voluntary process assigns a
regulatory class.

The product owner and designated software-safety reviewer, @Entreco, confirms
competence for the post-merge software-safety review. The reviewer has stated
that El Chrono is **not medical-device software**. The post-merge assessment
must record that conclusion and explain that no IEC 62304 software safety
class applies if the merged intended purpose and functions remain non-medical.
The absence of medical claims is relevant but is not, by itself, the
qualification test: the reviewer must also confirm the actual intended
purpose, functions, and approved qualification assessment. This distinction
preserves the project boundary that a disclaimer alone does not determine
qualification.

The current app is a starter screen; its timer, audio, platform lifecycle, and
controls are not implemented or verified. No product risks or controls are
being assigned an IEC 62304 class in this preliminary record. No regulatory
device class, IEC 62304 conformity, or certification is claimed.

## Sources and exact revisions

The assessment uses these candidate source records:

| Source | Revision assessed | Relevant content and evidence limit |
|---|---:|---|
| [M1-DOC-002, Intended use](../product/intended-use.md) | 0.6 | Ordinary-waiting purpose, exclusions, users, proposed functions, and non-medical boundary. The exact-revision approval evidence is unresolved in the source record. |
| [M1-DOC-003, Qualification and classification](../regulatory/qualification-and-classification.md) | 0.5 | Qualified EU/US non-device conclusion, bounded by M1-DOC-002 revision 0.6. PR acceptance and exact-revision approval remain pending. |
| [M1-DOC-004, Risk assessment](risk-assessment.md) | 0.1 | Six proposed risks, harms, and controls. Ratings and controls remain subject to software-safety review; no post-control risk is established. |
| [M1-DOC-009, Risk traceability matrix](risk-traceability-matrix.md) | 0.1 | Planned verification for the proposed controls; all results are pending. |

Issues [#3](https://github.com/Entreco/elchrono/issues/3) and
[#4](https://github.com/Entreco/elchrono/issues/4) are closed. Their merged
records are candidate inputs; their stated approval and review-evidence gaps
must be checked against the merged baseline during the follow-up. Issue #5
must remain open until that post-merge assessment and its review evidence are
recorded.

The IEC catalogue identifies the publication selected as a voluntary
reference: the consolidated first edition, IEC 62304:2006+AMD1:2015, edition
1.1, published 2015-06-26. Its stated scope is medical-device software. The
post-merge assessment uses that scope to determine applicability; it does not
apply the Clause 4.3 class categories to software outside that scope. If the
approved intended purpose, functions, or qualification assessment instead
indicate medical-device software, stop and escalate before conducting a
separate classification assessment against the authorized normative text.

## Software system assessed

The assessed system is the El Chrono application software for the proposed
ordinary-waiting use: the configured-duration timer, remaining-time
presentation and state communication, caregiver configuration, patient volume
control, and optional music behavior described by M1-DOC-002 revision 0.6 and
the project product requirements. The software is not yet implemented as a
timer. This is therefore a prospective product-level assessment, not a
classification of verified source code or a specific release.

The scaffold identifies browser, Android/iOS, and desktop application
targets; this record does not claim release support for them. TV and watch
paths are reserved and have no target configured; no support or safety
conclusion for them is made here. Platform launchers, operating systems,
browsers, and external audio providers are outside the El Chrono application
boundary. They may affect behavior or availability, but no external platform
or provider control is credited without a defined interface, evidence, and
review. A change to the supported platforms, system boundary, intended use,
or product functions requires an impact assessment.

## Post-merge classification decision

After this PR is merged, prepare a follow-up PR that updates this record using
the merged versions of M1-DOC-002, M1-DOC-003, M1-DOC-004, and M1-DOC-009.
The follow-up must:

1. identify the exact merged revisions and check any unresolved approval
   evidence and review findings;
2. confirm that the approved intended purpose, actual functions, claims, and
   qualified market assessment establish that El Chrono is not medical-device
   software (the absence of medical claims alone is not sufficient);
3. conclude that IEC 62304 software safety classification is **not
   applicable** to this non-medical software, so no IEC 62304 Class A, B, or C
   is assigned; and
4. record @Entreco's competent software-safety review, findings and
   dispositions, and the exact reviewed document revision and PR head.

If the merged intended purpose, functions, or qualification evidence do not
support the stated non-device conclusion, stop and escalate before recording
non-applicability. Do not instead assign Class A by default. This record and
its proposed post-merge conclusion do not claim regulatory approval, legal
compliance, or IEC 62304 conformity or certification.

## Evidence gaps and limitations

- This PR intentionally makes no IEC 62304 class assignment. The post-merge
  follow-up must confirm the applicability basis and exact approved inputs.
- Approval evidence for M1-DOC-002 revision 0.6, M1-DOC-003 revision 0.5,
  M1-DOC-004 revision 0.1, and M1-DOC-009 revision 0.1 remains unresolved in
  those records.
- M1-DOC-004 revision 0.1 and project-level guidance currently describe
  Class A as proposed. If the post-merge assessment confirms
  non-applicability, reconcile that wording and affected traceability in the
  follow-up PR; do not treat it as an approved classification.
- The designated competent reviewer has not yet performed the post-merge
  assessment; no reviewer disposition or human acceptance has occurred.
- No approved requirement IDs, implemented or verified controls, or product
  usability findings exist.
- The applicable project-process rationale remains subject to issue #6.
- This assessment does not establish regulatory device status, legal
  compliance, IEC conformity, certification, or release approval.

The post-merge classification follow-up and review evidence are required before
issue #5 can close. The PR lifecycle and closed prerequisite issues do not
complete that work.

## Sources

1. International Electrotechnical Commission, [IEC 62304 consolidated
   version](https://webstore.iec.ch/en/publication/22794),
   IEC 62304:2006+AMD1:2015, edition 1.1, published 2015-06-26. The catalogue
   describes its scope as medical-device software; no class is assigned here.
2. [M1-DOC-002, Intended use, revision 0.6](../product/intended-use.md).
3. [M1-DOC-003, Qualification and classification, revision 0.5](../regulatory/qualification-and-classification.md).
4. [M1-DOC-004, Risk assessment, revision 0.1](risk-assessment.md).
5. [M1-DOC-009, Risk traceability matrix, revision 0.1](risk-traceability-matrix.md).
6. [Document controls, M1-DOC-001, revision 1.6](../governance/document-control.md).
7. [Project medical-device documentation and safety policy](../../AGENTS.md#medical-device-documentation-and-safety).

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish the M1-DOC-005 candidate and defer the non-applicability decision to a post-merge assessment against approved source revisions |
