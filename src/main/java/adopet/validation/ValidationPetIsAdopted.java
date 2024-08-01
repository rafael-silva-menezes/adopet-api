package adopet.validation;

import adopet.dto.adoption.RequestAdoptionDto;
import adopet.model.Pet;
import adopet.repository.PetRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationPetIsAdopted implements IValidationRequestAdoption {
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
