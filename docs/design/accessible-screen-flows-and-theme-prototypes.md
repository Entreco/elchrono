# Accessible screen flows and theme prototypes

Settings appearance review: [PR #76](https://github.com/Entreco/elchrono/pull/76).

Desktop keyboard changes: [PR #73](https://github.com/Entreco/elchrono/pull/73).

| Field | Value |
|---|---|
| Document ID | M2-DOC-004 |
| Revision | 3.3 |
| Current change review | No PR opened yet; human acceptance and required specialist review pending |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Product design, accessibility, and usability: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#23](https://github.com/Entreco/elchrono/issues/23) |
| Review PR | Prior baselines: [PR #28](https://github.com/Entreco/elchrono/pull/28) (revision 0.2), [PR #29](https://github.com/Entreco/elchrono/pull/29) (revision 1.3), and [PR #32](https://github.com/Entreco/elchrono/pull/32); revision 1.6: [PR #41](https://github.com/Entreco/elchrono/pull/41); #47 update: [PR #59](https://github.com/Entreco/elchrono/pull/59); #67: [PR #68](https://github.com/Entreco/elchrono/pull/68); #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); Settings foreground: [PR #72](https://github.com/Entreco/elchrono/pull/72); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This record defines screen flows and the four timer visualizations approved by
the product owner for the v1 theme selector: Clock, Battery, Fan, and Ring.
The owner's decision on 2026-11-19 approves the visualizations as presented in
the local demo and selects Clock as the demo-matching initial theme; this is
not a claim of representative-user preference. The shared shell, Settings,
and About implementations are merged through #47, #65, and #67. Issue #66
completes the browser Timer candidate; no M5 usability validation or platform
support is established by implementation or compilation.

An interactive, browser-based demonstration is available in the
[local demo](demo/README.md). It has one configurable countdown, defaulting to
two hours, and a single Shape selector: Clock (formerly Disk), Battery, Hand fan, or Ring.
The former digital circle is merged into Clock. Changing shape preserves
the countdown, shared background/foreground/text colors, font, and text
visibility. Ring thickness appears only for Ring and is retained when changing
shape. Show remaining independently controls the duration display. A Show
details slider steps through Off, then 2–12 equal sections, defaulting to six.
Details add duration-based labels outside Clock only, Ring section
boundaries, battery divisions, or fixed fan separators. Clock keeps a continuous
fill regardless of details; only its outer labels change. Ring details add no
intermediate text; Show remaining still controls its central duration. One hour with two
sections shows 0 and 30. Configured durations below ten minutes use whole seconds
(five minutes with twelve sections: 0, 275, 250, ..., 25 clockwise).
Durations of ten minutes or more use compact minutes rounded to one decimal,
omitting trailing zeros (thirty minutes with twelve sections: 0, 27.5, 25,
22.5, ..., 2.5). A single unit label below Clock is shown only with details.
Units do not change as the countdown runs.
The base fill remains visible. Shapes have no explanatory captions.
Font and text color controls appear when time
or Clock numerals are visible, without
clearing their values when hidden. Each shape uses only the selected colors;
unselected colors are not added as decorative highlights or outlines. The
disk's optional duration numerals use 36 px text outside its progress area, with
reserved space for the larger labels. Clock uses a radial remaining-time
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

The requirement IDs cited below are from
[M2-DOC-001](../product/requirements.md); implementation and verification
status is in [M2-DOC-003](../safety/requirements-traceability-matrix.md).
The risk IDs are from [M1-DOC-004](../safety/risk-assessment.md); related
controls are not verified and risk disposition is recorded separately. This
design does not establish medical-device qualification, legal conformance, or
certification.

## Design principles

- The timer visualization represents remaining duration, not a clock, time of
  day, deadline guarantee, or safety-critical timing system.
- Each visualization uses the same continuous remaining-time mapping: its
  complete form represents 100% of the configured duration remaining; its
  empty form represents zero. Static examples use the same illustrative 60%
  remaining.
- Progress uses non-colour cues. The bottom-right action is Pause while
  running, Resume while paused, and Restart when finished or interrupted;
  detail 0 uses icon-only actions with accessible names and no Timer text.
  Interrupted and idle retain their warning-triangle and empty-circle cues.
- Accessible descriptions identify states and stale interrupted progress.
  Distinguishability, action comprehension and warning effectiveness at all
  details remain subject to accessibility, usability and risk review.
- For non-idle timers, accessible semantics expose a progress indicator named
  "Remaining waiting time" with a value from 0% to 100% remaining. The
  zero-duration idle state is described as not counting and not completed,
  rather than as a 0%-remaining countdown. Exact spoken wording and
  announcement cadence remain for accessibility/usability review; this record
  does not select an accessibility conformance baseline.
- No flashing, countdown animation, sound, or motion is proposed by these
  static drawings.
- The product owner selected the four shapes already in the demo for the v1
  theme selector: Clock, Battery, Fan, and Ring. This is a product/design
  decision, not a claim that users prefer one shape or that all intended users
  can use each shape successfully.
- Application font, colors, detail, and Ring thickness are now specified by
  REQ-012. Demo-only independent visibility controls remain exploratory.
  A control's inclusion does not establish usability or accessibility.
- Android TV and Apple TV are primary project targets. This visual design
  source does not establish remote/D-pad focus behavior, viewing-distance
  typography, TV layout limits, or platform support; those require target-
  specific design and verification. Wear OS and watchOS are outside v1 scope.

## Screen flows

### Main screen and states

![Historical illustrative Main screen states with text labels; the running and paused labels do not represent the current candidate.](main-states.svg)

The running and paused examples retain the same illustrative progress shape.
The interrupted example retains the shape but explicitly warns at detail 1-12
that progress may be out of date; at detail 0, an outlined warning cue and an
accessible stale-progress description replace all on-screen Timer text. It
must not imply timing continuity when that is unknown.
The proposed one-shot behavior pauses a running timer on Settings entry.
Close or Back resumes only retained running intent; returning from About to
Settings does not resume. Valid duration changes reset the engine paused to
full immediately; appearance-only changes and invalid drafts do not. Settings
alone never clears interruption. Zero duration presents idle without an
action or stale-progress warning while retaining interruption internally;
positive reconfiguration restores the warning and requires explicit Restart.
Restart clears interruption and starts a full countdown; lifecycle stop while Settings holds running intent
revokes automatic resume. Platform delivery and these transitions remain
unverified. Settings remains unprotected.

| Main element | Proposed presentation and semantics | Requirement / risk links |
|---|---|---|
| Remaining progress | A theme's progress shape; accessible role: progress indicator; label: "Remaining waiting time"; value: percent of the configured duration remaining, 0-100. At idle 00:00, expose an idle/not-completed description without a countdown progress value. | REQ-002, REQ-003; RISK-001, RISK-005 |
| Remaining time | At nonzero detail when a countdown value is available, center the selected-font and text-color value in a rounded, content-sized liquid-glass surface over the visualization, using 28 sp before system font scaling. Detail 0 and the idle prompt have no such surface. Use a tinted fallback and visible error if backdrop capture reports failure. Review legibility, overlap with markers and progress, and device-specific rendering; unrestricted color choices may still be unreadable. | REQ-003, REQ-012, REQ-013; RISK-001, RISK-005 |
| Running state | Bottom-right Pause action with pause icon, accessible name and running description; no visible Timer text at detail 0. | REQ-003, REQ-013; RISK-001, RISK-005 |
| Paused state | Bottom-right Resume action with play icon and accessible name; retain remaining time. Opening Settings pauses running. | REQ-002, REQ-003, REQ-013; RISK-001, RISK-005 |
| Interrupted state | Show a stale-progress warning at detail 1-12, or warning cue and accessible description at detail 0. Restart explicitly clears it and starts full duration; Settings alone cannot clear it. | REQ-003, REQ-013; RISK-002, RISK-005 |
| Finished state | A positive countdown stops at zero, distinct from idle, with a bottom-right Restart action; Restart starts the configured full duration. | REQ-003, REQ-010, REQ-013; RISK-001, RISK-005 |
| Idle state | 00:00 remains non-counting and non-animating with an empty base shape and no enabled action, including when interruption is retained internally. The interruption warning is hidden at zero and returns upon positive reconfiguration, requiring explicit Restart. The idle cue and accessible description do not represent completion. At detail 0, no on-screen Timer text is shown. | REQ-002, REQ-013; RISK-001, RISK-005 |
| Settings access | Settings is available without a caregiver-protection mechanism in v1. This is the product owner's explicit risk disposition, not a verified control; a patient may change the saved duration or theme. | REQ-005; RISK-004 |
| Settings lifecycle | Pause on open; close/Back resumes only retained running intent, from remaining time or the new full duration after an actual valid duration change. About return does not resume. | REQ-002; RISK-001, RISK-002 |
| Manual timer controls | Pause, Resume, and Restart as applicable at every detail, with localized accessible names; detail 0 is icon-only, Restart is visibly labeled at details 1-12. No enabled action at zero configuration. Controls must not accept input or focus beneath Settings/About or exit overlays. | REQ-002, REQ-010, REQ-013; RISK-001, RISK-005 |
| Volume and audio | No in-app volume control, music integration, or completion sound is included in v1. Music is deferred to v2 and its behavior requires separate review. | REQ-004, REQ-008, REQ-010; RISK-003 |

At completion, REQ-010 stops at zero in a distinct finished state and offers
Restart; it never loops. A configured 00:00 is idle, non-counting and not a
completed countdown. A duration change after finish prepares a paused full
timer requiring Resume.
This candidate behavior remains subject to review and approval.

### M5 Timer scope and detail mapping

Issue #66 completes the Timer screen for the browser MVP only. The visualization
uses the full available feature area and preserves one continuous remaining-
time fraction across Clock, Battery, Fan, and Ring. Clock's remaining arc starts
at 12 when full; as time elapses, its leading edge moves clockwise to the
right. Clock detail N shows N duration labels evenly around the disk and never
adds segment boundaries; for a one-hour configured duration and detail 2, the
labels are 0 at the top and 30 at the bottom. Durations below ten minutes use
whole-second labels; longer durations use compact minute values rounded to one
decimal with trailing zeros omitted. One localized unit label appears below
Clock only while details are shown. Battery uses vertical dividers, Fan uses
radial fan-blade dividers, and Ring uses dividers across its stroke. At detail N
from 2 through 12, N-1 equal boundaries form N segments; detail 1 is one
continuous, undivided segment. Detail 0 has no extra boundaries or Clock labels.
The application Fan is a centered, fully visible upper semicircle; its remaining
sector and stationary radial dividers follow the demo's sweep and clipping.

At detail 0, all on-screen Timer text is absent, including remaining time and
interruption warnings. Action icons distinguish running and paused actions;
interrupted and idle cues remain. Screen-reader descriptions identify states
and stale progress. Comprehension remains subject to human review. The feature
adds no countdown animation; elapsed
time continues to come from the monotonic timer engine. The selected background
color fills the Timer content surface and depleted visualization; the
foreground color represents remaining time, and the text color is used for
labels and state cues. A bounded headless Chrome render check observed the idle
screen at 320 × 640 and 1440 × 1000, plus running Clock and Fan presentations at
both viewports with a two-hour duration and detail 6. The Clock showed six
duration labels around a continuous disk with no internal boundaries; the Fan
semicircle and visible dividers fit within its available visualization area.
This is not full responsive acceptance; other details, states, reduced-motion,
screen-reader, and human review remain verification work.

### Settings screen

![Historical Settings prototype with a Save action; current Settings saves automatically and does not use Save to start.](settings-about.svg)

The duration entry example follows the candidate sequential-digit rule:
`205` displays as `02:05`. The configured duration is a whole-minute value
from 00:00 through 24:00. The examples describe field content, not a
verification result.

Requirements: REQ-001 (duration entry), REQ-002 (Settings pause, retained intent, and manual controls), REQ-004 (screen content), REQ-005 (v1 access decision),
and REQ-006 (theme selection). Optional music is deferred to v2.

Settings slides in from and out to the left over a stationary, full-window
Timer, including behind the app bar. Settings is full-width below 600 dp and
at most 440 dp wide otherwise. About slides over Settings in the same direction
and uses the same widths. The background Timer is blurred
where Compose supports native blur and otherwise remains unblurred; it is not
a sharp or unobstructed preview. Mobile targets accept sequential digits from the system keyboard
when available and show the reusable numeric keypad as a fallback. Desktop
uses physical-keyboard entry without the app keypad. Browser and TV use the
keypad without keyboard-based duration entry; TV support is not established.
These flows preserve `205` as `02:05`; layout, input, focus,
accessibility, and no-clipping behavior remain unverified.

The Navigation 3 host receives system Back. The Settings layer starts its exit
when Back completes rather than tracking predictive-back gesture progress;
gesture cancellation and device behavior remain unverified.

The v1 flow has no caregiver-only access gate: Settings can be opened without
protection. The user configures duration and visualization; valid changes
update the background Timer presentation and are saved automatically, without a Save action.
The full app-owned area uses the selected background, including app bars,
pane gutters, and About. Settings and About use Cloudy liquid-glass layers
separate from their sharp text and controls; the Timer's native blur is
separate. On Android below API 31 the native Timer blur is not applied;
Cloudy documents a tint-and-edge fallback instead of refraction through API 32.
Renderer behavior, fallback and legibility remain subject to verification. The selected
foreground colors control accents, selected choices, keypad buttons, focused
duration-field border, label, cursor and selection, and slider thumb and active
track. Keypad labels use contrasting black or white; keyboard focus outlines
remain independent. Settings text and input typography use the selected text
color and font; semantic error colors and keypad contrast are retained. The
Settings, close, and About-back app-bar icons use the selected text color.
Selected color swatches use a check icon in that color on a contrasting
black/white backing so selection remains visible when the swatch and text colors
match. The swatch remains visible around the indicator. Low-contrast text and
background choices can still obscure navigation icons; no restriction is added.
Each of the three color pickers offers the same 16 named presets, with up to
eight 48 dp targets per row; narrower panels wrap to additional rows. Presets
are grouped by similarity: neutrals from light to dark (white, paper, gray, ink,
black), then hues in wheel order (yellow, orange, red, pink, purple, navy, blue,
cyan, teal, green, lime). The
Rounded font uses the bundled Nunito typeface, distinct from Default; Serif is
also available alongside Monospace. Saved font and color choices remain
independent. Ring thickness retains all integer values from 1 to 100 percent
without displaying the dense discrete ticks. Compact layout, font rendering,
glyph coverage, and the expanded palette need platform and human review.
Shape,
detail and Ring thickness update the Timer preview, not the Settings layout.
The Detail slider suppresses its track ticks and end dot at zero but retains
its thumb, label, value, and 0-12 discrete interaction. Changes update
immediately, including appearance changes while a duration draft is invalid.
Since foreground and background choices are unrestricted, some combinations
can make controls or their accents difficult to distinguish; product,
accessibility, usability and risk review remains pending.
While Settings is open, a separate full-duration presentation snapshot does
not advance the engine. A changed valid duration immediately resets the engine
paused to its new full duration; appearance-only, unchanged and invalid drafts
do not reset it. Invalid drafts keep the last valid preview and show an error.
Closing via close or Back resumes only retained running intent, using remaining
time or the newly configured full duration. Manual pause remains paused; an
unchanged finished timer remains finished at zero; a duration change after
finish prepares a paused timer requiring Resume. Initial positive configuration
requires Resume. A zero configuration remains idle with no enabled action.
Preview and Settings alone never clear interruption; Restart is required.
Settings remains accessible without a caregiver gate.

The appearance controls are conditional on the selected visualization. Offer
a font choice only when it displays text, and a color choice only when it uses
color. These are proposed settings, scoped to the selected
visualization; available choices, defaults, saving/persistence, and any
reset-to-default action remain undecided. Any selected font or color must
preserve readable text, non-colour progress/state cues, and the same remaining
time semantics. Contrast and legibility need accessibility and usability
review; no conformance baseline or user preference is claimed.

There is no music area or audio control in the v1 Settings design. Music
integration is deferred to v2. Provider, connection, playback controls, start
behavior, volume controls/limits, completion sounds, and audio-failure
presentation are not decided for v2 and require later product, provider,
platform, and usability review.

### About screen

The About presentation includes the owner-supplied Entreco logo as a Compose
resource, displayed with fit scaling and accessible branding text. It groups
the app identity and required information into readable sections and uses the
existing Settings → About → Settings navigation in the shared shell. It is
shown as a left-sliding overlay over Settings on compact and expanded windows;
actual platform layouts and screen-reader behavior remain unverified.

The version/build value, support information, privacy policy, and relevant
licences have no authoritative source in the repository or supplied inputs.
The UI identifies each as unavailable instead of inventing details or
presenting dead links. These explicit gaps are not substitutes for the content
required by REQ-004, and they block a complete About screen. The product owner
must provide the actual version/build source and approved support, privacy, and
licence information before this requirement can pass.

The intended-use and limitations text is sourced from the project product
boundary and is limited to ordinary waiting-time support. It does not claim
medical use, clock or time-of-day display, deadline assurance, or
safety-critical timing. Human design, usability, and accessibility review
remain pending; the implementation does not establish user preference or
verification.

Requirements: REQ-004 (About content) and REQ-009 (non-medical claims boundary).

The proposed intended-use and limitation copy is restricted to ordinary
waiting-time support and says the app is not a clock or for medical or
safety-critical timing. It does not claim a clinical benefit or guarantee that
a wait has ended. The browser MVP exposes Main, Settings, and About in its
primary navigation. The browser implementation explicitly identifies missing
owned content as not provided; it does not fabricate a logo, build value,
support contact, privacy policy, or licence list.

## Timer visualizations

Issue #66's latest candidate reserves equal top/bottom decoration space
independently of detail. Text is overlaid in those bounded regions, scrollable
when short windows or larger fonts require it. Base geometry does not depend
on decoration visibility. Clock reserves one duration/font-aware envelope for
all label counts, including when labels are hidden. Visible state cues are
non-interactive and sit at the bottom-right of the Timer pane.

Battery matches the demo's shallow horizontal body, foreground outline,
inset clipped fill, and external foreground terminal. Preferred body height
is capped at 126 logical pixels; narrow/short layouts shrink it to fit while
wider layouts may stretch horizontally. The complete silhouette is centered.
Ring thickness is a normalized 1-100% of outer radius, default 24% to preserve
the prior nominal appearance, not the demo's pixel default. Its outer radius
stays fixed, track/progress share a centerline, and 100% uses a filled sector
rather than an overlapping thick stroke. Thickness survives shape changes
and storage reopen; legacy settings get the default without losing selections.
These designs require final human review; no new preference evidence is claimed.

The approved demo presents the four v1 choices: Clock, Battery, Fan, and Ring.
The separate [interactive demo](demo/README.md) is the visual reference.

| Visualization | Visual treatment | Remaining-time mapping | Requirement / risk links |
|---|---|---|---|
| Clock | A remaining-time disk sector whose leading edge starts at 12 and moves clockwise to the right as time elapses. Detail N adds N evenly spaced duration labels outside the disk and no segment boundaries; for a one-hour duration at detail 2, labels are 0 at the top and 30 at the bottom. Durations below ten minutes use whole seconds; longer durations use compact minutes and a unit label. No clock face, hands, or time-of-day markings. | The filled sector represents the fraction remaining; full is 100%, empty is zero. | REQ-003, REQ-006, REQ-013; RISK-001, RISK-005 |
| Battery | A rectangular fill drains linearly inside a terminal battery shape; N-1 vertical boundaries divide N equal segments. No battery percentage or charge claim. | Filled extent represents the same fraction remaining as Clock. | REQ-003, REQ-006, REQ-013; RISK-001, RISK-005 |
| Fan | A centered, fully visible, fixed-radius upper semicircle; the filled 180-degree sector empties toward its fixed edge. N-1 stationary radial fan-blade boundaries divide N equal segments and are visible only within the remaining sector. | Filled sector represents the same fraction remaining as Clock. | REQ-003, REQ-006, REQ-013; RISK-001, RISK-005 |
| Ring | A circular stroke depletes around a fixed track; N-1 boundaries across its stroke divide N equal segments. Remaining duration may be shown separately at detail 1-12. | Stroke extent represents the same fraction remaining as Clock. | REQ-003, REQ-006, REQ-013; RISK-001, RISK-005 |

These are the v1 theme choices approved by the product owner, not a claim that
any shape is preferred or usable by every intended user. Their shared mapping
is a design constraint; it has not been verified across the application,
durations, layouts, platforms, or assistive technology. The interactive demo
is the visual reference for these four shapes, not a substitute for application
verification or TV-specific remote/focus design.

The local demo makes these shapes interactive without establishing
requirements or product behavior. Its configurable countdown runs from a user
action, uses the browser monotonic clock, and immediately resets to the chosen
duration at completion without a completed state. Shape switching does not reset
countdown progress. It does not implement pause, restart controls, persistence,
audio, or interruption recovery. Styling previews are shared across shapes for
the current page session only; nothing is saved across reloads.

## Product decisions and evidence gaps

The following product decisions define the current v1 proposal. They do not
establish competent risk review, design approval, or verification:

| Decision | Design boundary |
|---|---|
| D-01: caregiver protection mechanism | Product owner accepts unprotected Settings for v1; the design shows no access gate. RISK-004 acceptability remains subject to competent review; no control effectiveness is claimed. |
| D-02: volume controls and limits | No in-app volume controls in v1. Reassess audio/volume requirements if a later version adds music. |
| D-03: music and audio behavior | Music is deferred to v2. No music start behavior or completion sound is included in v1; future audio behavior remains undecided. |
| D-04: timer start and Settings lifecycle | Settings pauses running; close resumes retained running intent, and actual valid duration changes reset paused immediately. Other changes do not reset. |
| D-10: text-free detail 0 | Hide Timer text; retain accessible icon actions and interrupted/idle cues. Human review of distinguishability and warning remains pending. |
| D-05: interruption recovery | No automatic or persisted recovery; explicit Restart clears the warning and starts full duration. Settings alone cannot clear it. |
| D-06: manual pause, resume, and restart controls | Main offers Pause, Resume or Restart as applicable at all detail levels. |
| D-09: Settings layout and duration input | Overlay Settings on the Timer at compact and expanded widths. Mobile uses available system keyboard input and falls back to the shared keypad; desktop uses keyboard entry without the app keypad; browser and TV use the keypad without keyboard-based duration entry. TV support is not established. |
| Visualization appearance | Font and color are proposed as conditional per-visualization settings; option lists, defaults, and persistence remain undecided and require accessibility/usability review. |

The product owner approved the four demo visualizations for the v1 design.
That decision does not close retrospective-evidence gaps or establish usability
for caregivers, representative patients, assistive technologies, keyboard,
touch, Android TV remote/D-pad, Apple TV focus, or any target platform. The
static SVGs remain non-interactive. Record exact reviewer scope, findings,
dispositions, limitations, and human acceptance of this revision in its review
PR.

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
| 1.3 | Replace Show progress with an Off/2–12-section Show details slider; subdivide Battery/Fan/Ring, keep Clock fill continuous, and label Clock only; remove shape explanations. Rename prototype Disk to Clock and use seconds below ten configured minutes, compact decimal minutes otherwise, with one unit label; no time-of-day display or product-boundary change. |
| 1.4 | Align the proposed Main and Settings flows with v1 decisions: no caregiver gate or volume/music controls, saving settings starts a full-duration countdown, opening Settings pauses it, and interruption recovery is not provided. |
| 1.5 | Update the linked risk-assessment source revision to 0.5; preserve the v1 product decisions and pending risk/design review. |
| 1.6 | Record the v1 decision to omit separate manual pause, resume, and reset controls; the existing prototype designs remain the review scope. |
| 1.7 | Record the product owner's approval of the four demo visualizations (Clock, Battery, Fan, Ring) for the v1 theme set; distinguish that decision from user preference and validation, and add TV-target interaction/layout limitations. |
| 1.8 | Record the supporting-pane Settings flow and target-specific keyboard/keypad policy; preserve usability, accessibility, layout, and platform verification limitations. |
| 1.9 | Record the About implementation candidate, owner-supplied logo resource, explicit unavailable-content gaps, intended-use boundary, and outstanding product/design/accessibility verification. |
| 2.0 | Define issue #66's adaptive Timer scope, level-0 visual/accessibility cues, exact detail segment mapping and clockwise Clock depletion; retain human design/usability/risk review and browser verification gaps. |
| 2.1 | Record Timer surface-color behavior and the bounded headless browser render observation; keep full visual, accessibility, reduced-motion, and human reviews pending. |
| 2.2 | Align application Clock detail with the demo's outer duration labels and units instead of concentric bands; specify centered, unclipped Fan geometry and retain human review and verification gaps. |
| 2.3 | Specify global background, translucent Settings, immediate duration preview, bottom-right status cue, shallow Battery, stable detail overlays, and normalized Ring thickness; retain review gaps. |
| 2.4 | Describe desktop keyboard entry without the app keypad; retain physical-device, accessibility, and layout review gaps. |
| 2.5 | Define live Settings appearance, the detail-zero dot-free slider track, and a subtle glass background with fallback; retain human and platform verification gaps. |
| 2.6 | Replace the Settings side pane and glass treatment with an overlay and native-blurred Timer backdrop; retain unblurred fallback and review gaps. |
| 2.7 | Describe the stationary full-window Timer and sliding glass Settings and About layers; retain platform and human review gaps. |
| 2.8 | Describe text-colored Material navigation and swatch selection icons and the swatch indicator backing; retain low-contrast, platform, and human review gaps. |
| 2.9 | Describe the centered enlarged remaining-time glass surface and its fallback; retain readability, rendering, overlap, and human review gaps. |
| 3.0 | Describe the expanded responsive palette, distinct fonts and tick-free Ring slider; retain accessibility and platform review gaps. |
| 3.1 | Specify one-shot states, explicit controls and Settings intent; retain historical prototype behavior and pending review. |
| 3.2 | Clarify zero idle priority and retained interruption with warning restored on positive reconfiguration; retain accessibility and risk review. |
| 3.3 | Group color presets by similarity: neutrals light to dark, then hues in wheel order; retain accessibility and human review gaps. |
