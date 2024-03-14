package com.groupchatapplication.chatserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.groupchatapplication.chatserver.model.Message;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message) {
        return message;
    }

    @SuppressWarnings("null")
    @MessageMapping("/private-message")
    public Message receivePrivatMessage(@Payload Message message) {
    simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
    return message;
}


}
