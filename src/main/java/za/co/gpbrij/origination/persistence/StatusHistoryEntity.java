package za.co.gpbrij.origination.persistence;
import jakarta.persistence.*;
import za.co.gpbrij.origination.workflow.ApplicationStatus;
import java.time.Instant;
@Entity @Table(name="application_status_history")
public class StatusHistoryEntity {
 @Id private String id; @Column(nullable=false) private String applicationId;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ApplicationStatus status;
 @Column(nullable=false) private Instant recordedAt; @Column(nullable=false) private String source;
 protected StatusHistoryEntity(){}
 public StatusHistoryEntity(String id,String app,ApplicationStatus status,Instant at,String source){this.id=id;applicationId=app;this.status=status;recordedAt=at;this.source=source;}
 public String getId(){return id;} public String getApplicationId(){return applicationId;} public ApplicationStatus getStatus(){return status;}
 public Instant getRecordedAt(){return recordedAt;} public String getSource(){return source;}
}