package br.com.codenoir.management_jobs.modules.candidate.useCases;

import br.com.codenoir.management_jobs.exceptions.JobNotFoundException;
import br.com.codenoir.management_jobs.exceptions.UserNotFoundException;
import br.com.codenoir.management_jobs.modules.candidate.entities.ApplyJobEntity;
import br.com.codenoir.management_jobs.modules.candidate.entities.CandidateEntity;
import br.com.codenoir.management_jobs.modules.candidate.repositories.ApplyJobRepository;
import br.com.codenoir.management_jobs.modules.candidate.repositories.CandidateRepository;
import br.com.codenoir.management_jobs.modules.company.entities.JobEntity;
import br.com.codenoir.management_jobs.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Captor
    private ArgumentCaptor<ApplyJobEntity> applyJobCaptor;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        assertThrows(UserNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(null, null);
        });
    }

    @Test
    @DisplayName("should not be able to apply job with job not found")
    public void shouldNotBeAbleToApplyJobWithJobNotFound() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        assertThrows(JobNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(idCandidate, null);
        });
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void shouldBeAbleToCreateANewApplyJob() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = new ApplyJobEntity(idCandidate, idJob);

        applyJob.setId(UUID.randomUUID());

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));

        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(any(ApplyJobEntity.class))).thenReturn(applyJob);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        verify(applyJobRepository).save(applyJobCaptor.capture());

        ApplyJobEntity entitySave = applyJobCaptor.getValue();

        assertNotNull(result.getId());

        assertEquals(idCandidate, entitySave.getCandidateId());

        assertEquals(idJob, entitySave.getJobId());

    }
}
