package com.wendel.test.runTheBank.adapter.controller.database.mapper;

import com.wendel.test.runTheBank.adapter.controller.database.entity.ClientEntity;
import com.wendel.test.runTheBank.adapter.controller.database.entity.AddressEntity;
import com.wendel.test.runTheBank.domain.Address;
import com.wendel.test.runTheBank.domain.Client;
import com.wendel.test.runTheBank.usecase.cipher.DecryptRequest;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import org.springframework.stereotype.Component;

@Component
public class ClientMapperDomainAndEntity {
    private final EncryptRequest encryptRequest;
    private final DecryptRequest decryptRequest;

    public ClientMapperDomainAndEntity(EncryptRequest encryptRequest, DecryptRequest decryptRequest) {
        this.encryptRequest = encryptRequest;
        this.decryptRequest = decryptRequest;
    }

    public ClientEntity convertClientToClientEntity(Client client) {
        return ClientEntity.builder()
                .id(client.getId())
                .cpf(encryptRequest.execute(client.getCpf()))
                .name(client.getName())
                .age(client.getAge())
                .addressEntity(client.getAddress().getId() == null ? null : AddressEntity.builder().id(client.getAddress().getId()).build())
                .build();
    }

    public Client convertClientEntityToClient(ClientEntity clientEntity) {
        return Client.builder()
                .id(clientEntity.getId())
                .cpf(decryptRequest.execute(clientEntity.getCpf()))
                .age(clientEntity.getAge())
                .name(clientEntity.getName())
                .address(clientEntity.getAddressEntity() == null ? null : convertAddressEntityToAddress(clientEntity.getAddressEntity()))
                .build();
    }

    public Address convertAddressEntityToAddress(AddressEntity address){
        return Address.builder()
                .id(address.getId())
                .address(address.getAddress())
                .neighborhood(address.getNeighborhood())
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .additionalAddress(address.getAdditionalAddress())
                .build();
    }



}
