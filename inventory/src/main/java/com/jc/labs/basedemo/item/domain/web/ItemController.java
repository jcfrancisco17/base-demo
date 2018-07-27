package com.jc.labs.basedemo.item.domain.web;

import com.jc.labs.basedemo.item.domain.Item;
import com.jc.labs.basedemo.item.domain.ItemApplicationService;
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
class ItemController {

    private ItemApplicationService itemApplicationService;

    ItemController(ItemApplicationService itemApplicationService) {
        this.itemApplicationService = itemApplicationService;
    }

    @PostMapping(path = "/inventory", produces = "application/hal+json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    Resource<ItemAddResponse> addToInventory(@RequestBody ItemAddRequest addRequest) {
        Item newItem = itemApplicationService.addToInventory(addRequest.getName());

        ItemAddResponse response = new ItemAddResponse(newItem.getId(), newItem.getName(), newItem.getDateCreated());
        Link selfRel = linkTo(methodOn(ItemController.class).getById(newItem.getId())).withSelfRel();
        response.add(selfRel);

        return new Resource<>(response, selfRel);
    }

    @GetMapping(path = "/inventory/{id}")
    Item getById(@PathVariable UUID id) {
        return itemApplicationService.getById(id);
    }
}
