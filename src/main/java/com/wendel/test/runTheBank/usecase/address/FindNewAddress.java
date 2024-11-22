package com.wendel.test.runTheBank.usecase.address;

import com.wendel.test.runTheBank.adapter.controller.request.AddressRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AddressFromViaCep;
import com.wendel.test.runTheBank.adapter.controller.response.AddressResponse;

public interface FindNewAddress {
    AddressFromViaCep execute(String zipcode);
}
