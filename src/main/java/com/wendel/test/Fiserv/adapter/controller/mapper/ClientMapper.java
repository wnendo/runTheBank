package com.wendel.test.Fiserv.adapter.controller.mapper;

import com.wendel.test.Fiserv.adapter.controller.request.ClientRequest;
import com.wendel.test.Fiserv.adapter.controller.response.ClientResponse;
import com.wendel.test.Fiserv.domain.Address;
import com.wendel.test.Fiserv.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client convertClientRequestToClient(ClientRequest clientRequest, String clientId, Address address) {

        return Client.builder()
                .id(clientId)
                .cpf(clientRequest.getCpf())
                .name(clientRequest.getName())
                .age(clientRequest.getAge())
                .address(address)
                .build();
    }

    public Client convertClientResponseToClient(ClientResponse clientResponse) {

        return Client.builder()
                .id(clientResponse.getId())
                .cpf(clientResponse.getCpf())
                .name(clientResponse.getName())
                .age(clientResponse.getAge())
                .address(clientResponse.getAddress())
                .build();
    }
    public ClientResponse convertClientToClientResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .age(client.getAge())
                .address(client.getAddress())
                .build();
    }
}
