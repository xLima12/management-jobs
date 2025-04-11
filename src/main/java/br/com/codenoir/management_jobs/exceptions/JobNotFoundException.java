package br.com.codenoir.management_jobs.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("Job not found");
    }
}
