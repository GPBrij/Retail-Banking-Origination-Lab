package za.co.gpbrij.origination.api;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.gpbrij.origination.service.OriginationService;
import java.util.List;
@RestController
@RequestMapping("/api/v1/applications")
public class OriginationController {
  private final OriginationService service;
  public OriginationController(OriginationService service) { this.service = service; }
  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public DecisionResponse create(@Valid @RequestBody ApplicationRequest request) { return service.assess(request); }
  @GetMapping("/{applicationId}")
  public ApplicationHistoryResponse get(@PathVariable String applicationId) { return service.find(applicationId); }
  @GetMapping
  public List<ApplicationHistoryResponse> list() { return service.list(); }
}
