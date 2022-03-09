package com.senai.devagro.devagro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Integer number;

    private String neighborhood;

    private String city;

    private String state;

    private String postalcode;

    public AddressEntity(String address, Integer number, String neighborhood, String city, String state, String postalcode) {
        this.address = address;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.postalcode = postalcode;
    }
}
