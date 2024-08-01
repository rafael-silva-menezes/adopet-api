package adopet.service.shelter;

import adopet.dto.shelter.GetShelterDto;
import adopet.dto.shelter.ListSheltersDto;
import adopet.dto.shelter.RegisterShelterDto;
import adopet.model.Shelter;
import adopet.repository.PetRepository;
import adopet.repository.ShelterRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService implements IShelterService {
    private ShelterRepository shelterRepository;
    private PetRepository petRepository;

    public ShelterService(ShelterRepository shelterRepository, PetRepository petRepository) {
        this.shelterRepository = shelterRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void registerShelter(RegisterShelterDto dto) {
        boolean alreadyRegistered = shelterRepository.existsByNameOrPhoneOrEmail(dto.name(), dto.phone(),
                dto.email());

        if (alreadyRegistered) {
            throw new ValidationException("Data already registered for another shelter!");
        }

        shelterRepository.save(new Shelter(dto.name(), dto.phone(), dto.email()));

    }

    @Override
    public List<ListSheltersDto> listShelters() {
        return shelterRepository.findAll()
                .stream()
                .map(ListSheltersDto::new)
                .toList();
    }

    @Override
    public GetShelterDto getShelter(String param) {
        Optional<Shelter> shelter;
        try {
            Long id = Long.parseLong(param);
            shelter = shelterRepository.findById(id);
        } catch (NumberFormatException e) {
            shelter = shelterRepository.findByName(param);
        }

        if (shelter.isEmpty()) {
            throw new ValidationException("Shelter not found");
        }

        return new GetShelterDto(shelter.get());
    }
}
