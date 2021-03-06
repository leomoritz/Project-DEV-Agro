package com.senai.devagro.devagro.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cnpj;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private final List<FarmEntity> farms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employer")
    private final List<EmployeeEntity> employees;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private final List<GrainEntity> grains;

    public CompanyEntity() {
        this.farms = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.grains = new ArrayList<>();
    }

    public CompanyEntity(String name, String cnpj, AddressEntity address) {
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.farms = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.grains = new ArrayList<>();
    }
}
