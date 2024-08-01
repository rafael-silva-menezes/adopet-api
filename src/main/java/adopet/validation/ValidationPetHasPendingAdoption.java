package adopet.validation;

import adopet.dto.adoption.RequestAdoptionDto;
import adopet.model.AdoptionStatus;
import adopet.repository.AdoptionRepository;
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
