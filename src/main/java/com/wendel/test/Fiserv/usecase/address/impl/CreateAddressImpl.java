package com.wendel.test.Fiserv.usecase.address.impl;

import com.wendel.test.Fiserv.adapter.controller.mapper.AddressMapper;
import com.wendel.test.Fiserv.adapter.controller.mapper.ClientMapper;
import com.wendel.test.Fiserv.adapter.controller.request.AddressRequest;
import com.wendel.test.Fiserv.adapter.controller.response.AddressResponse;
import com.wendel.test.Fiserv.adapter.gateway.web.WebGateway;
import com.wendel.test.Fiserv.domain.validator.ApiException;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;
import com.wendel.test.Fiserv.usecase.address.CreateAddress;
import com.wendel.test.Fiserv.usecase.address.SaveAddress;
import com.wendel.test.Fiserv.usecase.client.GetClient;
import com.wendel.test.Fiserv.usecase.client.SaveClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CreateAddressImpl implements CreateAddress {
    private final WebGateway webGateway;
    private final AddressMapper addressMapper;
    private final GetClient getClient;
    private final SaveAddress saveAddress;
    private final SaveClient saveClient;
    private final ClientMapper clientMapper;

    public CreateAddressImpl(WebGateway webGateway, AddressMapper addressMapper, GetClient getClient, SaveAddress saveAddress, SaveClient saveClient, ClientMapper clientMapper) {
        this.webGateway = webGateway;
        this.addressMapper = addressMapper;
        this.getClient = getClient;
        this.saveAddress = saveAddress;
        this.saveClient = saveClient;
        this.clientMapper = clientMapper;
    }

    @Override
    public AddressResponse execute(AddressRequest addressRequest){
        try{
            log.info("Searching for address by zipcode {}", addressRequest.getZipcode());
            var addressFromViaCep = webGateway.getAddressByZipcode(addressRequest.getZipcode());
            var address = addressMapper.convertAddressFromViaCepToAddress(addressFromViaCep);

            var client = clientMapper.convertClientResponseToClient(getClient.execute(addressRequest.getCpf()));

            address.setId(UUID.randomUUID().toString());
            client.setAddress(address);
            address.setClient(client.getId());
            saveAddress.execute(address,client.getId());
            saveClient.execute(client);

            return AddressResponse.builder()
                    .message("Address was set to CPF " + client.getCpf())
                    .build();
        }catch (Exception e){
            log.error("Error while saving address {} - {}", addressRequest.getZipcode(), e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while saving address"));
        }
    }
}
