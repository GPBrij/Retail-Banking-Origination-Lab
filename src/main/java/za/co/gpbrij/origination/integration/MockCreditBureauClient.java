package za.co.gpbrij.origination.integration;
import org.springframework.stereotype.Component;
@Component
public class MockCreditBureauClient {
  public int score(String applicantRef) {
    return 500 + Math.floorMod(applicantRef.hashCode(), 301);
  }
}
