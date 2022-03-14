package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class FarmHarvestDTO {

    private String farmName;
    private String grainHarvest;
    private Double quantityKgHarvested;
    private String harvestDate;
    private Double currentKgStock;

    public FarmHarvestDTO(String farmName, String grainHarvest, Double quantityKgHarvested, LocalDate harvestDate, Double currentKgStock) {
        this.farmName = farmName;
        this.grainHarvest = grainHarvest;
        this.quantityKgHarvested = quantityKgHarvested;
        this.harvestDate = UtilLocalDateConverter.localDateToString(harvestDate);
        this.currentKgStock = currentKgStock;
    }

    public FarmHarvestDTO(FarmEntity farm, Double quantityKgHarvested, LocalDate harvestDate) {
        farmName = farm.getName();
        grainHarvest = farm.getGrainProduced().getName();
        this.quantityKgHarvested = quantityKgHarvested;
        this.harvestDate = UtilLocalDateConverter.localDateToString(harvestDate);
        currentKgStock = farm.getInitialInventoryKg();
    }


}
