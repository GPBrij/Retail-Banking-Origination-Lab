package za.co.gpbrij.origination.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.gpbrij.origination.api.*;
import za.co.gpbrij.origination.domain.DecisionStatus;
import za.co.gpbrij.origination.integration.*;
import za.co.gpbrij.origination.persistence.*;
import za.co.gpbrij.origination.rules.InternalCreditRules;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Service
public class OriginationService {
  public static final String RULE_SET_VERSION = "0.2.0";
  private final MockCreditBureauClient bureau;
  private final MockSanctionsClient sanctions;
  private final InternalCreditRules rules;
  private final ApplicationRepository applications;
  private final DecisionRepository decisions;

  public OriginationService(MockCreditBureauClient bureau, MockSanctionsClient sanctions, InternalCreditRules rules,
      ApplicationRepository applications, DecisionRepository decisions) {
    this.bureau=bureau; this.sanctions=sanctions; this.rules=rules;
    this.applications=applications; this.decisions=decisions;
  }

  @Transactional
  public DecisionResponse assess(ApplicationRequest request) {
    String applicationId = UUID.randomUUID().toString();
    Instant now = Instant.now();
    applications.save(new ApplicationEntity(applicationId, request.applicantRef(), request.productType(),
      request.monthlyIncome(), request.monthlyExpenses(), request.existingDebt(), request.requestedAmount(),
      request.creditBureauConsent(), request.fullName(), request.countryCode(), now));

    int score = bureau.score(request.applicantRef());
    boolean match = sanctions.isMatch(request.fullName(), request.countryCode());
    List<String> reasons = rules.evaluate(request, score, match);
    BigDecimal disposable = request.monthlyIncome().subtract(request.monthlyExpenses()).subtract(request.existingDebt());
    DecisionStatus status = reasons.contains("SYNTHETIC_SANCTIONS_MATCH") ? DecisionStatus.DECLINED :
      reasons.isEmpty() ? DecisionStatus.APPROVED : DecisionStatus.REFER;

    decisions.save(new DecisionEntity(UUID.randomUUID().toString(), applicationId, status, score, match,
      disposable, String.join(",", reasons), RULE_SET_VERSION, Instant.now()));
    return new DecisionResponse(applicationId, status, score, match, disposable, reasons);
  }

  @Transactional(readOnly=true)
  public ApplicationHistoryResponse find(String applicationId) {
    ApplicationEntity app = applications.findById(applicationId)
      .orElseThrow(() -> new NoSuchElementException("Application not found"));
    DecisionEntity decision = decisions.findByApplicationId(applicationId)
      .orElseThrow(() -> new NoSuchElementException("Decision not found"));
    List<String> reasons = decision.getReasonCodes().isBlank() ? List.of() :
      Arrays.asList(decision.getReasonCodes().split(","));
    return new ApplicationHistoryResponse(app.getId(), app.getApplicantRef(), app.getProductType(),
      decision.getStatus(), decision.getBureauScore(), decision.isSanctionsMatch(), decision.getDisposableIncome(),
      reasons, decision.getRuleSetVersion(), app.getCreatedAt(), decision.getDecidedAt());
  }

  @Transactional(readOnly=true)
  public List<ApplicationHistoryResponse> list() {
    return applications.findAll().stream().map(a -> find(a.getId())).toList();
  }
}
