package com.jc.labs.basedemo.item.domain.web;

import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;
import java.util.UUID;

class ItemAddResponse extends ResourceSupport {

    private UUID itemId;
    private String name;
    private LocalDateTime dateCreated;

    ItemAddResponse(UUID id, String name, LocalDateTime dateCreated) {
        this.itemId = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public UUID getitemId() {
        return itemId;
    }
}
