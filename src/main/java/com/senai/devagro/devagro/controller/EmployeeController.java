package com.senai.devagro.devagro.controller;

import com.senai.devagro.devagro.controller.converter.EmployeeConverter;
import com.senai.devagro.devagro.dto.EmployeeDTO;
import com.senai.devagro.devagro.service.AddressService;
import com.senai.devagro.devagro.service.CompanyService;
import com.senai.devagro.devagro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeConverter employee){
        EmployeeDTO employeeDto = employeeService.createEmployee(employee.converter());
        return ResponseEntity.ok().body(employeeDto);
    }

    //READ
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAllEmployees(){
        List<EmployeeDTO> employeesDtos = employeeService.findAllEmployees();
        return ResponseEntity.ok().body(employeesDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable Long id){
        EmployeeDTO employeeDto = employeeService.findEmployeeById(id);
        return ResponseEntity.ok().body(employeeDto);
    }

    @GetMapping("/findAllEmployeesByCompany/{companyId}")
    public ResponseEntity<List<EmployeeDTO>> findAllEmployeesByCompanyId(@PathVariable Long companyId){
        List<EmployeeDTO> employeeDtos = employeeService.findAllByEmployerId(companyId);
        return ResponseEntity.ok().body(employeeDtos);
    }

    @GetMapping("/countAllEmployeesByCompany/{companyId}")
    public ResponseEntity<Long> countAllEmployeesByCompanyId(@PathVariable Long companyId){
        Long countEmployeesByCompany = employeeService.countByEmployerId(companyId);
        return ResponseEntity.ok().body(countEmployeesByCompany);
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateEmployeeById(@PathVariable Long id, @Valid @RequestBody EmployeeConverter employee){
        Long idUpdated = employeeService.updateEmployeeById(id, employee.converter());
        return ResponseEntity.ok().body(idUpdated);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteEmployeeById(@PathVariable Long id){
        Long idDeleted = employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().body(idDeleted);
    }

}
