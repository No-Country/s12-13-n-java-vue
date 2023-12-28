package com.latam.unamano.persistence.entities.client;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.latam.unamano.persistence.entities.chatMessage.ChatMessage;
import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;
import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.persistence.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> requestedTasks;
    @OneToMany(mappedBy = "client")
    private List<ChatRoom> chatMessagesAsClient;

    public Client(Long id) {
        this.id = id;
    }
}


