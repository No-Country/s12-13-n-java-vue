package com.latam.unamano.controller.webSocket;


import com.latam.unamano.dto.chatMessage.ChatMessageDto;
import com.latam.unamano.service.chatMessage.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class webSocketController {
    private final ChatMessageService chatMessageService;
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessageDto chat(@DestinationVariable String roomId, ChatMessageDto message){
        System.out.println(message);
        System.out.println(roomId);
        chatMessageService.saveMessage(message);
        return new ChatMessageDto(message.message(), message.username(),message.chatRoomId());
    }
}
