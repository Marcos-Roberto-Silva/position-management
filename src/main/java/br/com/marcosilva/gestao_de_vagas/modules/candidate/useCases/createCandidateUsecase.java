package br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.marcosilva.gestao_de_vagas.exceptions.UserFoundException;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.CandidateEntity;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {


        this.candidateRep.
            findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });

        var encryptedPassword = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(encryptedPassword);

        return this.candidateRep.save(candidateEntity);
    }
}
