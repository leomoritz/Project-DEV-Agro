package com.senai.devagro.devagro.controller.converter;

import com.senai.devagro.devagro.model.EmployeeEntity;
import com.senai.devagro.devagro.model.enums.Gender;
import com.senai.devagro.devagro.service.AddressService;
import com.senai.devagro.devagro.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeConverter {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Lastname is required.")
    private String lastname;

    @CPF
    @NotBlank(message = "CPF is required.")
    @Pattern(message = "CPF format is invalid. Enter the CPF in format XXX.XXX.XXX-XX", regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}")
    private String cpf;

    @NotNull(message = "Address ID is required.")
    private Long addressId;

    @NotBlank(message = "Phone is required.")
    @Pattern(message = "Phone format is invalid. Enter the Phone in format (XX)XXXXXXXXX", regexp = "\\([0-9]{2}\\) ([0-9]{9})")
    private String phoneContact;

    @NotBlank(message = "Gender is required.")
    private String gender;

    @NotNull(message = "Birthday is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // único formato aceito pelo LocalDate. Se o usuário digitar em outra ordem, formata para yyyy-MM-dd
    private LocalDate birthday;

    @NotNull(message = "Hiredate is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // único formato aceito pelo LocalDate. Se o usuário digitar em outra ordem, formata para yyyy-MM-dd
    private LocalDate hiredate;

    @NotNull(message = "Company ID is required.")
    private Long employerId;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressService addressService;

    public EmployeeEntity converter(){
        EmployeeEntity employee = new EmployeeEntity();

        employee.setName(name);
        employee.setLastname(lastname);
        employee.setCpf(cpf);
        employee.setAddress(addressService.getAddressEntityById(addressId));
        employee.setPhoneContact(phoneContact);
        employee.setGender(Enum.valueOf(Gender.class, gender)); // converte uma String para um Enum class especificado
        employee.setBirthday(birthday);
        employee.setHiredate(hiredate);
        employee.setEmployer(companyService.getCompanyEntityById(employerId));

        return employee;
    }

}
