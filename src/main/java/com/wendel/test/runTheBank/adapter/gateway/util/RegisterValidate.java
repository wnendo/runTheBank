package com.wendel.test.runTheBank.adapter.gateway.util;

import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.domain.validator.Validation;
import lombok.AllArgsConstructor;

import static com.wendel.test.runTheBank.adapter.gateway.util.Validator.notNull;

@AllArgsConstructor
public class RegisterValidate implements Validation<RegisterRequest> {
    private final CpfCnpjValidator cpfCnpjValidator;

    public void validate(RegisterRequest registerRequest){
        notNull(registerRequest, ExceptionMessage.NOT_NULL);
        notNull(registerRequest.getCpfOrCnpj(), ExceptionMessage.ERROR_CPF_CNPJ_NULL);
        if(!cpfCnpjValidator.isCnpj(registerRequest.getCpfOrCnpj()) && !cpfCnpjValidator.isCpf(registerRequest.getCpfOrCnpj()))
            throw new ApiException(ExceptionMessage.ERROR_CPF_CNPJ_VALIDATE);
    }
}
