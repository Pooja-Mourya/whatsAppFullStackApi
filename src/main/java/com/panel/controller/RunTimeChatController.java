package com.panel.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.panel.entity.Message;

@Controller
public class RunTimeChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public RunTimeChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/message")
    @SendTo("/group/public")
    public void receiveMessage(@Payload Message message) {
        // Process the received message (you can add your logic here)
        
        // Send the message to a specific destination (e.g., "/topic/chat/{roomId}")
        messagingTemplate.convertAndSend("/group/" + message.getChat().getId(), message);
    }
}
