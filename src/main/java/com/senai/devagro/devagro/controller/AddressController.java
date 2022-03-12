package com.senai.devagro.devagro.controller;

import com.senai.devagro.devagro.controller.converter.AddressConverter;
import com.senai.devagro.devagro.dto.AddressDTO;
import com.senai.devagro.devagro.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    //Create
    @PostMapping("/create")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressConverter address) {
        AddressDTO adressDto = addressService.createAddress(address.converter());
        return ResponseEntity.ok().body(adressDto);
    }

    /**
     * Método recebe um lote de endereços e persiste cada endereço no banco.
     *
     * @param address lista de endereços
     * @return uma lista de endereços convertidos em dto.
     */
    @PostMapping("/create/batch")
    public ResponseEntity<List<AddressDTO>> createAddressBatch(@Valid @RequestBody List<AddressConverter> address) {
        List<AddressDTO> addressesDtos = address.stream().map(addressConverter ->
                (addressService.createAddress(addressConverter.converter()))).collect(Collectors.toList());
        return ResponseEntity.ok().body(addressesDtos);
    }

    //Read
    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAllAddresses() {
        List<AddressDTO> addressesDto = addressService.findAllAddresses();
        return ResponseEntity.ok().body(addressesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findAddressById(@PathVariable Long id) {
        AddressDTO addressDTO = addressService.findAddressById(id);
        return ResponseEntity.ok().body(addressDTO);
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateAddressById(@PathVariable Long id, @Valid @RequestBody AddressConverter address) {
        Long idUpdated = addressService.updateAddressById(id, address.converter());
        return ResponseEntity.ok().body(idUpdated);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteAddressById(@PathVariable Long id) {
        Long idDeleted = addressService.deleteAddressById(id);
        return ResponseEntity.ok().body(idDeleted);
    }

}
