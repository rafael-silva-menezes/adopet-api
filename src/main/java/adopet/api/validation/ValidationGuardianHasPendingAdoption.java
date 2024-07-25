package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;
import adopet.api.model.Adoption;
import adopet.api.model.AdoptionStatus;
import adopet.api.model.Guardian;
import adopet.api.repository.AdoptionRepository;
import adopet.api.repository.GuardianRepository;
import jakarta.validation.ValidationException;

import java.util.List;

public class ValidationGuardianHasPendingAdoption implements ValidationRequestAdoptionInterface{
    private final AdoptionRepository adoptionRepository;
    private final GuardianRepository guardianRepository;

    public ValidationGuardianHasPendingAdoption(GuardianRepository guardianRepository, AdoptionRepository adoptionRepository) {
        this.guardianRepository = guardianRepository;
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public void validate(RequestAdoptionDto requestAdoptionDto) {
        List<Adoption> adoptions = adoptionRepository.findAll();
        Guardian guardian = guardianRepository.getReferenceById(requestAdoptionDto.guardianId());


        boolean guardianHasPendingAdoption = adoptions.stream()
                .anyMatch(a -> a.getGuardian().equals(guardian) && a.getStatus() == AdoptionStatus.WAITING_EVALUATION);

        if (guardianHasPendingAdoption) {
            throw new ValidationException("Guardian already has another adoption waiting for evaluation!");
        }
    }
}
