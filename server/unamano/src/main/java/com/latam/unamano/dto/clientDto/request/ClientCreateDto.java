package com.latam.unamano.dto.clientDto.request;

import com.latam.unamano.persistence.entities.user.User;

public record ClientCreateDto(Long id, User user) {
}
