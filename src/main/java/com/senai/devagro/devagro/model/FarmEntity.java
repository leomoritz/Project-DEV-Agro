package com.senai.devagro.devagro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "farms")
public class FarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
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

    @Column(name = "initial_inventory_kg", nullable = false)
    private Double initialInventoryKg;

    @Column(name = "last_harvest")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastHarvest;

    public FarmEntity(String name, AddressEntity address, CompanyEntity company, GrainEntity grainProduced, Double initialInventoryKg, LocalDate lastHarvest) {
        this.name = name;
        this.address = address;
        this.company = company;
        this.grainProduced = grainProduced;
        this.initialInventoryKg = initialInventoryKg;
        this.lastHarvest = lastHarvest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FarmEntity entity = (FarmEntity) o;
        return name.equals(entity.name) && address.equals(entity.address) && company.equals(entity.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, company);
    }
}
