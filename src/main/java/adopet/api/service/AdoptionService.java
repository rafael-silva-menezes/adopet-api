package adopet.api.service;

import adopet.api.dto.ApproveAdoptionDto;
import adopet.api.dto.RejectAdoptionDto;
import adopet.api.dto.RequestAdoptionDto;
import adopet.api.model.Adoption;
import adopet.api.model.Guardian;
import adopet.api.model.Pet;
import adopet.api.provider.EmailProviderInterface;
import adopet.api.repository.AdoptionRepository;
import adopet.api.repository.GuardianRepository;
import adopet.api.repository.PetRepository;
import adopet.api.validation.ValidationRequestAdoptionInterface;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdoptionService implements AdoptionServiceInterface {

    private static final String EMAIL_FROM = "adopet@email.com.br";
    private final List<ValidationRequestAdoptionInterface> validations;
    private final AdoptionRepository adoptionRepository;
    private final EmailProviderInterface emailProvider;
    private final GuardianRepository guardianRepository;
    private final PetRepository petRepository;

    public AdoptionService(AdoptionRepository adoptionRepository, EmailProviderInterface emailProvider,
                           GuardianRepository guardianRepository, PetRepository petRepository,
                           List<ValidationRequestAdoptionInterface> validations) {
        this.adoptionRepository = adoptionRepository;
        this.emailProvider = emailProvider;
        this.guardianRepository = guardianRepository;
        this.petRepository = petRepository;
        this.validations = validations;
    }

    public void request(RequestAdoptionDto requestAdoptionDto) {
        Pet pet = petRepository.getReferenceById(requestAdoptionDto.petId());
        Guardian guardian = guardianRepository.getReferenceById(requestAdoptionDto.guardianId());
        validations.forEach(v -> v.validate(requestAdoptionDto));

        Adoption adoption = new Adoption(guardian, pet, requestAdoptionDto.reason());
        adoptionRepository.save(adoption);

        sendEmail(adoption.getPet().getShelter().getEmail(), "Adoption Request", buildRequestEmailBody(adoption));
    }

    public void approve(ApproveAdoptionDto approveAdoptionDto) {
        Adoption adoption = adoptionRepository.getReferenceById(approveAdoptionDto.adoptionId());
        adoption.approve();

        sendEmail(adoption.getGuardian().getEmail(), "Adoption Approved", buildApprovalEmailBody(adoption));
    }

    public void reject(RejectAdoptionDto rejectAdoptionDto) {
        Adoption adoption = adoptionRepository.getReferenceById(rejectAdoptionDto.adoptionId());
        adoption.reprove(rejectAdoptionDto.justification());

        sendEmail(adoption.getGuardian().getEmail(), "Adoption Rejected", buildRejectionEmailBody(adoption));
    }

    private void sendEmail(String to, String subject, String body) {
        emailProvider.send(EMAIL_FROM, to, subject, body);
    }

    private String buildRequestEmailBody(Adoption adoption) {
        return String.format("Hello %s!\n\nAn adoption request has been registered today for the pet: %s. \nPlease review for approval or rejection.",
                adoption.getPet().getShelter().getName(),
                adoption.getPet().getName());
    }

    private String buildApprovalEmailBody(Adoption adoption) {
        return String.format("Congratulations %s!\n\nYour adoption of the pet %s, requested on %s, has been approved.\nPlease contact the shelter %s to schedule the pickup of your pet.",
                adoption.getGuardian().getName(),
                adoption.getPet().getName(),
                adoption.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                adoption.getPet().getShelter().getName());
    }

    private String buildRejectionEmailBody(Adoption adoption) {
        return String.format("Hello %s!\n\nUnfortunately, your adoption of the pet %s, requested on %s, has been rejected by the shelter %s with the following justification: %s",
                adoption.getGuardian().getName(),
                adoption.getPet().getName(),
                adoption.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                adoption.getPet().getShelter().getName(),
                adoption.getJustification());
    }
}
