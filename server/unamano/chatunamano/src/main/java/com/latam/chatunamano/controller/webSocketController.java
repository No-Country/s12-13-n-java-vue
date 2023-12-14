package com.latam.chatunamano.controller;

import com.latam.chatunamano.dto.ChatMessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class webSocketController {
    @MessageMapping("/chat/{roomid}")
    @SendTo("/topic/{roomid}")
    public ChatMessageDto chat(@DestinationVariable String roomId, ChatMessageDto message){
        return new ChatMessageDto(message.message(), message.user());
    }
}
