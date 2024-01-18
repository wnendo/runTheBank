package com.wendel.test.runTheBank.domain.validator;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    // General
    INTERNAL_ERROR("Ops... Something went wrong. Try again later."),
    ERROR_FROM_ACCOUNT("Missing FROM ACCOUNT from transaction information"),
    ERROR_TO_ACCOUNT("Missing TO ACCOUNT from transaction information"),
    ERROR_CPF_CNPJ_NULL("Missing CPF or CNPJ information"),
    ERROR_CPF_CNPJ_VALIDATE("CPF or CPNJ is not valid, please try again!"),
    ERROR_VALUE_TRANSACTION("Missing to value from transaction information"),
    NOT_NULL("Request cannot be null"),
    UNAUTHORIZED("Unauthorized");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}