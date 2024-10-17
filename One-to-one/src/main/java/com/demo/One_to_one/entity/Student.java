package com.demo.One_to_one.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.jdbc.Size;

import java.time.LocalDateTime;

@Entity
@Data
public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int rollNo;

        private String firstName;

        private String lastName;

        @Column(unique = true)
        private String email;

        @Column(unique = true, length = 15)
        private String phone;

        private LocalDateTime addmissionDate;


        @OneToOne
        private Address address;
}
