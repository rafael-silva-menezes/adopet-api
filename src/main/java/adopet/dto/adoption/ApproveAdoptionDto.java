package adopet.dto.adoption;

import jakarta.validation.constraints.NotNull;

public record ApproveAdoptionDto(@NotNull Long adoptionId) {
}
