package br.com.marcosilva.gestao_de_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases.AuthCompanyUseCase;
import br.com.marcosilva.gestao_de_vagas.modules.company.dto.AuthCompanyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthcompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;
    
    @PostMapping("/company")
    public String create(@RequestBody AuthCompanyDTO authCompanyDTO) throws Exception {
        
        return this.authCompanyUseCase.execute(authCompanyDTO);
    }
    
}
