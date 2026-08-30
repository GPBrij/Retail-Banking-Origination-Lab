package za.co.gpbrij.origination.policy;
import org.springframework.stereotype.Component;
import za.co.gpbrij.origination.domain.ProductType;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
@Component
public class ProductPolicyFactory {
 private final Map<ProductType,ProductPolicy> policies=new EnumMap<>(ProductType.class);
 public ProductPolicyFactory(List<ProductPolicy> productPolicies){
  productPolicies.forEach(p->policies.put(p.productType(),p));
  for(ProductType type:ProductType.values())
   if(!policies.containsKey(type)) throw new IllegalStateException("No policy registered for "+type);
 }
 public ProductPolicy resolve(ProductType type){
  ProductPolicy policy=policies.get(type);
  if(policy==null) throw new IllegalArgumentException("Unsupported product type: "+type);
  return policy;
 }
}