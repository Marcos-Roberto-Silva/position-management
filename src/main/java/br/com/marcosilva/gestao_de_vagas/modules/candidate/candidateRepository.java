package br.com.marcosilva.gestao_de_vagas.modules.candidate;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface candidateRepository extends JpaRepository<candidateEntity, UUID>{
    Optional<candidateEntity>findByUsernameOrEmail(String username, String email);
}
