package com.wendel.test.Fiserv.adapter.controller.database.mapper;

import com.wendel.test.Fiserv.adapter.controller.database.entity.AddressEntity;
import com.wendel.test.Fiserv.adapter.controller.database.entity.ClientEntity;
import com.wendel.test.Fiserv.domain.Address;
import com.wendel.test.Fiserv.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperDomainAndEntity {
    public AddressMapperDomainAndEntity() {

    }

    public AddressEntity convertAddressToAddressEntity(Address address, String clientId) {
        return AddressEntity.builder()
                .id(address.getId())
                .address(address.getAddress())
                .neighborhood(address.getNeighborhood())
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .additionalAddress(address.getAdditionalAddress())
                .clientEntity(clientId == null ? null : ClientEntity.builder().id(clientId).build())
                .build();
    }
    public Address convertAddressEntityToAddress(AddressEntity addressEntity) {
        return Address.builder()
                .id(addressEntity.getId())
                .address(addressEntity.getAddress())
                .neighborhood(addressEntity.getNeighborhood())
                .zipcode(addressEntity.getZipcode())
                .client(addressEntity.getClientEntity() == null ? null : addressEntity.getClientEntity().getId())
                .city(addressEntity.getCity())
                .additionalAddress(addressEntity.getAdditionalAddress())
                .build();
    }

    public Client convertClientEntityToClient(ClientEntity clientEntity){
        return Client.builder()
                        .id(clientEntity.getId())
                        .address(Address.builder().id(clientEntity.getAddressEntity().getId()).build())
                        .cpf(clientEntity.getCpf())
                        .age(clientEntity.getAge())
                        .name(clientEntity.getName())
                        .build();
    }

}

