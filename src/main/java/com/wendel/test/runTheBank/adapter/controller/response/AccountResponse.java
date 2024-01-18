package com.wendel.test.runTheBank.adapter.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.domain.enuns.RegisterStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    private String id;
    private String registerId;
    private AccountStatus accountStatus;
    private String message;
    private String agency;
    private BigDecimal balance;
}
