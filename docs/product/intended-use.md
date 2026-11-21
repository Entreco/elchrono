# Intended use

| Field | Value |
|---|---|
| Document ID | M1-DOC-002 |
| Revision | 1.1 |
| Current change review | [PR #78](https://github.com/Entreco/elchrono/pull/78); human acceptance and required specialist review pending |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Intended-use/product-boundary and software-safety scopes: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#2](https://github.com/Entreco/elchrono/issues/2); market selection follow-up in [#3](https://github.com/Entreco/elchrono/issues/3); duration update in [#22](https://github.com/Entreco/elchrono/issues/22) |
| Review PR | [PR #13](https://github.com/Entreco/elchrono/pull/13); [PR #16](https://github.com/Entreco/elchrono/pull/16); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](../governance/document-control.md#lifecycle-and-approval).

## Intended-use statement

El Chrono is a non-medical visual aid for showing the amount of ordinary
waiting time remaining. A caregiver configures a waiting duration, and a person
who benefits from a visual representation of that duration can view its progress
without seeing a clock face or time of day. The v1 product scope excludes
background music and in-app volume controls; timer operation and understanding
remaining time do not depend on audio or network access.

The timer is for ordinary waiting situations and is intended for general use.
People with cognitive or intellectual disabilities may use it, but the product
is not designed or intended to compensate for a disability or to address a
medical condition. It provides the same general waiting-time display regardless
of a person's diagnosis, disability, or health status; it does not assess or
adapt to any such status.

This intended-use boundary does not assert that the product is verified or
released. The issue #47 candidate implements a browser countdown, settings,
and About UI. Product-level timing, accessibility, offline, and interruption
behavior remain subject to verification; audio and in-app volume are outside
v1.

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
waiting duration. The planned duration range is 1 minute to 24 hours in
1-minute increments. Remaining-time meaning must stay consistent across themes
and platforms and must not rely on colour alone. The presentation must not
imply a clock face or time of day. These behaviours are requirements, not
verified capabilities of the current starter application.

V1 provides no background music, completion sounds, or in-app volume controls.
Any future audio scope is deferred and is not authorized by this record; it
requires separate requirements, provider/platform/privacy/licensing review,
and usability assessment. The timer must operate without internet access.
Offline operation remains a requirement, not a verified capability of the
current application.

## Foreseeable use and limitations

A person or caregiver could interpret the display as an exact deadline, a
guarantee that waiting has ended, or a signal to take another action. The
experience is limited to supporting understanding of a waiting period; it does
not establish what action should follow. The caregiver remains responsible for
choosing and explaining the duration and for determining what happens when the
wait ends.

The current requirements specify that opening Settings pauses a running timer;
closing resumes only its retained running intent, with remaining time or a new
full duration after a valid duration change. Manual pause and finished states
remain stopped; a duration change after finish prepares a paused timer. At zero
the timer stops in a distinct finished state; it does not restart automatically.
Pause, Resume and Restart are explicit actions. Interruption requires explicit
Restart, not Settings alone; lifecycle stop while Settings holds running intent
revokes automatic resume. No countdown recovery across process termination or
device restart is promised. Settings has no caregiver-protection gate. These
revised behaviors require product, accessibility, usability and risk review
and verification; prior evidence does not establish them.

Interruption detection must not imply timing continuity when it is uncertain.

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

M2-DOC-001 defines REQ-001 through REQ-010, with REQ-007 retired and not
reusable. M1-DOC-004 defines RISK-001 through RISK-006. The exact-revision
review and approval status for these records is recorded in their review PRs;
link applicable identifiers and source sections from the traceability matrix.

## Review evidence and limitations

- The product owner confirms @Entreco as the reviewer for the intended-use,
  product-boundary, and software-safety scopes for this candidate. Final
  acceptance must identify the exact PR head and findings disposition.
- Historical PR #9 acceptance metadata remains incomplete. This candidate
  does not reconstruct or claim missing historical acceptance; the current
  exact-revision review is independent.
- The EU and United States are selected for assessment in revision 0.3.
  Specific launch states, channels, and any non-EU European markets remain
  unspecified.
- Revisions 0.6 and 0.7 are candidates from closed issue #2. Revision 0.8
  updates the proposed duration range to 1 minute through 24 hours in
  1-minute increments; the corresponding sequential Hours/Minutes entry
  behavior is specified in M2-DOC-001. The EU/US qualification decision is
  recorded in M1-DOC-003 by @Entreco as qualified regulatory reviewer;
  approval evidence for this intended-use revision remains pending.
- @Entreco is assigned as regulatory qualification reviewer for M1-DOC-003.
  @Entreco is also assigned as competent software-safety reviewer for
  M1-DOC-005; that separate appointment does not constitute a software-safety
  review or approval of this intended-use record. A reviewer for any required
  software-safety scope on this record remains unassigned. An open PR makes
  the branch In review but does not waive required review gates.
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
| 0.8 | Update the proposed waiting duration range to 1 minute through 24 hours in 1-minute increments, consistent with the M2 product requirements candidate |
| 0.9 | Align the proposed requirement identifier prefix with REQ-NNN and direct trace links to the M2 requirements traceability matrix |
| 1.0 | Clarify v1 intended use and exclusions: no music, completion sound, or in-app volume controls; align Settings/completion/interruption scope with the M2 v1 decisions and preserve offline-verification limitations. |
| 1.1 | Align intended-use behavior with one-shot completion, Settings intent and explicit interruption restart; retain review gaps. |
