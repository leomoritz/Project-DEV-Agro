package com.senai.devagro.devagro.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senai.devagro.devagro.controller.converter.FarmConverter;
import com.senai.devagro.devagro.controller.converter.FarmHarvestConverter;
import com.senai.devagro.devagro.controller.converter.FarmWithdrawalStockConverter;
import com.senai.devagro.devagro.dto.*;
import com.senai.devagro.devagro.service.FarmService;
import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farm")
public class FarmController {

    @Autowired
    private FarmService farmService;

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<FarmDTO> createFarm(@Valid @RequestBody FarmConverter farm) {
        FarmDTO farmDto = farmService.createFarm(farm.converter());
        return ResponseEntity.ok().body(farmDto);
    }

    //READ
    @GetMapping
    public ResponseEntity<List<FarmDTO>> findAllFarms() {
        List<FarmDTO> farmsDtos = farmService.findAllFarms();
        return ResponseEntity.ok().body(farmsDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDTO> findFarmById(@PathVariable Long id) {
        FarmDTO farmDto = farmService.findFarmById(id);
        return ResponseEntity.ok().body(farmDto);
    }

    @GetMapping("/farmsCompany/{companyId}")
    public ResponseEntity<List<FarmDTO>> findAllFarmsByCompanyId(@PathVariable Long companyId) {
        List<FarmDTO> farmsDtos = farmService.findAllFarmsByCompanyId(companyId);
        return ResponseEntity.ok().body(farmsDtos);
    }

    @GetMapping(value = "/countFarmsCompany/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardMessageDTO> countFarmsByCompanyId(@PathVariable Long companyId) {
        Long countFarms = farmService.countByCompany(companyId);
        StandardMessageDTO standardMessageDTO = new StandardMessageDTO("Total company farms: " + countFarms);
        return ResponseEntity.ok().body(standardMessageDTO);
    }

    @GetMapping("/expectedDateNextHarvest/{companyId}")
    public ResponseEntity<List<FarmsByCompanyDTO>> getFarmsCompanyWithExpectedDateNextHarvest(@PathVariable Long companyId) {
        List<FarmsByCompanyDTO> farmsByCompanyDtos = farmService.findAllFarmsCompanyWithExpectedDateNextHarvest(companyId);
        return ResponseEntity.ok().body(farmsByCompanyDtos);
    }

    //UPDATE
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardMessageDTO> updateFarmById(@PathVariable Long id, @Valid @RequestBody FarmConverter farm) {
        Long idUpdated = farmService.updateFarmById(id, farm.converter());
        StandardMessageDTO standardMessageDTO = new StandardMessageDTO("Farm with ID " + idUpdated + " successfully updated!");
        return ResponseEntity.ok().body(standardMessageDTO);
    }

    //DELETE
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardMessageDTO> deleteFarmById(@PathVariable Long id) {
        Long idDeleted = farmService.deleteFarmById(id);
        StandardMessageDTO standardMessageDTO = new StandardMessageDTO("Farm with ID " + idDeleted + " successfully deleted!");
        return ResponseEntity.ok().body(standardMessageDTO);
    }

    //OTHERS

    @PutMapping("/registerHarvest/{id}")
    public ResponseEntity<FarmHarvestDTO> registerHarvestByFarmId(@PathVariable Long id, @RequestBody FarmHarvestConverter farmHarvest) {
        FarmHarvestDTO farmHarvestDto =
                farmService.registerHarvestByFarmId(id, farmHarvest.getQuantityKgHarvest(),
                        UtilLocalDateConverter.stringToLocalDate(farmHarvest.getHarvestDate()));
        return ResponseEntity.ok().body(farmHarvestDto);
    }

    @PutMapping("/withdrawalGrain/{id}")
    public ResponseEntity<FarmWithdrawalGrainDTO> withdrawalGrainByFarmId(@PathVariable Long id, @RequestBody FarmWithdrawalStockConverter farmWithdrawal) {
        FarmWithdrawalGrainDTO farmWithdrawalGrainDto =
                farmService.withdrawalGrainByFarmId(id, farmWithdrawal.getQuantityKgWithdrawal(),
                        UtilLocalDateConverter.stringToLocalDate(farmWithdrawal.getWithdrawalDate()));
        return ResponseEntity.ok().body(farmWithdrawalGrainDto);
    }

}
