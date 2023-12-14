package com.latam.unamano.persistence.repositories.chatMessage;

import com.latam.unamano.persistence.entities.chatMessage.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
