package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.FarmEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class FarmsByCompanyDTO {

    private Long id;
    private String name;
    private LocalDate expectedDateNextHarvest;

    public FarmsByCompanyDTO(Long id, String name, LocalDate expectedDateNextHarvest) {
        this.id = id;
        this.name = name;
        this.expectedDateNextHarvest = expectedDateNextHarvest;
    }

    public FarmsByCompanyDTO(FarmEntity farm, LocalDate expectedDateNextHarvest) {
        id = farm.getId();
        name = farm.getName();
        this.expectedDateNextHarvest = expectedDateNextHarvest;
    }

}
