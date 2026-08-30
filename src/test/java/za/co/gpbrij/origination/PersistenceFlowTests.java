package za.co.gpbrij.origination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.gpbrij.origination.api.ApplicationRequest;
import za.co.gpbrij.origination.domain.ProductType;
import za.co.gpbrij.origination.service.OriginationService;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest class PersistenceFlowTests {
 @Autowired OriginationService service;
 @Test void persistsApplicationDecisionAndPolicyVersion(){
  var r=new ApplicationRequest("SYNTH-V03",ProductType.CREDIT_CARD,new BigDecimal("45000"),new BigDecimal("18000"),
   new BigDecimal("5000"),new BigDecimal("75000"),true,"DEMO CUSTOMER","ZA");
  var decision=service.assess(r); var history=service.find(decision.applicationId());
  assertThat(history.applicationId()).isEqualTo(decision.applicationId());
  assertThat(history.ruleSetVersion()).isEqualTo("0.3.0"); assertThat(history.decidedAt()).isNotNull();
 }
}