# Risk assessment

| Field | Value |
|---|---|
| Document ID | M1-DOC-004 |
| Revision | 0.8 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software-safety and product-risk review: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#4](https://github.com/Entreco/elchrono/issues/4) |
| Review PR | Prior baselines: [PR #14](https://github.com/Entreco/elchrono/pull/14), [PR #16](https://github.com/Entreco/elchrono/pull/16), [PR #32](https://github.com/Entreco/elchrono/pull/32); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); issue #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This is an initial, qualitative assessment of foreseeable risks in the
proposed ordinary-waiting use of El Chrono. It identifies scenarios, possible
harms, assumptions, and controls for review and follow-up. It is not a
completed safety assessment, a determination that any risk is acceptable, or
evidence that a proposed control has been implemented or verified.

Issues #47, #65, and #67 provide the browser shell, Settings, and About
candidates; issue #66 updates the Timer presentation. Product verification,
patient usability findings, operational data, and verified accessibility or
interruption controls are not available to support frequency estimates. The assessment therefore uses provisional screening ratings and
explicitly records the assumptions behind them. The documented risk scenarios
and product decisions do not establish control effectiveness or residual-risk
acceptability.

This voluntary risk record does not change El Chrono's proposed non-medical
intended use, add a medical claim, or establish IEC 62304 conformity or
certification. The product is not intended for safety-critical timing,
including medication, procedure, or emergency timing.

## Assessed scope and sources

The proposed product boundary and ordinary-waiting use are defined in
[M1-DOC-002, revision 1.0](../product/intended-use.md). That revision is a
candidate; its exact-revision approval evidence remains unresolved. The
EU/US qualification conclusion in
[M1-DOC-003, revision 0.7](../regulatory/qualification-and-classification.md)
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
Customization and automatic persistence add review scope for unintended
configuration changes, invalid/stale stored values, loss of accessibility
semantics, detail representations, and storage-read/save failures. These
scenarios remain covered through RISK-004 and RISK-005; no post-control risk
rating or acceptability disposition is claimed until implementation and
human review evidence are available.

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

The candidate is not in field use, and no operational or representative-user
evidence supports a frequency estimate.
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
| RISK-002 | Backgrounding, sleep, process termination, or device restart results in resumption, reset, lost state, or a displayed state inconsistent with elapsed time; at detail 0 the text warning is intentionally hidden and an unclear replacement cue may fail to communicate stale progress. | [HAZ-02](#hazards) | The person waiting or caregiver relies on an apparently running, paused, or interrupted timer without understanding that timing continuity or progress is uncertain. | [HARM-01](#harms), [HARM-02](#harms) | 2 | 2 | 4 (Medium) | Confusion, distress, an unexpectedly extended wait, or premature action. | [RC-002-1 to RC-002-2](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review required. |
| RISK-003 | Future optional music or a completion sound starts unexpectedly, is too loud, stops or fails while playback appears successful, or distracts from the timer. Audio is excluded from v1 and planned for v2. | [HAZ-03](#hazards) | In a future audio-enabled version, the person waiting is startled, distressed, or distracted, or the caregiver believes failed audio is playing and misinterprets the experience. | [HARM-01](#harms), [HARM-02](#harms), [HARM-03](#harms) | 2 | 2 | 4 (Medium) | Discomfort, distress, interruption of the wait, or confusion about timer operation. | [RC-003-1 to RC-003-3](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review required before audio is introduced. |
| RISK-004 | In v1, a patient can open Settings without a caregiver-protection mechanism and change duration or theme, intentionally or unintentionally. | [HAZ-04](#hazards) | The person waiting or caregiver relies on a timer whose configuration no longer matches the intended wait. | [HARM-01](#harms), [HARM-02](#harms) | 2 | 2 | 4 (Medium) | Confusion, distress, or an unexpectedly shortened or extended ordinary wait. | [RC-004-1](#risk-controls). | Pending | Pending | Pending | Not assessed; no access-control mechanism is credited. | Product-owner acceptance of this bounded v1 exposure is recorded; no control effectiveness, risk reduction, or post-control score is claimed. |
| RISK-005 | Progress or timer states cannot be distinguished because of color-only information, ambiguous shapes or motion, insufficient contrast, inaccessible labels, or misunderstood text-free state cues at detail 0. | [HAZ-05](#hazards) | The person waiting or caregiver incorrectly believes the wait has ended, remains running, or has been interrupted. | [HARM-01](#harms), [HARM-02](#harms) | 2 | 2 | 4 (Medium) | Confusion, distress, unnecessary waiting, or premature action in an ordinary wait. | [RC-005-1 to RC-005-3](#risk-controls). | Pending | Pending | Pending | Not assessed; controls unverified. | Undetermined; qualified review required. |
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
| RC-001-4 | Start the countdown when valid settings are saved; pause it when Settings open; saving starts a new countdown at the full saved duration. At zero, reset immediately without a completed state. | The candidate implements this behavior in the counter domain and app shell; focused domain tests pass. Browser UI integration and full protocol verification remain pending. Music stop-at-zero applies only if music is introduced in v2. |

### RISK-002 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-002-1 | On `ON_STOP` while running, pause the timer and mark it interrupted; do not resume automatically on `ON_START`. Do not recover timer state after process termination or device restart. | The in-process lifecycle behavior avoids presenting uncertain progress as current; process termination/restart remains an explicit limitation, not a risk control or evidence of acceptable residual risk. Platform event delivery and effectiveness remain unverified. |
| RC-002-2 | Distinguish running, paused, and interrupted states; when continuity is uncertain, show a text warning at detail 1-12 and a distinct visual cue plus accessible stale-progress description at detail 0. Do not imply current progress. At completion, reset immediately without a completed state. | Likelihood: the warning/cue may reduce reliance on apparently reliable progress. The revised detail-0 presentation and its accessibility/usability require human review; no effectiveness is credited. |

### RISK-003 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-003-1 | Defer music, in-app volume controls, and completion sounds from v1; separately review and define audio behavior before v2 integration. | Scope exclusion avoids introducing audio behavior in v1; it does not reduce or accept the future audio risk. |
| RC-003-2 | Keep timer operation independent of network connectivity. | Likelihood: avoid connectivity being a timer prerequisite; no v1 music is provided. |
| RC-003-3 | Before v2 audio integration, define meaningful playback-failure presentation and do not show successful playback after failure. | Future requirement; no audio-failure control is implemented or verified in v1. |

### RISK-004 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-004-1 | Record the product owner's v1 decision to provide no caregiver-protection mechanism and retain the resulting risk for competent review; do not imply restricted access. | This records an explicit choice; it is not an implemented control or risk reduction. |

### RISK-005 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-005-1 | Use consistent non-color-only progress/state cues and accessible semantics; at detail 0, hide all Timer text and provide non-text state cues with screen-reader descriptions, including stale-progress meaning. | Likelihood: reduce misunderstanding caused by inaccessible or ambiguous presentation. The issue #66 detail-0 replacement for the text warning is unreviewed and unverified; no control effectiveness is credited. |
| RC-005-2 | Avoid flashing and unnecessary motion. | Likelihood: reduce distracting presentation that may contribute to misunderstanding. |
| RC-005-3 | Review themes and interactions with caregivers and representative patients under appropriate consent and safeguards. | Likelihood: identify and correct misunderstandings before release; review alone does not establish an effective control. |

### RISK-006 controls

| ID | Proposed control | Intended effect and rationale |
|---|---|---|
| RC-006-1 | Keep intended-use limits and claims consistent, exclude consequential timing from claims, and provide relevant limitations in product information. | Likelihood: seek to reduce reliance in out-of-scope situations; warnings alone do not ensure correct use. |
| RC-006-2 | Escalate conflicting actual use, functions, or claims before affected work continues and reassess qualification when needed. | Likelihood: seek to prevent unresolved scope conflicts from continuing; effectiveness depends on detection and action. |

## Product decisions and risk rationale

The product owner supplied the following v1 decisions on 2026-11-17. The
product owner designated @Entreco as reviewer for the risk and safety scopes
and confirmed that designation on 2026-11-19. Exact-revision review findings
and acceptance are recorded against the current PR head; product decisions
below do not demonstrate control effectiveness.

| Decision | Product disposition | Scope rationale and remaining risk |
|---|---|---|
| D-01 — Caregiver protection | No caregiver-protection mechanism in v1; patients may access Settings and change duration/theme. The product owner accepts this bounded exposure for v1. | The v1 purpose is ordinary waiting-time support, and Settings contain only duration/theme controls; unintended changes can still cause confusion or extend/shorten a wait. No access control is credited and no risk reduction or post-control score is claimed. Reassess before expanding Settings, adding personal data or audio accounts, or claiming broader use. |
| D-02 — Volume controls | No in-app volume controls in v1; revisit only if a later product scope adds audio. | No audio is provided in v1, so there is no app playback volume to adjust. This avoids an unused control, but does not choose system-volume behavior or promise controls in v2. |
| D-03 — Music and sound | Defer music integration and its start behavior to v2; no music or completion sound in v1. | With no v1 audio capability, there is no playback start or volume behavior to select or misrepresent. Provider, consent/data flow, playback restrictions, audio failure, and sound behavior require separate review before v2. |
| D-04 — Settings lifecycle | Opening Settings while running pauses the countdown; saving valid settings starts a new countdown at the full saved duration. | The explicit save is the start action and avoids silently resuming a possibly changed configuration. Counter-domain tests cover pause/save behavior; browser UI integration and product verification remain pending. Settings remain unprotected. |
| D-05 — Interruption detection and recovery | A configured app pauses and displays interrupted on lifecycle `ON_STOP` while running; `ON_START` does not resume it. A valid Settings save starts a new countdown from the full saved duration. State is not recovered across process termination or device restart. | This is an in-process interruption response, not recovery of timer continuity. Platform delivery and effectiveness remain unverified; process/restart recovery remains out of scope and RISK-002 review is pending. |
| D-10 — Text-free detail 0 | Issue #66 carries the owner-requested behavior to hide all on-screen Timer text, including interruption warnings, at detail 0 while retaining non-text state cues and accessible stale-progress descriptions. | This changes how the interruption warning is communicated. Human product, accessibility/usability, and risk review and browser verification remain pending; no equivalence or risk reduction is claimed. |

These are bounded product-scope justifications, not evidence that the risks
are eliminated, controls are effective, or residual risk is acceptable. The
user-facing non-medical and ordinary-waiting boundary remains essential; it
does not remove the need for qualified review. M1-DOC-005
revision 0.2 records that IEC 62304 software safety classification is not
applicable to the defined non-medical software scope. These product risks and
controls remain subject to their own qualified review and verification; this
applicability decision does not classify them or establish acceptability.

## Scenario walkthrough and review limitations

The following walkthrough follows the cause-to-harm-to-control chain required
for review. It records the scope considered, not evidence that a control works.
@Entreco is the product-designated reviewer for this scope; exact-revision
findings and acceptance are recorded in the current PR.

| Scenario | Cause → hazardous situation → harm | Proposed control and verification | Disposition |
|---|---|---|---|
| Incorrect timing (RISK-001) | Clock-source or duration defect → displayed remaining time does not match the configured wait → confusion, wait disruption, or out-of-scope reliance. | Monotonic time, bounded whole-minute input, non-negative remaining time, and consistent theme mapping; verify boundaries, lifecycle transitions, completion, and presentation using the M2 protocol. Timer-domain evidence covers only its documented scope; UI/platform behavior remains unverified. | Initial scenario and links are retained. Provisional likelihood/score are not measured; no residual-risk score is assigned before verification. |
| Sound/playback (RISK-003) | Future provider or sound failure/unexpected playback → user experiences unexpected audio or believes playback succeeded → discomfort, distress, or distraction. | V1 excludes music, volume, and completion sound. Verify their absence in v1. Any future audio requires reviewed provider/data-flow/failure requirements and platform tests. | No v1 sound behavior is assumed or tested. The future risk remains open and is not reduced or accepted by the v1 exclusion. |
| Settings access (RISK-004) | No caregiver gate → patient changes duration/theme → caregiver or patient relies on a changed configuration → confusion or an altered ordinary wait. | No access-control mitigation is selected. Verify that Settings is openly accessible and the saved configuration is visible; assess the exposure in UX and risk review. | Product owner accepts the bounded v1 exposure. No control, risk reduction, or post-control score is claimed. Reassess if Settings expands or intended use changes. |

Review limitations:

- The assessment remains an initial qualitative screening; ratings and
  criteria are provisional and not statistical or medical severity estimates.
- No control effectiveness, residual-risk score, or general residual-risk
  acceptance is claimed. RISK-004's bounded product-owner disposition does
  not accept unrelated risks or establish a reduced score.
- The intended-use and qualification scope must remain the non-medical
  ordinary-waiting use. New functions, claims, markets, or evidence of
  consequential reliance require reassessment and escalation.
- Exact-revision findings/disposition, final head SHA, and human acceptance
  are required in the current PR. Merge and issue closure alone are not review
  evidence.

These gaps must be resolved or handled under the documented completion gates;
they are not waived by this initial assessment.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Initial candidate assessment for issue #4; define screening method and six proposed risk entries, with traceability in M1-DOC-009 and unresolved review evidence |
| 0.2 | Replace the proposed IEC 62304 classification dependency with the M1-DOC-005 non-applicability conclusion; retain the separate risk-review and verification gates |
| 0.3 | Align proposed risk controls and open decisions with M2 requirements for caregiver-only Settings and immediate timer reset/music stop at completion; keep controls unverified and review pending |
| 0.4 | Remove requirement-ID cross-references; retain the risk controls and verification here while requirement-risk-test links are maintained in the M2 traceability matrix |
| 0.5 | Align risk scenarios and controls with the v1 product-owner decisions on Settings access, volume/audio scope, save-to-start pause behavior, and no interruption recovery; retain risk-review and verification gates. |
| 0.6 | Record the bounded RISK-004 v1 product-owner disposition and a cause-to-harm walkthrough for timing, future audio, and Settings access; retain provisional ratings and unverified-control limitations. |
| 0.7 | Align risk sources to the current intended-use and qualification candidates; record the counter/settings-domain implementation and focused test evidence without claiming product verification, control effectiveness, or risk acceptance. |
| 0.8 | Align RISK-002 and D-05 with the lifecycle stop response: pause and mark interrupted without automatic resume, distinguish it from unavailable process/restart recovery, and retain platform verification and risk-review gaps. |
| 0.9 | Add issue #66's text-free detail-0 warning-cue change to RISK-002/RISK-005 and RC-002-2/RC-005-1; retain provisional ratings and require human review before crediting effectiveness. |
