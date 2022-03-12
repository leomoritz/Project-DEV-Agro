package com.senai.devagro.devagro.controller.converter;

import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.service.AddressService;
import com.senai.devagro.devagro.service.CompanyService;
import com.senai.devagro.devagro.service.GrainService;
import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FarmConverter {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
    @Pattern(message = "Last Harvest format is invalid. Enter the last harvest in format dd-mm-yyyy", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String lastHarvest;

    public FarmEntity converter(CompanyService companyService, AddressService addressService, GrainService grainService){
        FarmEntity entity = new FarmEntity();

        entity.setName(name);
        entity.setAddress(addressService.getAddressEntityById(addressId));
        entity.setCompany(companyService.getCompanyEntityById(companyId));
        entity.setGrainProduced(grainService.getGrainEntityById(grainId));
        entity.setInitialInventoryKg(initialInventoryKg);
        entity.setLastHarvest(UtilLocalDateConverter.stringToLocalDate(lastHarvest));

        return entity;
    }

}
