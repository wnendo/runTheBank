package com.wendel.test.runTheBank.adapter.controller.mapper;

import com.wendel.test.runTheBank.adapter.controller.response.AddressFromViaCep;
import com.wendel.test.runTheBank.adapter.controller.response.AddressResponse;
import com.wendel.test.runTheBank.domain.Address;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddressMapper {

    public Address convertAddressFromViaCepToAddress(AddressFromViaCep addressFromViaCep) {
        return Address.builder()
                .id(UUID.randomUUID().toString())
                .address(addressFromViaCep.getLogradouro())
                .neighborhood(addressFromViaCep.getBairro())
                .zipcode(addressFromViaCep.getCep())
                .city(addressFromViaCep.getLocalidade())
                .additionalAddress(addressFromViaCep.getComplemento())
                .build();
    }


    public AddressResponse convertAddressDataToAddress(Address address) {
        return AddressResponse.builder()
                .address(address.getAddress())
                .neighborhood(address.getNeighborhood())
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .additionalAddress(address.getAdditionalAddress())
                .build();
    }

}
