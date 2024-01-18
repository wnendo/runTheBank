package com.wendel.test.runTheBank.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendel.test.runTheBank.adapter.controller.RegisterController;
import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.gateway.util.RegisterValidate;
import com.wendel.test.runTheBank.usecase.register.CreateRegister;
import com.wendel.test.runTheBank.usecase.register.GetRegister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public final class RegisterTest {
    private RegisterController registerController;

    private CreateRegister createRegister;
    private GetRegister getRegister;

    private RegisterValidate registerValidate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() throws Exception {
        createRegister = Mockito.mock(CreateRegister.class);
        registerValidate = Mockito.mock(RegisterValidate.class);
        GetRegister getRegister = objectMapper.readValue("{\"dbGateway\":{\"registerRepository\":{},\"accountRepository\":{},\"transactionRepository\":{},\"registerMapperDomainAndEntity\":{\"encryptRequest\":{},\"decryptRequest\":{}},\"accountMapperDomainAndEntity\":{\"encryptRequest\":{},\"decryptRequest\":{}},\"transactionMapperDomainAndEntity\":{\"encryptRequest\":{},\"decryptRequest\":{}}},\"registerMapper\":{}}", GetRegister.class);
        registerController = new RegisterController(createRegister, getRegister, registerValidate);
//        injectField(registerController, "createRegister", createRegister);
    }

    @Test
    public void testMethodCreateRegister() throws Exception {
        RegisterResponse executeResult = objectMapper.readValue("{\"id\":\"df342d95-9c86-4920-91c7-99b0f01c6eb0\",\"name\":null,\"cpfOrCnpj\":null,\"address\":null,\"password\":null,\"message\":null,\"status\":\"CREATED\",\"account\":null}", RegisterResponse.class);
        Mockito.when(createRegister.execute(any(RegisterRequest.class))).thenReturn(executeResult);
        RegisterRequest registerRequest = objectMapper.readValue("{\"name\":\"Wendel\",\"cpfOrCnpj\":\"42050445857\",\"address\":\"Rua Dois\",\"password\":\"Password\"}", RegisterRequest.class);
        executeResult = registerController.createRegister(registerRequest);
        RegisterResponse executeResultExpected = objectMapper.readValue("{\"id\":\"df342d95-9c86-4920-91c7-99b0f01c6eb0\",\"name\":null,\"cpfOrCnpj\":null,\"address\":null,\"password\":null,\"message\":null,\"status\":\"CREATED\",\"account\":null}", RegisterResponse.class);
        Assert.assertEquals(executeResultExpected, executeResult);
    }@Test
    public void testMethodGetRegister() throws Exception {
        String id = "3265de6a-14fd-4ddc-b396-77d7e4b1dfc9";
        RegisterResponse executeResult = objectMapper.readValue("{\"id\":null,\"name\":\"Wendel\",\"cpfOrCnpj\":\"42050445857\",\"address\":\"Rua Dois\",\"password\":\"Password\",\"message\":null,\"status\":null,\"account\":[]}", RegisterResponse.class);
        Mockito.when(getRegister.execute(eq(id))).thenReturn(executeResult);
        executeResult = registerController.getRegister(id);
        RegisterResponse executeResultExpected = objectMapper.readValue("{\"id\":null,\"name\":\"Wendel\",\"cpfOrCnpj\":\"42050445857\",\"address\":\"Rua Dois\",\"password\":\"Password\",\"message\":null,\"status\":null,\"account\":[]}", RegisterResponse.class);
        Assert.assertEquals(executeResultExpected, executeResult);
    }
}
