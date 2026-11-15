# Risk assessment

| Field | Value |
|---|---|
| Document ID | M1-DOC-004 |
| Revision | 0.4 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software-safety reviewer: unassigned; blocking |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#4](https://github.com/Entreco/elchrono/issues/4) |
| Review PR | [PR #14](https://github.com/Entreco/elchrono/pull/14); [PR #16](https://github.com/Entreco/elchrono/pull/16) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This is an initial, qualitative assessment of foreseeable risks in the
proposed ordinary-waiting use of El Chrono. It identifies scenarios, possible
harms, assumptions, and controls for review and follow-up. It is not a
completed safety assessment, a determination that any risk is acceptable, or
evidence that a proposed control has been implemented or verified.

The current application is a starter screen, not an implemented timer. There
are no patient usability findings, operational data, or verified timer, audio,
accessibility, or interruption controls to support frequency estimates. The
assessment therefore uses provisional screening ratings and explicitly records
the assumptions behind them. A qualified software-safety reviewer must review
the method, scenarios, ratings, and proposed controls before approval.

This voluntary risk record does not change El Chrono's proposed non-medical
intended use, add a medical claim, or establish IEC 62304 conformity or
certification. The product is not intended for safety-critical timing,
including medication, procedure, or emergency timing.

## Assessed scope and sources

The proposed product boundary and ordinary-waiting use are defined in
[M1-DOC-002, revision 0.6](../product/intended-use.md). That revision is a
candidate; its exact-revision approval evidence remains unresolved. The
EU/US qualification conclusion in
[M1-DOC-003, revision 0.5](../regulatory/qualification-and-classification.md)
is bounded by that intended-use candidate and is also subject to its recorded
review and approval process. This assessment does not independently establish
either record's approval.

The assessment covers incorrect or misleading timing; interruption and
recovery; unexpected, excessive, or failed audio; patient access to caregiver
settings; accessibility-related misunderstanding; and foreseeable misuse
outside the intended use. Product requirements and risk controls are proposed
from the project purpose, product requirements, accessibility and timer
reliability guidance, and non-medical boundary in
[AGENTS.md](../../AGENTS.md). No approved requirement IDs exist; none are
invented here.

All scenarios within the issue's required scope are addressed below; none is
treated as non-applicable. Provider-specific connectivity and playback failures
are included under RISK-003, with details dependent on a later approved
integration and platform scope.

## Initial assessment method and criteria

Use the following ordinal scales as a consistent initial screening method.
They are not statistical estimates, clinical severity categories, or evidence
of risk acceptability.

**Severity (S)** describes the plausible consequence if the hazardous
situation occurs:

| Value | Criterion |
|---|---|
| 1 | Temporary confusion, inconvenience, or discomfort during ordinary waiting, with no substantial disruption expected. |
| 2 | Significant confusion, distress, or disruption that may require caregiver intervention or materially affect the waiting experience. |
| 3 | Serious harm is plausible, including if someone relies on the timer in a consequential situation outside the intended use. This rating does not imply that such use is supported. |

**Likelihood (L)** is a qualitative estimate of how plausible the hazardous
situation is, given the described use and known evidence. For the initial
assessment, consider the situation before the proposed controls:

| Value | Criterion |
|---|---|
| 1 | Unlikely in the described use, based on currently available evidence. |
| 2 | Plausible in foreseeable use or misuse; relevant evidence is incomplete. |
| 3 | Expected or recurrent without a control, supported by evidence. |

No evidence supports a frequency estimate for this unimplemented product.
Ratings of L=2 are provisional screening judgments, not measured probabilities;
L=1 also indicates an assumption requiring review. Do not assign L=3 without
supporting evidence.

Calculate the pre-control and, once supported, post-control screening scores
as `S × L` using the same scales and matrix:

| Severity \ Likelihood | 1 | 2 | 3 |
|---|---:|---:|---:|
| 1 | Low (1) | Low (2) | Medium (3) |
| 2 | Low (2) | Medium (4) | High (6) |
| 3 | Medium (3) | High (6) | High (9) |

The score prioritizes review; it is not an acceptance threshold. Post-control
severity, likelihood, and score are pending until the relevant controls are
implemented and verified. A competent reviewer must then assess the combined
controls against the same scenario, record the supporting evidence and
limitations, and determine whether any change to severity or likelihood is
justified. Do not subtract control effects or infer acceptance from a test
pass or a lower score. Any high rating,
severity-3 scenario, material uncertainty, or conflict with intended use
requires explicit review and disposition; this screening method cannot waive
that review. The software-safety reviewer must confirm or revise the criteria
and ratings and explain changes.

## Hazards

These identifiers describe the potential sources of harm in the risk analysis.
They are proposed for this assessment and require competent review.

| ID | Description |
|---|---|
| HAZ-01 | Incorrect remaining-time information. |
| HAZ-02 | Interruption or lost state goes undetected or appears as reliable progress. |
| HAZ-03 | Unexpected sensory event or falsely signaled playback or timer status. |
| HAZ-04 | A caregiver-configured setting changes without the caregiver's knowledge. |
| HAZ-05 | The presentation is interpreted differently from the configured remaining time or actual state. |
| HAZ-06 | An ordinary-waiting timer is treated as an authoritative or safety-critical timing system. |

## Harms

These identifiers group the potential consequences described in the risk
analysis; they do not imply that a consequence has occurred or that its
likelihood or severity has been verified.

| ID | Description |
|---|---|
| HARM-01 | Confusion, distress, or conflict associated with the waiting experience. |
| HARM-02 | Disruption of an ordinary wait, including unnecessary extension, interruption, or premature action. |
| HARM-03 | Discomfort or distress from unexpected or excessive audio. |
| HARM-04 | Serious harm from reliance on the timer for a consequential or safety-critical purpose outside its intended use. |

## Risk analysis

The **Risk** column contains the provisional pre-control score; the adjacent
**Description** describes potential harm. Pre-control severity and likelihood
are shown separately. **Reduced Risk** is the post-control score, with
post-control severity, likelihood, and description separately recorded.
All controls are proposed and unverified; reduced risk and acceptability remain
undetermined. Each proposed control and its intended effect is identified in
the [control tables](#risk-controls). Supporting evidence,
ownership, intended-use trace, and planned verification are in the
[risk traceability matrix](risk-traceability-matrix.md).

| ID | Scenario | Hazard | Hazardous Situation | Harm | Pre-control S | Pre-control L | Risk | Description | Risk Controls | Post-control S | Post-control L | Reduced Risk | Reduced Risk Description | Acceptability |
|---|---|---|---|---|---:|---:|---|---|---|---|---|---|---|---|
| RISK-001 | A timing defect, inaccurate elapsed-time source, wall-clock change, duration-boundary error, or inconsistent visual mapping causes progress or completion to be early, late, frozen, or misleading. | [HAZ-01](#hazards) | The person waiting or caregiver relies on the incorrect display or completion state to judge whether the wait has ended or how much remains. | [HARM-01](#harms), [HARM-02](#harms), [HARM-04](#harms) | 2 | 2 | 4 (Medium) | Confusion, distress, conflict, or an unnecessarily extended or interrupted ordinary wait; more serious consequences are possible if misused for a consequential purpose outside intended use. | [RC-001-1 to RC-001-4](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review required. |
| RISK-002 | Backgrounding, sleep, process termination, or device restart results in resumption, reset, lost state, or a displayed state inconsistent with elapsed time. | [HAZ-02](#hazards) | The person waiting or caregiver relies on an apparently running, paused, or completed timer without knowing timing continuity is uncertain. | [HARM-01](#harms), [HARM-02](#harms) | 2 | 2 | 4 (Medium) | Confusion, distress, an unexpectedly extended wait, or premature action. | [RC-002-1 to RC-002-2](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review required. |
| RISK-003 | Optional music or a future completion sound starts unexpectedly, is too loud, stops or fails while playback appears successful, or distracts from the timer. | [HAZ-03](#hazards) | The person waiting is startled, distressed, or distracted, or the caregiver believes failed audio is playing and misinterprets the experience. | [HARM-01](#harms), [HARM-02](#harms), [HARM-03](#harms) | 2 | 2 | 4 (Medium) | Discomfort, distress, interruption of the wait, or confusion about timer operation. | [RC-003-1 to RC-003-3](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review required. |
| RISK-004 | The person waiting reaches caregiver controls or unintentionally changes duration, theme, or music settings. | [HAZ-04](#hazards) | The person waiting or caregiver relies on a timer whose configuration no longer matches the intended wait. | [HARM-01](#harms), [HARM-02](#harms) | 2 | 2 | 4 (Medium) | Confusion, distress, or an unexpectedly shortened or extended ordinary wait. | [RC-004-1 to RC-004-2](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review required. |
| RISK-005 | Progress or timer states cannot be distinguished because of color-only information, ambiguous shapes or motion, insufficient contrast, or inaccessible labels. | [HAZ-05](#hazards) | The person waiting or caregiver incorrectly believes the wait has ended, remains running, or has been interrupted. | [HARM-01](#harms), [HARM-02](#harms) | 2 | 2 | 4 (Medium) | Confusion, distress, unnecessary waiting, or premature action in an ordinary wait. | [RC-005-1 to RC-005-3](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review required. |
| RISK-006 | A user relies on the timer for medication, a procedure, emergency response, a deadline guarantee, or another consequential action despite the exclusion. | [HAZ-06](#hazards) | A user acts, delays action, or omits another required timing or safety system based on the display or completion state. | [HARM-04](#harms) | 3 | 2 | 6 (High) | Serious harm is plausible in this out-of-scope use. | [RC-006-1 to RC-006-2](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review and disposition required. |

## Risk controls

The effect column describes the **intended** mechanism for the named risk,
not a demonstrated reduction. These proposals need requirements, design and
competent review; some describe decisions or reviews rather than implemented
product controls. Likelihood-oriented measures seek to prevent or reduce
exposure to the hazardous situation; severity-oriented measures seek to limit
harm if it occurs. Neither effect may be credited without relevant evidence.
Individual effects cannot be added to calculate the overall post-control score.

### RISK-001 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-001-1 | Use an appropriate monotonic source during active execution instead of frames or callback counts. | Likelihood: reduce timing errors from callback delays or wall-clock changes during active execution. |
| RC-001-2 | Prevent negative remaining time and duplicate completion. | Likelihood: reduce misleading timer states and repeated completion signals. |
| RC-001-3 | Keep remaining-time visual semantics consistent across themes and platforms. | Likelihood: reduce misleading or inconsistent interpretation of progress. |
| RC-001-4 | Implement the reviewed completion behavior; define manual pause/reset behavior before implementation. | Likelihood: establish consistent behavior that can be implemented and verified. Immediate reset to the configured duration and stopping optional music at completion are proposed, subject to review and approval. |

### RISK-002 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-002-1 | Define interruption detection, persistence, and recovery requirements; choose recovery behavior through requirements and risk/UX review. | Likelihood: reduce undetected interruptions and incorrect recovery; effect depends on the chosen behavior and platform support. |
| RC-002-2 | Distinguish running, paused, and interrupted states; at completion, reset immediately to the configured duration without displaying a completed state. Do not imply continuity when unknown. | Likelihood: reduce reliance on an apparently reliable state when timing continuity is uncertain; completion behavior is proposed and remains subject to review and approval. |

### RISK-003 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-003-1 | Define and review volume limits, sound initiation, and completion sounds; avoid sudden or unnecessarily loud audio. | Severity: seek to limit discomfort if sound occurs; likelihood: reduce unexpected or excessive sound. Actual effect depends on decisions and usability evidence. |
| RC-003-2 | Preserve timer operation without music or internet. | Likelihood: reduce interruption of the wait when audio or connectivity fails. |
| RC-003-3 | Define meaningful playback-failure presentation and do not show successful playback after failure. | Likelihood: reduce mistaken belief that playback succeeded; presentation remains undecided. |

### RISK-004 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-004-1 | Define separate patient and caregiver capabilities and select and review a caregiver protection mechanism. | Likelihood: reduce unauthorized access to or alteration of caregiver settings; mechanism remains undecided. |
| RC-004-2 | Preserve patient access to the approved volume control without exposing caregiver timer settings. | Likelihood: reduce settings exposure while allowing volume adjustment; design and limits remain undecided. |

### RISK-005 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-005-1 | Use redundant, consistent visual and accessible semantics for progress and states. | Likelihood: reduce misunderstanding caused by inaccessible or ambiguous presentation. |
| RC-005-2 | Avoid flashing and unnecessary motion. | Likelihood: reduce distracting presentation that may contribute to misunderstanding. |
| RC-005-3 | Review themes and interactions with caregivers and representative patients under appropriate consent and safeguards. | Likelihood: identify and correct misunderstandings before release; review alone does not establish an effective control. |

### RISK-006 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-006-1 | Keep intended-use limits and claims consistent, exclude consequential timing from claims, and provide relevant limitations in product information. | Likelihood: seek to reduce reliance in out-of-scope situations; warnings alone do not ensure correct use. |
| RC-006-2 | Escalate conflicting actual use, functions, or claims before affected work continues and reassess qualification when needed. | Likelihood: seek to prevent unresolved scope conflicts from continuing; effectiveness depends on detection and action. |

## Decisions and follow-up

The following behavior and control decisions remain unresolved and require
requirements and risk/UX review, primarily in M2:

- manual pause/reset and interruption/recovery behavior; the proposed
  immediate reset/music-stop completion behavior requires review and approval;
- caregiver protection and patient access to timer settings;
- volume limits, audio initiation, completion sounds, and playback-failure
  presentation; and
- the accessible presentation and validation of progress and timer states.

Do not silently choose these behaviors during implementation. M1-DOC-005
revision 0.2 records that IEC 62304 software safety classification is not
applicable to the defined non-medical software scope. These product risks and
controls remain subject to their own qualified review and verification; this
applicability decision does not classify them or establish acceptability.

## Required review walkthrough and evidence gaps

Before approval, the reviewer must walk through at least one incorrect-timing,
one sound, and one settings-access scenario from cause through hazardous
situation, harm, proposed control, and follow-up verification, and check for
omissions. Record actual review scope, findings, and dispositions in the PR.
That review has not occurred.

- No reviewer is assigned to the risk assessment's required software-safety
  walkthrough; this remains a blocking review gap. The reviewer appointment
  for M1-DOC-005 is scoped to IEC 62304 applicability and does not by itself
  complete this risk review.
- No approved requirement IDs, implemented controls, residual-risk
  assessments, verification results, or representative patient usability
  findings exist in this record.
- Initial ratings and proposed controls require competent review; no risk is
  declared acceptable.
- The intended-use candidate's exact-revision approval and the qualification
  record's PR acceptance remain unresolved in their source documents. Issue
  closure or document lifecycle status does not establish that evidence.
- The assessment is limited to the intended-use candidate and evidence listed
  above; new functionality, claims, use environments, or market scope require
  impact assessment and may require reassessment.

These gaps must be resolved or handled under the documented completion gates;
they are not waived by this initial assessment.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Initial candidate assessment for issue #4; define screening method and six proposed risk entries, with traceability in M1-DOC-009 and unresolved review evidence |
| 0.2 | Replace the proposed IEC 62304 classification dependency with the M1-DOC-005 non-applicability conclusion; retain the separate risk-review and verification gates |
| 0.3 | Align proposed risk controls and open decisions with M2 requirements for caregiver-only Settings and immediate timer reset/music stop at completion; keep controls unverified and review pending |
| 0.4 | Remove requirement-ID cross-references; retain the risk controls and verification here while requirement-risk-test links are maintained in the M2 traceability matrix |
