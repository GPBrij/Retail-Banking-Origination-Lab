package za.co.gpbrij.origination.persistence;

import jakarta.persistence.*;
import za.co.gpbrij.origination.domain.ProductType;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "applications")
public class ApplicationEntity {
    @Id private String id;
    @Column(nullable=false) private String applicantRef;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private ProductType productType;
    @Column(nullable=false, precision=19, scale=2) private BigDecimal monthlyIncome;
    @Column(nullable=false, precision=19, scale=2) private BigDecimal monthlyExpenses;
    @Column(nullable=false, precision=19, scale=2) private BigDecimal existingDebt;
    @Column(nullable=false, precision=19, scale=2) private BigDecimal requestedAmount;
    @Column(nullable=false) private boolean creditBureauConsent;
    @Column(nullable=false) private String fullName;
    @Column(nullable=false) private String countryCode;
    @Column(nullable=false) private Instant createdAt;

    protected ApplicationEntity() {}
    public ApplicationEntity(String id, String applicantRef, ProductType productType, BigDecimal monthlyIncome,
      BigDecimal monthlyExpenses, BigDecimal existingDebt, BigDecimal requestedAmount,
      boolean creditBureauConsent, String fullName, String countryCode, Instant createdAt) {
        this.id=id; this.applicantRef=applicantRef; this.productType=productType; this.monthlyIncome=monthlyIncome;
        this.monthlyExpenses=monthlyExpenses; this.existingDebt=existingDebt; this.requestedAmount=requestedAmount;
        this.creditBureauConsent=creditBureauConsent; this.fullName=fullName; this.countryCode=countryCode; this.createdAt=createdAt;
    }
    public String getId(){return id;} public String getApplicantRef(){return applicantRef;}
    public ProductType getProductType(){return productType;} public BigDecimal getMonthlyIncome(){return monthlyIncome;}
    public BigDecimal getMonthlyExpenses(){return monthlyExpenses;} public BigDecimal getExistingDebt(){return existingDebt;}
    public BigDecimal getRequestedAmount(){return requestedAmount;} public boolean isCreditBureauConsent(){return creditBureauConsent;}
    public String getFullName(){return fullName;} public String getCountryCode(){return countryCode;} public Instant getCreatedAt(){return createdAt;}
}
