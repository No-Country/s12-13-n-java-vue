package com.latam.unamano.persistence.entities.client;

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

	
}
