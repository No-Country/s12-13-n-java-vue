package com.latam.unamano.persistence.entities.workerEntity;

import com.latam.unamano.persistence.entities.chatMessage.ChatMessage;
import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import com.latam.unamano.persistence.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "workers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "worker_occupations",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "occupation_id"))
    private List<Occupation> occupations;
    @OneToMany(mappedBy = "worker")
    private List<ChatRoom> chatMessagesAsWorker;


}