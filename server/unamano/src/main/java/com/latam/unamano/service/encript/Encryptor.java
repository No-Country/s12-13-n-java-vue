package com.latam.unamano.service.encript;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encryptor {

    private final BCryptPasswordEncoder encoder;

    /** Constructor to create a new instance of Encryptor
     */
    public Encryptor() {
        this.encoder = new BCryptPasswordEncoder();
    }

    /** Method to encrypt a given password
     */
    public String encrypt(String password) {
        return encoder.encode(password);
    }
}
