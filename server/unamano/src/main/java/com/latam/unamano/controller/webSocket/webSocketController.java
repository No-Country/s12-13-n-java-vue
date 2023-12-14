package com.latam.unamano.controller.webSocket;


import com.latam.unamano.dto.chatMessage.ChatMessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class webSocketController {
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessageDto chat(@DestinationVariable String roomId, ChatMessageDto message){
        System.out.println(message);
        System.out.println(roomId);
        return new ChatMessageDto(message.message(), message.user());
    }
}
