package br.com.marcosilva.gestao_de_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.candidateEntity;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases.createCandidateUsecase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class candidateController {

    @Autowired
    private createCandidateUsecase candidateUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody candidateEntity candidateEntity) {
        try {
           var result = this.candidateUseCase.execute(candidateEntity);
           return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
