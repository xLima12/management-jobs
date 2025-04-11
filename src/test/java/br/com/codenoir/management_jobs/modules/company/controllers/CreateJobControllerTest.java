package br.com.codenoir.management_jobs.modules.company.controllers;

import br.com.codenoir.management_jobs.modules.company.dto.CreateJobDTO;
import br.com.codenoir.management_jobs.modules.company.entities.CompanyEntity;
import br.com.codenoir.management_jobs.modules.company.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.codenoir.management_jobs.utils.TestUtils;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void shouldBeAbleToCreateANewJob() throws Exception {

        var company = new CompanyEntity("COMPANY_USER", "email@company.com", "COMPANY_PASSWORD",
                "COMPANY_NAME", "COMPANY_DESCRIPTION");

        company = companyRepository.saveAndFlush(company);

        var createdJobDTO = new CreateJobDTO("DESCRIPTION_TEST","BENEFITS_TEST", "LEVEL_TEST");

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createdJobDTO))
                .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_@123#"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotBeAbleToCreateANewJobIfCompanyNotFound() throws Exception {

        var createdJobDTO = new CreateJobDTO("DESCRIPTION_TEST", "BENEFITS_TEST", "LEVEL_TEST");

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createdJobDTO))
                .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
