package br.com.marcosilva.gestao_de_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcosilva.gestao_de_vagas.modules.company.useCases.AuthCompanyUseCase;
import br.com.marcosilva.gestao_de_vagas.modules.company.dto.AuthCompanyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;
    
    @PostMapping("/auth")
    public String create(@RequestBody AuthCompanyDTO authCompanyDTO) throws Exception {
        
        return this.authCompanyUseCase.execute(authCompanyDTO);
    }
    
}
