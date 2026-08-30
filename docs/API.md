# API Documentation

## Two-dimensional API map

```text
                                     API OPERATION
RESOURCE DIMENSION          CREATE  GET ONE  LIST  CLAIM  REVIEW  HISTORY  AUDIT
Application                   [X]      [X]    [X]    [ ]    [ ]      [X]     [X]
Referral                      [X]      [ ]    [X]    [X]    [X]      [X]     [X]
KYC/PEP state                 [X]      [X]    [ ]    [ ]    [ ]      [X]     [X]
Reviewer                      [ ]      [ ]    [ ]    [X]    [X]      [X]     [X]

METAFIELDS
Base URL    : http://localhost:8090
Media type  : application/json
Validation  : Jakarta Bean Validation
Identifiers : UUID strings
Persistence : Active H2 process only
Actors      : SYSTEM or synthetic reviewer text
Boundary    : Synthetic API inputs only
```

## Origination

### Submit application

`POST /api/v1/applications`

```json
{
  "applicantRef": "SYNTH-V04-KYC-001",
  "productType": "SAVINGS_ACCOUNT",
  "monthlyIncome": 45000,
  "monthlyExpenses": 18000,
  "existingDebt": 5000,
  "requestedAmount": 0,
  "creditBureauConsent": true,
  "fullName": "KYC REVIEW CUSTOMER",
  "countryCode": "ZA"
}
```

Synthetic markers:

- `KYC REVIEW` produces `SYNTHETIC_REVIEW`.
- `PEP REVIEW` produces `SYNTHETIC_POTENTIAL_MATCH`.
- `TEST BLOCKED PERSON` produces the synthetic sanctions-match path.

### Retrieve evidence

- `GET /api/v1/applications/{applicationId}`
- `GET /api/v1/applications`

## Referral workflow

### List open referrals

`GET /api/v1/workflow/referrals`

### Claim referral

`POST /api/v1/workflow/referrals/{applicationId}/claim`

```json
{
  "reviewer": "SYNTHETIC_REVIEWER"
}
```

### Complete review

`POST /api/v1/workflow/referrals/{applicationId}/review`

```json
{
  "reviewer": "SYNTHETIC_REVIEWER",
  "outcome": "APPROVE",
  "note": "Synthetic review completed"
}
```

`outcome` is `APPROVE` or `DECLINE`. The reviewer value must match the reviewer that claimed the referral.

### Retrieve workflow evidence

`GET /api/v1/workflow/applications/{applicationId}`

Returns current application, KYC, PEP and referral states plus reviewer, status history and audit events.

## Current error behavior

A missing application or referral is mapped to a `404` response by the existing advice. A reviewer mismatch raises an illegal-state error; a standardized conflict response is recommended for a later release.