package com.senai.devagro.devagro.repository;

import com.senai.devagro.devagro.model.GrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrainRepository extends JpaRepository<GrainEntity, Long> {

    /**
     * @param companyId
     * @return retorna a lista de grãos de uma empresa
     */
    public List<GrainEntity> findAllByCompany(Long companyId);

}
