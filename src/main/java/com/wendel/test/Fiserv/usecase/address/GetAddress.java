package com.wendel.test.Fiserv.usecase.address;

import com.wendel.test.Fiserv.adapter.controller.response.AddressResponse;

public interface GetAddress {
    AddressResponse execute(String id);
}
