package ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement;

import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementTestDataProvider.createContact;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementTestDataProvider.createJobContent;
import static ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementTestDataProvider.createOwner;
import static org.assertj.core.api.Assertions.assertThat;

import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.events.JobAdvertisementEvent;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.events.JobAdvertisementEvents;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.admin.seco.jobs.services.jobadservice.core.domain.events.DomainEventMockUtils;

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

   // @Test
    public void testUpdate() {
        //Prepare
        JobAdvertisement jobAdvertisement = new JobAdvertisement.Builder()
                .setId(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01)
                .setOwner(createOwner(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01))
                .setContact(createContact(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01))
                .setJobContent(createJobContent(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01))
                .setPublication(new Publication.Builder().build())
                .setSourceSystem(SourceSystem.JOBROOM)
                .setStatus(JobAdvertisementStatus.CREATED)
                .build();

        //Execute
        jobAdvertisement.update(
                new JobAdvertisementUpdater.Builder(null)
                        .setCompany(
                                new Company.Builder()
                                        .setName("name")
                                        .setStreet("street")
                                        .setHouseNumber("houseNumber")
                                        .setPostalCode("postalCode")
                                        .setCity("city")
                                        .setCountryIsoCode("countryIsoCode")
                                        .setPostOfficeBoxNumber("postOfficeBoxNumber")
                                        .setPostOfficeBoxPostalCode("postOfficeBoxPostalCode")
                                        .setPostOfficeBoxCity("postOfficeBoxCity")
                                        .setPhone("phone")
                                        .setEmail("email")
                                        .setWebsite("website")
                                        .build()
                        )
                        .build()
        );

        //Validate
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
        assertThat(jobAdvertisementEvent.getAggregateId()).isEqualTo(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01);
    }

    @Test
    public void testInspect() {
        //Prepare
        JobAdvertisement jobAdvertisement = new JobAdvertisement.Builder()
                .setId(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01)
                .setOwner(createOwner(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01))
                .setContact(createContact(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01))
                .setJobContent(createJobContent(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01))
                .setPublication(new Publication.Builder().build())
                .setSourceSystem(SourceSystem.JOBROOM)
                .setStatus(JobAdvertisementStatus.CREATED)
                .setStellennummerEgov("stellennummerEgov")
                .build();

        //Execute
        jobAdvertisement.inspect();

        //Validate
        assertThat(jobAdvertisement.getStatus()).isEqualTo(JobAdvertisementStatus.INSPECTING);

        JobAdvertisementEvent jobAdvertisementEvent = domainEventMockUtils.assertSingleDomainEventPublished(JobAdvertisementEvents.JOB_ADVERTISEMENT_INSPECTING.getDomainEventType());
        assertThat(jobAdvertisementEvent.getAggregateId()).isEqualTo(JobAdvertisementTestDataProvider.JOB_ADVERTISEMENT_ID_01);
    }

    //@Test
    public void testApprove() {
    }

    //@Test
    public void testReject() {
    }

    //@Test
    public void testCancel() {
    }

    //@Test
    public void testRefining() {
    }

    //@Test
    public void testPublishRestricted() {
    }

    //@Test
    public void testPublishPublic() {
    }

    //@Test
    public void testArchive() {
    }

}
