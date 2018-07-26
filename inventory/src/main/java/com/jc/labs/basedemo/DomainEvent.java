package com.jc.labs.basedemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
class DomainEvent {

    @Id
    private UUID id;

    private String eventName;

    @Column(columnDefinition = "CLOB NOT NULL")
    private String data;

    private EventState state;

    private enum EventState {
        NEW,
        PUBLISHED
    }

    private DomainEvent() {
        //JPA
    }

    private DomainEvent(String eventName, String data, EventState state) {
        this.id = UUID.randomUUID();
        this.eventName = eventName;
        this.data = data;
        this.state = state;
    }

    static DomainEvent newDomainEvent(String eventName, String data) {
        return new DomainEvent(eventName, data, EventState.NEW);
    }

    void markPublished() {
        this.state = EventState.PUBLISHED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DomainEvent that = (DomainEvent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
