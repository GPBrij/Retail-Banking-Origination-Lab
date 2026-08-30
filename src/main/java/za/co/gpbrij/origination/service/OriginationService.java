package za.co.gpbrij.origination.service;
import org.springframework.stereotype.Service;
import za.co.gpbrij.origination.api.*;
import za.co.gpbrij.origination.domain.DecisionStatus;
import za.co.gpbrij.origination.integration.*;
import za.co.gpbrij.origination.rules.InternalCreditRules;
import java.math.BigDecimal;
import java.util.*;
@Service
public class OriginationService {
  private final MockCreditBureauClient bureau;
  private final MockSanctionsClient sanctions;
  private final InternalCreditRules rules;
  public OriginationService(MockCreditBureauClient b, MockSanctionsClient s, InternalCreditRules r) { bureau=b; sanctions=s; rules=r; }
  public DecisionResponse assess(ApplicationRequest request) {
    int score = bureau.score(request.applicantRef());
    boolean match = sanctions.isMatch(request.fullName(), request.countryCode());
    var reasons = rules.evaluate(request, score, match);
    BigDecimal disposable = request.monthlyIncome().subtract(request.monthlyExpenses()).subtract(request.existingDebt());
    DecisionStatus status = reasons.contains("SYNTHETIC_SANCTIONS_MATCH") ? DecisionStatus.DECLINED :
      reasons.isEmpty() ? DecisionStatus.APPROVED : DecisionStatus.REFER;
    return new DecisionResponse(UUID.randomUUID().toString(), status, score, match, disposable, reasons);
  }
}
