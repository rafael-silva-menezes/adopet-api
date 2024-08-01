package adopet.api.infrastructure.persistence;

import adopet.api.infrastructure.model.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetModel, Long> {

}
