package br.com.marcosilva.gestao_de_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {
    public String username;
    public String password;
}
