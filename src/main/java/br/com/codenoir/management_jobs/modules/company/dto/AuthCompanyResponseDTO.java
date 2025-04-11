package br.com.codenoir.management_jobs.modules.company.dto;

import lombok.Data;

@Data
public class AuthCompanyResponseDTO {

    public AuthCompanyResponseDTO() {}

    public AuthCompanyResponseDTO(String access_token, Long expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    private String access_token;
    private Long expires_in;

}
