package com.wendel.test.Fiserv.usecase.address;

import com.wendel.test.Fiserv.adapter.controller.response.AddressFromViaCep;

public interface FindNewAddress {
    AddressFromViaCep execute(String zipcode);
}
