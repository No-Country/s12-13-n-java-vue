package com.latam.unamano.service.chatMessage;

import com.latam.unamano.dto.chatMessage.ChatMessageDto;
import com.latam.unamano.dto.chatMessage.ChatMessageResponseDto;
import com.latam.unamano.persistence.entities.chatMessage.ChatMessage;
import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;
import com.latam.unamano.persistence.repositories.chatMessage.ChatMessageRepository;
import com.latam.unamano.service.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public void saveMessage (ChatMessageDto chatMessageDto){
        ChatMessage message = new ChatMessage();
        message.setMessage(chatMessageDto.message());
        message.setUsername(chatMessageDto.username());
        message.setDateMessageCreated(LocalDateTime.now());
        ChatRoom chatRoom = chatRoomService.getChatRoomById(chatMessageDto.chatRoomId());
        message.setChatRoom(chatRoom);
        chatMessageRepository.save(message);

    }

    public List<ChatMessageResponseDto> getAllMessagesByRoomId(Long roomId){
        List<ChatMessage> chatMessages = chatMessageRepository.getAllChatMessagesByChatRoomId(roomId);
        return chatMessages.stream().map(ChatMessageResponseDto :: new).toList();

    }
}
