package com.jc.labs.basedemo;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageBrokerChannels {

    @Output
    MessageChannel inventoryAdded();
}
