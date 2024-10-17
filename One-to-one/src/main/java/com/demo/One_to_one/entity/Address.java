package com.demo.One_to_one.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    private String city;

    private int sector;

    @Column(length = 8)
    private int pinCode;

    @OneToOne
    private Student student;
}
