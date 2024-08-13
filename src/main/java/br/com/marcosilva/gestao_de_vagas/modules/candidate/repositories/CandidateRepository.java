package br.com.marcosilva.gestao_de_vagas.modules.candidate.repositories;

import java.util.UUID;
import java.util.Optional;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{
    Optional<CandidateEntity>findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity>findByUsername(String username);
}
