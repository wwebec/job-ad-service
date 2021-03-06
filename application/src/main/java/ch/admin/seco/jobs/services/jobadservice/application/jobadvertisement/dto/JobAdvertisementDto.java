package ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.CancellationCode;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisement;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementStatus;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.SourceSystem;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.WorkForm;

public class JobAdvertisementDto {

    private String id;

    private JobAdvertisementStatus status;

    private SourceSystem sourceSystem;

    private String externalReference;

    private String stellennummerEgov;

    private String stellennummerAvam;

    private String fingerprint;

    private boolean reportingObligation;

    private LocalDate reportingObligationEndDate;

    private boolean reportToAvam;

    private String jobCenterCode;

    private LocalDate approvalDate;

    private LocalDate rejectionDate;

    private String rejectionCode;

    private String rejectionReason;

    private LocalDate cancellationDate;

    private CancellationCode cancellationCode;

    @Valid
    private JobContentDto jobContent;

    @Valid
    private PublicationDto publication;

    protected JobAdvertisementDto() {
        // For reflection libs
    }

    public JobAdvertisementDto(String id, JobAdvertisementStatus status, SourceSystem sourceSystem, String externalReference, String stellennummerEgov, String stellennummerAvam, String fingerprint, boolean reportingObligation, LocalDate reportingObligationEndDate, boolean reportToAvam, String jobCenterCode, LocalDate approvalDate, LocalDate rejectionDate, String rejectionCode, String rejectionReason, LocalDate cancellationDate, CancellationCode cancellationCode, JobContentDto jobContent, OwnerDto owner, ContactDto contact, PublicationDto publication) {
        this.id = id;
        this.status = status;
        this.sourceSystem = sourceSystem;
        this.externalReference = externalReference;
        this.stellennummerEgov = stellennummerEgov;
        this.stellennummerAvam = stellennummerAvam;
        this.fingerprint = fingerprint;
        this.reportingObligation = reportingObligation;
        this.reportingObligationEndDate = reportingObligationEndDate;
        this.reportToAvam = reportToAvam;
        this.jobCenterCode = jobCenterCode;
        this.approvalDate = approvalDate;
        this.rejectionDate = rejectionDate;
        this.rejectionCode = rejectionCode;
        this.rejectionReason = rejectionReason;
        this.cancellationDate = cancellationDate;
        this.cancellationCode = cancellationCode;
        this.jobContent = jobContent;
        this.publication = publication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JobAdvertisementStatus getStatus() {
        return status;
    }

    public void setStatus(JobAdvertisementStatus status) {
        this.status = status;
    }

    public SourceSystem getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(SourceSystem sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getStellennummerEgov() {
        return stellennummerEgov;
    }

    public void setStellennummerEgov(String stellennummerEgov) {
        this.stellennummerEgov = stellennummerEgov;
    }

    public String getStellennummerAvam() {
        return stellennummerAvam;
    }

    public void setStellennummerAvam(String stellennummerAvam) {
        this.stellennummerAvam = stellennummerAvam;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public boolean isReportingObligation() {
        return reportingObligation;
    }

    public void setReportingObligation(boolean reportingObligation) {
        this.reportingObligation = reportingObligation;
    }

    public LocalDate getReportingObligationEndDate() {
        return reportingObligationEndDate;
    }

    public void setReportingObligationEndDate(LocalDate reportingObligationEndDate) {
        this.reportingObligationEndDate = reportingObligationEndDate;
    }

    public boolean isReportToAvam() {
        return reportToAvam;
    }

    public void setReportToAvam(boolean reportToAvam) {
        this.reportToAvam = reportToAvam;
    }

    public String getJobCenterCode() {
        return jobCenterCode;
    }

    public void setJobCenterCode(String jobCenterCode) {
        this.jobCenterCode = jobCenterCode;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getRejectionDate() {
        return rejectionDate;
    }

    public void setRejectionDate(LocalDate rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    public String getRejectionCode() {
        return rejectionCode;
    }

    public void setRejectionCode(String rejectionCode) {
        this.rejectionCode = rejectionCode;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public CancellationCode getCancellationCode() {
        return cancellationCode;
    }

    public void setCancellationCode(CancellationCode cancellationCode) {
        this.cancellationCode = cancellationCode;
    }

    public JobContentDto getJobContent() {
        return jobContent;
    }

    public void setJobContent(JobContentDto jobContent) {
        this.jobContent = jobContent;
    }

    public PublicationDto getPublication() {
        return publication;
    }

    public void setPublication(PublicationDto publication) {
        this.publication = publication;
    }

    public static JobAdvertisementDto toDto(JobAdvertisement jobAdvertisement) {
        JobAdvertisementDto jobAdvertisementDto = new JobAdvertisementDto();
        jobAdvertisementDto.setId(jobAdvertisement.getId().getValue());
        jobAdvertisementDto.setStatus(jobAdvertisement.getStatus());
        jobAdvertisementDto.setSourceSystem(jobAdvertisement.getSourceSystem());
        jobAdvertisementDto.setExternalReference(jobAdvertisement.getExternalReference());
        jobAdvertisementDto.setStellennummerEgov(jobAdvertisement.getStellennummerEgov());
        jobAdvertisementDto.setStellennummerAvam(jobAdvertisement.getStellennummerAvam());
        jobAdvertisementDto.setFingerprint(jobAdvertisement.getFingerprint());
        jobAdvertisementDto.setReportingObligation(jobAdvertisement.isReportingObligation());
        jobAdvertisementDto.setReportingObligationEndDate(jobAdvertisement.getReportingObligationEndDate());
        jobAdvertisementDto.setReportToAvam(jobAdvertisement.isReportToAvam());
        jobAdvertisementDto.setJobCenterCode(jobAdvertisement.getJobCenterCode());
        jobAdvertisementDto.setApprovalDate(jobAdvertisement.getApprovalDate());
        jobAdvertisementDto.setRejectionDate(jobAdvertisement.getRejectionDate());
        jobAdvertisementDto.setRejectionCode(jobAdvertisement.getRejectionCode());
        jobAdvertisementDto.setRejectionReason(jobAdvertisement.getRejectionReason());
        jobAdvertisementDto.setCancellationDate(jobAdvertisement.getCancellationDate());
        jobAdvertisementDto.setCancellationCode(jobAdvertisement.getCancellationCode());
        jobAdvertisementDto.setJobContent(JobContentDto.toDto(jobAdvertisement.getJobContent()));
        jobAdvertisementDto.setPublication(PublicationDto.toDto(jobAdvertisement.getPublication()));

        // Eager load data from ElementCollection
        Set<WorkForm> workForms = jobAdvertisement.getJobContent().getEmployment().getWorkForms() != null
                ? jobAdvertisement.getJobContent().getEmployment().getWorkForms()
                : Collections.emptySet();

        jobAdvertisementDto.getJobContent().getEmployment().setWorkForms(new HashSet<>(workForms));
        return jobAdvertisementDto;
    }
}
