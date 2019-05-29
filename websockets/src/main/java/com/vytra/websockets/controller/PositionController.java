package com.vytra.websockets.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vytra.websockets.model.Position;
import com.vytra.websockets.model.Positionated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PositionController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/position.{type}.{id}")
    @SendTo("/topic/position.{type}.{id}")
    public String positionated(@DestinationVariable String type, @DestinationVariable String id, Position position) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String message = mapper.writeValueAsString(new Positionated(type, id, position.getLatitude(), position.getLongitude()));

        template.convertAndSend("/topic/general-position." + "driver", message);

        return message;
    }
}
