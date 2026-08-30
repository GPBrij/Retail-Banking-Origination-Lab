# Version 0.3 - Product Policy Strategy

## Two-dimensional policy map

```text
                              POLICY DIMENSION
PRODUCT DIMENSION      BUREAU  AFFORDABILITY  AMOUNT  SANCTIONS  EXPLAIN
Home loan                [X]       [X]         [X]      [X]       [X]
Credit card              [X]       [X]         [X]      [X]       [X]
Savings account          [ ]       [ ]         [X]      [X]       [X]
Debit card               [ ]       [ ]         [X]      [X]       [X]

METAFIELDS
Pattern      : Strategy plus policy resolver
Input        : Product type and synthetic application
Selection    : ProductPolicyFactory
Strategies   : HomeLoan, CreditCard, SavingsAccount, DebitCard
Common rule  : Synthetic sanctions override
Evidence     : Product-specific reason codes and policy version 0.3.0
Control      : Protected traits excluded
Boundary     : Synthetic learning rules, not lending policy
```

## Design

`ProductPolicy` defines the common strategy contract. Spring discovers four policy implementations. `ProductPolicyFactory` resolves the correct strategy from `ProductType`. `OriginationService` orchestrates common screening, delegates product rules, persists the result, and returns explainable evidence.

## Synthetic rules

- Home loan: bureau threshold 650, positive disposable income, amount no greater than 60 times monthly income.
- Credit card: bureau threshold 580, positive disposable income, amount no greater than 12 times monthly income.
- Savings account: requested credit amount must be zero; no bureau threshold is applied by the policy.
- Debit card: requested credit amount must be zero; no bureau threshold is applied by the policy.

These values are laboratory assumptions only.