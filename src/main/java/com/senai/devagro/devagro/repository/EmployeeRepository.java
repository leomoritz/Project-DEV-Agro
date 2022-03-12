package com.senai.devagro.devagro.repository;

import com.senai.devagro.devagro.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    boolean existsByCpf(String cpf);

    /**
     * @param companyId
     * @return retorna a lista de colaboradores de uma empresa
     */
    public List<EmployeeEntity> findAllByEmployer(Long companyId);

    /**
     * @param companyId
     * @return retorna a quantidade de colaboradores de uma empresa.
     */
    public Long countByEmployer(Long companyId);

}
