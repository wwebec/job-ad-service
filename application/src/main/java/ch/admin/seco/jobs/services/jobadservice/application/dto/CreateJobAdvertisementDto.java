package ch.admin.seco.jobs.services.jobadservice.application.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public class CreateJobAdvertisementDto {

    @NotNull
    private String title;
    @NotNull
    private String description;
    private LocalDate employmentStartDate;
    private LocalDate employmentEndDate;
    private Boolean immediately;
    private Boolean permanent;
    private int workloadPercentageMin;
    private int workloadPercentageMax;
    private String drivingLicenseLevel;
    // FIXME Education
    private ApplyChannelDto applyChannel;
    @NotNull
    private CompanyDto company;
    @NotNull
    private ContactDto contact;
    @NotNull
    private Set<LocalityDto> localities;
    @NotNull
    private OccupationDto occupation;
    private Set<LanguageSkillDto> languageSkills;

    protected CreateJobAdvertisementDto() {
        // For reflection libs
    }

    public CreateJobAdvertisementDto(String title, String description, LocalDate employmentStartDate, LocalDate employmentEndDate, Boolean immediately, Boolean permanent, int workloadPercentageMin, int workloadPercentageMax, String drivingLicenseLevel, ApplyChannelDto applyChannel, CompanyDto company, ContactDto contact, Set<LocalityDto> localities, OccupationDto occupation, Set<LanguageSkillDto> languageSkills) {
        this.title = title;
        this.description = description;
        this.employmentStartDate = employmentStartDate;
        this.employmentEndDate = employmentEndDate;
        this.immediately = immediately;
        this.permanent = permanent;
        this.workloadPercentageMin = workloadPercentageMin;
        this.workloadPercentageMax = workloadPercentageMax;
        this.drivingLicenseLevel = drivingLicenseLevel;
        this.applyChannel = applyChannel;
        this.company = company;
        this.contact = contact;
        this.localities = localities;
        this.occupation = occupation;
        this.languageSkills = languageSkills;
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

    public LocalDate getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(LocalDate employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public LocalDate getEmploymentEndDate() {
        return employmentEndDate;
    }

    public void setEmploymentEndDate(LocalDate employmentEndDate) {
        this.employmentEndDate = employmentEndDate;
    }

    public Boolean getImmediately() {
        return immediately;
    }

    public void setImmediately(Boolean immediately) {
        this.immediately = immediately;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    public int getWorkloadPercentageMin() {
        return workloadPercentageMin;
    }

    public void setWorkloadPercentageMin(int workloadPercentageMin) {
        this.workloadPercentageMin = workloadPercentageMin;
    }

    public int getWorkloadPercentageMax() {
        return workloadPercentageMax;
    }

    public void setWorkloadPercentageMax(int workloadPercentageMax) {
        this.workloadPercentageMax = workloadPercentageMax;
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

    public Set<LocalityDto> getLocalities() {
        return localities;
    }

    public void setLocalities(Set<LocalityDto> localities) {
        this.localities = localities;
    }

    public OccupationDto getOccupation() {
        return occupation;
    }

    public void setOccupation(OccupationDto occupation) {
        this.occupation = occupation;
    }

    public Set<LanguageSkillDto> getLanguageSkills() {
        return languageSkills;
    }

    public void setLanguageSkills(Set<LanguageSkillDto> languageSkills) {
        this.languageSkills = languageSkills;
    }

}
