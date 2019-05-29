package com.vytra.services.services;

import com.vytra.services.response.notification.MessageUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderService {


    @Autowired
    private Source source;

    public boolean send(MessageUsers messageUsers) {
        return this.source.output().send(MessageBuilder.withPayload(messageUsers).build());
    }
}
