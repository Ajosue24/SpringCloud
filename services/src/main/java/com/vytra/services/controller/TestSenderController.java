package com.vytra.services.controller;

import com.vytra.services.entity.security.User;
import com.vytra.services.response.notification.MessageUsers;
import com.vytra.services.services.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class TestSenderController {

    @Autowired
    NotificationSenderService notificationSenderService;

    @GetMapping
    public void testMessage(){
        notificationSenderService.send(new MessageUsers(new User(1l),"Hola","U.u"));
    }
}
