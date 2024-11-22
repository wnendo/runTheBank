package com.wendel.test.runTheBank.adapter.gateway.util;

import com.wendel.test.runTheBank.adapter.controller.request.AddressRequest;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.domain.validator.Validation;
import lombok.AllArgsConstructor;

import static com.wendel.test.runTheBank.adapter.gateway.util.Validator.notNull;

@AllArgsConstructor
public class AddressValidate implements Validation<AddressRequest> {

    public void validate(AddressRequest addressRequest){
        notNull(addressRequest, ExceptionMessage.NOT_NULL);
        notNull(addressRequest.getZipcode(), ExceptionMessage.ERROR_CPF_CNPJ_NULL);
    }
}
