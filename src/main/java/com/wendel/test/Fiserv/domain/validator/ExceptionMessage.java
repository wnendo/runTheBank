package com.wendel.test.Fiserv.domain.validator;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    // General
    INTERNAL_ERROR("Ops... Something went wrong. Try again later."),
    ERROR_CPF_CNPJ_NULL("Missing CPF or CNPJ information"),
    ERROR_ZIPCODE_NULL("Missing ZIPCODE information"),
    ERROR_CPF_CNPJ_VALIDATE("CPF or CPNJ is not valid, please try again!"),
    NOT_NULL("Request cannot be null"),
    UNAUTHORIZED("Unauthorized"),
    INVALID_JSON("Invalid json");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}