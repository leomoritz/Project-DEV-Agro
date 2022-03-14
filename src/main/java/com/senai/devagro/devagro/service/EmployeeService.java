package com.senai.devagro.devagro.service;

import com.senai.devagro.devagro.dto.EmployeeDTO;
import com.senai.devagro.devagro.model.EmployeeEntity;
import com.senai.devagro.devagro.model.enums.Gender;
import com.senai.devagro.devagro.repository.EmployeeRepository;
import com.senai.devagro.devagro.service.exceptions.EntityAlreadyExistsException;
import com.senai.devagro.devagro.service.exceptions.EntityNotFoundException;
import com.senai.devagro.devagro.service.exceptions.EntityNullException;
import com.senai.devagro.devagro.service.exceptions.InvalidEnumException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CompanyService companyService;

    /**
     * Busca todos os funcionários cadastrados no banco de dados e converte para dto (EmployeeDTO::new).
     *
     * @return uma lista com o dto dos funcionários que foram encontrados no banco de dados.
     */
    public List<EmployeeDTO> findAllEmployees() {
        return repository.findAll().stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca um funcionário no banco de dados com base no ID informado.
     *
     * @param id
     * @return o dto do funcionário que foi encontrado pelo ID.
     */
    public EmployeeDTO findEmployeeById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null.");
        }

        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isEmpty()) {
            throw new EntityNotFoundException("Employee with id " + id + " does not exists!");
        }

        return new EmployeeDTO(employee.get());

    }

    /**
     * Cadastra um funcionário no banco de dados com base nos dados informados.
     *
     * @param entity
     * @return o dto do funcionário que foi cadastrado.
     */
    public EmployeeDTO createEmployee(EmployeeEntity entity) {
        if (entity == null) {
            throw new EntityNullException("Employee cannot be empty or null.");
        }

        if (entity.getGender() == Gender.UNKNOW) {
            throw new InvalidEnumException("Gender does not exists. Enter Male or Female.");
        }

        if (repository.existsByCpf(entity.getCpf())) {
            throw new EntityAlreadyExistsException("Employee with cpf " + entity.getCpf() + " already exists.");
        }

        addressService.createOrUpdateAddress(entity.getAddress()).ifPresent(entity::setAddress);
        companyService.getCompanyIfAlreadyExists(entity.getEmployer()).ifPresent(entity::setEmployer);

        EmployeeEntity employee = repository.save(entity);

        return new EmployeeDTO(employee);
    }

    /**
     * Atualiza um funcionário no banco de dados com base no ID e nos novos dados de funcionário informados.
     *
     * @param id, newEmployee
     * @return o id do funcionário que foi alterado.
     */
    public Long updateEmployeeById(Long id, EmployeeEntity newEmployee) {
        if (id == null || newEmployee == null) {
            throw new EntityNullException("Id / Employee cannot be empty or null.");
        }

        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isEmpty()) {
                throw new EntityNotFoundException("Employee with id " + id + " does not exists!");
            }

            if (!Objects.equals(newEmployee.getCpf(), employee.get().getCpf())) {
                if (repository.existsByCpf(newEmployee.getCpf())) {
                    throw new EntityAlreadyExistsException("Employee with cpf " + newEmployee.getCpf() + " already exists.");
                }
            }

            if (newEmployee.getGender() == Gender.UNKNOW) {
                throw new InvalidEnumException("Gender does not exists. Enter Male or Female.");
        }

        addressService.createOrUpdateAddress(newEmployee.getAddress()).ifPresent(newEmployee::setAddress);
        companyService.getCompanyIfAlreadyExists(newEmployee.getEmployer()).ifPresent(newEmployee::setEmployer);

        employee.get().setName(newEmployee.getName());
        employee.get().setLastname(newEmployee.getLastname());
        employee.get().setCpf(newEmployee.getCpf());
        employee.get().setPhoneContact(newEmployee.getPhoneContact());
        employee.get().setGender(newEmployee.getGender());
        employee.get().setBirthday(newEmployee.getBirthday());
        employee.get().setHiredate(newEmployee.getHiredate());
        employee.get().setEmployer(newEmployee.getEmployer());
        employee.get().setAddress(newEmployee.getAddress());

        repository.save(employee.get());

        return id;

    }

    /**
     * Deleta um funcionário no banco de dados com base no ID do mesmo.
     *
     * @param id
     * @return o id do funcionário que foi deletado
     */
    public Long deleteEmployeeById(Long id) {
        if (id == null) {
            throw new EntityNullException("Employee ID cannot be empty or null.");
        }

        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isEmpty()) {
            throw new EntityNotFoundException("Employee with id " + id + " does not exists!");
        }

        repository.delete(employee.get());

        return id;

    }

}
