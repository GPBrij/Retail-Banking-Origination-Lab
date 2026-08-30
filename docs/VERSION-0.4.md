# Version 0.4 - Governed Reviewer Workflow

## Two-dimensional implementation map

```text
                                   CONTROL LIFECYCLE
CAPABILITY DIMENSION     CAPTURE  SCREEN  REFER  CLAIM  REVIEW  HISTORY  AUDIT
KYC state                  [X]      [X]     [X]    [ ]     [X]      [X]    [X]
PEP state                  [X]      [X]     [X]    [ ]     [X]      [X]    [X]
Referral queue             [ ]      [ ]     [X]    [X]     [X]      [X]    [X]
Reviewer workflow          [ ]      [ ]     [ ]    [X]     [X]      [X]    [X]
Application state          [X]      [X]     [X]    [X]     [X]      [X]    [X]
Audit events               [ ]      [X]     [X]    [X]     [X]      [ ]    [X]

METAFIELDS
Release      : v0.4.0
Purpose      : Synthetic governed onboarding and reviewer workflow
New entities : Referral, StatusHistory and AuditEvent
New service  : WorkflowService
New API      : WorkflowController
Screening    : MockKycPepService
Test         : WorkflowTests
Rule version : 0.4.0
Boundary     : No real KYC, PEP, reviewer or banking-decision use
```

## Implemented states

- Application: `RECEIVED`, `SCREENING`, `DECISIONED`, `REFERRED`, `UNDER_REVIEW`, `APPROVED`, `DECLINED`
- KYC: `NOT_STARTED`, `SYNTHETIC_CLEAR`, `SYNTHETIC_REVIEW`
- PEP: `NOT_SCREENED`, `SYNTHETIC_NO_MATCH`, `SYNTHETIC_POTENTIAL_MATCH`
- Referral: `OPEN`, `CLAIMED`, `APPROVED`, `DECLINED`
- Review outcome: `APPROVE`, `DECLINE`

## Implemented workflow

A reason from product rules, synthetic KYC review, or synthetic PEP review produces `REFER`. A referral is opened, then claimed by a synthetic reviewer. The same reviewer can approve or decline the referral. Status transitions and audit events are persisted for the active H2 session.

## Validation

The v0.4 release passed seven automated tests with zero failures and zero errors. The new workflow test validated create, claim, review, final status, history and audit evidence.