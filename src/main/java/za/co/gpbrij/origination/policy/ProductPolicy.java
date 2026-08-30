package za.co.gpbrij.origination.policy;
import za.co.gpbrij.origination.api.ApplicationRequest;
import za.co.gpbrij.origination.domain.ProductType;
import java.util.List;
public interface ProductPolicy {
    ProductType productType();
    String policyName();
    String policyVersion();
    List<String> evaluate(ApplicationRequest request, int bureauScore);
}