# Test Evidence

## Two-dimensional verification view

```text
                                      TEST LAYER
SCENARIO DIMENSION          BUILD  CONTEXT  POST  GET ONE  GET ALL  EVIDENCE
Version 0.1 referral         [X]     [X]     [X]    [ ]      [ ]      [X]
Version 0.1 approval         [X]     [X]     [X]    [ ]      [ ]      [X]
Version 0.1 decline          [X]     [X]     [X]    [ ]      [ ]      [X]
Version 0.2 persistence      [X]     [X]     [X]    [X]      [X]      [X]

METAFIELDS
Runtime     : Java 21 and Spring Boot
Build       : Maven clean test
Automated   : Context test and persistence-flow test
Manual      : POST, GET by identifier, GET collection
Data        : Synthetic only
Evidence    : Console output and response fields
Limitation  : No load, security, mutation, or concurrency testing yet
```

## Automated result

```text
Tests run: 2
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

## Version 0.1 manual evidence

| Scenario | Status | Score | Sanctions | Disposable income | Reasons |
|---|---:|---:|---:|---:|---|
| Low synthetic bureau score | REFER | 546 | false | 22000 | BUREAU_SCORE_BELOW_LAB_THRESHOLD |
| Threshold met | APPROVED | 580 | false | 22000 | None |
| Synthetic sanctions match | DECLINED | 538 | true | 13000 | SYNTHETIC_SANCTIONS_MATCH; BUREAU_SCORE_BELOW_LAB_THRESHOLD |

## Version 0.2 manual evidence

```text
applicantRef     : SYNTH-V02-1001
productType      : CREDIT_CARD
status           : APPROVED
bureauScore      : 641
sanctionsMatch   : False
disposableIncome : 22000
ruleSetVersion   : 0.2.0
```

The application identifier and timestamps are generated at runtime and therefore vary by execution.