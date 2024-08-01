package adopet.validation;

import adopet.dto.adoption.RequestAdoptionDto;
import adopet.model.AdoptionStatus;
import adopet.repository.AdoptionRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationGuardianHasPendingAdoption implements IValidationRequestAdoption {
    private final AdoptionRepository adoptionRepository;

    public ValidationGuardianHasPendingAdoption(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public void validate(RequestAdoptionDto requestAdoptionDto) {
        boolean guardianHasPendingAdoption = adoptionRepository
                .existsByGuardianIdAndStatus(requestAdoptionDto.guardianId(), AdoptionStatus.WAITING_EVALUATION);

        if (guardianHasPendingAdoption) {
            throw new ValidationException("Guardian already has another adoption waiting for evaluation!");
        }
    }
}
