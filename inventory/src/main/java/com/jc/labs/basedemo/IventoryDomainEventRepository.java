package com.jc.labs.basedemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface IventoryDomainEventRepository extends JpaRepository<InventoryDomainEvent, UUID> {

    List<InventoryDomainEvent> findByState(EventState state);
}
