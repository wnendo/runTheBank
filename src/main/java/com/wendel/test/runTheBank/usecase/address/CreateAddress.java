package com.wendel.test.runTheBank.usecase.address;

import com.wendel.test.runTheBank.adapter.controller.request.AddressRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AddressResponse;
import com.wendel.test.runTheBank.domain.Address;

public interface CreateAddress {
    AddressResponse execute(AddressRequest addressRequest);
}
