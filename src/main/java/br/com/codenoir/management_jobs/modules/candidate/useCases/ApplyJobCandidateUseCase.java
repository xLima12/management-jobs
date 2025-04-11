package br.com.codenoir.management_jobs.modules.candidate.useCases;

import br.com.codenoir.management_jobs.exceptions.JobNotFoundException;
import br.com.codenoir.management_jobs.exceptions.UserNotFoundException;
import br.com.codenoir.management_jobs.modules.candidate.entities.ApplyJobEntity;
import br.com.codenoir.management_jobs.modules.candidate.repositories.ApplyJobRepository;
import br.com.codenoir.management_jobs.modules.candidate.repositories.CandidateRepository;
import br.com.codenoir.management_jobs.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {

        this.candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);

        this.jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);

        var applyJob = new ApplyJobEntity(idCandidate, idJob);

        applyJob = applyJobRepository.save(applyJob);

        return applyJob;
    }

}
