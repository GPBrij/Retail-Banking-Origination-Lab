# Governed Reviewer Workflow

## Two-dimensional state and evidence map

```text
                                      WORKFLOW STAGE
EVIDENCE DIMENSION          RECEIVED  SCREENING  REFERRED  CLAIMED  RESOLVED
Application status             [X]       [X]        [X]       [X]      [X]
KYC state                      [ ]       [X]        [X]       [X]      [X]
PEP state                      [ ]       [X]        [X]       [X]      [X]
Referral status                [ ]       [ ]        [X]       [X]      [X]
Reviewer                       [ ]       [ ]        [ ]       [X]      [X]
Status history                 [X]       [X]        [X]       [X]      [X]
Audit event                    [ ]       [X]        [X]       [X]      [X]

METAFIELDS
Trigger      : Product or synthetic screening reason requiring review
Queue entry  : ReferralEntity with OPEN status
Ownership    : Synthetic reviewer claim
Resolution   : APPROVE or DECLINE
Evidence     : StatusHistoryEntity and AuditEventEntity
Rule version : 0.4.0
Boundary     : Demonstration workflow, not operational case management
```

## State journey

```text
RECEIVED -> SCREENING -> APPROVED
                     -> DECLINED
                     -> REFERRED -> UNDER_REVIEW -> APPROVED
                                                   -> DECLINED
```

## Audit events

The implementation records `SYNTHETIC_SCREENING`, `DECISION_CREATED`, `REFERRAL_CREATED`, `REFERRAL_CLAIMED`, and `REVIEW_COMPLETED` during the matching flow.

## Current workflow limitations

- Only open referrals are returned by the queue endpoint.
- No unclaim, reassignment, escalation or service-level tracking exists.
- There is no authentication or concurrent-claim protection.
- Review notes are plain synthetic text.