package com.latam.unamano.persistence.repositories.chatRoom;

import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
