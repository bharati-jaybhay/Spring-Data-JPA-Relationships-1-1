package com.demo.One_to_one.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.engine.jdbc.Size;
import org.springframework.data.annotation.Reference;

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

        @CreationTimestamp
        private LocalDateTime admissionDate;

        @UpdateTimestamp
        private LocalDateTime lastUpdated;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "address_address_id")
        @JsonManagedReference
        private Address address;
}
