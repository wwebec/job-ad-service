package ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto;

import java.time.LocalDate;
import java.util.List;

import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisement;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.JobAdvertisementStatus;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.SourceSystem;

public class JobAdvertisementDto {

    private String id;
    private String stellennummerEgov;
    private String stellennummerAvam;
    private String fingerprint;
    private SourceSystem sourceSystem;
    private String sourceEntryId;
    private String externalUrl;
    private JobAdvertisementStatus status;
    private LocalDate ravRegistrationDate;
    private LocalDate approvalDate;
    private LocalDate rejectionDate;
    private String rejectionCode;
    private String rejectionReason;
    private LocalDate cancellationDate;
    private String cancellationCode;
    private boolean reportingObligation;
    private LocalDate reportingObligationEndDate;
    private LocalDate publicationStartDate;
    private LocalDate publicationEndDate;
    private boolean eures;
    private boolean euresAnonymous;
    private String title;
    private String description;
    private EmploymentDto employment;
    private String jobCenterCode;
    private String drivingLicenseLevel;
    private ApplyChannelDto applyChannel;
    private CompanyDto company;
    private ContactDto contact;
    private LocalityDto locality;
    private List<OccupationDto> occupations;
    private List<LanguageSkillDto> languageSkills;

    protected JobAdvertisementDto() {
        // For reflection libs
    }

