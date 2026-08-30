# Version 0.2 - Persistent Decision Evidence

## Capability increment

```text
APPLICATION
    |
    +--> persist applicant and product data
    +--> execute mock bureau and sanctions checks
    +--> apply internal rule set 0.2.0
    +--> persist decision evidence
    +--> retrieve application history
```

## New endpoints

- `GET /api/v1/applications`
- `GET /api/v1/applications/{applicationId}`

## New evidence fields

- Application creation timestamp
- Decision timestamp
- Rule-set version
- Status, score, sanctions indicator, disposable income, and reasons

## Limitation

H2 remains in-memory, so data is retained only while the application process is running.
