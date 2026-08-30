package za.co.gpbrij.origination.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.gpbrij.origination.api.*;
import za.co.gpbrij.origination.domain.DecisionStatus;
import za.co.gpbrij.origination.integration.*;
import za.co.gpbrij.origination.persistence.*;
import za.co.gpbrij.origination.policy.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
@Service
public class OriginationService {
 private final MockCreditBureauClient bureau; private final MockSanctionsClient sanctions;
 private final ProductPolicyFactory policyFactory; private final ApplicationRepository applications;
 private final DecisionRepository decisions;
 public OriginationService(MockCreditBureauClient b,MockSanctionsClient s,ProductPolicyFactory f,
  ApplicationRepository a,DecisionRepository d){bureau=b;sanctions=s;policyFactory=f;applications=a;decisions=d;}
 @Transactional public DecisionResponse assess(ApplicationRequest r){
  String applicationId=UUID.randomUUID().toString(); Instant now=Instant.now();
  applications.save(new ApplicationEntity(applicationId,r.applicantRef(),r.productType(),r.monthlyIncome(),
   r.monthlyExpenses(),r.existingDebt(),r.requestedAmount(),r.creditBureauConsent(),r.fullName(),r.countryCode(),now));
  int score=bureau.score(r.applicantRef()); boolean match=sanctions.isMatch(r.fullName(),r.countryCode());
  ProductPolicy policy=policyFactory.resolve(r.productType()); List<String> reasons=new ArrayList<>(policy.evaluate(r,score));
  if(match) reasons.add(0,"SYNTHETIC_SANCTIONS_MATCH");
  BigDecimal disposable=r.monthlyIncome().subtract(r.monthlyExpenses()).subtract(r.existingDebt());
  DecisionStatus status=match?DecisionStatus.DECLINED:reasons.isEmpty()?DecisionStatus.APPROVED:DecisionStatus.REFER;
  decisions.save(new DecisionEntity(UUID.randomUUID().toString(),applicationId,status,score,match,disposable,
   String.join(",",reasons),policy.policyVersion(),Instant.now()));
  return new DecisionResponse(applicationId,status,score,match,disposable,reasons);
 }
 @Transactional(readOnly=true) public ApplicationHistoryResponse find(String id){
  ApplicationEntity a=applications.findById(id).orElseThrow(()->new NoSuchElementException("Application not found"));
  DecisionEntity d=decisions.findByApplicationId(id).orElseThrow(()->new NoSuchElementException("Decision not found"));
  List<String> reasons=d.getReasonCodes().isBlank()?List.of():Arrays.asList(d.getReasonCodes().split(","));
  return new ApplicationHistoryResponse(a.getId(),a.getApplicantRef(),a.getProductType(),d.getStatus(),d.getBureauScore(),
   d.isSanctionsMatch(),d.getDisposableIncome(),reasons,d.getRuleSetVersion(),a.getCreatedAt(),d.getDecidedAt());
 }
 @Transactional(readOnly=true) public List<ApplicationHistoryResponse> list(){return applications.findAll().stream().map(a->find(a.getId())).toList();}
}