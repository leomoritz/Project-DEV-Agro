package com.senai.devagro.devagro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grains")
public class GrainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @Column(name = "average_harvest_time")
    private Integer averageHarvestTime;

    public GrainEntity(String name, CompanyEntity company, Integer averageHarvestTime) {
        this.name = name;
        this.company = company;
        this.averageHarvestTime = averageHarvestTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrainEntity grain = (GrainEntity) o;
        return name.equals(grain.name) && company.equals(grain.company) && averageHarvestTime.equals(grain.averageHarvestTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, averageHarvestTime);
    }
}
