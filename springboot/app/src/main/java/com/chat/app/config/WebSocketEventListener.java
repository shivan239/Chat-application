package com.chat.app.config;

import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.chat.app.app.ChatMessage;
import com.chat.app.app.ChatMessage.ChatMessageBuilder;
import com.chat.app.app.MessageType;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTemplate;
    @EventListener
    public void handleWebSocketDisconnectListener
        (SessionDisconnectEvent  event){
    {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String)headerAccessor.getSessionAttributes().get("username");
        if(username != null ){
            log.info("user disconnected: {}", username);
            var chatMessage = ChatMessage.builder()
            .type(MessageType.LEAVE)
            .sender(username)
            .build();
            messageTemplate.convertAndSend("/topic/public", chatMessage);

        }
    }
}
    
}
