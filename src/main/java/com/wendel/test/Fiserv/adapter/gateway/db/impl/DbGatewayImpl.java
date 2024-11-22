package com.wendel.test.Fiserv.adapter.gateway.db.impl;

import com.wendel.test.Fiserv.adapter.controller.database.mapper.ClientMapperDomainAndEntity;
import com.wendel.test.Fiserv.adapter.controller.database.mapper.AddressMapperDomainAndEntity;
import com.wendel.test.Fiserv.adapter.controller.database.repository.ClientRepository;
import com.wendel.test.Fiserv.adapter.controller.database.repository.AddressRepository;
import com.wendel.test.Fiserv.adapter.gateway.db.DbGateway;
import com.wendel.test.Fiserv.domain.Client;
import com.wendel.test.Fiserv.domain.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DbGatewayImpl implements DbGateway {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final AddressMapperDomainAndEntity addressMapperDomainAndEntity;
    private final ClientMapperDomainAndEntity clientMapperDomainAndEntity;

    public DbGatewayImpl(AddressRepository addressRepository, ClientRepository clientRepository, AddressMapperDomainAndEntity addressMapperDomainAndEntity, ClientMapperDomainAndEntity clientMapperDomainAndEntity) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.addressMapperDomainAndEntity = addressMapperDomainAndEntity;
        this.clientMapperDomainAndEntity = clientMapperDomainAndEntity;
    }

    @Override
    public void saveAddress(Address address, String clientId) {
        try {
            log.info("Saving address {}", address.getId());
            addressRepository.save(addressMapperDomainAndEntity.convertAddressToAddressEntity(address, clientId));
        }catch (Exception e){
            log.error("Error while trying to save address with id {}", address.getId());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address getAddress(String id) {
        try {
            return addressMapperDomainAndEntity.convertAddressEntityToAddress(
                    addressRepository.findById(id)
                            .orElseThrow());
        }catch (Exception e){
            log.error("Error while trying to get address with id {}", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAddress(String id) {
        try {
            addressRepository.deleteById(id);
        }catch (Exception e){
            log.error("Error while trying to delete address with id {}", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveClient(Client client) {
        try {
            clientRepository.save(clientMapperDomainAndEntity.convertClientToClientEntity(client));
        }catch (Exception e){
            log.error("Error while trying to save client with id {} - {}", client.getId(), e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public Client getClient(String id) {
        try {
            return clientMapperDomainAndEntity.convertClientEntityToClient(
                    clientRepository.findByCpf(id)
                            .orElseThrow());
        }catch (Exception e){
            log.error("Error while trying to get client with id {} {}", id , e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteClient(String cpf) {
        try {
            clientRepository.updateClient(cpf);
        }catch (Exception e){
            log.error("Error while trying to delete client with id {}", cpf);
            throw new RuntimeException(e);
        }
    }
}
