package com.latam.unamano.dto.chatMessage;

import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;

public record ChatMessageDto(String message, String username ,Long chatRoomId) {

}
