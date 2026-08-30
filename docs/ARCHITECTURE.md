# Architecture

## Purpose

This document describes the implemented Version 0.1 and Version 0.2 architecture without presenting the laboratory as a production banking platform.

## Two-dimensional component map

```text
                                      ARCHITECTURE LAYER
COMPONENT DIMENSION          API  SERVICE  RULES  INTEGRATION  DATA  EVIDENCE
OriginationController        [X]    [ ]     [ ]      [ ]       [ ]     [ ]
OriginationService           [ ]    [X]     [ ]      [ ]       [ ]     [X]
InternalCreditRules          [ ]    [ ]     [X]      [ ]       [ ]     [X]
MockCreditBureauClient       [ ]    [ ]     [ ]      [X]       [ ]     [X]
MockSanctionsClient          [ ]    [ ]     [ ]      [X]       [ ]     [X]
ApplicationEntity            [ ]    [ ]     [ ]      [ ]       [X]     [X]
DecisionEntity               [ ]    [ ]     [ ]      [ ]       [X]     [X]
Repositories                 [ ]    [ ]     [ ]      [ ]       [X]     [ ]

METAFIELDS
Style       : Layered Spring Boot application
Pattern     : Controller, service, mock adapter, rules, repository
Input       : Validated synthetic application request
Orchestrator: OriginationService
State       : H2 in-memory persistence
Output      : Explainable decision and retrieval view
Trust       : Caller -> API -> mocks/rules -> persistence -> response
Boundary    : No production integration or operational decisioning
```

## System context

```text
[Synthetic caller]
        |
        v
[Origination REST API]
        |
        +--> [Validation and consent]
        |
        +--> [Application persistence]
        |
        +--> [Mock credit bureau]
        |
        +--> [Mock sanctions screening]
        |
        +--> [Internal credit rules]
        |
        +--> [Decision persistence]
        |
        v
[Explainable response and history retrieval]
```

## Version evolution

```text
v0.1
Request -> Validate -> Screen -> Assess -> Decide -> Respond

v0.2
Request -> Validate -> Persist Application -> Screen -> Assess
        -> Decide -> Persist Decision -> Retrieve Evidence
```

## Components

| Component | Responsibility | Version |
|---|---|---|
| `OriginationController` | POST and GET API routes | v0.1, expanded v0.2 |
| `OriginationService` | Orchestration and decision persistence | v0.1, expanded v0.2 |
| `InternalCreditRules` | Laboratory reason-code evaluation | v0.1 |
| `MockCreditBureauClient` | Deterministic synthetic score | v0.1 |
| `MockSanctionsClient` | Synthetic name matching | v0.1 |
| `ApplicationEntity` | Persist application evidence | v0.2 |
| `DecisionEntity` | Persist decision evidence | v0.2 |
| Repositories | H2 data access | v0.2 |

## Limitations

- H2 data exists only while the application is running.
- Product-specific policy strategies are not yet implemented.
- There is no authentication, role model, workflow queue, real KYC, real bureau, real sanctions, or PEP integration.
- Rules remain compiled in Java and are not externally managed.