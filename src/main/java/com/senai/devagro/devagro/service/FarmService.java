package com.senai.devagro.devagro.service;

import com.senai.devagro.devagro.dto.FarmDTO;
import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.repository.FarmRepository;
import com.senai.devagro.devagro.service.exceptions.EntityNotFoundException;
import com.senai.devagro.devagro.service.exceptions.EntityNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmService {

    @Autowired
    private FarmRepository repository;

    /**
     * Busca todas as fazendas cadastradas no banco de dados e converte para dto (FarmDTO::new).
     *
     * @return uma lista com o dto das fazendas que foram encontradas no banco de dados.
     */
    public List<FarmDTO> findAllFarms() {
        return repository.findAll().stream().map(FarmDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca uma fazenda no banco de dados e converte para dto.
     *
     * @return o dto da fazenda que foi encontrada pelo ID.
     */
    public FarmDTO findFarmById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null!");
        }

        Optional<FarmEntity> farm = repository.findById(id);

        if (farm.isEmpty()) {
            throw new EntityNotFoundException("Farm with id " + id + " does not exists!");
        }

        return new FarmDTO(farm.get());

    }

    /**
     * Cadastra uma fazenda no banco de dados com base nos dados informados.
     *
     * @param entity
     * @return o dto da fazenda que foi cadastrada.
     */
    public FarmDTO createFarm(FarmEntity entity) {
        if (entity == null) {
            throw new EntityNullException("Farm cannot be empty or null!");
        }

        FarmEntity farm = repository.save(entity);

        return new FarmDTO(farm);

    }

    /**
     * Atualiza uma fazenda no banco de dados com base no ID e nos novos dados da fazenda.
     *
     * @param id, newFarm
     * @return o id da fazenda que foi alterada.
     */
    public Long updateFarmById(Long id, FarmEntity newFarm) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null!");
        }

        Optional<FarmEntity> farm = repository.findById(id);

        if (farm.isEmpty()) {
            throw new EntityNotFoundException("Farm with id " + id + " does not exists!");
        }

        farm.get().setName(newFarm.getName());
        farm.get().setAddress(newFarm.getAddress());
        farm.get().setCompany(newFarm.getCompany());
        farm.get().setGrainProduced(newFarm.getGrainProduced());
        farm.get().setInitialInventoryKg(newFarm.getInitialInventoryKg());
        farm.get().setLastHarvest(newFarm.getLastHarvest());

        createFarm(farm.get());

        return id;

    }

    /**
     * Deleta uma fazenda no banco de dados com base no ID informado.
     *
     * @param id
     * @return o id da fazenda que foi deletada.
     */
    public Long deleteFarmById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null!");
        }

        Optional<FarmEntity> farm = repository.findById(id);

        if (farm.isEmpty()) {
            throw new EntityNotFoundException("Farm with id " + id + " does not exists!");
        }

        repository.delete(farm.get());

        return id;

    }

}
