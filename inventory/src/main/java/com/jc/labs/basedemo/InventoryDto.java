package com.jc.labs.basedemo;

import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryDto {
    private UUID id;
    private String name;
    private LocalDateTime dateCreated;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public InventoryDto(UUID id, String name, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }
}
