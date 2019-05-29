package com.vytra.websockets.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vytra.websockets.model.AdministrationHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UsuariosController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/administration/{userId}")
    @SendTo("/subscription/administration/{userId}")
    public String administration(@DestinationVariable String userId, String history) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String message = mapper.writeValueAsString(new AdministrationHistory(userId, history));

        template.convertAndSend("/subscription/general/administration", message);

        return message;
    }
}
