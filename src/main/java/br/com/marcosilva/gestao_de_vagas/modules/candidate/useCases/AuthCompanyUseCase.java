package br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.marcosilva.gestao_de_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.marcosilva.gestao_de_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO) throws Exception {
        var company =  this.companyRepository.findByUsername(authCompanyDTO.getUsername())
        .orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Company not found.");
            }
        );

        var passworMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passworMatches) {
            throw new Exception();
        }

        

    }
}
