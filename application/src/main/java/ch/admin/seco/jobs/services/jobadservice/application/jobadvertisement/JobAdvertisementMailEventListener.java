package ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement;

import ch.admin.seco.jobs.services.jobadservice.application.MailSenderData;
import ch.admin.seco.jobs.services.jobadservice.application.MailSenderService;
import ch.admin.seco.jobs.services.jobadservice.core.domain.AggregateNotFoundException;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisement;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementId;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementRepository;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.events.JobAdvertisementCancelledEvent;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.events.JobAdvertisementCreatedEvent;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.events.JobAdvertisementRefinedEvent;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.events.JobAdvertisementRejectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Component
public class JobAdvertisementMailEventListener {

    private static Logger LOG = LoggerFactory.getLogger(JobAdvertisementMailEventListener.class);

    private static final String JOB_ADVERTISEMENT_CREATED_SUBJECT = "mail.jobAd.created.subject";
    private static final String JOB_ADVERTISEMENT_CREATED_TEMPLATE = "JobAdCreatedMail.html";
    private static final String JOB_ADVERTISEMENT_REFINED_SUBJECT = "mail.jobAd.refined.subject";
    private static final String JOB_ADVERTISEMENT_REFINED_TEMPLATE = "JobAdRefinedMail.html";
    private static final String JOB_ADVERTISEMENT_REJECTED_SUBJECT = "mail.jobAd.rejected.subject";
    private static final String JOB_ADVERTISEMENT_REJECTED_TEMPLATE = "JobAdRejectedMail.html";
    private static final String JOB_ADVERTISEMENT_CANCELLED_SUBJECT = "mail.jobAd.cancelled.subject";
    private static final String JOB_ADVERTISEMENT_CANCELLED_TEMPLATE = "JobAdCancelledMail.html";

    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final MailSenderService mailSenderService;
    private final MessageSource messageSource;


    @Autowired
    public JobAdvertisementMailEventListener(JobAdvertisementRepository jobAdvertisementRepository, MailSenderService mailSenderService, MessageSource messageSource) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.mailSenderService = mailSenderService;
        this.messageSource = messageSource;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onCreated(JobAdvertisementCreatedEvent event) {
        LOG.debug("EVENT catched for mail: JOB_ADVERTISEMENT_CREATED for JobAdvertisementId: '{}'", event.getAggregateId().getValue());
        final JobAdvertisement jobAdvertisement = getJobAdvertisement(event.getAggregateId());
        final Locale contactLocale = jobAdvertisement.getContact().getLanguage();
        Map<String, Object> variables = new HashMap<>();
        variables.put("jobAdvertisement", jobAdvertisement);
        mailSenderService.send(
                new MailSenderData.Builder()
                        .setTo(jobAdvertisement.getContact().getEmail())
                        .setSubject(messageSource.getMessage(JOB_ADVERTISEMENT_CREATED_SUBJECT,
                                new Object[]{jobAdvertisement.getJobContent().getJobDescriptions().get(0).getTitle(), jobAdvertisement.getStellennummerEgov()}, contactLocale))
                        .setTemplateName(JOB_ADVERTISEMENT_CREATED_TEMPLATE)
                        .setTemplateVariables(variables)
                        .setLocale(contactLocale)
                        .build()
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onRefined(JobAdvertisementRefinedEvent event) {
        LOG.debug("EVENT catched for mail: JOB_ADVERTISEMENT_REFINED for JobAdvertisementId: '{}'", event.getAggregateId().getValue());
        final JobAdvertisement jobAdvertisement = getJobAdvertisement(event.getAggregateId());
        final Locale contactLocale = jobAdvertisement.getContact().getLanguage();
        Map<String, Object> variables = new HashMap<>();
        variables.put("jobAdvertisement", jobAdvertisement);
        mailSenderService.send(
                new MailSenderData.Builder()
                        .setTo(jobAdvertisement.getContact().getEmail())
                        .setSubject(messageSource.getMessage(JOB_ADVERTISEMENT_REFINED_SUBJECT,
                                new Object[]{jobAdvertisement.getJobContent().getJobDescriptions().get(0).getTitle(), jobAdvertisement.getStellennummerEgov()}, contactLocale))
                        .setTemplateName(JOB_ADVERTISEMENT_REFINED_TEMPLATE)
                        .setTemplateVariables(variables)
                        .setLocale(contactLocale)
                        .build()
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onRejected(JobAdvertisementRejectedEvent event) {
        LOG.debug("EVENT catched for mail: JOB_ADVERTISEMENT_REJECTED for JobAdvertisementId: '{}'", event.getAggregateId().getValue());
        final JobAdvertisement jobAdvertisement = getJobAdvertisement(event.getAggregateId());
        final Locale contactLocale = jobAdvertisement.getContact().getLanguage();
        Map<String, Object> variables = new HashMap<>();
        variables.put("jobAdvertisement", jobAdvertisement);
        mailSenderService.send(
                new MailSenderData.Builder()
                        .setTo(jobAdvertisement.getContact().getEmail())
                        .setSubject(messageSource.getMessage(JOB_ADVERTISEMENT_REJECTED_SUBJECT,
                                new Object[]{jobAdvertisement.getJobContent().getJobDescriptions().get(0).getTitle(), jobAdvertisement.getStellennummerEgov()}, contactLocale))
                        .setTemplateName(JOB_ADVERTISEMENT_REJECTED_TEMPLATE)
                        .setTemplateVariables(variables)
                        .setLocale(contactLocale)
                        .build()
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onCancelled(JobAdvertisementCancelledEvent event) {
        LOG.debug("EVENT catched for mail: JOB_ADVERTISEMENT_CANCELLED for JobAdvertisementId: '{}'", event.getAggregateId().getValue());
        final JobAdvertisement jobAdvertisement = getJobAdvertisement(event.getAggregateId());
        final Locale contactLocale = jobAdvertisement.getContact().getLanguage();
        Map<String, Object> variables = new HashMap<>();
        variables.put("jobAdvertisement", jobAdvertisement);
        mailSenderService.send(
                new MailSenderData.Builder()
                        .setTo(jobAdvertisement.getContact().getEmail())
                        .setSubject(messageSource.getMessage(JOB_ADVERTISEMENT_CANCELLED_SUBJECT,
                                new Object[]{jobAdvertisement.getJobContent().getJobDescriptions().get(0).getTitle(), jobAdvertisement.getStellennummerEgov()}, contactLocale))
                        .setTemplateName(JOB_ADVERTISEMENT_CANCELLED_TEMPLATE)
                        .setTemplateVariables(variables)
                        .setLocale(contactLocale)
                        .build()
        );
    }

    private JobAdvertisement getJobAdvertisement(JobAdvertisementId jobAdvertisementId) throws AggregateNotFoundException {
        Optional<JobAdvertisement> jobAdvertisement = jobAdvertisementRepository.findById(jobAdvertisementId);
        return jobAdvertisement.orElseThrow(() -> new AggregateNotFoundException(JobAdvertisement.class, jobAdvertisementId.getValue()));
    }

}
