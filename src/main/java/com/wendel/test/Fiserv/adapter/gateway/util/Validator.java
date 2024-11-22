package com.wendel.test.Fiserv.adapter.gateway.util;

import com.wendel.test.Fiserv.domain.validator.ApiException;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;

import java.util.Objects;

public abstract class Validator {
    private Validator(){}

    public static void notNull(Object object, ExceptionMessage exceptionMessage){
        if(Objects.isNull(object)){
            throw new ApiException(exceptionMessage);
        }
    }


}
