package za.co.gpbrij.origination.integration;
import org.springframework.stereotype.Component;
import za.co.gpbrij.origination.workflow.*;
@Component
public class MockKycPepService {
 public KycState kyc(String name){return name.toUpperCase().contains("KYC REVIEW")?KycState.SYNTHETIC_REVIEW:KycState.SYNTHETIC_CLEAR;}
 public PepState pep(String name){return name.toUpperCase().contains("PEP REVIEW")?PepState.SYNTHETIC_POTENTIAL_MATCH:PepState.SYNTHETIC_NO_MATCH;}
}