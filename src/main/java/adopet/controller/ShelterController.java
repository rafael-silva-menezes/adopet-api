package adopet.controller;

import adopet.dto.pet.GetPetDto;
import adopet.dto.pet.RegisterPetDto;
import adopet.dto.shelter.GetShelterDto;
import adopet.dto.shelter.ListSheltersDto;
import adopet.dto.shelter.RegisterShelterDto;
import adopet.service.pet.IPetService;
import adopet.service.shelter.IShelterService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelters")
public class ShelterController {
    private final IShelterService shelterService;
    private final IPetService petService;

    public ShelterController(IShelterService shelterService, IPetService petService) {
        this.shelterService = shelterService;
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<ListSheltersDto>> listAll() {
        List<ListSheltersDto> shelters = this.shelterService.listShelters();
        return ResponseEntity.ok(shelters);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid RegisterShelterDto dto) {
        try {
            this.shelterService.registerShelter(dto);
            return ResponseEntity.ok().build();
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{param}/pets")
    public ResponseEntity<List<GetPetDto>> listPets(@PathVariable Long param) {
        try {
            List<GetPetDto> petsInShelter = petService.listPetsInShelter(param);
            return ResponseEntity.ok(petsInShelter);
        } catch (ValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{param}/pets")
    @Transactional
    public ResponseEntity<String> registerPet(@PathVariable String param, @RequestBody @Valid RegisterPetDto petDto) {
        try {
            GetShelterDto shelterDto = shelterService.getShelter(param);
            petService.registerPet(petDto, shelterDto);
            return ResponseEntity.ok().build();
        } catch (ValidationException nfe) {
            return ResponseEntity.notFound().build();
        }
    }

}
