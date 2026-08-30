package za.co.gpbrij.origination.rules;
import org.springframework.stereotype.Component;
import za.co.gpbrij.origination.api.ApplicationRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class InternalCreditRules {
  public List<String> evaluate(ApplicationRequest request, int bureauScore, boolean sanctionsMatch) {
    List<String> reasons = new ArrayList<>();
    BigDecimal disposable = request.monthlyIncome().subtract(request.monthlyExpenses()).subtract(request.existingDebt());
    if (sanctionsMatch) reasons.add("SYNTHETIC_SANCTIONS_MATCH");
    if (bureauScore < 580) reasons.add("BUREAU_SCORE_BELOW_LAB_THRESHOLD");
    if (disposable.signum() <= 0) reasons.add("NO_POSITIVE_DISPOSABLE_INCOME");
    if (request.requestedAmount().compareTo(request.monthlyIncome().multiply(BigDecimal.valueOf(60))) > 0)
      reasons.add("REQUEST_EXCEEDS_LAB_LIMIT");
    return reasons;
  }
}
