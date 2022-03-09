package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.EmployeeEntity;
import com.senai.devagro.devagro.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Long id;
    private String name;
    private String lastname;
    private String cpf;
    private AddressDTO address;
    private String phoneContact;
    private String gender;
    private String birthday;
    private String hiredate;
    private CompanyDTO company;

    public EmployeeDTO(Long id, String name, String lastname, String cpf, AddressDTO address, String phoneContact, Gender gender, LocalDate birthday,
                       LocalDate hiredate, CompanyDTO company) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.address = address;
        this.phoneContact = phoneContact;
        this.gender = gender.getGenderDescription();
        this.birthday = dateTimeFormatter.format(birthday);
        this.hiredate = dateTimeFormatter.format(hiredate);
        this.company = company;
    }

    public EmployeeDTO(EmployeeEntity employee) {
        id = employee.getId();
        name = employee.getName();
        lastname = employee.getLastname();
        cpf = employee.getCpf();
        address = new AddressDTO(employee.getAddress());
        phoneContact = employee.getPhoneContact();
        gender = employee.getGender().getGenderDescription();
        birthday = dateTimeFormatter.format(employee.getBirthday());
        hiredate = dateTimeFormatter.format(employee.getHiredate());
        company = new CompanyDTO(employee.getEmployer());
    }

}
