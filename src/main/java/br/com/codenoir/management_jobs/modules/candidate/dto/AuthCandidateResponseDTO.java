package br.com.codenoir.management_jobs.modules.candidate.dto;

import lombok.Data;

@Data
public class AuthCandidateResponseDTO {

    public AuthCandidateResponseDTO() {}

    public AuthCandidateResponseDTO(String access_token, Long expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    private String access_token;
    private Long  expires_in;

}
