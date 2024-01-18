package com.wendel.test.runTheBank.adapter.controller.mapper;

import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.domain.Register;
import com.wendel.test.runTheBank.usecase.cipher.DecryptRequest;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterMapper {

    public Register convertRegisterRequestToRegister(RegisterRequest registerRequest) {
        return Register.builder()
                .id(UUID.randomUUID().toString())
                .name(registerRequest.getName())
                .cpfOrCnpj(registerRequest.getCpfOrCnpj())
                .address(registerRequest.getAddress())
                .password(registerRequest.getPassword())
                .build();
    }
    public RegisterResponse convertRegisterDataToRegister(Register register) {
        return RegisterResponse.builder()
                .name(register.getName())
                .cpfOrCnpj(register.getCpfOrCnpj())
                .address(register.getAddress())
                .password(register.getPassword())
                .account(register.getAccount())
                .build();
    }

}
