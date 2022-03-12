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
public class CompanyFarmDTO {

    private String name;
    private String cnpj;

    public CompanyFarmDTO(CompanyEntity company) {
        this.name = company.getName();
        this.cnpj = company.getCnpj();
    }
}
