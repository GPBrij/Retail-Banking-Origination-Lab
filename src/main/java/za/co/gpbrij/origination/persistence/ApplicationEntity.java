package za.co.gpbrij.origination.persistence;
import jakarta.persistence.*;
import za.co.gpbrij.origination.domain.ProductType;
import za.co.gpbrij.origination.workflow.*;
import java.math.BigDecimal;
import java.time.Instant;
@Entity @Table(name="applications")
public class ApplicationEntity {
 @Id private String id;
 @Column(nullable=false) private String applicantRef;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ProductType productType;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal monthlyIncome;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal monthlyExpenses;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal existingDebt;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal requestedAmount;
 @Column(nullable=false) private boolean creditBureauConsent;
 @Column(nullable=false) private String fullName;
 @Column(nullable=false) private String countryCode;
 @Column(nullable=false) private Instant createdAt;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ApplicationStatus applicationStatus;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private KycState kycState;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private PepState pepState;
 protected ApplicationEntity(){}
 public ApplicationEntity(String id,String ref,ProductType type,BigDecimal income,BigDecimal expenses,BigDecimal debt,
  BigDecimal amount,boolean consent,String name,String country,Instant created){
  this.id=id;applicantRef=ref;productType=type;monthlyIncome=income;monthlyExpenses=expenses;existingDebt=debt;
  requestedAmount=amount;creditBureauConsent=consent;fullName=name;countryCode=country;createdAt=created;
  applicationStatus=ApplicationStatus.RECEIVED;kycState=KycState.NOT_STARTED;pepState=PepState.NOT_SCREENED;
 }
 public String getId(){return id;} public String getApplicantRef(){return applicantRef;} public ProductType getProductType(){return productType;}
 public BigDecimal getMonthlyIncome(){return monthlyIncome;} public BigDecimal getMonthlyExpenses(){return monthlyExpenses;}
 public BigDecimal getExistingDebt(){return existingDebt;} public BigDecimal getRequestedAmount(){return requestedAmount;}
 public boolean isCreditBureauConsent(){return creditBureauConsent;} public String getFullName(){return fullName;}
 public String getCountryCode(){return countryCode;} public Instant getCreatedAt(){return createdAt;}
 public ApplicationStatus getApplicationStatus(){return applicationStatus;} public KycState getKycState(){return kycState;}
 public PepState getPepState(){return pepState;}
 public void setApplicationStatus(ApplicationStatus v){applicationStatus=v;} public void setKycState(KycState v){kycState=v;}
 public void setPepState(PepState v){pepState=v;}
}