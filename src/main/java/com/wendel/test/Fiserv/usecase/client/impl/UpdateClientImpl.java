package com.wendel.test.Fiserv.usecase.client.impl;

import com.wendel.test.Fiserv.adapter.controller.mapper.AddressMapper;
import com.wendel.test.Fiserv.adapter.controller.mapper.ClientMapper;
import com.wendel.test.Fiserv.adapter.controller.request.ClientRequest;
import com.wendel.test.Fiserv.adapter.controller.response.ClientResponse;
import com.wendel.test.Fiserv.adapter.gateway.web.WebGateway;
import com.wendel.test.Fiserv.domain.Address;
import com.wendel.test.Fiserv.domain.Client;
import com.wendel.test.Fiserv.usecase.address.GetAddress;
import com.wendel.test.Fiserv.usecase.address.SaveAddress;
import com.wendel.test.Fiserv.usecase.client.GetClient;
import com.wendel.test.Fiserv.usecase.client.SaveClient;
import com.wendel.test.Fiserv.usecase.client.UpdateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateClientImpl implements UpdateClient {
    private final SaveClient saveClient;
    private final SaveAddress saveAddress;
    private final ClientMapper clientMapper;
    private final WebGateway webGateway;
    private final AddressMapper addressMapper;
    private final GetClient getClient;
    private final GetAddress getAddress;

    public UpdateClientImpl(SaveClient saveClient, SaveAddress saveAddress, ClientMapper clientMapper, WebGateway webGateway, AddressMapper addressMapper, GetClient getClient, GetAddress getAddress) {
        this.saveClient = saveClient;
        this.saveAddress = saveAddress;
        this.clientMapper = clientMapper;
        this.webGateway = webGateway;
        this.addressMapper = addressMapper;
        this.getClient = getClient;
        this.getAddress = getAddress;
    }

    @Override
    public ClientResponse execute(ClientRequest clientRequest) {
        try {
            var address = new Address();
            var client = clientMapper.convertClientResponseToClient(getClient.execute(clientRequest.getCpf()));

            address = createAddress(clientRequest.getZipcode(), client);

            client.setName(clientRequest.getName());
            client.setAge(clientRequest.getAge());
            client.setAddress(address);

            saveClient.execute(client);

            return ClientResponse.builder()
                    .id(client.getId())
                    .message("Client updated")
                    .build();
        } catch (Exception e) {
            log.error("Error while trying to CREATE client {}", e.getMessage());
            return ClientResponse.builder()
                    .message("[Error] while trying to CREATE client")
                    .build();
        }
    }

    private Address createAddress(String zipcode, Client client) {
        log.info("Creating Address");
        if (zipcode == null && client.getAddress() != null) {
            var addressExisted = client.getAddress();
            saveAddress.execute(addressExisted, client.getId());
            return addressExisted;
        } else {
            log.info("Searching for address by zipcode {}", zipcode);
            var addressFromViaCep = webGateway.getAddressByZipcode(zipcode);
            var address = addressMapper.convertAddressFromViaCepToAddress(addressFromViaCep);
            address.setId(client.getAddress().getId());
            saveAddress.execute(address, client.getId());
            return address;
        }
    }
}
