package adopet.api.infrastructure.gateway;

import adopet.api.application.gateway.PetGateway;
import adopet.api.domain.entity.Pet;
import adopet.api.infrastructure.mapper.PetModelMapper;
import adopet.api.infrastructure.model.PetModel;
import adopet.api.infrastructure.persistence.PetRepository;

public class PetRepositoryGateway implements PetGateway {
    private final PetRepository petRepository;
    private final PetModelMapper petModelMapper;

    public PetRepositoryGateway(PetRepository petRepository, PetModelMapper petModelMapper) {
        this.petRepository = petRepository;
        this.petModelMapper = petModelMapper;
    }

    @Override
    public Pet create(Pet pet) {
        PetModel petModel = petModelMapper.toModel(pet);
        PetModel savedPetModel = petRepository.save(petModel);
        return petModelMapper.toEntity(savedPetModel);
    }
}
