package com.latam.unamano.persistence.entities.rating;

import com.latam.unamano.persistence.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


}
