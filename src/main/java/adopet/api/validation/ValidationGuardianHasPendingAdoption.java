package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;
import adopet.api.model.AdoptionStatus;
import adopet.api.repository.AdoptionRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationGuardianHasPendingAdoption implements ValidationRequestAdoptionInterface {
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
