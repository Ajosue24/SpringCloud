package com.vytra.websockets.controller;

import com.vytra.websockets.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageTestController {

    @MessageMapping("/message")
    @SendTo("/topic/javainuse")
    public String send(String message) throws Exception {
        return message;
    }

    @Autowired
    private SimpMessagingTemplate template;

    public void sendToTopicGreetings(String greeting) {
        template.convertAndSend("/topic/greetings", greeting);
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return "Hello, " + message + "!";
    }


    @MessageMapping("/position/{type}")
    @SendTo("/topic/position.{type}")
    public String positionated(@DestinationVariable String type) throws Exception {
        Thread.sleep(1000); // simulated delay
        return "Hello, ";
    }



}
