package ch.admin.seco.jobs.services.jobadservice.infrastructure.web.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.LanguageLevel;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.LanguageSkill;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.utils.SupportedLanguageIsoCode;

public class ApiLanguageSkillDto {

    @NotBlank
    @SupportedLanguageIsoCode
    private String languageIsoCode;

    @NotNull
    private LanguageLevel spokenLevel;

    @NotNull
    private LanguageLevel writtenLevel;

    protected ApiLanguageSkillDto() {
        // For reflection libs
    }

    public ApiLanguageSkillDto(String languageIsoCode, LanguageLevel spokenLevel, LanguageLevel writtenLevel) {
        this.languageIsoCode = languageIsoCode;
        this.spokenLevel = spokenLevel;
        this.writtenLevel = writtenLevel;
    }

    public String getLanguageIsoCode() {
        return languageIsoCode;
    }

    public void setLanguageIsoCode(String languageIsoCode) {
        this.languageIsoCode = languageIsoCode;
    }

    public LanguageLevel getSpokenLevel() {
        return spokenLevel;
    }

    public void setSpokenLevel(LanguageLevel spokenLevel) {
        this.spokenLevel = spokenLevel;
    }

    public LanguageLevel getWrittenLevel() {
        return writtenLevel;
    }

    public void setWrittenLevel(LanguageLevel writtenLevel) {
        this.writtenLevel = writtenLevel;
    }

    public static ApiLanguageSkillDto toDto(LanguageSkill languageSkill) {
        ApiLanguageSkillDto languageSkillDto = new ApiLanguageSkillDto();
        languageSkillDto.setLanguageIsoCode(languageSkill.getLanguageIsoCode());
        languageSkillDto.setSpokenLevel(languageSkill.getSpokenLevel());
        languageSkillDto.setWrittenLevel(languageSkill.getWrittenLevel());
        return languageSkillDto;
    }

    public static List<ApiLanguageSkillDto> toDto(List<LanguageSkill> languageSkills) {
        if (languageSkills != null) {
            return languageSkills.stream().map(ApiLanguageSkillDto::toDto).collect(Collectors.toList());
        }

        return null;
    }
}
