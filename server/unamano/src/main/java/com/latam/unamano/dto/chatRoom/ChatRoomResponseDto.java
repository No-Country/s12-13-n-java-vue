package com.latam.unamano.dto.chatRoom;

import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;

public record ChatRoomResponseDto(Long id, String name, String clientName, String workerName) {
    public ChatRoomResponseDto (ChatRoom chatRoom){
        this(chatRoom.getId(),chatRoom.getRoomName(),
                chatRoom.getClient().getUser().getFirstName()
                        +" "+ chatRoom.getClient().getUser().getLastName(),
                chatRoom.getWorker().getUser().getFirstName()
                        +" "+ chatRoom.getWorker().getUser().getLastName());

    }

}