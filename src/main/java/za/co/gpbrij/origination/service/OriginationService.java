package za.co.gpbrij.origination.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.gpbrij.origination.api.*;import za.co.gpbrij.origination.domain.DecisionStatus;import za.co.gpbrij.origination.integration.*;
import za.co.gpbrij.origination.persistence.*;import za.co.gpbrij.origination.policy.*;import za.co.gpbrij.origination.workflow.*;
import java.math.BigDecimal;import java.time.Instant;import java.util.*;
@Service
public class OriginationService {
 private final MockCreditBureauClient bureau;private final MockSanctionsClient sanctions;private final MockKycPepService screening;
 private final ProductPolicyFactory policies;private final ApplicationRepository applications;private final DecisionRepository decisions;private final WorkflowService workflow;
 public OriginationService(MockCreditBureauClient b,MockSanctionsClient s,MockKycPepService k,ProductPolicyFactory p,ApplicationRepository a,DecisionRepository d,WorkflowService w){bureau=b;sanctions=s;screening=k;policies=p;applications=a;decisions=d;workflow=w;}
 @Transactional public DecisionResponse assess(ApplicationRequest r){
  String id=UUID.randomUUID().toString();ApplicationEntity app=new ApplicationEntity(id,r.applicantRef(),r.productType(),r.monthlyIncome(),r.monthlyExpenses(),r.existingDebt(),r.requestedAmount(),r.creditBureauConsent(),r.fullName(),r.countryCode(),Instant.now());applications.save(app);
  workflow.change(id,ApplicationStatus.SCREENING,"SYSTEM");KycState kyc=screening.kyc(r.fullName());PepState pep=screening.pep(r.fullName());app.setKycState(kyc);app.setPepState(pep);applications.save(app);workflow.audit(id,"SYNTHETIC_SCREENING","SYSTEM","KYC="+kyc+",PEP="+pep);
  int score=bureau.score(r.applicantRef());boolean match=sanctions.isMatch(r.fullName(),r.countryCode());ProductPolicy policy=policies.resolve(r.productType());List<String> reasons=new ArrayList<>(policy.evaluate(r,score));
  if(kyc==KycState.SYNTHETIC_REVIEW)reasons.add("SYNTHETIC_KYC_REVIEW");if(pep==PepState.SYNTHETIC_POTENTIAL_MATCH)reasons.add("SYNTHETIC_PEP_REVIEW");if(match)reasons.add(0,"SYNTHETIC_SANCTIONS_MATCH");
  BigDecimal disposable=r.monthlyIncome().subtract(r.monthlyExpenses()).subtract(r.existingDebt());DecisionStatus status=match?DecisionStatus.DECLINED:reasons.isEmpty()?DecisionStatus.APPROVED:DecisionStatus.REFER;
  decisions.save(new DecisionEntity(UUID.randomUUID().toString(),id,status,score,match,disposable,String.join(",",reasons),"0.4.0",Instant.now()));workflow.audit(id,"DECISION_CREATED","SYSTEM",status+":"+String.join(",",reasons));
  if(status==DecisionStatus.REFER)workflow.refer(id,reasons);else workflow.change(id,status==DecisionStatus.APPROVED?ApplicationStatus.APPROVED:ApplicationStatus.DECLINED,"SYSTEM");
  return new DecisionResponse(id,status,score,match,disposable,reasons);
 }
 @Transactional(readOnly=true) public ApplicationHistoryResponse find(String id){ApplicationEntity a=applications.findById(id).orElseThrow(()->new NoSuchElementException("Application not found"));DecisionEntity d=decisions.findByApplicationId(id).orElseThrow(()->new NoSuchElementException("Decision not found"));List<String> reasons=d.getReasonCodes().isBlank()?List.of():Arrays.asList(d.getReasonCodes().split(","));return new ApplicationHistoryResponse(a.getId(),a.getApplicantRef(),a.getProductType(),d.getStatus(),d.getBureauScore(),d.isSanctionsMatch(),d.getDisposableIncome(),reasons,d.getRuleSetVersion(),a.getCreatedAt(),d.getDecidedAt());}
 @Transactional(readOnly=true) public List<ApplicationHistoryResponse> list(){return applications.findAll().stream().map(a->find(a.getId())).toList();}
}