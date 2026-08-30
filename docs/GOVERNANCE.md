# Governance and Responsible Use

## Two-dimensional control map

```text
                                      CONTROL OUTCOME
CONTROL DIMENSION           PREVENT  DETECT  EXPLAIN  EVIDENCE  OVERSIGHT
Synthetic-data boundary       [X]      [X]      [X]      [X]       [X]
Bureau-consent gate           [X]      [ ]      [X]      [X]       [ ]
Protected-trait exclusion     [X]      [ ]      [X]      [ ]       [X]
Reason codes                  [ ]      [X]      [X]      [X]       [X]
Rule-set version              [ ]      [X]      [X]      [X]       [X]
Timestamps                    [ ]      [X]      [ ]      [X]       [X]
Referral outcome              [ ]      [X]      [X]      [X]       [X]

METAFIELDS
Objective    : Demonstrate governance embedded in software design
Owner        : Repository maintainer
Subjects     : Synthetic applicants only
Decision use : Learning and portfolio demonstration only
Explainability: Status plus reason codes
Traceability : Application ID, rule version, timestamps
Oversight    : REFER represents a human-review boundary
Prohibition  : No real customer, lending, pricing, or screening use
```

## Governance principles

### Purpose limitation

The platform exists solely as a synthetic learning and portfolio environment.

### Fairness boundary

Race, religion, gender, disability, sexual orientation, age, ethnicity, and neighbourhood or location proxies must not be added to scoring.

### Explainability

Every non-approved result exposes laboratory reason codes. A synthetic sanctions match takes the `DECLINED` path; other rule failures take the `REFER` path.

### Traceability

Version 0.2 stores the application identifier, application timestamp, decision timestamp, rule-set version, status, score, match indicator, disposable income, and reason codes.

### Human oversight

`REFER` is a workflow boundary for future human review. Version 0.2 does not yet implement the review queue.

## Prohibited use

Do not use this repository for real credit decisions, pricing, affordability, account opening, KYC, AML, sanctions, PEP, customer eligibility, or operational banking processing.