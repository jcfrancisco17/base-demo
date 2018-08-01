package com.jc.labs.basedemo.item.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageBrokerChannels {

    @Output
    MessageChannel itemAdded();

    @Output
    MessageChannel itemDeleted();
}
