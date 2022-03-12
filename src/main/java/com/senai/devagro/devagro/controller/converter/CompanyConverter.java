package com.senai.devagro.devagro.controller.converter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senai.devagro.devagro.model.AddressEntity;
import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.repository.AddressRepository;
import com.senai.devagro.devagro.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyConverter {

    @NotBlank(message = "Name is required.")
    private String name;

    @CNPJ
    @NotBlank(message = "CNPJ is required.")
    @Pattern(message = "CNPJ format is invalid. Enter the CNPJ in format XX.XXX.XXX/XXXX-XX", regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
    private String cnpj;

    @NotNull(message = "Address is required.")
    @Valid
    private AddressConverter address;

    public CompanyEntity converter() {
        CompanyEntity entity = new CompanyEntity();
        entity.setName(name);
        entity.setCnpj(cnpj);
        entity.setAddress(address.converter());
        return entity;
    }

}
