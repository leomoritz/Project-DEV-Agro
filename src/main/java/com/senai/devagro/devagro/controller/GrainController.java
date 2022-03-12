package com.senai.devagro.devagro.controller;

import com.senai.devagro.devagro.controller.converter.GrainConverter;
import com.senai.devagro.devagro.dto.GrainDTO;
import com.senai.devagro.devagro.service.CompanyService;
import com.senai.devagro.devagro.service.GrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grain")
public class GrainController {

    @Autowired
    private GrainService grainService;

    @Autowired
    private CompanyService companyService;

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<GrainDTO> createGrain(@Valid @RequestBody GrainConverter grain){
        GrainDTO grainDto = grainService.createGrain(grain.converter(companyService));
        return ResponseEntity.ok().body(grainDto);
    }

    //READ
    @GetMapping
    public ResponseEntity<List<GrainDTO>> findAllGrains(){
        List<GrainDTO> grainsDtos = grainService.findAllGrains();
        return ResponseEntity.ok().body(grainsDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrainDTO> findGrainById(@PathVariable Long id){
        GrainDTO grainDto = grainService.findGrainById(id);
        return ResponseEntity.ok().body(grainDto);
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateGrainById(@PathVariable Long id, @Valid @RequestBody GrainConverter grain){
        Long idUpdated = grainService.updateGrainById(id, grain.converter(companyService));
        return ResponseEntity.ok().body(idUpdated);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteGrainById(@PathVariable Long id){
        Long idDeleted = grainService.deleteGrainById(id);
        return ResponseEntity.ok().body(idDeleted);
    }

}
