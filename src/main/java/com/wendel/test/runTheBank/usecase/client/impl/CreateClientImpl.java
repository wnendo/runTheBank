package com.wendel.test.runTheBank.usecase.client.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.AddressMapper;
import com.wendel.test.runTheBank.adapter.controller.mapper.ClientMapper;
import com.wendel.test.runTheBank.adapter.controller.request.ClientRequest;
import com.wendel.test.runTheBank.adapter.controller.response.ClientResponse;
import com.wendel.test.runTheBank.adapter.gateway.web.WebGateway;
import com.wendel.test.runTheBank.domain.Address;
import com.wendel.test.runTheBank.domain.Client;
import com.wendel.test.runTheBank.usecase.address.SaveAddress;
import com.wendel.test.runTheBank.usecase.client.CreateClient;
import com.wendel.test.runTheBank.usecase.client.SaveClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

            var address = createAddress(clientRequest.getZipcode());
            var client = createClient(clientRequest, address.getZipcode());

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

    private Address createAddress(String zipcode){

        log.info("Searching for address by zipcode {}", zipcode);
        var addressFromViaCep = webGateway.getAddressByZipcode(zipcode);
        var address = addressMapper.convertAddressFromViaCepToAddress(addressFromViaCep);

        log.info("Saving Address");
        saveAddress.execute(address);

        return address;
    }

    private Client createClient(ClientRequest clientRequest, String zipcode){
        var client = clientMapper.convertClientRequestToClient(clientRequest, zipcode);
        log.info("Creating client with id {}", client.getId());

        saveClient.execute(client);
        log.info("Client created successfully");

        return client;
    }
}
