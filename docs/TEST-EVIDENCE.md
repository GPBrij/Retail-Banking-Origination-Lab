# Test Evidence

## Two-dimensional verification map

```text
                                          TEST DIMENSION
CAPABILITY DIMENSION              CONTEXT  UNIT  INTEGRATION  RUNTIME  EVIDENCE
Application startup                 [X]     [ ]      [X]        [X]      [X]
Product policies                    [ ]     [X]      [ ]        [X]      [X]
Persistence                         [ ]     [ ]      [X]        [X]      [X]
Referral and reviewer workflow      [ ]     [ ]      [X]        [ ]      [X]
Status history and audit events     [ ]     [ ]      [X]        [ ]      [X]

METAFIELDS
Build       : Maven clean test
Runtime     : Java 21 and Spring Boot
Automated   : Context, persistence, policy and workflow tests
Current total: 7 tests
Result      : 7 passed, 0 failures, 0 errors
Limitation  : Complete manual v0.4 API transcript not yet captured
Boundary    : Synthetic test cases only
```

## Latest automated result

```text
Tests run: 7
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

## Proven automated workflow

The workflow test creates a referred synthetic application, claims the referral, approves the review, verifies the final application status, and confirms status-history and audit-event evidence.

## Earlier runtime evidence

Version 0.3 manually demonstrated successful home-loan, credit-card, savings-account and debit-card requests. Version 0.2 manually demonstrated application creation, retrieval by identifier and collection retrieval.

## Recommendation

Capture a manual v0.4 API run showing KYC referral creation, referral listing, claim, review and workflow evidence. Add only synthetic identifiers and omit local machine paths.