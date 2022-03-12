package com.senai.devagro.devagro.dto;

import com.senai.devagro.devagro.model.AddressEntity;
import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.model.EmployeeEntity;
import com.senai.devagro.devagro.model.enums.Gender;
import com.senai.devagro.devagro.utils.UtilLocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    private Long id;
    private String name;
    private String lastname;
    private String cpf;
    private AddressDTO address;
    private String phoneContact;
    private String gender;
    private String birthday;
    private String hiredate;
    private CompanyEmployeeDTO company;

    public EmployeeDTO(Long id, String name, String lastname, String cpf, AddressEntity addressEntity, String phoneContact, Gender gender, LocalDate birthday,
                       LocalDate hiredate, CompanyEntity companyEntity) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.address = new AddressDTO(addressEntity);
        this.phoneContact = phoneContact;
        this.gender = gender.getGenderDescription();
        this.birthday = UtilLocalDateConverter.localDateToString(birthday);
        this.hiredate = UtilLocalDateConverter.localDateToString(hiredate);
        this.company = new CompanyEmployeeDTO(companyEntity);
    }

    public EmployeeDTO(EmployeeEntity employee) {
        id = employee.getId();
        name = employee.getName();
        lastname = employee.getLastname();
        cpf = employee.getCpf();
        address = new AddressDTO(employee.getAddress());
        phoneContact = employee.getPhoneContact();
        gender = employee.getGender().getGenderDescription();
        birthday = UtilLocalDateConverter.localDateToString(employee.getBirthday());
        hiredate = UtilLocalDateConverter.localDateToString(employee.getHiredate());
        company = new CompanyEmployeeDTO(employee.getEmployer());
    }

}
