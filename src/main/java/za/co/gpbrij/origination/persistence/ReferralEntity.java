package za.co.gpbrij.origination.persistence;
import jakarta.persistence.*;
import za.co.gpbrij.origination.workflow.ReferralStatus;
import java.time.Instant;
@Entity @Table(name="referrals")
public class ReferralEntity {
 @Id private String id; @Column(nullable=false,unique=true) private String applicationId;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ReferralStatus status;
 @Column(length=1000) private String reasonCodes; private String reviewer; private String reviewNote;
 @Column(nullable=false) private Instant createdAt; private Instant claimedAt; private Instant resolvedAt;
 protected ReferralEntity(){}
 public ReferralEntity(String id,String app,String reasons,Instant created){this.id=id;applicationId=app;reasonCodes=reasons;createdAt=created;status=ReferralStatus.OPEN;}
 public String getId(){return id;} public String getApplicationId(){return applicationId;} public ReferralStatus getStatus(){return status;}
 public String getReasonCodes(){return reasonCodes;} public String getReviewer(){return reviewer;} public String getReviewNote(){return reviewNote;}
 public Instant getCreatedAt(){return createdAt;} public Instant getClaimedAt(){return claimedAt;} public Instant getResolvedAt(){return resolvedAt;}
 public void claim(String value){reviewer=value;status=ReferralStatus.CLAIMED;claimedAt=Instant.now();}
 public void resolve(ReferralStatus result,String note){status=result;reviewNote=note;resolvedAt=Instant.now();}
}