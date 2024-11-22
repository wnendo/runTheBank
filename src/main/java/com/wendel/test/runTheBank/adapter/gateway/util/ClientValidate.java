package com.wendel.test.runTheBank.adapter.gateway.util;

import com.wendel.test.runTheBank.adapter.controller.request.AddressRequest;
import com.wendel.test.runTheBank.adapter.controller.request.ClientRequest;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.domain.validator.Validation;
import lombok.AllArgsConstructor;

import static com.wendel.test.runTheBank.adapter.gateway.util.Validator.notNull;

@AllArgsConstructor
public class ClientValidate implements Validation<ClientRequest> {
    private final CpfCnpjValidator cpfCnpjValidator;

    public void validate(ClientRequest clientRequest){
        notNull(clientRequest, ExceptionMessage.NOT_NULL);
        notNull(clientRequest.getCpf(), ExceptionMessage.ERROR_CPF_CNPJ_NULL);
        if(!cpfCnpjValidator.isCnpj(clientRequest.getCpf()) && !cpfCnpjValidator.isCpf(clientRequest.getCpf()))
            throw new ApiException(ExceptionMessage.ERROR_CPF_CNPJ_VALIDATE);
    }
}
