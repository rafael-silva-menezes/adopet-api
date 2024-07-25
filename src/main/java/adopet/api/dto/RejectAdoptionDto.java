package adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RejectAdoptionDto(@NotNull Long adoptionId, @NotBlank String justification) {
}
