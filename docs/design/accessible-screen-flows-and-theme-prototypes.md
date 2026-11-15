# Accessible screen flows and theme prototypes

| Field | Value |
|---|---|
| Document ID | M2-DOC-004 |
| Revision | 1.2 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Product design, accessibility, and usability reviewers: unassigned |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#23](https://github.com/Entreco/elchrono/issues/23) |
| Review PR | Prior baseline: [PR #28](https://github.com/Entreco/elchrono/pull/28) (revision 0.2); current revision pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This record proposes low-fidelity screen flows and three timer visualizations
for review. It is design evidence for the proposed ordinary-waiting use, not
application implementation, a usability finding, or approval of product
requirements. The current application remains a starter screen, not an
implemented timer.

An interactive, browser-based demonstration is available in the
[local demo](demo/README.md). It has one configurable countdown, defaulting to
two hours, and a single Shape selector: Disk, Battery, Hand fan, or Ring.
The former digital circle is merged into Disk. Changing shape preserves
the countdown, shared background/foreground/text colors, font, and text
visibility. Ring thickness appears only for Ring and is retained when changing
shape. Independent Show remaining and Show progress checkboxes control the
duration display and additional markers respectively. Progress adds 0–55
numerals outside Disk/Ring, eight battery divisions, or fixed fan separators.
The base fill remains visible. Font and text color controls appear when time
or circular numerals are visible, without
clearing their values when hidden. Each shape uses only the selected colors;
unselected colors are not added as decorative highlights or outlines. The
disk's optional 0–55 numerals use 36 px text outside its progress area, with
reserved space for the larger labels. Disk uses a radial remaining-time
treatment, Battery drains linearly, and the hand-fan
shape empties over 180 degrees toward a fixed left edge, like a disk sweep,
keeping its radius and separator positions fixed rather than folding them. The
optional disk numerals
are an exploratory client-feedback variant only; the product design remains
without clock-face numerals or markings. These are prototype controls only,
separate from the Kotlin application and not approved product requirements.
The prototype also permits duration selection from one minute through 24 hours
and hiding or showing its personalization panel; these controls do not
establish changed requirements.

The requirement IDs cited below are candidates from
[M2-DOC-001, revision 0.3](../product/requirements.md); they remain proposed
pending required review and approval. The risk IDs are proposed in
[M1-DOC-004, revision 0.4](../safety/risk-assessment.md); the related risk
controls are not verified. Intended-use and risk-assessment review gaps remain
open. This design does not establish medical-device qualification, legal
conformance, or certification.

## Design principles

- The timer visualization represents remaining duration, not a clock, time of
  day, deadline guarantee, or safety-critical timing system.
- Each visualization uses the same continuous remaining-time mapping: its
  complete form represents 100% of the configured duration remaining; its
  empty form represents zero. Static examples use the same illustrative 60%
  remaining.
- Progress has a non-colour cue: filled extent and remaining track are
  distinguishable by shape and position. State is also stated in text.
- The proposed theme presentation is visual-only. Accessible semantics expose
  a progress indicator named "Remaining waiting time" with a value from 0% to
  100% remaining, plus a separate textual timer state. Exact spoken wording
  and announcement cadence remain for accessibility/usability review; this
  record does not select an accessibility conformance baseline.
- No flashing, countdown animation, sound, or motion is proposed by these
  static drawings.
- The concepts do not establish user preference, a fixed number of themes, or
  which themes will be available in a release.

## Screen flows

### Main screen and states

![Three illustrative Main screen states: running, paused, and interrupted. The same progress shape appears in each, with text labels distinguishing state.](main-states.svg)

The running and paused examples retain the same illustrative progress shape.
The interrupted example retains the shape but explicitly warns that progress
may be out of date; it must not imply timing continuity when that is unknown.
Volume remains an accessible Main-screen control in every state shown. No
pause, resume, reset, or interruption-recovery action is drawn because those
behaviors are unresolved.

| Main element | Proposed presentation and semantics | Requirement / risk links |
|---|---|---|
| Remaining progress | A theme's progress shape; accessible role: progress indicator; label: "Remaining waiting time"; value: percent of the configured duration remaining, 0-100. | REQ-003; RISK-001, RISK-005 |
| Running state | Text label "Running" separate from progress semantics. | REQ-003; RISK-001, RISK-005 |
| Paused state | Text label "Paused" separate from progress semantics; no transition or control is selected. | REQ-003; RISK-001, RISK-005 |
| Interrupted state | Text label "Timer interrupted" and "Progress may be out of date"; no recovery action or continuity claim is selected. | REQ-003; RISK-002, RISK-005 |
| Volume | A large, labelled Main-screen adjustment target; accessible name "Volume". The illustrated slider position is not a default; limits and range are not specified. | REQ-004, REQ-005; RISK-003, RISK-004 |
| Settings access | Caregiver-only access is required, but the access route is an abstract gate in this flow. No PIN, gesture, or other protection mechanism is selected. | REQ-005; RISK-004 |

At zero, the candidate REQ-010 behavior is an immediate return to the
configured duration and stopping optional music. There is no completed screen
or completed-state message. The proposed behavior remains subject to review and
approval; no transition timing or audio-start behavior is specified here.

### Settings screen

![Settings prototype with Hours and Minutes fields, visualization choices, conditional appearance settings, and optional music.](settings-about.svg)

The duration entry example follows the candidate sequential-digit rule:
`205` displays as `02:05`. The configured duration is a whole-minute value
from 00:01 through 24:00. The examples describe field content, not a
verification result.

Requirements: REQ-001 (duration entry), REQ-002 (caregiver start), REQ-004
(screen content), REQ-005 (caregiver-only access), REQ-006 (theme selection),
and REQ-008 (optional music).

The caregiver flow is abstract: caregiver-only access gate (mechanism pending)
then duration, visualization, optional appearance settings, and optional-music
configuration. The proposed initial visualization is a Time Timer-inspired
remaining-time disk. This adapts the supplied dial-like reference to the
no-clock requirement: it has no clock face, numerals, tick marks, or hand. It
is a design proposal, not a selected or approved product default. A caregiver
starts the countdown after a valid duration is configured, as required by
REQ-002; the start control's placement is not selected.

The appearance controls are conditional on the selected visualization. Offer
a font choice only when it displays text, and a color choice only when it uses
color. These are proposed caregiver settings, scoped to the selected
visualization; available choices, defaults, saving/persistence, and any
reset-to-default action remain undecided. Any selected font or color must
preserve readable text, non-colour progress/state cues, and the same remaining
time semantics. Contrast and legibility need accessibility and usability
review; no conformance baseline or user preference is claimed.

The optional-music area identifies a product capability without choosing a
provider, connection screen, playback control, default, start behavior, or
completion sound. Those details require later product, provider, platform, and
usability review. If a later integration presents an audio failure, it must
not interrupt timer operation or imply that playback succeeded; this record
does not select that feedback UI.

### About screen

The About portion of the prototype reserves space for the company logo,
version/build details, support information, privacy policy, relevant licences,
intended use, and limitations, as required by REQ-004. Unknown content is
explicitly marked for its owner to supply; no logo asset, contact details,
policy, licence list, or build value is fabricated here.

Requirements: REQ-004 (About content) and REQ-009 (non-medical claims boundary).

The proposed intended-use and limitation copy is restricted to ordinary
waiting-time support and says the app is not a clock or for medical or
safety-critical timing. It does not claim a clinical benefit or guarantee that
a wait has ended. The About entry point and navigation placement remain
unselected.

## Timer visualizations

![Three proposed timer visualizations: a Time Timer-inspired remaining disk as the proposed default, a digital duration countdown, and a numeric circular progress indicator.](timer-visualizations.svg)

| Visualization | Visual treatment | Remaining-time mapping | Requirement / risk links |
|---|---|---|---|
| Time Timer-inspired remaining disk (proposed default) | A filled disk sector shrinks as time elapses. No dial, face, numeral, tick mark, or hand is shown; the nearby "Remaining" label makes the duration meaning explicit. | Full disk means 100% remains; empty disk means zero. | REQ-003, REQ-006; RISK-001, RISK-005 |
| Digital duration countdown | Prominent remaining duration (illustrated as `02:05`) with a textual "Remaining" label; it is not time of day. Font and, if used, text color are configurable proposals. | The duration value counts down; optional progress indication follows the same remaining-time fraction. Units and display precision require review. | REQ-003, REQ-006; RISK-001, RISK-005 |
| Numeric circular progress | Circular progress ring with a central remaining-duration value (illustrated as `45 min`); no dial markings or hands. | Ring fill and numeric duration both represent time remaining; their mapping must stay consistent. | REQ-003, REQ-006; RISK-001, RISK-005 |

The remaining disk is the proposed first/default visualization; digital
countdown and numeric circular progress are alternatives inspired by the
references supplied for this review. All three are unselected design proposals
pending review, not a prescribed final theme count or a claim that any is
preferred or usable by a representative patient. Their shared mapping is a
design constraint; it has not been implemented or verified across durations,
screen sizes, platforms, or assistive technology. The attached references
inform the concepts but are not copied into the repository.

The local demo makes these concepts interactive without establishing
requirements or product behavior. Its configurable countdown runs from a user
action, uses the browser monotonic clock, and immediately resets to the chosen
duration at completion without a completed state. Shape switching does not reset
countdown progress. It does not implement pause, restart controls, persistence,
audio, or interruption recovery. Styling previews are shared across shapes for
the current page session only; nothing is saved across reloads.

## Open decisions and evidence gaps

The designs intentionally leave these candidate requirement decisions and
appearance questions unresolved:

| Decision | Design boundary |
|---|---|
| D-01: caregiver protection mechanism | Caregiver-only Settings is shown as a conceptual gate only; its mechanism and placement are not designed. |
| D-02: patient-accessible and caregiver-configured volume limits | A labelled volume adjustment target is identified; limits, range, and default remain open. |
| D-03: music start behavior and completion sounds | Music remains optional; provider, connection, start behavior, and any completion sound remain open. |
| D-04: manual pause and reset behavior | Required states are illustrated; controls and transitions are not selected. |
| D-05: interruption detection and recovery | The interrupted presentation avoids implying continuity; detection, persistence, and recovery are not selected. |
| Visualization appearance | Font and color are proposed as conditional per-visualization settings; option lists, defaults, and persistence remain undecided and require accessibility/usability review. |

Required product-design, accessibility, usability, and specialist review
appointments are unassigned. No caregiver or representative-patient review,
feedback, finding, disposition, requirement approval, or design approval is
claimed. The static SVGs remain non-interactive; the browser demo provides
interactive view switching but has not been tested for screen readers,
keyboard, touch, or any target platform. Record actual reviewer scopes,
feedback, findings, and dispositions in the review PR; keep unresolved findings
and limitations explicit.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish candidate screen flows and three low-fidelity theme concepts for issue #23; preserve open decisions and review gaps. |
| 0.2 | Propose a Time Timer-inspired remaining disk as the first/default visualization and digital and numeric-ring alternatives; add conditional per-visualization font/color settings while preserving no-clock and open-decision constraints. |
| 0.3 | Add a local interactive two-hour browser demo of the three visualizations and per-view appearance previews; keep it separate from the application and product requirements. |
| 0.4 | Refine the demo with a reversed disk sweep, visualization-colored digital background, and adjustable numeric-ring thickness; keep these prototype-only and preserve the no-clock boundary. |
| 0.5 | Add prototype-only background-color and number-visibility options, a draining digital fill, and optional clock-position disk numerals for feedback; retain the product no-clock boundary. |
| 0.6 | Add configurable demo duration, a hide/show personalization control, and a ring-thickness range from hairline to solid fill. |
| 0.7 | Apply the selected background to the disk's center and depleted area; place digital time inside its shape; add battery/circle/half-circle shapes and four background presets. |
| 0.8 | Separate background, foreground, and text color controls and limit each visualization to those three selected colors. |
| 0.9 | Place optional disk numerals outside the progress area; make the digital circle drain radially and the hand fan fold closed with elapsed time. |
| 1.0 | Triple optional disk numeral size to 36 px with surrounding space; replace horizontal fan scaling with a fixed-radius angular opening from 180 degrees to zero. |
| 1.1 | Unify the demo into one shape selector and shared settings; merge Circle into Disk; retain hidden text preferences and ring thickness across shape changes. |
| 1.2 | Separate remaining-time visibility from shape-specific progress markers; add ring numerals and battery divisions; replace fan folding with a fixed-edge sweep and clipped stationary separators. |
