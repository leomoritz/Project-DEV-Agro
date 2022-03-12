package com.senai.devagro.devagro.controller.converter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senai.devagro.devagro.model.AddressEntity;
import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.model.EmployeeEntity;
import com.senai.devagro.devagro.model.enums.Gender;
import com.senai.devagro.devagro.service.AddressService;
import com.senai.devagro.devagro.service.CompanyService;
import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
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
import java.time.format.DateTimeFormatter;

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

    @NotNull(message = "Address is required.")
    private AddressConverter address;

    @NotBlank(message = "Phone is required.")
    @Pattern(message = "Phone format is invalid. Enter the Phone in format (XX)XXXXXXXXX", regexp = "\\([0-9]{2}\\) ([0-9]{9})")
    private String phoneContact;

    @NotBlank(message = "Gender is required. Enter MALE or FEMALE")
    private String gender;

    @NotNull(message = "Birthday is required.")
    @Pattern(message = "Birthday format is invalid. Enter the birthday in format dd-mm-yyyy", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String birthday;

    @NotNull(message = "Hiredate is required.")
    @Pattern(message = "Hiredate format is invalid. Enter the hiredate in format dd-mm-yyyy", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String hiredate;

    @NotNull(message = "Company ID is required.")
    private Long employerId;

    public EmployeeEntity converter(){

        EmployeeEntity employee = new EmployeeEntity();
        CompanyEntity company = new CompanyEntity();
        company.setId(employerId);

        employee.setName(name);
        employee.setLastname(lastname);
        employee.setCpf(cpf);
        employee.setAddress(address.converter());
        employee.setPhoneContact(phoneContact);
        employee.setGender(Enum.valueOf(Gender.class, gender.toUpperCase())); // converte uma String para um Enum class especificado
        employee.setBirthday(UtilLocalDateConverter.stringToLocalDate(birthday));
        employee.setHiredate(UtilLocalDateConverter.stringToLocalDate(hiredate));
        employee.setEmployer(company);
        //employee.setEmployer(companyService.getCompanyEntityById(employerId));

        return employee;
    }
}
