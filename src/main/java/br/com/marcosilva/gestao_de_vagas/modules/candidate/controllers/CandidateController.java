package br.com.marcosilva.gestao_de_vagas.modules.candidate.controllers;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases.CreateCandidateUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
           var result = this.createCandidateUseCase.execute(candidateEntity);
           return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
