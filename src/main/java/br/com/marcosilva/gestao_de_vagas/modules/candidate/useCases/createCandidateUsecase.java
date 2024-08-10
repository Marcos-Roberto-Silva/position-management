package br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcosilva.gestao_de_vagas.exceptions.userfoundException;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.candidateEntity;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.candidateRepository;

@Service
public class createCandidateUsecase {

    @Autowired
    private candidateRepository candidateRep;

    public candidateEntity execute(candidateEntity candidate) {
        this.candidateRep.
            findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
            .ifPresent((user) -> {
                throw new userfoundException();
            });
        return this.candidateRep.save(candidate);
    }
}
