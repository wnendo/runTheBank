package com.wendel.test.Fiserv.usecase.address.impl;

import com.wendel.test.Fiserv.adapter.controller.response.AddressFromViaCep;
import com.wendel.test.Fiserv.adapter.gateway.web.WebGateway;
import com.wendel.test.Fiserv.domain.validator.ApiException;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;
import com.wendel.test.Fiserv.usecase.address.FindNewAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindNewAddressImpl implements FindNewAddress {
    private final WebGateway webGateway;

    public FindNewAddressImpl(WebGateway webGateway) {
        this.webGateway = webGateway;
    }

    @Override
    public AddressFromViaCep execute(String zipcode) {
        try {
            log.info("Searching for address by zipcode {}", zipcode);
            return webGateway.getAddressByZipcode(zipcode);

        } catch (Exception e) {
            log.error("Error while searching address {} - {}", zipcode, e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while searching address" + e));
        }
    }
}
