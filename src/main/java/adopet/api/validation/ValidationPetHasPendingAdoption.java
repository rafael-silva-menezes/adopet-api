package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;
import adopet.api.model.AdoptionStatus;
import adopet.api.repository.AdoptionRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationPetHasPendingAdoption implements IValidationRequestAdoption {
    private final AdoptionRepository adoptionRepository;

    public ValidationPetHasPendingAdoption(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public void validate(RequestAdoptionDto requestAdoptionDto) {
        boolean petHasPendingAdoption = adoptionRepository
                .existsByPetIdAndStatus(requestAdoptionDto.petId(), AdoptionStatus.WAITING_EVALUATION);

        if (petHasPendingAdoption) {
            throw new ValidationException("Pet is already waiting for evaluation to be adopted!");
        }
    }
}
