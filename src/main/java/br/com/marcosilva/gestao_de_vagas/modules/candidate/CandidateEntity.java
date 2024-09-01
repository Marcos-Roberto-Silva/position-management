package br.com.marcosilva.gestao_de_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Schema(example = "Marcos Silva", requiredMode = Schema.RequiredMode.REQUIRED, description = "nome do candidato")
    private String name;

    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços")
    @Schema(example = "marcosSilva", requiredMode = Schema.RequiredMode.REQUIRED, description = "username do candidato")
    private String username;

    @Email(message = "O campo [email] deve conter um email válido")
    @Schema(example = "marcosilva@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "email do candidato")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve estar entre 10 e 100 caracteres")
    @Schema(example = "#456&89", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "senha do candidato")
    private String password;
    @Schema(example = "Software Developer Junior")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
