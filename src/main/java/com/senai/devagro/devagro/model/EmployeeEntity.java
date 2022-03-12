package com.senai.devagro.devagro.model;

import com.senai.devagro.devagro.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(name = "phone_contact")
    private String phoneContact;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hiredate;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private CompanyEntity employer;

    public EmployeeEntity(String name, String lastname, String cpf, AddressEntity address, String phoneContact, Gender gender, LocalDate birthday,
                          LocalDate hiredate, CompanyEntity employer) {
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
