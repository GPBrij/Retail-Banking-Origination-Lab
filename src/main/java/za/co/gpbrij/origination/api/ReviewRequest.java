package za.co.gpbrij.origination.api;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import za.co.gpbrij.origination.workflow.ReviewOutcome;
public record ReviewRequest(@NotBlank String reviewer,@NotNull ReviewOutcome outcome,@NotBlank String note){}