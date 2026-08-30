package za.co.gpbrij.origination.api;
import jakarta.validation.constraints.NotBlank;
public record ClaimRequest(@NotBlank String reviewer){}