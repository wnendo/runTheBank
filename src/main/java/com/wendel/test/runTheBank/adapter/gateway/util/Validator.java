package com.wendel.test.runTheBank.adapter.gateway.util;

import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;

import java.util.Objects;

public abstract class Validator {
    private Validator(){}

    public static void notNull(Object object, ExceptionMessage exceptionMessage){
        if(Objects.isNull(object)){
            throw new ApiException(exceptionMessage);
        }
    }


}
