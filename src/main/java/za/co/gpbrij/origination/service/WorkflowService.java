package za.co.gpbrij.origination.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.gpbrij.origination.api.*;
import za.co.gpbrij.origination.persistence.*;
import za.co.gpbrij.origination.workflow.*;
import java.time.Instant;
import java.util.*;
@Service
public class WorkflowService {
 private final ApplicationRepository apps; private final ReferralRepository referrals; private final StatusHistoryRepository history; private final AuditEventRepository audits;
 public WorkflowService(ApplicationRepository a,ReferralRepository r,StatusHistoryRepository h,AuditEventRepository e){apps=a;referrals=r;history=h;audits=e;}
 @Transactional public ReferralEntity claim(String id,String reviewer){
  ReferralEntity r=referrals.findByApplicationId(id).orElseThrow(()->new NoSuchElementException("Referral not found"));r.claim(reviewer);referrals.save(r);
  change(id,ApplicationStatus.UNDER_REVIEW,reviewer);audit(id,"REFERRAL_CLAIMED",reviewer,"Synthetic referral claimed");return r;
 }
 @Transactional public ReferralEntity review(String id,ReviewRequest req){
  ReferralEntity r=referrals.findByApplicationId(id).orElseThrow(()->new NoSuchElementException("Referral not found"));
  if(r.getReviewer()==null||!r.getReviewer().equals(req.reviewer()))throw new IllegalStateException("Referral must be claimed by this reviewer");
  ReferralStatus rs=req.outcome()==ReviewOutcome.APPROVE?ReferralStatus.APPROVED:ReferralStatus.DECLINED;r.resolve(rs,req.note());referrals.save(r);
  change(id,req.outcome()==ReviewOutcome.APPROVE?ApplicationStatus.APPROVED:ApplicationStatus.DECLINED,req.reviewer());
  audit(id,"REVIEW_COMPLETED",req.reviewer(),req.outcome()+": "+req.note());return r;
 }
 @Transactional(readOnly=true) public List<ReferralEntity> open(){return referrals.findByStatus(ReferralStatus.OPEN);}
 @Transactional(readOnly=true) public WorkflowView view(String id){
  ApplicationEntity a=apps.findById(id).orElseThrow(()->new NoSuchElementException("Application not found"));ReferralEntity r=referrals.findByApplicationId(id).orElse(null);
  var hs=history.findByApplicationIdOrderByRecordedAtAsc(id).stream().map(x->new WorkflowView.StatusItem(x.getStatus(),x.getRecordedAt(),x.getSource())).toList();
  var es=audits.findByApplicationIdOrderByOccurredAtAsc(id).stream().map(x->new WorkflowView.AuditItem(x.getEventType(),x.getActor(),x.getDetails(),x.getOccurredAt())).toList();
  return new WorkflowView(id,a.getApplicationStatus(),a.getKycState(),a.getPepState(),r==null?null:r.getStatus(),r==null?null:r.getReviewer(),hs,es);
 }
 @Transactional public void change(String id,ApplicationStatus status,String source){ApplicationEntity a=apps.findById(id).orElseThrow();a.setApplicationStatus(status);apps.save(a);history.save(new StatusHistoryEntity(UUID.randomUUID().toString(),id,status,Instant.now(),source));}
 @Transactional public void audit(String id,String type,String actor,String details){audits.save(new AuditEventEntity(UUID.randomUUID().toString(),id,type,actor,details,Instant.now()));}
 @Transactional public void refer(String id,List<String> reasons){referrals.save(new ReferralEntity(UUID.randomUUID().toString(),id,String.join(",",reasons),Instant.now()));change(id,ApplicationStatus.REFERRED,"SYSTEM");audit(id,"REFERRAL_CREATED","SYSTEM",String.join(",",reasons));}
}