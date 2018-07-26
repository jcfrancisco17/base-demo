package com.jc.labs.basedemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class InventoryApplicationService {

    private InventoryRepository inventoryRepository;

    private DomainEventRepository domainEventRepository;

    InventoryApplicationService(InventoryRepository inventoryRepository,
                                DomainEventRepository domainEventRepository) {
        this.inventoryRepository = inventoryRepository;
        this.domainEventRepository = domainEventRepository;
    }

    public InventoryDto addToInventory(String name) {
        Inventory inventory = new Inventory(name);
        inventoryRepository.save(inventory);
        DomainEvent inventoryAdded = DomainEvent.newDomainEvent("inventoryAdded", toJSON(inventory));
        domainEventRepository.save(inventoryAdded);
        return new InventoryDto(inventory.getId(), inventory.getName(), inventory.getDateCreated());
    }

    private String toJSON(Inventory inventory) {
        try {
            return new ObjectMapper().writeValueAsString(inventory);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public InventoryDto getById(UUID id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElse(Inventory.empty());
        return new InventoryDto(inventory.getId(), inventory.getName(), inventory.getDateCreated());
    }
}
