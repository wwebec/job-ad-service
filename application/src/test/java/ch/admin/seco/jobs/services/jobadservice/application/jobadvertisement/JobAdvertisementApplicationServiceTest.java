package ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement;

import static ch.admin.seco.jobs.services.jobadservice.core.time.TimeMachine.now;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_02;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_03;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_04;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_05;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import ch.admin.seco.jobs.services.jobadservice.application.LocalityService;
import ch.admin.seco.jobs.services.jobadservice.application.ProfessionService;
import ch.admin.seco.jobs.services.jobadservice.application.RavRegistrationService;
import ch.admin.seco.jobs.services.jobadservice.application.ReportingObligationService;
import ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto.ApplyChannelDto;
import ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto.CompanyDto;
import ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto.ContactDto;
import ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto.CreateJobAdvertisementWebFormDto;
import ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto.EmploymentDto;
import ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto.LanguageSkillDto;
import ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto.LocalityDto;
import ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto.OccupationDto;
import ch.admin.seco.jobs.services.jobadservice.core.domain.events.DomainEvent;
import ch.admin.seco.jobs.services.jobadservice.core.domain.events.DomainEventMockUtils;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisement;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementEvents;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementId;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementRepository;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementStatus;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.LanguageLevel;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.Locality;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.SourceSystem;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.WorkExperience;


@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
public class JobAdvertisementApplicationServiceTest {

    private DomainEventMockUtils domainEventMockUtils;

    @MockBean
    private RavRegistrationService ravRegistrationService;

    @MockBean
    private ReportingObligationService reportingObligationService;

    @MockBean
    private LocalityService localityService;

    @MockBean
    private ProfessionService professionService;

    @Autowired
    private JobAdvertisementRepository jobAdvertisementRepository;

    @Autowired
    private JobAdvertisementApplicationService sut; //System Under Test

    @Before
    public void setUp() {
        domainEventMockUtils = new DomainEventMockUtils();

        when(localityService.enrichCodes(any())).thenReturn(new Locality("remarks", "ctiy", "zipCode", null, null, "BE", "CH", null));
    }

    @After
    public void tearDown() {
        domainEventMockUtils.clearEvents();
    }

    @Test
    public void createFromWebForm() {
        //Prepare
        CreateJobAdvertisementWebFormDto createJobAdvertisementWebFormDto = new CreateJobAdvertisementWebFormDto(
                true,
                "title",
                "description",
                new EmploymentDto(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 12, 31), 365, true, false, 80, 100),
                "drivingLicenseLevel",
                new ApplyChannelDto("mailAddress", "emailAddress", "phoneNumber", "formUrl", "additionalInfo"),
                new CompanyDto("name", "stree", "houseNumber", "zipCode", "city", "CH", null, null, null, "phone", "email", "website"),
                new ContactDto("MR", "firstName", "lastName", "phone", "email"),
                new LocalityDto("remarks", "ctiy", "zipCode", null, null, "BE", "CH", null),
                new OccupationDto("avamCode", WorkExperience.MORE_THAN_1_YEAR, "educationCode"),
                Collections.singletonList(new LanguageSkillDto("de", LanguageLevel.PROFICIENT, LanguageLevel.PROFICIENT))
        );

        //Execute
        JobAdvertisementId jobAdvertisementId = sut.createFromWebForm(createJobAdvertisementWebFormDto);

        //Validate
        JobAdvertisement jobAdvertisement = jobAdvertisementRepository.getOne(jobAdvertisementId);
        assertThat(jobAdvertisement).isNotNull();
        assertThat(jobAdvertisement.getStatus()).isEqualTo(JobAdvertisementStatus.CREATED);
        assertThat(jobAdvertisement.getSourceSystem()).isEqualTo(SourceSystem.JOBROOM);

