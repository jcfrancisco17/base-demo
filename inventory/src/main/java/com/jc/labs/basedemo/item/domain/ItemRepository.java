package com.jc.labs.basedemo.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ItemRepository extends JpaRepository<Item, UUID> {
}
