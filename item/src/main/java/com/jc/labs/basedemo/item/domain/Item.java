package com.jc.labs.basedemo.item.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Item {

    @Id
    private UUID id;

    private String name;

    private LocalDateTime dateCreated;

    private Item() {
        // JPA
    }

    Item(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.dateCreated = LocalDateTime.now();
    }

    public static Item empty() {
        return new Item();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
