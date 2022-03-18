package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.FarmEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class GrainStockByCompanyDTO implements Comparable<GrainStockByCompanyDTO> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrainStockByCompanyDTO that = (GrainStockByCompanyDTO) o;
        return grainName.equals(that.grainName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grainName);
    }

    @Override
    public int compareTo(GrainStockByCompanyDTO o) {
        return grainName.compareTo(o.grainName);
    }
}
