package com.wendel.test.Fiserv.usecase.client.impl;

import com.wendel.test.Fiserv.adapter.controller.mapper.AddressMapper;
import com.wendel.test.Fiserv.adapter.controller.mapper.ClientMapper;
import com.wendel.test.Fiserv.adapter.controller.request.ClientRequest;
import com.wendel.test.Fiserv.adapter.controller.response.ClientResponse;
import com.wendel.test.Fiserv.adapter.gateway.web.WebGateway;
import com.wendel.test.Fiserv.domain.Address;
import com.wendel.test.Fiserv.domain.Client;
import com.wendel.test.Fiserv.usecase.address.SaveAddress;
import com.wendel.test.Fiserv.usecase.client.CreateClient;
import com.wendel.test.Fiserv.usecase.client.SaveClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CreateClientImpl implements CreateClient {
    private final SaveClient saveClient;
    private final SaveAddress saveAddress;
    private final ClientMapper clientMapper;
    private final WebGateway webGateway;
    private final AddressMapper addressMapper;

    public CreateClientImpl(SaveClient saveClient, SaveAddress saveAddress, ClientMapper clientMapper, WebGateway webGateway, AddressMapper addressMapper) {
        this.saveClient = saveClient;
        this.saveAddress = saveAddress;
        this.clientMapper = clientMapper;
        this.webGateway = webGateway;
        this.addressMapper = addressMapper;
    }

    @Override
    public ClientResponse execute(ClientRequest clientRequest) {
        try {
            Address address = new Address();
            String clientId = UUID.randomUUID().toString();

            var client = createClient(clientRequest, clientId, address);

            if (clientRequest.getZipcode() != null) {
                address = createAddress(clientRequest.getZipcode(), clientId);
            }
            client.setAddress(address);
            saveClient.execute(client);

            return ClientResponse.builder()
                    .id(client.getId())
                    .message("Client created")
                    .build();
        } catch (Exception e) {
            log.error("Error while trying to CREATE client {}", e.getMessage());
            return ClientResponse.builder()
                    .message("[Error] while trying to CREATE client")
                    .build();
        }
    }

    private Address createAddress(String zipcode, String clientId) {

        log.info("Searching for address by zipcode {}", zipcode);
        var addressFromViaCep = webGateway.getAddressByZipcode(zipcode);
        var address = addressMapper.convertAddressFromViaCepToAddress(addressFromViaCep);
        address.setId(UUID.randomUUID().toString());
        log.info("Saving Address");
        saveAddress.execute(address, clientId);

        return address;
    }

    private Client createClient(ClientRequest clientRequest, String clientId, Address address) {
        var client = clientMapper.convertClientRequestToClient(clientRequest, clientId, address);
        log.info("Creating client with id {}", client.getId());

        saveClient.execute(client);
        log.info("Client created successfully");

        return client;
    }
}
