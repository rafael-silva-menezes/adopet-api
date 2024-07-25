package adopet.api.controller;

import adopet.api.dto.ApproveAdoptionDto;
import adopet.api.dto.RejectAdoptionDto;
import adopet.api.dto.RequestAdoptionDto;
import adopet.api.service.AdoptionServiceInterface;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {

    private final AdoptionServiceInterface adoptionService;

    public AdoptionController(AdoptionServiceInterface adoptionService) {
        this.adoptionService = adoptionService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> request(@RequestBody @Valid RequestAdoptionDto requestAdoption) {
        try {
            this.adoptionService.request(requestAdoption);
            return ResponseEntity.ok().build();

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/approve")
    @Transactional
    public ResponseEntity<String> approve(@RequestBody @Valid ApproveAdoptionDto approveAdoption) {
        this.adoptionService.approve(approveAdoption);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reject")
    @Transactional
    public ResponseEntity<String> reject(@RequestBody @Valid RejectAdoptionDto rejectAdoption) {
        this.adoptionService.reject(rejectAdoption);
        return ResponseEntity.ok().build();
    }

}
