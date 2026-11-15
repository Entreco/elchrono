# Medical-device qualification and target-market assessment

| Field | Value |
|---|---|
| Document ID | M1-DOC-003 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Regulatory reviewer: unassigned; blocking |
| Approver | @Entreco, after required review and findings resolution |
| Related issue | [#3](https://github.com/Entreco/elchrono/issues/3) |
| Review PR | [PR #13](https://github.com/Entreco/elchrono/pull/13) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This is a candidate assessment of medical-device qualification and potential
regulatory classification for the selected European Union and United States
markets. It is not a qualified legal or regulatory determination, approval,
certification, or authorization to distribute. The source review date is
2026-11-15.

The assessment applies the proposed intended use in
[M1-DOC-002, revision 0.3](../product/intended-use.md), which is a candidate
revision on this branch and is not established as an approved prerequisite.
Issue #2 is closed, but its closure and earlier PR history do not establish
approval of this candidate revision or completion of required specialist
review. A qualified regulatory reviewer must assess the exact revision and
challenge the plausible alternative interpretation before this record can be
approved. No uncertainty below is resolved merely by the product's non-medical
position or by this document.

This record separates:

- whether the product meets a market's legal definition of a medical device;
- the regulatory device class and obligations, which apply only if the
  product first qualifies as a device; and
- the project's proposed IEC 62304 software safety class, a separate voluntary
  process decision not made by this assessment.

## Product and intended-use facts assessed

The proposed product is a visual aid for showing progress through a caregiver-
configured ordinary waiting duration. The person waiting sees remaining-time
progress without a clock face or time of day. Background music is optional and
does not communicate the duration. The proposed audience includes people who
benefit from a visual representation and includes people with cognitive or
intellectual disabilities. The product is not intended to diagnose, treat,
monitor, or manage a medical condition; to provide medication, procedure, or
other safety-critical timing; or to replace a caregiver's judgment.

These are intended-use statements, not verified product capabilities. The
current application is a starter screen, not an implemented timer. No approved
launch country list, US state list, distribution channel, patient account, or
patient-data flow is established. Future functionality, labeling, marketing,
provider integrations, actual use, or a more specific patient population may
change the analysis.

## European Union

### Applicable test

Regulation (EU) 2017/745 (MDR), Article 2(1), includes software intended by its
manufacturer for one or more specified medical purposes. Relevant purposes
include diagnosis, prevention, monitoring, prediction, prognosis, treatment or
alleviation of disease, and diagnosis, monitoring, treatment, alleviation of,
or compensation for an injury or disability. Article 2(12) defines intended
purpose by reference to manufacturer-supplied information, including labeling,
instructions, promotional or sales materials, and statements. Qualification
therefore depends on the product's actual intended purpose and claims, not only
on a disclaimer or the absence of a particular feature.

The European Commission's MDCG 2019-11 rev.1 guidance (June 2025) addresses
qualification and classification of software under the MDR and IVDR. It is
guidance for applying the legislation; it does not replace the MDR or a
competent, product-specific assessment.

### Application and candidate conclusion

The stated ordinary-waiting purpose, absence of medical claims, and lack of a
medical decision, diagnosis, monitoring, or treatment function support a
non-device interpretation. However, the proposed intended audience includes
people with cognitive or intellectual disabilities. The MDR expressly includes
software intended to compensate for a disability. A reviewer must determine
whether this product's specific intended purpose and presentation amount to
such compensation, even though it is not intended to treat or monitor a
condition. No evidence in this draft resolves that question.

**Candidate conclusion: EU qualification is unresolved.** A non-device outcome
is plausible on the stated ordinary-waiting purpose, but the disability-
compensation language creates a material alternative interpretation that must
be resolved by the qualified reviewer. Do not represent the product as
determined to be either a device or a non-device before that review. The
non-medical product boundary remains controlling; if the assessment indicates
medical-device qualification, escalate the conflict to the product owner
before affected work continues rather than silently expanding the intended use.

### Classification and obligations if the product qualifies

No MDR class is assigned in this draft. If the reviewer concludes that the
software qualifies as a medical device, they must determine the applicable
classification under MDR Annex VIII using the final intended purpose and
functionality. Rule 11 may be relevant to software: its decision-support and
physiological-monitoring criteria must be considered, and its residual
Class I category must not be used as a shortcut around qualification or
classification analysis. The resulting conformity-assessment route and
manufacturer obligations must then be identified under the MDR. Do not claim
CE marking, conformity, or an exemption without the applicable assessment and
evidence.

If the product is determined not to be a medical device, MDR device-class and
conformity obligations do not follow from this assessment. That conclusion
would not remove other applicable obligations. In particular:

| Area | Applicability question and follow-up |
|---|---|
| Personal data | Assess GDPR territorial/material scope and obligations if the app, analytics, hosting, or connected providers process personal data. Confirm whether any collected information is health data, document provider flows, and establish the relevant controller/processor roles and user information before release. |
| Accessibility | Assess whether the product or its distribution service falls within the European Accessibility Act (Directive (EU) 2019/882) and applicable national implementing rules. A standalone timer app is not assumed to be in scope merely because it is software; product and channel facts must be checked. Accessibility requirements in the product specification still apply independently. |
| Consumer and national rules | Identify applicable consumer, advertising, language, and national requirements for the actual Member States and distribution channels before launch. The EU assessment does not cover non-EU EEA or other European markets. |

These are applicability checks, not conclusions that every listed instrument
applies or a complete EU market-access review.

## United States

### Applicable test

Section 201(h)(1) of the Federal Food, Drug, and Cosmetic Act, codified at
21 U.S.C. § 321(h)(1), defines a device by reference to intended uses including
diagnosis of disease or other conditions; cure, mitigation, treatment, or
prevention of disease; and affecting the structure or a function of the body.
The statutory definition excludes software functions excluded under section
520(o). FDA's September 2022 guidance, *Policy for Device Software Functions
and Mobile Medical Applications*, describes how FDA applies oversight to
software functions that meet the device definition and identifies certain
functions that do not meet that definition or for which FDA does not intend to
enforce applicable requirements.

FDA advises defining intended use and indications for use before determining
whether a product is a device. If a product is a device, classification is a
separate step based on its intended use and indications, including searching
for an applicable FDA product classification.

### Application and candidate conclusion

The proposed visual countdown communicates ordinary waiting progress. The
current intended use does not state that the app diagnoses, mitigates, treats,
or prevents a disease, provides clinical decision support, or affects body
structure or function. Those facts support a non-device interpretation under
the cited federal definition. The proposed user population alone does not
settle the question; actual intended function, indications, labeling, and
claims must be checked. No fully implemented product or final distribution
claims are available for assessment.

**Candidate conclusion: a non-device interpretation is supported by the
current stated purpose, but is not approved.** A qualified reviewer must verify
the intended-use facts and consider whether the final product or claims meet
the statutory definition or an applicable software-function exclusion. No FDA
device class, product code, exemption, or premarket pathway is assigned here.

If the reviewer concludes that a device function exists, identify the
appropriate product classification, class, applicable controls, exemptions,
and market-entry requirements from the final intended use and FDA sources.
Do not infer Class I, a 510(k) exemption, or any other pathway from the fact
that this is a timer.

### Other obligations and limits

A non-device conclusion does not establish that the product is free from other
federal or state requirements. Before US distribution:

| Area | Applicability question and follow-up |
|---|---|
| Consumer protection and claims | Ensure product claims and practices are not deceptive or unfair under applicable federal and state consumer-protection laws. Claims must remain consistent with the non-medical intended-use boundary. |
| Children's privacy | The intended age range is not specified. Assess COPPA (16 C.F.R. Part 312) if the service is directed to children under 13 or the operator has actual knowledge it collects personal information from such children. Define the age and data-collection approach before launch. |
| State privacy and accessibility | Identify launch states and channels, then assess applicable state privacy, consumer-protection, and accessibility requirements. No state-specific applicability or exemption analysis is made here. |
| Provider data flows | If YouTube, Spotify, analytics, or other services are connected, document their data processing, permissions, consent where required, retention, and disconnection behavior. The timer must continue to work without those services. |

This is a screening list, not an exhaustive federal or state legal review.

## Cross-market outcome and required review

| Market | Candidate qualification outcome | Regulatory class |
|---|---|---|
| European Union | Unresolved; non-device is plausible, but possible compensation for disability requires qualified review. | Not assigned; only assess if device qualification is established. |
| United States | Non-device interpretation is supported by the current stated purpose but requires qualified review of the final functions, intended use, and claims. | Not assigned; only assess if a device function is established. |

Before approval, the appointed regulatory reviewer must:

1. Confirm competence for EU and US software/device qualification and the
   applicable regulatory scope.
2. Review the exact intended-use revision and candidate document revision,
   including the intended population and all public-facing claims.
3. Challenge the plausible EU interpretation that the intended purpose
   compensates for disability and document a reasoned disposition.
4. Confirm the US device-definition analysis and identify any applicable
   software-function exclusion or device classification only if warranted.
5. Resolve uncertain qualification/classification, identify applicable
   obligations for selected distribution contexts, and record findings and
   dispositions in the PR. If a conclusion conflicts with the non-medical
   product direction, escalate before affected work continues.

The regulatory reviewer is currently unassigned in
[document controls](../governance/document-control.md#responsibilities).
No qualified review, final conclusion, or approval is evidenced by this
candidate. A general human review of the PR does not substitute for the
required specialist review.

## Reassessment triggers

Reassess both markets before relying on this outcome if any of the following
changes:

- the intended users, patient population, care setting, supported use, or
  exclusions;
- claims, labeling, app-store descriptions, sales materials, or provider
  descriptions;
- timer or audio functionality, including a use for medication, procedure,
  treatment, or other safety-critical timing;
- data collection, clinical decision support, patient monitoring, or
  integrations that change product purpose;
- selected countries, US states, distribution channels, or applicable law; or
- authoritative guidance or legislation relevant to the analysis.

## Sources

Source research was performed on 2026-11-15. The European Commission index was
checked for the MDR consolidation link and MDCG guidance version/publication
date; FDA determination, software-policy, and classification pages were
retrieved. Direct retrieval of the EUR-Lex legal texts, MDCG PDF text, eCFR,
and FTC source was unavailable in this session. The appointed reviewer must
verify the current legal text, any amendments since the linked consolidation,
the guidance analysis, and each conditional obligation before approval.
Statutory text controls over guidance. This list supports preliminary screening
and is not a complete survey of all market-access obligations.

1. [Regulation (EU) 2017/745, consolidated text dated 2025-01-10](https://eur-lex.europa.eu/eli/reg/2017/745/2025-01-10), especially Article 2(1), Article 2(12), Article 10, Article 20, Article 52, and Annex VIII Rule 11.
2. European Commission, [MDCG 2019-11 rev.1, Qualification and classification of software](https://health.ec.europa.eu/document/download/b45335c5-1679-4c71-a91c-fc7a4d37f12b_en?filename=mdcg_2019_11_en.pdf), June 2025; listed in the Commission's [MDCG guidance index](https://health.ec.europa.eu/medical-devices-sector/new-regulations/guidance-mdcg-endorsed-documents-and-other-guidance_en).
3. [Regulation (EU) 2016/679 (GDPR)](https://eur-lex.europa.eu/eli/reg/2016/679/oj/eng); see also the European Commission's [data-protection legal framework](https://commission.europa.eu/law/law-topic/data-protection/legal-framework-eu-data-protection_en).
4. [Directive (EU) 2019/882, European Accessibility Act](https://eur-lex.europa.eu/eli/dir/2019/882/oj/eng).
5. FDA, [How to Determine if Your Product is a Medical Device](https://www.fda.gov/medical-devices/classify-your-medical-device/how-determine-if-your-product-medical-device), including the Section 201(h)(1) definition and FDA's two-step determination approach.
6. FDA, [Policy for Device Software Functions and Mobile Medical Applications](https://www.fda.gov/regulatory-information/search-fda-guidance-documents/policy-device-software-functions-and-mobile-medical-applications), September 2022.
7. FDA, [Classify Your Medical Device](https://www.fda.gov/medical-devices/overview-device-regulation/classify-your-medical-device), and [Product Classification Database](https://www.accessdata.fda.gov/scripts/cdrh/cfdocs/cfPCD/classification.cfm).
8. [Children's Online Privacy Protection Rule, 16 C.F.R. Part 312](https://www.ecfr.gov/current/title-16/chapter-I/subchapter-C/part-312).
9. [Federal Trade Commission Act, Section 5, 15 U.S.C. § 45](https://www.ftc.gov/legal-library/browse/statutes/federal-trade-commission-act).

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Initial candidate assessment for EU and US; identify unresolved qualification, classification, and review evidence |
