package com.jc.labs.basedemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
class InventoryDomainEvent {

    @Id
    private UUID id;

    private String eventName;

    @Column(columnDefinition = "CLOB NOT NULL")
    private String data;

    @Enumerated(EnumType.STRING)
    private EventState state;

    private InventoryDomainEvent() {
        //JPA
    }

    private InventoryDomainEvent(String eventName, String data, EventState state) {
        this.id = UUID.randomUUID();
        this.eventName = eventName;
        this.data = data;
        this.state = state;
    }

    static InventoryDomainEvent newDomainEvent(String eventName, String data) {
        return new InventoryDomainEvent(eventName, data, EventState.NEW);
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
        InventoryDomainEvent that = (InventoryDomainEvent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public String getData() {
        return data;
    }
}
