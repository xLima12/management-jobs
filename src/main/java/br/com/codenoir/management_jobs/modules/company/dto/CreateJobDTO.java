package br.com.codenoir.management_jobs.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateJobDTO {

    public CreateJobDTO(String description, String benefits, String level) {
        this.description = description;
        this.benefits = benefits;
        this.level = level;
    }

    @Schema(example = "Vaga para pessoa desenvolvedora júnior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "GymPass, Plano de Saúde", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
