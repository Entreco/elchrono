# Software safety classification

| Field | Value |
|---|---|
| Document ID | M1-DOC-005 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software-safety reviewer: unassigned; blocking |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#5](https://github.com/Entreco/elchrono/issues/5) |
| Review PR | Pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This record assesses the proposed use of IEC 62304:2006 with Amendment 1:2015
as a voluntary software-lifecycle and safety-classification reference for
El Chrono. It is not a determination that El Chrono is a medical device, a
regulatory device classification, a claim of IEC 62304 conformity or
certification, or evidence that the timer or any risk control has been
implemented or verified.

The project direction is a non-medical visual aid for ordinary waiting-time
support. The qualified EU and US assessment in
[M1-DOC-003, revision 0.5](../regulatory/qualification-and-classification.md)
concludes that the product, as described in
[M1-DOC-002, revision 0.6](../product/intended-use.md), is not a medical
device in those markets. IEC 62304 is used voluntarily and subject to the
applicability review in issue [#6](https://github.com/Entreco/elchrono/issues/6).
Neither that non-device conclusion nor this voluntary process assigns a
regulatory class.

This is a candidate assessment, not an approved classification. The current
app is a starter screen, and its timer, audio, platform lifecycle, and controls
are not implemented or verified. A competent software-safety reviewer must
check the analysis against the exact normative standard, challenge the
classification assumptions, and record a conclusion. **Class A is not
established by this record. Unknown classification is a blocking outcome, not
a pass.**

## Sources and exact revisions

The assessment uses these candidate source records:

| Source | Revision assessed | Relevant content and evidence limit |
|---|---:|---|
| [M1-DOC-002, Intended use](../product/intended-use.md) | 0.6 | Ordinary-waiting purpose, exclusions, users, proposed functions, and non-medical boundary. The exact-revision approval evidence is unresolved in the source record. |
| [M1-DOC-003, Qualification and classification](../regulatory/qualification-and-classification.md) | 0.5 | Qualified EU/US non-device conclusion, bounded by M1-DOC-002 revision 0.6. PR acceptance and exact-revision approval remain pending. |
| [M1-DOC-004, Risk assessment](risk-assessment.md) | 0.1 | Six proposed risks, harms, and controls. Ratings and controls remain subject to software-safety review; no post-control risk is established. |
| [M1-DOC-009, Risk traceability matrix](risk-traceability-matrix.md) | 0.1 | Planned verification for the proposed controls; all results are pending. |

Issues [#3](https://github.com/Entreco/elchrono/issues/3) and
[#4](https://github.com/Entreco/elchrono/issues/4) are closed, but their
closure and merged PRs do not resolve the approval gaps stated by the source
records. The revisions above are identified for traceability, not represented
as approved inputs. Approval of this classification cannot precede approval of
the prerequisite evidence at exact revisions.

The IEC catalogue identifies the publication selected as a voluntary
reference: the consolidated first edition, IEC 62304:2006+AMD1:2015, edition
1.1, published 2015-06-26.
The catalogue page establishes publication identity and scope but does not
provide the normative clause text. The reviewer must verify the decision
criteria and this analysis against an authorized copy of the complete
standard, particularly Clause 4.3 and its relationship to the standard's risk
management process. This document does not reproduce the standard.

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

## Classification method

The proposed method is to apply the software safety classification decision
process in IEC 62304 Clause 4.3 to the software system and its potential
contribution to hazardous situations, using risk-management evidence for the
defined intended use. The reviewer must confirm the clause interpretation
against the authorized edition before approval. The categories are commonly
described as A where injury or damage to health is not possible, B where
non-serious injury is possible, and C where death or serious injury is
possible; the exact criteria and any relevant amendment text must be checked
against the normative source. This summary is a paraphrase, not a substitute
for the standard.

For each risk, the assessment must distinguish:

1. the software contribution to the hazardous situation;
2. the possible harm and whether the applicable classification criterion is
   met;
3. any external risk control that is applicable to the product and supported
   by evidence; and
4. the resulting class under the standard's decision process.

Do not treat an intended-use exclusion, disclaimer, caregiver responsibility,
or proposed control as an effective external risk control without evidence
that it applies and is adequate. Do not infer the software class from the
EU/US regulatory device-class conclusion. No approved requirement IDs are
available; none are invented here.

## Risk-by-risk assessment

The risks below are the proposed IDs from M1-DOC-004 revision 0.1, not approved
or verified risk determinations. The software contribution and review questions
are an initial analysis for qualified challenge. No external control or
post-control risk reduction is credited.

| Risk | Potential software contribution | External-control evidence and classification question |
|---|---|---|
| [RISK-001](risk-assessment.md#risk-analysis) — incorrect or misleading remaining time | The timer, elapsed-time handling, duration boundaries, completion behavior, or inconsistent visual mapping could directly produce an inaccurate or misleading state. | No verified external control is identified. The reviewer must determine what harms are possible within intended use, whether RISK-006 changes the consequence analysis, and how the standard's class criteria apply. |
| [RISK-002](risk-assessment.md#risk-analysis) — interruption or lost state | Application or platform lifecycle behavior could lose timing continuity or present an apparently reliable state after interruption. Recovery behavior and persistence are undecided. | No verified external recovery control is identified. The reviewer must assess whether platform behavior can be relied on and what evidence would support that reliance; no such evidence is presently available. |
| [RISK-003](risk-assessment.md#risk-analysis) — unexpected, excessive, or failed audio | Application audio behavior could start or stop unexpectedly, misrepresent playback success, or distract from the timer. | Device volume, caregiver supervision, and provider behavior have not been established as controls. Proposed audio controls are unverified. Determine whether any audio-related harm meets a classification criterion and what evidence is required. |
| [RISK-004](risk-assessment.md#risk-analysis) — change to caregiver settings | The application could expose caregiver controls or permit an unintended change that alters the configured duration or experience. | No caregiver-protection mechanism or verified external access control is selected. Caregiver supervision is not credited as a control without evidence and review. |
| [RISK-005](risk-assessment.md#risk-analysis) — misunderstanding of progress or state | The application presentation, accessibility semantics, or theme mapping could be misunderstood as showing a different remaining time or timer state. | No representative usability evidence or verified external control is available. Caregiver explanation is not credited as effective mitigation without support. |
| [RISK-006](risk-assessment.md#risk-analysis) — reliance outside intended use | Software output could contribute to a user treating the timer as an authoritative deadline or using it for consequential or safety-critical timing despite the documented exclusion. The risk assessment records serious harm as plausible in such misuse. | The intended-use exclusions and proposed claims/limitations controls are not verified protections. The reviewer must explicitly decide how foreseeable misuse and the possible serious harm affect classification; neither the non-device conclusion nor the exclusion alone resolves this question. |

The initial risk record describes potential confusion, distress, disruption,
and serious harm in out-of-scope use, but does not establish that each
consequence is injury or damage to health under IEC 62304. Making that
determination, considering foreseeable misuse, and resolving the relationship
between the non-medical boundary and RISK-006 require the competent reviewer.
Do not dismiss the risk or translate its provisional severity rating directly
into an IEC class.

## External risk controls and assumptions

No external risk control is currently demonstrated as applicable and effective
for reducing the classification basis:

- The caregiver's role in selecting and explaining a wait is part of the
  proposed use, but no validated training, interaction, or supervision control
  is established.
- M1-DOC-004 proposes intended-use limitations and escalation for RISK-006.
  Their wording, presentation, user comprehension, and effectiveness have not
  been verified; warnings alone are not assumed to prevent misuse.
- Operating-system, browser, device-audio, and provider behavior is not
  specified as a safety control and has not been assessed as one.
- The risk assessment's proposed controls are not implemented or verified.
  No post-control ratings, residual-risk acceptability, or verification result
  is available in M1-DOC-009.

If a reviewer relies on an external control, the final record must identify its
source and owner, show that it applies to the system and use under assessment,
and cite evidence supporting its effectiveness. Otherwise, assess the risk
without credit for that control.

## Candidate conclusion and required disposition

**Classification conclusion: pending competent software-safety review; no
software safety class is approved by this candidate.** The current evidence
does not establish that proposed Class A is defensible: risk controls remain
unverified, the source records are candidates with unresolved approval
evidence, and the classification-relevant interpretation of RISK-006 has not
been resolved. This is a blocker, not a conclusion that Class B or C applies.

The assigned reviewer must challenge the above analysis and record a
reasoned disposition against each risk and the exact source revisions:

- If the reviewer justifies Class A, record why no applicable risk can produce
  injury or damage to health under the standard's criteria, how RISK-006 and
  foreseeable misuse were handled, and what external controls (if any) are
  supported by evidence.
- If Class A is not justified, record the classification that is justified by
  the evidence, escalate to the product owner, and revise the voluntary
  lifecycle process and affected M1 records before affected work proceeds.
- If material evidence or interpretation remains unresolved, keep the
  classification unknown and the issue blocked; unknown is not a passing
  outcome.

No qualified software-safety reviewer is assigned in M1-DOC-001 revision 1.4.
The product owner must appoint a competent reviewer, record the review scope
and a concise competency statement, and provide the exact candidate revision
and supporting evidence. Review challenges, findings, resolutions, and the
review disposition belong in the PR. The PR's human acceptance is separate
from, and cannot substitute for, the required specialist review.

## Evidence gaps and limitations

- The full normative IEC text was not available to the author during drafting;
  clause wording and application must be verified by the reviewer against an
  authorized copy before approval.
- Approval evidence for M1-DOC-002 revision 0.6, M1-DOC-003 revision 0.5,
  M1-DOC-004 revision 0.1, and M1-DOC-009 revision 0.1 remains unresolved in
  those records.
- No competent software-safety reviewer is assigned, and no reviewer
  challenge or human acceptance has occurred.
- No approved requirement IDs, implemented or verified controls,
  post-control risks, or product usability findings exist.
- The applicable project-process rationale remains subject to issue #6.
- This assessment does not establish regulatory device status, legal
  compliance, IEC conformity, certification, or release approval.

These are blocking gaps for classification approval and issue #5 closure.
They must not be hidden by the open PR, the closed prerequisite issues, or
the branch-derived documentation lifecycle.

## Sources

1. International Electrotechnical Commission, [IEC 62304 consolidated
   version](https://webstore.iec.ch/en/publication/22794),
   IEC 62304:2006+AMD1:2015, edition 1.1, published 2015-06-26. See Clause 4.3
   for software safety classification; confirm normative wording against the
   authorized standard.
2. [M1-DOC-002, Intended use, revision 0.6](../product/intended-use.md).
3. [M1-DOC-003, Qualification and classification, revision 0.5](../regulatory/qualification-and-classification.md).
4. [M1-DOC-004, Risk assessment, revision 0.1](risk-assessment.md).
5. [M1-DOC-009, Risk traceability matrix, revision 0.1](risk-traceability-matrix.md).
6. [Document controls, M1-DOC-001, revision 1.4](../governance/document-control.md).
7. [Project medical-device documentation and safety policy](../../AGENTS.md#medical-device-documentation-and-safety).

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Initial candidate classification analysis for issue #5; identify exact source revisions, risk questions, unverified external controls, and blocking review evidence |
