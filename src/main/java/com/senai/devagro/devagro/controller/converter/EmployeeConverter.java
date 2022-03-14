package com.senai.devagro.devagro.controller.converter;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.model.EmployeeEntity;
import com.senai.devagro.devagro.model.enums.Gender;
import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Optional;

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

    @NotBlank(message = "Phone is required.")
    @Pattern(message = "Phone format is invalid. Enter the Phone in format (XX)XXXXXXXXX", regexp = "\\([0-9]{2}\\) ([0-9]{9})")
    private String phoneContact;

    @NotBlank(message = "Gender is required. Enter Male or Female")
    private String gender;

    @NotNull(message = "Birthday is required.")
    @Pattern(message = "Birthday format is invalid. Enter the birthday in format dd-mm-yyyy", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String birthday;

    @NotNull(message = "Hiredate is required.")
    @Pattern(message = "Hiredate format is invalid. Enter the hiredate in format dd-mm-yyyy", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String hiredate;

    @NotNull(message = "Company ID is required.")
    private Long employerId;

    @NotNull(message = "Address is required.")
    @Valid
    private AddressConverter address;

    public EmployeeEntity converter() {

        EmployeeEntity employee = new EmployeeEntity();

        employee.setName(name);
        employee.setLastname(lastname);
        employee.setCpf(cpf);
        employee.setAddress(address.converter());
        employee.setPhoneContact(phoneContact);
        employee.setGender(convertStringGenderToEnum(gender.toUpperCase()));
        employee.setBirthday(UtilLocalDateConverter.stringToLocalDate(birthday));
        employee.setHiredate(UtilLocalDateConverter.stringToLocalDate(hiredate));
        employee.setEmployer(getEmployeeCompany());

        return employee;
    }

    private CompanyEntity getEmployeeCompany() {
        CompanyEntity company = new CompanyEntity();
        company.setId(this.employerId);
        return company;
    }

    private Gender convertStringGenderToEnum(String gender) {

        switch (gender) {
            case "MALE", "FEMALE" -> {
                return Enum.valueOf(Gender.class, gender);
            }
            default -> {
                return Gender.UNKNOW;
            }
        }
    }
}
