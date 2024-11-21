package com.wendel.test.runTheBank.adapter.controller.database.mapper;

import com.wendel.test.runTheBank.adapter.controller.database.entity.ClientEntity;
import com.wendel.test.runTheBank.adapter.controller.database.entity.AddressEntity;
import com.wendel.test.runTheBank.domain.Address;
import com.wendel.test.runTheBank.domain.Client;
import com.wendel.test.runTheBank.usecase.cipher.DecryptRequest;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapperDomainAndEntity {
    private final EncryptRequest encryptRequest;
    private final DecryptRequest decryptRequest;

    public AddressMapperDomainAndEntity(EncryptRequest encryptRequest, DecryptRequest decryptRequest) {
        this.encryptRequest = encryptRequest;
        this.decryptRequest = decryptRequest;
    }

    public AddressEntity convertAddressToAddressEntity(Address address) {
        return AddressEntity.builder()
                .id(address.getId())
                .address(address.getAddress())
                .number(address.getNeighborhood())
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .additionalAddress(address.getAdditionalAddress())
                .build();
    }
    public Address convertAddressEntityToAddress(AddressEntity addressEntity) {
        return Address.builder()
                .id(addressEntity.getId())
                .address(addressEntity.getAddress())
                .neighborhood(addressEntity.getNumber())
                .zipcode(addressEntity.getZipcode())
                .client(convertClientListEntityToClientList(addressEntity.getClientEntity()))
                .city(addressEntity.getCity())
                .additionalAddress(addressEntity.getAdditionalAddress())
                .build();
    }

    public List<Client> convertClientListEntityToClientList(List<ClientEntity> accountEntities){
        return accountEntities.stream().map(clientEntity ->
                Client.builder()
                        .id(clientEntity.getId())
                        .addressId(clientEntity.getAddressEntity().getId())
                        .cpf(clientEntity.getCpf())
                        .age(clientEntity.getAge())
                        .name(clientEntity.getName())
                        .build()
        ).collect(Collectors.toList());
    }

}
