# Software development plan

| Field | Value |
|---|---|
| Document ID | M1-DOC-007 |
| Revision | 0.8 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Software development/lifecycle reviewer: unassigned; software-safety reviewer for risk-process scope: unassigned; other review scopes as assigned below |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#7](https://github.com/Entreco/elchrono/issues/7); duration-verification update in [#22](https://github.com/Entreco/elchrono/issues/22); M3 provider feasibility update in [#36](https://github.com/Entreco/elchrono/issues/36); dependency/release documentation in [#38](https://github.com/Entreco/elchrono/issues/38) |
| Review PR | [PR #20](https://github.com/Entreco/elchrono/pull/20); issue #22 update in [PR #32](https://github.com/Entreco/elchrono/pull/32); revision 0.7 in [PR #49](https://github.com/Entreco/elchrono/pull/49); issue #38 update pending |

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

The current application is a starter screen, not an implemented timer. A
planned activity, control, test, platform, or output in this document is not
evidence that it exists, is effective, or has passed verification.

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
| Intended use | M1-DOC-002 revision 0.8 is the current candidate on this change branch; its exact-revision approval evidence is unresolved. The assessed revision 0.6 is cited at commit `9b5054691ae14a08fcbf591a6b0f073e49c54b79`. | Preserve its ordinary-waiting, non-medical boundary and exclusions as proposed scope. Do not describe the current intended-use candidate as approved or use it as an approved input until its required acceptance is evidenced. |
| Qualification and target-market assessment | [M1-DOC-003 revision 0.6 at the preparation baseline](https://github.com/Entreco/elchrono/blob/6d2cd083155d247786fbc9d7cac44421eed03bfe/docs/regulatory/qualification-and-classification.md) records a qualified EU/US conclusion against M1-DOC-002 revision 0.6. Its human acceptance remains pending. | Use only within the reviewed scope; do not extend it to unselected states, channels, functions, or markets. Resolve exact-revision acceptance and assigned applicability follow-ups before affected work or distribution. |
| Initial risks and risk traceability | M1-DOC-004 revision 0.5 and M1-DOC-009 revision 0.7 are candidates in PR #32. The required risk walkthrough/review is pending; controls, residual risks, and verification are not established. | Treat listed risks and controls as proposed. RISK-002 is used for the process walkthrough below, not as an approved risk disposition or proof of a verified recovery control. |
| Software-safety applicability | [M1-DOC-005 revision 0.2 at the accepted PR #16 head](https://github.com/Entreco/elchrono/blob/2a8791c9a1cf76a96768e3033a88af3f51f81732/docs/safety/software-safety-classification.md), accepted at head `dc01e2ebc25f1458ef0a9a901a28128cdf7464ea`. [Revision 0.3 at the preparation baseline](https://github.com/Entreco/elchrono/blob/6d2cd083155d247786fbc9d7cac44421eed03bfe/docs/safety/software-safety-classification.md) records that acceptance in the current record. | Use the non-applicability conclusion for the defined scope only. Reassess if purpose, functions, claims, or qualification change; do not assign an IEC 62304 class by default. |
| Document and review controls | [M1-DOC-001](document-control.md) is revised in this issue #38 candidate to index M3-DOC-005/006 and the reusable templates. Its historical acceptance identity/head and specialist-review gaps remain unresolved. | Apply its exact-revision, reviewer-competence, PR acceptance, change-impact, issue-closure, and lifecycle rules. Review the exact M1-DOC-001 candidate head with this change; do not represent historical evidence gaps as resolved by this plan. |

Approval of M1-DOC-007 must not precede approved prerequisite evidence.
M1-DOC-002, M1-DOC-003, M1-DOC-004, and M1-DOC-009 currently have the
acceptance or competent-review gaps stated above. This candidate may be
prepared for review in advance, but the affected approval and issue-closure
gates remain blocked until those source records are reconciled at exact
revisions. Issue status alone does not waive this requirement.

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
| Specialist reviewer | Reviews within an assigned subject-matter scope, checks evidence, records findings and disposition | Assign as required by the applicability matrix. The software development/lifecycle reviewer for this plan and the software-safety reviewer for the issue #7 process walkthrough are unassigned. |
| Document approver | Reviews the final candidate and accepts the exact revision after required reviews and findings resolution | @Entreco, subject to the human acceptance gate and required specialist reviews |
| Milestone approver | Checks the M1 evidence baseline and authorizes handoff | Unassigned; appointment and scope remain required before [issue #8](https://github.com/Entreco/elchrono/issues/8) can close |
| Release approver | Decides whether a specific platform release is supported and ready | Product owner with required platform and specialist review; record per-platform evidence and limitations |

The regulatory qualification and software-safety appointments recorded for
M1-DOC-003 and M1-DOC-005 are limited to those records' stated scopes. They do
not automatically appoint the same person to review this lifecycle plan,
M1-DOC-004 risk controls, privacy, accessibility, cybersecurity, or another
legal applicability question.

## Development lifecycle and milestone gates

Each change starts with a tracked issue that states scope, acceptance criteria,
dependencies, relevant approved source revisions, verification, and required
review scopes. If a necessary approved input is missing, record the gap and
block approval or affected work; do not substitute a draft, assumption, or
closed issue.

| Milestone | Responsible roles | Inputs | Planned work and controlled outputs | Verification and approval gate |
|---|---|---|---|---|
| M1 — Intended use and safety foundation | Product owner; document owners; qualified reviewers for each applicability, qualification, and risk scope; M1 milestone approver | Product boundary and project instructions; approved source revisions and exact PR evidence; M1 completion criteria | Establish intended-use and market boundaries, applicability rationale, initial risks and controls, document/reviewer responsibilities, and this development plan. | Approve exact source revisions only after competent reviewers, required findings resolution, traceability, and human acceptance are recorded. Keep M1 review/handoff separate from document lifecycle status. |
| M2 — Requirements and accessible UX | Product owner; requirements/accessibility reviewer; caregivers and representative users for appropriately scoped usability work | Accepted M1 inputs; product requirements and accessibility guidance; reviewed risk scenarios | Establish stable requirement IDs, test-protocol acceptance criteria, complete requirements traceability, timer states/behaviors, accessibility and usability criteria, theme review, and caregiver/patient interaction boundaries. | Keep requirement statements in the register, test criteria in the protocol, and requirement/source/risk/design/usability/test/evidence relationships in the traceability matrix. Review requirements against intended use and risks. Validate theme and interaction assumptions under appropriate consent and safeguards. Do not infer preferences or introduce unresolved pause/reset, volume, completion-sound, caregiver-protection-mechanism, or recovery decisions implicitly. Review the proposed immediate reset/music-stop completion behavior. |
| M3 — Architecture and engineering foundation | Product owner; architecture, security, privacy, dependency/licensing, and platform reviewers as applicable | Approved M1/M2 records; module layout; applicable laws/standards decisions; dependency and provider constraints | Record architecture and dependency/SOUP assessments; M3-DOC-003 records provider feasibility only and does not select or approve a provider/platform; M3-DOC-004 records Android/iOS feasibility without establishing support. Maintain [M3-DOC-005](../architecture/dependency-inventory.md) for direct dependency provenance, licensing/maintenance evidence, and preliminary SOUP assessment; propose build/version/artifact conventions in [M3-DOC-006](../architecture/build-release-versioning.md); use the [controlled-record templates](../templates/README.md); record data flows, security/privacy design as applicable, supported-platform evidence, persistence/lifecycle boundaries, and provider constraints. | Review against approved inputs and open legal applicability gates. Resolve legal/security assessments needed by a design before affected architecture work. Reassess exact dependency graphs, provider terms, and packaged licences before distribution; resolve provider-policy eligibility questions before selecting Spotify. Keep timer domain behavior independent of rendering and music integrations. M3 work does not clear the open M2 gate. |
| M4 — Reliable timer engine | Product owner; timer-domain implementers; software-safety/risk reviewer for assigned risk scope | Approved timer requirements, architecture, risk/control links, and test criteria | Implement approved timer-domain behavior with injected controllable time sources and platform lifecycle boundaries. | Verify all 1,440 whole-minute durations from 1 minute through 24 hours, sequential Hours/Minutes entry and invalid duration configurations, pause/resume and completion boundaries, non-negative remaining time, duplicate completion prevention, and approved interruption behavior. Do not wait real minutes or hours for deterministic tests. |
| M5 — Browser MVP | Product owner; browser implementers; design/localization and accessibility reviewers | Approved requirements, design resources, localization, and browser support scope | Implement the browser experience and the available themes using shared design/localization resources and accessible semantics. | Verify consistent remaining-time meaning across themes, running/paused/interrupted distinctions, immediate reset and music stop at completion, readable contrast, no color-only communication, keyboard and screen-reader behavior, and usability findings. Do not claim a theme is preferred without evidence. |
| M6 — Music and volume | Product owner; provider/platform, privacy, licensing, and accessibility reviewers as applicable | Approved provider choice, data-flow/privacy decisions, platform scope, and requirements; M3-DOC-003 feasibility findings are input, not approval | Add only approved provider integrations, playback behavior, patient-accessible volume, and associated data-flow/licensing records. | Verify operation with music disabled and without internet; audio/provider failures must be explicit and must not interrupt the timer or appear successful. Review terms, authorization, consent, data handling, token storage, licensing, subscriptions, autoplay/embedding, and platform limitations before implementation/release. Resolve the provider-policy eligibility questions recorded in M3-DOC-003 before selecting Spotify. |
| M7 — Mobile and desktop releases | Product owner/release approver; Android, iOS, desktop, accessibility, and provider reviewers for each shipped target | Approved support matrix, platform-specific requirements, packaged builds, verification and limitation records | Validate and package Android, iOS, and desktop targets within the agreed support matrix; prepare About/support/privacy/licence/intended-use information. | Approve each shipped platform separately after packaging, lifecycle, accessibility/input, provider support, verification, limitations, and release evidence are reviewed. Compilation alone does not establish support. |
| M8 — TV and watch releases | Product owner; TV/watch platform and interaction reviewers; provider/accessibility reviewers as applicable | Separately selected target/toolchain; support and input requirements; lifecycle/provider assessments | Evaluate reserved Android TV, Apple TV, Wear OS, and watchOS targets only after their toolchains and platform plans are selected. | Do not claim support from Android/iOS builds. Validate packaging, remote/D-pad or watch interaction, lifecycle, audio/provider support, and any companion-app requirement before a platform-specific release decision. |
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

- M1-DOC-002 revision 0.8 and M1-DOC-003 revision 0.6 do not yet have
  evidenced exact-revision human acceptance in their records.
- M1-DOC-004 revision 0.5 and M1-DOC-009 revision 0.7 remain candidates; the
  required risk walkthrough/review is pending, and no proposed control,
  residual risk, or verification result is established.
- M1-DOC-006 revision 0.1 is the exact accepted applicability-matrix input.
  Revision 0.2's reconciliation PR #19 is merged, but the record still has
  pending human identity and reviewed-head fields; do not infer fresh
  acceptance. Its legal/privacy/accessibility/cybersecurity follow-up gates
  remain open as stated in the matrix.
- The software development/lifecycle reviewer for this plan, the risk-process
  reviewer, and the M1 milestone approver remain unassigned. The product owner
  must assign reviewers and confirm competence for the actual scope through
  the document-control process in [issue #1](https://github.com/Entreco/elchrono/issues/1).
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
