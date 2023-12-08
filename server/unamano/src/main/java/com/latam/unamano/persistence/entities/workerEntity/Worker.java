/*package com.latam.unamano.persistence.entities.workerEntity;

import com.latam.unamano.persistence.entities.addressEntity.Address;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import com.latam.unamano.persistence.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker extends User {

    @ManyToMany
    @JoinTable(name = "worker_occupations",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "occupation_id"))
    private List<Occupation> occupations;

    public static final class WorkerBuilder{
        private String firstname;
        private String lastname;
        private String email;
        private Address address;
        private String phone;
        private List<Occupation> occupations;

        public WorkerBuilder(){}

        public WorkerBuilder firstname(String firstname){
            this.firstname = firstname;
            return this;
        }


        public WorkerBuilder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public WorkerBuilder email(String email){
            this.email = email;
            return this;
        }

        public WorkerBuilder address(Address address){
            this.address = address;
            return this;
        }

        public WorkerBuilder occupation(List<Occupation> occupationList){
            this.occupations = occupationList;
            return this;
        }

        public WorkerBuilder phone(String phone){
            this.phone = phone;
            return this;
        }

        public Worker build(){
            Worker worker = new Worker();
            worker.setFirstName(firstname);
            worker.setLastName(lastname);
            worker.setEmail(email);
            worker.setAddresses(address);
            worker.setOccupations(occupations);
            worker.setPhoneNumber(phone);
            return worker;
        }
    }
}

 */
