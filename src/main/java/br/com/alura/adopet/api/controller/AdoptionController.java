package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.model.AdoptionStatus;
import br.com.alura.adopet.api.repository.AdoptionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping
    @Transactional
    public ResponseEntity<String> requestAdoption(@RequestBody @Valid Adoption adoption) {
        if (adoption.getPet().getAdopted() == true) {
            return ResponseEntity.badRequest().body("Pet has already been adopted!");
        } else {
            List<Adoption> adoptions = repository.findAll();
            for (Adoption a : adoptions) {
                if (a.getGuardian() == adoption.getGuardian() && a.getStatus() == AdoptionStatus.WAITING_EVALUATION) {
                    return ResponseEntity.badRequest().body("Guardian already has another adoption waiting for evaluation!");
                }
            }
            for (Adoption a : adoptions) {
                if (a.getPet() == adoption.getPet() && a.getStatus() == AdoptionStatus.WAITING_EVALUATION) {
                    return ResponseEntity.badRequest().body("Pet is already waiting for evaluation to be adopted!");
                }
            }
            for (Adoption a : adoptions) {
                int counter = 0;
                if (a.getGuardian() == adoption.getGuardian() && a.getStatus() == AdoptionStatus.APPROVED) {
                    counter = counter + 1;
                }
                if (counter == 5) {
                    return ResponseEntity.badRequest().body("Guardian has reached the maximum limit of 5 adoptions!");
                }
            }
        }
        adoption.setDate(LocalDateTime.now());
        adoption.setStatus(AdoptionStatus.WAITING_EVALUATION);
        repository.save(adoption);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(adoption.getPet().getShelter().getEmail());
        email.setSubject("Adoption Request");
        email.setText("Hello " + adoption.getPet().getShelter().getName() +"!\n\nAn adoption request has been registered today for the pet: " + adoption.getPet().getName() +". \nPlease review for approval or rejection.");
        emailSender.send(email);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/approve")
    @Transactional
    public ResponseEntity<String> approve(@RequestBody @Valid Adoption adoption) {
        adoption.setStatus(AdoptionStatus.APPROVED);
        repository.save(adoption);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(adoption.getGuardian().getEmail());
        email.setSubject("Adoption Approved");
        email.setText("Congratulations " + adoption.getGuardian().getName() +"!\n\nYour adoption of the pet " + adoption.getPet().getName() +", requested on " + adoption.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", has been approved.\nPlease contact the shelter " + adoption.getPet().getShelter().getName() +" to schedule the pickup of your pet.");
        emailSender.send(email);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/reject")
    @Transactional
    public ResponseEntity<String> reject(@RequestBody @Valid Adoption adoption) {
        adoption.setStatus(AdoptionStatus.FAILED);
        repository.save(adoption);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(adoption.getGuardian().getEmail());
        email.setSubject("Adoption Rejected");
        email.setText("Hello " + adoption.getGuardian().getName() +"!\n\nUnfortunately, your adoption of the pet " + adoption.getPet().getName() +", requested on " + adoption.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", has been rejected by the shelter " + adoption.getPet().getShelter().getName() +" with the following justification: " + adoption.getJustifiedStatus());
        emailSender.send(email);

        return ResponseEntity.ok().build();
    }

}
