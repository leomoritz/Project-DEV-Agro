package com.senai.devagro.devagro.repository;

import com.senai.devagro.devagro.dto.GrainStockByCompanyDTO;
import com.senai.devagro.devagro.model.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<FarmEntity, Long> {

    boolean existsByNameAndAddress_IdAndCompany_Id(String name, Long addressId, Long companyId);

    /**
     * @param companyId
     * @return retorna a lista de fazendas de uma empresa
     */
    public List<FarmEntity> findAllByCompanyId(Long companyId);

    /**
     * @param companyId
     * @return retorna a quantidade de fazendas de uma empresa.
     */
    public Long countByCompanyId(Long companyId);

    /**
     * Consulta customizada para buscar os grãos associados à fazenda com base na empresa informada.
     *
     * @param companyId
     * @return
     */
    @Query("SELECT obj FROM FarmEntity obj JOIN FETCH obj.grainProduced WHERE obj.company.id = :companyId ORDER BY obj.initialInventoryKg ASC")
    List<FarmEntity> findAllGrainsInStockByCompanyId(Long companyId);

}
