package adopet.dto.pet;

import adopet.model.Pet;
import adopet.model.PetType;

public record GetPetDto(Long id, PetType type, String name, String breed, Integer age,
                        String color, Float weight) {
    public GetPetDto(Pet pet) {
        this(pet.getId(), pet.getType(), pet.getName(), pet.getBreed(), pet.getAge(),
                pet.getColor(), pet.getWeight());
    }
}
