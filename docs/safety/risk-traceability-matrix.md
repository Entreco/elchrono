# Risk traceability matrix

| Field | Value |
|---|---|
| Document ID | M1-DOC-009 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software-safety reviewer: unassigned; blocking |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#4](https://github.com/Entreco/elchrono/issues/4) |
| Review PR | [PR #14](https://github.com/Entreco/elchrono/pull/14) |

Lifecycle status is derived from the branch and PR context; see
[document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and use

This matrix links the scenarios and proposed controls in
[M1-DOC-004 revision 0.1](risk-assessment.md#risk-entries) to intended-use
sources, assessment assumptions, and planned verification. Each risk and
follow-up is owned by the product owner, @Entreco. No approved requirement IDs
or verification plan exist yet; add actual IDs and links when established.
These entries do not establish that controls are implemented, verified, or
effective, or that residual risks are acceptable.

The source is [M1-DOC-002 revision 0.6](../product/intended-use.md); its
exact-revision approval evidence remains unresolved. Test results are not
available for the proposed controls. After relevant verification is performed,
update the corresponding row with each relevant control ID, the actual test or
review, result, tested revision/platform, and a durable evidence link. Record
failures, limitations, and pending control coverage as well as passes; do not
treat unrelated app tests as evidence of a risk control. A qualified review
of the combined controls is needed before recording a post-control score in
M1-DOC-004; a passing test does not itself establish a reduction or
acceptability. Review findings and human acceptance remain in the PR under
[document controls](../governance/document-control.md).

## Matrix

Each risk ID links to the assessment table; the
[control tables](risk-assessment.md#risk-controls) identify the proposed
controls and their intended effects. All verification below is **planned**,
not performed; evidence and results are pending for every control.

| Risk and proposed controls | Intended-use trace | Assessment basis and assumptions | Planned verification | Test/review evidence and result |
|---|---|---|---|---|
| [RISK-001](risk-assessment.md#risk-entries) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio); [limitations](../product/intended-use.md#foreseeable-use-and-limitations) | Ordinary waiting, consistent remaining-time semantics, and durations from 5 minutes to 24 hours in 5-minute increments. Timer behavior is not implemented or verified; duration boundaries, pause/resume, and completion need requirements. Consequence depends on context and likelihood lacks operational evidence. | Deterministic tests with an injected time source for all allowed durations including boundaries, invalid configuration, pause/resume, completion boundaries, wall-clock changes, and consistent visual semantics. | Pending; no control verification or result. |
| [RISK-002](risk-assessment.md#risk-entries) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio); [limitations](../product/intended-use.md#foreseeable-use-and-limitations) | Platform lifecycle and recovery are unimplemented and untested. Interruption recovery is unresolved; a monotonic timestamp alone does not provide persistence across process or device restart. Whether recovery resumes, pauses, or restarts requires risk/UX review. | Controlled tests of backgrounding, sleep, process termination, restart, and wall-clock changes on each supported platform, using approved recovery behavior and a controllable time source. | Pending; no control verification or result. |
| [RISK-003](risk-assessment.md#risk-entries) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | Music is optional and unnecessary for timer operation or understanding. Audio failure must not interrupt the timer or silently appear successful. Volume limits and completion sounds are undecided; this record authorizes no sound or default. Audio behavior and user response have not been evaluated. | Verify timer operation with audio disabled and offline, failure reporting, volume behavior, and approved sound behavior on supported platforms; review sensory impact with users. | Pending; no control verification or result. |
| [RISK-004](risk-assessment.md#risk-entries) | [Users and roles](../product/intended-use.md#users-population-and-roles); [visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | Patients are intended to adjust volume without accessing caregiver settings. Protection and volume limits are unspecified; authentication, locking, and supervision are not chosen. Access controls and paths are unimplemented and unevaluated. | Review and test patient and caregiver navigation, including attempts to reach or alter protected settings, and accessibility of the approved volume control. | Pending; no control verification or result. |
| [RISK-005](risk-assessment.md#risk-entries) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio); [limitations](../product/intended-use.md#foreseeable-use-and-limitations) | Product requirements call for predictable layouts, contrast, large controls, accessible labels, and no color-only time semantics. Theme preferences and comprehension cannot be assumed; no representative usability findings exist. | Inspect contrast and accessibility semantics; conduct appropriate usability review of remaining-time meaning and state distinctions across themes and supported inputs. | Pending; no control verification, user findings, or result. |
| [RISK-006](risk-assessment.md#risk-entries) | [Limitations](../product/intended-use.md#foreseeable-use-and-limitations); [exclusions and claims](../product/intended-use.md#exclusions-and-claims-boundary) | Safety-critical timing and authoritative deadline reliance are excluded. Serious harm is plausible if misused, not a supported use; its likelihood is unmeasured and requires reviewer confirmation. Actual use and public claims have not been observed. | Review requirements, user-facing content, and product claims against intended-use exclusions before release; assess changes against the [qualification record](../regulatory/qualification-and-classification.md) and this risk. | Pending; no control verification or result. |

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish risk-to-source and planned-verification traceability; record missing evidence without claiming test results |
