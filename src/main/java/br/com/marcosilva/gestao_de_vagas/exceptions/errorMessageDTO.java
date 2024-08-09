package br.com.marcosilva.gestao_de_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class errorMessageDTO {
    private String message;
    private String field;
}
