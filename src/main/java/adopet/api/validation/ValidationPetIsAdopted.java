package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;
import adopet.api.model.Pet;
import adopet.api.repository.PetRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationPetIsAdopted implements ValidationRequestAdoptionInterface {
    private final PetRepository petRepository;

    public ValidationPetIsAdopted(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void validate(RequestAdoptionDto requestAdoptionDto) {
        Pet pet = petRepository
                .getReferenceById(requestAdoptionDto.petId());
        if (pet.getAdopted()) {
            throw new ValidationException("Pet has already been adopted!");
        }
    }
}
