package com.latam.unamano.persistence.repositories.chatRoom;

import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {


    List<ChatRoom> getAllChatsRoomByClientId(Long ClientId);
    List<ChatRoom> getAllChatsRoomByWorkerId(Long WorkerId);
}
