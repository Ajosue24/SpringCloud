package com.vytra.services.services;


import com.vytra.services.response.notification.MessageUsers;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.integration.support.MessageBuilder;

@Service
public class OrderSender {

    @Autowired
    private Source source;

    public boolean send(MessageUsers order) {
        return this.source.output().send(MessageBuilder.withPayload(order).build());
    }
}
