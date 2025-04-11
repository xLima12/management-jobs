package br.com.codenoir.management_jobs.modules.candidate.useCases;

import br.com.codenoir.management_jobs.exceptions.UserFoundException;
import br.com.codenoir.management_jobs.modules.candidate.entities.CandidateEntity;
import br.com.codenoir.management_jobs.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
            .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });

        var password = this.passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }
}
