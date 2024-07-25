package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;
import adopet.api.model.Adoption;
import adopet.api.model.AdoptionStatus;
import adopet.api.model.Guardian;
import adopet.api.repository.AdoptionRepository;
import adopet.api.repository.GuardianRepository;
import jakarta.validation.ValidationException;

import java.util.List;

public class ValidationApprovedAdoptionCount implements ValidationRequestAdoptionInterface{
    private static final int MAX_APPROVED_ADOPTIONS = 5;
    private final AdoptionRepository adoptionRepository;
    private final GuardianRepository guardianRepository;

    public ValidationApprovedAdoptionCount(GuardianRepository guardianRepository, AdoptionRepository adoptionRepository) {
        this.guardianRepository = guardianRepository;
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public void validate(RequestAdoptionDto requestAdoptionDto) {
        List<Adoption> adoptions = adoptionRepository.findAll();
        Guardian guardian = guardianRepository.getReferenceById(requestAdoptionDto.guardianId());
        long approvedAdoptionCount = adoptions.stream()
                .filter(a -> a.getGuardian().equals(guardian) && a.getStatus() == AdoptionStatus.APPROVED)
                .count();

        if (approvedAdoptionCount >= MAX_APPROVED_ADOPTIONS) {
            throw new ValidationException("Guardian has reached the maximum limit of 5 adoptions!");
        }
    }
}
