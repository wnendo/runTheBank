package com.wendel.test.runTheBank.adapter.gateway.util;

import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.domain.validator.Validation;

import static com.wendel.test.runTheBank.adapter.gateway.util.Validator.notNull;

public class TransactionValidate implements Validation<TransactionRequest> {

    public void validate(TransactionRequest transactionRequest){
        notNull(transactionRequest, ExceptionMessage.NOT_NULL);
        notNull(transactionRequest.getToAccount(), ExceptionMessage.ERROR_TO_ACCOUNT);
        notNull(transactionRequest.getFromAccount(), ExceptionMessage.ERROR_FROM_ACCOUNT);
        notNull(transactionRequest.getAmount(), ExceptionMessage.ERROR_VALUE_TRANSACTION);
    }
}
