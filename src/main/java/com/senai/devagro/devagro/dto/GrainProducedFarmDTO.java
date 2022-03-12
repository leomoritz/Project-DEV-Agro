package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.GrainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GrainProducedFarmDTO {

    private String name;

    public GrainProducedFarmDTO(GrainEntity entity) {
        this.name = entity.getName();
    }
}
