# Version 0.1 Baseline

## Two-dimensional capability view

```text
                           DECISION STAGE
CAPABILITY             CAPTURE  VERIFY  ASSESS  DECIDE  EXPLAIN
Product selection        [X]      [ ]      [ ]      [ ]      [ ]
Consent validation       [X]      [X]      [ ]      [ ]      [X]
Mock bureau              [ ]      [X]      [X]      [ ]      [X]
Mock sanctions           [ ]      [X]      [X]      [X]      [X]
Affordability            [ ]      [ ]      [X]      [X]      [X]
Reason codes             [ ]      [ ]      [X]      [X]      [X]

METAFIELDS
Release  : v0.1 baseline
Purpose  : Prove the end-to-end decision slice
State    : Stateless request and response
Products : Home loan, credit card, savings account, debit card
Outcomes : APPROVED, REFER, DECLINED
Evidence : Score, match indicator, disposable income, reasons
Boundary : Synthetic learning only
```

## Demonstrated outcomes

- Referral caused by a bureau score below the laboratory threshold.
- Approval where no laboratory rule failed.
- Decline caused by a synthetic sanctions-list match.