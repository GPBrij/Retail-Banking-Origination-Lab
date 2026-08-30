# Version 0.2 Persistent Decision Evidence

## Two-dimensional increment view

```text
                                  EVIDENCE LIFECYCLE
CAPABILITY DIMENSION       CREATE  STORE  VERSION  RETRIEVE  LIST
Application                  [X]    [X]      [ ]       [X]     [X]
Decision                     [X]    [X]      [X]       [X]     [X]
Reason codes                 [X]    [X]      [X]       [X]     [X]
Timestamps                   [X]    [X]      [ ]       [X]     [X]
Rule-set version             [X]    [X]      [X]       [X]     [X]

METAFIELDS
Release     : v0.2.0
Increment   : Persistence and retrieval
Database    : H2 in memory
Rule version: 0.2.0
New routes  : GET one and GET all
Test        : PersistenceFlowTests
Durability  : Active-process lifetime only
Boundary    : No production persistence claim
```

## Added components

- `ApplicationEntity`
- `DecisionEntity`
- `ApplicationRepository`
- `DecisionRepository`
- `ApplicationHistoryResponse`
- `ApiExceptionHandler`
- `PersistenceFlowTests`

## Demonstrated retrieval

The runtime test created an application, retrieved it by identifier, and listed it through the collection endpoint with rule-set version and timestamps.