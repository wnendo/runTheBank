package com.wendel.test.runTheBank.usecase.address;

import com.wendel.test.runTheBank.adapter.controller.response.AddressResponse;

public interface GetAddress {
    AddressResponse execute(String id);
}
