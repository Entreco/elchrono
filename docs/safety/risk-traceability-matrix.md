# Risk traceability matrix

| Field | Value |
|---|---|
| Document ID | M1-DOC-009 |
| Revision | 1.4 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software-safety review: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#4](https://github.com/Entreco/elchrono/issues/4); duration/traceability update in [#22](https://github.com/Entreco/elchrono/issues/22); provider-feasibility update in [#36](https://github.com/Entreco/elchrono/issues/36) |
| Review PR | Prior baseline: [PR #14](https://github.com/Entreco/elchrono/pull/14); revision 0.7: [PR #32](https://github.com/Entreco/elchrono/pull/32); revision 0.8 in [PR #49](https://github.com/Entreco/elchrono/pull/49); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); #67 candidate: [draft PR #68](https://github.com/Entreco/elchrono/pull/68); #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context; see
[document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and use

This matrix links the scenarios and proposed controls in
[M1-DOC-004 revision 0.8](risk-assessment.md#risk-analysis) to intended-use
sources, assessment assumptions, and planned verification. Each risk and
follow-up is owned by the product owner, @Entreco. The M2
[requirements traceability matrix](requirements-traceability-matrix.md) is the
authoritative location for requirement-to-risk-to-test links. This M1 matrix
does not establish that controls are implemented, verified, or effective, or
that residual risks are acceptable.

The source is [M1-DOC-002 revision 1.0](../product/intended-use.md); its
exact-revision approval evidence remains unresolved. Focused JVM tests cover
counter and settings-domain behavior, but do not establish control effectiveness
or product verification. Update each row with relevant test/review, result,
tested revision/platform, and a durable evidence link. Record failures,
limitations, and pending control coverage as well as passes; do not treat
unrelated app tests as evidence of a risk control. A qualified review
of the combined controls is needed before recording a post-control score in
M1-DOC-004; a passing test does not itself establish a reduction or
acceptability. Review findings and human acceptance remain in the PR under
[document controls](../governance/document-control.md).

## Matrix

Each risk ID links to the assessment table; the
[control tables](risk-assessment.md#risk-controls) identify the proposed
controls and their intended effects. Product-level verification remains
planned; focused domain-test results are recorded for RISK-001. No result
establishes control effectiveness or residual-risk acceptability.

| Risk and proposed controls | Intended-use trace | Assessment basis and assumptions | Planned verification | Test/review evidence and result |
|---|---|---|---|---|
| [RISK-001](risk-assessment.md#risk-analysis) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio); [limitations](../product/intended-use.md#foreseeable-use-and-limitations) | The browser candidate implements a timer and Settings UI, but is not in field use; operational and representative-user evidence is unavailable. Product UI, layout, accessibility, and interruption behavior remain unverified. | Verify all 1,440 durations, invalid configuration and digit entry, Settings-open pause/save-to-start, completion boundary, and consistent visual semantics with deterministic inputs; separately verify UI, supported layouts, accessibility, and interruption paths. | JVM tests cover duration values, invalid boundaries, injected-time countdown/pause/save/reset, core sequential duration entry, and the Settings keyboard/keypad policy. Run: `./kotlin test -p jvm -m common -m counter -m design -m settings -m localization`. These domain results do not establish UI/control effectiveness; product verification and risk acceptance remain pending. |
| [RISK-002](risk-assessment.md#risk-analysis) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio); [limitations](../product/intended-use.md#foreseeable-use-and-limitations) | Product decision: no recovery behavior is provided or promised in v1. The browser MVP records a hidden-page/page-hide event and pauses when detected. Issue #66 replaces the on-screen interrupted warning at detail 0 with a visual cue and accessible stale-progress description; the change is not human-reviewed or product-verified. | Verify browser hidden/return transitions at detail 0 and 1-12, text and visual cues, accessible stale-progress description, no automatic resume, and fresh-start behavior after valid Settings changes. Reassess before adding recovery or expanding supported behavior. | Focused state-mapping JVM tests pass; rendered browser behavior, assistive-technology output, usability, risk review, and control effectiveness remain pending. |
| [RISK-003](risk-assessment.md#risk-analysis) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | Music and in-app volume controls are excluded from v1 and deferred for future consideration. [M3-DOC-003](../architecture/audio-provider-feasibility-findings.md) records provider policy, platform, data-flow, and testability constraints; no future provider or playback behavior is approved. | No v1 audio behavior to verify. Before v2 audio, review provider/data flows, playback limits/failure, volume, start and completion sound behavior, then verify timer independence and audio behavior. | Deferred; no audio implementation or verification is claimed. |
| [RISK-004](risk-assessment.md#risk-analysis) | [Users and roles](../product/intended-use.md#users-population-and-roles); [visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | Product owner accepts unprotected Settings for v1; patients can change duration/theme. No gate or access-control effect is claimed. An unintended change may cause confusion or alter a wait; competent review of RISK-004 acceptability remains pending. | Verify the implemented behavior matches the approved open-access decision and clearly reports saved configuration; complete competent risk/UX review of the unprotected Settings exposure. | Pending; no control verification, risk acceptance review, or result. |
| [RISK-005](risk-assessment.md#risk-analysis) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio); [limitations](../product/intended-use.md#foreseeable-use-and-limitations) | Product requirements call for predictable layouts, contrast, large controls, accessible labels, and no colour-only time semantics. Issue #66 adds text-free level-0 state cues and segment treatments across four themes; their comprehension has not been evaluated. No representative usability findings exist. Android TV is configured as a candidate; Apple TV is unconfigured. Neither is verified for support. | Inspect state/progress semantics, non-color cues, each shape at all detail levels, responsive layouts, and reduced motion. Obtain the required human accessibility/usability/risk review. Before claiming TV support, separately verify remote/focus and viewing-distance readability on configured targets. | Focused JVM mapping and detail tests pass; browser rendering, screen-reader behavior, reduced-motion behavior, human findings, control effectiveness, and risk acceptance remain pending. |
| [RISK-006](risk-assessment.md#risk-analysis) | [Limitations](../product/intended-use.md#foreseeable-use-and-limitations); [exclusions and claims](../product/intended-use.md#exclusions-and-claims-boundary) | Safety-critical timing and authoritative deadline reliance are excluded. Serious harm is plausible if misused, not a supported use; its likelihood is unmeasured and requires reviewer confirmation. Actual use and public claims have not been observed. | Review requirements, user-facing content, and product claims against intended-use exclusions before release; assess changes against the [qualification record](../regulatory/qualification-and-classification.md) and this risk. Include About's intended-use/limitations copy and all owner-provided content, and the TV target/claims boundary when scope changes. | About copy is aligned to the candidate ordinary-waiting boundary and required content gaps are disclosed; no control or claim review is verified, and required owner sources plus human review remain pending. |

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish risk-to-source and planned-verification traceability; record missing evidence without claiming test results |
| 0.2 | Update the RISK-001 source and planned verification to the 1-minute duration increment and sequential Hours/Minutes entry requirement |
| 0.3 | Align planned risk verification with proposed caregiver-only Settings, patient remaining-time visibility, and immediate reset/music stop at completion; keep all review and verification pending |
| 0.4 | Remove requirement-level trace references; designate the M2 requirements traceability matrix as the single location for requirement-risk-test links |
| 0.5 | Update the source risk-assessment revision and keep detailed requirement-risk-test traceability in the M2 matrix |
| 0.6 | Align planned risk verification with v1 decisions for open Settings, no volume/audio controls, save-to-start pause behavior, and no interruption recovery; retain competent risk review and verification gaps. |
| 0.7 | Update the linked M1-DOC-004 source revision to 0.5; retain all risk review and verification gaps. |
| 0.8 | Link M3-DOC-003 provider-feasibility evidence to RISK-003 without changing its assessment, controls, or pending verification. |
| 0.9 | Align the M1 source revisions and add current browser-interruption and primary-TV verification limitations without claiming control effectiveness or platform support. |
| 1.0 | Align the current risk source and record focused counter/settings-domain test evidence while retaining pending product-level verification and risk review. |
| 1.1 | Record core duration-entry ownership, Settings keyboard/keypad policy test scope, and adaptive-layout verification limits; do not treat builds or policy tests as UI/control verification. |
| 1.2 | Record the candidate Android TV launcher without claiming TV risk-control verification. |
| 1.3 | Link the About copy and owner-controlled content gaps to RISK-006 review scope; do not claim the risk control or human review is verified. |
| 1.4 | Align the linked M1-DOC-004 revision and trace issue #66's detail-0 warning redesign, all-shape detail coverage, and explicit pending human/browser evidence. |
