package ch.admin.seco.jobs.services.jobadservice.core.domain.events;

import ch.admin.seco.jobs.services.jobadservice.core.conditions.Condition;
import ch.admin.seco.jobs.services.jobadservice.core.time.TimeMachine;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public abstract class DomainEvent {

    private final DomainEventId id;

    private final LocalDateTime registrationTime;

    private final DomainEventType domainEventType;

    private final String aggregateType;

    private String userExternalId;

    private String userDisplayName;

    private String userEmail;

    protected transient Map<String, Object> additionalAttributes = new HashMap<>();

    public DomainEvent(DomainEventType domainEventType, String aggregateType) {
        this.id = new DomainEventId();
        this.domainEventType = Condition.notNull(domainEventType);
        this.aggregateType = aggregateType;
        this.registrationTime = TimeMachine.now();
    }

    public abstract String getAggregateId();

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public DomainEventType getDomainEventType() {
        return domainEventType;
    }

    public String getUserExternalId() {
        return userExternalId;
    }

    public DomainEventId getId() {
        return id;
    }

    public Map<String, Object> getAttributesMap() {
        return Collections.unmodifiableMap(additionalAttributes);
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setAuditUser(AuditUser auditUser) {
        Condition.notNull(auditUser);
        this.userDisplayName = auditUser.getDisplayName();
        this.userExternalId = auditUser.getExternalId();
        this.userEmail = auditUser.getEmail();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainEvent)) return false;
        DomainEvent that = (DomainEvent) o;
        return Objects.equals(id, that.id);
    }
}
