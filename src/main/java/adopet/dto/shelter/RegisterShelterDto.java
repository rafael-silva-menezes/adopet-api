package adopet.dto.shelter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterShelterDto(@NotBlank String name,
                                 @NotBlank @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}") String phone,
                                 @Email @NotBlank String email) {
}
