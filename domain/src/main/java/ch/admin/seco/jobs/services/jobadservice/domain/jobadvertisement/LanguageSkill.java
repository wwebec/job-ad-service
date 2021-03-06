package ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import ch.admin.seco.jobs.services.jobadservice.core.conditions.Condition;
import ch.admin.seco.jobs.services.jobadservice.core.domain.ValueObject;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.utils.SupportedLanguageIsoCode;

@Embeddable
@Access(AccessType.FIELD)
public class LanguageSkill implements ValueObject<LanguageSkill> {

    @SupportedLanguageIsoCode
    @NotBlank
    private String languageIsoCode;

    @Enumerated(EnumType.STRING)
    private LanguageLevel spokenLevel;

    @Enumerated(EnumType.STRING)
    private LanguageLevel writtenLevel;

    protected LanguageSkill() {
        // For reflection libs
    }

    public LanguageSkill(Builder builder) {
        this.languageIsoCode = Condition.notBlank(builder.languageIsoCode).toLowerCase();
        this.spokenLevel = builder.spokenLevel;
        this.writtenLevel = builder.writtenLevel;
    }

    public String getLanguageIsoCode() {
        return languageIsoCode;
    }

    public LanguageLevel getSpokenLevel() {
        return spokenLevel;
    }

    public LanguageLevel getWrittenLevel() {
        return writtenLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        LanguageSkill that = (LanguageSkill) o;
        return Objects.equals(languageIsoCode, that.languageIsoCode) &&
                spokenLevel == that.spokenLevel &&
                writtenLevel == that.writtenLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageIsoCode, spokenLevel, writtenLevel);
    }

    @Override
    public String toString() {
        return "LanguageSkill{" +
                "languageIsoCode='" + languageIsoCode + '\'' +
                ", spokenLevel=" + spokenLevel +
                ", writtenLevel=" + writtenLevel +
                '}';
    }

    public static final class Builder {
        private String languageIsoCode;
        private LanguageLevel spokenLevel;
        private LanguageLevel writtenLevel;

        public Builder() {
        }

        public LanguageSkill build() {
            return new LanguageSkill(this);
        }

        public Builder setLanguageIsoCode(String languageIsoCode) {
            this.languageIsoCode = languageIsoCode;
            return this;
        }

        public Builder setSpokenLevel(LanguageLevel spokenLevel) {
            this.spokenLevel = spokenLevel;
            return this;
        }

        public Builder setWrittenLevel(LanguageLevel writtenLevel) {
            this.writtenLevel = writtenLevel;
            return this;
        }
    }
}
