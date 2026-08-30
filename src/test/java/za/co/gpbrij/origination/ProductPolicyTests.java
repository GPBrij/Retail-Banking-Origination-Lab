package za.co.gpbrij.origination;
import org.junit.jupiter.api.Test;
import za.co.gpbrij.origination.api.ApplicationRequest;
import za.co.gpbrij.origination.domain.ProductType;
import za.co.gpbrij.origination.policy.*;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
class ProductPolicyTests {
 private ApplicationRequest request(ProductType type,BigDecimal amount){return new ApplicationRequest("SYNTH-POLICY",type,
  new BigDecimal("45000"),new BigDecimal("18000"),new BigDecimal("5000"),amount,true,"DEMO CUSTOMER","ZA");}
 @Test void homeLoanUsesHigherSyntheticBureauThreshold(){
  assertThat(new HomeLoanPolicy().evaluate(request(ProductType.HOME_LOAN,new BigDecimal("1000000")),640))
   .contains("HOME_LOAN_BUREAU_SCORE_BELOW_LAB_THRESHOLD");
 }
 @Test void creditCardAcceptsScoreAtItsSyntheticThreshold(){
  assertThat(new CreditCardPolicy().evaluate(request(ProductType.CREDIT_CARD,new BigDecimal("75000")),580)).isEmpty();
 }
 @Test void savingsDoesNotApplyBureauThreshold(){
  assertThat(new SavingsAccountPolicy().evaluate(request(ProductType.SAVINGS_ACCOUNT,BigDecimal.ZERO),500)).isEmpty();
 }
 @Test void debitCardRequiresZeroRequestedAmount(){
  assertThat(new DebitCardPolicy().evaluate(request(ProductType.DEBIT_CARD,BigDecimal.ONE),500))
   .contains("DEBIT_CARD_REQUESTED_AMOUNT_MUST_BE_ZERO");
 }
}