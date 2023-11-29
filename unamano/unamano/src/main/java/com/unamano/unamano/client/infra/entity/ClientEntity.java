package com.unamano.unamano.client.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String lastname;

    public static final class ClientEntityBuilder {
        private Long id;
        private String username;
        private String lastname;

        public ClientEntityBuilder() {
        }


        public ClientEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ClientEntityBuilder username(String username) {
            this.username = username;
            return this;
        }

        public ClientEntityBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }


        public ClientEntity build() {
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setId(id);
            clientEntity.setUsername(username);
            clientEntity.setLastname(lastname);
            return clientEntity;
        }
    }
}
