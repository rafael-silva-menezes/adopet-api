package adopet.api.repository;

import adopet.api.model.Adoption;
import adopet.api.model.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    boolean existsByPetIdAndStatus(Long petId, AdoptionStatus status);

    boolean existsByGuardianIdAndStatus(Long guardianId, AdoptionStatus adoptionStatus);

    long countByGuardianIdAndStatus(Long guardianId, AdoptionStatus status);

}
