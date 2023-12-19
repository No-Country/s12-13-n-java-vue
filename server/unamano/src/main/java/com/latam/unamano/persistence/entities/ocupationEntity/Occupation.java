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
	private String color;
/* #53BCFF Kinesiólogía
#FF4C4C Enfermería
#26C255 Transporte
#A51ABC Cuidador
#4462FF Tramites
#26C255 Transporte
#FFF848 Delivery
#FF9243 Extraccionista */ 
    @ManyToMany(mappedBy = "occupations")
    private List<Task> task;
}
