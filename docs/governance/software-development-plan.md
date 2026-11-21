# Software development plan

| Field | Value |
|---|---|
| Document ID | M1-DOC-007 |
| Revision | 1.7 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | @Entreco, designated by the product owner as human reviewer across applicable M1/M2/M3 disciplines for issue #47; designation is self-attested, with final exact-head acceptance pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#7](https://github.com/Entreco/elchrono/issues/7); duration-verification update in [#22](https://github.com/Entreco/elchrono/issues/22); M3 provider feasibility update in [#36](https://github.com/Entreco/elchrono/issues/36); dependency/release documentation in [#38](https://github.com/Entreco/elchrono/issues/38); M4 scope reconciliation in [#44](https://github.com/Entreco/elchrono/issues/44); M5 scope reconciliation in [#46](https://github.com/Entreco/elchrono/issues/46); Timer update in [#66](https://github.com/Entreco/elchrono/issues/66) |
| Review PR | [PR #20](https://github.com/Entreco/elchrono/pull/20); issue #22 update in [PR #32](https://github.com/Entreco/elchrono/pull/32); revision 0.7 in [PR #49](https://github.com/Entreco/elchrono/pull/49); issue #38 revision 0.8 in [PR #52](https://github.com/Entreco/elchrono/pull/52); issue #44 revision 0.9 in [PR #56](https://github.com/Entreco/elchrono/pull/56); issue #46 revision 1.0 in [PR #57](https://github.com/Entreco/elchrono/pull/57); issue #47 review: [PR #59](https://github.com/Entreco/elchrono/pull/59); issue #66 review: [draft PR #70](https://github.com/Entreco/elchrono/pull/70); final exact-head acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](document-control.md#lifecycle-and-approval).

## Purpose and limits

This plan describes how the project will develop, review, verify, release, and
maintain El Chrono using the repository's existing Kotlin Multiplatform and
Compose Multiplatform structure. It applies the project controls and scoped
applicability decisions in the
[standards and documentation applicability matrix](applicability-matrix.md)
and the [document controls](document-control.md).

El Chrono is intended as a non-medical visual aid for ordinary waiting-time
support. This plan does not introduce diagnosis, treatment, medical-condition
monitoring, disability compensation, safety-critical timing, or medical
claims. The matrix and its source qualification records govern the assessed
markets and stated limitations. If actual intended use, functionality, claims,
or markets conflict with that scope, escalate and assess obligations before
affected work continues.

The M1-DOC-005 conclusion is that IEC 62304 software safety classification is
not applicable to the currently defined non-medical scope; no Class A, B, or C
is assigned. This development plan is a voluntary project control. It does not
make IEC 62304 applicable, claim conformity or certification, or establish
legal qualification for any market.

The issue #47 browser MVP is implemented in this candidate, but its build and
domain tests do not establish browser usability, accessibility, offline
behavior, timer-lifecycle recovery, or platform support. A planned activity,
control, test, platform, or output in this document is not evidence that it is
effective or has passed product verification.

## Inputs and approval boundaries

Use the applicable source at an exact, reviewable revision. A record's
presence on `main`, a closed issue, a revision number, or a derived
**Approved and effective** lifecycle state does not by itself prove required
human or specialist review. The evidence below distinguishes accepted
decisions from candidate records and unresolved evidence.

| Input | Exact source and evidence | Use in this plan |
|---|---|---|
| Project scope and engineering constraints | [Repository instructions](../../AGENTS.md), especially non-medical boundary, architecture, accessibility/reliability, privacy/music, milestones, and validation rules | Governs project boundaries and existing tooling. It is not a controlled requirements register. Reconcile this plan with any future approved requirements and applicable market assessments. |
| Standards and documentation applicability | [M1-DOC-006 revision 0.1 at its accepted PR #18 merge](https://github.com/Entreco/elchrono/blob/4e695cbdce992cedf7de2751448115aa74f2d3ab/docs/governance/applicability-matrix.md), accepted at PR #18 head `fce218225968af2e1f5b2c766186b1a1eb0c9835`. [Revision 0.2 at the preparation baseline](https://github.com/Entreco/elchrono/blob/6d2cd083155d247786fbc9d7cac44421eed03bfe/docs/governance/applicability-matrix.md) reconciles that evidence in PR #19, but its acceptance record still has the human and reviewed-head fields pending. | The accepted revision establishes the matrix's scoped decisions and staged applicability gates. Use revision 0.2 as current context, not as proof of fresh acceptance. Pending legal, privacy, accessibility, cybersecurity, and market determinations remain gates before affected work or distribution. |
| Intended use | M1-DOC-002 revision 1.0 is the current candidate on this change branch; owner review is recorded in M1-DOC-008. Final exact-head acceptance remains pending. | Preserve its ordinary-waiting, non-medical boundary and exclusions. Do not extend the qualification or claims beyond the reviewed purpose and functions. |
| Qualification and target-market assessment | M1-DOC-003 revision 0.7 assesses intended-use revision 1.0 and records the qualified reviewer's confirmation that the defined purpose remains non-medical. Final exact-head acceptance remains pending. | Use only within the reviewed EU/US scope; unresolved privacy, accessibility, cybersecurity, state/channel, and other market obligations remain follow-up gates before affected work or distribution. |
| Initial risks and risk traceability | M1-DOC-004 revision 0.7 and M1-DOC-009 revision 1.1 are current candidates. The owner reviewed the bounded v1 disposition and scenario walkthrough; focused domain tests do not establish control effectiveness, residual-risk acceptability, or product verification. | Preserve the risks and limitations. RISK-002's interruption handling is not claimed as a verified control or risk acceptance. |
| Software-safety applicability | [M1-DOC-005 revision 0.2 at the accepted PR #16 head](https://github.com/Entreco/elchrono/blob/2a8791c9a1cf76a96768e3033a88af3f51f81732/docs/safety/software-safety-classification.md), accepted at head `dc01e2ebc25f1458ef0a9a901a28128cdf7464ea`. [Revision 0.3 at the preparation baseline](https://github.com/Entreco/elchrono/blob/6d2cd083155d247786fbc9d7cac44421eed03bfe/docs/safety/software-safety-classification.md) records that acceptance in the current record. | Use the non-applicability conclusion for the defined scope only. Reassess if purpose, functions, claims, or qualification change; do not assign an IEC 62304 class by default. |
| Document and review controls | [M1-DOC-001](document-control.md) revision 2.48 records the owner-designated M1/M2/M3 review scope for issue #47. The designation is self-attested; this PR's final-head acceptance is pending. | Apply exact-revision acceptance, scope-specific review, change-impact, issue-closure, and lifecycle rules. Historical acceptance gaps remain historical and are not reconstructed. |

The product owner has approved the M1/M2/M3 review scope and the documented
M2 product/design decisions for issue #47. This permits implementation against
those decisions while the exact final PR-head acceptance remains pending.
Product verification, empirical usability/accessibility evidence, risk-control
verification, market-specific legal follow-ups, and platform support decisions
remain separate gates; issue status or owner review does not satisfy them.

## Responsibilities and review competence

The product owner assigns people to each review scope and records the
appointment and the person's confirmation of relevant competence in the
applicable controlled record. A role title, prior approval, or AI assistance
does not establish competence for another scope. One person may hold multiple
roles when competent; do not impose independence unless an applicable
requirement calls for it.

| Role | Responsibility | Current assignment/evidence |
|---|---|---|
| Product owner | Owns product boundary, markets, resources, reviewer appointments, and affected-work escalation | @Entreco, per M1-DOC-001 |
| Document owner | Maintains controlled revisions, source links, evidence gaps, findings, and impact assessment | @Entreco for this record; identify the owner for each later record |
| Author | Prepares an issue-scoped change and identifies assumptions and missing evidence | Identify the actual author; AI assistance may prepare text but cannot review or approve it |
| Specialist reviewer | Reviews within an assigned subject-matter scope, checks evidence, records findings and disposition | @Entreco is designated by the product owner as the human reviewer across applicable M1/M2/M3 scopes for issue #47 and self-attests competence. This designation does not close distinct legal, usability, platform, or verification evidence gaps. |
| Document approver | Reviews the final candidate and accepts the exact revision after required reviews and findings resolution | @Entreco, subject to the human acceptance gate and required specialist reviews |
| Milestone approver | Checks the M1 evidence baseline and authorizes handoff | Unassigned; appointment and scope remain required before [issue #8](https://github.com/Entreco/elchrono/issues/8) can close |
| Release approver | Decides whether a specific platform release is supported and ready | Product owner with required platform and specialist review; record per-platform evidence and limitations |

For issue #47, the product owner has explicitly designated @Entreco as reviewer
across applicable M1/M2/M3 disciplines. The designation is recorded as
self-attested and does not imply an independent credential audit, resolve an
open legal applicability question, establish control effectiveness, or provide
product verification.

## Development lifecycle and milestone gates

Each change starts with a tracked issue that states scope, acceptance criteria,
dependencies, relevant approved source revisions, verification, and required
review scopes. If a necessary approved input is missing, record the gap and
block approval or affected work; do not substitute a draft, assumption, or
closed issue.

| Milestone | Responsible roles | Inputs | Planned work and controlled outputs | Verification and approval gate |
|---|---|---|---|---|
| M1 — Intended use and safety foundation | Product owner; document owners; qualified reviewers for each applicability, qualification, and risk scope; M1 milestone approver | Product boundary and project instructions; approved source revisions and exact PR evidence; M1 completion criteria | Establish intended-use and market boundaries, applicability rationale, initial risks and controls, document/reviewer responsibilities, and this development plan. | Approve exact source revisions only after competent reviewers, required findings resolution, traceability, and human acceptance are recorded. Keep M1 review/handoff separate from document lifecycle status. |
| M2 — Requirements and accessible UX | Product owner/reviewer @Entreco; caregivers and representative users for future appropriately scoped usability work | Owner-reviewed M1/M2 candidate records and explicit limitations | The current issue #47 baseline records ten active requirements, the four owner-approved themes, timer states/behaviors, and caregiver/patient boundaries. | The owner approves these design/product decisions for the issue #47 implementation. Keep test criteria in the protocol and relationships in traceability. The approval does not establish representative preference, caregiver workflow usability, assistive-technology evaluation, product verification, or risk-control effectiveness. |
| M3 — Architecture and engineering foundation | Product owner; architecture, security, privacy, dependency/licensing, and platform reviewers as applicable | Owner-reviewed M1/M2 scope; module layout; applicable laws/standards decisions; dependency and provider constraints | Record architecture and dependency/SOUP assessments; M3-DOC-003 records provider feasibility only and does not select or approve a provider/platform; M3-DOC-004 records Android/iOS feasibility without establishing support. Maintain [M3-DOC-005](../architecture/dependency-inventory.md) for direct dependency provenance, licensing/maintenance evidence, and preliminary SOUP assessment; propose build/version/artifact conventions in [M3-DOC-006](../architecture/build-release-versioning.md); use the [controlled-record templates](../templates/README.md); record data flows, security/privacy design as applicable, supported-platform evidence, persistence/lifecycle boundaries, and provider constraints. | Review against owner-reviewed scope and open legal applicability gates. Resolve legal/security assessments needed by a design before affected architecture work. Reassess exact dependency graphs, provider terms, and packaged licences before distribution; resolve provider-policy eligibility questions before selecting Spotify. Keep timer domain behavior independent of rendering and music integrations. M3 records and builds do not establish platform support or release readiness. |
| M4 — Reliable timer engine | Product owner; timer-domain implementers; software-safety/risk reviewer for assigned risk scope | M2-DOC-001 revision 0.8 and M2-DOC-002 revision 1.0 contain the owner-reviewed product requirements and planned verification criteria for issue #47; exact final-PR acceptance remains pending. | Implement only the reviewed timer-domain behavior with injected controllable time sources and platform lifecycle boundaries. The v1 scope specifies every whole-minute duration from 00:01 through 24:00, sequential Hours/Minutes entry, Settings-open pause and valid-save start, no separate manual pause/resume/reset controls, and the documented interruption behavior. | Verify all 1,440 whole-minute durations and invalid configurations, approved Settings/save and completion behavior, non-negative remaining time, duplicate-completion prevention, and approved interrupted-state behavior. Do not imply timing continuity or current progress after interruption unless explicitly approved and verified; do not infer restart, sleep/background, process-termination, or device-time recovery. Update traceability and limitations, and use an injected time source rather than waiting real minutes or hours. |
| M5 — Browser MVP | Product owner/reviewer @Entreco; browser implementers; product, accessibility, usability, and risk review within assigned scope | [Requirements](../product/requirements.md) revision 1.1, [risk assessment](../safety/risk-assessment.md) revision 1.0, [test protocol](../testing/test-protocol.md) revision 1.4, [traceability](../safety/requirements-traceability-matrix.md) revision 3.6, [design](../design/accessible-screen-flows-and-theme-prototypes.md) revision 2.2, [usability plan](../usability/usability-plan.md) and [findings](../usability/usability-findings.md) revision 0.9, and [platform matrix](../architecture/platform-architecture-and-support-matrix.md) revision 0.2 | Issues #47, #65, and #67 provide the browser shell, Settings, and About; #66 completes the Timer candidate. The four approved themes use a common continuous remaining-time fraction; Clock's leading edge moves clockwise from 12 and detail levels 1-12 show duration labels around its continuous disk, while Battery, Fan, and Ring use equal segment divisions. Fan is centered and fully visible within its visualization area. At level 0 all on-screen Timer text is hidden, with non-text cues and accessible state/stale-progress descriptions. Zero duration is idle/non-counting, not completion. Settings remains adaptive, auto-saved, and integrated with About. Android CLI builds may serve only as shared-code compilation checks, not browser verification or support claims. | Run deterministic tests for duration/state boundaries and all four shapes/detail levels; build and exercise the browser target, and record actual viewport, screen-reader, reduced-motion, offline, and navigation conditions. The bounded Clock/Fan renders at compact and expanded sizes are observations, not full visual acceptance. Human review of the revised level-0 interruption warning, Clock labels, Fan layout, design/usability and risk disposition, browser no-clipping behavior, and exact target configuration remains required. An Android build does not verify M5 browser behavior or establish Android product support. M4's broader reliability/lifecycle and platform support gates remain separate. |
| M6 — Music and volume | Product owner; provider/platform, privacy, licensing, and accessibility reviewers as applicable | Approved provider choice, data-flow/privacy decisions, platform scope, and requirements; M3-DOC-003 feasibility findings are input, not approval | Add only approved provider integrations, playback behavior, patient-accessible volume, and associated data-flow/licensing records. | Verify operation with music disabled and without internet; audio/provider failures must be explicit and must not interrupt the timer or appear successful. Review terms, authorization, consent, data handling, token storage, licensing, subscriptions, autoplay/embedding, and platform limitations before implementation/release. Resolve the provider-policy eligibility questions recorded in M3-DOC-003 before selecting Spotify. |
| M7 — Mobile and desktop releases | Product owner/release approver; Android, iOS, desktop, accessibility, and provider reviewers for each shipped target | Approved support matrix, platform-specific requirements, packaged builds, verification and limitation records | Validate and package Android, iOS, and desktop targets within the agreed support matrix; prepare About/support/privacy/licence/intended-use information. | Approve each shipped platform separately after packaging, lifecycle, accessibility/input, provider support, verification, limitations, and release evidence are reviewed. Compilation alone does not establish support. |
| M8 — TV releases | Product owner; Android TV/Apple TV platform and interaction reviewers; provider/accessibility reviewers as applicable | Separately selected target/toolchain; support and input requirements; lifecycle/provider assessments | Android TV and Apple TV are primary future targets. Wear OS and watchOS are outside v1 and are not included in this release plan. | Do not claim TV support from shared/mobile builds. Validate packaging, remote/D-pad interaction, lifecycle, provider support, and usability before a platform-specific release decision. |
| M9 — Release baseline and maintenance | Product owner/release approver; assigned maintenance, legal, privacy/security, and platform reviewers as relevant | Per-platform release evidence, approved baselines, limitations, open anomalies, and current market/data-flow scope | Preserve release configuration, approved platform baselines, user-facing limitations, unresolved anomalies, maintenance ownership, and reassessment/incident procedures. | Reconcile traceability and open findings for each release; review actual market, legal, privacy, accessibility, provider, security, and platform scope with competent reviewers before relevant distribution or maintenance decisions. |

The milestones describe planned project work, not a declaration that any
feature, platform, legal assessment, review, or release is complete. A later
approved requirement or applicability decision takes precedence over a
conflicting plan detail after the resulting change has been assessed and
reviewed.

## Requirements, risks, and traceability

Maintain traceability from each approved source and requirement to relevant
risks, controls, design decisions, verification, findings, and release
limitations. M2 establishes the controlled requirement ID scheme; do not
invent requirement IDs before then. The risk IDs in M1-DOC-004 exist as
proposed entries and must not be presented as reviewed or acceptable until
their review is complete. M1-DOC-009 contains planned verification, not test
results.

For each change:

1. Link the exact source document revisions and issue acceptance criteria.
2. Identify affected intended-use statements, requirements, risks, proposed
   controls, architectures/dependencies, platforms, and user-facing claims.
3. Define measurable verification before implementation and link it to the
   relevant requirement and risk/control; distinguish test, inspection,
   analysis, and usability evidence.
4. Record the tested document/build revision, platform and environment,
   command or method, result, failures, limitations, and durable evidence link.
5. Update traceability and the foundation index when a controlled record or
   evidence link changes. A passing test does not alone establish control
   effectiveness, residual-risk acceptability, or release approval.

## Configuration and change control

All changes enter `main` through a pull request. Use the repository issue,
branch, PR, and commit naming conventions; keep the change focused and preserve
the exact reviewed head. For a controlled document, increment its revision for
substantive content changes, link approved prerequisites at commit-pinned
revisions, and record its issue and review PR. Branch/PR lifecycle transitions
alone do not require revision changes or stored status labels.

Before implementation and again before merge, assess whether the change
affects:

- intended purpose, users, environment, exclusions, or public claims;
- selected markets, states, channels, legal obligations, or standards
  applicability;
- requirements, accessibility/usability criteria, hazards, risks, controls, or
  residual-risk evidence;
- architecture, dependencies, data flows, privacy/security, providers, or
  supported platform behavior;
- verification coverage, release instructions, limitations, maintenance, or
  any prior reviewer conclusion.

Document the impact, owner, required reviewer scopes, decisions, and affected
records in the issue/PR and update controlled records as needed. If a change
conflicts with the non-medical intended purpose or makes medical-device
software/other obligations potentially applicable, escalate to the product
owner and qualified reviewers before affected work proceeds. Do not assign a
software safety class or claim conformity by default.

Record anomalies as issues with observed and expected behavior, exact build
and platform, reproduction/evidence where appropriate, affected requirements
and risks, disposition, and verification of any correction. Do not include
patient-identifiable data, credentials, or identifiable logs/screenshots.
Unresolved findings that affect an acceptance or release gate block that gate.
Non-blocking limitations require a rationale, owner, and linked follow-up; they
cannot waive a required approval.

## Verification and review

Verification methods are selected from approved requirements and risks and
include automated deterministic tests, platform-specific checks, inspection,
and appropriately planned usability review. Use the project Kotlin Toolchain
wrappers and documented CLI commands from the repository root; validate the
affected modules/platforms first and report unavailable tools or targets as
limitations. Do not claim a check passed if it was not run.

Before a PR is merge-ready:

- the final diff and exact document/code revisions are identified;
- each required reviewer is appointed, their competence is confirmed for the
  assigned scope, and they review the final candidate;
- acceptance criteria and planned verification are evidenced, findings are
  resolved or explicitly dispositioned, and traceability/index links are
  updated;
- a human records their handle and full reviewed head SHA in the PR and checks
  the human acceptance box; agents leave it unchecked; and
- each required review conversation is resolved before merge.

Any new commit changes the reviewed head and requires renewed review and human
acceptance. The human checkbox is a manual gate, not a technically enforced
GitHub approval. This plan does not claim that required branch protections are
configured or verified; see M1-DOC-001 for the verification required before
relying on them.

## Release and maintenance decisions

Release approval is per shipped platform. Before each release, confirm the
platform is in the approved support matrix; relevant requirements and risks
have evidence; accessibility and lifecycle/input behavior have been checked;
provider/audio and offline behavior are as approved; unresolved anomalies and
limitations are visible; and About/support/privacy/licence/intended-use
materials match actual behavior and evidence. Record the release commit,
version/build, platform, verification results, reviewer scopes, accepted
limitations, and release decision in the [platform release record](../templates/release-record-template.md).
Use the proposed [build/version/artifact conventions](../architecture/build-release-versioning.md)
only after their required review; they do not establish packaging, platform
support, reproducibility, or approval.

Do not claim TV or watch support because shared/mobile code compiles. Do not
claim medical benefit, medical-device status, certification, unsupported
provider playback, or unverified OS/browser support. Keep the timer usable
without music or internet and surface audio errors without interrupting timing.

For maintenance, reassess when intended purpose, functionality, claims,
markets/channels, applicable laws or standards, risks, dependencies, data
flows, or actual use changes. Assign an owner and appropriate competent
reviewers for incident, privacy/security, platform, or legal questions; record
the affected baseline, decision, corrective change, verification, and any
distribution/reporting obligation. Specific legal reporting duties depend on
the actual product role, markets, data, and event; this plan does not invent
those obligations.

## Process walkthrough: hypothetical timer-recovery change

This walkthrough exercises the planned process for a hypothetical defect where
the display appears to resume after backgrounding or restart without reliable
timing continuity. It is process evidence only: no timer recovery is
implemented, no runtime test was run, and the behavior must not be selected
without the M2 requirements and risk/UX review.

| Step | Planned action and record | Acceptance evidence / current limitation |
|---|---|---|
| 1. Intake and scope | Open a change/anomaly issue describing the observed and expected state, affected platform/build, reproduction, and whether elapsed time or saved state is uncertain. Link this plan and the applicable issue. | No actual defect or runtime observation is asserted by this example. |
| 2. Trace the risk | Link proposed [RISK-002 and HAZ-02](../safety/risk-assessment.md#risk-analysis), the interruption/recovery controls, and [planned recovery verification](../safety/risk-traceability-matrix.md). | The cited records are candidates; the risk review and controls are not approved or verified. |
| 3. Check requirements and intended use | Find or create the approved M2 requirement for backgrounding, sleep, process termination, restart, and wall-clock changes, including distinguishable interrupted state. Link intended-use limits and do not treat the timer as safety-critical. | M2 requirement IDs and recovery behavior do not yet exist. Do not invent an ID or silently choose resume, pause, or reset behavior. |
| 4. Assess impact and decide | The owner and appointed competent reviewers assess user impact, platforms, persistence/time-source assumptions, proposed control, privacy/data implications, testability, and affected documents. Escalate any conflict with intended use or applicable scope. | Record decisions and open findings in the issue/PR; this walkthrough makes no risk acceptability or legal conclusion. |
| 5. Design and implement | Implement only the reviewed requirement and design through the existing module/platform boundaries, with a controllable time source and explicit unknown/interrupted state where required by approved behavior. | No implementation or platform capability is claimed here. |
| 6. Verify and review | Run deterministic tests for lifecycle interruptions and wall-clock changes on each affected supported platform; check state presentation, negative remaining time, duplicate completion, and regression coverage. Review exact changed files and update the risk traceability matrix with results and limitations. | The tests are planned; no runtime evidence exists until executed on the approved implementation. |
| 7. Release and maintain | Resolve blocking findings, obtain required human/specialist reviews and exact-head acceptance, assess per-platform release readiness, and document release/limitations. Link the resulting release evidence and any follow-up anomaly. | No release, risk reduction, or issue closure follows from this hypothetical example. |

The issue #7 reviewer should trace this scenario from the recovery change to
the proposed risk, requirement gap, review/verification activities, anomaly
handling, and platform-specific release decision. Record the actual process
walkthrough findings and disposition in the PR. This author-prepared example
does not count as that independent human review.

## Open evidence and limitations

- M1-DOC-002 revision 1.0, M1-DOC-003 revision 0.7, M1-DOC-004 revision 0.7,
  M1-DOC-009 revision 1.1, and the current M1/M2/M3 evidence records remain
  subject to final exact-head acceptance in this PR.
- The owner has appointed themself as reviewer and M1 milestone approver for
  the current review scope. This resolves the absent-appointment blocker for
  this review; it does not supply independent credential evidence or complete
  open legal/applicability and platform determinations.
- Risk controls, residual-risk acceptability, and product verification are not
  established by the documented owner decisions or the successful build/tests.
- Browser/OS support, TV target configuration/support, and empirical
  accessibility/usability evidence remain unverified.
- Requirements, timer implementation, tests, usability findings, release
  support, and platform-specific verification described here are planned or
  absent; this document does not supply their evidence.

Resolve each gap before relying on the affected decision or closing issue #7.
Keep non-blocking limitations, owners, follow-up links, and review disposition
explicit. A merge to `main` derives the documentation lifecycle state but does
not by itself prove these reviews, verification, or M1 completion.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish the voluntary, repository-specific lifecycle plan and process walkthrough; link exact prerequisite evidence and preserve unresolved review and verification gates. |
| 0.2 | Align planned timer-engine duration verification with the M2 one-minute duration range and sequential Hours/Minutes entry requirement. |
| 0.3 | Align M2/M5 lifecycle gates with available-theme selection, caregiver-only Settings, and the proposed immediate reset/music-stop completion behavior; keep required reviews pending. |
| 0.4 | Separate M2 requirement statements, protocol acceptance criteria, and complete traceability relationships into their respective controlled records. |
| 0.5 | Reconcile the current M1 risk-record revisions and the M2 requirements/test/traceability document structure. |
| 0.6 | Update the M1 risk source revisions for the recorded v1 product decisions; preserve pending risk review, verification, and approval gates. |
| 0.7 | Link M3-DOC-003 as a feasibility input to M3/M6 while making clear it does not approve a provider, platform, or playback behavior. |
| 0.8 | Link issue #38's M3-DOC-005/006 dependency and release conventions and templates; retain provider/platform review gates and the unresolved M2 dependency. |
| 0.9 | Reconcile the M4 plan with candidate v1 duration and interruption scope; preserve the unresolved M2/reviewer gate and block affected implementation until the scope is reviewed and approved. |
| 1.0 | Reconcile M5 with the candidate v1 records, retain the exact roadmap mismatch and pending reviews, and block affected implementation until the product/reviewer gate and browser-support baseline are resolved. |
| 1.1 | Record owner approval of the M1/M2/M3 review scope for issue #47, update current source revisions and v1 decisions, unblock implementation from the absent-reviewer issue, and retain exact-head acceptance, product-verification, usability, and platform-support limitations. |
| 1.2 | Align M4/M5 planning with the core/counter and feature-owned UI architecture; record focused domain-test evidence without treating it as M4 completion or product verification. |
| 1.3 | Reconcile the current plan with D-09, the adaptive supporting-pane and keypad implementation, current M1/M2 source revisions, and remaining UI/input/platform verification gaps. |
| 1.4 | Record the current test/build results and Skiko Wasm test-runner limitation; retain the product verification and platform-support gates. |
| 1.5 | Update M5 scope and controlled-source revisions for issue #66's responsive Timer, text-free detail-0 states, segment mapping, and idle 00:00; retain browser and human-review gates and distinguish Android compilation from browser verification. |
| 1.6 | Align M5's current test, traceability, and design inputs with their updated revisions and bounded browser-render evidence; retain the unresolved acceptance and human-review gates. |
| 1.7 | Align M5 sources and detail mapping with the demo-matching Clock labels and centered Fan geometry; record bounded compact/expanded renders while retaining full verification and human-review gates. |
