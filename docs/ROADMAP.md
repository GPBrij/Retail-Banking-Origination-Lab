# Roadmap

## Two-dimensional roadmap

```text
                                         DELIVERY DIMENSION
VERSION DIMENSION        DECISION  DATA  POLICY  SCREENING  WORKFLOW  PLATFORM  DOCS
v0.1.0                      [X]     [ ]    [ ]       [X]       [ ]       [ ]      [X]
v0.2.0                      [X]     [X]    [ ]       [X]       [ ]       [ ]      [X]
v0.3.0                      [X]     [X]    [X]       [X]       [ ]       [ ]      [X]
v0.4.0                      [X]     [X]    [X]       [X]       [X]       [ ]      [X]
v0.4.1                      [X]     [X]    [X]       [X]       [X]       [ ]      [X]
v0.5.0 recommended          [X]     [X]    [X]       [X]       [X]       [X]      [X]
v1.0.0 recommended          [X]     [X]    [X]       [X]       [X]       [X]      [X]

METAFIELDS
Sequence   : Incremental, test-gated releases
Repository : One evolving GitHub repository
Versioning : Commits and annotated tags
Current    : v0.4 workflow implemented
Next       : Platform hardening and contract quality
Boundary   : Planned items are recommendations, not current claims
```

## Recommended v0.5.0

- PostgreSQL profile and Flyway migrations
- OpenAPI documentation
- Consistent validation, not-found and conflict responses
- Optimistic locking for referrals
- Idempotency protection on claim and review operations
- Structured correlation IDs and safe observability
- Expanded controller and repository integration tests

## Recommended v0.6.0

- Synthetic authentication and role authorization
- Reviewer and policy-administrator role boundaries
- Referral reassignment and escalation states
- Externalized, versioned laboratory policy configuration

## Recommended v1.0.0

- Containerized local environment
- Complete architecture decision records
- Threat model and security review
- CI build gate
- Complete manual API evidence
- Public portfolio readiness review