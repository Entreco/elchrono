# Software safety classification

| Field | Value |
|---|---|
| Document ID | M1-DOC-005 |
| Revision | 0.3 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Software-safety reviewer | @Entreco; competence confirmed by reviewer |
| Reviewer conclusion date | 2026-11-15 |
| Approver | @Entreco, after PR human acceptance and findings resolution |
| Related issue | [#5](https://github.com/Entreco/elchrono/issues/5) |
| Review PR | [PR #16](https://github.com/Entreco/elchrono/pull/16) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](../governance/document-control.md#lifecycle-and-approval).

## Decision and scope

**Reviewer conclusion: El Chrono, as defined by the intended purpose and
functions assessed below, is not medical-device software. IEC 62304:2006 with
Amendment 1:2015 software safety classification is not applicable. No IEC
62304 Class A, B, or C is assigned.**

This is a non-applicability determination, not an unknown class, a claim that
Class A is justified, or a regulatory device class. It does not claim
conformity with or certification to IEC 62304. El Chrono's product purpose is
general-purpose visual support for ordinary waiting time; it does not make
medical claims or have a medical intended purpose. The absence of claims is
not the sole basis for this conclusion: the intended purpose, specified
functions, exclusions, and qualified EU/US assessment are considered together.

The assessment covers the El Chrono application as a whole: caregiver
configuration of an ordinary waiting duration, visual communication of
remaining waiting time and timer state, the patient's volume control, and
optional background music. The current app is a starter screen, and these
timer and audio behaviors are not yet implemented or verified. The conclusion
is about the product's defined purpose and scope, not verification of a
released implementation. Browser, Android/iOS, and desktop are scaffolded
targets; this record does not claim release support. TV and watch remain
unconfigured and outside this assessment.

## Exact source baseline

The records below are the merged baseline at
[PR #15 merge commit `9b5054691ae14a08fcbf591a6b0f073e49c54b79`](https://github.com/Entreco/elchrono/commit/9b5054691ae14a08fcbf591a6b0f073e49c54b79).
These exact revisions are assessed; later changes to intended use, functions,
claims, markets, or risks require reassessment.

| Source | Revision | Relevance to this determination |
|---|---:|---|
| [M1-DOC-002, Intended use](https://github.com/Entreco/elchrono/blob/9b5054691ae14a08fcbf591a6b0f073e49c54b79/docs/product/intended-use.md) | 0.6 | General ordinary-waiting purpose; visual remaining-time presentation; optional nonessential music; explicit medical-purpose and safety-critical timing exclusions. |
| [M1-DOC-003, Qualification and classification](https://github.com/Entreco/elchrono/blob/9b5054691ae14a08fcbf591a6b0f073e49c54b79/docs/regulatory/qualification-and-classification.md) | 0.5 | Qualified EU/US conclusion that El Chrono is not a medical device as described in M1-DOC-002 revision 0.6; no regulatory device class applies. |
| [M1-DOC-004, Risk assessment](https://github.com/Entreco/elchrono/blob/9b5054691ae14a08fcbf591a6b0f073e49c54b79/docs/safety/risk-assessment.md) | 0.1 | Six proposed risks, including ordinary-waiting misunderstanding and possible serious harm from reliance outside intended use. The risks and controls remain subject to their own review and verification. |
| [M1-DOC-009, Risk traceability matrix](https://github.com/Entreco/elchrono/blob/9b5054691ae14a08fcbf591a6b0f073e49c54b79/docs/safety/risk-traceability-matrix.md) | 0.1 | Planned verification for product risks; no control effectiveness is credited in determining IEC 62304 applicability. |
| [M1-DOC-001, Document controls](https://github.com/Entreco/elchrono/blob/9b5054691ae14a08fcbf591a6b0f073e49c54b79/docs/governance/document-control.md) | 1.6 | Review responsibilities and the repository's controlled-record process. |

The fact that the source documents are on `main` establishes their repository
lifecycle status, not that every historical PR acceptance or specialist review
was evidenced. Those gaps remain as recorded in the source documents; this
assessment does not invent or resolve that separate historical evidence.

## Rationale

IEC 62304:2006+AMD1:2015 is titled *Medical device software — Software life
cycle processes*. The IEC catalogue describes its scope as medical-device
software, whether software is itself a medical device or is embedded in or
integral to one. The project's M1-DOC-003 assessment concludes, for the exact
intended purpose and functions in M1-DOC-002 revision 0.6, that El Chrono is
not a medical device in either selected market. This record adopts that
qualified non-device conclusion for the corresponding software scope.

The functions assessed provide an ordinary waiting-time visual countdown.
They do not diagnose, prevent, monitor, treat, alleviate, or manage a medical
condition; compensate for a disability; process health information to make a
medical recommendation; or provide medication, procedure, emergency, or
other safety-critical medical timing. The intended use is general and does
not vary with a person's diagnosis or health status. Optional music is
accompaniment, not a medical or timing function. The defined purpose and
functions therefore do not make this medical-device software.

**Absence of medical claims alone is not the decision rule.** Claims,
instructions, and marketing must remain consistent with intended purpose, but
the decision also rests on the product functions and intended-use boundaries
in M1-DOC-002 revision 0.6 and the qualified market analysis in M1-DOC-003
revision 0.5. If actual functionality or intended use diverges from those
records, this conclusion cannot be relied on without reassessment.

Because the software is outside the stated scope of IEC 62304, its Clause 4.3
software safety classes are not applied. The risk levels in M1-DOC-004 are
not IEC 62304 classes, and this decision does not convert them into a class.
No reliance on an unimplemented product control or an external control is
needed to reach the non-applicability conclusion.

Earlier M1 planning text proposed Class A before the standard's applicability
had been decided. That proposal is withdrawn for the defined non-medical
software scope: the conclusion is **not applicable**, not Class A and not an
unknown class. The risk evidence does not justify overriding the scope
determination or assigning a class by default.

## Disposition of initial risks

The risks remain relevant to product requirements, design, usability, and
verification. Their existence does not make the product medical-device
software or require assigning an IEC 62304 class. In particular, RISK-006
describes possible misuse outside the stated intended purpose; it remains a
product risk and a trigger to review claims and intended use, but it does not
change the purpose assessed here. No risk is declared acceptable by this
applicability determination.

| Risk in M1-DOC-004 revision 0.1 | Disposition for IEC 62304 applicability |
|---|---|
| [RISK-001](risk-assessment.md#risk-analysis): Incorrect or misleading remaining time | Product timing reliability risk for an ordinary waiting aid. It does not add a medical purpose or make the timer medical-device software. |
| [RISK-002](risk-assessment.md#risk-analysis): Interruption or lost state | Product lifecycle/recovery risk. Its controls and verification remain open; it does not alter the assessed intended purpose. |
| [RISK-003](risk-assessment.md#risk-analysis): Unexpected, excessive, or failed audio | Product audio and sensory-experience risk. Optional audio is not necessary to understand remaining time and does not add a medical function. |
| [RISK-004](risk-assessment.md#risk-analysis): Change to caregiver settings | Product access/configuration risk. Caregiver protection remains a design and requirements question, not a medical function. |
| [RISK-005](risk-assessment.md#risk-analysis): Misunderstanding progress or state | Product accessibility/usability risk. It remains relevant to verification and user evaluation, not IEC software classification. |
| [RISK-006](risk-assessment.md#risk-analysis): Reliance outside intended use | Foreseeable misuse risk, including possible serious harm if the timer is used for excluded consequential or safety-critical purposes. The intended-purpose exclusions and non-device qualification are the basis for the scope decision; no control is assumed to eliminate misuse. Any evidence of such use becoming intended or promoted requires reassessment and escalation. |

These dispositions address the issue #5 classification question only. They
do not replace the risk review walkthrough, determine residual risk
acceptability, or verify controls required by issue #4.

## Reviewer conclusion

@Entreco confirms competence as the software-safety reviewer for this
applicability decision and concludes that the defined El Chrono software is
not medical-device software. The reviewer considered the application
boundary, the ordinary-waiting intended purpose and exclusions in M1-DOC-002
revision 0.6, the EU/US non-device assessment in M1-DOC-003 revision 0.5, and
all six proposed risks in M1-DOC-004 revision 0.1. The conclusion is that
IEC 62304 software safety classification is not applicable and no Class A,
B, or C is assigned. This is separate from the regulatory conclusion that no
medical-device class applies; neither conclusion claims regulatory approval,
legal advice, or certification.

The reviewer supplied this applicability conclusion for M1-DOC-005 revision
0.2 on 2026-11-15. @Entreco accepted the exact PR #16 head,
`dc01e2ebc25f1458ef0a9a901a28128cdf7464ea`; the PR merged as
`2a8791c9a1cf76a96768e3033a88af3f51f81732`. This revision records that
acceptance evidence without changing the assessed scope or conclusion. No
separate or independent reviewer is represented.

## Reassessment triggers and limitations

Reassess before relying on this conclusion if any of the following changes:

- the intended purpose or claims, including disability compensation or any
  medical-purpose claim;
- actual functions, including health-data processing, condition-specific
  behavior, monitoring, recommendations, or safety-critical timing;
- the intended users, care setting, supported use, or exclusions;
- the qualified market assessment or the selected markets; or
- an applicable standard or its scope.

If a change makes the software medical-device software, escalate and assess
the applicable regulatory and standards obligations before affected work
continues. Do not assign Class A by default. This record does not determine
the applicability of other laws, standards, or voluntary product controls.
Product risks and their controls still require the review and verification
identified in M1-DOC-004 and M1-DOC-009.

## Sources

1. International Electrotechnical Commission, [IEC 62304 consolidated
   version](https://webstore.iec.ch/en/publication/22794),
   IEC 62304:2006+AMD1:2015, edition 1.1, published 2015-06-26.
2. The exact merged source records listed in the [source baseline](#exact-source-baseline).
3. [Project non-medical product boundary and safety policy](../../AGENTS.md#non-medical-product-boundary).

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish M1-DOC-005 and defer the applicability decision to a post-merge assessment. |
| 0.2 | Record @Entreco's full software-safety applicability conclusion against the exact merged M1 source revisions; withdraw the premature Class A proposal; no IEC 62304 class applies. |
| 0.3 | Record @Entreco's human acceptance of the exact PR #16 head and its merge commit. |
