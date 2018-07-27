package com.jc.labs.basedemo;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class InventoryEventPublisher {

    private IventoryDomainEventRepository inventoryDomainEventRepository;

    private MessageBrokerChannels messageBrokerChannels;

    InventoryEventPublisher(IventoryDomainEventRepository iventoryDomainEventRepository,
                            MessageBrokerChannels messageBrokerChannels) {
        this.inventoryDomainEventRepository = iventoryDomainEventRepository;
        this.messageBrokerChannels = messageBrokerChannels;
    }

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void publishEvents() {
        System.out.println("Publishing events to broker");
        var events = inventoryDomainEventRepository.findByState(EventState.NEW);
        events
                .forEach(domainEvent -> {
                    boolean sent = messageBrokerChannels.inventoryAdded().send(buildMessage(domainEvent));
                   if (sent) {
                       domainEvent.markPublished();
                   }
                });
    }

    private Message<String> buildMessage(InventoryDomainEvent domainEvent) {
        return MessageBuilder.withPayload(domainEvent.getData()).build();
    }

}
