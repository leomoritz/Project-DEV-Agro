package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.model.GrainEntity;
import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class FarmDTO {

    private Long id;
    private String name;
    private AddressDTO address;
    private CompanyFarmDTO company;
    private GrainProducedFarmDTO grainProduced;
    private Double initialInventoryKg;
    private String lastHarvest;

    public FarmDTO(Long id, String name, AddressDTO address, CompanyEntity company, GrainEntity grainProduced, Double initialInventoryKg, LocalDate lastHarvest) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.company = new CompanyFarmDTO(company);
        this.grainProduced = new GrainProducedFarmDTO(grainProduced);
        this.initialInventoryKg = initialInventoryKg;
        this.lastHarvest = UtilLocalDateConverter.localDateToString(lastHarvest);
    }

    public FarmDTO(FarmEntity farm) {
        id = farm.getId();
        name = farm.getName();
        address = new AddressDTO(farm.getAddress());
        company = new CompanyFarmDTO(farm.getCompany());
        grainProduced = new GrainProducedFarmDTO(farm.getGrainProduced());
        initialInventoryKg = farm.getInitialInventoryKg();
        lastHarvest = UtilLocalDateConverter.localDateToString(farm.getLastHarvest());
    }

}
