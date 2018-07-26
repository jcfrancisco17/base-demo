package com.jc.labs.basedemo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
class InventoryController {

    private InventoryApplicationService inventoryApplicationService;

    InventoryController(InventoryApplicationService inventoryApplicationService) {
        this.inventoryApplicationService = inventoryApplicationService;
    }

    @PostMapping(path = "/inventory", produces = "application/hal+json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    Resource<InventoryAddResponse> addToInventory(@RequestBody InventoryAddRequest addRequest) {
        InventoryDto newInventory = inventoryApplicationService.addToInventory(addRequest.getName());

        InventoryAddResponse response = new InventoryAddResponse(newInventory.getId(), newInventory.getName(), newInventory.getDateCreated());
        Link selfRel = linkTo(methodOn(InventoryController.class).getById(newInventory.getId())).withSelfRel();
        response.add(selfRel);

        return new Resource<>(response, selfRel);
    }

    @GetMapping(path = "/inventory/{id}")
    InventoryDto getById(@PathVariable UUID id) {
        return inventoryApplicationService.getById(id);
    }
}
