package com.chat.app.app;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController{
@MessageMapping("/app.sendMessage")
@SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatmessage){
return chatmessage;
    }
    @MessageMapping("/app.addUser")
@SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,SimpMessageHeaderAccessor headerAccessor){
   headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
   return chatMessage;
    }
}