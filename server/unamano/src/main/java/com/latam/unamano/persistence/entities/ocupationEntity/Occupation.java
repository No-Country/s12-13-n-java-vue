package com.latam.unamano.persistence.entities.ocupationEntity;

import com.latam.unamano.persistence.entities.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "occupations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "occupation_name")
    private String occupationName;
    private String description;
    @ManyToMany(mappedBy = "occupations")
    private List<Task> task;
}
