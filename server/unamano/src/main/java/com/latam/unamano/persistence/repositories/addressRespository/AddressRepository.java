package com.latam.unamano.persistence.repositories.addressRespository;

import com.latam.unamano.persistence.entities.addressEntity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
