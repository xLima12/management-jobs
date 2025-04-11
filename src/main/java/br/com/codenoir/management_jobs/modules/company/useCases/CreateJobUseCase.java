package br.com.codenoir.management_jobs.modules.company.useCases;

import br.com.codenoir.management_jobs.exceptions.CompanyNotFoundException;
import br.com.codenoir.management_jobs.modules.company.entities.JobEntity;
import br.com.codenoir.management_jobs.modules.company.repositories.CompanyRepository;
import br.com.codenoir.management_jobs.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(CompanyNotFoundException::new);

        return this.jobRepository.save(jobEntity);
    }
}
