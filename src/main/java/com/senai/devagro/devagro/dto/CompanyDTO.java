package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.model.EmployeeEntity;
import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.model.GrainEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class CompanyDTO {

    private Long id;
    private String name;
    private String cnpj;
    private AddressDTO address;
    private List<FarmDTO> farms;
    private List<EmployeeDTO> employees;
    private List<GrainDTO> grains;

    public CompanyDTO(Long id, String name, String cnpj, AddressDTO address) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.farms = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.grains = new ArrayList<>();
    }

    public CompanyDTO(CompanyEntity company) {
        id = company.getId();
        name = company.getName();
        cnpj = company.getCnpj();
        address = new AddressDTO(company.getAddress());
        farms = farmToDto(company.getFarms());
        employees = employeeToDto(company.getEmployees());
        grains = grainToDto(company.getGrains());
    }

    public List<FarmDTO> farmToDto(List<FarmEntity> items){
        return items.stream().map(FarmDTO::new).collect(Collectors.toList());
    }

    public List<EmployeeDTO> employeeToDto(List<EmployeeEntity> items){
        return items.stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    public List<GrainDTO> grainToDto(List<GrainEntity> items){
        return items.stream().map(GrainDTO::new).collect(Collectors.toList());
    }

}
