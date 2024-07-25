package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;
import adopet.api.model.AdoptionStatus;
import adopet.api.repository.AdoptionRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationApprovedAdoptionCount implements IValidationRequestAdoption {
    private static final int MAX_APPROVED_ADOPTIONS = 5;
    private final AdoptionRepository adoptionRepository;

    public ValidationApprovedAdoptionCount(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public void validate(RequestAdoptionDto requestAdoptionDto) {
        long approvedAdoptionCount = adoptionRepository
                .countByGuardianIdAndStatus(requestAdoptionDto.guardianId(), AdoptionStatus.APPROVED);

        if (approvedAdoptionCount >= MAX_APPROVED_ADOPTIONS) {
            throw new ValidationException("Guardian has reached the maximum limit of 5 adoptions!");
        }
    }
}
