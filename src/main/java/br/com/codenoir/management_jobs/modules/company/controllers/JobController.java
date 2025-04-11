package br.com.codenoir.management_jobs.modules.company.controllers;

import br.com.codenoir.management_jobs.modules.company.dto.CreateJobDTO;
import br.com.codenoir.management_jobs.modules.company.entities.JobEntity;
import br.com.codenoir.management_jobs.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas", description = "Essa função é responsável por " +
            "cadastrar as vagas dentro da empresa")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = JobEntity.class))
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        try {
            var jobEntity = new JobEntity(createJobDTO.getDescription(), createJobDTO.getLevel(), createJobDTO.getBenefits(),
                    UUID.fromString(companyId.toString()));

            var result = this.createJobUseCase.execute(jobEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
