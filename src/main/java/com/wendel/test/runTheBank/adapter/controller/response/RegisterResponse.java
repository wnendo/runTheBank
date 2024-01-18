package com.wendel.test.runTheBank.adapter.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.enuns.RegisterStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse {
    private String name;
    private String cpfOrCnpj;
    private String address;
    private String password;
    private String message;
    private String id;
    private RegisterStatus registerStatus;
    private List<Account> account;
}
