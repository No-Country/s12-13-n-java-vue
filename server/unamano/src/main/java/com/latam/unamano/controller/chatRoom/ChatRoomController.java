package com.latam.unamano.controller.chatRoom;

import com.latam.unamano.dto.chatRoom.ChatRoomDto;
import com.latam.unamano.dto.chatRoom.ChatRoomResponseDto;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.service.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chats")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    @PostMapping
    public ResponseEntity<String> createChatRoom(@RequestBody ChatRoomDto chatRoomDto){
        chatRoomService.createChatRoom(chatRoomDto.name(),chatRoomDto.client(),chatRoomDto.worker());
        return ResponseEntity.ok("Creado");

    }
    @GetMapping
    public ResponseEntity<List<ChatRoomResponseDto>> getAllChatsByUserId(){
        return ResponseEntity.ok(chatRoomService.getAllChatsRoomByUserid());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoomResponseDto> getChatRoomById(@PathVariable Long id){
        return ResponseEntity.ok(new ChatRoomResponseDto(chatRoomService.getChatRoomById(id)));

    }
}
