package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyEmployeeDTO {

    private String name;

    public CompanyEmployeeDTO(CompanyEntity company) {
        this.name = company.getName();
    }
}
