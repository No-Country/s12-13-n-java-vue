package com.latam.unamano.dto.addressDto;


import com.latam.unamano.persistence.entities.addressEntity.Address;

public record AddressDto(String street, String number, String state, String city, String country) {

    public AddressDto(Address address){
        this(address.getStreet(),address.getNumber(),address.getState(),
                address.getCity(),address.getCountry());
    }
}
