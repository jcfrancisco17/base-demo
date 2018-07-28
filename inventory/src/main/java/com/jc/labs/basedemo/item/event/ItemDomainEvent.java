package com.jc.labs.basedemo.item.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ItemDomainEvent {

    @Id
    private UUID id;

    private String eventName;

    @Column(columnDefinition = "CLOB NOT NULL")
    private String data;

    @Enumerated(EnumType.STRING)
    private EventState state;

    private ItemDomainEvent() {
        //JPA
    }

    private ItemDomainEvent(String eventName, String data, EventState state) {
        this.id = UUID.randomUUID();
        this.eventName = eventName;
        this.data = data;
        this.state = state;
    }

    public static ItemDomainEvent newItemAdded(String data) {
        return new ItemDomainEvent("itemAdded", data, EventState.NEW);
    }

    //Make these domain event names type safe
    public static ItemDomainEvent itemDeleted(String data) {
        return new ItemDomainEvent("itemDeleted", data, EventState.NEW);
    }

    String getEventName() {
        return eventName;
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
        ItemDomainEvent that = (ItemDomainEvent) o;
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
