package adopet.service.pet;

import adopet.dto.pet.GetPetDto;
import adopet.dto.pet.RegisterPetDto;
import adopet.dto.shelter.GetShelterDto;

import java.util.List;

public interface IPetService {
    void registerPet(RegisterPetDto petDto, GetShelterDto shelterDto);
    List<GetPetDto> listPetsInShelter(Long shelterId);
    List<GetPetDto> listAllAvailablePets();
}
