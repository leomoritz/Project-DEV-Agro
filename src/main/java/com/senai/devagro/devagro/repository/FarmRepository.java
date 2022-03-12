package com.senai.devagro.devagro.repository;

import com.senai.devagro.devagro.model.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<FarmEntity, Long> {

    //boolean existsByNameAndAnddressAndCompany(String name, Long addressId, Long companyId);

    /**
     * @param companyId
     * @return retorna a lista de fazendas de uma empresa
     */
    public List<FarmEntity> findAllByCompany(Long companyId);

    /**
     * @param companyId
     * @return retorna a quantidade de fazendas de uma empresa.
     */
    public Long countByCompany(Long companyId);

}
