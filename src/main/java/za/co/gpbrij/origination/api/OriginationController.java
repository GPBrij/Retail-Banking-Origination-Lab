package za.co.gpbrij.origination.api;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.gpbrij.origination.service.OriginationService;
@RestController
@RequestMapping("/api/v1/applications")
public class OriginationController {
  private final OriginationService service;
  public OriginationController(OriginationService service) { this.service = service; }
  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public DecisionResponse create(@Valid @RequestBody ApplicationRequest request) { return service.assess(request); }
}
