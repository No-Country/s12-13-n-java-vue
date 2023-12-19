package com.latam.unamano.persistence.repositories.chatMessage;

import com.latam.unamano.persistence.entities.chatMessage.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> getAllChatMessagesByChatRoomId(Long chatRoomId);
}
