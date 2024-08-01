package adopet.controller;

import adopet.dto.adoption.ApproveAdoptionDto;
import adopet.dto.adoption.RejectAdoptionDto;
import adopet.dto.adoption.RequestAdoptionDto;
import adopet.service.adoption.IAdoptionService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {

    private final IAdoptionService adoptionService;

    public AdoptionController(IAdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> request(@RequestBody @Valid RequestAdoptionDto requestAdoptionDto) {
        try {
            this.adoptionService.request(requestAdoptionDto);
            return ResponseEntity.ok().build();

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/approve")
    @Transactional
    public ResponseEntity<String> approve(@RequestBody @Valid ApproveAdoptionDto approveAdoptionDto) {
        this.adoptionService.approve(approveAdoptionDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reject")
    @Transactional
    public ResponseEntity<String> reject(@RequestBody @Valid RejectAdoptionDto rejectAdoptionDto) {
        this.adoptionService.reject(rejectAdoptionDto);
        return ResponseEntity.ok().build();
    }

}
