package br.com.codenoir.management_jobs.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
public class CompanyEntity {

    public CompanyEntity() {}

    public CompanyEntity(String username, String email, String password, String name, String description) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter de (10) a (100) caracteres")
    private String password;
    private String website;
    private String name;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
