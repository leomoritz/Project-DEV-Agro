package com.senai.devagro.devagro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "grain_id")
    private CompanyEntity company;

    @Column(name = "average_harvest_time")
    private Integer averageHarvestTime;

    public GrainEntity(String name, CompanyEntity company, Integer averageHarvestTime) {
        this.name = name;
        this.company = company;
        this.averageHarvestTime = averageHarvestTime;
    }
}
