package com.jc.labs.basedemo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryAddResponse extends ResourceSupport {

    private UUID inventoryId;
    private String name;
    private LocalDateTime dateCreated;

    public InventoryAddResponse(UUID id, String name, LocalDateTime dateCreated) {
        this.inventoryId = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public UUID getInventoryId() {
        return inventoryId;
    }
}
