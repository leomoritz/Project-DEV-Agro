package com.senai.devagro.devagro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "farms")
public class FarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @OneToOne
    @JoinColumn(name = "grain_produced_id")
    private GrainEntity grainProduced;

    @Column(name = "initial_inventory_kg")
    private Double initialInventoryKg;

    @Column(name = "last_harvest")
    private LocalDate lastHarvest;

    public FarmEntity(String name, AddressEntity address, CompanyEntity company, GrainEntity grainProduced, Double initialInventoryKg, LocalDate lastHarvest) {
        this.name = name;
        this.address = address;
        this.company = company;
        this.grainProduced = grainProduced;
        this.initialInventoryKg = initialInventoryKg;
        this.lastHarvest = lastHarvest;
    }

}
