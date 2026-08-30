# Governance and Responsible Use

## Two-dimensional control map

```text
                                         CONTROL OUTCOME
CONTROL DIMENSION              PREVENT  DETECT  EXPLAIN  EVIDENCE  OVERSIGHT  ACCOUNTABILITY
Synthetic-data boundary         [X]      [X]      [X]      [X]       [X]          [X]
Consent gate                    [X]      [ ]      [X]      [X]       [ ]          [X]
Product-policy separation       [X]      [X]      [X]      [X]       [X]          [X]
KYC/PEP review state            [ ]      [X]      [X]      [X]       [X]          [X]
Referral queue                  [ ]      [X]      [X]      [X]       [X]          [X]
Reviewer claim                  [X]      [X]      [X]      [X]       [X]          [X]
Status history                  [ ]      [X]      [X]      [X]       [X]          [X]
Audit events                    [ ]      [X]      [X]      [X]       [X]          [X]

METAFIELDS
Objective      : Embed governance controls in a synthetic software workflow
Decision owner : SYSTEM initially; synthetic reviewer for referrals
Human oversight: Claim and review path for REFER decisions
Explainability : Product and screening reason codes
Traceability   : Application ID, actor, source, status and timestamps
Data class     : Synthetic portfolio data
Prohibition    : No real banking, compliance, pricing or eligibility use
```

## Control interpretation

- **Referral queue:** separates automated decisions from cases requiring synthetic human oversight.
- **Claim control:** establishes reviewer ownership before a review outcome can be submitted.
- **Status history:** records lifecycle transitions independently from the current application status.
- **KYC and PEP states:** demonstrate screening-state orchestration without connecting to real screening sources.
- **Audit events:** provide event type, actor, detail and timestamp for key workflow actions.
- **Product policies:** prevent one undifferentiated rule set from governing unlike products.

## Recommendations

- Introduce authenticated identities and role-based access before treating reviewer ownership as a security control.
- Add separation-of-duty rules between claim, review and policy administration.
- Add optimistic locking and idempotency to protect workflow integrity.
- Define retention, masking and deletion policies before durable personal-data storage.
- Version screening logic and product policies independently.
- Add an appeal or reconsideration lifecycle only as a synthetic governance demonstration.

These are recommendations for future versions and are not implemented in v0.4.