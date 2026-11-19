# Product requirements

| Field | Value |
|---|---|
| Document ID | M2-DOC-001 |
| Revision | 0.6 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Product requirements and accessibility: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#22](https://github.com/Entreco/elchrono/issues/22) |
| Review PR | Prior baseline: [PR #32](https://github.com/Entreco/elchrono/pull/32); prior review: [PR #41](https://github.com/Entreco/elchrono/pull/41); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This is the candidate requirements register for El Chrono's proposed
non-medical visual support for ordinary waiting. It records the product
requirements that guide design and implementation. Acceptance criteria and
verification procedures belong in the test protocol; requirement, risk,
design, usability, and test relationships belong in the traceability matrix.
The issue #47 candidate implements a browser MVP against this proposed
requirements baseline. Product-level verification, usability, accessibility,
offline behavior, and platform support are not established by its builds or
domain tests.

The [intended-use record, M1-DOC-002 revision 1.0](intended-use.md) is a
candidate whose review/approval gaps remain open. This register does not
approve that source record, resolve its gaps, expand the intended use, or claim
medical-device qualification, legal conformance, or certification.
Requirement-to-source and requirement-to-risk links are maintained in the
requirements traceability matrix. Resolve prerequisite review and
applicability gates before relying on this register for affected work or
closing issue #22.

## Requirement identification

Requirement IDs use the form `REQ-NNN`. Earlier revisions used the
`ELC-REQ-NNN` prefix; this revision drops `ELC` while retaining the numeric
suffixes. IDs are stable across revisions and retired IDs are not reused.
REQ-007 (previously ELC-REQ-007) was retired before approval. The requirements
in this revision incorporate the product decisions recorded below; their
exact-revision review and acceptance are recorded in the current review PR.

| ID | Requirement |
|---|---|
| REQ-001 | A caregiver shall be able to configure every whole-minute waiting duration from 00:01 through 24:00 using two numeric fields, Hours and Minutes, populated by sequential digits. The first digit enters the minute ones place, the second the minute tens place, and each subsequent digit shifts the existing digits left, moving them into the hour field. |
| REQ-002 | Saving valid timer settings shall start the countdown from the saved duration. Opening Settings while a countdown is running shall pause it; saving valid Settings shall start a new countdown from the full saved duration. In v1, no separate manual pause, resume, or reset controls shall be provided; the Settings-open pause and valid-save start behavior are the only selected timer controls. |
| REQ-003 | A patient shall be able to see the timer's remaining-time presentation while it is running, paused, or interrupted. Each available visualization shall specify whether it shows numeric remaining time, visual progress, or both; no visualization shall show a clock face or time of day. |
| REQ-004 | The product shall provide Main, Settings, and About screens. Main shall contain the selected timer visualization; Settings shall provide controls for duration and theme; About shall contain the company logo, version/build details, support information, privacy policy, relevant licences, intended use, and limitations. Music integration and in-app volume controls are excluded from v1. |
| REQ-005 | In v1, Settings shall not require a caregiver-protection mechanism. |
| REQ-006 | A caregiver shall be able to select one of the four v1 themes available in Settings: Clock, Battery, Fan, and Ring. |
| REQ-008 | Timer operation and remaining-time presentation shall not depend on internet connectivity. Music integration and associated playback-failure handling are deferred to v2 and are not v1 capabilities. |
| REQ-009 | Product screens and user-facing claims shall describe ordinary waiting-time support only; they shall not present El Chrono as a clock, deadline guarantee, medical function, or safety-critical timer. |
| REQ-010 | When the countdown reaches zero, the timer shall reset immediately to the configured duration and shall not display a completed timer state. If optional music is introduced in v2, playback shall stop at zero. |
| REQ-011 | Android TV and Apple TV are primary v1 product targets. Their user-facing flows shall be operable through the target platform's remote/controller focus model, with a visible focus indicator, predictable navigation, readable content at ordinary viewing distance, and no interaction that relies on colour alone. Platform support is not claimed until the target is configured and the approved platform verification is passed. |

## Product decisions and scope

The product-owner decisions below define the v1 scope. D-01 through D-06 were
provided on 2026-11-17. On 2026-11-19, the product owner approved the four
demo visualizations for v1 and designated Android TV and Apple TV as primary
product targets, with Wear OS and watchOS outside v1. These decisions do not
establish representative-user preference, platform support, or verification.
Reassess affected risks and requirements before v2 or any change to intended
use.

| ID | Decision | Current disposition |
|---|---|---|
| D-01 | Caregiver protection mechanism | Product owner accepts the risk of unprotected Settings for v1; Settings may be accessed by patients. No control or risk reduction is claimed. RISK-004 disposition is documented in the risk assessment. |
| D-02 | Volume controls and limits | No in-app volume controls are included in v1. Reassess if audio is introduced in a later version; this decision does not promise volume controls in v2. |
| D-03 | Music start behavior and completion sounds | Music integration is deferred to v2; no music start behavior or in-app playback is needed in v1. No completion sound is included in v1; v2 audio behavior requires separate review and requirements. |
| D-04 | Timer start, Settings opening, and saving | Saving valid Settings starts a countdown from the full saved duration. Opening Settings while running pauses the countdown; saving starts a new countdown from the full saved duration rather than resuming the prior remaining time. |
| D-05 | Interruption detection and recovery | No interruption recovery behavior is provided or promised in v1. If timing continuity is uncertain, the product must not imply that progress is current; the usability/design limitation and RISK-002 remain subject to review. |
| D-06 | Manual pause, resume, and reset controls | No separate manual pause, resume, or reset controls are included in v1. Opening Settings while running is the selected pause behavior; saving valid Settings is the only selected start/restart action and starts from the full saved duration. |
| D-07 | Primary platforms and v1 exclusions | Android TV and Apple TV are the primary product targets. Wear OS and watchOS are outside v1 scope. The browser remains the M5 implementation/verification target; its completion does not establish TV support. TV support requires separate target configuration, packaging, remote/focus, viewing-distance, lifecycle, accessibility, and usability verification. |
| D-08 | V1 visualization set | The v1 theme choices are Clock, Battery, Fan, and Ring, as shown in the approved demo. Clock is the initial selection, matching the demo; this default is not evidence of user preference. The visual mapping and use of each theme still require application verification and appropriate usability/accessibility review. |

Requirement REQ-007 was retired before approval at the product owner's
direction; it must not be reused. No WCAG conformance baseline is selected by
this register. This does not remove applicable accessibility laws or the
project's general accessibility and usability expectations.

The test protocol, not this register, defines acceptance criteria and planned
verification. The requirements traceability matrix maintained under
[issue #25](https://github.com/Entreco/elchrono/issues/25) is the authoritative
record linking requirements to intended use, risks, design/usability evidence,
and test cases/results. No implementation, verification result,
representative-user finding, or requirement approval is claimed by this
candidate.

Legal accessibility applicability remains unresolved in
[M1-DOC-006](../governance/applicability-matrix.md). This register selects no
accessibility standard or conformance baseline. Accessibility and usability
review under the project's general requirements remains necessary before
approval and implementation.

Other evidence gaps include exact-revision approval of the intended-use record,
the required review of the risk assessment, appointment of competent
requirements/accessibility reviewers, and the review/verification needed for
these product decisions. In particular, the product owner's D-01 disposition
does not replace qualified review or formally determine RISK-004
acceptability. Keep these gaps visible; issue closure or document lifecycle
does not establish that they were resolved.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish the initial proposed requirements and explicit open decisions for issue #22; define the ELC-REQ-NNN identifier scheme and record initial accessibility criteria as a voluntary project baseline without claiming legal applicability or conformance. |
| 0.2 | Make duration entry whole-minute and digit-based; clarify caregiver start, patient visibility, Settings access, and theme selection; propose immediate timer reset and music stop at completion; retire ELC-REQ-007 and remove the WCAG baseline at the product owner's direction. |
| 0.3 | Use REQ-NNN identifiers and keep the requirements register to requirement statements; assign acceptance criteria to the test protocol and requirement-risk-test links to the traceability matrix. |
| 0.4 | Record the v1 product decisions for unprotected Settings, no volume controls, v2 music deferral, save-to-start/settings-open pause behavior, and no interruption recovery; retain competent review and verification gaps. |
| 0.5 | Resolve the v1 manual pause/resume/reset decision: no separate controls; Settings-open pauses and valid Save starts from the full saved duration. |
| 0.6 | Add the owner-approved Clock, Battery, Fan, and Ring v1 theme set, define Clock as the demo-matching initial selection, designate Android TV and Apple TV as primary targets and Wear/watch as outside v1, and retain separate verification/support gates. |
