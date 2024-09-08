package br.com.marcosilva.gestao_de_vagas.modules.candidate.repositories;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.marcosilva.gestao_de_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository  extends JpaRepository<ApplyJobEntity, UUID>{
}
