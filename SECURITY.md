# Security Policy

## Two-dimensional security boundary

```text
                                     SECURITY ACTION
ASSET DIMENSION              EXCLUDE  VALIDATE  AUTHORIZE  AUDIT  PUBLISH
Real personal data             [X]      [X]        [ ]      [ ]     [ ]
Credentials and secrets        [X]      [X]        [ ]      [ ]     [ ]
Real screening information     [X]      [X]        [ ]      [ ]     [ ]
Synthetic reviewer actions     [ ]      [X]        [ ]      [X]     [X]
Build artifacts                [X]      [X]        [ ]      [ ]     [ ]

METAFIELDS
Data class : Synthetic portfolio data
Secrets    : Prohibited from Git
Build      : target/ excluded
Current auth: Not implemented
Audit      : Workflow events, not a production security log
Disclosure : Contact repository owner privately
Boundary   : No operational security assurance
```

Do not commit real personal information, identity numbers, account numbers, bureau records, sanctions or PEP data, passwords, tokens, keys, production URLs, build output, or local reports containing workstation paths.

The reviewer endpoints are unauthenticated in v0.4 and must be treated only as a synthetic workflow demonstration. Authentication, authorization, concurrency protection and standardized security error handling are recommended before any broader system use.