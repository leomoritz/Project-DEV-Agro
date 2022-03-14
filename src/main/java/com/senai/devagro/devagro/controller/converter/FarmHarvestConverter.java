package com.senai.devagro.devagro.controller.converter;

import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class FarmHarvestConverter {

    @NotNull(message = "Quantity (kg) is required.")
    private Double quantityKgHarvest;

    @NotNull(message = "Harvest Date is required.")
    @Pattern(message = "Harvest Date format is invalid. Enter the harvest date in format dd-mm-yyyy", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String harvestDate;

    public FarmHarvestConverter(Double quantityKgHarvest, String harvestDate) {
        this.quantityKgHarvest = quantityKgHarvest;
        this.harvestDate = harvestDate;
    }
}
