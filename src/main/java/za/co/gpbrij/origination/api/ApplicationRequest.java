package za.co.gpbrij.origination.api;
import jakarta.validation.constraints.*;
import za.co.gpbrij.origination.domain.ProductType;
import java.math.BigDecimal;
public record ApplicationRequest(
 @NotBlank String applicantRef,
 @NotNull ProductType productType,
 @NotNull @Positive BigDecimal monthlyIncome,
 @NotNull @PositiveOrZero BigDecimal monthlyExpenses,
 @NotNull @PositiveOrZero BigDecimal existingDebt,
 @NotNull @PositiveOrZero BigDecimal requestedAmount,
 @AssertTrue(message="Credit bureau consent is required") boolean creditBureauConsent,
 @NotBlank String fullName,
 @NotBlank String countryCode
) {}
