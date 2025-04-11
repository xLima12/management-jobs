package br.com.codenoir.management_jobs.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
public class JobEntity {

    public JobEntity() {}

    public JobEntity(String description, String level, String benefits, UUID companyId) {
        this.description = description;
        this.level = level;
        this.benefits = benefits;
        this.companyId = companyId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Schema(example = "Vaga para designer", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @NotBlank()
    @Schema(example = "SENIOR")
    private String level;
    @Schema(example = "Vale Alimentação, Plano de Saúde")
    private String benefits;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
}
