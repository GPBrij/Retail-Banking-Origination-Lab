package za.co.gpbrij.origination.persistence;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="audit_events")
public class AuditEventEntity {
 @Id private String id; @Column(nullable=false) private String applicationId; @Column(nullable=false) private String eventType;
 @Column(nullable=false) private String actor; @Column(nullable=false,length=2000) private String details; @Column(nullable=false) private Instant occurredAt;
 protected AuditEventEntity(){}
 public AuditEventEntity(String id,String app,String type,String actor,String details,Instant at){this.id=id;applicationId=app;eventType=type;this.actor=actor;this.details=details;occurredAt=at;}
 public String getId(){return id;} public String getApplicationId(){return applicationId;} public String getEventType(){return eventType;}
 public String getActor(){return actor;} public String getDetails(){return details;} public Instant getOccurredAt(){return occurredAt;}
}