package br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases;

import br.com.marcosilva.gestao_de_vagas.exceptions.JobNotFoundException;
import br.com.marcosilva.gestao_de_vagas.exceptions.UserNotFoundException;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.marcosilva.gestao_de_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        this.jobRepository.findById(jobId).orElseThrow(
                () -> {
                    throw new JobNotFoundException();
                }
        );

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        applyJob = applyJobRepository.save(applyJob);

        return applyJob;
    }
}
