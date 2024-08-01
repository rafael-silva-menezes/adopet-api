package adopet.api.infrastructure.mapper;

import adopet.api.domain.entity.Pet;
import adopet.api.infrastructure.model.PetModel;

import java.util.List;
import java.util.stream.Collectors;

public class PetModelMapper {
    public PetModel toModel(Pet pet) {
        return new PetModel(
                pet.id(),
                pet.type(),
                pet.name(),
                pet.breed(),
                pet.age(),
                pet.color(),
                pet.weight(),
                pet.adopted(),
                new ShelterModelMapper().toModel(pet.shelter()),
                new AdoptionModelMapper().toModel(pet.adoption())
        );
    }

    public Pet toEntity(PetModel petModel) {
        return new Pet(
                petModel.getId(),
                petModel.getType(),
                petModel.getName(),
                petModel.getBreed(),
                petModel.getAge(),
                petModel.getColor(),
                petModel.getWeight(),
                petModel.getAdopted(),
                new ShelterModelMapper().toEntity(petModel.getShelter()),
                new AdoptionModelMapper().toEntity(petModel.getAdoption())
        );
    }

    public List<PetModel> toModelList(List<Pet> pets) {
        return pets.stream().map(this::toModel).collect(Collectors.toList());
    }

    public List<Pet> toEntityList(List<PetModel> petModels) {
        return petModels.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
