package br.com.marcosilva.gestao_de_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Schema(example = "Vaga para design")
    @NotBlank(message = "description is a required field.")
    private String description;

    @Schema(example = "GymPass, Day Off de aniversário, etc...")
    private String benefits;

    @Schema(example = "Pleno")
    @NotBlank(message = "Level is a required field.")
    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable=false, updatable=false, nullable = false)
    private CompanyEntity companyEntity;

    @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
