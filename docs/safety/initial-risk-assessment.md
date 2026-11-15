# Initial risk assessment

| Field | Value |
|---|---|
| Document ID | M1-DOC-004 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software-safety reviewer: unassigned; blocking |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#4](https://github.com/Entreco/elchrono/issues/4) |
| Review PR | Not opened; add the PR link when this local candidate is published for review |

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
situation is before the proposed controls, given the described use and known
evidence:

| Value | Criterion |
|---|---|
| 1 | Unlikely in the described use, based on currently available evidence. |
| 2 | Plausible in foreseeable use or misuse; relevant evidence is incomplete. |
| 3 | Expected or recurrent without a control, supported by evidence. |

No evidence supports a frequency estimate for this unimplemented product.
Ratings of L=2 are provisional screening judgments, not measured probabilities;
L=1 also indicates an assumption requiring review. Do not assign L=3 without
supporting evidence.

Calculate the initial screening score as `S × L` and use this matrix:

| Severity \ Likelihood | 1 | 2 | 3 |
|---|---:|---:|---:|
| 1 | Low (1) | Low (2) | Medium (3) |
| 2 | Low (2) | Medium (4) | High (6) |
| 3 | Medium (3) | High (6) | High (9) |

The score prioritizes review; it is not an acceptance threshold. No residual
ratings are assigned because controls are only proposed. Any high rating,
severity-3 scenario, material uncertainty, or conflict with intended use
requires explicit review and disposition; this screening method cannot waive
that review. The software-safety reviewer must confirm or revise the criteria
and ratings and explain changes.

## Risk entries

### RISK-001 — Incorrect or misleading remaining time

- **Scenario:** A timing defect, inaccurate elapsed-time source, wall-clock
  change, duration-boundary error, or inconsistent visual mapping causes the
  displayed progress or completion state to be early, late, frozen, or
  otherwise misleading.
- **Hazard:** The timer communicates incorrect remaining-time information.
- **Hazardous situation:** The person waiting or caregiver relies on the
  incorrect display or completion state to decide whether the configured wait
  has ended or how much remains.
- **Potential harm:** Confusion, distress, conflict, or unnecessary extension
  or interruption of an ordinary wait. More serious consequences are possible
  only if the timer is misused for a consequential purpose outside its
  intended use.
- **Initial assessment:** S=2, L=2, score 4 (Medium). Both values are
  provisional: the consequence depends on context, and there is no implemented
  timer or operational evidence to estimate likelihood.
- **Assumptions and evidence:** M1-DOC-002 describes ordinary waiting and
  requires consistent remaining-time semantics, a 5-minute to 24-hour range,
  and 5-minute increments. Timer behavior is not implemented or verified.
  Duration boundary, pause/resume, and completion behavior need requirements.
- **Proposed controls:** Derive elapsed time from an appropriate monotonic
  source during active execution rather than animation frames or callback
  counts; prevent negative remaining time and duplicate completion; keep
  remaining-time meaning consistent across themes and platforms; define
  completion and pause/reset behavior before implementation. These controls
  are proposed, not verified.
- **Future verification:** Deterministic tests with an injected time source
  covering allowed duration boundaries, invalid configuration, pause/resume,
  completion boundaries, wall-clock changes, and consistent visual semantics.
  Requirement IDs and the verification plan are not yet established.
