# Architecture

## Two-dimensional component view

```text
                                           ARCHITECTURE DIMENSION
COMPONENT DIMENSION             API  ORCHESTRATION  POLICY  SCREENING  DATA  WORKFLOW  EVIDENCE
OriginationController           [X]       [ ]        [ ]      [ ]      [ ]     [ ]       [ ]
WorkflowController              [X]       [ ]        [ ]      [ ]      [ ]     [X]       [X]
OriginationService              [ ]       [X]        [X]      [X]      [X]     [X]       [X]
WorkflowService                 [ ]       [X]        [ ]      [ ]      [X]     [X]       [X]
ProductPolicyFactory            [ ]       [ ]        [X]      [ ]      [ ]     [ ]       [ ]
Product policies                [ ]       [ ]        [X]      [ ]      [ ]     [ ]       [X]
Mock screening clients          [ ]       [ ]        [ ]      [X]      [ ]     [ ]       [X]
JPA entities/repositories       [ ]       [ ]        [ ]      [ ]      [X]     [X]       [X]

METAFIELDS
Style       : Layered modular monolith
Patterns    : Strategy, factory, service and repository
Entry points: OriginationController and WorkflowController
State       : H2 in-memory relational persistence
Business flow: OriginationService and WorkflowService
Evidence    : Decisions, referrals, status history and audit events
Boundary    : Synthetic portfolio architecture only
```

## Logical architecture

```text
CALLER
  |
  +--> ORIGINATION API
  |       |
  |       +--> APPLICATION PERSISTENCE
  |       +--> SYNTHETIC SCREENING
  |       +--> PRODUCT POLICY FACTORY
  |       +--> DECISION PERSISTENCE
  |       +--> REFERRAL CREATION
  |
  +--> WORKFLOW API
          |
          +--> OPEN REFERRALS
          +--> CLAIM OWNERSHIP
          +--> REVIEW OUTCOME
          +--> STATUS HISTORY
          +--> AUDIT EVENTS
```

## Recommendations

1. **Persistent database profile:** add PostgreSQL and schema migrations so evidence survives restarts.
2. **Security boundary:** add authentication and role-based authorization before exposing reviewer endpoints.
3. **Workflow concurrency:** use entity versions or locking to prevent two reviewers claiming the same referral.
4. **Externalised policy:** separate laboratory thresholds from Java source and version policy configuration.
5. **Observability:** add correlation identifiers, structured logs and metrics without logging personal data.
6. **API contract:** add OpenAPI documentation and standardized validation/conflict responses.
7. **Portfolio boundary:** retain synthetic naming and never add real customer or screening datasets.

These are recommended future enhancements, not capabilities claimed by v0.4.