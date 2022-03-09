package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.FarmEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class FarmDTO {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Long id;
    private String name;
    private AddressDTO address;
    private CompanyDTO company;
    private GrainDTO grainProduced;
    private Double initialInventoryKg;
    private String lastHarvest;

    public FarmDTO(Long id, String name, AddressDTO address, CompanyDTO company, GrainDTO grainProduced, Double initialInventoryKg, LocalDate lastHarvest) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.company = company;
        this.grainProduced = grainProduced;
        this.initialInventoryKg = initialInventoryKg;
        this.lastHarvest = dateTimeFormatter.format(lastHarvest);
    }

    public FarmDTO(FarmEntity farm){
        id = farm.getId();
        name = farm.getName();
        address = new AddressDTO(farm.getAddress());
        company = new CompanyDTO(farm.getCompany());
        grainProduced = new GrainDTO(farm.getGrainProduced());
        initialInventoryKg = farm.getInitialInventoryKg();
        lastHarvest = dateTimeFormatter.format(farm.getLastHarvest());
    }

}
