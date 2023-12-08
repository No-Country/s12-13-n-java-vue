package com.latam.unamano.persistence.entities.task;


import com.latam.unamano.persistence.entities.addressEntity.Address;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import com.latam.unamano.persistence.entities.user.User;
import com.latam.unamano.utils.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="task_title")
    private String taskTitle;
    @Column(name="task_description")
    private String description;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    private BigDecimal price;
<<<<<<< HEAD
=======
    @Column(name = "currency_type")
    private String currencyType;
>>>>>>> dev-backend
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name="task_occupation",
            joinColumns = @JoinColumn(name="task_id"),
            inverseJoinColumns = @JoinColumn(name="occupation_id")
    )
    private List<Occupation> occupations;
    //@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private LocalDateTime taskDate;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private User client;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    //TODO Relaciones entre clases
    /*
    private User client;
    private User worker;
    private List<Occupation> occupations;
    private Address address;
    private Rating workerRating;
    private Rating clientRating;
    private List<Postulation> postulations;
    private List<WorkingDay> workingDay;
    */



}
