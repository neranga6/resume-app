package com.webapp.neo.controller;
import com.webapp.neo.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;


@Controller
    public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
        @MessageMapping("/chat.sendMessage")
        @SendTo("/topic/public")
        public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
            return chatMessage;
        }

        @MessageMapping("/chat.addUser")
        @SendTo("/topic/public")
        public ChatMessage addUser(@Payload ChatMessage chatMessage,
                                   SimpMessageHeaderAccessor headerAccessor) {
            // Add username in web socket session
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
            return chatMessage;
        }

//    @MessageMapping("/chat.sendPrivateMessage")
//    @SendTo("/queue/private")
//    public void sendPrivateMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        String recipient = "Neo";
//        String sender = (String) headerAccessor.getSessionAttributes().get("username");
//
//        if (sender != null && recipient != null) {
//            chatMessage.setType(ChatMessage.MessageType.CHAT);
//            chatMessage.setSender(sender);
//            messagingTemplate.convertAndSendToUser(recipient, "/queue/private", chatMessage);
//        }
//    }

    }

