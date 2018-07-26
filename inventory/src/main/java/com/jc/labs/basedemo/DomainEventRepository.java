package com.jc.labs.basedemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface DomainEventRepository extends JpaRepository<DomainEvent, UUID> {
}
