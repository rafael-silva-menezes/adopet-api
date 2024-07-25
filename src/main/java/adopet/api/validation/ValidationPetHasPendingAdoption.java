package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;
import adopet.api.model.Adoption;
import adopet.api.model.AdoptionStatus;
import adopet.api.model.Pet;
import adopet.api.repository.AdoptionRepository;
import adopet.api.repository.PetRepository;
import jakarta.validation.ValidationException;

import java.util.List;

public class ValidationPetHasPendingAdoption implements ValidationRequestAdoptionInterface {
    private final AdoptionRepository adoptionRepository;
    private final PetRepository petRepository;

    public ValidationPetHasPendingAdoption(PetRepository petRepository, AdoptionRepository adoptionRepository) {
        this.petRepository = petRepository;
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public void validate(RequestAdoptionDto requestAdoptionDto) {
        List<Adoption> adoptions = adoptionRepository.findAll();
        Pet pet = petRepository.getReferenceById(requestAdoptionDto.petId());

        boolean petHasPendingAdoption = adoptions.stream()
                .anyMatch(a -> a.getPet().equals(pet) && a.getStatus() == AdoptionStatus.WAITING_EVALUATION);

        if (petHasPendingAdoption) {
            throw new ValidationException("Pet is already waiting for evaluation to be adopted!");
        }
    }
}
