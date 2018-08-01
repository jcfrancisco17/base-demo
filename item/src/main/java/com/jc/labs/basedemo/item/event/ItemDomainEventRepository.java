package com.jc.labs.basedemo.item.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemDomainEventRepository extends JpaRepository<ItemDomainEvent, UUID> {
    List<ItemDomainEvent> findByState(EventState state);
}
