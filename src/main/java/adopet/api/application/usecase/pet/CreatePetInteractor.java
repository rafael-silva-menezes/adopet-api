package adopet.api.application.usecase.pet;

import adopet.api.application.gateway.PetGateway;
import adopet.api.domain.entity.Pet;

public class CreatePetInteractor {
    private PetGateway petGateway;

    public CreatePetInteractor(PetGateway petGateway) {
        this.petGateway = petGateway;
    }

    Pet create(Pet pet) {
        return petGateway.create(pet);
    }
}
