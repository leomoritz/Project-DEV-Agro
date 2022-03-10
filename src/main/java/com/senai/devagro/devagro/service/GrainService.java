package com.senai.devagro.devagro.service;

import com.senai.devagro.devagro.dto.GrainDTO;
import com.senai.devagro.devagro.model.GrainEntity;
import com.senai.devagro.devagro.repository.GrainRepository;
import com.senai.devagro.devagro.service.exceptions.EntityNotFoundException;
import com.senai.devagro.devagro.service.exceptions.EntityNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrainService {

    @Autowired
    private GrainRepository repository;

    /**
     * Busca todos os grãos cadastrados no banco de dados e converte para dto (GrainDTO::new).
     *
     * @return uma lista com o dto dos grãos que foram encontrados no banco de dados.
     */
    public List<GrainDTO> findAllGrains() {
        return repository.findAll().stream().map(GrainDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca um grão no banco de dados com base no ID informado.
     *
     * @param id
     * @return o dto do grão que foi encontrado pelo ID.
     */
    public GrainDTO findGrainById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null!");
        }

        Optional<GrainEntity> grain = repository.findById(id);

        if (grain.isEmpty()) {
            throw new EntityNotFoundException("Grain with id " + id + " does not exists!");
        }

        return new GrainDTO(grain.get());

    }

    /**
     * Busca um grão no banco de dados com base no ID informado.
     *
     * @param id
     * @return a entidade do grão que foi encontrado pelo ID.
     */
    public GrainEntity getGrainEntityById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null!");
        }

        Optional<GrainEntity> grain = repository.findById(id);

        if (grain.isEmpty()) {
            throw new EntityNotFoundException("Grain with id " + id + " does not exists!");
        }

        return grain.get();

    }

    /**
     * Cadastra um grão no banco de dados com base nos dados informados.
     *
     * @param entity
     * @return o dto do grão que foi cadastrado.
     */
    public GrainDTO createGrain(GrainEntity entity) {
        if (entity == null) {
            throw new EntityNullException("Grain cannot be empty or null!");
        }

        GrainEntity grain = repository.save(entity);

        return new GrainDTO(grain);

    }

    /**
     * Atualiza um grão no banco de dados com base no ID e nos novos dados de grão informados.
     *
     * @param id, newGrain
     * @return o id do grão que foi alterado.
     */
    public Long updateGrainById(Long id, GrainEntity newGrain) {
        if (id == null || newGrain == null) {
            throw new EntityNullException("Id / Grain cannot be empty or null!");
        }

        Optional<GrainEntity> grain = repository.findById(id);

        if (grain.isEmpty()) {
            throw new EntityNotFoundException("Grain with id " + id + " does not exists!");
        }

        grain.get().setName(newGrain.getName());
        grain.get().setCompany(newGrain.getCompany());
        grain.get().setAverageHarvestTime(newGrain.getAverageHarvestTime());

        createGrain(grain.get());

        return id;

    }

    /**
     * Deleta um grão no banco de dados com base no ID informado.
     *
     * @param id
     * @return o id do grão que foi deletado
     */
    public Long deleteGrainById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id / Grain cannot be empty or null!");
        }

        Optional<GrainEntity> grain = repository.findById(id);

        if (grain.isEmpty()) {
            throw new EntityNotFoundException("Grain with id " + id + " does not exists!");
        }

        repository.delete(grain.get());

        return id;
    }

}
