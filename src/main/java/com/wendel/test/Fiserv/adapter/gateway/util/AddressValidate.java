package com.wendel.test.Fiserv.adapter.gateway.util;

import com.wendel.test.Fiserv.adapter.controller.request.AddressRequest;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;
import com.wendel.test.Fiserv.domain.validator.Validation;
import lombok.AllArgsConstructor;

import static com.wendel.test.Fiserv.adapter.gateway.util.Validator.notNull;

@AllArgsConstructor
public class AddressValidate implements Validation<AddressRequest> {

    public void validate(AddressRequest addressRequest){
        notNull(addressRequest, ExceptionMessage.NOT_NULL);
        notNull(addressRequest.getZipcode(), ExceptionMessage.ERROR_CPF_CNPJ_NULL);
    }
}
