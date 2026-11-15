## Summary

Describe the change and link the related issue.

All documents under `docs/` derive lifecycle status from the viewed branch:
Draft without an open PR targeting `main`, In review with an open PR (including
draft PRs), and Approved and effective on `main` after merge. Do not commit
lifecycle labels. See [document controls](https://github.com/Entreco/elchrono/blob/main/docs/governance/document-control.md#lifecycle-and-approval).

## Verification and limitations

Record checks, results, unresolved findings, and required reviewer scopes.

Lifecycle status does not prove required reviews or verification occurred;
keep missing evidence explicit and satisfy the pre-merge gates.

## Human acceptance

Agents must leave this unchecked. A human may self-review their own PR.
After any new commit, clear the checkbox and review the new head before merge.

- Accepting human: pending
- Reviewed head commit (full SHA): pending
- [ ] Human review completed and final revision accepted for merge

The merger must verify acceptance covers the final head and required review
scopes. This is a manual gate, not GitHub-enforced approval.
