package za.co.gpbrij.origination.api;
import za.co.gpbrij.origination.domain.DecisionStatus;
import java.math.BigDecimal;
import java.util.List;
public record DecisionResponse(String applicationId, DecisionStatus status, int bureauScore,
 boolean sanctionsMatch, BigDecimal disposableIncome, List<String> reasons) {}
