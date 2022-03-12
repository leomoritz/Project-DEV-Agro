package com.senai.devagro.devagro.controller;

import com.senai.devagro.devagro.controller.converter.CompanyConverter;
import com.senai.devagro.devagro.dto.CompanyDTO;
import com.senai.devagro.devagro.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //Create
    @PostMapping("/create")
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyConverter company){
        CompanyDTO companyDto = companyService.createCompany(company.converter());
        return ResponseEntity.ok().body(companyDto);
    }

    //Read
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAllCompanies(){
        List<CompanyDTO> companiesDto = companyService.findAllCompanies();
        return ResponseEntity.ok().body(companiesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findCompanyById(@PathVariable Long id){
        CompanyDTO companyDto = companyService.findCompanyById(id);
        return ResponseEntity.ok().body(companyDto);
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateCompanyById(@PathVariable Long id, @Valid @RequestBody CompanyConverter company){
        Long idUpdated = companyService.updateCompanyById(id, company.converter());
        return ResponseEntity.ok().body(idUpdated);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteCompanyById(@PathVariable Long id){
        Long idDeleted = companyService.deleteCompanyById(id);
        return ResponseEntity.ok().body(idDeleted);
    }

}
