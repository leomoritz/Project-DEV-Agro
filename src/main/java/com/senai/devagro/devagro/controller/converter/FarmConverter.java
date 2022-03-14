package com.senai.devagro.devagro.controller.converter;

import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FarmConverter {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Address ID is required.")
    @Valid
    private AddressConverter address;

    @NotNull(message = "Company ID is required.")
    private Long companyId;

    @NotNull(message = "Grain is required.")
    @Valid
    private GrainConverter grain;

    @NotNull(message = "Initial Inventory Kg is required.")
    private Double initialInventoryKg;

    @NotNull(message = "Last Harvest is required.")
    @Pattern(message = "Last Harvest format is invalid. Enter the last harvest in format dd-mm-yyyy", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String lastHarvest;

    public FarmEntity converter(){
        FarmEntity entity = new FarmEntity();

        entity.setName(name);
        entity.setAddress(address.converter());
        entity.setCompany(getFarmCompany());
        entity.setGrainProduced(grain.converter());
        entity.setInitialInventoryKg(initialInventoryKg);
        entity.setLastHarvest(UtilLocalDateConverter.stringToLocalDate(lastHarvest));

        return entity;
    }

    private CompanyEntity getFarmCompany() {
        CompanyEntity company = new CompanyEntity();
        company.setId(companyId);
        return company;
    }
}
