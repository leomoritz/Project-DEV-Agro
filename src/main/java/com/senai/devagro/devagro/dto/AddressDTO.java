package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
    private String postalcode;

    public AddressDTO(AddressEntity addressEntity) {
        id = addressEntity.getId();
        address = addressEntity.getAddress();
        number = addressEntity.getNumber();
        neighborhood = addressEntity.getNeighborhood();
        city = addressEntity.getCity();
        state = addressEntity.getState();
        postalcode = addressEntity.getPostalcode();
    }
}