    public JobAdvertisementDto(String id, String stellennummerEgov, String stellennummerAvam, String fingerprint, SourceSystem sourceSystem, String sourceEntryId, String externalUrl, JobAdvertisementStatus status, LocalDate ravRegistrationDate, LocalDate approvalDate, LocalDate rejectionDate, String rejectionCode, String rejectionReason, LocalDate cancellationDate, String cancellationCode, boolean reportingObligation, LocalDate reportingObligationEndDate, LocalDate publicationStartDate, LocalDate publicationEndDate, boolean eures, boolean euresAnonymous, String title, String description, EmploymentDto employment, String jobCenterCode, String drivingLicenseLevel, ApplyChannelDto applyChannel, CompanyDto company, ContactDto contact, LocalityDto locality, List<OccupationDto> occupations, List<LanguageSkillDto> languageSkills) {
        this.id = id;
        this.stellennummerEgov = stellennummerEgov;
        this.stellennummerAvam = stellennummerAvam;
        this.fingerprint = fingerprint;
        this.sourceSystem = sourceSystem;
        this.sourceEntryId = sourceEntryId;
        this.externalUrl = externalUrl;
        this.status = status;
        this.ravRegistrationDate = ravRegistrationDate;
        this.approvalDate = approvalDate;
        this.rejectionDate = rejectionDate;
        this.rejectionCode = rejectionCode;
        this.rejectionReason = rejectionReason;
        this.cancellationDate = cancellationDate;
        this.cancellationCode = cancellationCode;
        this.reportingObligation = reportingObligation;
        this.reportingObligationEndDate = reportingObligationEndDate;
        this.publicationStartDate = publicationStartDate;
        this.publicationEndDate = publicationEndDate;
        this.eures = eures;
        this.euresAnonymous = euresAnonymous;
        this.title = title;
        this.description = description;
        this.employment = employment;
        this.jobCenterCode = jobCenterCode;
        this.drivingLicenseLevel = drivingLicenseLevel;
        this.applyChannel = applyChannel;
        this.company = company;
        this.contact = contact;
        this.locality = locality;
        this.occupations = occupations;
        this.languageSkills = languageSkills;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static JobAdvertisementDto toDto(JobAdvertisement jobAdvertisement) {
        JobAdvertisementDto jobAdvertisementDto = new JobAdvertisementDto();
        jobAdvertisementDto.setId(jobAdvertisement.getId().getValue());
        jobAdvertisementDto.setStellennummerEgov(jobAdvertisement.getStellennummerEgov());
        jobAdvertisementDto.setStellennummerAvam(jobAdvertisement.getStellennummerAvam());
        jobAdvertisementDto.setFingerprint(jobAdvertisement.getFingerprint());
        jobAdvertisementDto.setSourceSystem(jobAdvertisement.getSourceSystem());
        jobAdvertisementDto.setSourceEntryId(jobAdvertisement.getSourceEntryId());
        jobAdvertisementDto.setExternalUrl(jobAdvertisement.getExternalUrl());
        jobAdvertisementDto.setStatus(jobAdvertisement.getStatus());
        jobAdvertisementDto.setRavRegistrationDate(jobAdvertisement.getRavRegistrationDate());
        jobAdvertisementDto.setApprovalDate(jobAdvertisement.getApprovalDate());
        jobAdvertisementDto.setRejectionDate(jobAdvertisement.getRejectionDate());
        jobAdvertisementDto.setRejectionCode(jobAdvertisement.getRejectionCode());
        jobAdvertisementDto.setRejectionReason(jobAdvertisement.getRejectionReason());
        jobAdvertisementDto.setCancellationDate(jobAdvertisement.getCancellationDate());
        jobAdvertisementDto.setCancellationCode(jobAdvertisement.getCancellationCode());
        jobAdvertisementDto.setReportingObligation(jobAdvertisement.isReportingObligation());
        jobAdvertisementDto.setReportingObligationEndDate(jobAdvertisement.getReportingObligationEndDate());
        jobAdvertisementDto.setPublicationStartDate(jobAdvertisement.getPublicationStartDate());
        jobAdvertisementDto.setPublicationEndDate(jobAdvertisement.getPublicationEndDate());
        jobAdvertisementDto.setEures(jobAdvertisement.isEures());
        jobAdvertisementDto.setEuresAnonymous(jobAdvertisement.isEuresAnonymous());
        jobAdvertisementDto.setTitle(jobAdvertisement.getTitle());
        jobAdvertisementDto.setDescription(jobAdvertisement.getDescription());
        jobAdvertisementDto.setEmployment(EmploymentDto.toDto(jobAdvertisement.getEmployment()));
        jobAdvertisementDto.setJobCenterCode(jobAdvertisement.getJobCenterCode());
        jobAdvertisementDto.setDrivingLicenseLevel(jobAdvertisement.getDrivingLicenseLevel());
        jobAdvertisementDto.setApplyChannel(ApplyChannelDto.toDto(jobAdvertisement.getApplyChannel()));
        jobAdvertisementDto.setCompany(CompanyDto.toDto(jobAdvertisement.getCompany()));
        jobAdvertisementDto.setContact(ContactDto.toDto(jobAdvertisement.getContact()));
        jobAdvertisementDto.setLocality(LocalityDto.toDto(jobAdvertisement.getLocality()));
        jobAdvertisementDto.setOccupations(OccupationDto.toDto(jobAdvertisement.getOccupations()));
        jobAdvertisementDto.setLanguageSkills(LanguageSkillDto.toDto(jobAdvertisement.getLanguageSkills()));
        return jobAdvertisementDto;
    }

    public String getStellennummerEgov() {
        return stellennummerEgov;
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

    public SourceSystem getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(SourceSystem sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceEntryId() {
        return sourceEntryId;
    }

    public void setSourceEntryId(String sourceEntryId) {
        this.sourceEntryId = sourceEntryId;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public JobAdvertisementStatus getStatus() {
        return status;
    }

    public void setStatus(JobAdvertisementStatus status) {
        this.status = status;
    }

    public LocalDate getRavRegistrationDate() {
        return ravRegistrationDate;
    }

    public void setRavRegistrationDate(LocalDate ravRegistrationDate) {
        this.ravRegistrationDate = ravRegistrationDate;
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

    public String getCancellationCode() {
        return cancellationCode;
    }

    public void setCancellationCode(String cancellationCode) {
        this.cancellationCode = cancellationCode;
    }

    public boolean isReportingObligation() {
        return reportingObligation;
    }

    public void setReportingObligation(boolean reportingObligation) {
        this.reportingObligation = reportingObligation;
    }

    public void setStellennummerEgov(String stellennummerEgov) {
        this.stellennummerEgov = stellennummerEgov;
    }

    public LocalDate getReportingObligationEndDate() {
        return reportingObligationEndDate;
    }

    public LocalDate getPublicationStartDate() {
        return publicationStartDate;
    }

    public void setPublicationStartDate(LocalDate publicationStartDate) {
        this.publicationStartDate = publicationStartDate;
    }

    public LocalDate getPublicationEndDate() {
        return publicationEndDate;
    }

    public void setPublicationEndDate(LocalDate publicationEndDate) {
        this.publicationEndDate = publicationEndDate;
    }

    public boolean isEures() {
        return eures;
    }

    public void setEures(boolean eures) {
        this.eures = eures;
    }

    public boolean isEuresAnonymous() {
        return euresAnonymous;
    }

    public void setEuresAnonymous(boolean euresAnonymous) {
        this.euresAnonymous = euresAnonymous;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReportingObligationEndDate(LocalDate reportingObligationEndDate) {
        this.reportingObligationEndDate = reportingObligationEndDate;
    }

    public EmploymentDto getEmployment() {
        return employment;
    }

    public String getJobCenterCode() {
        return jobCenterCode;
    }

    public void setJobCenterCode(String jobCenterCode) {
        this.jobCenterCode = jobCenterCode;
    }

    public String getDrivingLicenseLevel() {
        return drivingLicenseLevel;
    }

    public void setDrivingLicenseLevel(String drivingLicenseLevel) {
        this.drivingLicenseLevel = drivingLicenseLevel;
    }

    public ApplyChannelDto getApplyChannel() {
        return applyChannel;
    }

    public void setApplyChannel(ApplyChannelDto applyChannel) {
        this.applyChannel = applyChannel;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    public void setEmployment(EmploymentDto employment) {
        this.employment = employment;
    }

    public LocalityDto getLocality() {
        return locality;
    }

    public List<OccupationDto> getOccupations() {
        return occupations;
    }

    public void setOccupations(List<OccupationDto> occupations) {
        this.occupations = occupations;
    }

    public List<LanguageSkillDto> getLanguageSkills() {
        return languageSkills;
    }

    public void setLanguageSkills(List<LanguageSkillDto> languageSkills) {
        this.languageSkills = languageSkills;
    }

    public void setLocality(LocalityDto locality) {
        this.locality = locality;
    }
}
