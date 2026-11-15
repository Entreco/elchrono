# Medical-device qualification and target-market assessment

| Field | Value |
|---|---|
| Document ID | M1-DOC-003 |
| Revision | 0.2 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Regulatory reviewer: unassigned; blocking |
| Approver | @Entreco, after required review and findings resolution |
| Related issue | [#3](https://github.com/Entreco/elchrono/issues/3) |
| Review PR | [PR #13](https://github.com/Entreco/elchrono/pull/13) |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This assessment records the product owner's qualification conclusion for the
selected European Union and United States markets: **El Chrono, as presently
specified, is not a medical device in either market.** No regulatory medical-
device class applies. This is a product qualification decision based on the
intended purpose and functions documented here; it is not legal advice, a
regulator's determination, product approval, certification, or authorization
to distribute. The source review date is 2026-11-15.

The assessment applies the proposed intended use in
[M1-DOC-002, revision 0.4](../product/intended-use.md), a candidate revision on
this branch. Issue #2 is closed, but its closure and earlier PR history do not
establish approval of this revision or completion of required specialist
review. The decision below is explicit and reasoned; a qualified regulatory
reviewer must still check the exact revisions, challenge the alternative
interpretations, and record any findings before this controlled record can be
approved. This review gate does not change the product owner's conclusion into
an unresolved or undecided outcome.

This record separates:

- whether the product meets a market's legal definition of a medical device;
- the regulatory device class and obligations, which apply only if the
  product first qualifies as a device; and
- the project's proposed use of IEC 62304 practices and Class A software safety
  classification, adopted voluntarily as a separate process decision. It does
  not mean the product is a medical device, establish a regulatory device
  class, or claim IEC 62304 conformity or certification.

## Product and intended-use facts assessed

The proposed product is a general-use visual timer for showing progress through
a caregiver-configured ordinary waiting duration. The person waiting sees
remaining-time progress without a clock face or time of day. Background music
is optional and does not communicate the duration. People with cognitive or
intellectual disabilities may use the same general timer, but it does not
assess, diagnose, select, or adapt its function to a person's disability or
health status and is not intended to compensate for a disability. The product
is not intended to diagnose, treat, monitor, or manage a medical condition; to
provide medication, procedure, or other safety-critical timing; or to replace
a caregiver's judgment.

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

### Application and qualification conclusion

The MDR's Article 2(1) medical-purpose test includes software intended for
specified purposes such as diagnosis, prevention, monitoring, treatment or
alleviation of disease, and compensation for an injury or disability. Under
Article 2(12), intended purpose is assessed from the manufacturer's
information, including labeling, instructions, and promotional or sales
materials. El Chrono's intended purpose is ordinary-waiting-time visualization
for general use. It does not have a medical-purpose function, assess or act on
health information, or claim to compensate for a disability. The fact that a
person with a disability may use a general-purpose timer does not, by itself,
make disability compensation the product's intended purpose.

**Qualification conclusion: El Chrono, as specified in M1-DOC-002 revision
0.4, is not a medical device under the EU MDR.** The product does not meet the
medical-purpose element of Article 2(1) on its defined intended purpose and
functions. Accordingly, no MDR regulatory device class is assigned, and MDR
device conformity-assessment and CE-marking obligations do not apply on this
qualification basis. This is not a claim that the product complies with or is
certified under the MDR.

The plausible alternative interpretation—that the app is intended to
compensate for disability—is considered and rejected because disability is
neither an indication nor the function served: the same ordinary-waiting timer
is offered to all users without condition-specific assessment, adaptation, or
medical claims. A qualified reviewer must challenge this reasoning against
the exact candidate intended-use wording and actual planned functionality. If
the facts or claims differ, reassess and escalate any conflict before affected
work continues.

Because this conclusion is non-device, MDR device classification (including
Annex VIII Rule 11) is not applicable; no regulatory Class I, II, or III is
assigned. Rule 11 must not be used to describe the voluntary IEC 62304
software safety classification.

The non-device conclusion does not remove other applicable obligations. In
particular:

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

### Application and qualification conclusion

The proposed visual countdown communicates ordinary waiting progress. The
current intended use does not state that the app diagnoses, mitigates, treats,
or prevents a disease, provides clinical decision support, or affects body
structure or function. It is not intended for any other medical purpose.
People with cognitive or intellectual disabilities may use it, but the
product's purpose and function do not depend on or respond to that status.
Those facts support the non-device conclusion under the cited federal
definition. The product is not yet fully implemented, so actual functionality
and public-facing claims must remain within this documented intended use.

**Qualification conclusion: El Chrono, as specified in M1-DOC-002 revision
0.4, is not a medical device under the FD&C Act.** Its intended use is a
general-purpose aid to understand ordinary waiting time. It is not intended
for diagnosis, cure, mitigation, treatment, or prevention of disease, to
affect body structure or function, or for another medical purpose. It also
does not provide clinical decision support or process health information to
make a medical recommendation. Users' possible disabilities do not change
that purpose.

Accordingly, FDA device classification, product-code assignment, device
exemption, and premarket-submission requirements do not apply on this
qualification basis. Software-function exclusions under section 520(o) need
not be relied on to reach this conclusion. A qualified reviewer must check
the exact intended use and final functions against the statutory definition
and current FDA guidance. Do not represent this conclusion as an FDA decision
or clearance.

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

| Market | Qualification conclusion | Regulatory class |
|---|---|---|
| European Union | Not a medical device under the MDR as specified in M1-DOC-002 revision 0.4; no medical purpose, including no intended disability compensation. | Not applicable; no regulatory device class is assigned. |
| United States | Not a medical device under the FD&C Act as specified in M1-DOC-002 revision 0.4; no intended medical use. | Not applicable; no regulatory device class is assigned. |

Before approval, the appointed regulatory reviewer must:

1. Confirm competence for EU and US software/device qualification and the
   applicable regulatory scope.
2. Review the exact intended-use revision and candidate document revision,
   including the intended population and all public-facing claims.
3. Challenge the EU alternative interpretation that the product's intended
   purpose compensates for disability and record whether the documented
   distinction between audience and purpose is supported.
4. Verify the US analysis against the final functions, intended use, and claims.
5. Check applicable non-device obligations for selected distribution contexts
   and record findings and dispositions in the PR. If evidence indicates a
   medical purpose or contradicts the defined product scope, escalate to the
   product owner before affected work continues; do not silently change the
   qualification conclusion or intended use.

The regulatory reviewer is currently unassigned in
[document controls](../governance/document-control.md#responsibilities).
The qualification conclusions above are the product owner's documented
determination, not evidence that the required qualified review or record
approval has occurred. A general human review of the PR does not substitute
for the required specialist review.

## Reassessment triggers

Reassess both markets before relying on this outcome if any of the following
changes:

- a medical indication, condition-specific function, disability-compensation
  purpose, or any other element of the intended use;
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
| 0.2 | Record the non-device qualification conclusion in both markets, explain the distinction between user audience and medical purpose, and separate voluntary IEC 62304 Class A practices from regulatory device classification |
