package adopet.repository;

import adopet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByShelterId(Long shelterId);

    List<Pet> findByAdoptedFalse();
}
