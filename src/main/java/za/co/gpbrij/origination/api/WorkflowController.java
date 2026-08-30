package za.co.gpbrij.origination.api;
import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import za.co.gpbrij.origination.persistence.ReferralEntity;import za.co.gpbrij.origination.service.WorkflowService;import java.util.List;
@RestController @RequestMapping("/api/v1/workflow")
public class WorkflowController {
 private final WorkflowService service;public WorkflowController(WorkflowService s){service=s;}
 @GetMapping("/referrals") public List<ReferralEntity> open(){return service.open();}
 @PostMapping("/referrals/{applicationId}/claim") public ReferralEntity claim(@PathVariable String applicationId,@Valid @RequestBody ClaimRequest r){return service.claim(applicationId,r.reviewer());}
 @PostMapping("/referrals/{applicationId}/review") public ReferralEntity review(@PathVariable String applicationId,@Valid @RequestBody ReviewRequest r){return service.review(applicationId,r);}
 @GetMapping("/applications/{applicationId}") public WorkflowView view(@PathVariable String applicationId){return service.view(applicationId);}
}