package com.jc.labs.basedemo.item.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jc.labs.basedemo.item.event.ItemDomainEvent;
import com.jc.labs.basedemo.item.event.ItemDomainEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ItemApplicationService {

    private ItemRepository itemRepository;

    private ItemDomainEventRepository itemDomainEventRepository;

    ItemApplicationService(ItemRepository itemRepository,
                           ItemDomainEventRepository iventoryDomainEventRepository) {
        this.itemRepository = itemRepository;
        this.itemDomainEventRepository = iventoryDomainEventRepository;
    }

    public Item addItem(String name) {
        Item item = new Item(name);
        Item newItem = itemRepository.save(item);
        ItemDomainEvent inventoryAdded = ItemDomainEvent.newItemAdded(toJSON(newItem));
        itemDomainEventRepository.save(inventoryAdded);
        return newItem;
    }

    public void deleteItem(UUID id) {
        Item toDelete = itemRepository
                .findById(id)
                .orElseThrow(ItemNotFoundException::new);

        itemRepository.deleteById(id);
        itemDomainEventRepository.save(ItemDomainEvent.itemDeleted(toJSON(toDelete)));
    }

    private String toJSON(Item item) {
        try {
            return new ObjectMapper().writeValueAsString(item);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Item getById(UUID id) {
        return itemRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
    }
}
