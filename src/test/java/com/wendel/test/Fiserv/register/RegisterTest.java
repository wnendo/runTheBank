package com.wendel.test.Fiserv.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendel.test.Fiserv.adapter.controller.AddressController;
import com.wendel.test.Fiserv.adapter.gateway.util.AddressValidate;
import com.wendel.test.Fiserv.usecase.address.CreateAddress;
import com.wendel.test.Fiserv.usecase.address.FindNewAddress;
import com.wendel.test.Fiserv.usecase.address.GetAddress;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public final class RegisterTest {
    private AddressController addressController;
    @Mock
    private GetAddress getAddress;
    @Mock
    private CreateAddress createAddress;
    @Mock
    private AddressValidate addressValidate;
    @Mock
    private FindNewAddress findNewAddress;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        addressController = new AddressController(getAddress, createAddress, addressValidate, findNewAddress);
    }

}
