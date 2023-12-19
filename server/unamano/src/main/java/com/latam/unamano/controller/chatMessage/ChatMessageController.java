package com.latam.unamano.controller.chatMessage;

import com.latam.unamano.dto.chatMessage.ChatMessageResponseDto;
import com.latam.unamano.dto.chatRoom.ChatRoomResponseDto;
import com.latam.unamano.service.chatMessage.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/messages")
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ChatMessageResponseDto>> getAllMessagesByRoomId(@PathVariable Long id){
        return ResponseEntity.ok(chatMessageService.getAllMessagesByRoomId(id));

    }
}
