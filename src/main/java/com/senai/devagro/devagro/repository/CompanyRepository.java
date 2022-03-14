package com.senai.devagro.devagro.repository;

import com.senai.devagro.devagro.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsByCnpj(String cnpj);

    Optional<CompanyEntity> findByCnpj(String cnpj);

}
