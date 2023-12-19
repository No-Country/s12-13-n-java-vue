package com.latam.unamano.dto.chatMessage;

import com.latam.unamano.persistence.entities.chatMessage.ChatMessage;

import java.time.LocalDateTime;

public record ChatMessageResponseDto(String message, String username, LocalDateTime dateTime) {

    public ChatMessageResponseDto (ChatMessage message){
        this(message.getMessage(), message.getUsername(), message.getDateMessageCreated());
    }
}
