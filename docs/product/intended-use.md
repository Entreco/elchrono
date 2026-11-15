# Intended use

| Field | Value |
|---|---|
| Document ID | M1-DOC-002 |
| Revision | 0.7 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Regulatory qualification reviewer: @Entreco for M1-DOC-003; software-safety reviewer for this intended-use record: unassigned |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#2](https://github.com/Entreco/elchrono/issues/2); market selection follow-up in [#3](https://github.com/Entreco/elchrono/issues/3) |
| Review PR | [PR #13](https://github.com/Entreco/elchrono/pull/13); follow-up PR pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](../governance/document-control.md#lifecycle-and-approval).

## Intended-use statement

El Chrono is proposed as a non-medical visual aid for showing the amount of
waiting time remaining. A caregiver configures a waiting duration, and a person
who benefits from a visual representation of that duration can view its progress
without seeing a clock face or time of day. Background music may optionally
accompany the experience; it is not necessary to understand the visual remaining
time or for the timer to operate as intended.

The timer is for ordinary waiting situations and is intended for general use.
People with cognitive or intellectual disabilities may use it, but the product
is not designed or intended to compensate for a disability or to address a
medical condition. It provides the same general waiting-time display regardless
of a person's diagnosis, disability, or health status; it does not assess or
adapt to any such status.

This is a proposed intended-use boundary, not a description of a completed
product. The current application is a starter screen, not an implemented timer.
The timer, audio behaviour, accessibility, and interruption handling require
separate implementation and verification.

## Users, population, and roles

- **Caregiver:** the adult or responsible supporter who selects and explains a
  waiting duration and configures the experience. The exact caregiver
  responsibilities and access controls are not yet specified.
- **Person waiting:** a person who benefits from a visual representation of
  remaining waiting time. The product direction includes people with cognitive
  or   intellectual disabilities; this describes potential users, not a medical
  indication. The app does not assess, diagnose, determine eligibility by, or
  adapt its function to an individual's condition.
- **Product owner:** @Entreco, as designated in
  [document controls](../governance/document-control.md).

Patient-account requirements, the degree of supervision, and the precise
population and use contexts require review. No patient account or identifiable
patient data is part of this proposed use.

## Care environments and supported situations

The proposed situation is an ordinary waiting period in a context where a
caregiver can set and explain the timer. No particular facility or care setting
has been selected. The selected assessment markets are the EU and United
States; do not infer use in a clinical, educational, residential, or other
specific institution until the product owner and qualified reviewers confirm
the scope.

The app is not intended to provide a clock, time-of-day display, appointment
schedule, deadline guarantee, or authoritative indication that a person may
begin or stop another activity.

## Supported role of visual time and optional audio

The visual experience is intended to communicate progress through a configured
waiting duration. The planned duration range is 5 minutes to 24 hours in
5-minute increments. Remaining-time meaning must stay consistent across themes
and platforms and must not rely on colour alone. The presentation must not
imply a clock face or time of day. These behaviours are requirements, not
verified capabilities of the current starter application.

Optional background music is intended only as accompaniment. It does not define
or communicate the remaining duration. The intended product must remain usable
without music or an internet connection, and audio failure must not interrupt
the timer or be represented as successful playback. These are product
requirements, not verified capabilities of the current starter application.
Patients are intended to be able to change volume without gaining access to
caregiver settings; the control and access details remain subject to
requirements and review.

## Foreseeable use and limitations

A person or caregiver could interpret the display as an exact deadline, a
guarantee that waiting has ended, or a signal to take another action. The
experience is limited to supporting understanding of a waiting period; it does
not establish what action should follow. The caregiver remains responsible for
choosing and explaining the duration and for determining what happens when the
wait ends.

Pause, reset, completion, patient access to caregiver settings, volume limits,
and recovery after interruption are not decided by this record. Their
requirements and associated risks need review; this statement does not define
their behaviour.

## Exclusions and claims boundary

El Chrono is not intended to:

- diagnose, treat, monitor, or manage a medical condition;
- compensate for an injury or disability or provide a medical-purpose
  function;
- provide safety-critical timing, including medication or procedure timing;
- support emergency response or be relied on as an emergency timer; or
- replace a caregiver's judgment or another required timing or safety system.

No clinical benefit, improved health outcome, regulatory classification,
compliance, or certification is claimed. The qualification assessment for the
selected EU and US markets is recorded in
[M1-DOC-003](../regulatory/qualification-and-classification.md). That
conclusion applies only to the intended use and functions described here; if
they change or conflict with the non-medical product boundary, reassess and
escalate before affected work continues.

## Target markets

The European Union and United States are selected for qualification assessment.
The EU scope is the EU Member States; other EEA or European jurisdictions are
not included unless separately selected. The United States scope is federal;
launch states, territories, distribution channels, and launch decisions have
not been specified. The qualification conclusion for this intended use is
recorded in [M1-DOC-003](../regulatory/qualification-and-classification.md).
Selection for assessment does not authorize distribution.

## Sources and traceability

- [Project purpose and intended users](../../AGENTS.md#purpose)
- [Non-medical product boundary](../../AGENTS.md#non-medical-product-boundary)
- [Product requirements](../../AGENTS.md#product-requirements)
- [Medical-device documentation and safety](../../AGENTS.md#medical-device-documentation-and-safety)
- [M1 milestone scope and completion gate](https://github.com/Entreco/elchrono/milestone/1)
- [Foundation evidence index](../governance/document-control.md#foundation-evidence-index)

No approved requirement or risk identifiers exist at this revision. Link actual
identifiers and source sections when the applicable controlled records are
established.

## Review evidence gaps

- Issue #1 is closed and its PR #9 is merged. Its main documentation baseline
  is Approved and effective under the lifecycle rule. However, PR #9's checked
  acceptance box still lacks an accepting human and reviewed full head SHA,
  and required specialist review evidence remains unresolved. Reconcile the
  prerequisite review evidence before merging this record; lifecycle status
  does not prove those reviews happened.
- The EU and United States are selected for assessment in revision 0.3.
  Specific launch states, channels, and any non-EU European markets remain
  unspecified.
- Revision 0.6 is a candidate follow-up to closed issue #2. The EU/US
  qualification decision is recorded in M1-DOC-003 by
  @Entreco as qualified regulatory reviewer; approval evidence for this
  intended-use revision remains pending.
- @Entreco is assigned as regulatory qualification reviewer for M1-DOC-003.
  @Entreco is also assigned as competent software-safety reviewer for
  M1-DOC-005; that separate appointment does not constitute a software-safety
  review or approval of this intended-use record. A reviewer for any required
  software-safety scope on this record remains unassigned. An open PR makes
  the branch In review but does not waive required review gates.
- There are no approved requirement or risk IDs yet. Link real identifiers and
  source sections when established; do not invent them.
- No patient usability findings or verification evidence are established by
  this record.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Initial proposed intended-use statement and explicit evidence gaps for issue #2 |
| 0.2 | Replace stored lifecycle labels with branch/PR-derived status, link PR #10, and distinguish prerequisite lifecycle from unresolved review evidence |
| 0.3 | Select the EU and United States for qualification assessment and preserve unresolved launch-scope and approval evidence |
| 0.4 | Clarify general-use ordinary-waiting purpose and explicitly exclude disability-compensation and other medical purposes |
| 0.5 | Link the qualified review conclusion and clarify the scope of the non-device determination |
| 0.6 | Use a revision-independent reference to the qualification record to avoid cyclic revision references |
| 0.7 | Clarify the scope of the M1-DOC-005 software-safety reviewer appointment; preserve this record's separate review gap |
