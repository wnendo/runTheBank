package com.wendel.test.Fiserv.adapter.gateway.web;

import com.wendel.test.Fiserv.adapter.controller.response.AddressFromViaCep;

public interface WebGateway {
    AddressFromViaCep getAddressByZipcode(String zipcode);
}
