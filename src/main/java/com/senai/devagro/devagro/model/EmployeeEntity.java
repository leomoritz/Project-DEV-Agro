package com.senai.devagro.devagro.model;

import com.senai.devagro.devagro.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private String cpf;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(name = "phone_contact")
    private String phoneContact;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthday;

    private LocalDate hiredate;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private EmployeeEntity employer;

    public EmployeeEntity(String name, String lastname, String cpf, AddressEntity address, String phoneContact, Gender gender, LocalDate birthday,
                          LocalDate hiredate, EmployeeEntity employer) {
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.address = address;
        this.phoneContact = phoneContact;
        this.gender = gender;
        this.birthday = birthday;
        this.hiredate = hiredate;
        this.employer = employer;
    }

}
