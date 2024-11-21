package com.wendel.test.runTheBank.adapter.controller.mapper;

import com.wendel.test.runTheBank.adapter.controller.request.ClientRequest;
import com.wendel.test.runTheBank.adapter.controller.response.ClientResponse;
import com.wendel.test.runTheBank.domain.Address;
import com.wendel.test.runTheBank.domain.Client;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientMapper {

    public Client convertClientRequestToClient(ClientRequest clientRequest, String addressId) {

        return Client.builder()
                .id(UUID.randomUUID().toString())
                .cpf(clientRequest.getCpf())
                .name(clientRequest.getName())
                .age(clientRequest.getAge())
                .addressId(addressId)
                .build();
    }
    public ClientResponse convertClientToClientResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .age(client.getAge())
                .addressId(client.getAddressId())
                .build();
    }
}
