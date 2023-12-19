package com.latam.unamano.dto.chatRoom;

import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.persistence.entities.workerEntity.Worker;

public record ChatRoomDto(Long id, String name, Client client, Worker worker) {
    public ChatRoomDto (ChatRoom chatRoom){
        this(chatRoom.getId(),chatRoom.getRoomName(),chatRoom.getClient(),chatRoom.getWorker());

    }

}
