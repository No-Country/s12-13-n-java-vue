package com.latam.unamano.dto.clientDto.response;

import com.latam.unamano.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientData {
    private Long id;
    private Long id_user;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Role rol;
    private Long id_address;
    private String city;
    private String country;
    private String profilePicture;
}
