package com.jc.labs.basedemo.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
@EnableBinding(NotificationServiceApplication.MessageBrokerChannels.class)
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @StreamListener(MessageBrokerChannels.ITEM_ADDED)
    public void handleAdded(String message) {
        System.out.println("Received payload to add: " + message);
    }

    @StreamListener(MessageBrokerChannels.ITEM_DELETED)
    public void handleDeleted(String message) {
        System.out.println("Received payload to delete: " + message);
    }

    /**
     * An example of how to do custom channels
     */
    interface MessageBrokerChannels {
        String ITEM_ADDED = "itemAdded";

        String ITEM_DELETED = "itemDeleted";

        @Input(value = ITEM_ADDED)
        SubscribableChannel itemAdded();

        @Input(value = ITEM_DELETED)
        SubscribableChannel itemDeleted();

    }
}
