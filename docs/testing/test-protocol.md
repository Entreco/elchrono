# Product test protocol

| Field | Value |
|---|---|
| Document ID | M2-DOC-002 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Test and product requirements reviewers: unassigned |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#25](https://github.com/Entreco/elchrono/issues/25) |
| Review PR | Pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This protocol contains planned test procedures and acceptance criteria for
[M2-DOC-001, Product requirements](../product/requirements.md). Requirement
statements remain in that register; requirement-to-source, risk, design,
usability, and test relationships and verification evidence belong in the
[requirements traceability matrix](../safety/requirements-traceability-matrix.md).

These are planned criteria, not completed verification. Run tests only against
reviewed requirements and designs, record the exact build/platform and results,
and report failures, limitations, and unavailable test conditions. Use an
injected controllable time source for timer tests; do not wait real minutes or
hours. No accessibility conformance baseline is selected by this protocol.

## Test cases

| Test ID | Requirement | Procedure | Acceptance criteria |
|---|---|---|---|
| TP-REQ-001 | [REQ-001](../product/requirements.md#requirement-identification) | Enter representative digit sequences, test the allowed duration boundaries, exercise invalid values, and enumerate all whole-minute durations with a controllable input/test harness. | `2`, `20`, `205`, and `2400` display as `00:02`, `00:20`, `02:05`, and `24:00`. Every duration from `00:01` through `24:00` is representable. `00:00`, minute values above `59`, and values above `24:00` cannot be configured as valid durations. |
| TP-REQ-002 | [REQ-002](../product/requirements.md#requirement-identification) | Configure a valid duration as a caregiver and activate Start; advance the injected time source. | The timer enters the running state from the configured duration and its remaining-time presentation updates as time advances. |
| TP-REQ-003 | [REQ-003](../product/requirements.md#requirement-identification) | Review each available visualization's specification and exercise its running, paused, and interrupted presentations at defined time points. | Each visualization specifies numeric remaining time, visual progress, or both, and maps progress consistently to remaining time. Remaining-time information is visible in each required state. No clock face or time of day is presented, and state meaning does not rely on colour alone. Numeric display, if present, follows its declared units and rounding. |
| TP-REQ-004 | [REQ-004](../product/requirements.md#requirement-identification) | Inspect the Main, Settings, and About designs and implementation against the required content. | Main includes the selected timer visualization and accessible volume control. Settings includes caregiver controls for duration, theme, and optional music. About includes company logo, version/build details, support information, privacy policy, relevant licences, intended use, and limitations. Missing content sources are recorded rather than fabricated. |
| TP-REQ-005 | [REQ-005](../product/requirements.md#requirement-identification) | Exercise caregiver and patient navigation paths, including attempts by a patient to open Settings; adjust volume on Main as a patient. | Caregiver can access Settings and its controls. Patient cannot access Settings through available navigation paths, but can change volume from Main without entering Settings. Test the protection mechanism selected by reviewed design. |
| TP-REQ-006 | [REQ-006](../product/requirements.md#requirement-identification) | For each theme shown as available, select it as a caregiver and inspect the timer. | Each available theme can be selected and becomes the selected timer theme. Do not assert theme preference without usability evidence. |
| TP-REQ-008 | [REQ-008](../product/requirements.md#requirement-identification) | Run the timer with music disabled and with network unavailable; simulate audio unavailable/failure and playback active at timer completion. | Timer operation and remaining-time presentation continue without music or internet. Audio failure does not interrupt the timer and is not shown as successful playback. Optional music stops when the countdown reaches zero. |
| TP-REQ-009 | [REQ-009](../product/requirements.md#requirement-identification) | Review application screens, About content, and external product claims against intended-use exclusions. | No content presents the app as a clock, guarantees a deadline, makes medical claims, or presents it for safety-critical timing. Conflicts in intended purpose, functionality, claims, or markets are escalated before affected work continues. |
| TP-REQ-010 | [REQ-010](../product/requirements.md#requirement-identification) | With music active and a controllable time source, advance the countdown to zero. | The timer immediately resets to the configured duration, optional music stops, and no completed state is displayed. Completion is handled once and remaining time does not become negative. This criterion remains subject to review and approval of the proposed behavior. |

## Execution record

No test execution or result is recorded in this candidate protocol. Record actual
execution evidence in the requirements traceability matrix, including protocol
revision, test/build revision, platform, result, reviewer, evidence link, and
limitations. Keep failures and blocked tests visible; do not report planned
criteria as passed.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish planned test cases and acceptance criteria separately from requirement statements; no execution evidence claimed. |
