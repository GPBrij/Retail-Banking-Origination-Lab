# Changelog

## Two-dimensional release map

```text
                              CAPABILITY DIMENSION
VERSION       DECISION  DATA  POLICY  SCREENING  REFERRAL  REVIEW  AUDIT  DOCS
v0.1.0           [X]     [ ]    [ ]       [X]       [ ]      [ ]    [ ]    [X]
v0.2.0           [X]     [X]    [ ]       [X]       [ ]      [ ]    [X]    [X]
v0.2.1           [X]     [X]    [ ]       [X]       [ ]      [ ]    [X]    [X]
v0.3.0           [X]     [X]    [X]       [X]       [ ]      [ ]    [X]    [X]
v0.4.0           [X]     [X]    [X]       [X]       [X]      [X]    [X]    [X]
v0.4.1           [X]     [X]    [X]       [X]       [X]      [X]    [X]    [X]

METAFIELDS
Artifact : Release history
Control  : Test-gated commits and annotated Git tags
Evidence : Code, tests, API results and documents
Boundary : Implemented capability is separated from planned capability
```

## v0.4.1 - Documentation alignment

- Updated all portfolio documents for the complete v0.4 workflow.
- Added referral, screening, reviewer, status-history and audit-event documentation.
- Added `WORKFLOW.md` and expanded API examples.
- Clarified limitations and future recommendations.

## v0.4.0 - Governed reviewer workflow

- Added synthetic KYC and PEP states.
- Added application lifecycle states and status history.
- Added referral entity, queue and claim processing.
- Added reviewer approval and decline workflow.
- Added timestamped audit events.
- Added workflow APIs, workflow view and automated workflow test.

## v0.3.0 - Product Policy Strategy Pattern

- Added four product-policy strategies and factory resolution.
- Added product-specific reason codes and policy tests.

## v0.2.1 - Documentation refresh

- Added architecture, API, governance, roadmap, test evidence, security and version documents.

## v0.2.0 - Persistent decision evidence

- Added application and decision persistence, rule version, timestamps and retrieval APIs.

## v0.1.0 - Explainable decision engine

- Added four products, mock screening, affordability and explainable decisions.