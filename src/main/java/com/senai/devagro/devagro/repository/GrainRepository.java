package com.senai.devagro.devagro.repository;

import com.senai.devagro.devagro.model.GrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrainRepository extends JpaRepository<GrainEntity, Long> {

    boolean existsByNameAndCompany_Id(String name, Long companyId);

    /**
     * @param companyId
     * @return retorna a lista de grãos de uma empresa
     */
    List<GrainEntity> findAllByCompany(Long companyId);

    /**
     * Busca um grão pelo nome e pela empresa
     *
     * @param name
     * @param companyId
     * @return retorna um grão se for encontrado
     */
    Optional<GrainEntity> findByNameAndCompany_Id(String name, Long companyId);

}
