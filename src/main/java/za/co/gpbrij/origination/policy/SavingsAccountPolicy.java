package za.co.gpbrij.origination.policy;
import org.springframework.stereotype.Component;
import za.co.gpbrij.origination.api.ApplicationRequest;
import za.co.gpbrij.origination.domain.ProductType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class SavingsAccountPolicy implements ProductPolicy {
 public ProductType productType(){return ProductType.SAVINGS_ACCOUNT;}
 public String policyName(){return "SAVINGS_ACCOUNT_LAB_POLICY";}
 public String policyVersion(){return "0.3.0";}
 public List<String> evaluate(ApplicationRequest r,int score){
  List<String> reasons=new ArrayList<>();
  if(r.requestedAmount().compareTo(BigDecimal.ZERO)!=0)
   reasons.add("SAVINGS_ACCOUNT_REQUESTED_AMOUNT_MUST_BE_ZERO");
  return reasons;
 }
}