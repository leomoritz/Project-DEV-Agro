package com.senai.devagro.devagro.service;

import com.senai.devagro.devagro.dto.CompanyDTO;
import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.repository.CompanyRepository;
import com.senai.devagro.devagro.service.exceptions.EntityNotFoundException;
import com.senai.devagro.devagro.service.exceptions.EntityNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    /**
     * Busca todas as empresas cadastradas no banco de dados e converte para dto (CompanyDTO::new).
     *
     * @return uma lista com o dto das empresas que foram encontradas no banco de dados.
     */
    public List<CompanyDTO> findAllCompanies() {
        return repository.findAll().stream().map(CompanyDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca uma empresa no banco de dados com base no ID informado.
     *
     * @param id
     * @return o dto da empresa que foi encontrada pelo ID.
     */
    public CompanyDTO findCompanyById(Long id) {
        if (id == null) {
            throw new EntityNullException("Company ID cannot be empty or null.");
        }

        Optional<CompanyEntity> company = repository.findById(id);

        if (company.isEmpty()) {
            throw new EntityNotFoundException("Company with id " + id + " does not exists!");
        }

        return new CompanyDTO(company.get());

    }

    /**
     * Busca uma empresa no banco de dados com base no ID informado.
     *
     * @param id
     * @return o entity da empresa que foi encontrada pelo ID.
     */
    public CompanyEntity getCompanyEntityById(Long id) {
        if (id == null) {
            throw new EntityNullException("Company ID cannot be empty or null.");
        }

        Optional<CompanyEntity> company = repository.findById(id);

        if (company.isEmpty()) {
            throw new EntityNotFoundException("Company with id " + id + " does not exists!");
        }

        return company.get();

    }

    /**
     * Cadastra uma empresa no banco de dados.
     *
     * @param entity
     * @return o dto da empresa cadastrada
     */
    public CompanyDTO createCompany(CompanyEntity entity) {
        if (entity == null) {
            throw new EntityNullException("Company cannot be empty or null.");
        }

        CompanyEntity company = repository.save(entity);

        return new CompanyDTO(company);

    }

    /**
     * Atualiza uma empresa no banco de dados com base no ID da empresa e os novos dados de empresa
     *
     * @param id, newCompany
     * @return o id da empresa que foi alterado
     */
    public Long updateCompanyById(Long id, CompanyEntity newCompany) {
        if (id == null || newCompany == null) {
            throw new EntityNullException("Id / Company cannot be empty or null.");
        }

        Optional<CompanyEntity> company = repository.findById(id);

        if (company.isEmpty()) {
            throw new EntityNotFoundException("Company with id " + id + " does not exists!");
        }

        company.get().setName(newCompany.getName());
        company.get().setCnpj(newCompany.getCnpj());
        company.get().setAddress(newCompany.getAddress());

        createCompany(company.get());

        return id;

    }

    /**
     * Deleta uma empresa no banco de dados com base no ID da empresa
     *
     * @param id
     * @return o id da empresa que foi deletado
     */
    public Long deleteCompanyById(Long id) {
        if (id == null) {
            throw new EntityNullException("Company ID cannot be empty or null.");
        }

        Optional<CompanyEntity> company = repository.findById(id);

        if (company.isEmpty()) {
            throw new EntityNotFoundException("Company with id " + id + " does not exists!");
        }

        repository.delete(company.get());

        return id;

    }

}
