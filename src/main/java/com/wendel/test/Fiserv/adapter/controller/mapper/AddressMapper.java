package com.wendel.test.Fiserv.adapter.controller.mapper;

import com.wendel.test.Fiserv.adapter.controller.response.AddressFromViaCep;
import com.wendel.test.Fiserv.adapter.controller.response.AddressResponse;
import com.wendel.test.Fiserv.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address convertAddressFromViaCepToAddress(AddressFromViaCep addressFromViaCep) {
        return Address.builder()
                .address(addressFromViaCep.getLogradouro())
                .neighborhood(addressFromViaCep.getBairro())
                .zipcode(addressFromViaCep.getCep())
                .city(addressFromViaCep.getLocalidade())
                .additionalAddress(addressFromViaCep.getComplemento())
                .build();
    }


    public AddressResponse convertAddressDataToAddress(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .address(address.getAddress())
                .neighborhood(address.getNeighborhood())
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .additionalAddress(address.getAdditionalAddress())
                .clientId(address.getId())
                .build();
    }

    public Address convertAddressResponseToAddress(AddressResponse address) {
        return Address.builder()
                .id(address.getId())
                .address(address.getAddress())
                .neighborhood(address.getNeighborhood())
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .additionalAddress(address.getAdditionalAddress())
                .client(address.getClientId())
                .build();
    }

}
