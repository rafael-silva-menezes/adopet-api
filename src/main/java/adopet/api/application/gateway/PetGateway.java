package adopet.api.application.gateway;

import adopet.api.domain.entity.Pet;

import java.util.List;

public interface PetGateway {
    Pet create(Pet pet);
    List<Pet> getPets();
    Pet getPet(String id);
}
