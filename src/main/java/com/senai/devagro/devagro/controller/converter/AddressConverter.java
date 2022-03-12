package com.senai.devagro.devagro.controller.converter;

import com.senai.devagro.devagro.model.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressConverter {

    @NotBlank(message = "Address is required.")
    private String address;

    @NotNull(message = "Number is required.")
    private Integer number;

    @NotBlank(message = "Neighborhood is required.")
    private String neighborhood;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "State is required.")
    @Size(message = "State violates lenght constraint. Enter the state abbreviation in XX format", min = 2, max = 2)
    private String state;

    @NotBlank(message = "Postal-code is required.")
    @Size(message = "Postal-code violates lenght constraint (min = 8 / max = 9)", min = 8, max = 9)
    private String postalcode;

    public AddressEntity converter(){
        AddressEntity entity = new AddressEntity();
        entity.setAddress(address);
        entity.setState(state);
        entity.setPostalcode(postalcode);
        entity.setNumber(number);
        entity.setCity(city);
        entity.setNeighborhood(neighborhood);
        return entity;
    }

}
