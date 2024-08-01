package adopet.dto.pet;

import adopet.model.PetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterPetDto(@NotNull PetType type, @NotBlank String name, @NotBlank String breed, @NotNull Integer age,
                             @NotBlank String color, @NotNull Float weight) {
}
