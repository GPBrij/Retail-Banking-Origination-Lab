package za.co.gpbrij.origination.api;
import za.co.gpbrij.origination.domain.DecisionStatus;
import za.co.gpbrij.origination.domain.ProductType;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
public record ApplicationHistoryResponse(String applicationId, String applicantRef, ProductType productType,
 DecisionStatus status, int bureauScore, boolean sanctionsMatch, BigDecimal disposableIncome,
 List<String> reasons, String ruleSetVersion, Instant createdAt, Instant decidedAt) {}
