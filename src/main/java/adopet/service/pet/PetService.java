package adopet.service.pet;

import adopet.dto.pet.GetPetDto;
import adopet.dto.pet.RegisterPetDto;
import adopet.dto.shelter.GetShelterDto;
import adopet.model.Pet;
import adopet.model.Shelter;
import adopet.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService implements IPetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void registerPet(RegisterPetDto petDto,GetShelterDto shelterDto) {
        Shelter shelter = new Shelter(shelterDto.name(), shelterDto.phone(), shelterDto.email());
        Pet pet = new Pet(petDto.type(), petDto.name(), petDto.breed(), petDto.age(),
                petDto.color(), petDto.weight(), shelter);
        petRepository.save(pet);
    }

    @Override
    public List<GetPetDto> listPetsInShelter(Long shelterId) {
        return petRepository.findByShelterId(shelterId).stream().map(GetPetDto::new).toList();
    }

    @Override
    public List<GetPetDto> listAllAvailablePets(){
        return petRepository.findByAdoptedFalse().stream().map(GetPetDto::new).toList();
    }

}
