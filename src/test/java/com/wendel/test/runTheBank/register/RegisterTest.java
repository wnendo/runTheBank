package com.wendel.test.runTheBank.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendel.test.runTheBank.adapter.controller.AddressController;
import com.wendel.test.runTheBank.adapter.controller.request.AddressRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AddressResponse;
import com.wendel.test.runTheBank.adapter.gateway.util.ClientValidate;
import com.wendel.test.runTheBank.usecase.address.GetAddress;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public final class RegisterTest {
    private AddressController addressController;
    @Mock
    private GetAddress getAddress;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        addressController = new AddressController(getAddress);
    }

}
