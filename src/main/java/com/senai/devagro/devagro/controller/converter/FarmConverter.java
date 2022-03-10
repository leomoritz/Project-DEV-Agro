package com.senai.devagro.devagro.controller.converter;

import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.service.AddressService;
import com.senai.devagro.devagro.service.CompanyService;
import com.senai.devagro.devagro.service.GrainService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FarmConverter {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Address ID is required.")
    private Long addressId;

    @NotNull(message = "Company ID is required.")
    private Long companyId;

    @NotNull(message = "Grain ID is required.")
    private Long grainId;

    @NotNull(message = "Initial Inventory Kg is required.")
    private Double initialInventoryKg;

    @NotNull(message = "Last Harvest is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastHarvest;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private GrainService grainService;

    public FarmEntity converter(){
        FarmEntity entity = new FarmEntity();

        entity.setName(name);
        entity.setAddress(addressService.getAddressEntityById(addressId));
        entity.setCompany(companyService.getCompanyEntityById(companyId));
        entity.setGrainProduced(grainService.getGrainEntityById(grainId));
        entity.setInitialInventoryKg(initialInventoryKg);
        entity.setLastHarvest(lastHarvest);

        return entity;
    }

}
