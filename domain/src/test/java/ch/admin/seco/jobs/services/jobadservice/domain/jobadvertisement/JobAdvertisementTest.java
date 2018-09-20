package ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement;

import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.fixture.CompanyTestFixture.testCompany;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.fixture.EmploymentTestFixture.testEmploymentPermanent;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.fixture.JobAdvertisementIdTestFixture.job01;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.fixture.JobAdvertisementTestFixture.prepareJobAdvertisementBuilder;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.fixture.JobAdvertisementTestFixture.testJobAdvertisement;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.fixture.JobAdvertisementTestFixture.testJobAdvertisementWithId01;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.fixture.JobContentTestFixture.testJobContent;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobcenter.fixture.JobCenterTestFixture.testJobCenter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.admin.seco.jobs.services.jobadservice.core.conditions.ConditionException;
import ch.admin.seco.jobs.services.jobadservice.core.domain.events.DomainEventMockUtils;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.events.JobAdvertisementEvent;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.events.JobAdvertisementEvents;
import ch.admin.seco.jobs.services.jobadservice.domain.jobcenter.JobCenter;

public class JobAdvertisementTest {

    private DomainEventMockUtils domainEventMockUtils;

    @Before
    public void setUp() {
        domainEventMockUtils = new DomainEventMockUtils();
    }

    @After
    public void tearDown() {
        domainEventMockUtils.clearEvents();
    }

    //@Test
    public void testUpdate() {
        //given
        JobAdvertisement jobAdvertisement = testJobAdvertisementWithId01();

        //when
        jobAdvertisement.update(
                new JobAdvertisementUpdater.Builder(null)
                        .setCompany(
                                testCompany()
                        )
                        .build()
        );

        //then
        assertThat(jobAdvertisement.getStatus()).isEqualTo(JobAdvertisementStatus.CREATED);

        Company company = jobAdvertisement.getJobContent().getCompany();
        assertThat(company).isNotNull();
        assertThat(company.getName()).isEqualTo("name");
        assertThat(company.getStreet()).isEqualTo("street");
        assertThat(company.getHouseNumber()).isEqualTo("houseNumber");
        assertThat(company.getPostalCode()).isEqualTo("postalCode");
        assertThat(company.getCity()).isEqualTo("city");
        assertThat(company.getCountryIsoCode()).isEqualTo("countryIsoCode");
        assertThat(company.getPostOfficeBoxNumber()).isEqualTo("postOfficeBoxNumber");
        assertThat(company.getPostOfficeBoxPostalCode()).isEqualTo("postOfficeBoxPostalCode");
        assertThat(company.getPostOfficeBoxCity()).isEqualTo("postOfficeBoxCity");
        assertThat(company.getPhone()).isEqualTo("phone");
        assertThat(company.getEmail()).isEqualTo("email");
        assertThat(company.getWebsite()).isEqualTo("website");

        JobAdvertisementEvent jobAdvertisementEvent = domainEventMockUtils.assertSingleDomainEventPublished(JobAdvertisementEvents.JOB_ADVERTISEMENT_UPDATED.getDomainEventType());
        assertThat(jobAdvertisementEvent.getAggregateId()).isEqualTo(job01.id());
    }

    @Test
    public void testInspect() {
        //given
        JobAdvertisement jobAdvertisement = testJobAdvertisement();

        //when
        jobAdvertisement.inspect();

        //then
        assertThat(jobAdvertisement.getStatus()).isEqualTo(JobAdvertisementStatus.INSPECTING);

        JobAdvertisementEvent jobAdvertisementEvent = domainEventMockUtils.assertSingleDomainEventPublished(JobAdvertisementEvents.JOB_ADVERTISEMENT_INSPECTING.getDomainEventType());
        assertThat(jobAdvertisementEvent.getAggregateId()).isEqualTo(job01.id());
    }

    @Test
    public void testShortTermValidation() {
        //given
        JobContent jobContent = testJobContent(job01.id());
        jobContent.setEmployment(testEmploymentPermanent());

        //when
        ConditionException exception = catchThrowableOfType(prepareJobAdvertisementBuilder(jobContent)::build, ConditionException.class);

        //then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).contains("Employment is short-term and permanent at the same time");
    }

    @Test
    public void testUpdateJobCenter() {
        JobAdvertisement jobAdvertisement = testJobAdvertisement(job01.id(), testJobContent(job01.id()), "jobCenterCode");
        JobCenter jobCenter = testJobCenter();
        jobAdvertisement.updateJobCenter(jobCenter);

        assertThat(jobAdvertisement.getJobContent().getDisplayCompany().getName()).isEqualTo("name");
    }

    @Test
    public void testShouldNotUpdateJobCenter() {
        JobAdvertisementId id = job01.id();
        JobContent jobContent = testJobContent(id);
        jobContent.setDisplayCompany(testCompany());
        JobAdvertisement jobAdvertisement = testJobAdvertisement(id, jobContent, "jobCenterCodeOther");
        JobCenter jobCenter = testJobCenter();

        assertThatThrownBy(() -> jobAdvertisement.updateJobCenter(jobCenter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("can not be different");
    }
}
