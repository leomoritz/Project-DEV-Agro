package com.senai.devagro.devagro.controller;

import com.senai.devagro.devagro.controller.converter.FarmConverter;
import com.senai.devagro.devagro.dto.FarmDTO;
import com.senai.devagro.devagro.service.AddressService;
import com.senai.devagro.devagro.service.CompanyService;
import com.senai.devagro.devagro.service.FarmService;
import com.senai.devagro.devagro.service.GrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farm")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private GrainService grainService;

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<FarmDTO> createFarm(@Valid @RequestBody FarmConverter farm){
        FarmDTO farmDto = farmService.createFarm(farm.converter(companyService, addressService, grainService));
        return ResponseEntity.ok().body(farmDto);
    }

    //READ
    @GetMapping
    public ResponseEntity<List<FarmDTO>> findAllFarms(){
        List<FarmDTO> farmsDtos = farmService.findAllFarms();
        return ResponseEntity.ok().body(farmsDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDTO> findFarmById(@PathVariable Long id){
        FarmDTO farmDto = farmService.findFarmById(id);
        return ResponseEntity.ok().body(farmDto);
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateFarmById(@PathVariable Long id, @Valid @RequestBody FarmConverter farm){
        Long idUpdated = farmService.updateFarmById(id, farm.converter(companyService, addressService, grainService));
        return ResponseEntity.ok().body(idUpdated);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteFarmById(@PathVariable Long id){
        Long idDeleted = farmService.deleteFarmById(id);
        return ResponseEntity.ok().body(idDeleted);
    }

}
