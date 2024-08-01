package adopet.repository;

import adopet.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    boolean existsByNameOrPhoneOrEmail(String name, String phone, String email);

    Optional<Shelter> findByName(String name);
}