- **Intended-use trace:** [Supported role of visual time](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio);
  [foreseeable use and limitations](../product/intended-use.md#foreseeable-use-and-limitations).
- **Owner:** Product owner, @Entreco.

### RISK-002 — Interruption or recovery obscures timer state

- **Scenario:** The app is backgrounded, the device sleeps, the process is
  terminated, or the device restarts while a timer is active; the timer then
  resumes, resets, loses state, or presents a state that does not match elapsed
  time.
- **Hazard:** Interruption or lost state is not detected or is represented as
  reliable progress.
- **Hazardous situation:** The person waiting or caregiver sees an apparently
  running, paused, or completed timer and relies on it without knowing that
  timing continuity is uncertain.
- **Potential harm:** Confusion, distress, an unexpectedly extended wait, or
  premature action based on a misleading completion state.
- **Initial assessment:** S=2, L=2, score 4 (Medium), provisional; platform
  lifecycle behavior and recovery have not been implemented or tested.
- **Assumptions and evidence:** M1-DOC-002 leaves interruption recovery
  unresolved. A monotonic timestamp alone does not provide persistence across
  process or device restart. No recovery behavior is approved.
- **Proposed controls:** Define interruption detection, persistence, and
  recovery requirements; represent running, paused, completed, and interrupted
  states distinctly; do not imply verified timing continuity when it is
  unknown. Whether recovery resumes, pauses, or restarts must be decided through
  requirements and risk/UX review, not by this assessment.
- **Future verification:** Controlled tests for backgrounding, sleep, process
  termination, restart, and wall-clock changes on each supported platform,
  using the approved recovery behavior and a controllable time source.
- **Intended-use trace:** [Supported role of visual time](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio);
  [foreseeable use and limitations](../product/intended-use.md#foreseeable-use-and-limitations).
- **Owner:** Product owner, @Entreco.

### RISK-003 — Unexpected, excessive, or failed audio

- **Scenario:** Optional music or a future completion sound starts unexpectedly,
  is too loud, stops or fails while the interface implies playback succeeded,
  or distracts from understanding the timer.
- **Hazard:** Audio creates an unexpected sensory event or falsely signals
  successful playback or timer status.
- **Hazardous situation:** The person waiting is startled, distressed, or
  distracted, or the caregiver believes audio is playing when it is not and
  misinterprets the experience.
- **Potential harm:** Discomfort, distress, interruption of the wait, or
  confusion about whether the timer remains active.
- **Initial assessment:** S=2, L=2, score 4 (Medium), provisional; audio
  behavior and user response have not been implemented or evaluated.
- **Assumptions and evidence:** Music is optional and is not required for
  timer operation or understanding remaining time. Audio failure must not
  interrupt the timer or silently appear successful. Volume limits and
  completion-sound behavior are undecided.
- **Proposed controls:** Define and review volume limits, sound initiation,
  completion-sound behavior, and meaningful playback failure states before
  implementation; preserve timer operation without music or internet; do not
  display a success state when playback failed. Avoid sudden or unnecessarily
  loud audio. This assessment does not choose defaults or authorize a
  completion sound.
- **Future verification:** Verify timer operation with audio disabled and
  offline, playback failure reporting, volume behavior, and approved sound
  behavior on supported platforms. Usability review is needed for sensory
  impact.
- **Intended-use trace:** [Supported role of visual time and optional audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio).
- **Owner:** Product owner, @Entreco.

### RISK-004 — Patient access changes caregiver timer settings

- **Scenario:** The person waiting can navigate from the patient-facing
  experience into caregiver controls or unintentionally change duration,
  theme, or music settings.
- **Hazard:** A caregiver-configured timer setting is changed without the
  caregiver's knowledge.
- **Hazardous situation:** The person waiting or caregiver relies on a timer
  whose duration or other configured behavior no longer matches the intended
  wait.
- **Potential harm:** Confusion, distress, or an unexpectedly shortened or
  extended ordinary wait.
- **Initial assessment:** S=2, L=2, score 4 (Medium), provisional; access
  controls and interaction paths are not implemented or evaluated.
- **Assumptions and evidence:** Patients are intended to adjust volume without
  gaining caregiver settings access. Caregiver protection and its mechanism
  remain unspecified; volume limits also require review.
- **Proposed controls:** Define separate patient and caregiver capabilities;
  preserve patient access to the approved volume control without exposing
  caregiver timer settings; select and review any protection mechanism before
  implementation. This record does not choose authentication, locking, or
  supervision behavior.
- **Future verification:** Review and test patient and caregiver navigation
  paths, including attempts to reach or alter protected settings, and verify
  that the approved volume control remains accessible.
- **Intended-use trace:** [Users, population, and roles](../product/intended-use.md#users-population-and-roles);
  [supported role of visual time and optional audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio).
- **Owner:** Product owner, @Entreco.

### RISK-005 — Accessibility or state misunderstanding

- **Scenario:** A person cannot distinguish remaining-time progress or
  running, paused, completed, and interrupted states because information is
  conveyed only by color, ambiguous shapes or motion, insufficient contrast,
  or inaccessible control labels.
- **Hazard:** The presentation is interpreted differently from the configured
  remaining time or actual timer state.
- **Hazardous situation:** The person waiting or caregiver believes the wait
  has ended, is still running, or has been interrupted when that is not the
  case.
- **Potential harm:** Confusion, distress, unnecessary waiting, or premature
  action within the ordinary-waiting context.
- **Initial assessment:** S=2, L=2, score 4 (Medium), provisional; no
  representative usability findings exist.
- **Assumptions and evidence:** The product requirements require predictable
  layouts, readable contrast, large controls, accessible labels, and no
  color-only remaining-time semantics. Theme preferences and comprehension
  cannot be assumed.
- **Proposed controls:** Use redundant, consistent visual and accessible
  semantics for progress and timer states; avoid flashing and unnecessary
  motion; review themes and interactions with caregivers and representative
  patients under appropriate consent and safeguarding arrangements.
- **Future verification:** Inspect contrast and accessibility semantics and
  perform suitable usability review of remaining-time meaning and state
  distinctions across themes and supported input methods. No user findings are
  claimed here.
- **Intended-use trace:** [Supported role of visual time](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio);
  [foreseeable use and limitations](../product/intended-use.md#foreseeable-use-and-limitations).
- **Owner:** Product owner, @Entreco.

### RISK-006 — Foreseeable use for consequential or safety-critical timing

- **Scenario:** A caregiver or other user relies on the timer for medication,
  a procedure, emergency response, a deadline guarantee, or another
  consequential action despite the stated exclusion.
- **Hazard:** An ordinary-waiting timer is treated as an authoritative or
  safety-critical timing system.
- **Hazardous situation:** A user acts, delays action, or omits another
  required timing or safety system based on the timer's display or completion
  state.
- **Potential harm:** Serious harm is plausible in such an out-of-scope
  situation.
- **Initial assessment:** S=3, L=2, score 6 (High), provisional. Severity
  reflects the possible consequence if misused, not a supported product use.
  Likelihood is an unmeasured, conservative plausibility judgment for
  foreseeable misuse and requires reviewer confirmation.
- **Assumptions and evidence:** M1-DOC-002 explicitly excludes
  safety-critical timing and reliance on the timer as an authoritative
  deadline. No clinical or safety-critical use is intended or claimed. Actual
  use and public claims have not been observed.
- **Proposed controls:** Keep intended-use limits and all product claims
  consistent; do not describe the timer as suitable for consequential timing;
  retain clear limitations in relevant product information. Escalate any
  requested function, claim, or evidence of actual use that conflicts with the
  non-medical boundary before affected work continues. Reassess qualification
  if intended use, functionality, or claims change.
- **Future verification:** Review requirements, user-facing content, and
  product claims against the intended-use exclusions before release; assess
  changes against the qualification record and this risk entry.
- **Intended-use trace:** [Foreseeable use and limitations](../product/intended-use.md#foreseeable-use-and-limitations);
  [exclusions and claims boundary](../product/intended-use.md#exclusions-and-claims-boundary).
- **Owner:** Product owner, @Entreco.

## Decisions and follow-up

The following behavior and control decisions remain unresolved and require
requirements and risk/UX review, primarily in M2:

- pause, reset, completion, and interruption/recovery behavior;
- caregiver protection and patient access to timer settings;
- volume limits, audio initiation, completion sounds, and playback-failure
  presentation; and
- the accessible presentation and validation of progress and timer states.

Do not silently choose these behaviors during implementation. The software-
safety reviewer must determine whether any issue must be resolved earlier to
support the proposed software safety classification in M1-DOC-005. The
classification decision remains subject to qualified review and escalation if
the proposed Class A process cannot be justified.

## Required review walkthrough and evidence gaps

Before approval, the reviewer must walk through at least one incorrect-timing,
one sound, and one settings-access scenario from cause through hazardous
situation, harm, proposed control, and follow-up verification, and check for
omissions. Record actual review scope, findings, and dispositions in the PR.
That review has not occurred in this local draft.

- No software-safety reviewer is assigned; this is a blocking review gap.
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
| 0.1 | Initial candidate assessment for issue #4; define screening method, six proposed risk entries, traceability, and unresolved review evidence |
