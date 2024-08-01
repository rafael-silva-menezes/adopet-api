package adopet.repository;

import adopet.model.Adoption;
import adopet.model.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    boolean existsByPetIdAndStatus(Long petId, AdoptionStatus status);

    boolean existsByGuardianIdAndStatus(Long guardianId, AdoptionStatus adoptionStatus);

    long countByGuardianIdAndStatus(Long guardianId, AdoptionStatus status);

}
