package com.wendel.test.Fiserv.usecase.address;

import com.wendel.test.Fiserv.adapter.controller.request.AddressRequest;
import com.wendel.test.Fiserv.adapter.controller.response.AddressResponse;

public interface CreateAddress {
    AddressResponse execute(AddressRequest addressRequest);
}
