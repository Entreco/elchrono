# Usability review plan

| Field | Value |
|---|---|
| Document ID | M2-DOC-005 |
| Revision | 1.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Product usability, accessibility, and safeguarding/privacy review: @Entreco, designated by the product owner on 2026-11-19; exact-revision acceptance pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#24](https://github.com/Entreco/elchrono/issues/24) |
| Review PR | Prior baselines: [PR #30](https://github.com/Entreco/elchrono/pull/30), [PR #32](https://github.com/Entreco/elchrono/pull/32), and [PR #41](https://github.com/Entreco/elchrono/pull/41); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); issue #67: [PR #68](https://github.com/Entreco/elchrono/pull/68); issue #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); status-indicator follow-up: [draft PR #74](https://github.com/Entreco/elchrono/pull/74); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This plan describes a proposed, safeguarded usability review of El Chrono's
screen and timer-visualization prototypes. It is grounded in the proposed
[requirements register, M2-DOC-001 revision 1.3](../product/requirements.md),
the [test protocol, M2-DOC-002 revision 1.6](../testing/test-protocol.md), the
[requirements traceability matrix, M2-DOC-003 revision 3.8](../safety/requirements-traceability-matrix.md),
the [screen-flow and theme-prototype record, M2-DOC-004 revision 2.4](../design/accessible-screen-flows-and-theme-prototypes.md),
and [risk assessment, M1-DOC-004 revision 1.2](../safety/risk-assessment.md).
The product owner approved the four demo visualizations for the v1 theme
selector; this plan does not claim their usability or representative-user
preference.

The review is intended to identify misunderstandings and design questions for
ordinary waiting-time support. It is not clinical research, a medical
assessment, product validation, accessibility-conformance testing, or evidence
that any control is effective. Do not make medical claims or present the timer
for safety-critical timing. Requirements, risk controls, and design decisions
remain subject to their own review and approval.

No participant session may begin under this candidate plan. The product owner
must appoint competent reviewers, approve the final protocol and safeguarding
arrangements, arrange participants, and ensure the consent process is
appropriate to each participant, jurisdiction, age, and capacity before
recruitment or a session.

Two sessions took place on 2026-11-16, before this plan was created. They were
not governed by this document; this plan does not retrospectively authorize or
validate them. Their reported scope, evidence, and procedural limitations are
recorded in [M2-DOC-006](usability-findings.md). This plan applies to any
future sessions.

## Review objectives

1. Explore whether people understand that each timer visualization represents
   remaining duration, not time of day, and how they interpret its progress.
2. Explore whether running, paused, and interrupted states are distinguishable
   and whether an interrupted display avoids implying reliable progress,
   including the absence of running/paused icons at detail 0, the retained
   interrupted/idle cues, and the accessible stale-progress description.
3. Review the proposed Main, Settings, and About flows, including the
   unprotected Settings access selected for v1.
4. Explore caregiver comprehension of duration entry, theme selection, and
   the pause-on-open/save-to-start behavior, including the absence of separate
   manual pause, resume, and reset controls.
5. Gather open-ended reactions to the proposed visualizations and sensory
   presentation without assuming a preference, fixed theme count, or
   representative preference from any one participant.
6. Explore how the Clock's duration labels and the centered Fan shape are
   understood at compact and expanded sizes, without treating label values or
   geometry as evidence of usability.
7. Identify accessibility barriers and unanswered questions for the actual
   prototype/input methods reviewed, without implying standards conformance.

## Prototype and review scope

Review the exact M2-DOC-004 revision 2.4 candidate content. Before any session,
verify that the materials shown match that revision and record any changed
artifact and revision in the findings record.

The future review scope includes readability of translucent Settings against
each global background, whether the bottom-right cue is mistaken for a button,
understanding immediate duration previews versus a paused countdown, Battery
recognition, absence of detail-induced motion, and thin/solid Ring
interpretation. Ask neutral prompts; do not assume these treatments are
preferred. No new participant session is authorized by this change.

The candidate evidence includes:

- Static SVGs and written proposals for Main, Settings, About, and running,
  paused, and interrupted presentations. The states are not interactive.
- A local browser demonstration with a configurable countdown and Clock,
  Battery, Hand fan, and Ring shapes. It permits shape selection, duration
  selection, shared color/font controls, remaining-time visibility, Off or
  2–12 details, and hiding/showing the personalization panel. Its settings are
  prototype-only; it does not demonstrate the revised Settings lifecycle.
- No implemented application screens, screen-reader support, or interruption
  recovery. The demo does not implement pause, save-to-start transitions,
  persistence, or recovery. It must not be presented as the product.

Do not treat unavailable prototype behavior as tested. In particular, actual
navigation, patient access protection, audio behavior, screen-reader
semantics, keyboard/touch support, the completion transition, and timer
recovery need separate evidence or remain explicit gaps.

## Participant approach and eligibility

Seek feedback from:

- Caregivers who support ordinary waiting and can comment on caregiver setup
  and patient/caregiver role separation.
- People with cognitive or intellectual disabilities who are within the
  proposed intended user population, with an appropriate range of relevant
  communication and accessibility needs where feasible.

No single participant represents all caregivers or people with disabilities.
Use non-judgmental, accessible explanations, adequate time, breaks, and
participant-preferred communication. Do not require a participant to explain a
diagnosis or disclose medical history. Do not describe participation as care,
assessment, or treatment.

Before recruitment, the product owner and appointed safeguarding/privacy
reviewers must define and document a suitable recruitment route, eligibility
screen, facilitator competence, accommodations, consent capacity process, and
whether any legally authorized representative is needed. Obtain the
participant's informed consent where they can provide it and any additional
permission required by applicable law; seek and respect the participant's
assent and ongoing willingness. A representative's permission does not
override a participant's refusal or distress. If capacity, consent, assent,
privacy, or safeguarding is unclear, do not proceed until appropriately
qualified review resolves it.

Participation must be voluntary, with no effect on services, care, or
relationship with a caregiver. Explain the ordinary-waiting purpose, what will
happen, what data will be captured, who can access it, the right to skip a
question or stop, and how to withdraw notes before anonymized aggregation.
Provide the information in an accessible form. Do not recruit through a
participant's dependency relationship in a way that pressures participation.

## Preparation and session method

Before the first session:

1. Complete the required reviewer appointments and approve this protocol,
   accessible participant information, consent/permission process, assent and
   stop procedure, facilitator arrangements, data handling, and any
   jurisdiction-specific safeguarding or ethics review.
2. Confirm and archive the exact prototype artifacts, revision, browser/device
   configuration, and any assistive technology or input method to be used.
   Do not include personal accounts or identifiers in the demo.
3. Prepare a quiet, predictable session with a trained facilitator, an
   optional observer, breaks, and a participant-selected communication method.
   Explain that the prototype can be incomplete and that the participant is
   evaluating the design, not being tested.
4. Use open prompts first. Avoid teaching the intended answer before asking
   what the participant understands. Do not use colour as the sole cue or
   create flashing, loud, or unnecessary sensory effects.
5. Stop immediately at the participant's request, refusal, distress, fatigue,
   or safeguarding concern. Do not encourage continued participation.

Use a moderated, think-aloud or conversational review only where comfortable
for the participant. The facilitator may read a task verbatim, but should not
correct, coach, or infer success from silence. Record task outcomes as
completed, completed with assistance, not completed, skipped, or not
applicable, with a concise behavioral observation and the participant's
meaning in their own terms where safely paraphrasable.

## Tasks and neutral prompts

| ID | Participant / material | Task and prompts | Evidence sought |
|---|---|---|---|
| U-01 | Patient participant; same-progress examples from the Clock, Battery, Hand fan, and Ring demo | “What do you think this shows?” “What makes you think that?” “What do you think will happen next?” Ask before explaining the intended meaning. | Interpretation of remaining duration, progress direction, ambiguity with a clock/time of day, and differences between shapes. Do not treat a preference as a usability result. |
| U-02 | Patient participant; running, paused, interrupted, and idle Main-state examples at detail 0 and 1-12 | “What do you think is happening in each picture?” “How can you tell?” “In which one might the progress be out of date?” Ask about any visible cue before explaining it. | Whether icons and warnings are understood without relying on colour, including stale-progress meaning and the absence of running/paused cues at detail 0. These are static comprehension tasks, not transition tests; do not infer accessibility from the design alone. |
| U-03 | Caregiver participant; duration-entry proposal and demo duration selector | Ask the caregiver to describe how they would enter a short duration and a duration with hours. Use examples `2`, `20`, `205`, and `2400`; ask what each should display and whether any value is unclear. | Comprehension of the proposed Hours/Minutes sequential-digit rule and range, not proof that the demo implements or verifies REQ-001. |
| U-04 | Caregiver and patient participants as appropriate; static Main/Settings flow | “Show me how you would change the timer duration or theme.” Explain that v1 Settings has no caregiver-protection mechanism and may be opened by a patient; ask what they expect or consider concerning about that choice. Ask what they expect after Settings pauses a running timer and confirm that no separate manual pause, resume, or reset controls are provided. | Comprehension of the open Settings behavior, the selected save-to-start behavior, and possible consequences of open access. This is formative feedback, not evidence that RISK-004 is acceptable or controlled. |
| U-05 | Caregiver participant; four demo shapes in a counterbalanced order where feasible | “What do you notice?” “Is any option easier or harder to understand?” “Would you choose one for this wait? Why or why not?” “Is there anything you would avoid?” | Individual reactions and reasons, including no preference or mixed preference. Do not generalize to all users or prescribe a fixed theme set. |
| U-06 | Patient participant; chosen prototype at a comfortable viewing size | Ask what text, progress, and state information is noticeable; offer the participant's preferred zoom, communication, or assistive method if available. “Is anything difficult to see, understand, or use?” | Observed barriers and accommodations. The artifacts are not instrumented for assistive technology; do not claim screen-reader, keyboard, touch, or conformance verification unless separately performed and recorded. |
| U-07 | Caregiver and patient participants as appropriate; Settings and About static proposals | “What do you understand about the app's purpose and limitations from About?” “What do you expect when you save these settings?” | Comprehension of the ordinary-waiting boundary and save-to-start behavior. Music and in-app volume controls are not v1 capabilities and are not evaluated. |
| U-08 | Caregiver and patient participants as appropriate; completion description in the design record | Ask what they expect when the timer reaches zero, then ask what they expect after Settings are opened while it is running and saved. | Comprehension of immediate reset/no completed state and Settings-open pause/save-to-start. Audio behavior is not evaluated in v1. These are description-only tasks unless a later prototype demonstrates transitions. |
| U-09 | Patient participant; the issue #66 detail-0 Timer in each state and the accessible description where available | Ask what the timer is doing and whether the progress can be trusted after interruption. Ask what the person expects at 00:00. Do not teach the intended meaning first. | How the absence of running/paused cues affects interpretation, whether interrupted/stale and idle cues are understood, and whether idle 00:00 is mistaken for completion. This proposed future review has not been performed. |

After each relevant task, ask “What would you change?” and “Is there anything
you want us to know?” Allow “nothing,” “not sure,” or no response. Record
questions raised and unresolved interpretations without prompting toward
positive feedback.

## Feedback handling and evidence

Use only a participant code and role category in working notes. Do not record
names, contact details, exact age, diagnosis, medical history, photographs,
audio/video, or identifiable quotations in the repository. Do not request
patient account data or credentials. Capture only task outcome, concise
non-identifying observation, an optional paraphrase, accessibility method
needed for the task, and the associated prototype and requirement/risk links.
Do not record an accommodation's medical reason.

Do not use recording devices by default. If a reviewer proposes recording,
stop and obtain separate prior approval for necessity, consent, security,
retention, access, and deletion before any session; no recording may be
started unless that approval and consent are documented. Keep any necessary
raw notes outside the repository in a controlled location with restricted
access. Before recruitment, document the data owner, access, retention period,
deletion method, and withdrawal handling; do not assume a retention period.
Only de-identified, minimum-necessary summaries and design impacts belong in
the repository. If anonymization cannot be assured, omit the material.

For each observed issue, record a stable finding ID, prototype/revision,
participant role category (only if needed), task, observation, affected
requirement/design/risk IDs, severity/impact rationale, disposition, owner,
follow-up reference, and limitations in the findings record. Separate direct
observation from interpretation. Seek review of interpretations and proposed
changes; do not present a qualitative observation as prevalence, clinical
evidence, or proof of risk reduction.

## Disposition and readiness

Route actionable design or requirement changes to the appropriate issue and
record unresolved matters with an owner and follow-up. Update M2-DOC-003, the
requirements traceability matrix, with actual evidence or explicit gaps linked
to REQ-001 through REQ-010 as relevant, design items, and RISK-001 through
RISK-005. Do not silently change the recorded v1 decisions or make v2 music, audio,
volume, or interruption-recovery behavior appear implemented or approved.

The review is not complete until actual session scope/findings are recorded,
or reasonable recruitment/arrangement attempts and the reason sessions could
not proceed are documented alongside any alternative evidence and remaining
gaps. Do not call the prototypes user-validated without representative-user
evidence. [M2-DOC-006 revision 0.6](usability-findings.md) records two
retrospectively reported sessions, the caregiver availability explanation, and
the owner's decision to skip a replacement caregiver session for that
formative round. Its bounded sufficiency rationale supports further design
iteration, not validation of caregiver workflows or waiver of M2 gates.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish the proposed participant approach, consent/safeguarding prerequisites, prototype scope, tasks, privacy-minimizing evidence handling, and limitations for issue #24; no sessions authorized or claimed. |
| 0.2 | Clarify that the two reported sessions predated this plan and were not governed or retrospectively authorized by it; retain the plan for future sessions. |
| 0.3 | Link the reported formative sessions and caregiver-participation limitation; distinguish the owner's decision to continue iteration from completed validation or milestone approval. |
| 0.4 | Align future review tasks with the v1 decisions for open Settings, save-to-start pause behavior, and deferred v2 music/volume; retain the earlier sessions' evidence limitations. |
| 0.5 | Update the exact design source revision to M2-DOC-004 revision 1.5 and retain all participant, safeguarding, and review prerequisites. |
| 0.6 | Align requirements, protocol, traceability, and design references to the review candidate; include the no-manual-controls decision in future usability scope. |
| 0.7 | Align source references with current product/design/risk candidates and record the owner's reviewer designation and four-theme scope without claiming usability validation. |
| 0.8 | Align review scope with issue #66's text-free detail-0 cues, stale-progress warning, idle 00:00, and source revisions; add neutral future review tasks without claiming they were performed. |
| 0.9 | Align review sources with the demo-matching Clock labels and centered Fan geometry; add a neutral future review task while keeping all usability and accessibility reviews pending. |
| 1.0 | Extend future review scope to global appearance, cue interpretation, preview, stable details, Battery, and thin/solid Ring; authorize no new session. |
| 1.1 | Add the absent detail-0 running/paused distinction and revised indicator policy to future review; authorize no new session. |
