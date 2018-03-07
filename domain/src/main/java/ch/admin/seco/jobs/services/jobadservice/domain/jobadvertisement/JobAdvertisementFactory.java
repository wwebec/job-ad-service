package ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement;

import ch.admin.seco.jobs.services.jobadservice.core.domain.events.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Component;

@Component
public class JobAdvertisementFactory {

    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final DataFieldMaxValueIncrementer stellennummerEgovGenerator;

    @Autowired
    public JobAdvertisementFactory(JobAdvertisementRepository jobAdvertisementRepository,
                                   DataFieldMaxValueIncrementer stellennummerEgovGenerator) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.stellennummerEgovGenerator = stellennummerEgovGenerator;
    }

    public JobAdvertisement createFromWebForm(String title, String description, JobAdvertisementUpdater updater) {
        JobAdvertisement jobAdvertisement = new JobAdvertisement.Builder()
                .setId(new JobAdvertisementId())
                .setSourceSystem(SourceSystem.JOBROOM)
                .setStatus(JobAdvertisementStatus.CREATED)
                .setTitle(title)
                .setDescription(description)
                .setStellennummerEgov(this.stellennummerEgovGenerator.nextStringValue())
                .build();

        jobAdvertisement.init(updater);
        JobAdvertisement newJobAdvertisement = jobAdvertisementRepository.save(jobAdvertisement);
        DomainEventPublisher.publish(new JobAdvertisementEvent(JobAdvertisementEvents.JOB_ADVERTISEMENT_CREATED, newJobAdvertisement));
        return newJobAdvertisement;
    }

    public JobAdvertisement createFromApi(String title, String description, JobAdvertisementUpdater updater) {
        JobAdvertisement jobAdvertisement = new JobAdvertisement.Builder()
                .setId(new JobAdvertisementId())
                .setSourceSystem(SourceSystem.API)
                .setStatus(JobAdvertisementStatus.CREATED)
                .setTitle(title)
                .setDescription(description)
                .setStellennummerEgov(this.stellennummerEgovGenerator.nextStringValue())
                .build();

        jobAdvertisement.init(updater);
        JobAdvertisement newJobAdvertisement = jobAdvertisementRepository.save(jobAdvertisement);
        DomainEventPublisher.publish(new JobAdvertisementEvent(JobAdvertisementEvents.JOB_ADVERTISEMENT_CREATED, newJobAdvertisement));
        return newJobAdvertisement;
    }

    public JobAdvertisement createFromAvam(String title, String description, JobAdvertisementUpdater updater) {
        // TODO Tbd which data are passed to create the JobAdvertisement Object
        JobAdvertisement jobAdvertisement = new JobAdvertisement.Builder()
                .setId(new JobAdvertisementId())
                .setSourceSystem(SourceSystem.RAV)
                .setStatus(JobAdvertisementStatus.APPROVED)
                .setTitle(title)
                .setDescription(description)
                .build();

        jobAdvertisement.init(updater);
        JobAdvertisement newJobAdvertisement = jobAdvertisementRepository.save(jobAdvertisement);
        DomainEventPublisher.publish(new JobAdvertisementEvent(JobAdvertisementEvents.JOB_ADVERTISEMENT_APPROVED, newJobAdvertisement));
        return newJobAdvertisement;
    }

    public JobAdvertisement createFromExtern(String title, String description, JobAdvertisementUpdater updater) {
        // TODO Tbd which data are passed to create the JobAdvertisement Object
        JobAdvertisement jobAdvertisement = new JobAdvertisement.Builder()
                .setId(new JobAdvertisementId())
                .setSourceSystem(SourceSystem.EXTERN)
                .setStatus(JobAdvertisementStatus.PUBLISHED_PUBLIC)
                .setTitle(title)
                .setDescription(description)
                .build();

        jobAdvertisement.init(updater);
        JobAdvertisement newJobAdvertisement = jobAdvertisementRepository.save(jobAdvertisement);
        DomainEventPublisher.publish(new JobAdvertisementEvent(JobAdvertisementEvents.JOB_ADVERTISEMENT_PUBLISH_PUBLIC, newJobAdvertisement));
        return newJobAdvertisement;
    }
}
