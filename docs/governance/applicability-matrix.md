# Standards and documentation applicability matrix

| Field | Value |
|---|---|
| Document ID | M1-DOC-006 |
| Revision | 0.2 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | @Entreco reviewed and accepted revision 0.1 as product owner; specialist legal/privacy/accessibility determinations remain scoped as open follow-up gates below |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#6](https://github.com/Entreco/elchrono/issues/6) |
| Review PR | [PR #18](https://github.com/Entreco/elchrono/pull/18); [evidence reconciliation PR](https://github.com/Entreco/elchrono/pull/19) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [Lifecycle and approval](document-control.md#lifecycle-and-approval).

## Purpose and limits

This matrix identifies the standards, legal obligations, and voluntary project
controls that must be considered for El Chrono's defined scope. It explains
current applicability decisions and rationale, assigns a responsible owner
and planned milestone, identifies the evidence location, and states the review
gate. Revision 0.1 was reviewed and accepted by @Entreco as product owner in
[PR #18](https://github.com/Entreco/elchrono/pull/18). The reviewed head was
`fce218225968af2e1f5b2c766186b1a1eb0c9835`; the PR merged as
`4e695cbdce992cedf7de2751448115aa74f2d3ab`, whose GitHub commit signature
verification is valid. This approval accepts the matrix and its explicit
applicability dispositions and follow-up gates; it does not turn an unresolved
legal question into a legal conclusion or establish specialist competence
beyond the reviewer's appointed scope. Revision 0.2 reconciles this evidence
and is submitted for fresh review in PR #19.

This record is not legal advice, a compliance declaration, certification, or
proof that any product control is implemented or verified.

El Chrono is intended as a non-medical visual aid for ordinary waiting-time
support. This record does not extend that purpose to diagnosis, treatment,
medical-condition monitoring, disability compensation, or safety-critical
timing. Assess the actual intended purpose, functions, claims, distribution
markets, and channels together; a disclaimer or absence of medical claims
alone does not determine legal qualification. Escalate a conflict with this
boundary before affected work continues.

The assessed source snapshot is the repository baseline at commit
[`3adde063c0d18ea0837fcded1a8607936d33e5b4`](https://github.com/Entreco/elchrono/commit/3adde063c0d18ea0837fcded1a8607936d33e5b4).
The EU and US are selected for assessment, but US launch states and
distribution channels are not selected. The app is a starter screen, not an
implemented timer. No patient account or telemetry is approved by default;
provider integrations and their data flows are not yet selected. Several
source records are candidates with unresolved approval or specialist-review
evidence; citing them does not represent that evidence as complete.

This is not an exhaustive legal survey. The matrix records known screening
decisions and makes unresolved applicability questions visible. Qualified
specialist confirmation remains required for legal determinations outside the
review scope evidenced in the source records. Where the product, markets,
channels, or data flows are not yet specified, the applicable decision and its
development gate are stated rather than assumed.

## Applicability decisions

### Medical-device qualification and standards

| Source | Applicability and rationale | Owner; planned milestone | Evidence location | Review gate |
|---|---|---|---|---|
| Regulation (EU) 2017/745 (MDR), especially Articles 2(1) and 2(12); European Commission MDCG 2019-11 rev.1 | **Not applicable on the assessed scope.** M1-DOC-003 records the qualified reviewer's conclusion that the product described by M1-DOC-002 is not a medical device in the EU. This conclusion is bounded by the stated purpose, functions, and claims; it is not an MDR conformity or CE-marking claim. | Product owner and regulatory reviewer; M1, reassess before a material scope or claims change | [M1-DOC-003, revision 0.6](../regulatory/qualification-and-classification.md); [M1-DOC-002, revision 0.6 assessed source](https://github.com/Entreco/elchrono/blob/9b5054691ae14a08fcbf591a6b0f073e49c54b79/docs/product/intended-use.md); [revision 0.7 candidate](../product/intended-use.md) | M1-DOC-003 assesses intended-use revision 0.6. Revision 0.7 is a candidate that clarifies reviewer scope; it does not change the intended-use text, but the qualified reviewer must confirm the assessed scope and candidate source revisions. Reassess if actual use, functionality, claims, or markets differ. PR acceptance of the candidate revisions remains pending in their source records. |
| US Federal Food, Drug, and Cosmetic Act, 21 U.S.C. § 321(h)(1); FDA software-function guidance | **Not applicable on the assessed scope.** M1-DOC-003 records the qualified reviewer's conclusion that the defined product is not a medical device under the FD&C Act. No FDA device class, product code, exemption, clearance, or approval is claimed. | Product owner and regulatory reviewer; M1, reassess before a material scope or claims change | [M1-DOC-003, revision 0.6](../regulatory/qualification-and-classification.md); [M1-DOC-002, revision 0.6 assessed source](https://github.com/Entreco/elchrono/blob/9b5054691ae14a08fcbf591a6b0f073e49c54b79/docs/product/intended-use.md); [revision 0.7 candidate](../product/intended-use.md) | M1-DOC-003 assesses intended-use revision 0.6. Revision 0.7 is a candidate that clarifies reviewer scope; it does not change the intended-use text, but the qualified reviewer must confirm the assessed scope and candidate source revisions. Reassess if actual use, functionality, claims, or markets differ. |
| IEC 62304:2006 with Amendment 1:2015, edition 1.1 | **Not applicable to the currently defined non-medical software scope.** M1-DOC-005 concludes that the software is not medical-device software; no Class A, B, or C is assigned. This is distinct from regulatory device classification and is not a claim of IEC 62304 conformity. The standard's life-cycle practices must not be presented as normative obligations for this product scope. | Product owner and software-safety reviewer; M1, reassess on a change to intended purpose, function, or qualification | [M1-DOC-005, revision 0.3](https://github.com/Entreco/elchrono/blob/3adde063c0d18ea0837fcded1a8607936d33e5b4/docs/safety/software-safety-classification.md); [IEC catalogue entry](https://webstore.iec.ch/en/publication/22794) | The issue #5 reviewer conclusion and exact-head acceptance are recorded in M1-DOC-005. Confirm its source baseline remains applicable; reassess if the product becomes medical-device software. |

### Other market obligations and conditional assessments

| Source / topic | Applicability and rationale | Owner; planned milestone | Evidence location | Review gate |
|---|---|---|---|---|
| EU General Data Protection Regulation (GDPR), Regulation (EU) 2016/679; ePrivacy Directive 2002/58/EC and national implementation | **Conditional; processing and roles are not yet defined.** The current direction avoids patient accounts, identifiable patient data, and telemetry by default. Applicability depends on actual processing, actors, location, and any device-storage or tracking technology. Do not collect data or select analytics/tracking by implication. Before a provider or data flow is designed, determine controller/processor roles, data categories, purpose and lawful basis, notices/rights, retention, security, and any consent requirement. | Product owner; M2 for data requirements, M6 before music/provider integration, M7 before distribution | [M1-DOC-003, revision 0.6](../regulatory/qualification-and-classification.md); future approved data-flow/privacy requirements and provider assessments | Appoint a competent privacy reviewer; approve the data-flow and privacy decisions before implementation of any collection, account, analytics, or provider integration. Reassess on a new data flow or market. |
| EU Cyber Resilience Act, Regulation (EU) 2024/2847 | **Applicability unresolved; potentially relevant to software distributed in the EU.** The product's legal/economic-operator role, distribution form, product scope, exclusions, and applicable dates have not been assessed. Do not assume that a non-medical product is outside this regulation. A competent reviewer must determine whether and how the regulation affects product-security design, vulnerability handling, support, and reporting, and identify applicable dates. | Product owner; determine before M3 architecture/security planning and before any EU distribution | This matrix; M1-DOC-007 development plan; M3 architecture/SOUP and security records; M9 maintenance/incident records | The approved disposition is to retain this as a tracked applicability action, not to assume inclusion or exclusion. Record a reasoned scope/date conclusion and required process before affected architecture or EU distribution; this open result does not block approval of the matrix itself. |
| EU accessibility: Directive (EU) 2019/882 (European Accessibility Act) and applicable national implementing rules | **Applicability unresolved; channel and service facts are incomplete.** Do not assume a standalone app is in or out of scope. Assess the actual service and distribution model, including whether it provides an in-scope service such as e-commerce, and identify applicable national rules and technical criteria. Accessibility and usability requirements in the product specification apply independently of this legal determination. | Product owner; determine before M2 accessibility criteria are finalized and before EU distribution | [AGENTS.md accessibility requirements](../../AGENTS.md#accessibility-and-timer-reliability); M2 requirements/usability evidence; this matrix | The approved disposition is to retain this as a tracked applicability action, not to assume inclusion or exclusion. Record the service/channel scope and applicable criteria before finalizing legal conformance claims or EU distribution; this open result does not block approval of the matrix itself. |
| Accessibility technical criteria and standards (including any applicable harmonised EN 301 549 edition and referenced WCAG criteria) | **No legal or project conformance baseline has been selected.** Select measurable criteria in M2 after the EAA and US legal scopes are assessed. The product's accessibility requirements apply regardless; do not claim conformance to a standard or legal requirement without a scoped assessment and evidence. | Product owner; M2, verify on each supported platform before release | [AGENTS.md accessibility requirements](../../AGENTS.md#accessibility-and-timer-reliability); future requirements, usability findings, and platform verification | Accessibility reviewer confirms the applicable edition/criteria, test methods, input modes, and acceptance evidence before requirements are approved. |
| EU consumer protection, including Directive 2005/29/EC and, where a consumer contract is offered, Directive 2011/83/EU and national implementation | **Potentially applicable to consumer-facing claims and distribution; transaction and channel details are not defined.** Claims about remaining time, reliability, accessibility, music, privacy, and platform support must match verified behavior and intended use. No deadline guarantee, medical benefit, unsupported platform support, or provider playback capability may be claimed. | Product owner; M2 claims/requirements, M7 before consumer distribution | [M1-DOC-002, revision 0.7](../product/intended-use.md); product/store/support content and release records to be established | Competent review of consumer-facing statements, pricing/contract flows if any, and national requirements for selected EU markets before publication. |
| US Children's Online Privacy Protection Act (COPPA), 15 U.S.C. §§ 6501–6506 and 16 C.F.R. Part 312 | **Conditional; intended age range and data flows are unresolved.** Assess if the service is directed to children under 13 or the operator has actual knowledge it collects their personal information. The potential use by people with cognitive or intellectual disabilities does not itself establish an age group. Decide age scope and data practices before any child-directed or personal-data feature is implemented. | Product owner; M2 for population/data requirements, M6 before provider integration, M7 before US distribution | [M1-DOC-002, revision 0.7](../product/intended-use.md); future approved age/data requirements and privacy records | Competent privacy/legal review of audience, actual knowledge, and each collection/third-party flow; resolve before affected collection or launch. |
| US federal and state privacy, consumer-protection, and accessibility requirements; including FTC Act § 5 and applicable state laws | **Scope-dependent; not fully assessed.** FTC Act prohibitions on unfair or deceptive practices are relevant to product claims and data practices. State privacy, accessibility, and consumer rules depend on launch states, thresholds, channels, and processing; those facts are not yet set. Federal accessibility applicability also depends on the service and context. | Product owner; M2 for claims/data/accessibility requirements, M7 before US distribution | [M1-DOC-003, revision 0.6](../regulatory/qualification-and-classification.md); future launch-market, channel, privacy, accessibility, and release records | Competent reviewer maps applicable federal/state rules after launch states/channels and data flows are selected; review claims and accessibility evidence before release. |
| YouTube, Spotify, app-store, and platform terms, supported APIs/SDKs, licenses, and playback policies | **Not applicable to the present starter app; conditional on a future integration or distribution choice.** No provider, authentication flow, subscription model, or playback method is approved. Use only supported official integrations. Assess authorization, tokens, data sharing, consent, licensing, availability, autoplay/embedding limitations, and disconnection behavior; timer operation must not depend on a provider. | Product owner; M3 for integration architecture, M6 before music implementation, M7 before platform release | Future provider assessments, data-flow records, dependency/SOUP records, and platform verification; [AGENTS.md privacy and music requirements](../../AGENTS.md#privacy-and-music-integrations) | Product owner approves the provider and platform scope only after legal/terms, privacy, licensing, dependency, and playback limitations are reviewed. Verify failure behavior without interrupting the timer. |

This list is a screening baseline, not a complete market-access opinion. Other
product, consumer, privacy, accessibility, cybersecurity, tax, employment, or
distribution obligations may apply when the product's actual features,
audience, channels, and markets are defined. The product owner must record
selected launch jurisdictions and channels before making a release
applicability claim.

### Voluntary project controls and M1 completion-gate cross-check

The following controls are maintained for product quality, traceability, and
review discipline. They are **not represented as requirements of IEC 62304**
or as evidence of regulatory conformity. A control's planned status does not
mean its record, implementation, review, or verification is complete.

| Process / M1 gate record | Source and applicability rationale | Owner; planned milestone | Evidence location | Review gate |
|---|---|---|---|---|
| Intended use, users, environments, supported use, exclusions, and claims boundary — M1-DOC-002 | Product-scope foundation for the non-medical qualification decision and all later requirements. This project control is required by the M1 gate; approval evidence is unresolved. | @Entreco; M1 | [Intended use, revision 0.7](../product/intended-use.md) | Required regulatory and product-boundary review of the exact revision; acceptance and approval evidence must be recorded in its PR. |
| Target-market qualification and conditional obligation screening — M1-DOC-003 | The qualified EU/US non-device conclusion is bounded by the intended-use candidate. It does not settle all privacy, accessibility, cybersecurity, or state-level obligations. | @Entreco; M1, then revisit before each new market/channel | [Qualification and classification, revision 0.6](../regulatory/qualification-and-classification.md) | Qualified reviewer confirms exact source revisions, legal scope, and open market assessments; unresolved release-specific questions remain assigned. |
| Initial risk management and risk-to-verification traceability — M1-DOC-004 and M1-DOC-009 | Voluntary product-risk controls are relevant even though IEC 62304 classification is not applicable. Six risks and proposed controls are recorded; no control effectiveness, residual-risk acceptability, or verification is established. | @Entreco; M1, update in M2–M9 | [Risk assessment, revision 0.2](../safety/risk-assessment.md); [risk traceability matrix, revision 0.1](../safety/risk-traceability-matrix.md) | Competent software-safety reviewer and required scenario walkthrough remain unassigned/pending for #4; review controls and actual verification evidence before approval or reliance. |
| IEC 62304 applicability and classification rationale — M1-DOC-005 | **Not applicable** for the defined non-medical scope, per the recorded software-safety reviewer conclusion. This is not a default Class A assignment. | @Entreco; M1; reassess on scope change | [Software safety classification, revision 0.3](https://github.com/Entreco/elchrono/blob/3adde063c0d18ea0837fcded1a8607936d33e5b4/docs/safety/software-safety-classification.md) | Recheck the intended purpose, qualification, and exact assessed source baseline when scope changes. The #5 conclusion does not satisfy other specialist review scopes. |
| Development planning, responsibilities, and standards applicability — M1-DOC-001 and planned M1-DOC-007 | The project voluntarily adopts controlled records and lifecycle practices. The development plan has not been created; its planned record is not an applicable IEC 62304 document by virtue of this matrix. | @Entreco; M1 for foundation, M3 for architecture/engineering plans | [Document controls, revision 2.1 candidate](document-control.md); planned `docs/governance/software-development-plan.md` (#7) | Assign competent reviewers for each scope and confirm the plan covers this matrix's decisions, owners, milestones, review gates, and change/reassessment triggers before M1 handoff. |
| Controlled requirements, acceptance criteria, accessibility, and usability — M2 | Project requirements in [AGENTS.md](../../AGENTS.md) guide the scaffold but are not an approved, traceable requirements register. Stable requirement IDs and representative usability evidence do not yet exist. | @Entreco; M2 | Future controlled requirements register, usability evidence, and links to M1-DOC-004/009 | Review with caregivers and representative users under appropriate consent and safeguards; do not infer preferences or mark requirements verified without evidence. |
| Architecture, third-party components/SOUP, privacy/security design, and platform boundaries — M3 | Voluntary engineering controls. Assess dependencies and provider/platform constraints; no IEC 62304 conformance or approved provider is implied. CRA applicability is an open input to EU architecture. | @Entreco; M3, revisit for M6–M8 integrations | [Module layout](../architecture/module-layout.md); future architecture, dependency/SOUP, data-flow, and security records | Complete applicable legal/security assessments before architecture decisions; review dependency provenance, supported use, and data flows before adoption. |
| Verification, validation, and traceability — M4–M8 | Voluntary product verification and usability evidence, mapped to approved requirements and risks. Tests must cover allowed duration boundaries, invalid configuration, lifecycle interruptions, completion, accessibility, and platform-specific behavior as relevant; no test result is claimed here. | @Entreco; M4–M8, evidence indexed in M9 | Future verification plan/results and M1-DOC-009 updates | Approve measurable criteria before implementation; record exact revision/platform, results, limitations, failures, and risk/requirement links. |
| Configuration/change control and unresolved anomalies — M1-DOC-001 and M1-DOC-007 | Project-controlled change assessment is retained to protect source assumptions and exact reviewed revisions; this is a voluntary control, not an IEC 62304 applicability claim. | @Entreco; M1 foundation, maintained through M9 | [Document controls](document-control.md); future development-plan and change records | Assess every change for impact on intended use, markets, laws, standards, risks, requirements, dependencies, platforms, and required re-review; retain unresolved anomalies visibly. |
| Release, instructions, limitations, and platform support — M7–M9 | Release approval is per shipped platform. Compilation or a scaffold does not establish support. Consumer claims, provider behavior, accessibility, privacy, and product limitations must reflect verified evidence. | @Entreco; M7–M9 | Future platform verification, release approvals, About/support/privacy/license content, and maintenance records | Complete platform-specific review and verification before each release; do not claim TV/watch support without target validation or market authorization without required review. |
| Maintenance, incident assessment, and post-release reassessment — M9 | Voluntary lifecycle controls; legal reporting or response duties depend on the ultimately applicable laws, product role, markets, data flows, and incident facts. | @Entreco; M9 and ongoing | Future maintenance/incident procedures, issue records, and revised applicability assessments | Appoint responsible roles and establish escalation, reporting, correction, and reassessment routes before release; assess obligations with a competent reviewer. |
| Review responsibilities and exact-revision acceptance — M1-DOC-001 | Required project gate: specialist scope and competence must be explicit, findings resolved, and human acceptance tied to the exact final PR head. A merge, lifecycle status, or agent-authored text is not proof of qualified review. | @Entreco; M1 and every controlled-record change | [Document controls, revision 2.1 candidate](document-control.md); review evidence in each record's PR | Required specialist reviewers and the M1 milestone approver remain unassigned for their outstanding scopes; do not imply their approval. Agents leave the human acceptance checkbox unchecked. |

## M1 completion-gate reconciliation and follow-up gates

| M1 completion-gate deliverable | Current record/evidence | Disposition for this matrix |
|---|---|---|
| Approved intended use and non-medical boundary | M1-DOC-002 revision 0.7 is a candidate; exact-revision approval evidence is unresolved. | Source and scope are identified; do not describe the intended-use record as approved. |
| Qualification and target-market assessment | M1-DOC-003 revision 0.6 records a qualified EU/US non-device conclusion against M1-DOC-002 revision 0.6; human acceptance remains pending in its source record. M1-DOC-002 revision 0.7 is a candidate reviewer-scope clarification. US states/channels and other conditional obligations are not resolved. | Bound the medical-device determinations to their reviewed scope; have the qualified reviewer confirm the candidate source revision and assign market/channel follow-up before affected work or distribution. |
| Initial risk assessment | M1-DOC-004 revision 0.2 and M1-DOC-009 revision 0.1 exist as candidates; the risk walkthrough and software-safety reviewer for that scope remain pending. | Map the voluntary risk controls and preserve the separate review/verification gate. |
| Software-safety classification rationale | M1-DOC-005 revision 0.3 records IEC 62304 non-applicability for the defined scope and exact-head acceptance evidence. | No class is assigned. This conclusion does not establish applicability of other laws or standards. |
| Development plan and review responsibilities | M1-DOC-007 is reserved but not created; M1-DOC-001 revision 2.1 is a candidate that records existing role assignments and remaining gaps. | Keep the missing plan and unassigned reviewer scopes visible; the matrix cannot substitute for either. |
| Standards/documentation applicability matrix | M1-DOC-006 revision 0.1 was accepted by @Entreco as product owner at PR #18 head `fce218225968af2e1f5b2c766186b1a1eb0c9835`, merged as signature-verified commit `4e695cbdce992cedf7de2751448115aa74f2d3ab`. Revision 0.2 reconciles that evidence in PR #19. | The matrix approval accepts the documented dispositions, owners, and gates. It does not claim that pending CRA, accessibility, privacy/data-flow, or market-specific legal conclusions have already been determined. |

**Approved follow-up gates:** The matrix review accepts the following staged
actions; it does not waive them. Determine CRA applicability and any resulting
process before M3 architecture/security work that depends on the answer and
before EU distribution. Determine EAA/service scope before finalizing M2 legal
accessibility criteria or making EU conformance claims. Complete privacy and
provider data-flow review before any collection or music integration, and
assess state/channel obligations before the relevant release. The product
owner remains accountable for assigning competent reviewers and recording
their decisions in the applicable controlled records.

These are explicit follow-up actions, not completed legal determinations.
Affected work remains gated until the assigned applicability assessment and
evidence are recorded; do not infer approval, qualification, or a compliance
outcome from this matrix acceptance.

## Reassessment triggers

Reassess this matrix before relying on it if any of the following changes:

- intended purpose, users, care context, functions, claims, or exclusions;
- selected countries, US states, distribution channels, or transaction model;
- data collection, analytics, accounts, provider integrations, or third-party
  processing;
- legal text, official guidance, applicable standards, or the product's
  economic-operator role;
- supported platforms, release model, accessibility criteria, or maintenance
  commitments; or
- a risk, incident, review finding, or actual use calls an assumption into
  question.

Record the affected decisions, source revisions, owner, required reviews, and
evidence in the PR and update this matrix when its conclusions or gates change.

## Sources

The citations below identify primary or official source material for qualified
review; listing a source does not mean that its full text, current version, or
applicability has been independently reviewed for this candidate.

1. International Electrotechnical Commission, [IEC 62304 consolidated version](https://webstore.iec.ch/en/publication/22794), IEC 62304:2006+AMD1:2015, edition 1.1, published 2015-06-26. Catalogue scope and edition checked 2026-11-15.
2. [Regulation (EU) 2017/745 (MDR)](https://eur-lex.europa.eu/eli/reg/2017/745/2025-01-10); European Commission, [MDCG 2019-11 rev.1](https://health.ec.europa.eu/document/download/b45335c5-1679-4c71-a91c-fc7a4d37f12b_en?filename=mdcg_2019_11_en.pdf).
3. US [Federal Food, Drug, and Cosmetic Act, 21 U.S.C. § 321(h)(1)](https://uscode.house.gov/view.xhtml?req=granuleid:USC-prelim-title21-section321&num=0&edition=prelim); FDA, [Policy for Device Software Functions and Mobile Medical Applications](https://www.fda.gov/regulatory-information/search-fda-guidance-documents/policy-device-software-functions-and-mobile-medical-applications).
4. [Regulation (EU) 2016/679 (GDPR)](https://eur-lex.europa.eu/eli/reg/2016/679/oj/eng); [Directive 2002/58/EC (ePrivacy)](https://eur-lex.europa.eu/eli/dir/2002/58/oj/eng).
5. [Directive (EU) 2019/882 (European Accessibility Act)](https://eur-lex.europa.eu/eli/dir/2019/882/oj/eng).
6. [Directive 2005/29/EC (unfair commercial practices)](https://eur-lex.europa.eu/eli/dir/2005/29/oj/eng); [Directive 2011/83/EU (consumer rights)](https://eur-lex.europa.eu/eli/dir/2011/83/oj/eng).
7. [Regulation (EU) 2024/2847 (Cyber Resilience Act)](https://eur-lex.europa.eu/eli/reg/2024/2847/oj/eng).
8. US [Children's Online Privacy Protection Act, 15 U.S.C. §§ 6501–6506](https://uscode.house.gov/view.xhtml?path=/prelim@title15/chapter91&edition=prelim); [16 C.F.R. Part 312](https://www.ecfr.gov/current/title-16/chapter-I/subchapter-C/part-312).
9. US [Federal Trade Commission Act § 5, 15 U.S.C. § 45](https://www.ftc.gov/legal-library/browse/statutes/federal-trade-commission-act).
10. [Project scope, accessibility, privacy, music, and lifecycle requirements](../../AGENTS.md); [M1-DOC-001 through M1-DOC-005 and M1-DOC-009](document-control.md#foundation-evidence-index).

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Establish the M1 applicability matrix; separate legal/standards applicability from voluntary controls, cross-check M1 gate evidence, and preserve unresolved decisions and review gaps. |
| 0.2 | Record @Entreco's exact-head acceptance of revision 0.1 and GitHub-verified merge signature; clarify that approved staged applicability actions do not assert unresolved legal conclusions and link the evidence-reconciliation PR. |
