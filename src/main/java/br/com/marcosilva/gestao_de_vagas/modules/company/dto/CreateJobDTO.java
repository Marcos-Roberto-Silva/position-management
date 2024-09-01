package br.com.marcosilva.gestao_de_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {
    @Schema(example = "Vaga para pessoa desenvolvedora junior", requiredMode =  Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "GymPass, Plano de saúde", requiredMode =  Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Pleno", requiredMode =  Schema.RequiredMode.REQUIRED)
    private String level;

}
