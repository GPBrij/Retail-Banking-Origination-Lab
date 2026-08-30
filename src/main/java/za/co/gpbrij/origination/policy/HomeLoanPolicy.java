package za.co.gpbrij.origination.policy;
import org.springframework.stereotype.Component;
import za.co.gpbrij.origination.api.ApplicationRequest;
import za.co.gpbrij.origination.domain.ProductType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class HomeLoanPolicy implements ProductPolicy {
 public ProductType productType(){return ProductType.HOME_LOAN;}
 public String policyName(){return "HOME_LOAN_LAB_POLICY";}
 public String policyVersion(){return "0.3.0";}
 public List<String> evaluate(ApplicationRequest r,int score){
  List<String> reasons=new ArrayList<>();
  BigDecimal disposable=r.monthlyIncome().subtract(r.monthlyExpenses()).subtract(r.existingDebt());
  if(score<650) reasons.add("HOME_LOAN_BUREAU_SCORE_BELOW_LAB_THRESHOLD");
  if(disposable.signum()<=0) reasons.add("HOME_LOAN_NO_POSITIVE_DISPOSABLE_INCOME");
  if(r.requestedAmount().compareTo(r.monthlyIncome().multiply(BigDecimal.valueOf(60)))>0)
   reasons.add("HOME_LOAN_REQUEST_EXCEEDS_LAB_LIMIT");
  return reasons;
 }
}