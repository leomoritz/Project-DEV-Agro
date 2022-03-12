package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.CompanyEntity;
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
    private CompanyEmployeeDTO company;
    private Integer averageHarvestTime;

    public GrainDTO(Long id, String name, CompanyEntity company, Integer averageHarvestTime) {
        this.id = id;
        this.name = name;
        this.company = new CompanyEmployeeDTO(company);
        this.averageHarvestTime = averageHarvestTime;
    }

    public GrainDTO(GrainEntity grain){
        id = grain.getId();
        name = grain.getName();
        company = new CompanyEmployeeDTO(grain.getCompany());
        averageHarvestTime = grain.getAverageHarvestTime();
    }

}
