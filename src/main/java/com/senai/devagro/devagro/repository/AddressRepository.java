package com.senai.devagro.devagro.repository;

import com.senai.devagro.devagro.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    boolean existsByPostalcodeAndNumber(String postalcode, Integer number);

    Optional<AddressEntity> findByPostalcodeAndNumber(String postalcode, Integer number);

}
