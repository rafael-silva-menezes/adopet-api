package adopet.dto.adoption;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestAdoptionDto(@NotNull Long petId, @NotNull Long guardianId,
                                 @NotBlank String reason) {
}
