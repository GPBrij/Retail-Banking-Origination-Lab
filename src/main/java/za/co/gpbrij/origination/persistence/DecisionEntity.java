package za.co.gpbrij.origination.persistence;

import jakarta.persistence.*;
import za.co.gpbrij.origination.domain.DecisionStatus;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "decisions")
public class DecisionEntity {
    @Id private String id;
    @Column(nullable=false, unique=true) private String applicationId;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private DecisionStatus status;
    @Column(nullable=false) private int bureauScore;
    @Column(nullable=false) private boolean sanctionsMatch;
    @Column(nullable=false, precision=19, scale=2) private BigDecimal disposableIncome;
    @Column(nullable=false, length=1000) private String reasonCodes;
    @Column(nullable=false) private String ruleSetVersion;
    @Column(nullable=false) private Instant decidedAt;

    protected DecisionEntity() {}
    public DecisionEntity(String id, String applicationId, DecisionStatus status, int bureauScore,
      boolean sanctionsMatch, BigDecimal disposableIncome, String reasonCodes, String ruleSetVersion, Instant decidedAt) {
        this.id=id; this.applicationId=applicationId; this.status=status; this.bureauScore=bureauScore;
        this.sanctionsMatch=sanctionsMatch; this.disposableIncome=disposableIncome; this.reasonCodes=reasonCodes;
        this.ruleSetVersion=ruleSetVersion; this.decidedAt=decidedAt;
    }
    public String getId(){return id;} public String getApplicationId(){return applicationId;}
    public DecisionStatus getStatus(){return status;} public int getBureauScore(){return bureauScore;}
    public boolean isSanctionsMatch(){return sanctionsMatch;} public BigDecimal getDisposableIncome(){return disposableIncome;}
    public String getReasonCodes(){return reasonCodes;} public String getRuleSetVersion(){return ruleSetVersion;}
    public Instant getDecidedAt(){return decidedAt;}
}
