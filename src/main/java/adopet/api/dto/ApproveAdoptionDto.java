package adopet.api.dto;

import jakarta.validation.constraints.NotNull;

public record ApproveAdoptionDto(@NotNull Long adoptionId) {
}
