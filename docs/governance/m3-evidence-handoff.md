# M3 evidence gate review and M4 handoff

| Field | Value |
|---|---|
| Document ID | M3-DOC-008 |
| Revision | 0.3 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | M3 gate/integration; cross-platform architecture; browser/desktop; Android/iOS; TV input; provider, privacy, licensing/policy, lifecycle/audio, build/CI/configuration, dependency/SOUP, and documentation-control scopes: @Entreco, designated by the product owner on 2026-11-19; exact-head acceptance pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#40](https://github.com/Entreco/elchrono/issues/40) |
| Review PR | Prior baseline: [PR #55](https://github.com/Entreco/elchrono/pull/55); current exact-head review: pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](document-control.md#lifecycle-and-approval).

## Review outcome

**Owner-approved M3 review scope; exact final PR-head acceptance pending.**
On 2026-11-19, @Entreco confirmed they are the human reviewer for all
applicable disciplines and approved the M1, M2, and M3 reviews with their
evidence limitations. M2-DOC-007 revision 0.9 records the M2 design baseline
approval for #47. Android TV and Apple TV are primary future targets, while
Wear OS and watchOS are outside v1. M3-DOC-007 revision 0.2 retains Kotlin
Multiplatform/Compose without claiming platform support.

The owner designation is self-attested; no independent credential audit is
claimed. The source PR review metadata and CI evidence below are historical.
This M3 integration record is updated for the current review, but its final
human acceptance must still identify the exact head of the current PR. This
does not convert evidence gaps into verification results or close a release
gate.

The source evidence table below describes the historical snapshot inspected on
2026-11-18. The current owner disposition is recorded above and in the current
PR; it does not change those historical results or provide missing platform,
privacy, licensing, accessibility, dependency, verification, or release
evidence.

## Evidence baseline

The source revisions below are pinned to their merged PR heads. Their presence
on `main` establishes the repository documentation baseline, not completed
specialist review or human acceptance.

| M3 deliverable | Evidence inspected | Observed outcome and limitation |
|---|---|---|
| Architecture decision and platform support matrix | [M3-DOC-007, revision 0.2](../architecture/platform-architecture-and-support-matrix.md); prior baseline [revision 0.1](https://github.com/Entreco/elchrono/blob/e6c121ec1724f6c6851decae7a5f215b65cdf36f/docs/architecture/platform-architecture-and-support-matrix.md), [PR #54](https://github.com/Entreco/elchrono/pull/54) | Retains Kotlin Multiplatform/Compose; records Android TV/Apple TV as primary future targets and Wear/watch outside v1. It does not establish a release support baseline, minimum OS/browser versions, or configured TV targets. |
| Browser and desktop feasibility | [M3-DOC-001, revision 0.2](https://github.com/Entreco/elchrono/blob/345c0acf413cf55bd521d49fe0123e388ad4bc9c/docs/architecture/platform-feasibility-findings.md), [PR #42](https://github.com/Entreco/elchrono/pull/42) | Browser and desktop starter-shell demonstrations were limited to the recorded macOS/Chrome environment. The default browser port conflict, other hosts, offline behavior, accessibility/input, and minimum versions remain unresolved. |
| TV and watch feasibility | [M3-DOC-002, revision 0.2](https://github.com/Entreco/elchrono/blob/54d7c7f7711f0b5b7ae939eb2dc24ea25bb4705c/docs/architecture/tv-watch-feasibility-findings.md), [PR #49](https://github.com/Entreco/elchrono/pull/49) | Android TV and Apple TV are primary future targets but remain unconfigured. Wear OS/watchOS are outside v1. No TV/watch interaction, packaging, lifecycle, provider behavior, or usability is demonstrated. |
| Provider and background-execution feasibility | [M3-DOC-003, revision 0.1](https://github.com/Entreco/elchrono/blob/54d7c7f7711f0b5b7ae939eb2dc24ea25bb4705c/docs/architecture/audio-provider-feasibility-findings.md), [PR #49](https://github.com/Entreco/elchrono/pull/49) | Official YouTube/Spotify and platform constraints, data-flow implications, and testability limits are documented. No provider, playback, credentials, tokens, integration, or platform support is selected or verified; provider/privacy/licensing reviews remain pending. |
| Android and iOS feasibility | [M3-DOC-004, revision 0.3](https://github.com/Entreco/elchrono/blob/4766634e45ffa74203761ea42f8ba6430ec056c3/docs/architecture/android-ios-platform-feasibility-findings.md), [PR #50](https://github.com/Entreco/elchrono/pull/50) | Android build/AAB packaging and an iOS simulator shell run are recorded. Android launch, physical-device behavior, signing, accessibility, lifecycle, offline operation, and audio remain unverified. These demonstrations are not support commitments. |
| Reproducible developer setup and automated checks | [README setup and CI guidance](https://github.com/Entreco/elchrono/blob/d537cc321429a0339d0606cc1a071021c8af6b87/README.md), [CI workflow](https://github.com/Entreco/elchrono/blob/d537cc321429a0339d0606cc1a071021c8af6b87/.github/workflows/ci.yml), [PR #51](https://github.com/Entreco/elchrono/pull/51), [observed CI run](https://github.com/Entreco/elchrono/actions/runs/44389945776) | The Kotlin wrapper, repository configuration, local commands, and Linux workflow are documented. The observed workflow passed module discovery, JVM and browser Wasm builds, common/localization JVM tests, and checks. It does not provision Android or validate Android/iOS, TV/watch, physical devices, or other desktop OSs. |
| Dependency/SOUP inventory and release conventions | [M3-DOC-005, revision 0.1](https://github.com/Entreco/elchrono/blob/54d28b79fcf641aea78da182675d7188fb89f7bb/docs/architecture/dependency-inventory.md) and [M3-DOC-006, revision 0.1](https://github.com/Entreco/elchrono/blob/54d28b79fcf641aea78da182675d7188fb89f7bb/docs/architecture/build-release-versioning.md), [PR #52](https://github.com/Entreco/elchrono/pull/52) | Direct dependency declarations and preliminary assessments plus proposed build/version/artifact conventions are recorded. Transitive distributed-artifact licence review, maintenance/licensing actions, competent dependency review, and release-baseline decisions remain open. |
| Reusable documentation templates | [Template index](https://github.com/Entreco/elchrono/blob/54d28b79fcf641aea78da182675d7188fb89f7bb/docs/templates/README.md), [PR #52](https://github.com/Entreco/elchrono/pull/52) | Seven starting templates are present. They are not completed evidence, approvals, or product requirements. |
| M2 prerequisite and traceability | [M2-DOC-007, revision 0.9](m2-evidence-handoff.md), [M2-DOC-003, revision 2.8](../safety/requirements-traceability-matrix.md) | The product owner approved the M2 design baseline for #47 while retaining usability and product-verification limitations. Final current-PR-head acceptance remains pending. |

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

## M3 completion-gate cross-check (historical snapshot)

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
| M2 predecessor gate | At the reviewed snapshot, M2-DOC-007 said M2 remained not approved and recorded outstanding review, usability, risk, and verification issues. | **Historical outcome: blocking dependency unresolved at that snapshot. See the current review disposition above; do not infer milestone completion.** |

The owner approves the M3 review gate for issue #47 as an engineering
foundation with the limitations retained above. This does not declare the M3
milestone complete or establish platform support, provider approval, or release
readiness. The historical table above records that M2 was unresolved at the
snapshot; the owner has since approved the scoped M2 baseline for #47, so the
missing-reviewer dependency no longer blocks this implementation. Final
exact-head acceptance remains pending in the current PR. A successful build,
merged document, closed work item, or this integration record cannot establish
platform support or release readiness.

## M4 handoff and follow-up

| Follow-up | Owner / issue | Required action and evidence |
|---|---|---|
| Record final owner acceptance of the M2 review package before relying on its product inputs | @Entreco; [M2-DOC-007](m2-evidence-handoff.md), [M2 issue #26](https://github.com/Entreco/elchrono/issues/26) | The owner has approved the documented M2 design baseline for #47. Record acceptance against the final PR head; retain usability, risk, and verification limitations. |
| Reconcile timer-engine scope with approved requirements | @Entreco; [#44](https://github.com/Entreco/elchrono/issues/44) | Establish the accepted timer-engine scope against reviewed requirements, risks, architecture, and test criteria. Do not invent pause/reset, completion, caregiver protection, volume, or interruption behavior. |
| Implement and verify the timer engine | @Entreco; [#45](https://github.com/Entreco/elchrono/issues/45) | After prerequisite reviews, implement domain timing independently from rendering/music; verify allowed durations and boundaries with an injected time source and preserve explicit lifecycle limitations. |
| Complete and review the browser MVP | @Entreco; [#47](https://github.com/Entreco/elchrono/issues/47) | Implement Main/Settings/About against the owner-approved v1 requirements, run focused tests, and preserve the explicit gaps. Do not claim theme preference or broader platform support. |
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
| 0.2 | Record the owner's M1/M2/M3 reviewer designation and review disposition, align TV/watch scope and current M2/M3 candidates, and retain exact-head acceptance and platform/release limitations. |
| 0.3 | Record owner approval of the M3 review gate for issue #47, clarify that the historical M2 blocker is resolved for this scoped implementation, and preserve the distinction from milestone completion, platform support, and release readiness. |
