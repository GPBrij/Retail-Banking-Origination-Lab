# Version 0.4 - Governed Reviewer Workflow

```text
                                   CONTROL LIFECYCLE
CAPABILITY DIMENSION     CAPTURE  SCREEN  REFER  CLAIM  REVIEW  AUDIT
KYC state                  [X]      [X]     [X]    [ ]     [X]    [X]
PEP state                  [X]      [X]     [X]    [ ]     [X]    [X]
Referral queue             [ ]      [ ]     [X]    [X]     [X]    [X]
Status history             [X]      [X]     [X]    [X]     [X]    [X]
Reviewer workflow          [ ]      [ ]     [ ]    [X]     [X]    [X]
Audit events               [X]      [X]     [X]    [X]     [X]    [X]

METAFIELDS
Release     : v0.4.0
Purpose     : Synthetic governed onboarding and human-review workflow
States      : Application, KYC, PEP, referral and review states
Queue       : Open synthetic referrals
Reviewer    : Claim then approve or decline
Evidence    : Status history and audit events
Rule version: 0.4.0
Boundary    : No real KYC, PEP, sanctions or banking decision use
```

## New endpoints

- `GET /api/v1/workflow/referrals`
- `POST /api/v1/workflow/referrals/{applicationId}/claim`
- `POST /api/v1/workflow/referrals/{applicationId}/review`
- `GET /api/v1/workflow/applications/{applicationId}`

All screening indicators and reviewer identities are synthetic portfolio data.