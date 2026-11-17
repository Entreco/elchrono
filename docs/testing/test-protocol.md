# Product test protocol

| Field | Value |
|---|---|
| Document ID | M2-DOC-002 |
| Revision | 0.5 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Test and product requirements reviewers: unassigned |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#25](https://github.com/Entreco/elchrono/issues/25) |
| Review PR | Prior baselines: [PR #27](https://github.com/Entreco/elchrono/pull/27), [PR #31](https://github.com/Entreco/elchrono/pull/31), and [PR #32](https://github.com/Entreco/elchrono/pull/32); revision 0.5: [PR #41](https://github.com/Entreco/elchrono/pull/41) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This protocol contains planned test procedures and acceptance criteria for
[M2-DOC-001, revision 0.5, Product requirements](../product/requirements.md).
The exact source revisions and requirement-to-source, risk, design, usability,
and test relationships are identified in the
[requirements traceability matrix](../safety/requirements-traceability-matrix.md).
Requirement statements remain in the requirements register.

These are planned criteria, not completed verification. Run tests only against
reviewed requirements and designs, record the exact build/platform and results,
and report failures, limitations, and unavailable test conditions. Use an
injected controllable time source for timer tests; do not wait real minutes or
hours. No accessibility conformance baseline is selected by this protocol.

## Test cases

| Test ID | Requirement | Procedure | Acceptance criteria |
|---|---|---|---|
| TP-REQ-001 | [REQ-001](../product/requirements.md#requirement-identification) | Enter representative digit sequences, test the allowed duration boundaries, exercise invalid values, and enumerate all whole-minute durations with a controllable input/test harness. | `2`, `20`, `205`, and `2400` display as `00:02`, `00:20`, `02:05`, and `24:00`. Every duration from `00:01` through `24:00` is representable. `00:00`, minute values above `59`, and values above `24:00` cannot be configured as valid durations. |
| TP-REQ-002 | [REQ-002](../product/requirements.md#requirement-identification) | Inspect Main and Settings for manual timer controls. Save valid settings and advance the injected time source. While running, open Settings and record remaining time; keep Settings open while advancing time, then save valid settings. | No separate manual pause, resume, or reset controls are available in v1. Saving starts the countdown from the full saved duration. Opening Settings pauses it (remaining time does not change while Settings is open). Saving again starts a new countdown from the full saved duration, not the previously remaining time. |
| TP-REQ-003 | [REQ-003](../product/requirements.md#requirement-identification) | Review each available visualization's specification and exercise its running, paused, and interrupted presentations at defined time points and each supported layout configuration in scope. | Each visualization specifies numeric remaining time, visual progress, or both, and maps progress consistently to remaining time. Remaining-time information is fully visible and not clipped or obscured in each required state and supported layout configuration. No clock face or time of day is presented, and state meaning does not rely on colour alone. Numeric display, if present, follows its declared units and rounding. |
| TP-REQ-004 | [REQ-004](../product/requirements.md#requirement-identification) | Inspect Main, Settings, and About against their required v1 content in each supported layout configuration in scope. Confirm music and volume controls are absent in v1. | Main includes the selected timer visualization. Settings includes duration and theme controls, with no v1 music or volume controls. About includes company logo, version/build details, support information, privacy policy, relevant licences, intended use, and limitations. All required content is fully visible and not clipped or obscured. Missing content sources are recorded rather than fabricated. |
| TP-REQ-005 | [REQ-005](../product/requirements.md#requirement-identification) | Open Settings without a gate as a patient and change/save duration or theme. | Settings is not protected by a caregiver gate in v1 and permits the access specified by the product decision. The test verifies this behavior; it does not establish that the resulting risk is acceptable or controlled. |
| TP-REQ-006 | [REQ-006](../product/requirements.md#requirement-identification) | For each theme shown as available, select it as a caregiver and inspect the timer. | Each available theme can be selected and becomes the selected timer theme. Do not assert theme preference without usability evidence. |
| TP-REQ-008 | [REQ-008](../product/requirements.md#requirement-identification) | For v1, run the timer with network unavailable. Defer audio-failure and playback checks until a v2 music integration is approved and implemented. | Timer operation and remaining-time presentation do not depend on network connectivity. No v1 music integration or playback-failure behavior is claimed. The v2 audio criteria require review before that scope begins. |
| TP-REQ-009 | [REQ-009](../product/requirements.md#requirement-identification) | Review application screens, About content, and external product claims against intended-use exclusions. | No content presents the app as a clock, guarantees a deadline, makes medical claims, or presents it for safety-critical timing. Conflicts in intended purpose, functionality, claims, or markets are escalated before affected work continues. |
| TP-REQ-010 | [REQ-010](../product/requirements.md#requirement-identification) | With a controllable time source and no v1 audio, advance the countdown to zero. | The timer immediately resets to the configured duration, displays no completed state, handles completion once, and does not become negative. Stopping optional music is deferred to v2 and is not a v1 test result. |

## Execution record

No product test execution or result is recorded in this candidate protocol. A
prototype-only layout check for F01 was run in Chrome 157.0.8103.79 at 500 ×
1000 px and 1440 × 1200 px; see M2-DOC-006. It does not verify the application
or establish complete coverage because supported layout configurations are
undefined. Record actual product-test evidence in the requirements traceability matrix, including protocol
revision, test/build revision, platform, result, reviewer, evidence link, and
limitations. If the supported layout configurations needed to evaluate content
visibility are not defined or available, record the affected test as blocked
rather than passed. Keep failures and blocked tests visible; do not report
planned criteria as passed. The clipping reported in M2-DOC-006 is resolved in the prototype for the two
checked layouts; the complete product no-clipping criterion remains unverified.
The music/audio tests are deferred until
v2, not passed or waived.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish planned test cases and acceptance criteria separately from requirement statements; no execution evidence claimed. |
| 0.2 | Identify the requirements baseline revision and add explicit no-clipping acceptance criteria for required remaining-time and screen content; retain unrun and blocked result rules. |
| 0.3 | Align procedures and criteria with v1 decisions for open Settings, save-to-start pause behavior, absent volume/music controls, deferred v2 audio, and no v1 interruption recovery; record the clipping code correction without claiming layout verification. |
| 0.4 | Record the limited F01 prototype layout check separately from product test execution; retain blocked status for undefined supported layouts and unverified application behavior. |
| 0.5 | Align TP-REQ-002 with the explicit v1 decision to omit separate manual pause, resume, and reset controls. |
