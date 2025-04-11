package br.com.codenoir.management_jobs.modules.candidate.repositories;

import br.com.codenoir.management_jobs.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
