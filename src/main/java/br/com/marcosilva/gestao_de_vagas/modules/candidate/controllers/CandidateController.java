package br.com.marcosilva.gestao_de_vagas.modules.candidate.controllers;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases.ListAllJobsByFilterUseCase;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases.ProfileCandidateUseCase;
import br.com.marcosilva.gestao_de_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.entity.CandidateEntity;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/")
    @Operation(
            summary = "Cadastro de candidato",
            description = "Essa função é responsavél por cadastrar um candidato")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = CandidateEntity.class))
    )
    @ApiResponse(
            responseCode = "400", description = "User already exists"
    )
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
           var result = this.createCandidateUseCase.execute(candidateEntity);
           return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/")

    @Operation(
            summary = "Perfil do candidato",
            description = "Essa função é responsavél por buscar as informações do perfil do candidato")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "400", description = "User not found"
    )
    @SecurityRequirement(name = "jwt_auth")


    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString().toUpperCase()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "List of available candidate's vacancy", description = "This function is responsible to list all vacancy ")
    @ApiResponse(
            responseCode = "200",
            description = "List of available jobs",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
    )
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter) {
       return this.listAllJobsByFilterUseCase.execute(filter);
    }
}
