package com.latam.unamano.persistence.entities.client;

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

}
























//******************************************************/
/*package com.latam.unamano.persistence.entities.client;

import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.persistence.entities.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class Client extends User {

	
}*/
