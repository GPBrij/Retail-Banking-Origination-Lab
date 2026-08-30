# Changelog

## Two-dimensional release map

```text
                         CAPABILITY DIMENSION
VERSION          DECISION  PERSISTENCE  TRACEABILITY  POLICY  WORKFLOW
v0.1.0              [X]        [ ]          [X]         [ ]      [ ]
v0.2.0              [X]        [X]          [X]         [ ]      [ ]
v0.2.1 docs         [X]        [X]          [X]         [ ]      [ ]
v0.3.0 planned      [X]        [X]          [X]         [X]      [ ]
v0.4.0 planned      [X]        [X]          [X]         [X]      [X]

METAFIELDS
Artifact : Release history
Control  : Git commit and annotated version tag
Evidence : Source changes, tests, and documentation
Boundary : Describes implemented or explicitly planned capability only
```

## v0.2.1 - Documentation refresh

- Refreshed README for Versions 0.1 and 0.2.
- Added architecture, API, governance, test-evidence, and roadmap documents.
- Added two-dimensional dimension and metafield views to every document.
- Clarified H2 in-memory persistence and responsible-use boundaries.

## v0.2.0 - Persistent decision evidence

- Added application and decision persistence.
- Added application and decision repositories.
- Added rule-set version and timestamps.
- Added single-record and list retrieval endpoints.
- Added persistence-flow test.

## v0.1.0 - Explainable decision engine

- Added four synthetic product identifiers.
- Added origination REST endpoint.
- Added mock credit-bureau scoring and sanctions screening.
- Added affordability calculation and internal laboratory rules.
- Added explainable approval, referral, and decline outcomes.