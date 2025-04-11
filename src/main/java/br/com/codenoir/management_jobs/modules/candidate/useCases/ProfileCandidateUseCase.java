package br.com.codenoir.management_jobs.modules.candidate.useCases;

import br.com.codenoir.management_jobs.exceptions.UserNotFoundException;
import br.com.codenoir.management_jobs.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.codenoir.management_jobs.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);
        return new ProfileCandidateResponseDTO(candidate.getId(),candidate.getDescription(), candidate.getUsername(),
                candidate.getEmail(), candidate.getName());
    }

}
