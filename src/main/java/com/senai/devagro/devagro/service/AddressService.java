package com.senai.devagro.devagro.service;

import com.senai.devagro.devagro.dto.AddressDTO;
import com.senai.devagro.devagro.model.AddressEntity;
import com.senai.devagro.devagro.model.EmployeeEntity;
import com.senai.devagro.devagro.repository.AddressRepository;
import com.senai.devagro.devagro.service.exceptions.EntityAlreadyExistsException;
import com.senai.devagro.devagro.service.exceptions.EntityNotFoundException;
import com.senai.devagro.devagro.service.exceptions.EntityNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    /**
     * Busca todos os endereços do repositório e converte para AddressDTO (AddressDTO::new)
     *
     * @return uma lista com endereços convertidos para DTO
     */
    public List<AddressDTO> findAllAddresses() {
        return repository.findAll().stream().map(AddressDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca um determinado endereço do repositório com base no ID e converte para AddressDTO.
     *
     * @param id
     * @return uma endereço convertido para DTO
     */
    public AddressDTO findAddressById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null");
        }

        Optional<AddressEntity> address = repository.findById(id);

        if (address.isEmpty()) {
            throw new EntityNotFoundException("Address with id " + id + " does not exists!");
        }

        return new AddressDTO(address.get());

    }

    /**
     * Busca um determinado endereço do repositório com base no ID.
     *
     * @param id
     * @return uma endereço entity.
     */
    public AddressEntity getAddressEntityById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null");
        }

        Optional<AddressEntity> address = repository.findById(id);

        if (address.isEmpty()) {
            throw new EntityNotFoundException("Address with id " + id + " does not exists!");
        }

        return address.get();
    }

    /**
     * Busca um determinado endereço do repositório com base no cep e número.
     *
     * @param postalcode, number
     * @return uma endereço entity.
     */

    public Optional<AddressEntity> getAddressEntityByPostalcodeAndNumber(String postalcode, Integer number) {
        if (postalcode == null || number == null) {
            throw new EntityNullException("Postalcode or number cannot be empty or null");
        }

        return repository.findByPostalcodeAndNumber(postalcode, number);

    }

    /**
     * Cadastra um endereço no banco de dados.
     *
     * @param entity
     * @return uma endereço convertido para DTO
     */
    public AddressDTO createAddress(AddressEntity entity) {
        if (entity == null) {
            throw new EntityNullException("Address cannot be empty or null");
        }

        AddressEntity address = repository.save(entity);

        return new AddressDTO(address);

    }

    /**
     * Atualiza um endereço no banco de dados com base no ID do endereço e os dados do novo endereço
     *
     * @param id, newEntity
     * @return o id do endereço atualizado
     */
    public Long updateAddressById(Long id, AddressEntity newEntity) {
        if (id == null || newEntity == null) {
            throw new EntityNullException("Id / Address cannot be empty or null");
        }

        Optional<AddressEntity> address = repository.findById(id);

        if (address.isEmpty()) {
            throw new EntityNotFoundException("Address with id " + id + " does not exists!");
        }

        address.get().setAddress(newEntity.getAddress());
        address.get().setCity(newEntity.getCity());
        address.get().setNeighborhood(newEntity.getNeighborhood());
        address.get().setNumber(newEntity.getNumber());
        address.get().setPostalcode(newEntity.getPostalcode());
        address.get().setState(newEntity.getState());

        createAddress(address.get());

        return id;

    }

    /**
     * Deleta um endereço no banco de dados com base no ID do endereço
     *
     * @param id
     * @return o id do endereço que foi deletado
     */
    public Long deleteAddressById(Long id) {
        if (id == null) {
            throw new EntityNullException("Id cannot be empty or null");
        }

        Optional<AddressEntity> address = repository.findById(id);

        if (address.isEmpty()) {
            throw new EntityNotFoundException("Address with id " + id + " does not exists!");
        }

        repository.delete(address.get());

        return id;

    }

    /**
     * Verifica se o endereço já existe. Se existir então apenas atualiza, se não cria um novo endereço.
     *
     * @param addressEntity
     * @return o endereço criado ou atualizado.
     */
    protected Optional<AddressEntity> createOrUpdateAddress(AddressEntity addressEntity) {
        Optional<AddressEntity> address =
                getAddressEntityByPostalcodeAndNumber(addressEntity.getPostalcode(), addressEntity.getNumber());

        if (address.isPresent()) {
            updateAddressById(address.get().getId(), addressEntity);
            return address;
        } else {
            Long addressId = createAddress(addressEntity).getId();
            return Optional.of(getAddressEntityById(addressId));
        }
    }


}
