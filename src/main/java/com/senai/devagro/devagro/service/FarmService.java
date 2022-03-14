package com.senai.devagro.devagro.service;

import com.senai.devagro.devagro.dto.FarmDTO;
import com.senai.devagro.devagro.dto.FarmHarvestDTO;
import com.senai.devagro.devagro.dto.FarmWithdrawalGrainDTO;
import com.senai.devagro.devagro.dto.FarmsByCompanyDTO;
import com.senai.devagro.devagro.model.FarmEntity;
import com.senai.devagro.devagro.repository.FarmRepository;
import com.senai.devagro.devagro.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmService {

    @Autowired
    private FarmRepository repository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private GrainService grainService;

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
     * Busca uma fazenda no banco de dados através do ID.
     *
     * @return a entidade da fazenda que foi encontrada pelo ID.
     */
    public FarmEntity findFarmEntityById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null!");
        }

        Optional<FarmEntity> farm = repository.findById(id);

        if (farm.isEmpty()) {
            throw new EntityNotFoundException("Farm with id " + id + " does not exists!");
        }

        return farm.get();

    }

    /**
     * Busca todas as fazendas cadastradas no banco de dados por meio do ID da empresa e converte para dto (FarmDTO::new).
     *
     * @param companyId
     * @return uma lista com o dto das fazendas que foram encontradas no banco de dados através do ID da empresa.
     */
    public List<FarmDTO> findAllFarmsByCompanyId(Long companyId) {
        return repository.findAllByCompanyId(companyId).stream().map(FarmDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca todas as fazendas cadastradas no banco de dados por meio do ID da empresa.
     *
     * @param companyId
     * @return a quantidade de fazendas encontradas através do ID da empresa.
     */
    public Long countByCompany(Long companyId) {
        return repository.countByCompanyId(companyId);
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

        addressService.createOrUpdateAddress(entity.getAddress()).ifPresent(entity::setAddress);
        companyService.getCompanyIfAlreadyExists(entity.getCompany()).ifPresent(entity::setCompany);
        grainService.createOrUpdateGrain(entity.getGrainProduced()).ifPresent(entity::setGrainProduced);

        if (farmAlreadyExist(entity.getName(), entity.getAddress().getId(), entity.getCompany().getId())) {
            throw new EntityAlreadyExistsException("The entered farm already exists.");
        }

        FarmEntity farm = repository.save(entity);

        return new FarmDTO(farm);

    }

    /**
     * Atualiza uma fazenda no banco de dados com base no ID e nos novos dados da fazenda.
     *
     * @param id
     * @param newFarm
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

        addressService.createOrUpdateAddress(newFarm.getAddress()).ifPresent(newFarm::setAddress);
        companyService.getCompanyIfAlreadyExists(newFarm.getCompany()).ifPresent(newFarm::setCompany);
        grainService.createOrUpdateGrain(newFarm.getGrainProduced()).ifPresent(newFarm::setGrainProduced);

        if (!(farm.get().getName().equalsIgnoreCase(newFarm.getName()) &&
                Objects.equals(farm.get().getAddress().getId(), newFarm.getAddress().getId()) &&
                Objects.equals(farm.get().getCompany().getId(), newFarm.getCompany().getId()))) {
            if (farmAlreadyExist(newFarm.getName(), newFarm.getAddress().getId(), newFarm.getCompany().getId())) {
                throw new EntityAlreadyExistsException("The entered farm already exists.");
            }
        }

        farm.get().setName(newFarm.getName());
        farm.get().setAddress(newFarm.getAddress());
        farm.get().setCompany(newFarm.getCompany());
        farm.get().setGrainProduced(newFarm.getGrainProduced());
        farm.get().setInitialInventoryKg(newFarm.getInitialInventoryKg());
        farm.get().setLastHarvest(newFarm.getLastHarvest());

        repository.save(farm.get());

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

    /**
     * Verifica se a fazenda já existe
     *
     * @param name
     * @param addressId
     * @param companyId
     * @return uma fazenda caso ela exista
     */
    public boolean farmAlreadyExist(String name, Long addressId, Long companyId) {
        return repository.existsByNameAndAddress_IdAndCompany_Id(name, addressId, companyId);
    }

    /**
     * Busca as fazendas de uma determinada empresa e a data da próxima colheita desta fazenda.
     *
     * @param companyId
     * @return uma lista DTO de fazendas de uma empresa com a data prevista da próxima colheita
     */

    public List<FarmsByCompanyDTO> findAllFarmsCompanyWithExpectedDateNextHarvest(Long companyId) {

        List<FarmEntity> farmsCompany = repository.findAllByCompanyId(companyId);

        return farmsCompany.stream().map(
                entity -> new FarmsByCompanyDTO(entity, getExpectedDateNextHarvest(entity))
        ).collect(Collectors.toList());

    }

    /**
     * Calcula a data prevista para a próxima colheita considerando a data da última colheita e o tempo médio de colheita do grão produzido pela fazenda.
     *
     * @param farm
     * @return a data prevista para a próxima colheita.
     */

    private LocalDate getExpectedDateNextHarvest(FarmEntity farm) {

        LocalDate lastHarvest = farm.getLastHarvest();
        Integer averageHarvestTime = farm.getGrainProduced().getAverageHarvestTime();

        return lastHarvest.plusDays(averageHarvestTime);

    }

    /**
     * Registra uma colheita em uma fazenda, que aumenta o estoque de grãos daquela fazenda.
     *
     * @param id
     * @param quantityKgHarvested
     * @return um DTO com o nome da fazenda e do grão, bem como a data da colheita, a quantidade colhida de grãos e a quantidade do estoque após a colheita.
     */

    public FarmHarvestDTO registerHarvestByFarmId(Long id, Double quantityKgHarvested) {

        FarmEntity farm = findFarmEntityById(id);

        if(farm == null){
            throw new EntityNullException("The farm cannot be empty or null.");
        }

        try {
            consistQuantityKg(farm, quantityKgHarvested);
        } catch (Exception e) {
            e.getStackTrace();
        }

        LocalDate harvestDate = LocalDate.now();
        Double currentQuantityStock = farm.getInitialInventoryKg();
        currentQuantityStock += quantityKgHarvested;
        farm.setInitialInventoryKg(currentQuantityStock);

        return new FarmHarvestDTO(farm, quantityKgHarvested, harvestDate);
    }

    public FarmWithdrawalGrainDTO withdrawalGrainByFarmId(Long id, Double quantityKgWithdrawal){

        FarmEntity farm = findFarmEntityById(id);

        if(farm == null){
            throw new EntityNullException("The farm cannot be empty or null.");
        }

        if(quantityKgWithdrawal > farm.getInitialInventoryKg()){
            throw new InsufficientGrainStockException();
        }

        try {
            consistQuantityKg(farm, quantityKgWithdrawal);
        } catch (Exception e) {
            e.getStackTrace();
        }

        LocalDate withdrawalDate = LocalDate.now();
        Double currentQuantityStock = farm.getInitialInventoryKg();
        currentQuantityStock -= quantityKgWithdrawal;
        farm.setInitialInventoryKg(currentQuantityStock);

        return new FarmWithdrawalGrainDTO(farm, quantityKgWithdrawal, withdrawalDate);

    }


    private boolean consistQuantityKg(FarmEntity farm, Double quantityKg) {
        if (quantityKg == null) {
            throw new EntityNullException("The quantity cannot be empty or null.");
        }

        if (quantityKg <= 0) {
            throw new InvalidQuantityException("The quantity cannot be less than or equal to zero.");
        }

        return true;

    }

}
