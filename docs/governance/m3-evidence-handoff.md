# M3 evidence gate review and M4 handoff

| Field | Value |
|---|---|
| Document ID | M3-DOC-008 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | M3 gate/integration; cross-platform architecture; browser/desktop; Android/iOS; TV/watch tooling and interaction; provider, privacy, licensing/policy, and lifecycle/audio; build/CI/configuration; dependency/SOUP; documentation control; appointments and competence confirmation pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#40](https://github.com/Entreco/elchrono/issues/40) |
| Review PR | Pending; link this record to its review PR when opened |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](document-control.md#lifecycle-and-approval).

## Review outcome

**NOT APPROVED — the M3 completion gate is not ready to close.** M3 records,
setup, templates, dependency assessment, and an automated CI run are present.
However, the required M3 specialist reviews and exact-head human acceptance are
not evidenced, and the M2 predecessor remains explicitly not approved in
[M2-DOC-007, revision 0.8](https://github.com/Entreco/elchrono/blob/6248cb5afd8724b8f8f9e4ec5250e4c3778a438b/docs/governance/m2-evidence-handoff.md).
The M3 source PRs being merged and issues #33–#39 being closed do not establish
review completion or milestone approval. Do not close M3 on this evidence
alone.

This is an integration check of the records, stated revisions, linked PR
descriptions, and GitHub review metadata inspected on 2026-11-18. It is not a
qualified architecture, platform, privacy, licensing, dependency, accessibility,
or software-safety review. This record does not provide missing review,
verification, or approval evidence.

## Evidence baseline

The source revisions below are pinned to their merged PR heads. Their presence
on `main` establishes the repository documentation baseline, not completed
specialist review or human acceptance.

| M3 deliverable | Evidence inspected | Observed outcome and limitation |
|---|---|---|
| Architecture decision and platform support matrix | [M3-DOC-007, revision 0.1](https://github.com/Entreco/elchrono/blob/e6c121ec1724f6c6851decae7a5f215b65cdf36f/docs/architecture/platform-architecture-and-support-matrix.md), [PR #54](https://github.com/Entreco/elchrono/pull/54) | Retains the configured Kotlin Multiplatform/Compose approach for further evaluation. It does not establish a release support baseline, minimum OS/browser versions, or TV/watch targets. Its required reviews and human acceptance remain pending. |
| Browser and desktop feasibility | [M3-DOC-001, revision 0.2](https://github.com/Entreco/elchrono/blob/345c0acf413cf55bd521d49fe0123e388ad4bc9c/docs/architecture/platform-feasibility-findings.md), [PR #42](https://github.com/Entreco/elchrono/pull/42) | Browser and desktop starter-shell demonstrations were limited to the recorded macOS/Chrome environment. The default browser port conflict, other hosts, offline behavior, accessibility/input, and minimum versions remain unresolved. |
| TV and watch feasibility | [M3-DOC-002, revision 0.2](https://github.com/Entreco/elchrono/blob/54d7c7f7711f0b5b7ae939eb2dc24ea25bb4705c/docs/architecture/tv-watch-feasibility-findings.md), [PR #49](https://github.com/Entreco/elchrono/pull/49) | Android TV, Apple TV, Wear OS, and watchOS remain unconfigured feasibility candidates. No remote/watch interaction, packaging, lifecycle, provider behavior, or usability is demonstrated. Do not present them as supported products. |
| Provider and background-execution feasibility | [M3-DOC-003, revision 0.1](https://github.com/Entreco/elchrono/blob/54d7c7f7711f0b5b7ae939eb2dc24ea25bb4705c/docs/architecture/audio-provider-feasibility-findings.md), [PR #49](https://github.com/Entreco/elchrono/pull/49) | Official YouTube/Spotify and platform constraints, data-flow implications, and testability limits are documented. No provider, playback, credentials, tokens, integration, or platform support is selected or verified; provider/privacy/licensing reviews remain pending. |
| Android and iOS feasibility | [M3-DOC-004, revision 0.3](https://github.com/Entreco/elchrono/blob/4766634e45ffa74203761ea42f8ba6430ec056c3/docs/architecture/android-ios-platform-feasibility-findings.md), [PR #50](https://github.com/Entreco/elchrono/pull/50) | Android build/AAB packaging and an iOS simulator shell run are recorded. Android launch, physical-device behavior, signing, accessibility, lifecycle, offline operation, and audio remain unverified. These demonstrations are not support commitments. |
| Reproducible developer setup and automated checks | [README setup and CI guidance](https://github.com/Entreco/elchrono/blob/d537cc321429a0339d0606cc1a071021c8af6b87/README.md), [CI workflow](https://github.com/Entreco/elchrono/blob/d537cc321429a0339d0606cc1a071021c8af6b87/.github/workflows/ci.yml), [PR #51](https://github.com/Entreco/elchrono/pull/51), [observed CI run](https://github.com/Entreco/elchrono/actions/runs/44389945776) | The Kotlin wrapper, repository configuration, local commands, and Linux workflow are documented. The observed workflow passed module discovery, JVM and browser Wasm builds, common/localization JVM tests, and checks. It does not provision Android or validate Android/iOS, TV/watch, physical devices, or other desktop OSs. |
| Dependency/SOUP inventory and release conventions | [M3-DOC-005, revision 0.1](https://github.com/Entreco/elchrono/blob/54d28b79fcf641aea78da182675d7188fb89f7bb/docs/architecture/dependency-inventory.md) and [M3-DOC-006, revision 0.1](https://github.com/Entreco/elchrono/blob/54d28b79fcf641aea78da182675d7188fb89f7bb/docs/architecture/build-release-versioning.md), [PR #52](https://github.com/Entreco/elchrono/pull/52) | Direct dependency declarations and preliminary assessments plus proposed build/version/artifact conventions are recorded. Transitive distributed-artifact licence review, maintenance/licensing actions, competent dependency review, and release-baseline decisions remain open. |
| Reusable documentation templates | [Template index](https://github.com/Entreco/elchrono/blob/54d28b79fcf641aea78da182675d7188fb89f7bb/docs/templates/README.md), [PR #52](https://github.com/Entreco/elchrono/pull/52) | Seven starting templates are present. They are not completed evidence, approvals, or product requirements. |
| M2 prerequisite and traceability | [M2-DOC-007, revision 0.8](https://github.com/Entreco/elchrono/blob/6248cb5afd8724b8f8f9e4ec5250e4c3778a438b/docs/governance/m2-evidence-handoff.md), [M2-DOC-003, revision 2.5](https://github.com/Entreco/elchrono/blob/e6c121ec1724f6c6851decae7a5f215b65cdf36f/docs/safety/requirements-traceability-matrix.md), [PR #54](https://github.com/Entreco/elchrono/pull/54) | The M2 handoff explicitly says the M2 gate remains not approved, with exact-revision review, usability/risk disposition, and verification gaps. M3 does not clear that dependency. The traceability matrix links M3-DOC-007; no product requirement or risk is asserted approved by this integration record. |

## Review evidence and findings

The inspected GitHub review metadata for PRs [#42](https://github.com/Entreco/elchrono/pull/42),
[#43](https://github.com/Entreco/elchrono/pull/43),
[#49](https://github.com/Entreco/elchrono/pull/49),
[#50](https://github.com/Entreco/elchrono/pull/50),
[#51](https://github.com/Entreco/elchrono/pull/51),
[#52](https://github.com/Entreco/elchrono/pull/52), and
[#54](https://github.com/Entreco/elchrono/pull/54) showed no submitted formal
reviews or review comments. Their PR descriptions have the human-acceptance
checkbox checked while the accepting human and reviewed full SHA fields remain
pending. Those entries do not establish exact-head human acceptance and are
process discrepancies under the document controls. PR #54's Linux CI check
passed; passing CI is not a review.

PR [#53](https://github.com/Entreco/elchrono/pull/53) is closed without merge.
Its description records the successful CI run from PR #51, but PR #53 itself
does not add merged evidence. No reviewer findings or dispositions for the
M3 source records are evidenced by the inspected PR metadata. Reviewers must
record actual scopes, findings/dispositions, and limitations against the final
candidate; the human acceptance in this record's review PR must identify the
accepting human and full reviewed head SHA. Agents must leave that acceptance
unchecked.

## M3 completion-gate cross-check

The gate items follow the [M3 milestone definition](https://github.com/Entreco/elchrono/milestone/3).
“Evidence present” does not mean reviewed, approved, supported, or verified.

| Completion-gate item | Evidence and review check | Outcome |
|---|---|---|
| Architecture decision record and platform support matrix | M3-DOC-007 records the architecture decision and platform matrix. It limits the decision to continued evaluation of configured targets; TV/watch remain feasibility-only and minimum versions/support baselines are not set. Its specialist reviews and human acceptance are not evidenced. | **Record present; review gate not met.** |
| Platform and provider feasibility findings | M3-DOC-001–004 preserve observed prototypes, official-provider constraints, assumptions, and limitations. Runtime/platform evidence is incomplete by design, and required specialist review/acceptance is not evidenced. | **Findings recorded; reviews and platform support decisions remain open.** |
| Reproducible setup | The checked-in wrapper and README document setup/configuration and reproducible commands. The review record notes a successful first-use bootstrap and local checks; CI evidence is linked above. | **Setup evidence present for the documented environment; other hosts and platform SDK requirements remain limited as documented.** |
| Working automated checks | The observed Linux CI run passed module discovery, JVM/browser Wasm builds, common/localization JVM tests, and checks. The workflow excludes Android/iOS builds and TV/watch targets. | **Passed for the workflow's stated scope; this is not cross-platform product verification.** |
| Dependency/SOUP inventory and assessment | M3-DOC-005 contains a direct dependency inventory and preliminary assessment, with transitive licensing and other review actions open. | **Initial inventory present; assessment/review is incomplete.** |
| Documentation templates | Seven reusable templates are indexed in the repository. | **Templates present; they are structures, not evidence or approval.** |
| Data-flow and patient-data boundary | M3-DOC-003 describes provider data-flow and privacy implications. No patient account, identifiable patient data, telemetry, provider credentials, or tokens were introduced by the M3 work reviewed here. | **Boundaries documented for the feasibility scope; no integration/data-flow implementation is approved.** |
| M2 predecessor gate | M2-DOC-007 says M2 remains not approved and records outstanding review, usability, risk, and verification issues. | **Blocking dependency unresolved; do not rely on M3 evidence as M2 approval.** |

The M3 evidence package covers the named deliverables, and the documented CI
scope has passed. The M3 completion gate as a whole remains **not approved**
because required source reviews and exact-head acceptance are not evidenced
and the M2 dependency is unresolved. A successful build, merged document,
closed work item, or this agent integration check cannot substitute for those
gates.

## M4 handoff and follow-up

| Follow-up | Owner / issue | Required action and evidence |
|---|---|---|
| Resolve the M2 prerequisite before relying on product inputs | @Entreco; [M2-DOC-007](m2-evidence-handoff.md), [M2 issue #26](https://github.com/Entreco/elchrono/issues/26) | Obtain required exact-revision product, usability/accessibility, and risk review; disposition findings and limitations; resolve the M2 gate under its own review controls. M2 issue closure or PR merge alone does not clear the documented gate. |
| Reconcile timer-engine scope with approved requirements | @Entreco; [#44](https://github.com/Entreco/elchrono/issues/44) | Establish the accepted timer-engine scope against reviewed requirements, risks, architecture, and test criteria. Do not invent pause/reset, completion, caregiver protection, volume, or interruption behavior. |
| Implement and verify the timer engine | @Entreco; [#45](https://github.com/Entreco/elchrono/issues/45) | After prerequisite reviews, implement domain timing independently from rendering/music; verify allowed durations and boundaries with an injected time source and preserve explicit lifecycle limitations. |
| Reconcile and implement the browser MVP | @Entreco; [#46](https://github.com/Entreco/elchrono/issues/46), [#47](https://github.com/Entreco/elchrono/issues/47), [#48](https://github.com/Entreco/elchrono/issues/48) | Align scope to approved v1 requirements, then implement Main/Settings/About and verify browser behavior/accessibility. Do not claim theme preference, broader platform support, or unverified behavior. |
| Track M6 music and volume work | @Entreco; no M6 issue was present in the inspected issue inventory | Create/link an owned issue before work is treated as tracked. Use M3-DOC-003 as feasibility input only; resolve provider, licensing/policy, privacy/data-flow, platform, and failure-handling decisions before integration. Keep timer operation independent of music and internet. |

These are handoff actions, not authorization to proceed past unresolved
requirements, risk, privacy, licensing, or usability reviews. El Chrono remains
non-medical visual waiting-time support; this record does not extend intended
use to diagnosis, treatment, monitoring, or safety-critical medical timing.
Do not present TV/watch as supported because mobile or shared code builds.

## Review limitations

This integration review checked the linked records, their stated revisions,
selected PR descriptions, GitHub review metadata, and the documented CI result.
It did not rerun historical platform tests, inspect CI runner artifacts, repeat
provider documentation research, determine legal applicability, validate
dependency licences, test product behavior, establish reviewer competence, or
approve residual risk. Record these as gaps rather than inferred results.

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record the M3 evidence baseline, actual review gaps, completion-gate outcome, M2 dependency, and tracked M4/M5/M6 handoff for issue #40. |
