package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.FarmEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GrainStockByCompanyDTO {

    private String grainName;
    private Double quantityStock;

    public GrainStockByCompanyDTO(String grainName, Double quantityStock) {
        this.grainName = grainName;
        this.quantityStock = quantityStock;
    }

    public GrainStockByCompanyDTO(FarmEntity farm){
        grainName = farm.getGrainProduced().getName();
        quantityStock = farm.getInitialInventoryKg();
    }
}
