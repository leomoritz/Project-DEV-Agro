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
public class FarmWithdrawalGrainDTO {

    private String farmName;
    private String grainWithdrawn;
    private Double quantityKgWithdrawn;
    private String withdrawalDate;
    private Double currentKgStock;

    public FarmWithdrawalGrainDTO(String farmName, String grainWithdrawn, Double quantityKgWithdrawn, LocalDate withdrawalDate, Double currentKgStock) {
        this.farmName = farmName;
        this.grainWithdrawn = grainWithdrawn;
        this.quantityKgWithdrawn = quantityKgWithdrawn;
        this.withdrawalDate = UtilLocalDateConverter.localDateToString(withdrawalDate);
        this.currentKgStock = currentKgStock;
    }

    public FarmWithdrawalGrainDTO(FarmEntity farm, Double quantityKgWithdrawn, LocalDate withdrawalDate) {
        farmName = farm.getName();
        grainWithdrawn = farm.getGrainProduced().getName();
        this.quantityKgWithdrawn = quantityKgWithdrawn;
        this.withdrawalDate = UtilLocalDateConverter.localDateToString(withdrawalDate);
        currentKgStock = farm.getInitialInventoryKg();
    }

}
