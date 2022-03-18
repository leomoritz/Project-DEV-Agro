package com.senai.devagro.devagro.service;

import com.senai.devagro.devagro.dto.GrainDTO;
import com.senai.devagro.devagro.dto.GrainStockByCompanyDTO;
import com.senai.devagro.devagro.model.CompanyEntity;
import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.model.GrainEntity;
import com.senai.devagro.devagro.repository.GrainRepository;
import com.senai.devagro.devagro.service.exceptions.EntityAlreadyAssociatedException;
import com.senai.devagro.devagro.service.exceptions.EntityAlreadyExistsException;
import com.senai.devagro.devagro.service.exceptions.EntityNotFoundException;
import com.senai.devagro.devagro.service.exceptions.EntityNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrainService {

    @Autowired
    private GrainRepository repository;

    @Autowired
    private CompanyService companyService;

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
     * Busca todos os grãos de uma determinada empresa.
     *
     * @param companyId
     * @return uma lista dto de grãos de uma determinada empresa.
     */
    public List<GrainDTO> findAllByCompanyId(Long companyId) {
        return repository.findAllByCompanyId(companyId).stream().map(GrainDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca todos os grãos de uma determinada empresa.
     *
     * @param companyId
     * @return uma lista de grãos de uma determinada empresa.
     */
    protected List<GrainEntity> getAllGrainEntityByCompanyId(Long companyId) {
        return repository.findAllByCompanyId(companyId);

    }

    /**
     * Busca um grão no banco de dados com base no ID informado.
     *
     * @param id
     * @return a entidade do grão que foi encontrado pelo ID.
     */
    public Optional<GrainEntity> getGrainEntityById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null!");
        }

        Optional<GrainEntity> grain = repository.findById(id);

        if (grain.isEmpty()) {
            throw new EntityNotFoundException("Grain with id " + id + " does not exists!");
        }

        return grain;

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

        if (grainAlreadyExist(entity)) {
            throw new EntityAlreadyExistsException("The entered grain " + entity.getName() + " already exists.");
        }

        companyService.getCompanyIfAlreadyExists(entity.getCompany()).ifPresent(entity::setCompany);

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

        companyService.getCompanyIfAlreadyExists(newGrain.getCompany()).ifPresent(newGrain::setCompany);

        if (!(grain.get().equals(newGrain))) {
            if (grainAlreadyExist(newGrain)) {
                throw new EntityAlreadyExistsException("The entered grain already exists.");
            }

            // Não permite trocar o grão de empresa caso já tenha pelo menos uma associação com alguma fazenda da empresa atual.
            if (grainAlreadyAssociatedWithFarm(grain.get(), newGrain)) {
                throw new EntityAlreadyAssociatedException("The grain entered cannot be transferred to another company" +
                        " as it is already associated with a farm.");
            }
        }

        grain.get().setName(newGrain.getName());
        grain.get().setCompany(newGrain.getCompany());
        grain.get().setAverageHarvestTime(newGrain.getAverageHarvestTime());

        repository.save(grain.get());

        return id;

    }

    /**
     * Verifica se o grão já está associado a uma fazenda da empresa.
     *
     * @param grain
     * @param newGrain
     * @return verdadeiro caso o grão já esteja associado a pelo menos uma fazenda da empresa
     */
    private boolean grainAlreadyAssociatedWithFarm(GrainEntity grain, GrainEntity newGrain) {

        if (grain.getCompany() == newGrain.getCompany()) {
            return false;
        }

        Optional<CompanyEntity> company = companyService.getCompanyEntityById(grain.getCompany().getId());

        return company.
                map(companyEntity -> companyEntity.getFarms().stream().
                        anyMatch(farm -> Objects.equals(farm.getGrainProduced().getId(), grain.getId()))).
                orElse(false);
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

    /**
     * Busca um determinado grão do repositório com base no nome e empresa.
     *
     * @param name, company
     * @return um grão.
     */

    public Optional<GrainEntity> getGrainEntityByNameAndCompany(String name, Long companyId) {
        if (name == null || companyId == null) {
            throw new EntityNullException("Grain's name or company cannot be empty or null");
        }

        return repository.findByNameAndCompany_Id(name, companyId);

    }

    /**
     * Verifica se o grão já existe. Se existir então apenas atualiza, se não cria um novo grão.
     *
     * @param grainEntity
     * @return o grão criado ou atualizado.
     */
    protected Optional<GrainEntity> createOrUpdateGrain(GrainEntity grainEntity) {
        Optional<GrainEntity> grain = getGrainEntityByNameAndCompany(grainEntity.getName(), grainEntity.getCompany().getId());

        if (grain.isPresent()) {
            updateGrainById(grain.get().getId(), grainEntity);
            return grain;
        } else {
            Long grainId = createGrain(grainEntity).getId();
            return getGrainEntityById(grainId);
        }
    }

    /**
     * Verifica se o grão já existe.
     *
     * @param newGrain
     * @return verdadeiro se já existir, se não falso.
     */
    public boolean grainAlreadyExist(GrainEntity newGrain) {
        return repository.existsByNameAndAverageHarvestTimeAndCompany_Id(newGrain.getName(), newGrain.getAverageHarvestTime(), newGrain.getCompany().getId());
    }

    /**
     * Verifica se o grão informada já existe. Se existir então apenas atualiza, se não lança uma exceção
     *
     * @param grainProduced
     * @return o grão que já existe.
     */
    protected Optional<GrainEntity> getGrainIfAlreadyExists(GrainEntity grainProduced) {
        return getGrainEntityById(grainProduced.getId());
    }
}
