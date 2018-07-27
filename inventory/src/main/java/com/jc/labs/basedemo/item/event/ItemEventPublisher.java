package com.jc.labs.basedemo.item.event;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ItemEventPublisher {

    private ItemDomainEventRepository itemDomainEventRepository;

    private MessageBrokerChannels messageBrokerChannels;

    ItemEventPublisher(ItemDomainEventRepository itemDomainEventRepository,
                       MessageBrokerChannels messageBrokerChannels) {
        this.itemDomainEventRepository = itemDomainEventRepository;
        this.messageBrokerChannels = messageBrokerChannels;
    }

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void publishEvents() {
        System.out.println("Publishing events to broker");
        var events = itemDomainEventRepository.findByState(EventState.NEW);
        events
                .forEach(domainEvent -> {
                    boolean sent = messageBrokerChannels.itemAdded().send(buildMessage(domainEvent));
                   if (sent) {
                       domainEvent.markPublished();
                   }
                });
    }

    private Message<String> buildMessage(ItemDomainEvent domainEvent) {
        return MessageBuilder.withPayload(domainEvent.getData()).build();
    }

}
