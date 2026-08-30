package za.co.gpbrij.origination.integration;
import org.springframework.stereotype.Component;
import java.util.Set;
@Component
public class MockSanctionsClient {
  private final Set<String> syntheticWatchlist = Set.of("TEST BLOCKED PERSON", "SYNTHETIC MATCH");
  public boolean isMatch(String fullName, String countryCode) {
    return syntheticWatchlist.contains(fullName.trim().toUpperCase());
  }
}
