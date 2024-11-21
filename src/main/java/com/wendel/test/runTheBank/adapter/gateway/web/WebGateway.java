package com.wendel.test.runTheBank.adapter.gateway.web;

import com.wendel.test.runTheBank.adapter.controller.response.AddressFromViaCep;

public interface WebGateway {
    AddressFromViaCep getAddressByZipcode(String zipcode);
}
