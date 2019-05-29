package com.vytra.websockets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //config.enableSimpleBroker("/subscription");//topic
        config.setApplicationDestinationPrefixes("/app","/subscription/**"); //app
       config.enableStompBrokerRelay("/subscription/**","/queue","/topic")
               .setUserDestinationBroadcast("/topic/unresolved.user.dest")
               .setUserRegistryBroadcast("/topic/registry.broadcast")
               .setRelayHost("localhost")
               .setRelayPort(61613)
               .setClientLogin("guest")
               .setClientPasscode("guest");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/web-socket").withSockJS(); //gs-guide-websocket
    }

}