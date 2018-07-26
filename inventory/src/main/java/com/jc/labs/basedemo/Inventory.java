package com.jc.labs.basedemo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
class Inventory {

    @Id
    private UUID id;

    private String name;

    private LocalDateTime dateCreated;

    private Inventory() {
        // JPA
    }

    Inventory(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.dateCreated = LocalDateTime.now();
    }

    public static Inventory empty() {
        return new Inventory();
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
        Inventory inventory = (Inventory) o;
        return Objects.equals(id, inventory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
