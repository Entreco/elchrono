# Platform release record — [platform and product version]

| Field | Value |
|---|---|
| Document ID | [reserved ID] |
| Revision | [numeric revision] |
| Document owner | [owner] |
| Author | [actual author(s)] |
| Required reviewers | [platform, verification, accessibility, privacy/security, legal/provider scopes as applicable] |
| Approver | [release approver for this platform] |
| Related issue | [issue link] |
| Review PR | [PR link, when opened] |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Release identity

| Field | Value |
|---|---|
| Product version | [version] |
| Platform build number | [number, unique/monotonic for this platform identity] |
| Platform / architecture / supported configuration | [exact scope; link approved support matrix] |
| Application/package identity and format | [identifier and actual format] |
| Source commit | [full reviewed SHA] |
| Toolchain / SDK / build variant | [observed versions/configuration] |
| Artifact reference | [path or distribution reference] |
| SHA-256 | [digest of exact artifact bytes] |

## Release verification and traceability

| Requirement/risk | Test/protocol reference | Exact command and environment | Result/evidence | Limitation/anomaly |
|---|---|---|---|---|
| [stable ID] | [case ID] | [command, build, target] | [observed outcome/link] | [open item or none with evidence] |

## Platform review and limitations

[Record packaging/install/signing, lifecycle, input/accessibility, privacy,
provider/audio/offline behavior where applicable, user-facing information,
known limitations, and review findings. Do not claim unsupported platforms,
medical benefit, certification, or unverified OS/browser support.]

## Release decision

| Scope | Decision and rationale | Human approver | Evidence link |
|---|---|---|---|
| [this platform and artifact] | [approved / not approved / deferred with rationale] | [person and scope] | [PR/release record] |

Keep PR human acceptance in the PR description. This record does not replace
that acceptance or the required specialist review.

## Revision history

| Revision | Change |
|---|---|
| [revision] | [release evidence or decision update] |
