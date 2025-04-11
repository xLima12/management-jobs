package br.com.codenoir.management_jobs.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class ProfileCandidateResponseDTO {

    public ProfileCandidateResponseDTO() {}

    public ProfileCandidateResponseDTO(UUID id, String description, String username, String email, String name) {
        this.description = description;
        this.username = username;
        this.email = email;
        this.id = id;
        this.name = name;
    }

    @Schema(example = "Desenvolvedora Java")
    private String description;
    @Schema(example = "maria")
    private String username;
    @Schema(example = "maria@gmail.com")
    private String email;
    private UUID id;
    @Schema(example = "Maria de Souza")
    private String name;

}
