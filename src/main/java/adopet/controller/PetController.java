package adopet.controller;

import adopet.dto.pet.GetPetDto;
import adopet.service.pet.IPetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final IPetService petService;

    public PetController(IPetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<GetPetDto>> listAllAvailable() {
        List<GetPetDto> pets = petService.listAllAvailablePets();
        return ResponseEntity.ok(pets);
    }

}
