package com.unamano.unamano.client.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private Long id;
    private String username;
    private String lastname;


    public static final class ClientBuilder {
        private Long id;
        private String username;
        private String lastname;

        public ClientBuilder() {
        }


        public ClientBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ClientBuilder username(String username) {
            this.username = username;
            return this;
        }

        public ClientBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }


        public Client build() {
            Client client = new Client();
            client.setId(id);
            client.setUsername(username);
            client.setLastname(lastname);
            return client;
        }
    }

}
