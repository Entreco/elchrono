# Risk traceability matrix

| Field | Value |
|---|---|
| Document ID | M1-DOC-009 |
| Revision | 1.10 |
| Current change review | [PR #78](https://github.com/Entreco/elchrono/pull/78); human acceptance and required specialist review pending |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software-safety review: @Entreco, designated by the product owner; exact-revision acceptance and findings disposition are recorded in the review PR |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#4](https://github.com/Entreco/elchrono/issues/4); duration/traceability update in [#22](https://github.com/Entreco/elchrono/issues/22); provider-feasibility update in [#36](https://github.com/Entreco/elchrono/issues/36) |
| Review PR | Prior baseline: [PR #14](https://github.com/Entreco/elchrono/pull/14); revision 0.7: [PR #32](https://github.com/Entreco/elchrono/pull/32); revision 0.8 in [PR #49](https://github.com/Entreco/elchrono/pull/49); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); #67 candidate: [draft PR #68](https://github.com/Entreco/elchrono/pull/68); #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); status-indicator follow-up: [draft PR #74](https://github.com/Entreco/elchrono/pull/74); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context; see
[document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and use

This matrix links the scenarios and proposed controls in
[M1-DOC-004 revision 1.11](risk-assessment.md#risk-analysis) to intended-use
sources, assessment assumptions, and planned verification. Each risk and
follow-up is owned by the product owner, @Entreco. The M2
[requirements traceability matrix](requirements-traceability-matrix.md) is the
authoritative location for requirement-to-risk-to-test links. This M1 matrix
does not establish that controls are implemented, verified, or effective, or
that residual risks are acceptable.

The source is [M1-DOC-002 revision 1.1](../product/intended-use.md); its
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

Historical issue #66 refinement coverage is superseded where duration changes
now reset the engine. RISK-001 maps revised preview/reset tests to TP-REQ-014;
RISK-004 maps legacy
settings and thickness validation to TP-REQ-012/014; RISK-005 maps global
background/readability, stable detail geometry, Battery silhouette, Ring
thickness, and cue interpretation to TP-REQ-003/013/015. Pure JVM tests and
bounded browser observations do not establish comprehension, control
effectiveness, or risk acceptance; human review remains pending.

Each risk ID links to the assessment table; the
[control tables](risk-assessment.md#risk-controls) identify the proposed
controls and their intended effects. Product-level verification remains
planned; focused domain-test results are recorded for RISK-001. No result
establishes control effectiveness or residual-risk acceptability.

| Risk and proposed controls | Intended-use trace | Assessment basis and assumptions | Planned verification | Test/review evidence and result |
|---|---|---|---|---|
| [RISK-001](risk-assessment.md#risk-analysis) | [Waiting and limitations](../product/intended-use.md#foreseeable-use-and-limitations) | Duration changes, remaining time and one-shot completion can be misunderstood or incorrect. | TP-REQ-002/010/014: deterministic all-duration boundaries, Settings intent, actual changes and appearance-only edits; UI and usability review. | Operator-reported passing JVM tests exercise all positive durations and one-shot completion, Settings intent and duration resets. [Execution record](../testing/test-protocol.md#one-shot-timer-and-manual-controls-candidate); product and risk review pending. |
| [RISK-002](risk-assessment.md#risk-analysis) | [Limitations](../product/intended-use.md#foreseeable-use-and-limitations) | Lifecycle delivery and icon-only stale-progress warning may be misunderstood. At zero, idle presentation hides the warning despite retained interruption. | TP-REQ-002/003/013: stop while running and while Settings holds intent, no automatic recovery, Settings cannot clear warning, explicit Restart. | Reported JVM tests cover stop with held intent and explicit Restart. Zero idle priority and warning restoration after positive reconfiguration need human risk/accessibility review; actual platform delivery remains pending. |
| [RISK-003](risk-assessment.md#risk-analysis) | [Visual time and audio](../product/intended-use.md#supported-role-of-visual-time-and-optional-audio) | Music and in-app volume controls are excluded from v1 and deferred for future consideration. [M3-DOC-003](../architecture/audio-provider-feasibility-findings.md) records provider policy, platform, data-flow, and testability constraints; no future provider or playback behavior is approved. | No v1 audio behavior to verify. Before v2 audio, review provider/data flows, playback limits/failure, volume, start and completion sound behavior, then verify timer independence and audio behavior. | Deferred; no audio implementation or verification is claimed. |
| [RISK-004](risk-assessment.md#risk-analysis) | [Users and roles](../product/intended-use.md#users-population-and-roles) | Settings and new timer actions are accessible without a caregiver gate; unintended changes and resets may alter a wait. | TP-REQ-002/005/014 and caregiver usability/risk review of open access, duration edits and manual actions. | Reported JVM tests exercise manual actions and duration changes, not their consequences for users. No protection, control effectiveness or risk acceptance claimed. |
| [RISK-005](risk-assessment.md#risk-analysis) | [Waiting and limitations](../product/intended-use.md#foreseeable-use-and-limitations) | Icon-only actions at detail 0, finished/idle distinction and stale warning need evaluation; unrestricted colors may impair visibility. | TP-REQ-003/013/015: all details/states, focus, accessible names, overlay isolation, clipping and supported inputs; human usability and risk review. | Reported JVM tests exercise actions across details, Tab/Enter, overlay isolation and one compact large-font layout. Zero idle priority, screen readers, complete device matrix, human review and effectiveness remain pending. |
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
| 1.5 | Align with M1-DOC-004 revision 1.0 and add Clock marker accuracy and theme-layout clipping to planned risk verification; no control effectiveness or residual-risk acceptance is claimed. |
| 1.6 | Trace refinement preview, storage, geometry, and appearance checks to existing risks and protocol cases; retain human-review and effectiveness gaps. |
| 1.7 | Trace the missing detail-0 running/paused visual distinction to REQ-003, RC-005-1, and pending risk review. |
| 1.8 | Trace revised timer and Settings controls to planned verification and preserve historical evidence limitations. |
| 1.9 | Link operator-reported bounded JVM evidence and the zero-duration interruption-warning mismatch; retain platform and human-review gaps. |
| 1.10 | Trace approved zero idle priority and retained interruption; keep human and platform review pending. |
