package za.co.gpbrij.origination.api;
import za.co.gpbrij.origination.workflow.*;
import java.time.Instant;
import java.util.List;
public record WorkflowView(String applicationId,ApplicationStatus status,KycState kycState,PepState pepState,
 ReferralStatus referralStatus,String reviewer,List<StatusItem> statusHistory,List<AuditItem> auditEvents){
 public record StatusItem(ApplicationStatus status,Instant recordedAt,String source){}
 public record AuditItem(String eventType,String actor,String details,Instant occurredAt){}
}