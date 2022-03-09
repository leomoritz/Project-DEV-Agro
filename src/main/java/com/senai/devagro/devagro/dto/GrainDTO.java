package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.GrainEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GrainDTO {

    private Long id;
    private String name;
    private CompanyDTO company;
    private Integer averageHarvestTime;

    public GrainDTO(Long id, String name, CompanyDTO company, Integer averageHarvestTime) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.averageHarvestTime = averageHarvestTime;
    }

    public GrainDTO(GrainEntity grain){
        id = grain.getId();
        name = grain.getName();
        company = new CompanyDTO(grain.getCompany());
        averageHarvestTime = grain.getAverageHarvestTime();
    }

}
