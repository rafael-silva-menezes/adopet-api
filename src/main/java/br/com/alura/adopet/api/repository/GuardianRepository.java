package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

}