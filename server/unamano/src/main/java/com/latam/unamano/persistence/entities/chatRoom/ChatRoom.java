package com.latam.unamano.persistence.entities.chatRoom;


import com.latam.unamano.persistence.entities.chatMessage.ChatMessage;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "chat_rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id")
    private Worker worker;
    @OneToMany(mappedBy = "chatRoom",cascade = CascadeType.ALL)
    List<ChatMessage> chatMessages = new ArrayList<>();

    public ChatRoom (Long id){this.id = id;}




}

