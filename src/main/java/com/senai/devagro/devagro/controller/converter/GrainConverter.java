package com.senai.devagro.devagro.controller.converter;

import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.model.GrainEntity;
import com.senai.devagro.devagro.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GrainConverter {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Company ID is required.")
    private Long companyId;

    @NotNull(message = "Average harvest time is required.")
    private Integer averageHarvestTime;

    public GrainEntity converter() {
        GrainEntity entity = new GrainEntity();

        entity.setName(name);
        entity.setCompany(getFarmCompany());
        entity.setAverageHarvestTime(averageHarvestTime);

        return entity;
    }

    private CompanyEntity getFarmCompany() {
        CompanyEntity company = new CompanyEntity();
        company.setId(companyId);
        return company;
    }

}
