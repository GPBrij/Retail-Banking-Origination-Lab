# API Documentation

## Two-dimensional contract map

```text
                                   API OPERATION
DATA DIMENSION             POST APPLICATION  GET ONE  GET ALL  EVIDENCE
Application identifier            [X]           [X]      [X]      [X]
Applicant reference               [X]           [X]      [X]      [X]
Product type                      [X]           [X]      [X]      [X]
Affordability input               [X]           [ ]      [ ]      [ ]
Bureau consent                    [X]           [ ]      [ ]      [X]
Decision status                   [ ]           [X]      [X]      [X]
Bureau score                      [ ]           [X]      [X]      [X]
Sanctions indicator               [ ]           [X]      [X]      [X]
Reason codes                      [ ]           [X]      [X]      [X]
Rule-set version                  [ ]           [X]      [X]      [X]
Timestamps                        [ ]           [X]      [X]      [X]

METAFIELDS
Base URL    : http://localhost:8090
Media type  : application/json
Validation  : Jakarta Bean Validation
Identifier  : Generated UUID string
Errors      : Validation response or 404 error map
Persistence : H2 in-memory for active process only
Boundary    : Synthetic requests only
```

## Submit an application

`POST /api/v1/applications`

### Request

```json
{
  "applicantRef": "SYNTH-V02-1001",
  "productType": "CREDIT_CARD",
  "monthlyIncome": 45000,
  "monthlyExpenses": 18000,
  "existingDebt": 5000,
  "requestedAmount": 75000,
  "creditBureauConsent": true,
  "fullName": "DEMO CUSTOMER",
  "countryCode": "ZA"
}
```

### Response fields

| Field | Meaning |
|---|---|
| `applicationId` | Generated correlation identifier |
| `status` | `APPROVED`, `REFER`, or `DECLINED` |
| `bureauScore` | Deterministic synthetic score |
| `sanctionsMatch` | Synthetic match result |
| `disposableIncome` | Income minus expenses and existing debt |
| `reasons` | Explainable laboratory reason codes |

## Retrieve one application

`GET /api/v1/applications/{applicationId}`

The response adds `applicantRef`, `productType`, `ruleSetVersion`, `createdAt`, and `decidedAt` to the decision evidence.

## List applications

`GET /api/v1/applications`

Returns applications stored during the active H2 session.

## PowerShell example

```powershell
$Body = @{
    applicantRef        = "SYNTH-V02-1001"
    productType         = "CREDIT_CARD"
    monthlyIncome       = 45000
    monthlyExpenses     = 18000
    existingDebt        = 5000
    requestedAmount     = 75000
    creditBureauConsent = $true
    fullName            = "DEMO CUSTOMER"
    countryCode         = "ZA"
} | ConvertTo-Json

$Decision = Invoke-RestMethod `
    -Method Post `
    -Uri "http://localhost:8090/api/v1/applications" `
    -ContentType "application/json" `
    -Body $Body

Invoke-RestMethod `
    -Method Get `
    -Uri "http://localhost:8090/api/v1/applications/$($Decision.applicationId)"
```