        domainEventMockUtils.assertSingleDomainEventPublished(JobAdvertisementEvents.JOB_ADVERTISEMENT_CREATED.getDomainEventType());
    }

    @Test
    public void checkBlackoutPolicyExpiration() {
        // given
        jobAdvertisementRepository.save(createJobWithStatusAndReportingObligationEndDate(JOB_ADVERTISEMENT_ID_01, JobAdvertisementStatus.CREATED, null));
        jobAdvertisementRepository.save(createJobWithStatusAndReportingObligationEndDate(JOB_ADVERTISEMENT_ID_02, JobAdvertisementStatus.CANCELLED, null));
        jobAdvertisementRepository.save(createJobWithStatusAndReportingObligationEndDate(JOB_ADVERTISEMENT_ID_03, JobAdvertisementStatus.PUBLISHED_RESTRICTED, now().toLocalDate().plusDays(1)));
        jobAdvertisementRepository.save(createJobWithStatusAndReportingObligationEndDate(JOB_ADVERTISEMENT_ID_04, JobAdvertisementStatus.PUBLISHED_RESTRICTED, now().toLocalDate().minusDays(1)));
        jobAdvertisementRepository.save(createJobWithStatusAndReportingObligationEndDate(JOB_ADVERTISEMENT_ID_05, JobAdvertisementStatus.PUBLISHED_RESTRICTED, now().toLocalDate()));

        // when
        this.sut.checkBlackoutPolicyExpiration();

        // then
        DomainEvent domainEvent = domainEventMockUtils.assertSingleDomainEventPublished(JobAdvertisementEvents.JOB_ADVERTISEMENT_BLACKOUT_EXPIRED.getDomainEventType());
        assertThat(domainEvent.getAggregateId()).isEqualTo(JOB_ADVERTISEMENT_ID_04);
    }

    @Test
    public void checkPublicationExpiration() {
        // given
        jobAdvertisementRepository.save(createJobWithStatusAndPublicationEndDate(JOB_ADVERTISEMENT_ID_01, JobAdvertisementStatus.CREATED, null));
        jobAdvertisementRepository.save(createJobWithStatusAndPublicationEndDate(JOB_ADVERTISEMENT_ID_02, JobAdvertisementStatus.CANCELLED, null));
        jobAdvertisementRepository.save(createJobWithStatusAndPublicationEndDate(JOB_ADVERTISEMENT_ID_03, JobAdvertisementStatus.PUBLISHED_PUBLIC, now().toLocalDate().plusDays(1)));
        jobAdvertisementRepository.save(createJobWithStatusAndPublicationEndDate(JOB_ADVERTISEMENT_ID_04, JobAdvertisementStatus.PUBLISHED_PUBLIC, now().toLocalDate().minusDays(1)));
        jobAdvertisementRepository.save(createJobWithStatusAndPublicationEndDate(JOB_ADVERTISEMENT_ID_05, JobAdvertisementStatus.PUBLISHED_PUBLIC, now().toLocalDate()));

        // when
        this.sut.checkPublicationExpiration();

        // then
        DomainEvent domainEvent = domainEventMockUtils.assertSingleDomainEventPublished(JobAdvertisementEvents.JOB_ADVERTISEMENT_PUBLISH_EXPIRED.getDomainEventType());
        assertThat(domainEvent.getAggregateId()).isEqualTo(JOB_ADVERTISEMENT_ID_04);
    }


    private JobAdvertisement createJobWithStatusAndReportingObligationEndDate(JobAdvertisementId jobAdvertisementId, JobAdvertisementStatus status, LocalDate reportingObligationEndDate) {
        return new JobAdvertisement.Builder()
                .setId(jobAdvertisementId)
                .setSourceSystem(SourceSystem.JOBROOM)
                .setTitle(String.format("title-%s", jobAdvertisementId.getValue()))
                .setDescription(String.format("description-%s", jobAdvertisementId.getValue()))
                .setStatus(status)
                .setReportingObligationEndDate(reportingObligationEndDate)
                .build();
    }

    private JobAdvertisement createJobWithStatusAndPublicationEndDate(JobAdvertisementId jobAdvertisementId, JobAdvertisementStatus status, LocalDate publicationEndDate) {
        return new JobAdvertisement.Builder()
                .setId(jobAdvertisementId)
                .setSourceSystem(SourceSystem.JOBROOM)
                .setTitle(String.format("title-%s", jobAdvertisementId.getValue()))
                .setDescription(String.format("description-%s", jobAdvertisementId.getValue()))
                .setStatus(status)
                .setPublicationEndDate(publicationEndDate)
                .build();
    }

}
