package br.com.codenoir.management_jobs.exceptions;

import lombok.Data;

@Data
public class ErrorMessageDTO {

    public ErrorMessageDTO(){}

    public ErrorMessageDTO(String message, String field) {
        this.message = message;
        this.field = field;
    }

    private String message;
    private String field;

}
