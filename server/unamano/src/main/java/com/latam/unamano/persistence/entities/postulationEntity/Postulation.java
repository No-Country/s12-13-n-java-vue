package com.latam.unamano.persistence.entities.postulationEntity;

import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.utils.PostulationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "postulations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
//TODO Acá está el problema
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private LocalDateTime date = LocalDateTime.now();

    private PostulationStatus status;
}
