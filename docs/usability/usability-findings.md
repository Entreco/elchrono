# Usability review findings and evidence status

| Field | Value |
|---|---|
| Document ID | M2-DOC-006 |
| Revision | 0.8 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Product usability, accessibility, and safeguarding/privacy review: @Entreco, designated by the product owner on 2026-11-19; exact-revision acceptance pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#24](https://github.com/Entreco/elchrono/issues/24) |
| Review PR | Prior baselines: [PR #30](https://github.com/Entreco/elchrono/pull/30), [PR #32](https://github.com/Entreco/elchrono/pull/32), and [PR #41](https://github.com/Entreco/elchrono/pull/41); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); issue #67: [PR #68](https://github.com/Entreco/elchrono/pull/68); issue #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and evidence boundary

This record reports two usability-review sessions and distinguishes
participant feedback, facilitator recollection, retained screenshots, and
remaining evidence gaps. The sessions reviewed
[M2-DOC-004 revision 1.3](../design/accessible-screen-flows-and-theme-prototypes.md),
merged through [PR #29](https://github.com/Entreco/elchrono/pull/29). The
candidate requirement baseline for those sessions is
[M2-DOC-001 revision 0.3](../product/requirements.md); the current v1 decisions
are in revision 0.6 and were not part of those sessions, including D-06's
decision not to provide separate manual pause, resume, or reset controls.
The applicable proposed risks are RISK-001 through RISK-005 in
[M1-DOC-004 revision 0.6](../safety/risk-assessment.md).

## Session scope and evidence provenance

The two sessions took place on 2026-11-16. Each involved one person from the
intended user population; no caregiver session is reported. Both participants
independently explored the interactive prototype controls and viewed/selected
Clock, Battery, Hand fan, and Ring. The facilitator reports that voluntary
informed consent/permission and safeguarding arrangements were in place. This
is a retrospective facilitator report; no consent records or prior
safeguarding review evidence are included here.

The facilitator reports that no contemporaneous session notes or audio/video
recordings were made. Findings below were provided by the facilitator on
2026-11-17 from recollection. Two prototype-only screenshots from Session B
were retained; they contain no participant image or identifying information:

- [Clock clipping, cropped detail (756 × 507 pixels)](evidence/session-b-clock-crop.png)
- [Clock clipping, full prototype context (1718 × 1172 pixels)](evidence/session-b-clock-context.png)

The screenshots show the Clock prototype at a configured duration of 2 hours
0 minutes, Clean sans font, white background, green foreground, black text,
Show remaining enabled, six details sections, and Ready to start (the demo had
not been started). The lower remaining-time text is partly clipped. The
facilitator reports that the same clipping occurred in Session A, but that
participant did not notice it. Session B's screenshot is the retained direct
visual evidence; no screenshot from Session A is available.

The browser was reported as Google Chrome 157.0.8103.52 (arm64). Screenshot
pixel dimensions are recorded above; browser window/viewport dimensions and
zoom are unknown. No participant demographics, diagnoses, names, or other
identifiers are recorded.

## Caregiver availability and formative sufficiency

On 2026-11-17, the product owner reported that the caregiver expected to
participate was unavailable because of unexpected care responsibilities. The
caregiver had indicated that Participant B could make decisions on their
behalf. This is the reported arrangement relevant to caregiver participation;
no additional recruitment attempts are claimed. No personal details about the
care responsibilities are retained.

The caregiver's statement supports the owner's explanation for proceeding
with the available participants, but is not direct caregiver feedback,
evidence of legal representative authority, or verification of caregiver
workflows. Participant B's feedback remains classified as intended-user
feedback, not a caregiver session.

The product owner has elected not to arrange a replacement caregiver session
for this formative round and to continue M2 work with that limitation recorded.
The two sessions provide sufficient evidence for the **limited formative
purpose of identifying actionable issues and questions in the explored
visualization controls**:

- Both participants independently explored all four shapes and provided
  qualitative feedback about ease of use and available options.
- Session B identified clipped remaining-time text, supported by retained
  screenshots; the facilitator also observed it in Session A. This supplies a
  concrete design-correction target rather than only positive impressions.
- The feedback about optional time visibility, option load, and differing
  potential shape preferences informs further design work without selecting
  a universally preferred theme or asserting representative findings.

This rationale does not depend on treating two sessions as a representative
sample or setting a numerical validation threshold. El Chrono is intended for
non-medical ordinary waiting, not safety-critical timing; that bounded intended
use permits an exploratory design review, but does not mean there are no
usability or safety-related risks. The unresolved RISK-001 through RISK-005
controls, clipping finding, and process/accessibility gaps remain.

The decision supports continued design iteration within M2, not M2 completion,
release approval, or a claim that caregiver setup, protection, volume, audio,
or recovery behavior has been validated. G03 records the missing caregiver
perspective as an explicit limitation of this round. The milestone's remaining
review and completion gates still apply; any later need for caregiver evidence
must be assessed against the actual workflow and risk questions, not considered
waived by this formative disposition.

## Participant feedback and observations

These are qualitative reports from two sessions only. They are not prevalence
estimates or evidence that all intended users share a preference.

| ID | Session evidence | Requirement / design links | Applicable risks | Impact and disposition |
|---|---|---|---|---|
| M2-DOC-006-F01 | In Session B, the participant noticed that the Clock's remaining-time text was not fully visible and said it should be addressed. The screenshot confirms that the lower numeric remaining-time text was clipped while Show remaining was enabled. The facilitator also observed the same clipping in Session A; that participant did not notice it. | REQ-003, REQ-006; M2-DOC-004 revision 1.3, Clock visualization and Show remaining; Session B screenshots above | RISK-001, RISK-005 | The existing prototype CSS correction was checked in Chrome 157.0.8103.79 at 500 × 1000 px (mobile breakpoint) and 1440 × 1200 px (desktop). At both sizes, all six Clock label bounding boxes fit within the visualization stage with no horizontal page overflow. The product owner also reports the clipping is resolved. Close this specific prototype finding for the checked layouts; supported-layout configurations are not defined, so this is not a complete REQ-003/TP-REQ-003 pass or verification of the future application. |
| M2-DOC-006-F02 | Both participants independently explored the controls and selected all four shapes. Session A described the prototype as easy to use and liked it. Session B also described it as very easy to use. No specific task-success measure or timed task result was collected. | REQ-006; M2-DOC-004 revision 1.3 interactive demo | RISK-005 | Positive individual feedback; retain as qualitative feedback only. It does not establish usability for the broader population or validate untested flows. |
| M2-DOC-006-F03 | Session A was unsure whether Battery would be useful, while also saying it was good to have that option. No single shape was reported as preferred. Feedback relayed without a session-specific attribution was that preferences may depend on the person and that additional options might be useful for different needs. | REQ-006; M2-DOC-004 revision 1.3, available visualizations | RISK-005 | No theme is selected or removed. Keep theme choice open and consider the feedback in future design review; do not infer diagnoses, preference categories, or a fixed theme set. |
| M2-DOC-006-F04 | Session B understood that remaining time could be hidden when needed and appreciated that the prototype did not overload the user with options. | REQ-003, REQ-006; M2-DOC-004 revision 1.3, Show remaining and details controls | RISK-005 | Preserve as a participant observation, balanced against F01: optional hiding was understood, but when remaining time is shown it must remain fully visible. No broader preference claim is made. |

## Evidence gaps and limitations

| ID | Gap or limitation | Requirement / design links | Applicable risks | Disposition |
|---|---|---|---|---|
| M2-DOC-006-G01 | M2-DOC-005 was created after the 2026-11-16 sessions and did not govern them. No pre-session protocol or prior protocol approval evidence was supplied. The facilitator reports consent/permission and safeguarding arrangements were in place, but their scope and review evidence are not documented here. | M2-DOC-005; all session findings | RISK-001 through RISK-005 | Process/evidence gap remains open. Do not present these sessions as having followed M2-DOC-005. Product owner to document the actual arrangements and obtain appropriate review of the retrospective evidence before relying on it. |
| M2-DOC-006-G02 | No contemporaneous facilitator notes or recordings exist. Session details and feedback are based on facilitator recollection provided the following day; the Session B screenshots are the only retained session artifacts. | All session findings | RISK-005 | Limitation on detail and interpretation. Do not add unremembered prompts, responses, task outcomes, or participant attributes. |
| M2-DOC-006-G03 | Only two people from the intended user population participated. The expected caregiver was unavailable; their reported delegation to Participant B is not direct caregiver feedback. The small qualitative sample does not establish representative preferences or caregiver workflow usability. | REQ-001 through REQ-006, REQ-008 through REQ-010; M2-DOC-004 | RISK-001 through RISK-005 | Owner @Entreco elects to skip a replacement caregiver session for this formative round under the bounded rationale above. Retain the caregiver evidence gap; reassess against the revised v1 workflow, especially unprotected Settings, save-to-start behavior, and omission of separate manual timer controls. No milestone gate or risk-control verification is waived. |
| M2-DOC-006-G04 | The sessions explored the interactive controls and shapes, but no reported evidence establishes comprehension of sequential duration entry, Settings-open pause/save-to-start, absence of separate manual pause/resume/reset controls, open Settings access, interruption behavior, completion behavior, or About content. Music and volume are not v1 capabilities and were not tested. | REQ-001 through REQ-006, REQ-008 through REQ-010; M2-DOC-004 static flows and prototype limits | RISK-001 through RISK-005 | Unresolved; revised settings and lifecycle behaviors were not part of the sessions. Do not claim these areas were tested. |
| M2-DOC-006-G05 | No screen-reader, keyboard, touch, or other assistive-technology evaluation is reported. Viewport size and browser zoom for the clipped display are unknown. | REQ-003 through REQ-006; M2-DOC-004 revision 1.3 | RISK-005 | Accessibility and layout evidence remains incomplete. Investigate clipping across relevant viewport/layout conditions and evaluate applicable input methods separately; no conformance result is claimed. |
| M2-DOC-006-G06 | The 2026-11-16 sessions did not include issue #66's detail-0 state cues or the revised interruption presentation. No participant, caregiver, screen-reader, or risk-review evidence establishes that the text-free visual cues distinguish running, paused, interrupted/stale, and idle states. | REQ-002, REQ-003, REQ-013; M2-DOC-004 revision 2.0 | RISK-002, RISK-005 | New review gap. The visual cues and accessible descriptions are implementation/design candidates only. Obtain the required product, accessibility/usability, and risk review before claiming comprehension or effectiveness; no additional session is authorized by this record. |

RISK-001 through RISK-005 remain proposed and subject to review; these sessions
do not establish risk acceptance, control effectiveness, or risk reduction.
M2-DOC-003 is the authoritative requirements-to-risk-to-design/usability/test
matrix.

The owner-reported availability explanation and formative disposition above
replace the earlier assumption that no caregiver-arrangement information was
available. They do not resolve G01, G02, G04, G05, or G06.

## Follow-up and completion criteria

The specific prototype clipping in F01 is resolved for the two checked layouts
above. Define the supported layout matrix and verify the actual application
before claiming complete no-clipping coverage; the prototype check is not
product verification. Preserve F03's uncertainty and F04's positive
observation together; neither determines a final theme or option count.

Review the actual consent/safeguarding process and the retrospective evidence
limitations in G01/G02 with appropriately competent reviewers. Any additional
sessions must follow an approved protocol, including consent, safeguarding,
privacy, and data-handling arrangements, before they take place. Address G03
through G06 as appropriate to the reviewed usability scope and update
M2-DOC-003 with subsequent evidence. Do not close #24 until the issue's
acceptance criteria and review gates are met; these two sessions do not by
themselves establish completed validation.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record documentary evidence and explicit participant-review gaps for issue #24; make no claim of recruitment attempts, participant findings, or user validation. |
| 0.2 | Add facilitator-reported evidence from two 2026-11-16 sessions and anonymized Session B prototype screenshots; record the Clock clipping, participant feedback, retrospective-protocol gap, and remaining limitations without generalizing findings. |
| 0.3 | Record the owner-reported caregiver unavailability and delegation, the decision to skip a replacement session for this formative round, and the bounded sufficiency rationale for continued M2 design iteration; retain caregiver evidence and milestone review gaps. |
| 0.4 | Record the PR #32 prototype clipping correction with browser/layout verification pending; align the unresolved caregiver and untested-flow gaps with the revised v1 product scope without retroactively attributing feedback. |
| 0.5 | Record the owner-reported clipping resolution and the Chrome layout check at 500 × 1000 px and 1440 × 1200 px; close the prototype-specific finding for those layouts while retaining the undefined supported-layout and actual-product verification gaps. |
| 0.6 | Record that the current no-manual-controls decision was not included in the retrospective sessions; preserve the resulting usability evidence gaps. |
| 0.7 | Update current requirements and risk references and record the owner's reviewer designation; preserve the retrospective, caregiver, preference, and assistive-technology evidence gaps. |
| 0.8 | Record that the retrospective sessions did not evaluate issue #66's text-free detail-0 and interruption cues; keep comprehension, assistive-technology, and risk review explicitly pending. |
