package com.wendel.test.Fiserv.adapter.gateway.util;

import com.wendel.test.Fiserv.adapter.controller.request.ClientRequest;
import com.wendel.test.Fiserv.domain.validator.ApiException;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;
import com.wendel.test.Fiserv.domain.validator.Validation;
import lombok.AllArgsConstructor;

import static com.wendel.test.Fiserv.adapter.gateway.util.Validator.notNull;

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
